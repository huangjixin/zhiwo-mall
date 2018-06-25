package com.fulan.api.security.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.security.domain.OperateLog;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "user")
public interface OperateLogOtherService {
	/**
	 * 查询所有log
	 * @return
	 */
	@PostMapping("/operateLog/selectList")  
	List<OperateLog> selectByMap();
	
	
	@GetMapping(value = "/operateLog/selectPageList")
	PageInfo<OperateLog> selectByPage(
	            @RequestParam(name = "requestIp", required = false) String requestIp,
	            @RequestParam(name = "requestUrl", required = false) String requestUrl,
	            @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
	    );
	
}
