package com.fulan.application.util.spring;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 初始化SpringUtil中的applicationContext,防止Aware实例方法注入静态变量无法通过静态代码扫描
 * @author c_xiaofang-001
 *
 */
@Component
public class ApplicationManager implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(SpringUtil.getApplicationContext() == null) {
			SpringUtil.setApplicationContext(event.getApplicationContext());
		}
	}
}
