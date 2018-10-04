package com.zwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@MapperScan("com.zwo.modules.system.mapper")
public class SpringEurekaAuServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaAuServiceApplication.class, args);
	}
}
