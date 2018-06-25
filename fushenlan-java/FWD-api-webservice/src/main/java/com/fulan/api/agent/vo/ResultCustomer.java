package com.fulan.api.agent.vo;

import java.io.Serializable;
import java.util.List;
public class ResultCustomer implements Serializable {

	 /**
	 * 获取机构信息接口返回实体
	 */
	private static final long serialVersionUID = 1L;
	private List<Customer> customerList;
	private List<ErrorMessage> errorMessage;
	private String message;
	private String status;
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	public List<ErrorMessage> getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(List<ErrorMessage> errorMessage) {
		this.errorMessage = errorMessage;
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
	
}
