package com.fulan.application.controller.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.domain.AccountManageVo;
import com.fulan.api.security.domain.AccountRole;
import com.fulan.api.security.domain.Resource;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.service.RoleService;
import com.fulan.api.security.vo.RoleAndResourceVo;
import com.fulan.api.system.domain.Level;
import com.fulan.api.system.service.LevelService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.json.JsonUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

/**
 * @Author: zhouyun
 * @Date: 2018/1/18
 */
@Controller
public class ManageAccountController {

	@Autowired
	private AccountService accountClient;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private LevelService levelService;

	@RequestMapping("/manageAccounts/accountFindById")
	public String accountFindById(Model model, HttpServletRequest request, HttpServletResponse response) {
		String idStr = request.getParameter("id");
		if(idStr==null) {
			idStr ="0";
		}
		long id = Long.parseLong(idStr);
		 model.addAttribute("time", new Date());
	     model.addAttribute("message", "根据Id查询用户！");
		// 根据ID查询用户（正常）
		Account acc = accountClient.findByIdForManage(id);
		
		model.addAttribute("acc", acc);
		Response<List<Level>> data = levelService.getLevelList();
		List<Level> lList = data.getData();
		model.addAttribute("lList", lList);
		
		//System.out.println("根据id查询到的用户：" + acc.getAccountName());
		return "security/account-details";
	}
	
	
	@GetMapping("/manageAccounts/listByPages")
	public String listByPage(
			String accountName,
			String telephone,
			Model model,String pageNo,String pageSize){
		if(""==pageNo || null ==pageNo){
    		pageNo="1";
    	}
    	if(""==pageSize || null ==pageSize){
    		pageSize="10";
    	}
    	if(""==accountName || null ==accountName){
    		accountName="";
    	}
    	if(""==telephone || null ==telephone){
    		telephone="";
    	}
        PageInfo<AccountManageVo>  page = accountClient.listByPages(accountName, telephone, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
        model.addAttribute("page", page);
        model.addAttribute("accountName", accountName);
        model.addAttribute("telephone", telephone);
		return "security/account";
		
	}
	
	
	
	@GetMapping("/manageAccounts/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<AccountManageVo>> listByPageAjax(
			String accountName,
			String telephone,
			String pageNo,String pageSize){
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
		PageInfo<AccountManageVo>  accountManageVoList = accountClient.listByPages(accountName, telephone, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		Response<PageInfo<AccountManageVo>> response = new Response<>(Response.SUCCESS, null);		
		response.setData(accountManageVoList);
		return response;
		
	}
	

	
	
	
	@RequestMapping("/manageAccounts/listByPage")
	public String listByPage(Model model, HttpServletRequest request, HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		if(pageNo==null) {
			pageNo = "1";
		}
		String pageSize = request.getParameter("pageSize");
		if(pageSize==null) {
			pageSize = "10";
		}
		String idStr = request.getParameter("id");
		if(idStr==null || idStr=="") {
			idStr ="0";
		}
		long id = Long.parseLong(idStr);
		boolean locked=true;
	     //分页显示
	     PageInfo<Account> pageInfo = accountClient.listByPage(id, locked, Integer.valueOf(pageNo), Integer.valueOf(pageSize), "accountName", "asc","manage");
			//System.err.println("分页查询结果："+pageInfo.toString());
			
		model.addAttribute("page", pageInfo);
		return "security/account";
	}
	
	@RequestMapping("/manageAccounts/update")
	public String update(Model model, HttpServletRequest request, HttpServletResponse response) {
		String idStr = request.getParameter("id");
		if(idStr==null) {
			idStr ="0";
		}
		long id = Long.parseLong(idStr);
		 model.addAttribute("time", new Date());
	     model.addAttribute("message", "根据Id查询用户！");
		// 根据ID查询用户（正常）
		Account acc = accountClient.findByIdForManage(id);
		
		model.addAttribute("acc", acc);
		Response<List<Level>> data = levelService.getLevelList();
		List<Level> lList = data.getData();
		model.addAttribute("lList", lList);
		return "security/account-update";
	}
	@RequestMapping(value="/manageAccounts/updateAccounts", method=RequestMethod.POST)
	@ResponseBody
	public Response<String> updateAccounts(HttpServletRequest request, HttpServletResponse response,Account account) {
		Response<String> resultString =null;
		if(account.getId()==null) {
			account.setCreateTime(new Date());
			account.setUpdateTime(new Date());
			resultString = accountClient.insert(account);
		}else {
			// 更新用户
			account.setUpdateTime(new Date());
			resultString = accountClient.updateAccount(account);
		}
		//System.out.println("操作用户返回结果：" + resultString.getMsg());
		return resultString;
	}
	
	@RequestMapping(value="/manageAccount/resetPassword", method=RequestMethod.POST)
	@ResponseBody
	public Response<String> resetPassword(HttpServletRequest request, HttpServletResponse response,Account account) {
		String idStr = request.getParameter("id");
		if(idStr==null) {
			idStr ="0";
		}
		long id = Long.parseLong(idStr);
			// 重置密码(重置后密码为admin123)
		Response<String> resultString = accountClient.resetPassword(id);
		//System.out.println("操作用户返回结果：" + resultString.getMsg());
		return resultString;
	}
	
	@RequestMapping("/manageAccounts/daleteAccounts")
	@ResponseBody
	public Response<String> daleteAccounts(HttpServletRequest request, HttpServletResponse response) {
		String idStr = request.getParameter("id");
		if(idStr==null) {
			idStr ="0";
		}
		long id = Long.parseLong(idStr);
		// 删除用户
	     Response<String> daleteAccount = accountClient.deleteAccount(Long.valueOf(id));
		//System.out.println("删除用户返回结果：" + daleteAccount.getMsg());
		return daleteAccount;
	}
	
	
	@RequestMapping("/manageAccounts/insertAccountRole")
	@ResponseBody
	public Response<String> insertAccountRole(HttpServletRequest request, HttpServletResponse response) {
		//接受前台传的参数
		String accountIdStr = request.getParameter("accountId");
		Long accountId = Long.parseLong(accountIdStr);
		String jsonstr = request.getParameter("jsonstr");//前台传的json字符串
		
		JSONObject jsonObject = JsonUtil.stringJsonObject(jsonstr);//json字符串转化为json对象
		//遍历json的键值
		Set<String> keySet = jsonObject.keySet();
		Iterator<String> iterator = keySet.iterator();
		
		List<Long> roleId= new ArrayList<Long>();
		String next =null;
		Object object = new Object() ;
		//将json值放入集合
		while (iterator.hasNext()) {
			next= iterator.next();
			object= jsonObject.getString(next);
			roleId.add(Long.valueOf(String.valueOf(object)).longValue());
		}
		//把集合里的值放入数组
		Long[] roleIds = new Long[roleId.size()];
		for (int j = 0; j < roleId.size(); j++) {
			roleIds[j]=roleId.get(j);
		}
		
		Response<String> insertAccountRole = accountClient.insertAccountRole(accountId,roleIds );
		return insertAccountRole;
	}
	
	@RequestMapping(value="/manageAccounts/openRoles")
	public ModelAndView openRoles( HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv =new ModelAndView();
		String accountId = request.getParameter("accountId");
		List<AccountRole> findByAccountId = accountClient.findByAccountId(Long.valueOf(accountId));
		if(findByAccountId!=null) {
			mv.addObject("roleByAccountId", findByAccountId);
		}
		mv.addObject("accountId", accountId);
		mv.setViewName("security/openRole");
		return mv;
	}
	
	@RequestMapping(value="/manageAccounts/findRoleAndResource")
	@ResponseBody
	public List<RoleAndResourceVo> findRoleAndResource( HttpServletRequest request, HttpServletResponse response) {
		List<RoleAndResourceVo> allRoles=roleService.findRoleAndResource("");;
		return allRoles;
	}
	@RequestMapping(value="/manageAccounts/findResourceByRoleId")
	@ResponseBody
	public List<Resource> openResourceByroleId( HttpServletRequest request, HttpServletResponse response) {
		String roleId = request.getParameter("roleId");
		List<Resource> resources = roleService.findResourceByRoleId(Long.valueOf(roleId));
		
		return resources;
	}
}