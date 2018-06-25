package com.fulan.application.operatelog.intercepter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LogInterceptorConfig extends WebMvcConfigurerAdapter{

	@Bean
	OperationLogInteceptor localInterceptor() {
        return new OperationLogInteceptor();
    }

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localInterceptor()).addPathPatterns("/**");
	}
}
