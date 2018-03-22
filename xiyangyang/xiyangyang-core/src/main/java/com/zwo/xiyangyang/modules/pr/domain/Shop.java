package com.zwo.xiyangyang.modules.pr.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shop")
public class Shop implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 创建人
     */
    @Column(name = "CREATOR")
    private String creator;

    /**
     * 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    @Column(name = "ORG_ID")
    private String orgId;

    /**
     * 更新人
     */
    @Column(name = "UPDATER")
    private String updater;

    /**
     * 创建日期
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 更新日期
     */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    /**
     * 是否禁用
     */
    @Column(name = "ENABLED")
    private Boolean enabled;

    /**
     * 开始时间，用于查询绑定
     */
    @Column(name = "START_TIME")
    private Date startTime;

    /**
     * 结束时间，用于查询绑定
     */
    @Column(name = "END_TIME")
    private Date endTime;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 是否为默认，0：非，1：是
     */
    @Column(name = "IS_DEFAULT")
    private Boolean isDefault;

    /**
     * 英文名称
     */
    @Column(name = "EN_NAME")
    private String enName;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 关键字
     */
    @Column(name = "KEYWORDS")
    private String keywords;

    /**
     * 缩略图
     */
    @Column(name = "THUMBNAIL")
    private String thumbnail;

    /**
     * 代码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 商品种类外键ID
     */
    @Column(name = "CATEGORY_ID")
    private String categoryId;

    @Column(name = "CONTENT")
    private String content;

    /**
     * 英文内容
     */
    @Column(name = "EN_CONTENT")
    private String enContent;

    @Column(name = "USER_ID")
    private String userId;

    /**
     * 联系人
     */
    @Column(name = "CONTACT_TELEPHONE")
    private String contactTelephone;

    /**
     * 邮件
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 管理员姓名
     */
    @Column(name = "ADMIN_NAME")
    private String adminName;

    /**
     * 商标地址
     */
    @Column(name = "SHOP_ICON")
    private String shopIcon;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取创建人
     *
     * @return CREATOR - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取组织结构表ID，该字段用于过滤数据，不做外键关联
     *
     * @return ORG_ID - 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置组织结构表ID，该字段用于过滤数据，不做外键关联
     *
     * @param orgId 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取更新人
     *
     * @return UPDATER - 更新人
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * 设置更新人
     *
     * @param updater 更新人
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    /**
     * 获取创建日期
     *
     * @return CREATE_DATE - 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新日期
     *
     * @return UPDATE_DATE - 更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新日期
     *
     * @param updateDate 更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取是否禁用
     *
     * @return ENABLED - 是否禁用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否禁用
     *
     * @param enabled 是否禁用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取开始时间，用于查询绑定
     *
     * @return START_TIME - 开始时间，用于查询绑定
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置开始时间，用于查询绑定
     *
     * @param startTime 开始时间，用于查询绑定
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间，用于查询绑定
     *
     * @return END_TIME - 结束时间，用于查询绑定
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间，用于查询绑定
     *
     * @param endTime 结束时间，用于查询绑定
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取排序
     *
     * @return SORT - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取是否为默认，0：非，1：是
     *
     * @return IS_DEFAULT - 是否为默认，0：非，1：是
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否为默认，0：非，1：是
     *
     * @param isDefault 是否为默认，0：非，1：是
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取英文名称
     *
     * @return EN_NAME - 英文名称
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置英文名称
     *
     * @param enName 英文名称
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * 获取名称
     *
     * @return NAME - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取描述
     *
     * @return DESCRIPTION - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取关键字
     *
     * @return KEYWORDS - 关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键字
     *
     * @param keywords 关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * 获取缩略图
     *
     * @return THUMBNAIL - 缩略图
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置缩略图
     *
     * @param thumbnail 缩略图
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 获取代码
     *
     * @return CODE - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取商品种类外键ID
     *
     * @return CATEGORY_ID - 商品种类外键ID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 设置商品种类外键ID
     *
     * @param categoryId 商品种类外键ID
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return CONTENT
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取英文内容
     *
     * @return EN_CONTENT - 英文内容
     */
    public String getEnContent() {
        return enContent;
    }

    /**
     * 设置英文内容
     *
     * @param enContent 英文内容
     */
    public void setEnContent(String enContent) {
        this.enContent = enContent;
    }

    /**
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取联系人
     *
     * @return CONTACT_TELEPHONE - 联系人
     */
    public String getContactTelephone() {
        return contactTelephone;
    }

    /**
     * 设置联系人
     *
     * @param contactTelephone 联系人
     */
    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    /**
     * 获取邮件
     *
     * @return EMAIL - 邮件
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮件
     *
     * @param email 邮件
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取管理员姓名
     *
     * @return ADMIN_NAME - 管理员姓名
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 设置管理员姓名
     *
     * @param adminName 管理员姓名
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * 获取商标地址
     *
     * @return SHOP_ICON - 商标地址
     */
    public String getShopIcon() {
        return shopIcon;
    }

    /**
     * 设置商标地址
     *
     * @param shopIcon 商标地址
     */
    public void setShopIcon(String shopIcon) {
        this.shopIcon = shopIcon;
    }
}