package com.fulan.application.achievement.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 代理人团队列表查询接口 请求参数
 * 
 * @author FWDuser
 *
 */
public class QueryAgentTeamListRequest {

	@NotEmpty(message = " 代理人代码不能为空")
	private String agentCode; // 代理人代码

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
}
