package com.zwo.modules.mall.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.service.IPrductService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("product")
@Lazy(true)
public class ProductController extends BaseController<PrProduct> {
	@Autowired
	@Lazy(true)
	private IPrductService prductService;
	
	private static final String basePath = "views/mall/product/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"product_list";
	}
	
	@RequestMapping(value = {"create"},method=RequestMethod.GET)
	public String create(@Valid PrProduct product, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("product", product);
		return basePath+"product_edit";
	}
	
	@RequestMapping(value = {"edit"},method=RequestMethod.GET)
	public String edit(@Valid PrProduct product, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		product = prductService.selectByPrimaryKey(product.getId());
		uiModel.addAttribute("product", product);
		uiModel.addAttribute("operation", "edit");
		return basePath+"product_edit";
	}
	
}
