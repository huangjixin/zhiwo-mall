package com.zwo.modules.zhihuiduo.web;

import java.util.List;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAddress;
import com.zwo.modules.member.domain.MemberAddressCriteria;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.web.BaseController;

@Controller
@RequestMapping(value = { "memberInfo" })
@Lazy(true)
public class MemberAddressController extends BaseController {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;
	@Autowired
	@Lazy(true)
	private IMemberAddressService addressService;

	// @SuppressWarnings("rawtypes")
	// private RedisTemplate redisTemplate = SpringContextHolder
	// .getBean("redisTemplate");

	private static final String basePath = "views/member/";

	/*@RequestMapping()
	public String defaultMethod(Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return selectAddress(uiModel,
				 httpServletRequest,
				 httpServletResponse);
	}*/
	
	@RequiresAuthentication
	@RequestMapping(value = { "memberAddress" })
	public String selectAddress(Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		List<MemberAddress> list = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);
			if (member != null) {
				list = addressService.listAllByMemberId(member.getId());
			}
		}

		uiModel.addAttribute("addresses", list);
		return basePath + "memberAddress";
	}
	
	@RequiresAuthentication
	@RequestMapping(value = { "listAllByMemberId" })
	public List<MemberAddress> listAllByMemberId(Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		List<MemberAddress> list = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);
			if (member != null) {
				list = addressService.listAllByMemberId(member.getId());
			}
		}
		
		return list;
	}

	@RequiresAuthentication
	@RequestMapping(value = { "createMemberAddress" }, method = RequestMethod.POST)
	@ResponseBody
	public String createAddress(@ModelAttribute MemberAddress address,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);
			if (member != null) {
				/*if (address.getMemberId() != null) {
					Member memb = memberService.selectByPrimaryKey(address
							.getMemberId());
					if (member.getId().equals(memb.getId())) {
						return "0";
					}
				}*/

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

	@RequiresAuthentication
	@RequestMapping(value = { "updateMemberAddress" }, method = RequestMethod.POST)
	@ResponseBody
	public String updateAddress(@ModelAttribute MemberAddress address,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);
			if (member != null) {
				/*if (address.getMemberId() != null) {
					Member memb = memberService.selectByPrimaryKey(address
							.getMemberId());
					if (member.getId().equals(memb.getId())) {
						return "0";
					}
				}*/

				address.setMemberId(member.getId());
			}
		}

		int result = addressService.updateByPrimaryKey(address);
		String resultString = JSON.toJSONString(address);
		return resultString;
	}

	@RequiresAuthentication
	@RequestMapping(value = { "deleteMemberAddress" }, method = RequestMethod.POST)
	@ResponseBody
	public String deleteAddress(@ModelAttribute MemberAddress address,
			BindingResult bindingResult, Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);
			/*if (member != null) {
				if (address.getMemberId() != null) {
					Member memb = memberService.selectByPrimaryKey(address
							.getMemberId());
					if (member.getId().equals(memb.getId())) {
						return "0";
					}
				}
			}*/
		}

		int result = addressService.deleteByPrimaryKey(address.getId());
		if (result == 1) {
			return result + "";
		}

		return "0";
	}

	@RequiresAuthentication
	@RequestMapping(value = { "setDefaultMemberAddress" })
	@ResponseBody
	public String setDefaultMemberAddress(
			@ModelAttribute MemberAddress address, BindingResult bindingResult,
			Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Member member = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 member = memberService.selectMember(username);
			/*if (member != null) {
				if (address.getMemberId() != null) {
					Member memb = memberService.selectByPrimaryKey(address
							.getMemberId());
					if (member.getId().equals(memb.getId())) {
						return "0";
					}
				}
			}*/
		}
		
		if (member != null) {
			MemberAddressCriteria addressCriteria = new MemberAddressCriteria();
			addressCriteria.createCriteria().andMemberIdEqualTo(member.getId());
			MemberAddress record = new MemberAddress();
			record.setIsDefault("0");
			addressService.updateByExampleSelective(record, addressCriteria);
		}
		address = addressService.selectByPrimaryKey(address.getId());
		address.setIsDefault("1");
		int result = addressService.updateByPrimaryKey(address);
		return result + "";
	}
	
}
