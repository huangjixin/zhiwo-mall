package com.zwo.modules.zhihuiduo.web;

import java.util.List;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberAddressCriteria;
import com.zwo.modules.member.domain.MemberPlayAccount;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.zhihuiduo.dto.MemberInfo;
import com.zwotech.common.utils.SpringContextHolder;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping(value = { "memberInfo" })
@Lazy(true)
public class MemberInfoController extends BaseController {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	@Autowired
	@Lazy(true)
	private IMemberAddressService addressService;

	@SuppressWarnings("rawtypes")
	private RedisTemplate redisTemplate = SpringContextHolder
			.getBean("redisTemplate");

	private static final String basePath = "views/member/";

	/**
	 * 该方法用于返回会员的信息，会员必须处于登录状态；系统先查找缓存，如果没有开启Redis缓存，则跳过。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "info" })
	// @RequiresAuthentication
	public String getMemInfo(Model uiModel,
			HttpServletRequest httpServletRequest) {
		String memberInfoString = null; // 会员的结果存储为JSON字符串
		MemberInfo memberInfo = null; // 会员信息。
		List<MemberAddress> memberAddresses = null; // 会员的地址集。
		MemberAccount memberAccount = null; // 会员的账户。
		MemberPlayAccount memberPlayAccount = null; // 会员的智慧豆账户。
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");

			if (member != null) {
				/*
				 * ValueOperations<String, Object> valueOperations = null;
				 * if(redisTemplate!=null){ valueOperations
				 * =redisTemplate.opsForValue(); if(valueOperations != null ){
				 * // 检查Redis有没有存放会员的个人信息，有则返回。 String key =
				 * "memberInfo"+member.getId(); memberInfoString = (String)
				 * valueOperations.get(key); if(memberInfoString!=null){
				 * uiModel.addAttribute("rawData",memberInfoString); }else{ //
				 * 检查Redis没有存放会员的个人信息 memberInfo = new MemberInfo();
				 * 
				 * String memberAddresskey = "memberAddress"+member.getId();
				 * memberAddresses = (List<MemberAddress>)
				 * valueOperations.get(memberAddresskey); //
				 * 如果会员的地址没有，则查询数据库，加入缓存。 if(memberAddresses ==null){
				 * memberAddresses =
				 * memberService.selectMemberAddressByMId(member.getId());
				 * valueOperations.set(memberAddresskey, memberAddresses); }
				 * 
				 * // 如果会员的账户没有，则查询数据库，加入缓存。 String memberAccountkey =
				 * "memberAccount"+member.getId(); memberAccount =
				 * (MemberAccount) valueOperations.get(memberAccountkey); //
				 * 如果会员的账户没有，则查询数据库，加入缓存。 if(memberAccount ==null){
				 * memberAccount =
				 * memberService.selectMemberAccountByMId(member.getId());
				 * valueOperations.set(memberAccountkey, memberAccount); }
				 * 
				 * // 如果会员的智惠豆账户没有，则查询数据库，加入缓存。 String memberPlayAccountkey =
				 * "memberPlayAccount"+member.getId(); memberPlayAccount =
				 * (MemberPlayAccount)
				 * valueOperations.get(memberPlayAccountkey); //
				 * 如果会员的账户没有，则查询数据库，加入缓存。 if(memberPlayAccount ==null){
				 * memberPlayAccount =
				 * memberService.selectMemberPlayAccountByMemberId(member.getId(
				 * )); valueOperations.set(memberPlayAccountkey,
				 * memberPlayAccount); }
				 * 
				 * memberInfo.setMemberAddress(memberAddresses);
				 * memberInfo.setMemberAccount(memberAccount);
				 * memberInfo.setMemberPlayAccount(memberPlayAccount);
				 * 
				 * memberInfoString = JSON.toJSONString(memberInfo);
				 * uiModel.addAttribute("rawData",memberInfoString); }
				 * 
				 * }
				 * 
				 * }else{ memberInfo = new MemberInfo(); //会员的地址，账户
				 * memberAddresses =
				 * memberService.selectMemberAddressByMId(member.getId());
				 * memberInfo.setMemberAddress(memberAddresses); memberAccount =
				 * memberService.selectMemberAccountByMId(member.getId());
				 * memberInfo.setMemberAccount(memberAccount); memberPlayAccount
				 * =
				 * memberService.selectMemberPlayAccountByMemberId(member.getId(
				 * )); memberInfo.setMemberPlayAccount(memberPlayAccount);
				 * 
				 * memberInfoString = JSON.toJSONString(memberInfo);
				 * uiModel.addAttribute("rawData",memberInfoString); }
				 */

