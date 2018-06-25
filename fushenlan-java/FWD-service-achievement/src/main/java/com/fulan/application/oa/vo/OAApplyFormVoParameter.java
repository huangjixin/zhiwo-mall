package com.fulan.application.oa.vo;

import java.io.Serializable;

import com.fulan.application.oa.domain.FwdOaApplyForm;

/**
 * OAApplyFormVo参数实体类
 * @author FWDuser
 *
 */
public class OAApplyFormVoParameter implements Serializable {
      
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FwdOaApplyForm fwdOaApplyForm;
	
	private String[] types;
	
	private Integer[] ids;
	
	private Integer start;
	
	private Integer size;

	public FwdOaApplyForm getFwdOaApplyForm() {
		return fwdOaApplyForm;
	}

	public void setFwdOaApplyForm(FwdOaApplyForm fwdOaApplyForm) {
		this.fwdOaApplyForm = fwdOaApplyForm;
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}
}
