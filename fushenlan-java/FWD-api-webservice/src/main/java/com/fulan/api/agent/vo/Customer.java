package com.fulan.api.agent.vo;

import java.io.Serializable;

public class Customer implements Serializable {

	 /**
	 * 获取代理人机构信息接口返回实体
	 */
	 private static final long serialVersionUID = 1L;
	 private String birthDate;
     private String ccrId;
     private String fullName;
     private String gender;
     private String idNum;
     private String laId;
     private String mobileNum;
     private String roleCode;
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getCcrId() {
		return ccrId;
	}
	public void setCcrId(String ccrId) {
		this.ccrId = ccrId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getLaId() {
		return laId;
	}
	public void setLaId(String laId) {
		this.laId = laId;
	}
	public String getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

     
}
