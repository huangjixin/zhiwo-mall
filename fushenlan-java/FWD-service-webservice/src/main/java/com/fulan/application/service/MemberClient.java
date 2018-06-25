package com.fulan.application.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.agent.vo.CustomerSearchParm;
import com.fulan.api.agent.vo.ValidateUserReq;

@FeignClient(name = "WebService", url = "${url.xapi}")
@RequestMapping(value = "/ACCMWS/member",produces = {"application/json;charset=UTF-8"})
@ResponseBody
public interface MemberClient {
	@RequestMapping(value = "/validateUser", method = RequestMethod.POST)
	String validateUser(@RequestHeader("userId") String userId,@RequestHeader("loginType") String loginType,
			@RequestHeader("token") String token,@RequestBody ValidateUserReq req);


}