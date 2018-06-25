package com.fulan.core.monitoring.cat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dianping.cat.Cat;
import com.fulan.core.monitoring.cat.LoadCatDefinition.CatDefinition;

@Component
public class ServerStarterListenerForCat implements ApplicationListener<ContextRefreshedEvent>{
	
	private static Logger logger = LoggerFactory.getLogger(ServerStarterListenerForCat.class);

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			CatDefinition catDefinition = LoadCatDefinition.getCatDefinition();
			if(catDefinition != null && catDefinition.isEnabledCat()){
				Cat.getProducer().logTrace("Application.start", "success");
			}
		} catch (Exception e) {
			logger.error("无法连接到cat监控服务器...");
		}
	}

}
