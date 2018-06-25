package com.fulan.core.monitoring.log.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by aviyy on 17/10/19.
 */
@SuppressWarnings("serial")
public class Tlog implements Serializable,Cloneable{
/**
	App标志
	交易ID、
	调用 URL、
	请求报文 requestText（最大存储4000个字符）
	请求时间
	响应报文 responseText、
	响应时间
	消息类型 Type
	消息序号（1、2、3、4、5）
 */
	private Long id;
	private String appCode;
	private String bussinessId;
	private String url;
	private String requestText;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date requestTime;
	private String responseText;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date responseTime;
	private String type;
	private Integer msgOrder;
	public void setBaseTlog(String appCode,String bussinessId, String url, String requestText, Date requestTime,
			String responseText, Date responseTime, String type, Integer msgOrder) {
		this.appCode = appCode;
		this.bussinessId = bussinessId;
		this.url = url;
		this.requestText = requestText;
		this.requestTime = requestTime;
		this.responseText = responseText;
		this.responseTime = responseTime;
		this.type = type;
		this.msgOrder = msgOrder;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getBussinessId() {
		return bussinessId;
	}
	public void setBussinessId(String bussinessId) {
		this.bussinessId = bussinessId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getRequestText() {
		return requestText;
	}
	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}
	public Date getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	public String getResponseText() {
		return responseText;
	}
	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getMsgOrder() {
		return msgOrder;
	}
	public void setMsgOrder(Integer msgOrder) {
		this.msgOrder = msgOrder;
	}
	@Override
	public String toString() {
		return "Tlog [id=" + id + ", appCode=" + appCode + ", bussinessId=" + bussinessId + ", url=" + url
				+ ", requestText=" + requestText + ", requestTime=" + requestTime + ", responseText=" + responseText
				+ ", responseTime=" + responseTime + ", type=" + type + ", msgOrder=" + msgOrder + "]";
	}
	@Override
	public Tlog clone() throws CloneNotSupportedException {
		return (Tlog)super.clone();
	}
}
