package com.fulan.application.oa.domain;

import java.io.Serializable;
import java.util.Date;

public class FwdOaBankCard implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.DESCRIPTION
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.AGENT_CODE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String agentCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.CREATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Date createDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.UPDATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Date updateDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.IS_DEFAULT
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Boolean isDefault;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.USERNAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String username;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.ADDRESS
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.CARD_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String cardNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.APPLY_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String applyNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.AGENT_NAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String agentName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_bank_card.OA_APPLY_ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Integer oaApplyId;
    
    private String cardType;
    

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.ID
     *
     * @return the value of fwd_oa_bank_card.ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.ID
     *
     * @param id the value for fwd_oa_bank_card.ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.DESCRIPTION
     *
     * @return the value of fwd_oa_bank_card.DESCRIPTION
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.DESCRIPTION
     *
     * @param description the value for fwd_oa_bank_card.DESCRIPTION
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.AGENT_CODE
     *
     * @return the value of fwd_oa_bank_card.AGENT_CODE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.AGENT_CODE
     *
     * @param agentCode the value for fwd_oa_bank_card.AGENT_CODE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode == null ? null : agentCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.CREATE_DATETIME
     *
     * @return the value of fwd_oa_bank_card.CREATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.CREATE_DATETIME
     *
     * @param createDatetime the value for fwd_oa_bank_card.CREATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.UPDATE_DATETIME
     *
     * @return the value of fwd_oa_bank_card.UPDATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.UPDATE_DATETIME
     *
     * @param updateDatetime the value for fwd_oa_bank_card.UPDATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.IS_DEFAULT
     *
     * @return the value of fwd_oa_bank_card.IS_DEFAULT
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.IS_DEFAULT
     *
     * @param isDefault the value for fwd_oa_bank_card.IS_DEFAULT
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.USERNAME
     *
     * @return the value of fwd_oa_bank_card.USERNAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.USERNAME
     *
     * @param username the value for fwd_oa_bank_card.USERNAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.ADDRESS
     *
     * @return the value of fwd_oa_bank_card.ADDRESS
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.ADDRESS
     *
     * @param address the value for fwd_oa_bank_card.ADDRESS
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.CARD_NO
     *
     * @return the value of fwd_oa_bank_card.CARD_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.CARD_NO
     *
     * @param cardNo the value for fwd_oa_bank_card.CARD_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.APPLY_NO
     *
     * @return the value of fwd_oa_bank_card.APPLY_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getApplyNo() {
        return applyNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.APPLY_NO
     *
     * @param applyNo the value for fwd_oa_bank_card.APPLY_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.AGENT_NAME
     *
     * @return the value of fwd_oa_bank_card.AGENT_NAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.AGENT_NAME
     *
     * @param agentName the value for fwd_oa_bank_card.AGENT_NAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_bank_card.OA_APPLY_ID
     *
     * @return the value of fwd_oa_bank_card.OA_APPLY_ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Integer getOaApplyId() {
        return oaApplyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_bank_card.OA_APPLY_ID
     *
     * @param oaApplyId the value for fwd_oa_bank_card.OA_APPLY_ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setOaApplyId(Integer oaApplyId) {
        this.oaApplyId = oaApplyId;
    }
    @Override
    public boolean equals(Object obj) {
        if(cardNo.equals(((FwdOaBankCard)obj).getCardNo()))
            return true;//这里以cardNo为判定标准。
        else {
            return false;
        }
    }

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
}