package com.fulan.application.filter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fulan.application.util.constant.ExceptionMessageConstant;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/***
 * 异常处理
 */
 @Component
public class ErrorFilter extends ZuulFilter {
	Logger logger = LoggerFactory.getLogger(ErrorFilter.class);

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return -1;
	}

	public boolean shouldFilter() {
		return true;
	}

	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		ctx.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		ctx.set("error.exception", throwable.getCause());
		/*ctx.set("error.message", throwable.getCause().getLocalizedMessage());*/
		ctx.set("error.message", ExceptionMessageConstant.returnMessage(throwable.toString()));
		return null;
	}
}
