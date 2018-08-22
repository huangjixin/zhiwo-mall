/**
 * 
 */
package com.zwo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author FWDuser
 *
 */
@RestController
@RequestMapping("client2")
public class Test2Controller {
	
	@Autowired
	private ClinentApi api;

	@GetMapping("hello")
	public String getHello() {
		String s = this.api.hello();
		return s;
	}
}
