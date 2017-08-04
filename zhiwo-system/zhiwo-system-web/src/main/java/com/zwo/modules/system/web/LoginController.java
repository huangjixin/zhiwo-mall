package com.zwo.modules.system.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zwo.modules.system.domain.TbUser;
import com.zwotech.common.web.BaseController;

/**
 * @author Administrator
 * 登录控制器。
 */
@Controller
@RequestMapping("login")
@Lazy(true)
public class LoginController extends BaseController<TbUser> {
	
	private static final String basePath = "views/login";
	
	@RequestMapping(value = {""})
	public String list(HttpServletRequest httpServletRequest) {
		return basePath+"login";
	}
	
}
