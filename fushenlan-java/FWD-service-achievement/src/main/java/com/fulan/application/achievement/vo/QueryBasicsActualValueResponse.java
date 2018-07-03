package com.fulan.application.achievement.vo;
/**
 * 基础业绩实际值查询 响应参数
 * @author FWDuser
 *
 */

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class QueryBasicsActualValueResponse {

	private String agentGrade; // 代理人职级 01,"处理成功"；02,"处理失败"；03,"处理异常"

	private String fyc;// 当前代理人FYC

	private String fyp; // 当前代理人FYP

	private Integer caseNo;// 当前代理人CASE

	private PersonalAchievement personalAchievement; // 我的业绩

	private List<MyTeamMember> groupList = new ArrayList<MyTeamMember>();// 我的团队成员
	
}
