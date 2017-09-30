package com.zwo.modules.zhihuiduo.web;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import weixin.popular.api.SnsAPI;
import weixin.popular.bean.sns.SnsToken;
import weixin.popular.bean.user.User;

import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.service.IMemberService;
import com.zwo.modules.wechat.domain.WeChatVo;
import com.zwotech.common.utils.PasswordHelper;
import com.zwotech.common.web.BaseController;

/**
 * 会员登录控制器。
 * 
 * @author 黄记新 2017.8.8
 *
 */
@Controller
@Lazy(true)
@RequestMapping(value = { "memberLogin" })
public class MemberLoginController extends BaseController {
	@Autowired
	@Lazy(true)
	private IMemberService memberService;

//	@Autowired
//	@Lazy(true)
	private WeChatVo weChatVo;

	// @SuppressWarnings("rawtypes")
	// private RedisTemplate redisTemplate =
	// SpringContextHolder.getBean("redisTemplate");

	private static final String basePath = "views/member/";

	
	public MemberLoginController() {
		super();
		weChatVo = new WeChatVo();
	}

	/**
	 * 默认执行方法。
	 * 
	 * @param uiModel
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping()
	String defaultMethod(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		return login(uiModel, httpServletRequest, httpServletResponse, "");
	}

	@RequestMapping(value = { "login" }, method = RequestMethod.GET)
	public String login(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(required = false, defaultValue = "") String fromURL) {
		String localAddr = httpServletRequest.getLocalAddr();
		String remoteAddr = httpServletRequest.getRemoteAddr();
		String URL = httpServletRequest.getHeader("Referer");
		uiModel.addAttribute("fromURL", URL);

		boolean isWechatBrowser = false;
		String ua = ((HttpServletRequest) httpServletRequest).getHeader(
				"user-agent").toLowerCase();
		if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
			isWechatBrowser = true;
		}
		
		if(isWechatBrowser == true){
			String redirect_uri = "http://huangjixin.ngrok.xiaomiqiu.cn/memberLogin/getCodeAndAccessToken";
			String state = "123456";
			String url = SnsAPI.connectOauth2Authorize(weChatVo.getAppid(), redirect_uri,true,state);
			return "redirect:"+url;
		}
		
		return basePath + "login";
	}
	
	@RequestMapping(value = { "getCodeAndAccessToken" }, method = RequestMethod.GET)
	public String getCodeAndAccessToken(Model uiModel,
			@RequestParam String code, @RequestParam String state) {
		if(code!=null){
			SnsToken token = SnsAPI.oauth2AccessToken(weChatVo.getAppid(),
					weChatVo.getAppsecret(), code);
			if(token!=null){
				User user = SnsAPI.userinfo(token.getAccess_token(), token.getOpenid(), "zh_CN");
				uiModel.addAttribute("userInfo", user);
			}
		}
		
		return basePath + "login";
	}

	@RequestMapping(value = { "login" }, method = RequestMethod.POST)
	public String loginForm(Model uiModel, @RequestParam String username,
			@RequestParam String password,
			@RequestParam(required = false, defaultValue = "") String fromURL,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		password = PasswordHelper.encryptPassword(password);
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			if (!currentUser.isAuthenticated()) {
				token.setRememberMe(true);
				currentUser.login(token);// 验证角色和权限
			}
		} catch (Exception ex) {
			// throw new Exception("用户名或者密码错误");
			uiModel.addAttribute("message", "用户名或者密码错误");
			return "redirect:/memberLogin/login";
		}
		Member member = memberService.selectMember(username);
		currentUser.getSession().setAttribute("member", member);
		// uiModel.addAttribute("member", member);
		// String url =
		// WebUtils.getSavedRequest(httpServletRequest).getRequestUrl();
		String url = fromURL;
		url = ("".equals(url) ? "redirect:/mindex" : "redirect:" + url);
		return url;
	}

	/**
	 * 用户名和验证码一起登录，先判断验证码正确与否，如果正确，如果电话不存在则新建一个账号把电话存进去。
	 * 
	 * @param uiModel
	 * @param mobilPhone
	 * @param validateCode
	 * @param fromURL
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	@RequestMapping(value = { "loginByMobilPhone" }, method = RequestMethod.POST)
	public String loginByMobilPhone(Model uiModel,
			@ModelAttribute Member memb,
			@RequestParam String mobilPhone,
			@RequestParam String validateCode,
			@RequestParam(required = false) String openid,
			@RequestParam(required = false, defaultValue = "") String fromURL,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Member member = null;
		String openId =openid;
		// snsapi请求获取用户的openId，判断系统是否已经存在该openId,如果存在那么说明该用户已经关注过了。
		if(memb.getOpenId() !=null){
			member = memberService.selectMember(memb.getOpenId());
			if(member == null){
				member = memberService.selectMember(mobilPhone);
			}
		}else{
			member = memberService.selectMember(mobilPhone);
		}
		
		// 会员如果不存在就为他们创建一个账号
		if (member == null) {
			member = new Member();
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			String username = id.substring(0, 6);
			member.setId(id);
			member.setUsername(username);
			member.setPassword(username);
			member.setMobilPhone(mobilPhone);
		   
			if(memb.getOpenId() !=null){
				member.setOpenId(memb.getOpenId());
			}
			//是否订阅
			if(memb.getAge() !=null){
				member.setAge(memb.getAge());
			}
			if(memb.getOpenId() !=null){
				member.setOpenId(memb.getOpenId());
			}
			if(memb.getNickname() !=null){
				member.setNickname(memb.getNickname());
			}
			if(memb.getSex() !=null){
				member.setSex(memb.getSex());
			}
			if(memb.getIcon() !=null){
				member.setIcon(memb.getIcon());
			}
			if(memb.getOrgId() !=null){
				member.setOrgId(memb.getOrgId());
			}
			if(memb.getDescription() !=null){
				member.setDescription(memb.getDescription());
			}
			if(memb.getMemberGroupId() !=null){
				member.setMemberGroupId(memb.getMemberGroupId());
			}
			memberService.insertSelective(member);
		}else{
			if(memb.getOpenId() !=null){
				member.setOpenId(memb.getOpenId());
			}
			//是否订阅
			if(memb.getAge() !=null&&!"".equals(memb.getAge())){
				member.setAge(memb.getAge());
			}
			if(memb.getOpenId() !=null&&!"".equals(memb.getOpenId())){
				member.setOpenId(memb.getOpenId());
			}
			if(memb.getNickname() !=null&&!"".equals(memb.getNickname())){
				member.setNickname(memb.getNickname());
			}
			if(memb.getSex() !=null&&!"".equals(memb.getSex())){
				member.setSex(memb.getSex());
			}
			if(memb.getIcon() !=null&&!"".equals(memb.getOrgId())){
				member.setIcon(memb.getIcon());
			}
			if(memb.getOrgId() !=null&&!"".equals(memb.getOrgId())){
				member.setOrgId(memb.getOrgId());
			}
			if(memb.getDescription() !=null&&!"".equals(memb.getDescription())){
				member.setDescription(memb.getDescription());
			}
			if(memb.getMemberGroupId() !=null&&!"".equals(memb.getMemberGroupId())){
				member.setMemberGroupId(memb.getMemberGroupId());
			}
			if(null==member.getMobilPhone()){
				member.setMobilPhone(mobilPhone);
			}
			memberService.updateByPrimaryKeySelective(member);
		}

		String password = member.getUsername();
		String username = member.getUsername();

		Subject currentUser = SecurityUtils.getSubject();
		password = PasswordHelper.encryptPassword(password);
		UsernamePasswordToken token = new UsernamePasswordToken(username,
				password);
		try {
			if (!currentUser.isAuthenticated()) {
				token.setRememberMe(true);
				currentUser.login(token);// 验证角色和权限
			}
		} catch (Exception ex) {
			uiModel.addAttribute("message", "用户名或者密码错误");
			return "redirect:/memberLogin/login";
		}

		currentUser.getSession().setAttribute("member", member);

		String url = fromURL.indexOf("login") != -1 ? "" : fromURL;
		url = ("".equals(url) ? "redirect:/mindex" : "redirect:" + url);
		boolean isWechatBrowser;
		if (member.getOpenId() == null) {
			String ua = ((HttpServletRequest) httpServletRequest).getHeader(
					"user-agent").toLowerCase();
			if (ua.indexOf("micromessenger") > 0) {// 是微信浏览器
				isWechatBrowser = true;
			}
		}
		return url;
	}

	@RequestMapping(value = { "logout" }, method = RequestMethod.GET)
	public void logout(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			currentUser.logout();// 验证角色和权限
		}
	}

	@RequestMapping(value = { "register" }, method = RequestMethod.GET)
	public String register(Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return basePath + "register";
	}
}
