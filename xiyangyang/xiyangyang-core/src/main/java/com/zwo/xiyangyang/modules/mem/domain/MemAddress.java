package com.zwo.xiyangyang.modules.mem.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mem_address")
public class MemAddress implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 省
     */
    @Column(name = "PROVINCE")
    private String province;

    /**
     * 市
     */
    @Column(name = "CITY")
    private String city;

    /**
     * 区
     */
    @Column(name = "AREA")
    private String area;

    /**
     * 姓名
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 手机
     */
    @Column(name = "MOBIL_PHONE")
    private String mobilPhone;

    /**
     * 街道
     */
    @Column(name = "STREET")
    private String street;

    @Column(name = "MEMBER_ID")
    private String memberId;

    /**
     * 是否设置为默认1为是,0为否
     */
    @Column(name = "IS_DEFAULT")
    private String isDefault;

    @Column(name = "ENABLED")
    private String enabled;

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
     * 获取省
     *
     * @return PROVINCE - 省
     */
    public String getProvince() {
        return province;
    }

    /**
     * 设置省
     *
     * @param province 省
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取市
     *
     * @return CITY - 市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置市
     *
     * @param city 市
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取区
     *
     * @return AREA - 区
     */
    public String getArea() {
        return area;
    }

    /**
     * 设置区
     *
     * @param area 区
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取姓名
     *
     * @return NAME - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取手机
     *
     * @return MOBIL_PHONE - 手机
     */
    public String getMobilPhone() {
        return mobilPhone;
    }

    /**
     * 设置手机
     *
     * @param mobilPhone 手机
     */
    public void setMobilPhone(String mobilPhone) {
        this.mobilPhone = mobilPhone;
    }

    /**
     * 获取街道
     *
     * @return STREET - 街道
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道
     *
     * @param street 街道
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return MEMBER_ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取是否设置为默认1为是,0为否
     *
     * @return IS_DEFAULT - 是否设置为默认1为是,0为否
     */
    public String getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否设置为默认1为是,0为否
     *
     * @param isDefault 是否设置为默认1为是,0为否
     */
    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return ENABLED
     */
    public String getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(String enabled) {
        this.enabled = enabled;
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
}