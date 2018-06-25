/**
 * Project Name:FWD-api-webservice
 * File Name:CircInfo.java
 * Package Name:com.fulan.api.agent.vo
 * Date:2018年4月9日上午11:19:37
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.api.agent.vo;

/**
 * ClassName:CircInfo Reason: TODO ADD REASON Date: 2018年4月9日 上午11:19:37
 * 
 * @author chen.zhuang
 * @version
 * @since JDK 1.8
 */
public class CircInfo {

	private String applicationDate;
	private String remark;
	private String requesteByClient;
	private String submitedBy;

	public String getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRequesteByClient() {
		return requesteByClient;
	}

	public void setRequesteByClient(String requesteByClient) {
		this.requesteByClient = requesteByClient;
	}

	public String getSubmitedBy() {
		return submitedBy;
	}

	public void setSubmitedBy(String submitedBy) {
		this.submitedBy = submitedBy;
	}

}
