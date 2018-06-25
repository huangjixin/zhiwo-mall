package com.fulan.application.agent.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fulan.api.agent.vo.ValidateUserReq;
import com.fulan.application.service.MemberService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.log.annotation.NoLog;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@NoLog
@Api(tags = "member", description = "dms登录")
@RestController
@RequestMapping(value ="/member",produces = { "application/json;charset=utf-8" })
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@ApiOperation(value = "dms登录", notes = "登录", response = Response.class)
	@RequestMapping(value = "/validateUser",produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	@ResponseBody
	public Response<Boolean> validateUser(String userId,
			 String loginType,String token,@RequestBody ValidateUserReq req) {
		try {
			return memberService.validateUser(userId,loginType,token,req);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<Boolean>(Response.ERROR,"DMS登录失败");
		}
	}
}
