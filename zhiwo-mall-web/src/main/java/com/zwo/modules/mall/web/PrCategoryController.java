package com.zwo.modules.mall.web;

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

import com.zwo.modules.mall.domain.PrCategory;
import com.zwo.modules.mall.service.IPrCategoryService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("prCategory")
@Lazy(true)
public class PrCategoryController extends BaseController<PrCategory> {
	@Autowired
	@Lazy(true)
	private IPrCategoryService prCategoryService;
	
	private static final String basePath = "views/mall/category/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"category_list";
	}
	
//	@RequiresPermissions("system:prCategory:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid PrCategory prCategory, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("prCategory", prCategory);
		return basePath + "prCategory_edit";
	}

//	@RequiresPermissions("system:prCategory:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrCategory prCategory = prCategoryService.selectByPrimaryKey(id);

		uiModel.addAttribute("prCategory", prCategory);
		uiModel.addAttribute("operation", "edit");
		return basePath + "prCategory_edit";
	}
	
//	@RequiresPermissions("system:prCategory:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid PrCategory tbprCategory, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = prCategoryService.insertSelective(tbprCategory);
		if(res==1){
			redirectAttributes.addFlashAttribute("prCategory", tbprCategory);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/prCategory/create";
	}
	 
//	@RequiresPermissions("system:prCategory:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid PrCategory prCategory, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.prCategoryService.updateByPrimaryKeySelective(prCategory);
		if(res==1){
			redirectAttributes.addFlashAttribute("prCategory", prCategory);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("prCategory", prCategory);
		uiModel.addAttribute("operation", "edit");
		return basePath + "prCategory_edit";
	}
}
