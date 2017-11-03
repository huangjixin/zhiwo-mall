package com.zwo.modules.mall.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
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
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.domain.OrderTradeCriteria;
import com.zwo.modules.mall.service.IOrderTradeService;
import com.zwo.modules.shop.domain.ShopWithBLOBs;
import com.zwo.modules.system.domain.TbUser;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@RestController
@RequestMapping("order")
@Lazy(true)
public class OrderRestController extends BaseController<OrderTrade> {
	@Autowired
	@Lazy(true)
	private IOrderTradeService orderService;
	
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
		int result = orderService.deleteBatch(list);
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
		
		int result = orderService.deleteByPrimaryKey(id);
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
	public OrderTrade getOrderTrade(@PathVariable("id") String id, Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		OrderTrade order = orderService.selectByPrimaryKey(id);
		
		return order;
	}
	
	@RequestMapping(value = "/select")
	@ResponseBody
	public DatagridPage<OrderTrade> select(@ModelAttribute PageInfo<OrderTrade> pageInfo, @ModelAttribute OrderTrade order, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
 
		OrderTradeCriteria orderCriteria = null;
		orderCriteria = new OrderTradeCriteria();
		OrderTradeCriteria.Criteria criteria = orderCriteria.createCriteria();
		orderCriteria.setOrderByClause("id desc");
		if (null != order.getStatus() && !"".equals(order.getStatus())) {
			criteria.andStatusEqualTo(order.getStatus());
		}
		if (null != order.getId() && !"".equals(order.getId())) {
			criteria.andIdEqualTo(order.getId());
		}
		
		pageInfo = orderService.selectByPageInfo(orderCriteria, pageInfo);
		return super.setPage(pageInfo);
	}
	
	@RequestMapping(value = "myOrder")
	@ResponseBody
	public DatagridPage<OrderTrade> myOrder(@RequestParam String status,@ModelAttribute PageInfo<OrderTrade> pageInfo, @ModelAttribute OrderTrade order, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		super.select(pageInfo, uiModel, httpServletRequest, httpServletResponse);
		
		String userId = null;
		Subject currentUser = SecurityUtils.getSubject(); 
		if(currentUser!=null){
			TbUser user =  (TbUser) currentUser.getSession().getAttribute("user");
			if(user!=null){
				userId = user.getId();
			}
		}
		/*OrderTradeCriteria orderCriteria = null;
		orderCriteria = new OrderTradeCriteria();
		OrderTradeCriteria.Criteria criteria = orderCriteria.createCriteria();
		orderCriteria.setOrderByClause("id desc");
		if (null != order.getName() && !"".equals(order.getName())) {
			criteria.andNameLike("%" + order.getName() + "%");
		}*/
		
		pageInfo = orderService.selectByUserId(userId, status, pageInfo);
		return super.setPage(pageInfo);
	}
}
