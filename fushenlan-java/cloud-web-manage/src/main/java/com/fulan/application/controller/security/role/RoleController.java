package com.fulan.application.controller.security.role;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.security.domain.Role;
import com.fulan.api.security.domain.RoleResource;
import com.fulan.api.security.service.RoleResourceService;
import com.fulan.api.security.service.RoleService;
import com.fulan.api.security.vo.RoleAndResourceVo;
import com.fulan.application.util.domain.Response;

@Controller
@RequestMapping("manage")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	/*@GetMapping("role/list")
	public String list(Role Role,PageInfo pageInfo){
		roleService.list(Role, pageInfo);
		return HttpUrl.DEMO;
	}*/
	@RequiresPermissions("/manage/role/index")
	@GetMapping("role/index")
	public String index(){
		/*initialize();*/
		return "security/role/roleTree";
	}
	/**
	 * 初始化参数
	 * roleName 角色名
	 * parentId 角色父Id
	 * type 平台
	 */
	@RequestMapping("role/initialize")
	public void initialize(String roleName ,String parentId,String type){
		Role Role = new Role();
		Role.setRoleName(roleName);
		Role.setEnabled(true);
		Role.setParentId(parentId);
		roleService.saveForManage(Role, type);
	}
	@RequiresPermissions("/manage/role/index")
	@RequestMapping(value = "role/ajaxSele" , method=RequestMethod.POST)
	@ResponseBody
	public List<RoleAndResourceVo> ajaxSele(@RequestParam("id") String id){
		List<RoleAndResourceVo> list = roleService.findRoleAndResource(id);
		return list;
	}
	
	@RequestMapping(value = "role/saveOrUpdate" , method = RequestMethod.POST)
	@ResponseBody
	public Response<String> saveOrUpdate(Role role,String dele,String type){
		if("0".equals(dele)){
			role.setEnabled(false);
		}
		return roleService.saveForManage(role,type);
	}
    
	/**
	 * 查询所有角色并其关联的所有资源权限
	 * @return
	 */
	@RequestMapping(value="role/findRAR")
	@ResponseBody
	public List<RoleAndResourceVo> findRAR(String id){
		List<RoleAndResourceVo> list = roleService.findRoleAndResource(id);
		return list;
	}
	
	@RequestMapping(value="/role/find/list")
	@ResponseBody
	public List<Role> findList(){
		Response<List<Role>> response = roleService.listForManage();
	    return response.getData();
	}
	
	@RequestMapping(value="role/resourceLayer")
	public String resourceLayer(Model model,@RequestParam("roleId") String roleId,@RequestParam("parentId") String parentId){
		if(StringUtils.isNotEmpty(roleId)){
			Response<List<RoleResource>> response = roleResourceService.findByRoleIdFM(Long.valueOf(roleId));
			List<RoleResource> list = response.getData();
			model.addAttribute("list", list);
			model.addAttribute("roleId", roleId);
			model.addAttribute("parentId", parentId);
		}
		return "security/role/resourceLayer";
	}

}
