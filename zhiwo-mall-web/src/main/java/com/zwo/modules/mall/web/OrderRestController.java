package com.zwo.modules.mall.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.domain.OrderDelivery;
import com.zwo.modules.mall.domain.OrderDeliveryCriteria;
import com.zwo.modules.mall.service.IOrderDeliveryService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.system.domain.TbUser;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("orderDelivery")
@Lazy(true)
public class OrderRestController extends BaseController<OrderDelivery> {
	@Autowired
	@Lazy(true)
	private IOrderDeliveryService orderDeliveryService;
	
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequestMapping(value = "/deleteById")
	public String deleteById(@RequestParam(value = "idstring",required=true) String idstring, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {

		String[] ids = idstring.split(",");
		List<String> list = new ArrayList<String>();
		for (String idstr : ids) {
			list.add(idstr);
		}
		int result = orderDeliveryService.deleteBatch(list);
		return result+"";
	}
	
	/** 
	 * @Title: deleteById 
	 * @Description: 批量删除 
	 * @param idstring
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return String    返回类型 
	 * @throws 
	 */
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id",required=true) String id, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws IOException {
		
		int result = orderDeliveryService.deleteByPrimaryKey(id);
		return result+"";
	}
	 
	/**
	 * @Description: 查看详情 
	 * @param id
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "/show/{id}")
	public OrderDelivery getOrderDelivery(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		OrderDelivery orderDelivery = orderDeliveryService.selectByPrimaryKey(id);
		
		return orderDelivery;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<OrderDelivery> select(@ModelAttribute PageInfo<OrderDelivery> pageInfo, @ModelAttribute OrderDelivery orderDelivery, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		OrderDeliveryCriteria orderDeliveryCriteria = null;
		orderDeliveryCriteria = new OrderDeliveryCriteria();
		OrderDeliveryCriteria.Criteria criteria = orderDeliveryCriteria.createCriteria();
		orderDeliveryCriteria.setOrderByClause("id desc");
		if (null != orderDelivery.getName() && !"".equals(orderDelivery.getName())) {
			criteria.andNameLike("%" + orderDelivery.getName() + "%");
		}
		
		pageInfo = orderDeliveryService.selectByPageInfo(orderDeliveryCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid OrderDelivery orderDelivery, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {

		}
		
		String res = ""+orderDeliveryService.insertSelective(orderDelivery);
		return res;
	}
	
	@RequestMapping(value = "/testcreate", method = RequestMethod.GET)
	public String testcreate(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		OrderDelivery orderDelivery = new OrderDelivery();
		orderDelivery.setId(System.currentTimeMillis()+"");
		String res = ""+orderDeliveryService.insertSelective(orderDelivery);
		return res;
	}
	
	@RequestMapping(value = "/sendCreatProductTopic", method = RequestMethod.GET)
	public void sendCreatProductTopic(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//		prductService.sendCreateProductTopic("创建一个Topic成功。");
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(@Valid OrderDelivery orderDelivery, BindingResult result, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		if (result.hasErrors()) {
			
		}
		
		String res = ""+this.orderDeliveryService.updateByPrimaryKeySelective(orderDelivery);
		return res;
	}
}