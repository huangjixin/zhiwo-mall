package com.fulan.api.system.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fulan.api.system.domain.Version;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "system")
public interface VersionService {

	@PostMapping("/version/insert")
	Response<Boolean> insert(Version version);

	@PostMapping("/version/delete")
	Boolean delete(Long id);

	@PostMapping("/version/update")
	Response<Boolean> updateById(Version version);

	@GetMapping("/version/findById")
	Response<Version> findById(Long id);

	@GetMapping("/version/theNewstVersion")
	Response<String> theNewstVersion();
}
