package com.fulan.core.monitoring.cat.monitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dianping.cat.Cat;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class CatGateWayForErrorMonitor extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(CatGateWayForErrorMonitor.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {

		RequestContext ctx = RequestContext.getCurrentContext();
		try {
			if(ctx != null){
				Throwable throwable = ctx.getThrowable();
				Cat.getProducer().logError(throwable);
			}
		} catch (Exception e) {
			logger.error("CatMonitorForErrorFilter.run", e);
		}
		return null;
	}

	@Override
	public String filterType() {
		return "error";
	}

	@Override
	public int filterOrder() {
		return -2;
	}

}
