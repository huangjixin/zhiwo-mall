package com.fulan.api.agent.vo;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

@ApiModel(value = "AgentMember", description = "团队成员信息类")
public class AgentMember implements Serializable{
	
	/**
	 * 代理人名下的团队成员信息
	 */
	private static final long serialVersionUID = 1L;
	private Long agentCode;
	private String agentName;
	private Integer ranking;
	private Integer fyp;
	private Integer fyc;
	private Integer mcase;

	public Long getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(Long agentCode) {
		this.agentCode = agentCode;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Integer getFyp() {
		return fyp;
	}

	public void setFyp(Integer fyp) {
		this.fyp = fyp;
	}

	public Integer getFyc() {
		return fyc;
	}

	public void setFyc(Integer fyc) {
		this.fyc = fyc;
	}

	public Integer getMcase() {
		return mcase;
	}

	public void setMcase(Integer mcase) {
		this.mcase = mcase;
	}
}
