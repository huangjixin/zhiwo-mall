package com.zwo.modules.zhihuiduo.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.DatagridPage;
import com.github.pagehelper.PageInfo;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAccount;
import com.zwo.modules.member.domain.MemberCriteria;
import com.zwo.modules.member.domain.MemberPlayAccount;
import com.zwo.modules.member.domain.MemberPlayHisAccount;
import com.zwo.modules.member.domain.MemberPlayHisAccountCriteria;
import com.zwo.modules.member.service.IMemberAddressService;
import com.zwo.modules.member.service.IMemberPlayAccountService;
import com.zwo.modules.member.service.IMemberPlayHisAccountService;
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
	@Autowired
	@Lazy(true)
	private IMemberPlayAccountService memberPlayAccountService;
	@Autowired
	@Lazy(true)
	private IMemberPlayHisAccountService memberPlayHisAccountService;

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
		if(!subject.isAuthenticated()){
			return "/memberLogin";
		}
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);

			if (member != null) {
				uiModel.addAttribute("member", member);
			}
		}
		return basePath + "info";
	}


	/**
	 * 跳转到我的拼团页面。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequiresAuthentication
	@RequestMapping(value = { "memberGroupPurcse" })
	public String getMemberGroupPurcse(Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		MemberAccount memberAccount = null;
		// 会员的智慧豆账户。
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);
			if (member != null) {
				memberAccount = memberService.selectMemberAccountByMId(member
						.getId());
			}
		}
		uiModel.addAttribute("memberAccount", memberAccount);
		return basePath + "memberGroupPurcse";
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
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);
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
	public String toMemberPlayAccount( Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		MemberPlayAccount memberPlayAccount = null;
		// 会员的智慧豆账户。
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 Member member = memberService.selectMember(username);
			if (member != null) {
//				memberPlayAccount = memberService
//						.selectMemberPlayAccountByMemberId(member.getId());
				memberPlayAccount = memberPlayAccountService.selectByPrimaryKey(member.getId());
			}
		}
		uiModel.addAttribute("memberPlayAccount",memberPlayAccount);
		return basePath + "memberPlayAccount";
	}
	
	

	/**
	 * 账户历史记录
	 * @param pageInfo
	 * @param member
	 * @param uiModel
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = "selectMemberPlayHisAccount")
	@ResponseBody
	public DatagridPage<MemberPlayHisAccount> select(@ModelAttribute PageInfo<MemberPlayHisAccount> pageInfo, @ModelAttribute MemberPlayHisAccount memberPlayHisAccount, Model uiModel,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		String p = httpServletRequest.getParameter("current");
		String rows = httpServletRequest.getParameter("rowCount");
		// 当前页 
		int intPage = Integer.parseInt((p == null || p == "0") ? "1" : p);
		// 每页显示条数 
		int number = Integer.parseInt((rows == null || rows == "0") ? "10" : rows);

		pageInfo.setPageNum(intPage);
		pageInfo.setPageSize(number);
 
		Subject subject = SecurityUtils.getSubject();
		Member member = null;
		if (subject != null) {
			 String username = (String) subject.getPrincipal();
			 member = memberService.selectMember(username);
		}
		
		MemberPlayHisAccountCriteria hisAccountCriteria = new MemberPlayHisAccountCriteria();
		hisAccountCriteria.setOrderByClause("create_date desc");
		if (member != null) {
			hisAccountCriteria.createCriteria().andMemberIdEqualTo(member.getId());
		}
		
		pageInfo = memberPlayHisAccountService.selectByPageInfo(hisAccountCriteria, pageInfo);
		DatagridPage<MemberPlayHisAccount> page = new DatagridPage<MemberPlayHisAccount>();
		page.setTotal(pageInfo.getTotal());
		page.setRows(pageInfo.getList());
		page.setCurrent(intPage);
		page.setRowCount(number);
		return page;
	}
}
