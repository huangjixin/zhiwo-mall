package com.fulan.application.config;
//package com.service;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * 配置HTTPBASIC权限验证
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		// 忽略css.jq.img等文件
//		web.ignoring().antMatchers("/**.html", "/**.css", "/img/**", "/**.js", "/third-party/**");
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		/*if("dev".equals(env)){
//	        http.authorizeRequests().antMatchers("*//**","*//**//*filters").permitAll(); 
//	        http.csrf().disable(); 
//	        http.httpBasic(); 
//	        return; 
//	    }*/  
//		http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();  
//        http.logout().logoutUrl("/logout");  
//        http.csrf().disable();  
//  
//        http.authorizeRequests()  
//            .antMatchers("/login.html", "/**/*.css", "/img/**", "/third-party/**").permitAll();  
//  
//        http.authorizeRequests().antMatchers("/api/**").permitAll().antMatchers("/**")  
//            .authenticated();  
//  
//        // Enable so that the clients can authenticate via HTTP basic for registering  
//        http.httpBasic();  
//	
//	
//	}
//}