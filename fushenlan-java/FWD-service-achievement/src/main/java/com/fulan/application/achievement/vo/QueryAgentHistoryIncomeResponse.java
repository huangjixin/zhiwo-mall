package com.fulan.application.achievement.vo;

import java.util.List;

import lombok.Data;

/**
 * * 代理人历史收入查询 响应参数
 * 
 * @author FWDuser
 *
 */
@Data
public class QueryAgentHistoryIncomeResponse {

	private String preTax; // 税前小计金额

	private String afterTax; // 税后小计金额

	private String taxAmount; // 税金

	private List<AgentHistoryIncomeDetail> detailList; // 明细信息
}
