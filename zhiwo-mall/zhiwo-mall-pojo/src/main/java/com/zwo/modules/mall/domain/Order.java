package com.zwo.modules.mall.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "order")
public class Order implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 创建人
     */
    @Column(name = "CREATOR")
    private String creator;

    /**
     * 更新人
     */
    @Column(name = "UPDATER")
    private String updater;

    /**
     * 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    @Column(name = "ORG_ID")
    private String orgId;

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
    @Column(name = "DISABLE")
    private Boolean disable;

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
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 代码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 订单状态
     */
    @Column(name = "STATUS")
    private String status;

    /**
     * 商铺ID
     */
    @Column(name = "SHOP_ID")
    private String shopId;

    /**
     * 商品ID
     */
    @Column(name = "PRODUCT_ID")
    private String productId;

    /**
     * 实付
     */
    @Column(name = "DEAL_PRICE")
    private String dealPrice;

    /**
     * 会员ID
     */
    @Column(name = "MEMBER_ID")
    private String memberId;

    /**
     * 购买数量
     */
    @Column(name = "BUY_NUM")
    private Integer buyNum;

    /**
     * 收货人名称
     */
    @Column(name = "RECIVE_NAME")
    private String reciveName;

    /**
     * 具体地址
     */
    @Column(name = "ADDRESS")
    private String address;

    /**
     * 电话
     */
    @Column(name = "TELEPHONE")
    private String telephone;

    /**
     * 邮码
     */
    @Column(name = "MAIL_CODE")
    private String mailCode;

    /**
     * 物流公司
     */
    @Column(name = "DELIVERY_COMPANY")
    private String deliveryCompany;

    /**
     * 物流公司订单号
     */
    @Column(name = "DELIVERY_ORDER_CODE")
    private String deliveryOrderCode;

    /**
     * 是否为默认，0：非，1：是
     */
    @Column(name = "IS_FORM_SCCUESS")
    private Boolean isFormSccuess;

    /**
     * 运费
     */
    @Column(name = "TRANSPORT_FEE")
    private Double transportFee;

    private static final long serialVersionUID = 1L;

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
        this.id = id == null ? null : id.trim();
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
        this.creator = creator == null ? null : creator.trim();
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
        this.updater = updater == null ? null : updater.trim();
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
        this.orgId = orgId == null ? null : orgId.trim();
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
     * @return DISABLE - 是否禁用
     */
    public Boolean getDisable() {
        return disable;
    }

    /**
     * 设置是否禁用
     *
     * @param disable 是否禁用
     */
    public void setDisable(Boolean disable) {
        this.disable = disable;
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
        this.description = description == null ? null : description.trim();
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
        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取订单状态
     *
     * @return STATUS - 订单状态
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置订单状态
     *
     * @param status 订单状态
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * 获取商铺ID
     *
     * @return SHOP_ID - 商铺ID
     */
    public String getShopId() {
        return shopId;
    }

    /**
     * 设置商铺ID
     *
     * @param shopId 商铺ID
     */
    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    /**
     * 获取商品ID
     *
     * @return PRODUCT_ID - 商品ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置商品ID
     *
     * @param productId 商品ID
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * 获取实付
     *
     * @return DEAL_PRICE - 实付
     */
    public String getDealPrice() {
        return dealPrice;
    }

    /**
     * 设置实付
     *
     * @param dealPrice 实付
     */
    public void setDealPrice(String dealPrice) {
        this.dealPrice = dealPrice == null ? null : dealPrice.trim();
    }

    /**
     * 获取会员ID
     *
     * @return MEMBER_ID - 会员ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置会员ID
     *
     * @param memberId 会员ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 获取购买数量
     *
     * @return BUY_NUM - 购买数量
     */
    public Integer getBuyNum() {
        return buyNum;
    }

    /**
     * 设置购买数量
     *
     * @param buyNum 购买数量
     */
    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    /**
     * 获取收货人名称
     *
     * @return RECIVE_NAME - 收货人名称
     */
    public String getReciveName() {
        return reciveName;
    }

    /**
     * 设置收货人名称
     *
     * @param reciveName 收货人名称
     */
    public void setReciveName(String reciveName) {
        this.reciveName = reciveName == null ? null : reciveName.trim();
    }

    /**
     * 获取具体地址
     *
     * @return ADDRESS - 具体地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置具体地址
     *
     * @param address 具体地址
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * 获取电话
     *
     * @return TELEPHONE - 电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置电话
     *
     * @param telephone 电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * 获取邮码
     *
     * @return MAIL_CODE - 邮码
     */
    public String getMailCode() {
        return mailCode;
    }

    /**
     * 设置邮码
     *
     * @param mailCode 邮码
     */
    public void setMailCode(String mailCode) {
        this.mailCode = mailCode == null ? null : mailCode.trim();
    }

    /**
     * 获取物流公司
     *
     * @return DELIVERY_COMPANY - 物流公司
     */
    public String getDeliveryCompany() {
        return deliveryCompany;
    }

    /**
     * 设置物流公司
     *
     * @param deliveryCompany 物流公司
     */
    public void setDeliveryCompany(String deliveryCompany) {
        this.deliveryCompany = deliveryCompany == null ? null : deliveryCompany.trim();
    }

    /**
     * 获取物流公司订单号
     *
     * @return DELIVERY_ORDER_CODE - 物流公司订单号
     */
    public String getDeliveryOrderCode() {
        return deliveryOrderCode;
    }

    /**
     * 设置物流公司订单号
     *
     * @param deliveryOrderCode 物流公司订单号
     */
    public void setDeliveryOrderCode(String deliveryOrderCode) {
        this.deliveryOrderCode = deliveryOrderCode == null ? null : deliveryOrderCode.trim();
    }

    /**
     * 获取是否为默认，0：非，1：是
     *
     * @return IS_FORM_SCCUESS - 是否为默认，0：非，1：是
     */
    public Boolean getIsFormSccuess() {
        return isFormSccuess;
    }

    /**
     * 设置是否为默认，0：非，1：是
     *
     * @param isFormSccuess 是否为默认，0：非，1：是
     */
    public void setIsFormSccuess(Boolean isFormSccuess) {
        this.isFormSccuess = isFormSccuess;
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
}