package com.fulan.api.achievement.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "PersonalPerformVo",description = "个人业绩视图类")
public class PersonalPerformVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6376771558315001989L;

	private Integer fyp;

	private Integer targetFyp;

	private Integer balanceFyp;
	
	private Integer fyc;

	private Integer targetFyc;

	private Integer balanceFyc;
	
	private Integer perCase;

	private Integer targetCase;

	private Integer balanceCase;

	private String attendRate;//出席率
	
	private Integer newers;//招募新人数
	
	private Integer isToAcManpower; //是否达成活动人力;
	
	private String k1Rrate;  //k1续保率

	private List<ActivityAmountVo> activityAmountVos;//活动量管理

}
