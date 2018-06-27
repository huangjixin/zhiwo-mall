package com.zwo.xiyangyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tk.mybatis.spring.annotation.MapperScan;

@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = "com.zwo.xiyangyang")
@EnableCaching
//@EnableScheduling
//@EnableWebMvc
public class XiyangyangAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(XiyangyangAdminApplication.class, args);
	}
}
