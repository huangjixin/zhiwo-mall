package com.fulan.application.achievement.vo;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RankAssessmentIndicator {

	private String categroy;
	
	private Long amount;
	
	private Long target;
	
	private Long balance;
	
	private Float percentage;
	
	public RankAssessmentIndicator(String categroy, Long amount, Long target) {
		super();
		this.categroy = categroy;
		this.amount = amount;
		this.target = target;
	}
	
	public Long getBalance() {
		if(target==null || amount==null) {
			return null;
		}
		
		long val = target-amount;
		return val<0?0:val;
	}
	
	public Float getPercentage() {
		Long balance = getBalance();
		if(balance==null)
			return null;
		
		if(balance==0) 
			return 1f;
		
		BigDecimal b1 = new BigDecimal(amount);
		BigDecimal b2 = new BigDecimal(target);
		BigDecimal r = b1.divide(b2,2,BigDecimal.ROUND_HALF_UP);
		return r.floatValue();
	}
	
}
