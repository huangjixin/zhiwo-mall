package com.zwo.xiyangyang.modules.pr.domain;

import javax.persistence.*;

@Table(name = "pr_price")
public class PrPrice implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 属性表描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 属性ID
     */
    @Column(name = "PRODUCT_ID")
    private String productId;

    /**
     * 是否禁用，0否，1是
     */
    @Column(name = "ENABLED")
    private Boolean enabled;

    @Column(name = "VALUE_PRICE_ID")
    private String valuePriceId;

    /**
     * 团购价
     */
    @Column(name = "GOURP_PRICE")
    private String gourpPrice;

    /**
     * 属性值组合价
     */
    @Column(name = "INDEPENDENT_PRICE")
    private String independentPrice;

    /**
     * 属性对应的图片
     */
    @Column(name = "ICON")
    private String icon;

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
     * 获取属性ID
     *
     * @return PRODUCT_ID - 属性ID
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置属性ID
     *
     * @param productId 属性ID
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * 获取是否禁用，0否，1是
     *
     * @return ENABLED - 是否禁用，0否，1是
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否禁用，0否，1是
     *
     * @param enabled 是否禁用，0否，1是
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return VALUE_PRICE_ID
     */
    public String getValuePriceId() {
        return valuePriceId;
    }

    /**
     * @param valuePriceId
     */
    public void setValuePriceId(String valuePriceId) {
        this.valuePriceId = valuePriceId;
    }

    /**
     * 获取团购价
     *
     * @return GOURP_PRICE - 团购价
     */
    public String getGourpPrice() {
        return gourpPrice;
    }

    /**
     * 设置团购价
     *
     * @param gourpPrice 团购价
     */
    public void setGourpPrice(String gourpPrice) {
        this.gourpPrice = gourpPrice;
    }

    /**
     * 获取属性值组合价
     *
     * @return INDEPENDENT_PRICE - 属性值组合价
     */
    public String getIndependentPrice() {
        return independentPrice;
    }

    /**
     * 设置属性值组合价
     *
     * @param independentPrice 属性值组合价
     */
    public void setIndependentPrice(String independentPrice) {
        this.independentPrice = independentPrice;
    }

    /**
     * 获取属性对应的图片
     *
     * @return ICON - 属性对应的图片
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置属性对应的图片
     *
     * @param icon 属性对应的图片
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}