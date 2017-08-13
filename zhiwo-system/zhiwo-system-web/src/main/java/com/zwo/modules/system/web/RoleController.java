package com.zwo.modules.system.web;

import java.util.ArrayList;
import java.util.List;

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

import com.zwo.modules.system.domain.TbResources;
import com.zwo.modules.system.domain.TbRole;
import com.zwo.modules.system.service.ITbResourcesService;
import com.zwo.modules.system.service.ITbRoleService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("role")
@Lazy(true)
public class RoleController extends BaseController<TbRole> {
	@Autowired
	@Lazy(true)
	private ITbRoleService roleService;
	@Autowired
	@Lazy(true)
	private ITbResourcesService resourcesService;
	
	private static final String basePath = "views/system/role/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"role_list";
	}

	
//	@RequiresPermissions("system:role:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid TbRole role, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("resources", null);
		uiModel.addAttribute("role", role);
		return basePath + "role_edit";
	}

	
//	@RequiresPermissions("system:role:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		TbRole role = roleService.selectByPrimaryKey(id);
		List<TbResources> list = this.roleService.selectByRoleId(role.getId());
		List<String> resources = new ArrayList<String>();
		for (TbResources tbResources : list) {
			resources.add(tbResources.getId());
		}
		uiModel.addAttribute("resources", resources);
		uiModel.addAttribute("role", role);
		uiModel.addAttribute("operation", "edit");
		return basePath + "role_edit";
	}
	
//	@RequiresPermissions("system:role:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid TbRole role,@Valid String resources, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("role", role);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/role/create";
		}
		
		int res = roleService.insertSelective(role);
		if(res!=0){
			redirectAttributes.addFlashAttribute("role", role);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/role/create";
	}
	 
//	@RequiresPermissions("system:role:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid TbRole role, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("role", role);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = this.roleService.updateByPrimaryKeySelective(role);
		if(res==1){
			redirectAttributes.addFlashAttribute("role", role);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/role/edit/"+role.getId();
	}
}
