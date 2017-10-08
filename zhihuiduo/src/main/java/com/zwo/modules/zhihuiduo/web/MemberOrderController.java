package com.zwo.modules.zhihuiduo.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import weixin.popular.api.PayMchAPI;
import weixin.popular.bean.paymch.MchBaseResult;
import weixin.popular.bean.paymch.MchPayNotify;
import weixin.popular.bean.paymch.Unifiedorder;
import weixin.popular.bean.paymch.UnifiedorderResult;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.PayUtil;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.StreamUtils;
import weixin.popular.util.XMLConverUtil;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zwo.modules.mall.domain.OrderStatus;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.service.IOrderTradeService;
import com.zwo.modules.mall.service.IPrductService;
import com.zwo.modules.member.domain.GroupPurcse;
import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.service.IGroupPurcseMemberService;
import com.zwo.modules.member.service.IGroupPurcseService;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.payment.domain.PayTradePaymentOrder;
import com.zwo.modules.payment.service.IPayTradePaymentOrderService;
import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.service.IShopService;
import com.zwo.modules.system.domain.TbUser;
import com.zwo.modules.zhihuiduo.dto.ProductExtention;
import com.zwotech.common.redis.channel.ChannelContance;
import com.zwotech.common.utils.RedisUtil;
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

	@Autowired
	@Lazy(true)
	private IGroupPurcseService groupPurcseService;

	@Autowired
	@Lazy(true)
	private IGroupPurcseMemberService groupPurcseMemberService;

	@Autowired
	@Lazy(true)
	private IPayTradePaymentOrderService payTradePaymentOrderService;
	
	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate;

	@SuppressWarnings("rawtypes")
	private JmsTemplate jmsQueueTemplate;

	private static final String basePath = "views/member/";

	private String appid; // appid
	private String mch_id; // 微信支付商户号
	private String key; // API密钥
	// 重复通知过滤
	private static ExpireKey expireKey = new DefaultExpireKey();

	public MemberOrderController() {
		super();
		if (redisTemplate == null) {
			if (SpringContextHolder.getApplicationContext().containsBean(
					"redisTemplate"))
				redisTemplate = SpringContextHolder.getBean("redisTemplate");
		}
		if (jmsQueueTemplate == null) {
			if (SpringContextHolder.getApplicationContext().containsBean(
					"jmsQueueTemplate"))
				jmsQueueTemplate = SpringContextHolder
						.getBean("jmsQueueTemplate");
		}
	}

	/**
	 * 跳转到下单页面，dealPrice不包含运费，真正结算的时候应该包含。mode是group的话，那表示该团是拼团,
	 * 如果为indenpent的话即为独立开团，可以发货。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "checkOut")
	// @RequiresAuthentication
	public String checkOut(@RequestParam String goodsId,
			@RequestParam Integer buyNum, @RequestParam String packagePriceId,
			@RequestParam String proValues, @RequestParam String dealPrice,
			@RequestParam(defaultValue = "group") String mode,
			@RequestParam(required = false) String groupPurcseId,
			RedirectAttributes redirectAttributes, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		ProductExtention prExtention = new ProductExtention();
		String jsonString = null;
		Shop shop = null;
		PrProduct product = null;

		try {
			product = prductService.selectByPrimaryKey(goodsId);
			product.setGourpSalePrice(Double.valueOf(dealPrice));
			BeanUtils.copyProperties(prExtention, product);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		shop = shopService.selectByPrimKey(product.getShopId());

		Member member = null;

		OrderTrade orderTrade = new OrderTrade();
		String orderuuid = UUID.randomUUID().toString().replaceAll("-", "");
		orderTrade.setId(orderuuid);
		orderTrade.setStatus(OrderStatus.TO_BE_PAYED);
		orderTrade.setDisable(false);
		orderTrade.setBuyNum(buyNum);
		orderTrade.setDealPrice(dealPrice);
		//独立开团的话订单里面的isDefault字段设置为true，反则设置为false.
		orderTrade.setIsDefault("group".equals(mode));
		
		//订单根据此判断是否为开团或者拼团。
		orderTrade.setGroupPurcseId(groupPurcseId);
		String description = "";

		JSONArray jsonArray = JSONArray.parseArray(proValues);
		for (Object object : jsonArray) {
			JSONObject obj = (JSONObject) object;
			String name = obj.getString("name");
			description += name + " ";
		}

		orderTrade.setDescription(description);
		// 开拼团
		if ("group".equals(mode)) {
			orderTrade.setIsFormSccuess(false);
		} else { // 独立团，可以发货
			orderTrade.setIsFormSccuess(true);
		}

		if (product != null) {
			orderTrade.setShopId(product.getShopId());
			orderTrade.setProductId(goodsId);
		}

		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			String username = (String) subject.getPrincipal();
			member = memberService.selectMember(username);

			if (member != null) {
				orderTrade.setMemberId(member.getId());
				MemberAddress address = addressService
						.selectDefaultAddressByMemberId(member.getId());
				prExtention.setDefautAddress(address);
				uiModel.addAttribute("member", member);
				uiModel.addAttribute("address", address);
			}
		} else {

		}

		// 如果有用中间件那么就用ActiveMQ进行消息转发，否则另开线程下单
		/*
		 * if (jmsQueueTemplate != null) { String jsonString =
		 * JSONObject.toJSONString(orderTrade);
		 * ActiveMQUtil.send(jmsQueueTemplate,
		 * ChannelContance.ORDER_CREATE_QUEUE_CHANNEL, jsonString); } else {
		 * asycInsertOrderTrade(orderTrade); }
		 */
		asycInsertOrderTrade(orderTrade);
		if(this.redisTemplate!=null){
			redisTemplate.opsForValue().set(orderTrade.getId()+"_orderTrade", orderTrade, 15, TimeUnit.MINUTES);
		}
		// 获取用户的全部地址。
		List<MemberAddress> list = null;
		if (member != null) {
			list = addressService.listAllByMemberId(member.getId());
		}
		prExtention.setMode(mode);
		prExtention.setGroupPurcseId(groupPurcseId);
		prExtention.setShop(shop);
		prExtention.setOrder(orderTrade);
		prExtention.setMemberAddresses(list);
		prExtention.setShop(shop);

		uiModel.addAttribute("shop", shop);
		uiModel.addAttribute("order", orderTrade);
		uiModel.addAttribute("product", product);
		uiModel.addAttribute("addresses", list);

		jsonString = JSONObject.toJSONString(prExtention, true);
		uiModel.addAttribute("rawData", jsonString);
		return basePath + "checkOut";
		// return "redirect:/memberOrder/checkOut";
	}

	private void asycInsertOrderTrade(final OrderTrade orderTrade) {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			public void run() {
				orderTradeService.insertSelective(orderTrade);
			}
		});
	}

	

	// payway可选参数为：wechat，alipay，sendWithoutPay分别是微信支付，支付宝，货到付款。
	@RequestMapping(value = "getPayMchJs",method=RequestMethod.POST)
	@ResponseBody
	public String getPayMchJs(@RequestParam String payway,
			@RequestParam String orderId,
			@RequestParam String dealPrice,
			@RequestParam String transportFee,
			Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		//货到付款
		if ("sendWithoutPay".equals(payway)) {
			//直接开团
			processOrder(orderId, httpServletRequest, httpServletResponse);
		} else if ("wechat".equals(payway)) {//微信支付
			Unifiedorder unifiedorder = new Unifiedorder();
			unifiedorder.setAppid(appid);
			unifiedorder.setMch_id(mch_id);
			unifiedorder.setNonce_str(UUID.randomUUID().toString()
					.replace("-", ""));

			unifiedorder.setBody("商品信息");
			unifiedorder.setOut_trade_no(orderId);
			unifiedorder.setTotal_fee(dealPrice);// 单位分
			unifiedorder
					.setSpbill_create_ip(httpServletRequest.getRemoteAddr());// IP
			unifiedorder.setNotify_url("http://mydomain.com/test/notify");
			unifiedorder.setTrade_type("JSAPI");// JSAPI，NATIVE，APP，MWEB

			UnifiedorderResult unifiedorderResult = PayMchAPI.payUnifiedorder(
					unifiedorder, key);

			// @since 2.8.5 API返回数据签名验证
			if (unifiedorderResult.getSign_status() != null
					&& unifiedorderResult.getSign_status()) {
				String json = PayUtil.generateMchPayJsRequestJson(
						unifiedorderResult.getPrepay_id(), appid, key);
				return json;
			}
		} else if ("alipay".equals(payway)) {

		}
		return null;
	}

	/**
	 * 微信回调函数，根据groupPurcseId参数是否为NULL进行判断是开团还是参团。
	 * 
	 * @param goodsId
	 * @param shopId
	 * @param buyNum
	 * @param packagePriceId
	 * @param proValues
	 * @param dealPrice
	 * @param redirectAttributes
	 * @param uiModel
	 * @param mode
	 * @param groupPurcseId
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "checkBack")
	public String checkBack(@RequestParam String goodsId,
			@RequestParam Integer buyNum, @RequestParam String packagePriceId,
			@RequestParam String proValues, @RequestParam String dealPrice,
			@RequestParam(defaultValue = "group") String mode,
			@RequestParam(required = false) String groupPurcseId,
			RedirectAttributes redirectAttributes, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Member member = null;
		// groupPurcseId是不是为null表示是拼团还是开团
		GroupPurcse groupPurcse = null;
		GroupPurcseMember groupPurcseMember = new GroupPurcseMember();// 拼团中间表。
		int numberCount = 0;
		PrProduct product = prductService.selectByPrimaryKey("150383670510593");
		uiModel.addAttribute("product", product);
		if (null != groupPurcseId) {
			groupPurcse = groupPurcseService.selectByPrimaryKey(groupPurcseId);
			if (groupPurcse != null) {
				numberCount = groupPurcse.getNumberCount();
			}
		} else {
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			groupPurcse = new GroupPurcse();
			groupPurcse.setId(id);
			groupPurcse.setDisable(false);

			id = UUID.randomUUID().toString().replaceAll("-", "");
			groupPurcseMember = new GroupPurcseMember();
			groupPurcseMember.setId(id);
		}

		if (groupPurcse != null) {
			groupPurcse.setProductId(goodsId);
			if (member != null) {
				groupPurcse.setMemeberId(member.getId());
				groupPurcse.setMemberIcon(member.getIcon());
				groupPurcse.setMemberName(member.getNickname());
				groupPurcse.setMemberOpenId(member.getOpenId());
			}

		}

		if (groupPurcseMember != null) {
			if (member != null) {
				groupPurcseMember.setMemberId(member.getId());
				groupPurcseMember.setMemberIcon(member.getIcon());
				groupPurcseMember.setMemberName(member.getNickname());
				groupPurcseMember.setMemberOpenId(member.getOpenId());
			}
		}

		if (null != groupPurcseId) {// 参团。
			// 插入拼团中间表。
			if (groupPurcseMember != null) {
				groupPurcseMember.setGroupPurcseId(groupPurcse.getId());
			}
			int countGroupPurcseMember = groupPurcseMemberService
					.countByGroupPurcseId(groupPurcse.getId());
			// 满团后设置disable为true，表示该团已经满人了。
			if (numberCount != 0 && numberCount == (countGroupPurcseMember + 1)) {
				groupPurcse.setDisable(true);
			}
			groupPurcseService.updateByPrimaryKeySelective(groupPurcse);// 更新拼团。
		} else {
			// 开拼团
			if ("group".equals(mode)) {
				groupPurcse.setDisable(false);
			} else { // 独立团直接设置为不可用了
				groupPurcse.setDisable(true);
			}
			long dateTime = new Date().getTime();
			dateTime += 3600000 * 24 * 2;
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String d = format.format(dateTime);
			Date date = new Date();
			try {
				date = format.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			groupPurcse.setExpiredTime(date);
			if (jmsQueueTemplate != null) {
				// ActiveMQUtil.send(jmsQueueTemplate,
				// ChannelContance.GROUPPURCSE_CREATE_QUEUE_CHANNEL,
				// groupPurcse);
			} else {
				asycInsertGroupPurcse(groupPurcse);
				// groupPurcseService.insertSelective(groupPurcse); // 开团。
			}

		}

		groupPurcseMember.setGroupPurcseId(groupPurcse.getId());
		// 插入中间表，表示开团或者参团成功
		if (jmsQueueTemplate != null) {

		} else {
			// groupPurcseMemberService.insertSelective(groupPurcseMember);
		}
		asycInsertGroupPurcseMember(groupPurcseMember);

		// 如果拼团人数已经达到规定的数目，那么就更新订单，让客户发货。

		return basePath + "checkOut";
	}

	/**
	 * 支付回调通知
	 * 
	 * @param goodsId
	 * @param buyNum
	 * @param packagePriceId
	 * @param proValues
	 * @param dealPrice
	 * @param mode
	 * @param groupPurcseId
	 * @param redirectAttributes
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "payMchNotify")
	public void payMchNotify(HttpServletRequest request,HttpServletResponse response) throws IOException {
		// 获取请求数据
		String xmlData = StreamUtils.copyToString(request.getInputStream(),
				Charset.forName("utf-8"));
		// 将XML转为MAP,确保所有字段都参与签名验证
		Map<String, String> mapData = XMLConverUtil.convertToMap(xmlData);
		// 转换数据对象
		MchPayNotify payNotify = XMLConverUtil.convertToObject(
				MchPayNotify.class, xmlData);
		// 已处理 去重
		if (expireKey.exists(payNotify.getTransaction_id())) {
			return;
		}
		// @since 2.8.5
		payNotify.buildDynamicField(mapData);
		// 签名验证
		if (SignatureUtil.validateSign(mapData, key)) {
			expireKey.add(payNotify.getTransaction_id());
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("SUCCESS");
			baseResult.setReturn_msg("OK");
			response.getOutputStream().write(
					XMLConverUtil.convertToXML(baseResult).getBytes());
		} else {
			MchBaseResult baseResult = new MchBaseResult();
			baseResult.setReturn_code("FAIL");
			baseResult.setReturn_msg("ERROR");
			response.getOutputStream().write(
					XMLConverUtil.convertToXML(baseResult).getBytes());
		}
		
		//如果支付成功那么就开团。
		if (SignatureUtil.validateSign(mapData, key)) {
			String orderId = payNotify.getOut_trade_no();
			OrderTrade orderTrade = this.orderTradeService.selectByPrimaryKey(orderId);
			orderTrade.setStatus(OrderStatus.TO_BE_SENT_PAYED);
			this.orderTradeService.updateByPrimaryKeySelective(orderTrade);
			processOrder(orderId,request,response);
			
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			PayTradePaymentOrder paymentOrder = new PayTradePaymentOrder();
			paymentOrder.setId(id);
			paymentOrder.setMemberId(orderTrade.getMemberId());
			paymentOrder.setDisable(false);
			paymentOrder.setIsRefund(false);
			paymentOrder.setMerchantOrderNo(orderId);
			paymentOrder.setOrderId(orderId);
			paymentOrder.setOrderAmount(Double.valueOf(orderTrade.getDealPrice()));
			paymentOrder.setPayWayCode("wechat");
			paymentOrder.setProductId(orderTrade.getProductId());
			
			this.payTradePaymentOrderService.insertSelective(paymentOrder);
		}
		
	}

	/**
	 * 处理回调订单(此处的逻辑稍微复杂：取到订单Id，订单修改disable为true表示下单成功，接着处理开团的逻辑)
	 * @param orderId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "processOrder")
	public String processOrder(@RequestParam String orderId,HttpServletRequest request,HttpServletResponse response){
		OrderTrade orderTrade = null;
		if(this.redisTemplate!=null){
			orderTrade = (OrderTrade) redisTemplate.opsForValue().get(orderId+"_orderTrade");
		}else{
			orderTrade =this.orderTradeService.selectByPrimaryKey(orderId);
		}
		
		if(orderTrade==null){
			return null;
		}
		
		//订单取消或者交易成功不予以处理。
		if(orderTrade.getDisable()==true){
			return null;
		}
		//订单下单成功。
		orderTrade.setDisable(true);
		
		String groupPurcseId = orderTrade.getGroupPurcseId();
		Member member = null;
		// groupPurcseId是不是为null表示是拼团还是开团
		GroupPurcse groupPurcse = null;
		GroupPurcseMember groupPurcseMember = new GroupPurcseMember();// 拼团中间表。
		int numberCount = 2;//默认开团人数。
		
		PrProduct product = prductService.selectByPrimaryKey(orderTrade.getProductId());
		member = memberService.selectByPrimaryKey(orderTrade.getMemberId());
		
		
		//根据是否有团Id判断是开团还是拼团。
		if (null != groupPurcseId && !"".equals(groupPurcseId)) {
			groupPurcse = groupPurcseService.selectByPrimaryKey(groupPurcseId);
			if (groupPurcse != null) {
				numberCount = groupPurcse.getNumberCount();
			}
		} else {
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			groupPurcse = new GroupPurcse();
			groupPurcse.setId(id);
			//团满人设置为true，不满为false；
			groupPurcse.setDisable(false);
//			groupPurcseService.insertSelective(groupPurcse);
		}

		//设置开团的会员信息。
		groupPurcse.setProductId(product.getId());
		if (member != null) {
			groupPurcse.setMemeberId(member.getId());
			groupPurcse.setMemberIcon(member.getIcon());
			groupPurcse.setMemberName(member.getNickname()==null?member.getUsername():member.getNickname());
			groupPurcse.setMemberOpenId(member.getOpenId());
		}

		groupPurcseMember = new GroupPurcseMember();
		groupPurcseMember.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		//设置中间表信息。
		if (member != null) {
			groupPurcseMember.setMemberId(member.getId());
			groupPurcseMember.setMemberIcon(member.getIcon());
			groupPurcseMember.setMemberName(member.getNickname()==null?member.getUsername():member.getNickname());
			groupPurcseMember.setMemberOpenId(member.getOpenId());
		}

		if (null != groupPurcseId && !"".equals(groupPurcseId)) {// 参团。
			// 插入拼团中间表。
			if (groupPurcseMember != null) {
				groupPurcseMember.setGroupPurcseId(groupPurcse.getId());
			}
			int countGroupPurcseMember = groupPurcseMemberService
					.countByGroupPurcseId(groupPurcse.getId());
			// 满团后设置disable为true，表示该团已经满人了。
			if (numberCount != 0 && (countGroupPurcseMember + 1) >= numberCount) {
				groupPurcse.setDisable(true);
				orderTrade.setIsFormSccuess(true);
				
				//满人，发团。
				orderTradeService.updateOrderTradeByGroupId(groupPurcse.getId(),true);
			}
			groupPurcseService.updateByPrimaryKeySelective(groupPurcse);// 更新拼团。
		} else {
			// 开拼团
			if (orderTrade.getIsDefault()) {
				groupPurcse.setDisable(false);
			} else { // 独立团直接设置为不可用了
				groupPurcse.setDisable(true);
				orderTrade.setIsFormSccuess(true);
			}
			
			//开团截止时间为4天96小时。
			long dateTime = new Date().getTime();
			dateTime += 3600000 * 24 * 4;
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String d = format.format(dateTime);
			Date date = new Date();
			try {
				date = format.parse(d);
			} catch (ParseException e) {
				e.printStackTrace();
			}

			groupPurcse.setExpiredTime(date);
			
			groupPurcseService.insertSelective(groupPurcse); // 开团。
			if(groupPurcse.getDisable()){
				orderTradeService.updateOrderTradeByGroupId(groupPurcse.getId(),true);
			}
		}

		groupPurcseMember.setGroupPurcseId(groupPurcse.getId());
		groupPurcseMemberService.insertSelective(groupPurcseMember);
		
		orderTradeService.updateByPrimaryKeySelective(orderTrade);
		
		if(redisTemplate!=null){
			//发送消息更新该团的静态网页。
			RedisUtil.publish(redisTemplate, ChannelContance.GROUPPURCSE_CREATE_QUEUE_CHANNEL, groupPurcse);
		}
		
		return null;
	}

	private void asycInsertGroupPurcseMember(
			final GroupPurcseMember groupPurcseMember) {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			public void run() {
				groupPurcseMemberService.insertSelective(groupPurcseMember);
			}
		});
	}

	private void asycInsertGroupPurcse(final GroupPurcse groupPurcse) {
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new Runnable() {
			public void run() {
				groupPurcseService.insertSelective(groupPurcse);
			}
		});
	}
}
