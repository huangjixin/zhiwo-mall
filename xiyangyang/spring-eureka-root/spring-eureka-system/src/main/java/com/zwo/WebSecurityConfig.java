/**
 * 
 */
package com.zwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.zwo.modules.system.service.impl.UserDetailService;

/**
 * @author 黄记新
 *
 */
//@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailService userServiceDetail;
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll()
		// 允许所有请求通过 
		.and() .csrf() .disable() 
		// 禁用 Spring Security 自带的跨域处理 
		.sessionManagement() 
		// 定制我们自己的 session 策略 
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS); 
		// 调整为让 Spring Security 不创建和使用 session 

		//		http
//        .csrf().disable()
//        .authorizeRequests()
//        .regexMatchers(".*swagger.*",".*v2.*",".*webjars.*","/user/login.*","/user/registry.*","/user/test.*").permitAll()
//        .antMatchers("/**").authenticated();
//.antMatchers("/**").permitAll();
		// CSRF:因为不再依赖于Cookie，所以你就不需要考虑对CSRF（跨站请求伪造）的防范。
//		http.csrf().disable().exceptionHandling()
//				// .authenticationEntryPoint((request, response, authException) ->
//				// response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//				.authenticationEntryPoint(new AuthenticationEntryPoint() {
//					@Override
//					public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
//							AuthenticationException e) throws IOException, ServletException {
//						httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//					}
//				}).and().authorizeRequests().antMatchers("/**").authenticated().and().httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//        .passwordEncoder(new BCryptPasswordEncoder())//在此处应用自定义PasswordEncoder
//        .withUser("huangjixin")
//        .password("password")
//        .roles("USER");

		auth.userDetailsService(userServiceDetail).passwordEncoder(new BCryptPasswordEncoder());
	}
}
