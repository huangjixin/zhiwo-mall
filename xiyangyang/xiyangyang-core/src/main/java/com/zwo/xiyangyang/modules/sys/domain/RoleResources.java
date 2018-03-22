package com.zwo.xiyangyang.modules.sys.domain;

import javax.persistence.*;

@Table(name = "sys_role_resources")
public class RoleResources implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "RESOURCES_ID")
    private String resourcesId;

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
     * @return RESOURCES_ID
     */
    public String getResourcesId() {
        return resourcesId;
    }

    /**
     * @param resourcesId
     */
    public void setResourcesId(String resourcesId) {
        this.resourcesId = resourcesId;
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