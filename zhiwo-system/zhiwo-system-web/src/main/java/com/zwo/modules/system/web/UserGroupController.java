package com.zwo.modules.system.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.domain.TbUserGroup;
import com.zwo.modules.system.service.ITbRoleService;
import com.zwo.modules.system.service.ITbUserGroupService;
import com.zwo.modules.system.service.ITbUserService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping(value={"userGroup"})
@Lazy(true)
public class UserGroupController extends BaseController<TbUserGroup> {
	@Autowired
	@Lazy(true)
	private ITbUserGroupService userGroupService;
	@Autowired
	@Lazy(true)
	private ITbUserService userService;
	@Autowired
	@Lazy(true)
	private ITbRoleService roleService;
	
	private static final String basePath = "views/system/userGroup/";
	
	@RequiresPermissions("system:userGroup:view")
	@RequestMapping()
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"userGroup_list";
	}
	
	@RequiresPermissions("system:userGroup:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid TbUserGroup userGroup, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		List<TbRole> roles = roleService.selectByExample(null);
		uiModel.addAttribute("roles", roles);
		uiModel.addAttribute("userGroup", userGroup);
		return basePath + "userGroup_edit";
	}

	@RequiresPermissions("system:userGroup:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbUserGroup userGroup = userGroupService.selectByPrimaryKey(id);
		List<TbRole> roles = roleService.selectByExample(null);
		List<TbRole> rolesAuth = userGroupService.findByUserGroupId(id);
		for (TbRole tbRole : roles) {
			for (TbRole role : rolesAuth) {
				if(tbRole.getId().equals(role.getId())){
					tbRole.setSelected(true);
					break;
				}
			}
		}
		uiModel.addAttribute("roles", roles);
		uiModel.addAttribute("userGroup", userGroup);
		uiModel.addAttribute("operation", "edit");
		return basePath + "userGroup_edit";
	}
	
	@RequiresPermissions("system:userGroup:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid TbUserGroup tbuserGroup,@Valid String roles, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = userGroupService.insertSelective(tbuserGroup);
		if(res==1){
			redirectAttributes.addFlashAttribute("userGroup", tbuserGroup);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		if(!"".equals(roles)){
			String[] roleIds = roles.split(",");
			userGroupService.batchConnectUserGroupRole(Arrays.asList(roleIds), tbuserGroup.getId());
		}
		
		return "redirect:/userGroup/create";
	}
	 
	@RequiresPermissions("system:userGroup:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid TbUserGroup userGroup,@Valid String roles, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.userGroupService.updateByPrimaryKeySelective(userGroup);
		if(res==1){
			redirectAttributes.addFlashAttribute("userGroup", userGroup);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		//删除用户组关联的角色。
		userGroupService.batchUnconnectUserGroupRole(userGroup.getId());

		if(!"".equals(roles)){
			String[] roleIds = roles.split(",");
			userGroupService.batchConnectUserGroupRole(Arrays.asList(roleIds), userGroup.getId());
		}
		uiModel.addAttribute("userGroup", userGroup);
		uiModel.addAttribute("operation", "edit");
		return "redirect:/userGroup/edit/"+userGroup.getId();
	}
}
