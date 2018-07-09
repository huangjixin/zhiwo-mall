package com.fulan.application.oa.vo;

import java.util.List;

import lombok.Data;

@Data
public class FwdCqRespAgentGroupInfoDto {
	private String agentCode;
	private String agencyName;
	private String jobPosition;
	private String company;
	private String mobile;
	private String email;
	
	private String bankAccount;
	private String bankCode; 
	private String bankName; 

	private List<FwdAddressDto> addressList;
	private List<FwdAgentDto> groupList;
}
