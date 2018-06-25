package com.fulan.application.achievement.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentHistoryIncomeDetailedSubItem {

	private String category; // 收入子项

	private String amount; // 收入金额
}
