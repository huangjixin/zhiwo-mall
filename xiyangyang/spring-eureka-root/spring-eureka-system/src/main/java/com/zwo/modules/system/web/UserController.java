/**
 * 
 */
package com.zwo.modules.system.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Tony
 *
 */
@Api(tags = "User控制器Api", description = "用户控制器api，包括提供基础的增删改查")
@Controller
@RequestMapping("user")
public class UserController {

	@ApiOperation(value="获取用户名信息", notes="")
	@GetMapping("getUsername")
	@ResponseBody
	public String getUserName() {
		return "hi,Tony";
	}
	

	@ApiOperation(value="删除用户", notes="")
	@DeleteMapping("deteleUser")
	@ResponseBody
	public String deteleUser() {
		return "你已经删除了用户信息";
	}
}
