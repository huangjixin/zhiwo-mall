package com.fulan.api.agent.vo;

import java.io.Serializable;

public class License implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		 
	private String agentLicenseStatus;
	private String licenseFromDate;
	private String licenseNum;
	private String licenseToDate;
	private String licenseType;
	public String getAgentLicenseStatus() {
		return agentLicenseStatus;
	}
	public void setAgentLicenseStatus(String agentLicenseStatus) {
		this.agentLicenseStatus = agentLicenseStatus;
	}
	public String getLicenseFromDate() {
		return licenseFromDate;
	}
	public void setLicenseFromDate(String licenseFromDate) {
		this.licenseFromDate = licenseFromDate;
	}
	public String getLicenseNum() {
		return licenseNum;
	}
	public void setLicenseNum(String licenseNum) {
		this.licenseNum = licenseNum;
	}
	public String getLicenseToDate() {
		return licenseToDate;
	}
	public void setLicenseToDate(String licenseToDate) {
		this.licenseToDate = licenseToDate;
	}
	public String getLicenseType() {
		return licenseType;
	}
	public void setLicenseType(String licenseType) {
		this.licenseType = licenseType;
	}

	
}
