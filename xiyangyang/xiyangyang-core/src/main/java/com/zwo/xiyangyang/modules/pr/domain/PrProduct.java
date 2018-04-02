package com.zwo.xiyangyang.modules.pr.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "pr_product")
public class PrProduct implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
	@Transient
	private Shop shop;
	
	@Transient
	private List<PrPropertyValue> propertyValues;
	
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
     * 是否可用，1是，0否，默认商品应该是经过审核才可以用的。
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
     * 图标
     */
    @Column(name = "ICON")
    private String icon;

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
     * 产品分类外键ID
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

    /**
     * 是否允许分销
     */
    @Column(name = "ALLOW_DISTRIBUTION")
    private Boolean allowDistribution;

    /**
     * 分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     */
    @Column(name = "DISTRIBUTION_VALUE")
    private Double distributionValue;

    /**
     * 该商品所属的店
     */
    @Column(name = "SHOP_ID")
    private String shopId;

    /**
     * 用户ID，本来通过shop_id可以通过查询SHOP表拿到用户ID，但是此处为了方便查询做了冗余
     */
    @Column(name = "USER_ID")
    private String userId;

    /**
     * 进货价
     */
    @Column(name = "PURCHASING_COST")
    private Double purchasingCost;

    /**
     * 分销介绍
     */
    @Column(name = "DIST_INTRUEDUTION")
    private String distIntruedution;

    /**
     * 供应商ID
     */
    @Column(name = "SUPPLIER_ID")
    private String supplierId;

    /**
     * 团购价
     */
    @Column(name = "GOURP_SALE_PRICE")
    private Double gourpSalePrice;

    /**
     * 独立销售价
     */
    @Column(name = "INDEPENDENT_PRICE")
    private Double independentPrice;

    /**
     * 开团人数
     */
    @Column(name = "NUMBER_COUNT")
    private Integer numberCount;

    /**
     * 审核原因，多条用“||”分割开，比如管理员觉得你图片质量非常差，打回去让你重新修改上架，这个时候修改数据库的时候，用“||”分割开来多个原因
     */
    @Column(name = "AUDIT_DESCRIPTION")
    private String auditDescription;

    /**
     * 库存
     */
    @Column(name = "STORAGE")
    private Integer storage;

    /**
     * 商品状态，上架或者下架，默认上架为：online，下架为offline
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 市价或者划下价
     */
    @Column(name = "MARKET_PRICE")
    private Double marketPrice;

    /**
     * 运费
     */
    @Column(name = "TRANSPORT_FEE")
    private Double transportFee;

    /**
     * 商店名称
     */
    @Column(name = "SHOP_NAME")
    private String shopName;

    /**
     * 是否支持货到付款，1是，0否
     */
    @Column(name = "IS_SENT_UNPAY")
    private Integer isSentUnpay;

    /**
     * 审核状态，0未审核，1审核通过，2驳回
     */
    @Column(name = "CHECK_STATUS")
    private Integer checkStatus;

    /**
     * 类型，字段值暂未确定。
     */
    @Column(name = "TYPE")
    private String type;

    /**
     * 销售数量
     */
    @Column(name = "SALE_COUNT")
    private Integer saleCount;

    /**
     * 是否推荐到首页
     */
    @Column(name = "TO_INDEX")
    private Boolean toIndex;

    /**
     * 是否推荐到子栏目首页
     */
    @Column(name = "TO_SUB_INDEX")
    private Boolean toSubIndex;

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
     * 获取是否可用，1是，0否，默认商品应该是经过审核才可以用的。
     *
     * @return ENABLED - 是否可用，1是，0否，默认商品应该是经过审核才可以用的。
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用，1是，0否，默认商品应该是经过审核才可以用的。
     *
     * @param enabled 是否可用，1是，0否，默认商品应该是经过审核才可以用的。
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
     * 获取图标
     *
     * @return ICON - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
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
     * 获取产品分类外键ID
     *
     * @return CATEGORY_ID - 产品分类外键ID
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * 设置产品分类外键ID
     *
     * @param categoryId 产品分类外键ID
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
     * 获取是否允许分销
     *
     * @return ALLOW_DISTRIBUTION - 是否允许分销
     */
    public Boolean getAllowDistribution() {
        return allowDistribution;
    }

    /**
     * 设置是否允许分销
     *
     * @param allowDistribution 是否允许分销
     */
    public void setAllowDistribution(Boolean allowDistribution) {
        this.allowDistribution = allowDistribution;
    }

    /**
     * 获取分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     *
     * @return DISTRIBUTION_VALUE - 分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     */
    public Double getDistributionValue() {
        return distributionValue;
    }

    /**
     * 设置分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     *
     * @param distributionValue 分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     */
    public void setDistributionValue(Double distributionValue) {
        this.distributionValue = distributionValue;
    }

    /**
     * 获取该商品所属的店
     *
     * @return SHOP_ID - 该商品所属的店
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * 设置该商品所属的店
     *
     * @param shopId 该商品所属的店
     */
    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    /**
     * 获取用户ID，本来通过shop_id可以通过查询SHOP表拿到用户ID，但是此处为了方便查询做了冗余
     *
     * @return USER_ID - 用户ID，本来通过shop_id可以通过查询SHOP表拿到用户ID，但是此处为了方便查询做了冗余
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID，本来通过shop_id可以通过查询SHOP表拿到用户ID，但是此处为了方便查询做了冗余
     *
     * @param userId 用户ID，本来通过shop_id可以通过查询SHOP表拿到用户ID，但是此处为了方便查询做了冗余
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取进货价
     *
     * @return PURCHASING_COST - 进货价
     */
    public Double getPurchasingCost() {
        return purchasingCost;
    }

    /**
     * 设置进货价
     *
     * @param purchasingCost 进货价
     */
    public void setPurchasingCost(Double purchasingCost) {
        this.purchasingCost = purchasingCost;
    }

    /**
     * 获取分销介绍
     *
     * @return DIST_INTRUEDUTION - 分销介绍
     */
    public String getDistIntruedution() {
        return distIntruedution;
    }

    /**
     * 设置分销介绍
     *
     * @param distIntruedution 分销介绍
     */
    public void setDistIntruedution(String distIntruedution) {
        this.distIntruedution = distIntruedution;
    }

    /**
     * 获取供应商ID
     *
     * @return SUPPLIER_ID - 供应商ID
     */
    public String getSupplierId() {
        return supplierId;
    }

    /**
     * 设置供应商ID
     *
     * @param supplierId 供应商ID
     */
    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * 获取团购价
     *
     * @return GOURP_SALE_PRICE - 团购价
     */
    public Double getGourpSalePrice() {
        return gourpSalePrice;
    }

    /**
     * 设置团购价
     *
     * @param gourpSalePrice 团购价
     */
    public void setGourpSalePrice(Double gourpSalePrice) {
        this.gourpSalePrice = gourpSalePrice;
    }

    /**
     * 获取独立销售价
     *
     * @return INDEPENDENT_PRICE - 独立销售价
     */
    public Double getIndependentPrice() {
        return independentPrice;
    }

    /**
     * 设置独立销售价
     *
     * @param independentPrice 独立销售价
     */
    public void setIndependentPrice(Double independentPrice) {
        this.independentPrice = independentPrice;
    }

    /**
     * 获取开团人数
     *
     * @return NUMBER_COUNT - 开团人数
     */
    public Integer getNumberCount() {
        return numberCount;
    }

    /**
     * 设置开团人数
     *
     * @param numberCount 开团人数
     */
    public void setNumberCount(Integer numberCount) {
        this.numberCount = numberCount;
    }

    /**
     * 获取审核原因，多条用“||”分割开，比如管理员觉得你图片质量非常差，打回去让你重新修改上架，这个时候修改数据库的时候，用“||”分割开来多个原因
     *
     * @return AUDIT_DESCRIPTION - 审核原因，多条用“||”分割开，比如管理员觉得你图片质量非常差，打回去让你重新修改上架，这个时候修改数据库的时候，用“||”分割开来多个原因
     */
    public String getAuditDescription() {
        return auditDescription;
    }

    /**
     * 设置审核原因，多条用“||”分割开，比如管理员觉得你图片质量非常差，打回去让你重新修改上架，这个时候修改数据库的时候，用“||”分割开来多个原因
     *
     * @param auditDescription 审核原因，多条用“||”分割开，比如管理员觉得你图片质量非常差，打回去让你重新修改上架，这个时候修改数据库的时候，用“||”分割开来多个原因
     */
    public void setAuditDescription(String auditDescription) {
        this.auditDescription = auditDescription;
    }

    /**
     * 获取库存
     *
     * @return STORAGE - 库存
     */
    public Integer getStorage() {
        return storage;
    }

    /**
     * 设置库存
     *
     * @param storage 库存
     */
    public void setStorage(Integer storage) {
        this.storage = storage;
    }

    /**
     * 获取商品状态，上架或者下架，默认上架为：online，下架为offline
     *
     * @return STATUS - 商品状态，上架或者下架，默认上架为：online，下架为offline
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置商品状态，上架或者下架，默认上架为：online，下架为offline
     *
     * @param status 商品状态，上架或者下架，默认上架为：online，下架为offline
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取市价或者划下价
     *
     * @return MARKET_PRICE - 市价或者划下价
     */
    public Double getMarketPrice() {
        return marketPrice;
    }

    /**
     * 设置市价或者划下价
     *
     * @param marketPrice 市价或者划下价
     */
    public void setMarketPrice(Double marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取运费
     *
     * @return TRANSPORT_FEE - 运费
     */
    public Double getTransportFee() {
        return transportFee;
    }

    /**
     * 设置运费
     *
     * @param transportFee 运费
     */
    public void setTransportFee(Double transportFee) {
        this.transportFee = transportFee;
    }

    /**
     * 获取商店名称
     *
     * @return SHOP_NAME - 商店名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置商店名称
     *
     * @param shopName 商店名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     * 获取是否支持货到付款，1是，0否
     *
     * @return IS_SENT_UNPAY - 是否支持货到付款，1是，0否
     */
    public Integer getIsSentUnpay() {
        return isSentUnpay;
    }

    /**
     * 设置是否支持货到付款，1是，0否
     *
     * @param isSentUnpay 是否支持货到付款，1是，0否
     */
    public void setIsSentUnpay(Integer isSentUnpay) {
        this.isSentUnpay = isSentUnpay;
    }

    /**
     * 获取审核状态，0未审核，1审核通过，2驳回
     *
     * @return CHECK_STATUS - 审核状态，0未审核，1审核通过，2驳回
     */
    public Integer getCheckStatus() {
        return checkStatus;
    }

    /**
     * 设置审核状态，0未审核，1审核通过，2驳回
     *
     * @param checkStatus 审核状态，0未审核，1审核通过，2驳回
     */
    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    /**
     * 获取类型，字段值暂未确定。
     *
     * @return TYPE - 类型，字段值暂未确定。
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型，字段值暂未确定。
     *
     * @param type 类型，字段值暂未确定。
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取销售数量
     *
     * @return SALE_COUNT - 销售数量
     */
    public Integer getSaleCount() {
        return saleCount;
    }

    /**
     * 设置销售数量
     *
     * @param saleCount 销售数量
     */
    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    /**
     * 获取是否推荐到首页
     *
     * @return TO_INDEX - 是否推荐到首页
     */
    public Boolean getToIndex() {
        return toIndex;
    }

    /**
     * 设置是否推荐到首页
     *
     * @param toIndex 是否推荐到首页
     */
    public void setToIndex(Boolean toIndex) {
        this.toIndex = toIndex;
    }

    /**
     * 获取是否推荐到子栏目首页
     *
     * @return TO_SUB_INDEX - 是否推荐到子栏目首页
     */
    public Boolean getToSubIndex() {
        return toSubIndex;
    }

    /**
     * 设置是否推荐到子栏目首页
     *
     * @param toSubIndex 是否推荐到子栏目首页
     */
    public void setToSubIndex(Boolean toSubIndex) {
        this.toSubIndex = toSubIndex;
    }

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<PrPropertyValue> getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(List<PrPropertyValue> propertyValues) {
		this.propertyValues = propertyValues;
	}

}