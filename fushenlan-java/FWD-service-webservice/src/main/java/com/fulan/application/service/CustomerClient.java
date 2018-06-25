/**
 * Project Name:FWD-service-webservice
 * File Name:CustomerClient.java
 * Package Name:com.fulan.application.service
 * Date:2018年4月9日上午9:58:19
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.agent.vo.CustomerSearchParm;
import com.fulan.api.agent.vo.CustomerVo;

/**
 * ClassName:CustomerClient
 * Reason:	 TODO ADD REASON
 * Date:     2018年4月9日 上午9:58:19 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */
@FeignClient(name = "WebService", url = "${url.xapi}")
@RequestMapping(value = "/CCRWS",produces = {"application/json;charset=UTF-8"})
@ResponseBody
public interface CustomerClient {

	
	@RequestMapping(value = "/customer/search", method = RequestMethod.POST)
	String customerSearch(@RequestHeader("userId") String userId,@RequestHeader("systemId") String systemId,
			@RequestHeader("token") String token,@RequestBody CustomerSearchParm req);
	
	
	
	@RequestMapping(value = "/lead/create", method = RequestMethod.POST)
	String customerCreate(@RequestHeader("userId") String userId,@RequestHeader("systemId") String systemId,
			@RequestHeader("token") String token,@RequestBody CustomerVo req);


	@RequestMapping(value = "/customer/update", method = RequestMethod.POST)
	String customerUpdate(@RequestHeader("userId") String userId,@RequestHeader("systemId") String systemId,
			@RequestHeader("token") String token,@RequestBody CustomerVo req);
}

