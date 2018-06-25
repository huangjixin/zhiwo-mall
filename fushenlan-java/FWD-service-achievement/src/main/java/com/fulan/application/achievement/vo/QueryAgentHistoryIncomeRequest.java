package com.fulan.application.achievement.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 代理人历史收入查询 请求参数
 * 
 * @author FWDuser
 *
 */
public class QueryAgentHistoryIncomeRequest {
	@NotEmpty(message = "代理人编号不能为空")
	private String agentCode; // 代理人编号

	@NotEmpty(message = "时间不能为空")
	private String queryDate; // 时间参数 月份为单位，查询入参的月份的数据

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
}
