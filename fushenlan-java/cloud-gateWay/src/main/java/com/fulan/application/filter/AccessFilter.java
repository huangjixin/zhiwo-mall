package com.fulan.application.filter;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fulan.application.context.CommenConstant;
import com.fulan.application.token.TokenHelper;
import com.fulan.application.util.constant.GlobalConstant;
import com.fulan.application.util.str.StringUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import io.jsonwebtoken.Claims;

/**
 * 网关过滤器
 */
@Component
public class AccessFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);

	@Autowired
	private TokenHelper tokenHelper;

	@Override
	public String filterType() {
		// pre，routing，post，error
		return "pre";
	}

	@Override
	public int filterOrder() {
		// 执行顺序
		return 0;
	}

	public boolean shouldFilter() {
		return true;
	}

	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
		String url = ctx.getRequest().getRequestURL().toString();
		String token = ctx.getRequest().getHeader(GlobalConstant.TOKEN_HEADER);
		String currentUrl = ctx.getRequest().getServletPath();

		//token 为空是处理
		if (StringUtils.isEmpty(token)) {
			//是否是登录请求    不过滤地址
//			if (!isLoginUrl(currentUrl) && !isUnfiltered_Url(currentUrl) ) {
//				ctx.setResponseStatusCode(403);
//				ctx.setResponseBody("no  token!");
//				ctx.setSendZuulResponse(false);
//			}

		} else {
			Claims claims = tokenHelper.parseToken(token);
			if (claims == null && !isLoginUrl(currentUrl)) {
				ctx.setResponseStatusCode(403);
				ctx.setResponseBody("token Wrongful!");
				ctx.setSendZuulResponse(false);
			} else {

			}
		}

		return null;
	}

	/**
	 * 是否是登录请求
	 */
	private boolean isLoginUrl(String url) {
		if (url.contains(CommenConstant.LOGIN_URL)) {
			return true;
		}
		return false;
	}

	/**
	 * 不过滤地址
	 */
	private boolean isUnfiltered_Url(String url) {
		String[] str = CommenConstant.UNFILTERED_URL.split(",");
		for(int i=0;i<str.length;i++){
			if (url.contains(str[i])) {
				return true;
			}
		}
		return false;
	}


	/*
	 * 获取客户端真实ip
	 */
	public String getRemortIP() {

		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (Exception e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			// = 15
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;

	}

	/**
	 * 判断ip是否在白名单里
	 */
	public boolean isWhiteList() {
		String ip = getRemortIP();
		// 读取配置文件信息
		// InputStream inStream = new FileInputStream(new
		// File("application.properties"));
		InputStream in = ClassLoader.getSystemResourceAsStream("application.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String configIp = prop.getProperty("ip");
		if (!StringUtil.isEmpty(ip)) {// ip不为空
			if (ip.equals(configIp)) {
				return true;
			} else {
				return false;
			}
		} else {// ip为空
			return false;
		}
	}

}
