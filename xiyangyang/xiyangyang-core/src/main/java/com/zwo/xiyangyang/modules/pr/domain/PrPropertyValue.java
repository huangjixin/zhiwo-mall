package com.zwo.xiyangyang.modules.pr.domain;

import javax.persistence.*;

@Table(name = "pr_property_value")
public class PrPropertyValue implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 属性名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 英文代码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 属性表描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 属性值对应的图片
     */
    @Column(name = "IMAGE_ID")
    private String imageId;

    /**
     * 是否可用，0否，1是
     */
    @Column(name = "ENABLED")
    private Boolean enabled;

    @Column(name = "PRODUCT_ID")
    private String productId;

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
     * 获取属性名称
     *
     * @return NAME - 属性名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置属性名称
     *
     * @param name 属性名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取英文代码
     *
     * @return CODE - 英文代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置英文代码
     *
     * @param code 英文代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取属性表描述
     *
     * @return DESCRIPTION - 属性表描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置属性表描述
     *
     * @param description 属性表描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取属性值对应的图片
     *
     * @return IMAGE_ID - 属性值对应的图片
     */
    public String getImageId() {
        return imageId;
    }

    /**
     * 设置属性值对应的图片
     *
     * @param imageId 属性值对应的图片
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    /**
     * 获取是否可用，0否，1是
     *
     * @return ENABLED - 是否可用，0否，1是
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用，0否，1是
     *
     * @param enabled 是否可用，0否，1是
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return PRODUCT_ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
}