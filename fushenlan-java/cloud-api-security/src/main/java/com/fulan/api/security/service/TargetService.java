/**
 * Project Name:cloud-api-security
 * File Name:TargetService.java
 * Package Name:com.fulan.api.security.service
 * Date:2018年3月26日上午9:57:00
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.api.security.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ClassName:TargetService
 * Reason:	 TODO ADD REASON
 * Date:     2018年3月26日 上午9:57:00 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */
@FeignClient(name = "security")
public interface TargetService {


	@GetMapping("/customer/target/selectallaccountIds")
	List<Long>  selectallaccountIds(@RequestParam(name="orgId") String orgId);
	
}

