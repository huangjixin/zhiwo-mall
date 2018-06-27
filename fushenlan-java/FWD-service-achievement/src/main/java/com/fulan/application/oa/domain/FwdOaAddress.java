package com.fulan.application.oa.domain;

import java.io.Serializable;
import java.util.Date;

public class FwdOaAddress implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.DESCRIPTION
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.AGENT_CODE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String agentCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.CREATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Date createDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.UPDATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Date updateDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.IS_DEFAULT
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Boolean isDefault;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.COUNTRY
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String country;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.PROVINCE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String province;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.CITY
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.AREA
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String area;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.STREET
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String street;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.ADDRESS_DETAIL
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String addressDetail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.APPLY_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String applyNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.AGENT_NAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private String agentName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fwd_oa_address.OA_APPLY_ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private Integer oaApplyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fwd_oa_address
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.ID
     *
     * @return the value of fwd_oa_address.ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.ID
     *
     * @param id the value for fwd_oa_address.ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.DESCRIPTION
     *
     * @return the value of fwd_oa_address.DESCRIPTION
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.DESCRIPTION
     *
     * @param description the value for fwd_oa_address.DESCRIPTION
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.AGENT_CODE
     *
     * @return the value of fwd_oa_address.AGENT_CODE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getAgentCode() {
        return agentCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.AGENT_CODE
     *
     * @param agentCode the value for fwd_oa_address.AGENT_CODE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode == null ? null : agentCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.CREATE_DATETIME
     *
     * @return the value of fwd_oa_address.CREATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Date getCreateDatetime() {
        return createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.CREATE_DATETIME
     *
     * @param createDatetime the value for fwd_oa_address.CREATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.UPDATE_DATETIME
     *
     * @return the value of fwd_oa_address.UPDATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.UPDATE_DATETIME
     *
     * @param updateDatetime the value for fwd_oa_address.UPDATE_DATETIME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.IS_DEFAULT
     *
     * @return the value of fwd_oa_address.IS_DEFAULT
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.IS_DEFAULT
     *
     * @param isDefault the value for fwd_oa_address.IS_DEFAULT
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.COUNTRY
     *
     * @return the value of fwd_oa_address.COUNTRY
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getCountry() {
        return country;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.COUNTRY
     *
     * @param country the value for fwd_oa_address.COUNTRY
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.PROVINCE
     *
     * @return the value of fwd_oa_address.PROVINCE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getProvince() {
        return province;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.PROVINCE
     *
     * @param province the value for fwd_oa_address.PROVINCE
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.CITY
     *
     * @return the value of fwd_oa_address.CITY
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.CITY
     *
     * @param city the value for fwd_oa_address.CITY
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.AREA
     *
     * @return the value of fwd_oa_address.AREA
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getArea() {
        return area;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.AREA
     *
     * @param area the value for fwd_oa_address.AREA
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.STREET
     *
     * @return the value of fwd_oa_address.STREET
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getStreet() {
        return street;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.STREET
     *
     * @param street the value for fwd_oa_address.STREET
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.ADDRESS_DETAIL
     *
     * @return the value of fwd_oa_address.ADDRESS_DETAIL
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getAddressDetail() {
        return addressDetail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.ADDRESS_DETAIL
     *
     * @param addressDetail the value for fwd_oa_address.ADDRESS_DETAIL
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail == null ? null : addressDetail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.APPLY_NO
     *
     * @return the value of fwd_oa_address.APPLY_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getApplyNo() {
        return applyNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.APPLY_NO
     *
     * @param applyNo the value for fwd_oa_address.APPLY_NO
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo == null ? null : applyNo.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.AGENT_NAME
     *
     * @return the value of fwd_oa_address.AGENT_NAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public String getAgentName() {
        return agentName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.AGENT_NAME
     *
     * @param agentName the value for fwd_oa_address.AGENT_NAME
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fwd_oa_address.OA_APPLY_ID
     *
     * @return the value of fwd_oa_address.OA_APPLY_ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public Integer getOaApplyId() {
        return oaApplyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fwd_oa_address.OA_APPLY_ID
     *
     * @param oaApplyId the value for fwd_oa_address.OA_APPLY_ID
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    public void setOaApplyId(Integer oaApplyId) {
        this.oaApplyId = oaApplyId;
    }
}