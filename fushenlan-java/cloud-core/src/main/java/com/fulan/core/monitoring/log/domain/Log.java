package com.fulan.core.monitoring.log.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by aviyy on 17/10/19.
 */
@SuppressWarnings("serial")
public class Log implements Serializable,Cloneable{
	/**
		App标志、
		处理 controller、
		处理 method、
		调用 URL、
		调用URL-code
		处理服务器IP、
		信息报文 msgText（最大存储4000个字符）、
		创建时间 createDate、
		消息类型 MsgType（请求消息Request，响应消息Response）
		消息序号（1、2、3、4、5）
		用户信息（usercode，unitcode、P13code）、
		案件信息（caseno、、）
		交易ID （）
	 */
	private Long id;
	private String appCode;
//	private String appVersion;
	private String controller;
	private String method;
	private String url;
	private String urlCode;
	private String ip;
	private String remoteIp;
	private String msgText;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:MM:ss")
	private Date createTime;
	private String msgType;
	private Integer msgOrder;
	
	private String userCode;
	private String userName;
	private String userType;
	private String userUnitCode;
	
	private String caseNo;
	private String bussinessId;
	public void setBaseLog(String appCode, String controller, String method, String url, String urlCode, String ip,
			String remoteIp, String msgText, Date createTime, String msgType, Integer msgOrder, String userCode,
			String userName, String userType, String userUnitCode, String caseNo, String bussinessId) {
		this.appCode = appCode;
		this.controller = controller;
		this.method = method;
		this.url = url;
		this.urlCode = urlCode;
		this.ip = ip;
		this.remoteIp = remoteIp;
		this.msgText = msgText;
		this.createTime = createTime;
		this.msgType = msgType;
		this.msgOrder = msgOrder;
		this.userCode = userCode;
		this.userName = userName;
		this.userType = userType;
		this.userUnitCode = userUnitCode;
		this.caseNo = caseNo;
		this.bussinessId = bussinessId;
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
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUrlCode() {
		return urlCode;
	}
	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getRemoteIp() {
		return remoteIp;
	}
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	public String getMsgText() {
		return msgText;
	}
	public void setMsgText(String msgText) {
		this.msgText = msgText;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public Integer getMsgOrder() {
		return msgOrder;
	}
	public void setMsgOrder(Integer msgOrder) {
		this.msgOrder = msgOrder;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserUnitCode() {
		return userUnitCode;
	}
	public void setUserUnitCode(String userUnitCode) {
		this.userUnitCode = userUnitCode;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getBussinessId() {
		return bussinessId;
	}
	public void setBussinessId(String bussinessId) {
		this.bussinessId = bussinessId;
	}
	@Override
	public String toString() {
		return "Log [id=" + id + ", appCode=" + appCode + ", controller=" + controller + ", method=" + method + ", url="
				+ url + ", urlCode=" + urlCode + ", ip=" + ip + ", remoteIp=" + remoteIp + ", msgText=" + msgText
				+ ", createTime=" + createTime + ", msgType=" + msgType + ", msgOrder=" + msgOrder + ", userCode="
				+ userCode + ", userName=" + userName + ", userType=" + userType + ", userUnitCode=" + userUnitCode
				+ ", caseNo=" + caseNo + ", bussinessId=" + bussinessId + "]";
	}
	@Override
	public Log clone() throws CloneNotSupportedException {
		return (Log)super.clone();
	}
}
