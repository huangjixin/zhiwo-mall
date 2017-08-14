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

import com.zwo.modules.system.domain.TbResources;
import com.zwo.modules.system.service.ITbResourcesService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("resources")
@Lazy(true)
public class ResourcesController extends BaseController<TbResources> {
	@Autowired
	@Lazy(true)
	private ITbResourcesService resourcesService;
	
	private static final String basePath = "views/system/resources/";
	
	@RequiresPermissions("system:resources:view")
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"resources_list";
	}
	
	@RequiresPermissions("system:resources:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid TbResources resources, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("resources", resources);
		return basePath + "resources_edit";
	}

	@RequiresPermissions("system:resources:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbResources resources = resourcesService.selectByPrimaryKey(id);

		uiModel.addAttribute("resources", resources);
		uiModel.addAttribute("operation", "edit");
		return basePath + "resources_edit";
	}
	
	@RequiresPermissions("system:resources:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid TbResources resources, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("resources", resources);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/resources/create";
		}
		
		int res = resourcesService.insertSelective(resources);
		if(res!=0){
			redirectAttributes.addFlashAttribute("resources", resources);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/resources/create";
	}
	 

	@RequiresPermissions("system:resources:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid TbResources resources, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		redirectAttributes.addAttribute("operation", "edit");
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("resources", resources);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
			return "redirect:/resources/edit/"+resources.getId();
		}
		if(resources.getId().equals(resources.getParentId())){
			redirectAttributes.addFlashAttribute("resources", resources);
			redirectAttributes.addFlashAttribute("message", "请另选父亲节点！");
			return "redirect:/resources/edit/"+resources.getId();
		}
		int res = this.resourcesService.updateByPrimaryKeySelective(resources);
		if(res==1){
			redirectAttributes.addFlashAttribute("resources", resources);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/resources/edit/"+resources.getId();
	}
}
