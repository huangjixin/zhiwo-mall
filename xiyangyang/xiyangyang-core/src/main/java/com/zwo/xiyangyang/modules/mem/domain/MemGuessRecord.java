package com.zwo.xiyangyang.modules.mem.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 竞猜记录模型。
 * @author 黄记新
 *
 */
public class MemGuessRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6516824667887034212L;
	
	private String name;
	private String optionName;
	private double betRate;
	private int betValue;
	private String result;
	private Date createDate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public double getBetRate() {
		return betRate;
	}
	public void setBetRate(double betRate) {
		this.betRate = betRate;
	}
	public int getBetValue() {
		return betValue;
	}
	public void setBetValue(int betValue) {
		this.betValue = betValue;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
