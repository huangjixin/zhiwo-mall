package com.fulan.application.service;

import com.fulan.api.agent.vo.Req;
import com.fulan.api.agent.vo.ResultBranchs;
import com.fulan.api.agent.vo.ResultCustomer;
import com.fulan.api.agent.vo.ResultModel;
import com.fulan.application.util.domain.Response;

public interface AgentService {
	
	/**
	 * 查找代理人
	 * @param userId
	 * @param token
	 * @param agetnInfo
	 * @return
	 */
	Response<ResultModel> getAgent(String userId,String token,Req agentInfo);
	/**
	 * 查找机构
	 * @param userId
	 * @param token
	 * @param agetnInfo
	 * @return
	 */
	Response<ResultBranchs> getBranch(String userId,String token,Req agentInfo);
	/**
	 * 查找客户列表
	 * @param userId
	 * @param token
	 * @param agentInfo
	 * @return
	 */
	Response<ResultCustomer> getCustomerList(String userId,String token,Req agentInfo);
}

