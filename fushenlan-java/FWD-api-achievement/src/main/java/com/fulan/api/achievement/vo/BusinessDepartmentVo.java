package com.fulan.api.achievement.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "BusinessDepartmentVo", description = "营业部视图类")
public class BusinessDepartmentVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6958218124517349141L;

	private String fyp;

	private String fyc;

	private String busi_case;
	
	private Integer activeManpower; //活动人力 //不含主管本人
	
	private Integer busiCharge; //营业部主管数

	private Integer effectManpower;//有效人力
	
//	private Integer directRecruitmentManpower;//直接招募有效新人
	
	private Integer directorNum;//直接育成主管数
	
	private String k1_rate;//k1续保率
	
	private Integer zcrl;//在册人力

	public String getFyp() {
		return fyp;
	}

	public void setFyp(String fyp) {
		this.fyp = fyp;
	}

	public String getFyc() {
		return fyc;
	}

	public void setFyc(String fyc) {
		this.fyc = fyc;
	}

	public String getBusi_case() {
		return busi_case;
	}

	public void setBusi_case(String busi_case) {
		this.busi_case = busi_case;
	}

	public Integer getActiveManpower() {
		return activeManpower;
	}

	public void setActiveManpower(Integer activeManpower) {
		this.activeManpower = activeManpower;
	}

	public Integer getBusiCharge() {
		return busiCharge;
	}

	public void setBusiCharge(Integer busiCharge) {
		this.busiCharge = busiCharge;
	}

	public Integer getEffectManpower() {
		return effectManpower;
	}

	public void setEffectManpower(Integer effectManpower) {
		this.effectManpower = effectManpower;
	}

	public Integer getDirectorNum() {
		return directorNum;
	}

	public void setDirectorNum(Integer directorNum) {
		this.directorNum = directorNum;
	}

	public String getK1_rate() {
		return k1_rate;
	}

	public void setK1_rate(String k1_rate) {
		this.k1_rate = k1_rate;
	}

	public Integer getZcrl() {
		return zcrl;
	}

	public void setZcrl(Integer zcrl) {
		this.zcrl = zcrl;
	}
	
	
	
}
