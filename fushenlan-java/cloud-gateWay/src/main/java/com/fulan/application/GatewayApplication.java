package com.fulan.application;

import javax.servlet.http.HttpServletRequest;

import com.fulan.core.monitoring.cat.annotation.EnableCat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fulan.application.util.spring.SpringUtil;

import feign.Feign;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Retryer;

@SpringCloudApplication
@EnableZuulProxy
@ComponentScan(basePackages={"com.fulan"})
@EnableFeignClients(basePackages = "com.fulan.application.service")
@EnableCat(basePackages = { "com.fulan.application.controller", "com.fulan.application.service.**", "com.fulan.application.mapper" ,"com.fulan.application.custom.controller"
		,"com.fulan.application.manage.controller"},exclusions = {"com.fulan.application.domain"})
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	/***
	 * feign取消重试 默认5次（ConnectTimeout、ReadTimeout）
	 */
	@Bean
	Retryer feignRetryer() {
		return Retryer.NEVER_RETRY;
	}

	/***
	 * feign请求超时设置
	 */
	@Bean
	Request.Options requestOptions(ConfigurableEnvironment env) {
		int ribbonReadTimeout = env.getProperty("ribbon.ReadTimeout", int.class, 30000);
		int ribbonConnectionTimeout = env.getProperty("ribbon.ConnectTimeout", int.class, 6000);
		return new Request.Options(ribbonConnectionTimeout, ribbonReadTimeout);
	}

	@Bean
	public Feign.Builder feignBuilder() {
		return Feign.builder().requestInterceptor(new RequestInterceptor() {
			@Override
			public void apply(RequestTemplate requestTemplate) {
				ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				HttpServletRequest request = attributes.getRequest();
				String sessionId = SpringUtil.getToken(request);
				String token = request.getHeader("Token");
				String code = request.getHeader("code");
				String codeType = request.getHeader("codeType");
				requestTemplate.header("Cookie", "SESSION=" + sessionId + "");
				requestTemplate.header("Token", token);
				requestTemplate.header("code", code);
				requestTemplate.header("codeType", codeType);
			}
		});
	}
}
