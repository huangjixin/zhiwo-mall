package com.fulan.core.monitoring.cat.monitor;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.cat.Cat;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class CatGateWayMonitor extends ZuulFilter{

	Logger logger = LoggerFactory.getLogger(CatGateWayMonitor.class);

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
