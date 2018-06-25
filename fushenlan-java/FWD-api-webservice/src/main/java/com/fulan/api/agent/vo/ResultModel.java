package com.fulan.api.agent.vo;

import java.io.Serializable;
import java.util.List;
public class ResultModel implements Serializable {

	 /**
	 * 获取代理人信息接口返回实体
	 */
	private static final long serialVersionUID = 1L;
	private Agent agent;
	private List<Branch> branchs;
	private List<Address> addresses;
	private List<AgentReport> agentReport;
	private List<Contact> contacts;
	private List<ErrorMessage> errorMessage;
	private List<License> licenses;
	private List<Honor> honorList;
	private List<AgentMember> memberList;
	private String message;
	private String status;
	
	public Agent getAgent() {
		return agent;
	}
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public List<Branch> getBranchs() {
		return branchs;
	}
	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<AgentReport> getAgentReport() {
		return agentReport;
	}
	public void setAgentReport(List<AgentReport> agentReport) {
		this.agentReport = agentReport;
	}
	public List<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	public List<ErrorMessage> getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(List<ErrorMessage> errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<License> getLicenses() {
		return licenses;
	}
	public void setLicenses(List<License> licenses) {
		this.licenses = licenses;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public List<Honor> getHonorList() {
		return honorList;
	}

	public void setHonorList(List<Honor> honorList) {
		this.honorList = honorList;
	}

	public List<AgentMember> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<AgentMember> memberList) {
		this.memberList = memberList;
	}
}
