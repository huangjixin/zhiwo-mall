package com.zwo.modules.zhihuiduo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.domain.MemberPlayAccount;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwo.modules.member.service.IMemberService;
import com.zwotech.common.web.BaseController;

@SuppressWarnings("rawtypes")
@Controller
@RequestMapping("memberInfo")
@Lazy(true)
public class MemberInfoController extends BaseController {
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

	/**
	 * 默认执行方法。
	 * @param uiModel
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping()
	String defaultMethod(Model uiModel, HttpServletRequest httpServletRequest) {
		return getMemInfo(uiModel,httpServletRequest);
	}

	/**
	 * 该方法用于返回会员的信息，会员必须处于登录状态；系统先查找缓存，如果没有开启Redis缓存，则跳过。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = { "info" })
	@RequiresAuthentication
	public String getMemInfo(Model uiModel,
			HttpServletRequest httpServletRequest) {
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Member member = (Member) subject.getSession()
					.getAttribute("member");

			if (member != null) {
				uiModel.addAttribute("member", member);
			}
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
	@RequiresAuthentication
	@RequestMapping(value = { "memberAccount" })
	public String toMemberAccount(Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		MemberAccount memberAccount = null;
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
		uiModel.addAttribute("memberAccount", memberAccount);
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
	@RequiresAuthentication
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

}
