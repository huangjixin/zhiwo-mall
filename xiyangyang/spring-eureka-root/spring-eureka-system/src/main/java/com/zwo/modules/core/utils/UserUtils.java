package com.zwo.modules.core.utils;

import java.util.List;

import javax.servlet.http.HttpUtils;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserUtils {
	public static String getCurrentPrinciple() {
		return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

//	public static String getCurrentToken () {
//	return HttpUtils.getHeaders(HttpUtils.getHttpServletRequest()).get(AUTHORIZATION);
//	}

	public static List<SimpleGrantedAuthority> getCurrentAuthorities() {
		return (List<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
	}
}