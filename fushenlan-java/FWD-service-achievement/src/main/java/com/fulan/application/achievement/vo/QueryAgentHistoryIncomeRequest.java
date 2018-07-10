package com.fulan.application.achievement.vo;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

/**
 * 代理人历史收入查询 请求参数
 * 
 * @author FWDuser
 *
 */
@Data
public class QueryAgentHistoryIncomeRequest {
	@NotEmpty(message = "代理人编号不能为空")
	private String agentCode; // 代理人编号

	private String queryDate; // 时间参数 月份为单位，查询入参的月份的数据
	
	private String endDate;
	
	private String startDate;

}
