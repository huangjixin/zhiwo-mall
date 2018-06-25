package com.fulan.application.controller.systerm;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.system.domain.Organization;
import com.fulan.api.system.manage.ManageOrganizationService;
import com.fulan.api.system.service.OrganizationService;
import com.fulan.application.util.domain.Response;

/**
 * @Author: chenzuyu
 * @Date: 2018/1/18 09:16
 */
@Controller
public class ManageOrganizationWebController {

	@Autowired
    private ManageOrganizationService manageOrganizationService;
	
	
	/**
	 * 查找id下所有子孙节点
	 * 
	 */
	@RequestMapping("/manage/listChildOrganization")
	@ResponseBody
    public List<Organization> listChildOrganization(
    		@RequestParam(value="parentId",required=false)String parentId
    		){
		List<Organization> list=manageOrganizationService.listAllChildren(parentId);
    	return  list;
    }
	
	@RequestMapping("/manage/getAllOrganizationList")
	@ResponseBody
    public List<Organization> getOrganization(Model model){
		List<Organization> list=manageOrganizationService.getOrganizationList();
    	return  list;
    }
    @RequestMapping(value ="/manage/getOrganizationListByParentId", method = RequestMethod.POST)
	@ResponseBody
    public Response<List<Organization>> getOrganizationListByParentId(Model model,HttpServletRequest request){
    	String id = request.getParameter("id");
//    	Long parentId=Long.parseLong(id);
    	Response<List<Organization>> res = new Response<List<Organization>>();
		List<Organization> list=manageOrganizationService.getOrganizationListByParentId(id);
		res.setData(list);
    	return  res;
    }
	
	
	
	@RequestMapping(value ="/manage/getOrganizationById", method = RequestMethod.POST)
	@ResponseBody
    public Response<Organization> getOrganizationById(Model model,HttpServletRequest request){
//		Long organizationId = Long.parseLong(request.getParameter("organizationId"));
		String organizationId = request.getParameter("organizationId");
		Organization organ  = new Organization();
		organ.setId(organizationId);
		Response<Organization> res = new Response<Organization>();
		Organization organization=manageOrganizationService.getOrganizationById(organ.getId());
		res.setData(organization);
    	return res;
    }
	
	@GetMapping("/manage/insertOrganization")
    public String insertOrganization(Model model,Organization organization,String parentId){
//		organization.setParentId(parentId);
//		organization.setEnabled(true);
		
    	return null;
    }
	
	@RequestMapping("/manage/insertAndUpdateOrganization")
	@ResponseBody
    public Response<String> updateOrganization(Model model,Organization organization){
//		Long id=Long.parseLong(organization.getId());
//		//获取当前登录人
//		//获取当前时间
//		Date date = new Date();
//		Long userId=SessionContextUtils.getLoginUserId();
//		String userName=SessionContextUtils.getLoginName();
//		if(null!=id){
//			//执行修改请求
//			//保存修改人
//			organization.setUpdateById(id);
//			//保存修改时间
//			organization.setUpdateTime(date);
//			organization.setUpdateByName(userName);
//			Response<String> result = manageOrganizationService.updateOrganization(organization);
//	     	return result;
//		}
//		if(null==organization.getLayer()){
//			organization.setLayer(1);//第一层级layer为1
//		}
//		if(null==organization.getParentId()){
//			organization.setParentId((long) 0);//第一层级parentId为0
//		}
//		if(null==organization.getRootId()){
//			organization.setRootId((long) 0);//第一层级rootId为0
//		}
//		//执行新增请求
//		//保存创建人
//		organization.setCreateById(userId);
//		organization.setCreateTime(date);
//		organization.setCreateByName(userName);
//		//保存创建时间
//		Response<String> result = manageOrganizationService.insertOrganization(organization);
//		return result;
		return null;
    }
	
	
	@RequestMapping(value = "/manage/deleteOrganization", method = RequestMethod.POST)
	@ResponseBody
    public Response<String> deleteOrganization(Model model,HttpServletRequest request){
//		String id = request.getParameter("id");
//		Response<String> res = manageOrganizationService.deleteOrganization(id);
    	return null;
    }
	
	@RequestMapping("/manage/organizationJsp")
    public String organizationListJsp(Model model,HttpServletRequest request){
		//return "organization/organizationList";
		/*String id = request.getParameter("id");
		if(null==id){
			List<Organization> organizations=manageOrganizationService.getFirstOrganizationList();
			model.addAttribute("organizations",organizations);
		}else{
		List<Organization> organizations=manageOrganizationService.getFirstOrganizationList(id);
		model.addAttribute("organizations",organizations);
		}*/
		
		List<Organization> organizations=manageOrganizationService.getFirstOrganizationList();
		model.addAttribute("organizations",organizations);
		return "organization/organizationTree";
    }
	
	
	@RequestMapping("/manage/getOrganizationParent")
    public String getOrganizationParent(Model model,HttpServletRequest request){
		//parentId（父级ID）
		//顶层机构id(父辈顶层机构相同)
		//layer父辈加一（初始为一）
		String parentId = request.getParameter("id");
		String rootId= request.getParameter("rootId");
		String parentLayer= request.getParameter("layer");
		Integer layer=Integer.parseInt(parentLayer)+1;
		model.addAttribute("parentId",parentId);
		model.addAttribute("rootId",rootId);
		model.addAttribute("layer",layer);
    	return "organization/insertAndUpdateOrganization";
    }
}
