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

import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.service.IPrProductPropertyValueService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("productPropertyValue")
@Lazy(true)
public class ProductPropertyValueController extends BaseController<PrProductPropertyValue> {
	@Autowired
	@Lazy(true)
	private IPrProductPropertyValueService productPropertyValueService;
	
	private static final String basePath = "views/mall/productPropertyValue/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"productPropertyValue_list";
	}
	
//	@RequiresPermissions("system:productPropertyValue:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid PrProductPropertyValue productPropertyValue, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("productPropertyValue", productPropertyValue);
		return basePath + "productPropertyValue_edit";
	}

//	@RequiresPermissions("system:productPropertyValue:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrProductPropertyValue productPropertyValue = productPropertyValueService.selectByPrimaryKey(id);

		uiModel.addAttribute("productPropertyValue", productPropertyValue);
		uiModel.addAttribute("operation", "edit");
		return basePath + "productPropertyValue_edit";
	}
	
//	@RequiresPermissions("system:productPropertyValue:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid PrProductPropertyValue tbproductPropertyValue, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = productPropertyValueService.insertSelective(tbproductPropertyValue);
		if(res==1){
			redirectAttributes.addFlashAttribute("productPropertyValue", tbproductPropertyValue);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/productPropertyValue/create";
	}
	 
//	@RequiresPermissions("system:productPropertyValue:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid PrProductPropertyValue productPropertyValue, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.productPropertyValueService.updateByPrimaryKeySelective(productPropertyValue);
		if(res==1){
			redirectAttributes.addFlashAttribute("productPropertyValue", productPropertyValue);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("productPropertyValue", productPropertyValue);
		uiModel.addAttribute("operation", "edit");
		return basePath + "productPropertyValue_edit";
	}
}
