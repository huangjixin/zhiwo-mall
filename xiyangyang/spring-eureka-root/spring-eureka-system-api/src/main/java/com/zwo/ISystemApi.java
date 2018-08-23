/**
 * 
 */
package com.zwo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 黄记新
 *
 */
@FeignClient(name = "system-service")
public interface ISystemApi {
	
	@GetMapping("user/getUsername")
	public String getUsername();

}