package com.fulan.application.oa.vo;

import lombok.Data;

@Data
public class OaAgentDto {
	private String agentCode;
	private String agentName;
	private String subIsAtWork;
	private String isSupervisor;
	private String jobPosition;
}
