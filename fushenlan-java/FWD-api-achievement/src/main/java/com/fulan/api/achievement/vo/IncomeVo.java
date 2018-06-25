package com.fulan.api.achievement.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(tags = "IncomeVo", description = "收入VO")
public class IncomeVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 529201705311170615L;
	
	private String achieveTitle;//业绩标题 
	
	private Integer income;//收入
	
	private Integer targetPerform;//目标业绩
	
	private Integer balanceNum;//差额
	
	private Integer annualIncome;//年度总收入
	
	private List<IncomeDetailVo> incomeDetailVos;

}
