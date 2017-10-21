package com.zwo.modules.system.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "tb_user_trade_marke")
public class TbUserTradeMarke implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商标注册号
     */
    @Column(name = "MARK_CODE")
    private String markCode;

    /**
     * 商标注册证明
     */
    @Column(name = "MARK_CODE_AUTH")
    private String markCodeAuth;

    /**
     * 品牌授权证明
     */
    @Column(name = "BRAND_AUTH")
    private String brandAuth;

    /**
     * 品牌授权有效期
     */
    @Column(name = "BRAND_AUTH_INEFFITIVE")
    private String brandAuthIneffitive;

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

    @Column(name = "USER_ID")
    private String userId;

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
     * 获取商标注册号
     *
     * @return MARK_CODE - 商标注册号
     */
    public String getMarkCode() {
        return markCode;
    }

    /**
     * 设置商标注册号
     *
     * @param markCode 商标注册号
     */
    public void setMarkCode(String markCode) {
        this.markCode = markCode == null ? null : markCode.trim();
    }

    /**
     * 获取商标注册证明
     *
     * @return MARK_CODE_AUTH - 商标注册证明
     */
    public String getMarkCodeAuth() {
        return markCodeAuth;
    }

    /**
     * 设置商标注册证明
     *
     * @param markCodeAuth 商标注册证明
     */
    public void setMarkCodeAuth(String markCodeAuth) {
        this.markCodeAuth = markCodeAuth == null ? null : markCodeAuth.trim();
    }

    /**
     * 获取品牌授权证明
     *
     * @return BRAND_AUTH - 品牌授权证明
     */
    public String getBrandAuth() {
        return brandAuth;
    }

    /**
     * 设置品牌授权证明
     *
     * @param brandAuth 品牌授权证明
     */
    public void setBrandAuth(String brandAuth) {
        this.brandAuth = brandAuth == null ? null : brandAuth.trim();
    }

    /**
     * 获取品牌授权有效期
     *
     * @return BRAND_AUTH_INEFFITIVE - 品牌授权有效期
     */
    public String getBrandAuthIneffitive() {
        return brandAuthIneffitive;
    }

    /**
     * 设置品牌授权有效期
     *
     * @param brandAuthIneffitive 品牌授权有效期
     */
    public void setBrandAuthIneffitive(String brandAuthIneffitive) {
        this.brandAuthIneffitive = brandAuthIneffitive == null ? null : brandAuthIneffitive.trim();
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
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}