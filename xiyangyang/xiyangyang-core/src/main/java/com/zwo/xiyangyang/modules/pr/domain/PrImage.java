package com.zwo.xiyangyang.modules.pr.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "pr_image")
public class PrImage implements java.io.Serializable {

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
     * 图片连接地址
     */
    @Column(name = "URL")
    private String url;

    /**
     * 产品ID
     */
    @Column(name = "PRODUCT_ID")
    private String productId;

    /**
     * 图片物理地址
     */
    @Column(name = "LOCATION")
    private String location;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 真正的外键ID
     */
    @Column(name = "TEMP_PRODUCT_ID")
    private String tempProductId;

    /**
     * 商品的缩略图，也可能是商品属性图，也可能是商品详情描述图
三种类型的值分别是：thumbnail，detail，prop，prop_thumbnail
     */
    @Column(name = "TYPE")
    private String type;

    @Column(name = "IP")
    private String ip;

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
     * 获取图片连接地址
     *
     * @return URL - 图片连接地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置图片连接地址
     *
     * @param url 图片连接地址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取产品ID
     *
     * @return PRODUCT_ID - 产品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置产品ID
     *
     * @param productId 产品ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取图片物理地址
     *
     * @return LOCATION - 图片物理地址
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置图片物理地址
     *
     * @param location 图片物理地址
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取真正的外键ID
     *
     * @return TEMP_PRODUCT_ID - 真正的外键ID
     */
    public String getTempProductId() {
        return tempProductId;
    }

    /**
     * 设置真正的外键ID
     *
     * @param tempProductId 真正的外键ID
     */
    public void setTempProductId(String tempProductId) {
        this.tempProductId = tempProductId;
    }

    /**
     * 获取商品的缩略图，也可能是商品属性图，也可能是商品详情描述图
三种类型的值分别是：thumbnail，detail，prop，prop_thumbnail
     *
     * @return TYPE - 商品的缩略图，也可能是商品属性图，也可能是商品详情描述图
三种类型的值分别是：thumbnail，detail，prop，prop_thumbnail
     */
    public String getType() {
        return type;
    }

    /**
     * 设置商品的缩略图，也可能是商品属性图，也可能是商品详情描述图
三种类型的值分别是：thumbnail，detail，prop，prop_thumbnail
     *
     * @param type 商品的缩略图，也可能是商品属性图，也可能是商品详情描述图
三种类型的值分别是：thumbnail，detail，prop，prop_thumbnail
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return IP
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}