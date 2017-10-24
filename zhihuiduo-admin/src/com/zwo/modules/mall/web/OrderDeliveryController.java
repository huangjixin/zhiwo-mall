package com.zwo.modules.mall.web;

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

import com.zwo.modules.mall.domain.OrderDelivery;
import com.zwo.modules.mall.service.IOrderDeliveryService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("orderDelivery")
@Lazy(true)
public class OrderDeliveryController extends BaseController<OrderDelivery> {
	@Autowired
	@Lazy(true)
	private IOrderDeliveryService orderDeliveryService;

	private static final String basePath = "views/mall/orderDelivery/";
	
	/**
	 * 默认执行方法。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping()
	String defaultMethod(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return list(httpServletRequest);
	}
	
	@RequiresPermissions("mall:orderDelivery:view")
	@RequestMapping(value = {"list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"orderDelivery_list";
	}
	
	@RequiresPermissions("mall:orderDelivery:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid OrderDelivery orderDelivery, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("orderDelivery", orderDelivery);
		return basePath + "orderDelivery_edit";
	}

	@RequiresPermissions("mall:orderDelivery:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		OrderDelivery orderDelivery = orderDeliveryService.selectByPrimaryKey(id);

		uiModel.addAttribute("orderDelivery", orderDelivery);
		uiModel.addAttribute("operation", "edit");
		return basePath + "orderDelivery_edit";
	}
	
	@RequiresPermissions("mall:orderDelivery:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid OrderDelivery tborderDelivery, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = orderDeliveryService.insertSelective(tborderDelivery);
		if(res==1){
			redirectAttributes.addFlashAttribute("orderDelivery", tborderDelivery);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/orderDelivery/create";
	}
	 
	@RequiresPermissions("mall:orderDelivery:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid OrderDelivery orderDelivery, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.orderDeliveryService.updateByPrimaryKeySelective(orderDelivery);
		if(res==1){
			redirectAttributes.addFlashAttribute("orderDelivery", orderDelivery);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("orderDelivery", orderDelivery);
		uiModel.addAttribute("operation", "edit");
		return basePath + "orderDelivery_edit";
	}
}
