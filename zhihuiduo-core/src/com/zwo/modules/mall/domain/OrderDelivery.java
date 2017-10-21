package com.zwo.modules.mall.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "order_delivery")
public class OrderDelivery implements Serializable {
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
    @Column(name = "DISABLED")
    private Boolean disabled;

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
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    @Column(name = "ORDER_ID")
    private String orderId;

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
     * @return DISABLED - 是否禁用
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * 设置是否禁用
     *
     * @param disabled 是否禁用
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return ORDER_ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }
}