package com.zwo.modules.zhihuiduo.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
import com.zwo.modules.shop.domain.Shop;
import com.zwo.modules.shop.service.IShopService;
import com.zwo.modules.system.domain.TbUser;
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

	// @SuppressWarnings("rawtypes")
	// private RedisTemplate redisTemplate = SpringContextHolder
	// .getBean("redisTemplate");

	private static final String basePath = "views/member/";

	/**
	 * 跳转到下单页面，mode是group的话，那表示该团是拼团。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "checkOut")
	@RequiresAuthentication
	public String checkOut(@RequestParam String goodsId,
			@RequestParam String shopId, @RequestParam Integer buyNum,
			@RequestParam String packagePriceId,
			@RequestParam String proValues, @RequestParam String dealPrice,
			@RequestParam(defaultValue = "group") String mode,
			@RequestParam(required = false) String groupPurcseId,
			RedirectAttributes redirectAttributes, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		PrProduct product = prductService.selectByPrimaryKey(goodsId);
		Shop shop = shopService.selectByPrimaryKey(product.getShopId());
		Member member = null;
		
		OrderTrade orderTrade = new OrderTrade();
		String orderuuid = UUID.randomUUID().toString().replaceAll("-", "");
		orderTrade.setId(orderuuid);
		orderTrade.setStatus(OrderStatus.TO_BE_PAYED);
		orderTrade.setDisable(false);
		orderTrade.setBuyNum(buyNum);

		// 开拼团
		if ("group".equals(mode)) {
			orderTrade.setIsFormSccuess(false);
		} else { // 独立团
			orderTrade.setIsFormSccuess(true);
		}

		if (shop != null) {
			uiModel.addAttribute("shop", shop);

		}
		if (product != null) {
			orderTrade.setShopId(product.getShopId());
			uiModel.addAttribute("product", product);
			orderTrade.setProductId(goodsId);
		}

		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				orderTrade.setMemberId(member.getId());
				MemberAddress address = addressService
						.selectDefaultAddressByMemberId(member.getId());
				uiModel.addAttribute("member", member);
				uiModel.addAttribute("address", address);
			}
		} else {

		}

		// groupPurcseId是不是为null表示是拼团还是开团
		GroupPurcse groupPurcse = null;
		GroupPurcseMember groupPurcseMember = null;//拼团中间表。
		int numberCount = 0;
		
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
		
		if(groupPurcse !=null){
			groupPurcse.setProductId(goodsId);
			if(member!=null){
				groupPurcse.setMemeberId(member.getId());
				groupPurcse.setMemberIcon(member.getIcon());
				groupPurcse.setMemberName(member.getNickname());
				groupPurcse.setMemberOpenId(member.getOpenId());
			}
			
		}
		
		if(groupPurcseMember !=null){
			if(member!=null){
				groupPurcseMember.setMemberId(member.getId());
				groupPurcseMember.setMemberIcon(member.getIcon());
				groupPurcseMember.setMemberName(member.getNickname());
				groupPurcseMember.setMemberOpenId(member.getOpenId());
			}
		}
		
		if (null != groupPurcseId) {//参团。
			//插入拼团中间表。
			if(groupPurcseMember !=null){
				groupPurcseMember.setGroupPurcseId(groupPurcse.getId());
			}
			int countGroupPurcseMember = groupPurcseMemberService.countByGroupPurcseId(groupPurcse.getId());
			//满团后设置disable为true，表示该团已经满人了。
			if(numberCount!=0 && numberCount==(countGroupPurcseMember+1)){
				groupPurcse.setDisable(true);
			}
			groupPurcseService.updateByPrimaryKeySelective(groupPurcse);//更新拼团。
		}else{
			// 开拼团
			if ("group".equals(mode)) {
				groupPurcse.setDisable(false);;
			} else { // 独立团直接设置为不可用了
				groupPurcse.setDisable(true);
			}
			long dateTime = new Date().getTime();
			dateTime+=3600000*24*2;
			SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    String d = format.format(dateTime);  
		    Date date = new Date();
			try {
				date = format.parse(d);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    
			groupPurcse.setExpiredTime(date);
			groupPurcseService.insertSelective(groupPurcse); //开团。
		}
		
		groupPurcseMember.setGroupPurcseId(groupPurcse.getId());
		//插入中间表，表示开团或者参团成功
		groupPurcseMemberService.insertSelective(groupPurcseMember);
		
		//下单成功
		orderTradeService.insertSelective(orderTrade);

		uiModel.addAttribute("order", orderTrade);
		return basePath + "checkOut";
		// return "redirect:/memberOrder/checkOut";
	}

	/**
	 * 结算。
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
	@RequestMapping(value = "check")
	public String check_out(@RequestParam String goodsId,
			@RequestParam String shopId, @RequestParam Integer buyNum,
			@RequestParam String packagePriceId,
			@RequestParam String proValues, @RequestParam String dealPrice,
			RedirectAttributes redirectAttributes, Model uiModel,
			@RequestParam(defaultValue = "group") String mode,
			@RequestParam(required = false) String groupPurcseId,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		PrProduct product = prductService.selectByPrimaryKey("150383670510593");
		uiModel.addAttribute("product", product);
		return basePath + "checkOut";
	}

}
