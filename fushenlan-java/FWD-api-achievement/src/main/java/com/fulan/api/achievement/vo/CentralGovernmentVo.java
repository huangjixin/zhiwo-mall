package com.fulan.api.achievement.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "CentralGovernmentVo", description = "直辖组视图类")
public class CentralGovernmentVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 101002065792991904L;

	private Integer fyp;

	private Integer targetFyp;

	private Integer balanceFyp;

	private Integer fyc;

	private Integer targetFyc;

	private Integer balanceFyc;

	private Integer goverCase;

	private Integer targetCase;

	private Integer balanceCase;
	
	private Integer activeManpower; //活动人力
	
	private Integer activeNewManpower; //直辖组新人活动人力

	private Integer effectManpower;//有效人力
	
	private Integer directRecruitmentManpower;//直接招募有效新人
	
	private Integer directorNum;//直接育成主管数
	
	private String k1Rrate;//k1续保率
	
	private String perAttendRate; //个人出席率
	
	private Integer recruitNewers; // 招募新人数
	
	private Integer zcrl;//在册人力
	
	private List<TeamPerformanceVo> teams;


}
