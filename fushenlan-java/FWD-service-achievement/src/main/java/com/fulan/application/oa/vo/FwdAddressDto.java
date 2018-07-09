package com.fulan.application.oa.vo;

import lombok.Data;

@Data
public class FwdAddressDto {
	private String province;
	private String city;
	private String district;
	private String addressValue;
	private String addressType;
	private String isUsed;
	
	private String xPositon;
	private String yPosition;
}
