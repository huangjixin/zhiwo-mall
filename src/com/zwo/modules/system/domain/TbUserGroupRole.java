package com.zwo.modules.system.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "tb_user_group_role")
public class TbUserGroupRole implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "USERGROUP_ID")
    private String usergroupId;

    @Column(name = "ROLE_ID")
    private String roleId;

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
     * @return USERGROUP_ID
     */
    public String getUsergroupId() {
        return usergroupId;
    }

    /**
     * @param usergroupId
     */
    public void setUsergroupId(String usergroupId) {
        this.usergroupId = usergroupId == null ? null : usergroupId.trim();
    }

    /**
     * @return ROLE_ID
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