package com.fulan.api.achievement.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(tags = "IncomeDetailVo", description = "收入明细VO")
public class IncomeDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7754862884836628486L;

	
	private String incomeName;//奖金，收入名称
	
	private Integer subtotal;//小计
	
	private List<SubIncomeDetailVo> subIncomeDetailVos;

}
