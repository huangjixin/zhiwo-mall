package com.fulan.application.util.spring;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * 获取Spring Bean工具类
 */
public class SpringUtil {

	private static ApplicationContext applicationContext = null;
	
	private static Logger logger = LoggerFactory.getLogger(SpringUtil.class);

	/**
	 * 需要手动注入，才可以使用
	 * @param applicationContext
	 * @throws BeansException
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 根据Bean名称获取实例
	 * 
	 * @param name
	 * @return bean实例
	 * @throws BeansException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {

		return (T) applicationContext.getBean(name);
	}

	public static Map<String, ?> getBeansOfType(Class<?> class1) {
		return applicationContext.getBeansOfType(class1);
	}

	/**
	 * 根据类型获取实例
	 *
	 * @param type
	 *            类型
	 * @return bean实例
	 * @throws BeansException
	 */
	public static <T> T getBean(Class<T> type) throws BeansException {
		return applicationContext.getBean(type);
	}

	/**
	 * 根据类型获取实例
	 *
	 * @param type
	 *            类型
	 * @return bean实例
	 * @throws BeansException
	 */
	public static <T> T getBean(String beanName,Class<T> type) throws BeansException {
		return applicationContext.getBean(beanName,type);
	}

	/***
	 * 获取请求体token
	 */
	public static String getToken(HttpServletRequest request) {
		Cookie[] cookie = request.getCookies();
		if (cookie == null) {
			return null;
		}
		for (int i = 0; i < cookie.length; i++) {
			Cookie cook = cookie[i];
			logger.info("请求Cookie："+cook.getName()+":"+cook.getValue());
			if (cook.getName().equals("SESSION") && !"".equals(cook.getValue()+"".trim())) {
				return cook.getValue().toString();
			}
		}
		return null;
	}
	
	
}
