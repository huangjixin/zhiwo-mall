package com.fulan.application.controller.security.resource;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.mgt.SessionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.fulan.api.security.domain.Resource;
import com.fulan.api.security.service.ResourceService;
import com.fulan.api.security.vo.ResourceListVO;
import com.fulan.api.security.vo.ResourceVO;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * @Author: liyingbo
 * @Date: 2018/1/17 12:52
 */
@Controller
@RequestMapping("/manage/resource")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	@RequiresPermissions("/manage/resource/index")
	@GetMapping("index")
	public String index(){
		/*indexSave();*/
		return "security/resource/resourceTree";
	}
	
//	public void indexSave(){
//		Resource resource = new Resource();
//    	resource.setResourceName("代理人管理");
//    	resource.setParentId("0");
//    	resource.setLayer("0");
//    	resource.setSeq("0");
//    	resourceService.saveForManage(resource);
//    	resource.setResourceName("运营人员管理");
//    	resource.setParentId("0");
//    	resource.setLayer("0");
//    	resource.setSeq("1");
//    	resourceService.saveForManage(resource);
//    	resource.setResourceName("增员业务管理");
//    	resource.setParentId("0");
//    	resource.setLayer("0");
//    	resource.setSeq("2");
//    	resourceService.saveForManage(resource);
//	}
	
	@RequiresPermissions("/manage/resource/index")
	@GetMapping("save")
	public String save(Model model){
		Resource resource = new Resource();
    	resource.setResourceName("chailaoer");
    	resourceService.saveForManage(resource);
	    return "demo";
	}
	
	@RequiresPermissions("/manage/resource/index")
	@GetMapping("list")
	public String list(){
		PageInfo<Resource> pageInfo = new PageInfo<Resource>();
		/*pageInfo = resourceService.listByPage(1, 10);*/
		return "demo";
	}
	
	public String deleFor(String parentId,String ids){
		Resource resource = new Resource();
		resource.setParentId(parentId);
		Response<List<Resource>> response = resourceService.seleByParentIdForManage(resource);
		List<Resource> list = response.getData();
		if(list.size()>0){
			for(int i = 0 ; i < list.size() ; i++ ){
				ids += deleFor(list.get(i).getId().toString(),ids);
			}
			return ids+=parentId+",";
		}else{
			/*resourceService.deleteForManage(Long.parseLong(parentId));*/
			return ids+=parentId+",";
		}
	}
	
	@RequiresPermissions("/manage/resource/index")
	@PostMapping("dele")
	@ResponseBody
	public Response<String> dele(String id,Model model){
		if(!StringUtils.isEmpty(id)){
			/*Long dId = Long.parseLong(id);
			resourceService.deleteForManage(dId);*/
			String ids = "";
			ids = deleFor(id,ids);
			ids = ids.substring(0, ids.length()-1);
			return resourceService.deleteIdsForManage(ids);
		}
		return new Response<String>(Response.SUCCESS,"操作成功");
	}
	
	@RequiresPermissions("/manage/resource/index")
	@RequestMapping(value = "update" , method =RequestMethod.POST)
	@ResponseBody
	public Response<String> update(Resource resource,Model model,String type){
		if(null != resource && null != resource.getId() ){
			resource.setUpdateById(SessionContextUtils.getLoginUserId());
			resource.setUpdateByName(SessionContextUtils.getLoginName());
			resource.setUpdateTime(new Date());
			return resourceService.updateForManage(resource,type);
		}else{
			resource.setCreateById(SessionContextUtils.getLoginUserId());
			resource.setCreateByName(SessionContextUtils.getLoginName());
			return resourceService.saveForManage(resource);
		}
	}
	
	@RequiresPermissions("/manage/resource/index")
	@RequestMapping(value = "sele" , method = RequestMethod.POST)
	@ResponseBody
	public Response<Resource> sele(String id,Model model){
		Response<Resource> response  = new Response<Resource>();
		if(!StringUtils.isEmpty(id)){
			Long dId = Long.parseLong(id);
			response = resourceService.seleForManage(dId);
			return response;
			/*model.addAttribute("resource", response.getData());*/
		}
		response.setMsg("查询失败");
		return response;
	}
	/**
	 * pageSortFiled 排序字段名 默认seq
	 * pageSortType 排序顺序 (asc , desc) 默认asc
	 * @param mode
	 * @param resource
	 * @param pageSortFiled
	 * @param pageSortType
	 * @return
	 */
	@RequiresPermissions("/manage/resource/index")
	@RequestMapping("find/list")
	@ResponseBody
	public List<Resource> list(Resource resource,HttpServletRequest request,
			@RequestParam(value="roleParentId",required=false)String roleParentId){
		HttpSession session = request.getSession();
		String pid = (String) session.getAttribute("resourcePID");
		if(StringUtils.isNotEmpty(roleParentId)){
			Resource re = new Resource();
			resource.setParentId(roleParentId);
			List<Resource> resList = resourceService.seleByParentIdForManage(resource).getData();
			if(CollectionUtils.isNotEmpty(resList)){
				pid = String.valueOf(resList.get(0).getId());
			}
		}
		if(StringUtils.isNotEmpty(pid)){
			resource.setParentId(pid);
		}
		
		Response<List<Resource>> list = resourceService.seleAllForManage(resource);
		return list.getData();
	}
	
	@RequiresPermissions("/manage/resource/index")
	@RequestMapping("find/listByParentId")
	@ResponseBody
	public List<Resource> listByParentId(Model model,Resource resource){
		Response<List<Resource>> response = resourceService.seleByParentIdForManage(resource);
		return response.getData();
	}
	
	@RequiresPermissions("/manage/resource/index")
	@RequestMapping("find/listByPid")
	@ResponseBody
	public List<ResourceListVO> listByPid(Model model,String id){
		Long accountId = SessionContextUtils.getLoginUserId();
		List<ResourceListVO> resourceListVO = resourceService.listByParentIdForManage(id,accountId);
		return resourceListVO;
	}
	
	@RequiresPermissions("/manage/resource/index")
	@RequestMapping("find/listByPidOrId")
	@ResponseBody
	public Response<List<Resource>> listByPidOrId(Model model,String id){
		Response<List<Resource>> response = resourceService.listPIdOrIdFM(id);
		return response;
	}
	
}
