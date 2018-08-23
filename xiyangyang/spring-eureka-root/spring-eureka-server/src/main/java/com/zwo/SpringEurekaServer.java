/**
 * 
 */
package com.zwo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 黄记新
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringEurekaServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaServer.class, args);

	}

}
