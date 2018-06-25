/**
 * Project Name:elearn-core
 * File Name:RefererFilter.java
 * Package Name:com.fulan.application.filter
 * Date:2018年1月4日下午5:46:18
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.filter;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;
/**
 * ClassName:RefererFilter
 * Reason:	 TODO ADD REASON
 * Date:     2018年1月4日 下午5:46:18 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */
//@Configuration
public class RefererFilter extends OncePerRequestFilter implements Ordered  {

	   public static final String UTF = "utf-8";
	/**
	 * 在请求URL时自动调用，在ParameterRequestWrapper中调用
	 * @see org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 链接来源地址     
		  String referer = request.getHeader("Referrer");  
		  
        if (referer == null || !referer.contains(request.getServerName())) {  
        	/** TODO
             * 如果 链接地址来自其他网站，则返回错误页面 
             */        
        	response.setHeader("Content-type", "text/html;charset=UTF-8");  
            String data = "error";  
            OutputStream ps = response.getOutputStream();  
            //这句话的意思，使得放入流的数据是utf8格式  
            ps.write(data.getBytes("UTF-8"));  
            ps.close();
        } else {   
        	filterChain.doFilter(request, response);  
        }  
	}
	
	//设置优先级
	public int getOrder() {
		
		return 2;
	}

	
	 /**
     * 回收
     * */
    @Override
    public void destroy() {

    }
}

