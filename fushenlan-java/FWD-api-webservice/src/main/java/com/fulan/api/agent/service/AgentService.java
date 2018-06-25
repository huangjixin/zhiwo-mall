package com.fulan.api.agent.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.agent.vo.Req;
import com.fulan.api.agent.vo.ResultModel;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 获取代理人信息
 * </p>
 *
 * @since 2018-03-20
 */
@FeignClient(name = "WebService")
public interface AgentService {
	/**
	 * 获取代理人信息
	 * @param account
	 * @return
	 */
	@PostMapping("/agentServiceFromDms/getAgentFromDms")
	Response<ResultModel> getAgentFromDms(@RequestParam("userId") String userId,
							       		  @RequestParam("token") String token,
							              @RequestBody Req agentInfoVo);
	/**
	 * 获取代理人机构信息
	 * @param agentInfoVo
	 * @return
	 */
	@PostMapping("/agentService/getBranch")
	Response<String> getBranch(Req agentInfoVo);

}
