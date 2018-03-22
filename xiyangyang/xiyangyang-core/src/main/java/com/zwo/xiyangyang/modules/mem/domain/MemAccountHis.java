package com.zwo.xiyangyang.modules.mem.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mem_account_his")
public class MemAccountHis implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

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
     * 是否禁用,0否,1是
     */
    @Column(name = "ENABLED")
    private Boolean enabled;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 实名
     */
    @Column(name = "REAL_NAME")
    private String realName;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    @Column(name = "ORG_ID")
    private String orgId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    /**
     * 是否锁定,0否,1是
     */
    @Column(name = "LOCKED")
    private Boolean locked;

    /**
     * 账号余额
     */
    @Column(name = "BALANCE")
    private Double balance;

    /**
     * 存款变动金额
     */
    @Column(name = "DEPOSIT")
    private Double deposit;

    @Column(name = "TYPE")
    private String type;

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
     * 获取是否禁用,0否,1是
     *
     * @return ENABLED - 是否禁用,0否,1是
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否禁用,0否,1是
     *
     * @param enabled 是否禁用,0否,1是
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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
     * 获取实名
     *
     * @return REAL_NAME - 实名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置实名
     *
     * @param realName 实名
     */
    public void setRealName(String realName) {
        this.realName = realName;
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
     * @return MEMBER_ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取是否锁定,0否,1是
     *
     * @return LOCKED - 是否锁定,0否,1是
     */
    public Boolean getLocked() {
        return locked;
    }

    /**
     * 设置是否锁定,0否,1是
     *
     * @param locked 是否锁定,0否,1是
     */
    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    /**
     * 获取账号余额
     *
     * @return BALANCE - 账号余额
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * 设置账号余额
     *
     * @param balance 账号余额
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * 获取存款变动金额
     *
     * @return DEPOSIT - 存款变动金额
     */
    public Double getDeposit() {
        return deposit;
    }

    /**
     * 设置存款变动金额
     *
     * @param deposit 存款变动金额
     */
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    /**
     * @return TYPE
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}