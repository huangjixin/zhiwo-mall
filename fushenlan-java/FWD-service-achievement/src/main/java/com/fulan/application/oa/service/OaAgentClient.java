/**
 * Project Name:FWD-service-webservice
 * File Name:CustomerClient.java
 * Package Name:com.fulan.application.service
 * Date:2018年4月9日上午9:58:19
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.oa.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.application.achievement.vo.CommonQueryRepsonse;
import com.fulan.application.achievement.vo.QueryAgentHistoryIncomeRequest;
import com.fulan.application.achievement.vo.QueryAgentHistoryIncomeResponse;
import com.fulan.application.achievement.vo.QueryBasicsActualValueRequest;
import com.fulan.application.achievement.vo.QueryBasicsActualValueResponse;
import com.fulan.application.oa.vo.OaReqParamAgentCodeDto;
import com.fulan.application.oa.vo.OaRespAgentGroupInfoDto;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "OaAgentService", url = "${url.agent.service}")
@RequestMapping(value = "/agentService", produces = {"application/json;charset=UTF-8"})
@ResponseBody
public interface OaAgentClient{
	
	@RequestMapping(value = "/queryAgentGroupInfo", method = RequestMethod.POST)
	CommonQueryRepsonse<OaRespAgentGroupInfoDto> queryAgentGroupInfo(@RequestBody OaReqParamAgentCodeDto req);
	
}

