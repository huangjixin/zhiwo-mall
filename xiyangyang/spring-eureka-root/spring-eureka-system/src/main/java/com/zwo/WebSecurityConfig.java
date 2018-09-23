package com.zwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import com.zwo.modules.system.service.impl.UserDetailService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Bean
//	UserDetailsService customUserService() { // 2
//		return new SysUserServiceImpl();
//	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(customUserService());
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
	}

	@Autowired
	UserDetailService userDetailService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailService).passwordEncoder(new BCryptPasswordEncoder());
	}

//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.tokenStore(tokenStore()).tokenEnhancer(jwtTokenEnhancer())
//				.authenticationManager(authenticationManager);
//	}
//	
//
//	@Bean
//	public TokenStore tokenStore() {
//		return new JwtTokenStore(jwtTokenEnhancer());
//	}
//
//	@Bean
//	protected JwtAccessTokenConverter jwtTokenEnhancer() {
//		KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("fzp-jwt.jks"),
//				"fzpl23".toCharArray());
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		converter.setKeyPair(keyStoreKeyFactory.getKeyPair("fzp-jwt"));
//		return converter;
//	}
//	
//
//	@Autowired
//	@Qualifier("authenticationManagerBean")
//	private AuthenticationManager authenticationManager;
}