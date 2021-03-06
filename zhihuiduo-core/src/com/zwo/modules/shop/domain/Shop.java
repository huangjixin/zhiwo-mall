package com.zwo.modules.shop.domain;

import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class Shop implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.CREATOR
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.ORG_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String orgId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.UPDATER
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String updater;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.CREATE_DATE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.UPDATE_DATE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.DISABLE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private Boolean disable;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.START_TIME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.END_TIME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private Date endTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.SORT
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private Integer sort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.IS_DEFAULT
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private Boolean isDefault;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.EN_NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String enName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.DESCRIPTION
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.ICON
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String icon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.KEYWORDS
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String keywords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.THUMBNAIL
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String thumbnail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.CODE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.CATEGORY_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String categoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.USER_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.CONTACT_TELEPHONE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String contactTelephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.EMAIL
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String email;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column shop.ADMIN_NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private String adminName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table shop
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.ID
     *
     * @return the value of shop.ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.ID
     *
     * @param id the value for shop.ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.CREATOR
     *
     * @return the value of shop.CREATOR
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.CREATOR
     *
     * @param creator the value for shop.CREATOR
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.ORG_ID
     *
     * @return the value of shop.ORG_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.ORG_ID
     *
     * @param orgId the value for shop.ORG_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.UPDATER
     *
     * @return the value of shop.UPDATER
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.UPDATER
     *
     * @param updater the value for shop.UPDATER
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.CREATE_DATE
     *
     * @return the value of shop.CREATE_DATE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.CREATE_DATE
     *
     * @param createDate the value for shop.CREATE_DATE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.UPDATE_DATE
     *
     * @return the value of shop.UPDATE_DATE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.UPDATE_DATE
     *
     * @param updateDate the value for shop.UPDATE_DATE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.DISABLE
     *
     * @return the value of shop.DISABLE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public Boolean getDisable() {
        return disable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.DISABLE
     *
     * @param disable the value for shop.DISABLE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.START_TIME
     *
     * @return the value of shop.START_TIME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.START_TIME
     *
     * @param startTime the value for shop.START_TIME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.END_TIME
     *
     * @return the value of shop.END_TIME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.END_TIME
     *
     * @param endTime the value for shop.END_TIME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.SORT
     *
     * @return the value of shop.SORT
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.SORT
     *
     * @param sort the value for shop.SORT
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.IS_DEFAULT
     *
     * @return the value of shop.IS_DEFAULT
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.IS_DEFAULT
     *
     * @param isDefault the value for shop.IS_DEFAULT
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.EN_NAME
     *
     * @return the value of shop.EN_NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getEnName() {
        return enName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.EN_NAME
     *
     * @param enName the value for shop.EN_NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.NAME
     *
     * @return the value of shop.NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.NAME
     *
     * @param name the value for shop.NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.DESCRIPTION
     *
     * @return the value of shop.DESCRIPTION
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.DESCRIPTION
     *
     * @param description the value for shop.DESCRIPTION
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.ICON
     *
     * @return the value of shop.ICON
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getIcon() {
        return icon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.ICON
     *
     * @param icon the value for shop.ICON
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.KEYWORDS
     *
     * @return the value of shop.KEYWORDS
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.KEYWORDS
     *
     * @param keywords the value for shop.KEYWORDS
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.THUMBNAIL
     *
     * @return the value of shop.THUMBNAIL
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.THUMBNAIL
     *
     * @param thumbnail the value for shop.THUMBNAIL
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.CODE
     *
     * @return the value of shop.CODE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.CODE
     *
     * @param code the value for shop.CODE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.CATEGORY_ID
     *
     * @return the value of shop.CATEGORY_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.CATEGORY_ID
     *
     * @param categoryId the value for shop.CATEGORY_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.USER_ID
     *
     * @return the value of shop.USER_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.USER_ID
     *
     * @param userId the value for shop.USER_ID
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.CONTACT_TELEPHONE
     *
     * @return the value of shop.CONTACT_TELEPHONE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getContactTelephone() {
        return contactTelephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.CONTACT_TELEPHONE
     *
     * @param contactTelephone the value for shop.CONTACT_TELEPHONE
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone == null ? null : contactTelephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.EMAIL
     *
     * @return the value of shop.EMAIL
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.EMAIL
     *
     * @param email the value for shop.EMAIL
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column shop.ADMIN_NAME
     *
     * @return the value of shop.ADMIN_NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column shop.ADMIN_NAME
     *
     * @param adminName the value for shop.ADMIN_NAME
     *
     * @mbggenerated Mon Aug 07 08:17:46 CST 2017
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }
    
    public Integer getNumberCount() {
		return numberCount;
	}

	public void setNumberCount(Integer numberCount) {
		this.numberCount = numberCount;
	}

	public Integer getNumberGroupPurcse() {
		return numberGroupPurcse;
	}

	public void setNumberGroupPurcse(Integer numberGroupPurcse) {
		this.numberGroupPurcse = numberGroupPurcse;
	}

	/**
     * 商品总数。
     */
    private Integer numberCount;
    
    /**
     * 已买出去的商品总数。
     */
    private Integer numberGroupPurcse;
}