package com.zwo.modules.system.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zwo.modules.core.vo.Response;
import com.zwo.modules.system.security.PasswordCreator;
import com.zwo.modules.system.utils.JWTUtil;

import io.swagger.annotations.Api;

@Api(tags = "登陸控制器")
@RestController
public class LoginController {

//	@Resource
//	private IUserService userService;

//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("/login")
	public Response login(@RequestParam("username") String username, @RequestParam("password") String password) {
		Response response = new Response();
		try {
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
			SecurityUtils.getSubject().login(usernamePasswordToken);
			String cryptPassword = (String) PasswordCreator.ecrypt(password, null);
			Object token = JWTUtil.sign(username, cryptPassword);
			response.setData(token);
			response.setMessage("登陸成功");
			return response;
		} catch (Exception e) {
			response.setMessage("用戶名或者密碼錯誤");
			return response;
		}
	}

}