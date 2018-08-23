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
@Api(tags = "Role控制器Api", description = "角色控制器api，包括提供基础的增删改查")
@Controller
@RequestMapping("role")
public class RoleController {

	@ApiOperation(value="获取用户名信息", notes="")
	@GetMapping("getRolename")
	@ResponseBody
	public String getRoleName() {
		return "hi,Tony";
	}
	

	@ApiOperation(value="删除用户", notes="")
	@DeleteMapping("deteleRole")
	@ResponseBody
	public String deteleRole() {
		return "你已经删除了用户信息";
	}
}
