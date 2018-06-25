/**
 * 
 */
package com.fulan.application.oa.vo;

import java.util.List;

import com.fulan.application.oa.domain.FwdOaAddress;
import com.fulan.application.oa.domain.FwdOaApplyForm;
import com.fulan.application.oa.domain.FwdOaBankCard;
import com.fulan.application.oa.domain.FwdOaFormAttachment;
import com.fulan.application.oa.domain.FwdOaMobile;

/**
 * @author 黄记新 Tony
 *
 */
public class OAApplyFormVo extends FwdOaApplyForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FwdOaAddress address;
	private FwdOaBankCard bankCard;
	private FwdOaMobile mobile;
	
	private List<FwdOaFormAttachment> attachments;
	
	public FwdOaAddress getAddress() {
		return address;
	}
	public void setAddress(FwdOaAddress address) {
		this.address = address;
	}
	public FwdOaBankCard getBankCard() {
		return bankCard;
	}
	public void setBankCard(FwdOaBankCard bankCard) {
		this.bankCard = bankCard;
	}
	public FwdOaMobile getMobile() {
		return mobile;
	}
	public void setMobile(FwdOaMobile mobile) {
		this.mobile = mobile;
	}
	public List<FwdOaFormAttachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<FwdOaFormAttachment> attachments) {
		this.attachments = attachments;
	}

}
