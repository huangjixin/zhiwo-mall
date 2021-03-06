package com.fulan.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.fulan.core.monitoring.cat.annotation.EnableCat;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.fulan"})
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 60*60*24)// session过期时间24小时
@EnableCat(basePackages = { "com.fulan.application.controller", "com.fulan.application.service.**", "com.fulan.application.mapper" ,"com.fulan.application.custom.controller"
							,"com.fulan.application.manage.controller"})
public class MessageApplication {
	public static void main(String[] args) {
		SpringApplication.run(MessageApplication.class, args);
	}
}
