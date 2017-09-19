package com.zwo.modules.zhihuiduo.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.service.IMemberService;
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

	// @SuppressWarnings("rawtypes")
	// private RedisTemplate redisTemplate =
	// SpringContextHolder.getBean("redisTemplate");

	private static final String basePath = "views/member/";

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
		return login(uiModel, httpServletRequest, httpServletResponse,"");
	}

	@RequestMapping(value = { "login" }, method = RequestMethod.GET)
	public String login(Model uiModel, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse,
			@RequestParam(required=false,defaultValue="") String fromURL) {
		String localAddr = httpServletRequest.getLocalAddr();
		String remoteAddr = httpServletRequest.getRemoteAddr();
		String URL=httpServletRequest.getHeader("Referer");
		uiModel.addAttribute("fromURL",URL);
		
		return basePath + "login";
	}

	@RequestMapping(value = { "login" }, method = RequestMethod.POST)
	public String loginForm(Model uiModel, @RequestParam String username,
			@RequestParam String password,
			@RequestParam(required=false,defaultValue="") String fromURL,
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
			return "/memberLogin/login";
		}
		Member member = memberService.selectMember(username);
		currentUser.getSession().setAttribute("member", member);
		// uiModel.addAttribute("member", member);
		//String url = WebUtils.getSavedRequest(httpServletRequest).getRequestUrl();
		String url = fromURL;
		url=("".equals(url)?"redirect:/mindex":"redirect:"+url);
		return url;
	}
	

	@RequestMapping(value = { "register" }, method = RequestMethod.GET)
	public String register(Model uiModel,
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		uiModel.addAttribute("rawData", 123456);
		return basePath + "register";
	}
}
