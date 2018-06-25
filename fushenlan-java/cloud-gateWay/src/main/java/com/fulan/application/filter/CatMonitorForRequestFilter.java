package com.fulan.application.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dianping.cat.Cat;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class CatMonitorForRequestFilter extends ZuulFilter{

	Logger logger = LoggerFactory.getLogger(CatMonitorForRequestFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requestURL = "";
		try {
			if(request != null) {
				requestURL = request.getRequestURL()!=null?request.getRequestURL().toString():"";
			}
			if(!"".equals(requestURL)){
				Cat.getProducer().logEvent("RequestURL", requestURL);
			}
		} catch (Exception e) {
			logger.error("CatMonitorForRequestFilter.run", e);
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return -1;
	}

}
