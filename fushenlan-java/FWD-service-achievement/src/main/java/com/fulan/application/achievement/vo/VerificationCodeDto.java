package com.fulan.application.achievement.vo;

import lombok.Data;

@Data
public class VerificationCodeDto {
	private String mobileNo;
	private String agentCode;
	private String verificationCode;
}
