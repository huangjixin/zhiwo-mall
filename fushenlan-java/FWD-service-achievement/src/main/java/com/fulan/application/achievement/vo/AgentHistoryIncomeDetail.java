package com.fulan.application.achievement.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentHistoryIncomeDetail {
	private String category; // 收入类别大类

	private String amount; // 收入类别大类金额

	private List<AgentHistoryIncomeDetailedSubItem> subList; // 子项列表
}
