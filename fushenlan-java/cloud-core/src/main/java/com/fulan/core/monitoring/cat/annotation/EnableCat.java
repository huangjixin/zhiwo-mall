package com.fulan.core.monitoring.cat.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Cat监控开启注解
 * @author c_liziqing
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface EnableCat {
	
	/** 
	 * <pre>
	 * 配置的包里面的类将会被监控.
	 * 支持*(一级目录),**(多级目录)通配符
	 *  条件：
	 * 包里面的类需要被spring容器管理. 
	 * 目前带有@Controller,@RestController,@Service
	 * 注解的类被纳入监控范围.
	 * </pre>
	 *
	 */
	String[] basePackages() default {};
	
	/** 排除某些包 */
	String[] exclusions() default {};
	
	/**
	 * <pre>
	 * 远程调用监控 
	 * 默认值为"no"
	 * no: 不开启远程调用监控
	 * rest: 开启restTemplate监控
	 * </pre>
	 * @return
	 */
	String remote() default "no";
	
	/**
	 * <pre>
	 * 持久层监控 
	 * 默认值为"no"
	 * no: 不开启持久层监控
	 * jpa: 开启spring data jpa监控
	 * </pre>
	 * @return
	 */
	String dao() default "no";
	
	/**
	 * <pre>
	 * 网关层监控 
	 * 默认值为"false"
	 * false: 不开启网关层监控
	 * true: 开启网关层监控
	 * </pre>
	 * @return
	 */
	String enabledGateWay() default "false";
}
