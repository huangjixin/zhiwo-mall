package com.zwo.xiyangyang.modules.guess.dto;

import java.util.Date;

/**
 * 竞猜结果模型。
 * @author 黄记新
 *
 */
public class GuessData implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
	private String expect, opencode;
	private Date opentime;
	private String opentimestamp;

	public String getExpect() {
		return expect;
	}

	public void setExpect(String expect) {
		this.expect = expect;
	}

	public String getOpencode() {
		return opencode;
	}

	public void setOpencode(String opencode) {
		this.opencode = opencode;
	}

	public Date getOpentime() {
		return opentime;
	}

	public void setOpentime(Date opentime) {
		this.opentime = opentime;
	}

	public String getOpentimestamp() {
		return opentimestamp;
	}

	public void setOpentimestamp(String opentimestamp) {
		this.opentimestamp = opentimestamp;
	}
}
