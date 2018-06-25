package com.fulan.application.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dianping.cat.Cat;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

@Component
public class CatMonitorForErrorFilter extends ZuulFilter {

	Logger logger = LoggerFactory.getLogger(CatMonitorForErrorFilter.class);

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
