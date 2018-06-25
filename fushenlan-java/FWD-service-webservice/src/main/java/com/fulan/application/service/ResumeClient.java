package com.fulan.application.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.agent.vo.Req;

@FeignClient(name = "WebService", url = "${url.xapi}")
@RequestMapping(value = "/AGENTWS/agentService",produces = {"application/json;charset=UTF-8"})
@ResponseBody
public interface ResumeClient {
	@RequestMapping(value = "/getAgent", method = RequestMethod.POST)
	String getAgent(@RequestHeader("userId") String userId,@RequestHeader("token") String token,@RequestBody Req req);

	@RequestMapping(value = "/getBranch", method = RequestMethod.POST)
	String getBranch(@RequestHeader("userId") String userId,@RequestHeader("token") String token,@RequestBody Req req);

	@RequestMapping(value = "/getCustomerList", method = RequestMethod.POST)
	String getCustomerList(@RequestHeader("userId") String userId,@RequestHeader("token") String token,@RequestBody Req req);


}