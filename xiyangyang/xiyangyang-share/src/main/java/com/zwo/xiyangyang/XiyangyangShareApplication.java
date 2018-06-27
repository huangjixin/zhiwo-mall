package com.zwo.xiyangyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.zwo.xiyangyang")
@EnableScheduling
@EnableWebMvc
public class XiyangyangShareApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiyangyangShareApplication.class, args);
	}
}
