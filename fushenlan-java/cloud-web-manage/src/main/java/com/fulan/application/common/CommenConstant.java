package com.fulan.application.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class CommenConstant {
	
	 @Value("${ftpuploadusername}")
	 public  String ftpuploadusername;
	 
	 @Value("${ftpuploadpassword}")
	 public  String ftpuploadpassword;
	 
	 @Value("${ftpuploadhost}")
	 public  String ftpuploadhost;
	 
	 @Value("${ftpuploadport}")
	 public  Integer ftpuploadport;

	public String getFtpuploadusername() {
		return ftpuploadusername;
	}

	public String getFtpuploadpassword() {
		return ftpuploadpassword;
	}

	public String getFtpuploadhost() {
		return ftpuploadhost;
	}

	public Integer getFtpuploadport() {
		return ftpuploadport;
	}
	 
	 
	
}
