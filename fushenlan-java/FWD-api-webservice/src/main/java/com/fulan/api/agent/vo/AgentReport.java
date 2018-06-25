package com.fulan.api.agent.vo;

import java.io.Serializable;

public class AgentReport implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String leaderDesignation;
	private String leaderId;
	private String leaderName;
	private String leaderTier;
	private String leaderType;
	public String getLeaderDesignation() {
		return leaderDesignation;
	}
	public void setLeaderDesignation(String leaderDesignation) {
		this.leaderDesignation = leaderDesignation;
	}
	public String getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}
	public String getLeaderName() {
		return leaderName;
	}
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
	public String getLeaderTier() {
		return leaderTier;
	}
	public void setLeaderTier(String leaderTier) {
		this.leaderTier = leaderTier;
	}
	public String getLeaderType() {
		return leaderType;
	}
	public void setLeaderType(String leaderType) {
		this.leaderType = leaderType;
	}

}
