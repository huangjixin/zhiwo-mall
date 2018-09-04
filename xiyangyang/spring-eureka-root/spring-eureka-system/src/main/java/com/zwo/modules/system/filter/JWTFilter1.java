package com.zwo.modules.system.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;

import com.zwo.modules.system.vo.JWTToken;

public class JWTFilter1 extends BasicHttpAuthenticationFilter {
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		// 获取Authorization字段
		String authorization = httpServletRequest.getHeader("Authorization");
		if (authorization != null) {
			String[] array = authorization.split(" ");
			try {
				JWTToken token = new JWTToken(array[0], array[1]);
				// 提交给realm进行登入,如果错误他会抛出异常并被捕获
				getSubject(request, response).login(token);
				return true;
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
	}

}
