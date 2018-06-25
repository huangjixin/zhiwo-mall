package com.fulan.application;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fulan.application.filter.WebViewFilter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//	    @Bean
//	    public FilterRegistrationBean filterRegistrationBean() {
//	        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//	        WebSiteMeshFilter siteMeshFilter = new WebSiteMeshFilter();  
//	        filterRegistrationBean.setFilter(siteMeshFilter);
//	        filterRegistrationBean.addUrlPatterns("/*");
//	        filterRegistrationBean.setEnabled(true);
//	        return filterRegistrationBean;
//	    }
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	    	 registry.addInterceptor(new WebViewFilter()).addPathPatterns("/*");
	    	 super.addInterceptors(registry);
	    }
}
