/**
 * 
 */
package com.zwo.modules.system.web;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zwo.modules.core.service.IBaseService;
import com.zwo.modules.core.web.BaseController;
import com.zwo.modules.system.domain.User;
import com.zwo.modules.system.service.IUserService;
import com.zwo.modules.system.vo.UserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author Tony
 *
 */
@Api(tags = "User控制器Api", description = "用户控制器api，包括提供基础的增删改查")
@Controller
@RequestMapping("user")
public class UserController extends BaseController<User> {

	@Autowired
	private IUserService userService;


	@Override
	protected IBaseService<User> getBaseService() {
		return userService;
	}
	
	@ApiOperation(value="获取用户名信息", notes="")
	@GetMapping("getUsername")
	@ResponseBody
	public String getUserName() {
		return "hi,Tony";
	}
	

	@ApiOperation(value="根據用户名查找用户", notes="根據用户名查找用户")
	@GetMapping("findByUsername")
	@ResponseBody
	public UserVo findByUsername(@RequestParam(required=true)String username) {
		UserVo userVo = userService.findByUsername(username);
		return userVo;
	}
	
	@ApiOperation(value="根據用户名查找权限", notes="根據用户名查找权限")
	@GetMapping("findPermissions")
	@ResponseBody
	public Set<String> findPermissions(@RequestParam(required=true)String username) {
		Set<String> set = userService.findPermissions(username);
		return set;
	}
	
	@ApiOperation(value="根據用户名查找角色", notes="根據用户名查找角色")
	@GetMapping("findRoles")
	@ResponseBody
	public Set<String> findRoles(@RequestParam(required=true)String username) {
		Set<String> set = userService.findRoles(username);
		return set;
	}
	
	@ApiOperation(value="删除用户", notes="")
	@DeleteMapping("deteleUser")
	@ResponseBody
	public String deteleUser() {
		return "你已经删除了用户信息";
	}

}
