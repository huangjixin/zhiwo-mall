package com.fulan.api.plan.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.fulan.api.plan.domain.TagTarget;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "plan")
public interface TagTargetService {

	@GetMapping(value = "/customer/tagtarget/addTagTarget")
	Response<String> addTagTarget(TagTarget tagtarget);
	
}
