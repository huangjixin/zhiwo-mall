package com.fulan.application.achievement.vo;

import lombok.Data;

@Data
public class BankCardDto {
	private Integer cardId;
	private String mobileNo;
	private String agentCode;
	private String verificationCode;
}
