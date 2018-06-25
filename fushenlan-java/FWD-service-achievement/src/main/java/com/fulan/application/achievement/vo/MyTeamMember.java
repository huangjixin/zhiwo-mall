package com.fulan.application.achievement.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 我的团队成员
 * 
 * @author FWDuser
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyTeamMember {
	private String agentCode; // 代理人代码

	private String agentName; // 代理人姓名

	private String agentGrade; // 代理人职级

	private Integer ranking; // 团队业绩排名

	private Integer fyp; // 组员FYP

	private Integer fyc;// 组员FYC

	private Integer caseNo; // 组员CASE 

}
