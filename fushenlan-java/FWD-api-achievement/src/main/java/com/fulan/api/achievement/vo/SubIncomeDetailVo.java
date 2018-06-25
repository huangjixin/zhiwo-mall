package com.fulan.api.achievement.vo;

import java.io.Serializable;

import io.swagger.annotations.Api;
import lombok.Data;

@Data
@Api(tags = "SubIncomeDetailVo", description = "收入明细VO")
public class SubIncomeDetailVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8256656248830437716L;

	private String incomeName;//佣金名称
	
	private Integer  commision;//佣金

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public Integer getCommision() {
		return commision;
	}

	public void setCommision(Integer commision) {
		this.commision = commision;
	}
	
	
	
}
