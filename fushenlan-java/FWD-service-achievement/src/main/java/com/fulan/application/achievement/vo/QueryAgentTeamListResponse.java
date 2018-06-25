package com.fulan.application.achievement.vo;

import java.util.List;

/**
 *  代理人团队列表查询接口  响应参数
 * @author FWDuser
 *
 */
public class QueryAgentTeamListResponse {
	
	private String statusCode; // 处理状态码 01：交易成功；02：交易失败；03：交易异常

	private String statusMessage; // 处理结果 交易成功、失败或者异常则为具体失败或异常信息
	
	private String agentCode;//  代理人编号
	
	private String  agentName; //代理人名称
	
	private String jobPosition;   //代理人职位
	
	private String company; //  代理人所处公司
	
	private String mobile;  //  代理人联系方式
	
	private String   email;//代理人邮箱地址
	
	private List<AgentTeamInformation> groupList;   // 代理人团队信息
	
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getJobPosition() {
		return jobPosition;
	}

	public void setJobPosition(String jobPosition) {
		this.jobPosition = jobPosition;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<AgentTeamInformation> getGroupList() {
		return groupList;
	}

	public void setGroupList(List<AgentTeamInformation> groupList) {
		this.groupList = groupList;
	}


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




	/***
	 *   代理人团队信息
	 * @author FWDuser
	 *
	 */
	public class AgentTeamInformation{
		
		private String agentCode; //    代理人编号
		
		private String agentName; //    代理人名称

		private String jobPosition;  //    代理人职位
		
		private String isSupervisor; //    直辖主管标识
	

		public String getAgentCode() {
			return agentCode;
		}

		public void setAgentCode(String agentCode) {
			this.agentCode = agentCode;
		}

		public String getIsSupervisor() {
			return isSupervisor;
		}

		public void setIsSupervisor(String isSupervisor) {
			this.isSupervisor = isSupervisor;
		}

		public String getAgentName() {
			return agentName;
		}

		public void setAgentName(String agentName) {
			this.agentName = agentName;
		}

		public String getJobPosition() {
			return jobPosition;
		}

		public void setJobPosition(String jobPosition) {
			this.jobPosition = jobPosition;
		}
	}
	
}
