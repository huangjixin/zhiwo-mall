package com.fulan.application.achievement.vo;

import lombok.Data;

/**
	 * 我的业绩
	 * 
	 * @author FWDuser
	 *
	 */
@Data
public class PersonalAchievement {
	private Integer fyc; //   直辖组 FYC业绩

	private Integer activeNo; // 直辖组活动人次

	private Integer effectiveNo; // 直辖组有效人力

	private Integer bredNo; // 直接培育成主管

	private Integer recruitNo; // 招募有效新人

	private Integer k1Rate; // 直辖人K1续保率

}