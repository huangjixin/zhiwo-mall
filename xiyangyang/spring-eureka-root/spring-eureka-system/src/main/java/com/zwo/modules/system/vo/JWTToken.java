package com.zwo.modules.system.vo;

import org.apache.shiro.authc.AuthenticationToken;

public class JWTToken implements AuthenticationToken {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 用户名 
	private String username;
	// 密钥 
	private String token;

	public JWTToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return username;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}