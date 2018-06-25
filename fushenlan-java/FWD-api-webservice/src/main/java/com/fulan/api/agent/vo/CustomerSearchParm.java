/**
 * Project Name:FWD-api-webservice
 * File Name:CustomerSearchParm.java
 * Package Name:com.fulan.api.agent.vo
 * Date:2018年4月9日上午9:39:47
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.api.agent.vo;

import java.io.Serializable;

/**
 * ClassName:CustomerSearchParm Reason: TODO ADD REASON Date: 2018年4月9日
 * 上午9:39:47
 * 
 * @author chen.zhuang
 * @version
 * @since JDK 1.8
 */
public class CustomerSearchParm  implements Serializable{

	
	private static final long serialVersionUID = 1L;
	private String dob;
	private String gender;
	private String idNumber;
	private String idType;
	private String mobileNumber;
	private String name;
	private String status;
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	
	
	
	
}
