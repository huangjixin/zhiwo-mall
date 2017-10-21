package com.zwo.modules.system.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "tb_user_quantity_report")
public class TbUserQuantityReport implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "USER_ASSET_ID")
    private String userAssetId;

    /**
     * 密码
     */
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
     * 获取用户名
     *
     * @return USER_ASSET_ID - 用户名
     */
    public String getUserAssetId() {
        return userAssetId;
    }

    /**
     * 设置用户名
     *
     * @param userAssetId 用户名
     */
    public void setUserAssetId(String userAssetId) {
        this.userAssetId = userAssetId == null ? null : userAssetId.trim();
    }

    /**
     * 获取密码
     *
     * @return USER_ID - 密码
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置密码
     *
     * @param userId 密码
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}