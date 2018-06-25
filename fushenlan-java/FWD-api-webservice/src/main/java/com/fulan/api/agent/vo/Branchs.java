package com.fulan.api.agent.vo;

import java.io.Serializable;

public class Branchs implements Serializable {

	 /**
	 * 获取代理人机构信息接口返回实体
	 */
	private static final long serialVersionUID = 1L;
	 private String branchID;
     private String branchName;
     private String parentDesignation;
     private String parentID;
     private String parentName;
	public String getBranchID() {
		return branchID;
	}
	public void setBranchID(String branchID) {
		this.branchID = branchID;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getParentDesignation() {
		return parentDesignation;
	}
	public void setParentDesignation(String parentDesignation) {
		this.parentDesignation = parentDesignation;
	}
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
    	
}
