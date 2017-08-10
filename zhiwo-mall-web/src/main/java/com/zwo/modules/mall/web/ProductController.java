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

import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
import com.zwo.modules.mall.service.IPrductService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("product")
@Lazy(true)
public class ProductController extends BaseController<PrProduct> {
	@Autowired
	@Lazy(true)
	private IPrductService productService;
	
	private static final String basePath = "views/mall/product/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"product_list";
	}
	
//	@RequiresPermissions("mall:product:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid PrProductWithBLOBs product, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("product", product);
		return basePath + "product_edit";
	}

//	@RequiresPermissions("mall:product:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrProductWithBLOBs product = productService.selectByPrimKey(id);

		uiModel.addAttribute("product", product);
		uiModel.addAttribute("operation", "edit");
		return basePath + "product_edit";
	}
	
//	@RequiresPermissions("mall:product:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid PrProductWithBLOBs product, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("product", product);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/product/create";
		}
		
		int res = productService.insertSelective(product);
		if(res!=0){
			redirectAttributes.addFlashAttribute("product", product);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/product/create";
	}
	 
//	@RequiresPermissions("mall:product:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid PrProductWithBLOBs product, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("product", product);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = this.productService.updateByPrimaryKeySelective(product);
		if(res==1){
			redirectAttributes.addFlashAttribute("product", product);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/product/edit/"+product.getId();
	}
}
