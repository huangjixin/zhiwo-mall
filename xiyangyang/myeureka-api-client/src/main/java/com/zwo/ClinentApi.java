package com.zwo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "service1")
public interface ClinentApi {
	
	@GetMapping("client/hello")
	public String hello();
	
}
