package com.fulan.api.agent.vo;

import java.io.Serializable;

public class Contact implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		 
	private String contactNum;
	private String contactType;
	
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	
	
}
