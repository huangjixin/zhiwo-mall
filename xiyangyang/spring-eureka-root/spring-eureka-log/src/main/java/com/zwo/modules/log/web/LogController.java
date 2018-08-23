/**
 * 
 */
package com.zwo.modules.log.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.ISystemApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Tony
 *
 */
@Api(tags = "日志控制器，主要记录各种操作。控制器Api", description = "角色控制器api，包括提供基础的增删改查")
@Controller
@RequestMapping("log")
public class LogController {
	
	@Autowired
	private ISystemApi systemApi;

	@ApiOperation(value = "通过systemapi获取用户名信息", notes = "")
	@GetMapping("getUsername")
	@ResponseBody
	public String getUsername() {
		String username = this.systemApi.getUsername();
		
		return username;
	}
}
