package com.fulan.application.achievement.vo;

/**
 * 自定义错误实体类
 * @author FWDuser
 *
 */
public class ErrorMessage{
	
	//0表示失败，1表示成功。
	private String  state;
	
	private String  errorMessage;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
