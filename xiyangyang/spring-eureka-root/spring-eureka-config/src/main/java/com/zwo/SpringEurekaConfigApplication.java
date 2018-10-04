/**
 * 
 */
package com.zwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 黄记新
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
//@EnableSwagger2
public class SpringEurekaConfigApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaConfigApplication.class, args);
	}

}