				uiModel.addAttribute("member",member);
			}
		} else {

		}

		return basePath + "info";
	}

	/**
	 * 跳转到会员账户页面。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = { "memberAccount" })
	public String toMemberAccount(@ModelAttribute MemberAccount memberAccount,
			Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// 会员的智慧豆账户。
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				memberAccount = memberService.selectMemberAccountByMId(member
						.getId());
			}
		}
		uiModel.addAttribute(memberAccount);
		return basePath + "memberAccount";
	}

	/**
	 * 跳转到会员的智惠豆账户页面。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = { "memberPlayAccount" })
	public String toMemberPlayAccount(
			@ModelAttribute MemberPlayAccount memberPlayAccount, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		// 会员的智慧豆账户。
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				memberPlayAccount = memberService
						.selectMemberPlayAccountByMemberId(member.getId());
			}
		}
		uiModel.addAttribute(memberPlayAccount);
		return basePath + "memberPlayAccount";
	}

	// @RequiresAuthentication
	@RequestMapping(value = { "memberAddress" })
	public String selectAddress(Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		List<MemberAddress> list = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				list = addressService.listAllByMemberId(member.getId());
			}
		}

		/*MemberAddressCriteria addressCriteria = new MemberAddressCriteria();
		addressCriteria.setOrderByClause("create_date asc");
		list = addressService.selectByExample(addressCriteria);*/
		uiModel.addAttribute("addresses", list);
		return basePath + "memberAddress";
	}

	@RequestMapping(value = { "createMemberAddress" }, method = RequestMethod.POST)
	@ResponseBody
	public String createAddress(@ModelAttribute MemberAddress address,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				if(address.getMemberId()!=null){
					Member memb  = memberService.selectByPrimaryKey(address.getMemberId());
					if(member.getId().equals(memb.getId())){
						return "0";
					}
				}
				
				address.setMemberId(member.getId());
			}
		} 
		
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		address.setId(uuid);
		//
		int result = addressService.insertSelective(address);
		String resultString = JSON.toJSONString(address);
		return resultString;
		// return "redirect:/memberInfo/memberAddress";
	}
	
	@RequestMapping(value = { "updateMemberAddress" }, method = RequestMethod.POST)
	@ResponseBody
	public String updateAddress(@ModelAttribute MemberAddress address,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				if(address.getMemberId()!=null){
					Member memb  = memberService.selectByPrimaryKey(address.getMemberId());
					if(member.getId().equals(memb.getId())){
						return "0";
					}
				}
				
				address.setMemberId(member.getId());
			}
		} 
		
		int result = addressService.updateByPrimaryKeySelective(address);
		String resultString = JSON.toJSONString(address);
		return resultString;
	}

	@RequestMapping(value = { "deleteMemberAddress" }, method = RequestMethod.POST)
	@ResponseBody
	public String deleteAddress(@ModelAttribute MemberAddress address,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				if(address.getMemberId()!=null){
					Member memb  = memberService.selectByPrimaryKey(address.getMemberId());
					if(member.getId().equals(memb.getId())){
						return "0";
					}
				}
			}
		} 
		
		int result = addressService.deleteByPrimaryKey(address.getId());
		if(result==1){
			return result + "";
		}
		
		return "0";
	}

	@RequestMapping(value = { "setDefaultMemberAddress" })
	@ResponseBody
	public String setDefaultMemberAddress(
			@ModelAttribute MemberAddress address, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");
			if (member != null) {
				if(address.getMemberId()!=null){
					Member memb  = memberService.selectByPrimaryKey(address.getMemberId());
					if(member.getId().equals(memb.getId())){
						return "0";
					}
				}
			}
		} 
		
		MemberAddressCriteria addressCriteria = new MemberAddressCriteria();
		if(address.getMemberId()!= null){
			addressCriteria.createCriteria().andMemberIdEqualTo(
					address.getMemberId());
			MemberAddress record = new MemberAddress();
			record.setIsDefault("0");
			addressService.updateByExampleSelective(record, addressCriteria);
		}
		
		address.setIsDefault("1");
		int result = addressService
				.updateByPrimaryKeySelective(address);
		return result + "";
	}
}
