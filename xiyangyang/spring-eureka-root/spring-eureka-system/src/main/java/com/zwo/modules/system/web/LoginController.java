package com.zwo.modules.system.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zwo.modules.core.vo.Response;
import com.zwo.modules.system.service.IUserService;

@RestController
public class LoginController {

	@Resource
	private IUserService userService;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/login")
	public Response login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Response response = new Response();
		try {
			response.setMessage("登陸成功");
			return response;
		} catch (Exception e) {
			response.setMessage("用戶名或者密碼錯誤");
			return response;
		}
	}

}