package com.zwo.modules.system.domain;

import java.io.Serializable;
import javax.persistence.*;

import lombok.Data;

@Table(name = "bhm_role_res")
@Data
public class RoleResource implements Serializable {
    @Column(name = "role_id")
    private String roleId;

    @Column(name = "res_id")
    private String resId;

    private static final long serialVersionUID = 1L;

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

    /**
     * @return res_id
     */
    public String getResId() {
        return resId;
    }

    /**
     * @param resId
     */
    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }
}