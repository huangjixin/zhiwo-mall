/**
 * 
 */
package com.zwo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.zwo.modules.system.domain.JWT;

/**
 * @author 黄记新
 *
 */
@FeignClient(name = "system-service")
public interface ISystemApi {
	
	@GetMapping("user/getUsername")
	public String getUsername();

	@PostMapping(value = "/oauth/token")
    JWT getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type,
                 @RequestParam("username") String username, @RequestParam("password") String password);
}