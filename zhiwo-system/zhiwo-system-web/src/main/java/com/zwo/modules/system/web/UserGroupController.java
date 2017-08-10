package com.zwo.modules.system.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zwo.modules.system.domain.TbUserGroup;
import com.zwo.modules.system.service.ITbUserGroupService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("userGroup")
@Lazy(true)
public class UserGroupController extends BaseController<TbUserGroup> {
	@Autowired
	@Lazy(true)
	private ITbUserGroupService userGroupService;
	
	private static final String basePath = "views/system/userGroup/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"userGroup_list";
	}
	

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid TbUserGroup userGroup, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("userGroup", userGroup);
		return basePath + "userGroup_edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbUserGroup userGroup = userGroupService.selectByPrimaryKey(id);

		uiModel.addAttribute("userGroup", userGroup);
		uiModel.addAttribute("operation", "edit");
		return basePath + "userGroup_edit";
	}
	

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid TbUserGroup tbuserGroup, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = userGroupService.insertSelective(tbuserGroup);
		if(res==1){
			redirectAttributes.addFlashAttribute("userGroup", tbuserGroup);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/userGroup/create";
	}
	 
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid TbUserGroup userGroup, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.userGroupService.updateByPrimaryKeySelective(userGroup);
		if(res==1){
			redirectAttributes.addFlashAttribute("userGroup", userGroup);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("userGroup", userGroup);
		uiModel.addAttribute("operation", "edit");
		return basePath + "userGroup_edit";
	}
}
