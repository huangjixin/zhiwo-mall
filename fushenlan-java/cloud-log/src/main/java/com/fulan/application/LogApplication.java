package com.fulan.application;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import com.fulan.application.operatelog.constant.QueueConstant;


@SpringBootApplication
@EnableEurekaClient
public class LogApplication {
	
	@Bean
    public Queue createLogQueue() {
        return new Queue(QueueConstant.OPERATE_LOG_QUEUE);
    }
	
	public static void main(String[] args) {
		SpringApplication.run(LogApplication.class, args);
	}
	
	
}
