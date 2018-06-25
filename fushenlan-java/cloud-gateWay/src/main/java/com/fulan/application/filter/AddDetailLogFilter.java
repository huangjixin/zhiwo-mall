package com.fulan.application.filter;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/***
 * 日志过滤器
 */
@Component
public class AddDetailLogFilter extends ZuulFilter {

	private static Logger logger = LoggerFactory.getLogger("zuullog");

	@Value("${acmp.gateway.log.onoff:0}")
	private int logSwitch;

	@Override
	public String filterType() {
		// pre，routing，post，error
		return "pre";
	}

	@Override
	public int filterOrder() {
		// 执行顺序
		return -1;
	}

	public boolean shouldFilter() {
		return logSwitch != 0;
	}

	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		logger.info("URL : " + request.getRequestURL().toString());
		logger.info("HTTP_METHOD : " + request.getMethod());
		logger.info("REMOTE_IP : " + getRemoteIp(request));
		String localIp = "";

		try {
			localIp = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			logger.error("未知主机错误", e);
		}
		logger.info("LOCAL_IP : " + localIp);
		logger.info("METHOD : " + request.getMethod());
		Map<String, List<String>> map = ctx.getRequestQueryParams();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		if (map != null) {
			for (String key : map.keySet()) {
				for (String value : map.get(key)) {
					params.add(key, value);
				}
			}
		}
		logger.info("requestParams " + params);
		try {
			InputStream inputStream = request.getInputStream();
			String requestBody = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
			logger.info("requestBody " + requestBody);
		} catch (IOException e) {
			logger.error("流解析错误", e);
		}
		
		return null;
	}


	private String getRemoteIp(HttpServletRequest request) {
		String remoteIp = null;
		String remoteAddr = request.getRemoteAddr();
		String forwarded = request.getHeader("X-Forwarded-For");
		String realIp = request.getHeader("X-Real-IP");
		if (realIp == null) {
			if (forwarded == null) {
				remoteIp = remoteAddr;
			} else {
				remoteIp = remoteAddr + "/" + forwarded.split(",")[0];
			}
		} else {
			if (realIp.equals(forwarded)) {
				remoteIp = realIp;
			} else {
				if (forwarded != null) {
					forwarded = forwarded.split(",")[0];
				}
				remoteIp = realIp + "/" + forwarded;
			}
		}
		return remoteIp;
	}
}

