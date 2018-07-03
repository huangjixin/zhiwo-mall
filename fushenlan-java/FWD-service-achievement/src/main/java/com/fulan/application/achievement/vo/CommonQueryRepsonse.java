package com.fulan.application.achievement.vo;

import lombok.Data;
@Data
public class CommonQueryRepsonse<T> {
	private T response;
	private Status status;
	
	
	public static class Status{
		private String statusCode;
		private String statusMessage;
		
		Status(){};
		public String getStatusCode() {
			return statusCode;
		}
		public void setStatusCode(String statusCode) {
			this.statusCode = statusCode;
		}
		public String getStatusMessage() {
			return statusMessage;
		}
		public void setStatusMessage(String statusMessage) {
			this.statusMessage = statusMessage;
		}
		
		
	}
}
