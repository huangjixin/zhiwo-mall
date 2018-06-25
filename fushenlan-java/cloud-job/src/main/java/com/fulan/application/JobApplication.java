package com.fulan.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import com.fulan.core.monitoring.cat.annotation.EnableCat;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = { "com.fulan" })
@EnableCat(basePackages = { "com.fulan.application.**"}, remote = "rest")
public class JobApplication {

	public static void main(String[] args) {
		new SpringApplication(JobApplication.class).run(args);
	}

}
