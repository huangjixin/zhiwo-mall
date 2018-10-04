package com.zwo.modules.system.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Table(name = "bhm_user_role")
@Data
public class UserRole implements Serializable {
    @Column(name = "user_id")
    private String userId;

    @Column(name = "role_id")
    private String roleId;

    private static final long serialVersionUID = 1L;

    /**
     * @return user_id
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

    /**
     * @return role_id
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }
}