package com.zwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 黄记新
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class SpringEurekaLogApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaLogApplication.class, args);
	}

}