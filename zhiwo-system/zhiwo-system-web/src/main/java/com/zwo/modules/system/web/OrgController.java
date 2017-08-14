package com.zwo.modules.system.web;

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

import com.zwo.modules.system.domain.TbOrg;
import com.zwo.modules.system.service.ITbOrgService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("org")
@Lazy(true)
public class OrgController extends BaseController<TbOrg> {
	@Autowired
	@Lazy(true)
	private ITbOrgService orgService;
	
	private static final String basePath = "views/system/org/";
	
	@RequiresPermissions("system:org:view")
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"org_list";
	}
	
	@RequiresPermissions("system:org:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid TbOrg org, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("org", org);
		return basePath + "org_edit";
	}

	@RequiresPermissions("system:org:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbOrg org = orgService.selectByPrimaryKey(id);

		uiModel.addAttribute("org", org);
		uiModel.addAttribute("operation", "edit");
		return basePath + "org_edit";
	}
	
	@RequiresPermissions("system:org:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid TbOrg tborg, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = orgService.insertSelective(tborg);
		if(res==1){
			redirectAttributes.addFlashAttribute("org", tborg);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/org/create";
	}
	 
	@RequiresPermissions("system:org:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid TbOrg org, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.orgService.updateByPrimaryKeySelective(org);
		if(res==1){
			redirectAttributes.addFlashAttribute("org", org);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("org", org);
		uiModel.addAttribute("operation", "edit");
		return basePath + "org_edit";
	}
}
