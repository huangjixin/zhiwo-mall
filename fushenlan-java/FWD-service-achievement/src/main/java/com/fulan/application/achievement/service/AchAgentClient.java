/**
 * Project Name:FWD-service-webservice
 * File Name:CustomerClient.java
 * Package Name:com.fulan.application.service
 * Date:2018年4月9日上午9:58:19
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.application.achievement.service;

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
import com.fulan.application.util.domain.Response;

@FeignClient(name = "AchAgentService", url = "${url.agent.service}")
@RequestMapping(value = "/agentService", produces = {"application/json;charset=UTF-8"})
@ResponseBody
public interface AchAgentClient {
	
	@RequestMapping(value = "/queryAgentHistoryIncomeInfo", method = RequestMethod.POST)
	CommonQueryRepsonse<QueryAgentHistoryIncomeResponse> queryAgentHistoryIncomeInfo(@RequestBody QueryAgentHistoryIncomeRequest req);
	
	
	@RequestMapping(value = "/queryAgentAchievementInfo", method = RequestMethod.POST)
	CommonQueryRepsonse<QueryBasicsActualValueResponse> queryAgentAchievementInfo(@RequestBody QueryBasicsActualValueRequest req);
	
	
}

