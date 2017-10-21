package com.zwo.modules.member.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "member_play_account")
public class MemberPlayAccount implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "NAME")
    private String name;

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
     * 智慧豆数量
     */
    @Column(name = "ZHIHUIDOU_COUNT")
    private Integer zhihuidouCount;

    /**
     * 会员外键
     */
    @Column(name = "MEMBER_ID")
    private String memberId;

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
     * 获取用户名
     *
     * @return NAME - 用户名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户名
     *
     * @param name 用户名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
     * 获取智慧豆数量
     *
     * @return ZHIHUIDOU_COUNT - 智慧豆数量
     */
    public Integer getZhihuidouCount() {
        return zhihuidouCount;
    }

    /**
     * 设置智慧豆数量
     *
     * @param zhihuidouCount 智慧豆数量
     */
    public void setZhihuidouCount(Integer zhihuidouCount) {
        this.zhihuidouCount = zhihuidouCount;
    }

    /**
     * 获取会员外键
     *
     * @return MEMBER_ID - 会员外键
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置会员外键
     *
     * @param memberId 会员外键
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }
}