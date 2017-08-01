package com.zwo.modules.member.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "member_address")
public class MemberAddress implements Serializable {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 手机
     */
    @Column(name = "MOBIL_PHONE")
    private String mobilPhone;

    /**
     * 是否禁用
     */
    @Column(name = "DISABLE")
    private Boolean disable;

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
    private Boolean isDefault;

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
        this.province = province == null ? null : province.trim();
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
        this.city = city == null ? null : city.trim();
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
        this.area = area == null ? null : area.trim();
    }

    /**
     * 获取邮箱
     *
     * @return EMAIL - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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
        this.mobilPhone = mobilPhone == null ? null : mobilPhone.trim();
    }

    /**
     * 获取是否禁用
     *
     * @return DISABLE - 是否禁用
     */
    public Boolean getDisable() {
        return disable;
    }

    /**
     * 设置是否禁用
     *
     * @param disable 是否禁用
     */
    public void setDisable(Boolean disable) {
        this.disable = disable;
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
        this.street = street == null ? null : street.trim();
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
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 获取是否设置为默认1为是,0为否
     *
     * @return IS_DEFAULT - 是否设置为默认1为是,0为否
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否设置为默认1为是,0为否
     *
     * @param isDefault 是否设置为默认1为是,0为否
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }
}