package com.zwo.modules.wechat.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component
public class WeChatVo {
	
	@Value("${appid}")
	private String appid = "wx253edb88a84b178b";
	@Value("${appsecret}")
	private String appsecret="46f3bdc705e60dc9da2a5e97e40014d5";

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
}
