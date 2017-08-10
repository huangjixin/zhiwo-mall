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

import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.service.IPrProductPropertyService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("productProperty")
@Lazy(true)
public class ProductPropertyController extends BaseController<PrProductProperty> {
	@Autowired
	@Lazy(true)
	private IPrProductPropertyService productPropertyService;
	
	private static final String basePath = "views/mall/productProperty/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"productProperty_list";
	}
	
//	@RequiresPermissions("system:productProperty:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid PrProductProperty productProperty, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("productProperty", productProperty);
		return basePath + "productProperty_edit";
	}

//	@RequiresPermissions("system:productProperty:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrProductProperty productProperty = productPropertyService.selectByPrimaryKey(id);

		uiModel.addAttribute("productProperty", productProperty);
		uiModel.addAttribute("operation", "edit");
		return basePath + "productProperty_edit";
	}
	
//	@RequiresPermissions("system:productProperty:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid PrProductProperty tbproductProperty, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = productPropertyService.insertSelective(tbproductProperty);
		if(res==1){
			redirectAttributes.addFlashAttribute("productProperty", tbproductProperty);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/productProperty/create";
	}
	 
//	@RequiresPermissions("system:productProperty:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid PrProductProperty productProperty, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.productPropertyService.updateByPrimaryKeySelective(productProperty);
		if(res==1){
			redirectAttributes.addFlashAttribute("productProperty", productProperty);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("productProperty", productProperty);
		uiModel.addAttribute("operation", "edit");
		return basePath + "productProperty_edit";
	}
}
