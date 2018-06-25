/**
 * Project Name:elearn-core
 * File Name:XssFilter.java
 * Package Name:com.fulan.application.filter
 * Date:2018年1月4日下午3:59:31
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * ClassName:XssFilter Reason: TODO ADD REASON Date: 2018年1月4日 下午3:59:31
 * filter都默认继承OncePerRequestFilter，OncePerRequestFilter顾名思义，他能够确保在一次请求只通过一次filter，而不需要重复执行。
 * @author chen.zhuang
 * @version
 * @since JDK 1.8
 */
//@Configuration
public class XssFilter extends OncePerRequestFilter implements Ordered {

	
	/**
	 * 在请求URL时自动调用，在ParameterRequestWrapper中调用 cleanXss方法进行特殊处理
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

        	ParameterRequestWrapper requestWrapper = new ParameterRequestWrapper(request);
    		requestWrapper.cleanXss();
    		filterChain.doFilter(new SessionRequestWrapper(requestWrapper, 3600), response);
        
	}
	//设置优先级
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}

	public class SessionRequestWrapper extends HttpServletRequestWrapper {
		public SessionRequestWrapper(HttpServletRequest request, int maxInactiveInterval) {
			super(request);
			request.getSession().setMaxInactiveInterval(maxInactiveInterval);
		}
	}
	
	 /**
     * 回收
     * */
    @Override
    public void destroy() {

    }
}
