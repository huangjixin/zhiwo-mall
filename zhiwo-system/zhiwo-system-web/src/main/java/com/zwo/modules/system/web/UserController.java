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

import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.system.service.ITbUserService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("user")
@Lazy(true)
public class UserController extends BaseController<TbUser> {
	@Autowired
	@Lazy(true)
	private ITbUserService userService;
	
	private static final String basePath = "views/system/user/";
	
//	@RequiresPermissions("system:user:view")
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"user_list";
	}
	
//	@RequiresPermissions("system:user:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid TbUser user, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("user", user);
		return basePath + "user_edit";
	}

//	@RequiresPermissions("system:user:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbUser user = userService.selectByPrimaryKey(id);

		uiModel.addAttribute("user", user);
		uiModel.addAttribute("operation", "edit");
		return basePath + "user_edit";
	}
	
//	@RequiresPermissions("system:user:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid TbUser user, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/user/create";
		}
		
		int res = userService.insertSelective(user);
		if(res!=0){
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/user/create";
	}
	 
//	@RequiresPermissions("system:user:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid TbUser user, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = this.userService.updateByPrimaryKeySelective(user);
		if(res==1){
			redirectAttributes.addFlashAttribute("user", user);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/user/edit/"+user.getId();
	}
}
