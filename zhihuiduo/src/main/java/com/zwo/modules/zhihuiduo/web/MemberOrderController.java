package com.zwo.modules.zhihuiduo.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.zwo.modules.mall.domain.OrderStatus;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.service.IOrderTradeService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.service.IShopService;
import com.zwo.modules.system.domain.TbUser;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping(value = { "memberOrder" })
@Lazy(true)
public class MemberOrderController extends BaseController<TbUser> {
	@Autowired
	@Lazy(true)
	private IOrderTradeService orderTradeService;
	@Autowired
	@Lazy(true)
	private IPrductService prductService;
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	@Autowired
	@Lazy(true)
	private IShopService shopService;
	@Autowired
	@Lazy(true)
	private IMemberAddressService addressService;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder
			.getBean("redisTemplate");

	private static final String basePath = "views/member/";

	/**
	 * 跳转到下单页面
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "check_out")
	// @RequiresAuthentication
	public String check_out(@RequestParam String goodsId,
			@RequestParam String shopId, @RequestParam Integer buyNum,
			@RequestParam String packagePriceId,
			@RequestParam String proValues,
			@RequestParam String dealPrice,
			Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrProduct product = prductService.selectByPrimaryKey(goodsId);
		Shop shop = shopService.selectByPrimaryKey(shopId);
		OrderTrade orderTrade = new OrderTrade();
		String orderuuid = UUID.randomUUID().toString().replaceAll("-", "");
		orderTrade.setId(orderuuid);
		orderTrade.setStatus(OrderStatus.TO_BE_PAYED);
		orderTrade.setDisable(false);
		orderTrade.setBuyNum(buyNum);
		if (shop != null) {
			uiModel.addAttribute("shop",shop);
			orderTrade.setShopId(shopId);
		}
		if (product != null) {
			uiModel.addAttribute("product",product);
			orderTrade.setProductId(goodsId);
		}
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				orderTrade.setMemberId(member.getId());
				MemberAddress address = addressService
						.selectDefaultAddressByMemberId(member.getId());
				uiModel.addAttribute("member",member);
				uiModel.addAttribute("address", address);
			}
		} else {

		}
		
		orderTradeService.insertSelective(orderTrade);
		
		uiModel.addAttribute("order", orderTrade);
		return basePath + "memberOrder";
	}

}
