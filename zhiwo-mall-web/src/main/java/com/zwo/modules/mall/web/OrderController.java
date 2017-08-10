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

import com.zwo.modules.mall.domain.Order;
import com.zwo.modules.mall.service.IOrderService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping("order")
@Lazy(true)
public class OrderController extends BaseController<Order> {
	@Autowired
	@Lazy(true)
	private IOrderService orderService;
	
	private static final String basePath = "views/mall/order/";
	
	@RequestMapping(value = { "", "list" })
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"order_list";
	}
	
//	@RequiresPermissions("system:order:create")
	@RequestMapping(value = { "create" }, method = RequestMethod.GET)
	public String tocreate(@Valid Order order, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("order", order);
		return basePath + "order_edit";
	}

//	@RequiresPermissions("system:order:view")
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Order order = orderService.selectByPrimaryKey(id);

		uiModel.addAttribute("order", order);
		uiModel.addAttribute("operation", "edit");
		return basePath + "order_edit";
	}
	
//	@RequiresPermissions("system:order:create")
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid Order tborder, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		int res = orderService.insertSelective(tborder);
		if(res==1){
			redirectAttributes.addFlashAttribute("order", tborder);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		
		return "redirect:/order/create";
	}
	 
//	@RequiresPermissions("system:order:edit")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid Order order, BindingResult result, Model uiModel,
			RedirectAttributes redirectAttributes,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		int res = this.orderService.updateByPrimaryKeySelective(order);
		if(res==1){
			redirectAttributes.addFlashAttribute("order", order);
			redirectAttributes.addFlashAttribute("message", "保存用户成功！");
		}
		uiModel.addAttribute("order", order);
		uiModel.addAttribute("operation", "edit");
		return basePath + "order_edit";
	}
}
