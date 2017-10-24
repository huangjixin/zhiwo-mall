package com.zwo.modules.system.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "tb_user_account")
public class TbUserAccount implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "DISABLED")
    private Boolean disabled;

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
     * 用户ID，关联tb_user表
     */
    @Column(name = "USER_ID")
    private String userId;

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
     * @return DISABLED - 是否禁用,0否,1是
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * 设置是否禁用,0否,1是
     *
     * @param disabled 是否禁用,0否,1是
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
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
        this.realName = realName == null ? null : realName.trim();
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
     * 获取用户ID，关联tb_user表
     *
     * @return USER_ID - 用户ID，关联tb_user表
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID，关联tb_user表
     *
     * @param userId 用户ID，关联tb_user表
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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
}