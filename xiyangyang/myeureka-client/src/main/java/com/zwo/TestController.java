/**
 * 
 */
package com.zwo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 黄记新
 *
 */
@RestController
@RequestMapping("client")
public class TestController {
	
	@GetMapping("hello")
	public String hello() {
		return "hello,world";
	}
}
