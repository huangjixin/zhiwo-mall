package com.zwo.xiyangyang.modules.sys.domain;

import javax.persistence.*;

@Table(name = "sys_group_role")
public class GroupRole implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "USERGROUP_ID")
    private String usergroupId;

    @Column(name = "ROLE_ID")
    private String roleId;

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
     * @return USERGROUP_ID
     */
    public String getUsergroupId() {
        return usergroupId;
    }

    /**
     * @param usergroupId
     */
    public void setUsergroupId(String usergroupId) {
        this.usergroupId = usergroupId;
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
        this.roleId = roleId;
    }
}