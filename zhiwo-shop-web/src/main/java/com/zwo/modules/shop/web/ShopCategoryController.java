package com.zwo.modules.shop.web;

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

import com.zwo.modules.shop.domain.ShopCategory;
import com.zwo.modules.shop.service.IShopCategoryService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("shopCategory")
@Lazy(true)
public class ShopCategoryController extends BaseController<ShopCategory> {
	@Autowired
	@Lazy(true)
	private IShopCategoryService shopCategoryService;

	private static final String basePath = "views/shop/category/";

	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath + "shopCategory_list";
	}

	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid ShopCategory shopCategory, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("shopCategory", shopCategory);
		return basePath + "shopCategory_edit";
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ShopCategory shopCategory = shopCategoryService.selectByPrimaryKey(id);

		uiModel.addAttribute("shopCategory", shopCategory);
		uiModel.addAttribute("operation", "edit");
		return basePath + "shopCategory_edit";
	}
	

	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid ShopCategory shopCategory, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("shopCategory", shopCategory);
			redirectAttributes.addFlashAttribute("message", "数据绑定有误！");
			return "redirect:/shopCategory/create";
		}
		
		int res = shopCategoryService.insertSelective(shopCategory);
		if(res==1){
			redirectAttributes.addFlashAttribute("shopCategory", shopCategory);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		
		return "redirect:/shopCategory/create";
	}
	 
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid ShopCategory shopCategory, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			redirectAttributes.addFlashAttribute("shopCategory", shopCategory);
			redirectAttributes.addFlashAttribute("message", "填入的数据有误！");
		}
		
		int res = this.shopCategoryService.updateByPrimaryKeySelective(shopCategory);
		if(res==1){
			redirectAttributes.addFlashAttribute("shopCategory", shopCategory);
			redirectAttributes.addFlashAttribute("message", "保存成功！");
		}
		redirectAttributes.addAttribute("operation", "edit");
		return "redirect:/shopCategory/edit/"+shopCategory.getId();
	}

}
