/**
 * 
 */
package com.zwo;

import java.util.Base64;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import com.zwo.modules.system.service.impl.UserDetailService;

/**
 * @author lenovo
 *
 */
@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity
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
        http
            .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
            .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated()
            .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetail)
                .passwordEncoder(passwordEncoder());
    }

	@Bean
	public PasswordEncoder passwordEncoder() {
		// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new BCryptPasswordEncoder();
	}


	public static void main(String[] args) {
//		String content = "user-service:123456";
//		String encodeContent = Base64.getEncoder().encodeToString(content.getBytes());
//		System.out.println(encodeContent);
//		byte[] bs = Base64.getDecoder().decode(encodeContent);
//		String res = new String(bs);
//		System.out.println(res);
    	Jwt jwt = JwtHelper.decode("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Mzg3ODA0NDcsInVzZXJfbmFtZSI6IjEiLCJhdXRob3JpdGllcyI6WyJzeXN0ZW06dXNlciIsInN5c3RlbTpyZXNvdXJjZTplZGl0Iiwic3lzdGVtOnVzZXI6dmlldyIsInN5c3RlbTpyZXNvdXJjZSIsInN5c3RlbTpyZXNvdXJjZTp2aWV3Iiwic3lzdGVtOnVzZXI6ZGVsZXRlIiwic3lzdGVtOnJlc291cmNlOmRlbGV0ZSIsInN5c3RlbTp1c2VyOmVkaXQiXSwianRpIjoiYzE0MDY4YmItYWI2Mi00MDEyLWE5OTEtMTA0ZGJhNmFmMjU3IiwiY2xpZW50X2lkIjoidXNlci1zZXJ2aWNlIiwic2NvcGUiOlsic2VydmljZSJdfQ.T2MWvmIqbjfLTZCIiSIQq_5IgaCPUxwDChQxbvnccoRyDr_luhjPKSeJC59W6XBPU4lUC4yCdFpQa9BdnzW2cSRk6hnHXoxlXE1IvpUj4zkmHbnn4ZOYPk7bY81warvreOz_SWHp7BiNgOr5XvLdTyBLhFhRRfF5lcXq8spo2nBQUYppWAX-ZcFJksnGhgRc4nf1g_g0t3iOS0_UzZ9ydm6PqKZhEJRrsGkyvNjTC0v7-F27rGnyBn6mmS349--lfFX800x_0XN0KmKsMg1eJJbGn5uGU_6gUDOKsb7-TaMXyoQhMopXZy6O0coRmsi8jpquzbTBJpUHRj5fG9XNUQ");
    	System.out.println(jwt.getClaims());
	}
}
