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
    	Jwt jwt = JwtHelper.decode("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1Mzg3NjMwOTAsInVzZXJfbmFtZSI6IjEiLCJhdXRob3JpdGllcyI6WyIxIl0sImp0aSI6IjUwYTBiOTVhLWQwZGEtNGY1YS05NDI0LTQxZTFkMmM1YTVlOSIsImNsaWVudF9pZCI6InVzZXItc2VydmljZSIsInNjb3BlIjpbInNlcnZpY2UiXX0.I9rN77gGj7YKXVe5q7cTToiyYYLbE5ngzWMCjgHnbiU3wIAVQMz0fHlfl1LB9NOnN4qnwqp5IigaQrIzPnemYYAfMIEE5l8kOBc5M1bwTQmY4-jULQO9zku3YlJ1r43SMTrbR6lL95wA0fCvWtQfu-udeWQLj0neCaN60g9gCgDaQDIXDHWGVHpqorMjoF402kXO4HYKFs8YATsCRqgcVQV8htx5R_RBdvvuse2PGggfCqg4c1eJs8bURj_7T8QmABU1pWHOZZvDvdE64ki9rVdZ4pKUxMOZ36prTpNmT8K4XBVwYbnZQJ8mse6ibCGkBi90iuTC_f3AqM3vjZd8BA");
    	System.out.println(jwt.getClaims());
	}
}
