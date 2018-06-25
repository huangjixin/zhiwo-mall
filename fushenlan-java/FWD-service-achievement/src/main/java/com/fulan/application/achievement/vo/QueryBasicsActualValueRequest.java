package com.fulan.application.achievement.vo;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * 基础业绩实际值查询 请求参数
 * 
 * @author FWDuser
 *
 */
public class QueryBasicsActualValueRequest {

	@NotEmpty(message = "代理人编号不能为空")
	private String agentCode; // 代理人编号

	@NotEmpty(message = "查询类型不能为空")
	private String groupType; // 查询类型 码值：1 indicate 个人,2 indicate 团队，3 indicate 直辖组

	@NotEmpty(message = "时间参数不能为空")
	private String queryDate; // 时间参数 月份为单位，查询入参的月份的数据

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getQueryDate() {
		return queryDate;
	}

	public void setQueryDate(String queryDate) {
		this.queryDate = queryDate;
	}
}
