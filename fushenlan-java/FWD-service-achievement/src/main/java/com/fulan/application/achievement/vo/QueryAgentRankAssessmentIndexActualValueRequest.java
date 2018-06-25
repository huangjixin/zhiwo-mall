package com.fulan.application.achievement.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 代理人职级维持考核指标实际值查询接口
 * 
 * 代理人职级晋级考核指标实际值查询接口 请求参数
 * 
 * @author FWDuser
 *
 */
public class QueryAgentRankAssessmentIndexActualValueRequest {

	@NotEmpty(message = "代理人编号不能为空")
	private String agentCode; // 代理人编号

	private String line; // 职级发展路线

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
}
