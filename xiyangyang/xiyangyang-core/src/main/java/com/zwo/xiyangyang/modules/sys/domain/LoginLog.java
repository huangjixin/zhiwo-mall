package com.zwo.xiyangyang.modules.sys.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_login_log")
public class LoginLog implements java.io.Serializable {

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
     * 登录日期
     */
    @Column(name = "LOGIN_DATE")
    private Date loginDate;

    /**
     * 登录的IP地址
     */
    @Column(name = "IP")
    private String ip;

    /**
     * 登录人
     */
    @Column(name = "NAME")
    private String name;

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
     * 获取登录日期
     *
     * @return LOGIN_DATE - 登录日期
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * 设置登录日期
     *
     * @param loginDate 登录日期
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 获取登录的IP地址
     *
     * @return IP - 登录的IP地址
     */
    public String getIp() {
        return ip;
    }

    /**
     * 设置登录的IP地址
     *
     * @param ip 登录的IP地址
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * 获取登录人
     *
     * @return NAME - 登录人
     */
    public String getName() {
        return name;
    }

    /**
     * 设置登录人
     *
     * @param name 登录人
     */
    public void setName(String name) {
        this.name = name;
    }
}