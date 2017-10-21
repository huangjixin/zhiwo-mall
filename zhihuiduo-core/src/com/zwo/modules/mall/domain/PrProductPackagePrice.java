package com.zwo.modules.mall.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "pr_product_package_price")
public class PrProductPackagePrice implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 属性组合
     */
    @Column(name = "PROPERTIES")
    private String properties;

    /**
     * 属性值组合价
     */
    @Column(name = "INDEPENDENT_PRICE")
    private String independentPrice;

    /**
     * 产品ID，根据ID可以取出产品不同的组合价钱
     */
    @Column(name = "PRODUCT_ID")
    private String productId;

    /**
     * 团购价
     */
    @Column(name = "GOURP_PRICE")
    private String gourpPrice;

    /**
     * 属性ID
     */
    @Column(name = "PROPERTY_ID")
    private String propertyId;

    @Column(name = "PROPERTY_VAlUE_ID")
    private String propertyValueId;

    @Column(name = "DISABLE")
    private Byte disable;

    /**
     * 属性对应的图片
     */
    @Column(name = "ICON")
    private String icon;

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
     * 获取属性组合
     *
     * @return PROPERTIES - 属性组合
     */
    public String getProperties() {
        return properties;
    }

    /**
     * 设置属性组合
     *
     * @param properties 属性组合
     */
    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
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
        this.independentPrice = independentPrice == null ? null : independentPrice.trim();
    }

    /**
     * 获取产品ID，根据ID可以取出产品不同的组合价钱
     *
     * @return PRODUCT_ID - 产品ID，根据ID可以取出产品不同的组合价钱
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置产品ID，根据ID可以取出产品不同的组合价钱
     *
     * @param productId 产品ID，根据ID可以取出产品不同的组合价钱
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
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
        this.gourpPrice = gourpPrice == null ? null : gourpPrice.trim();
    }

    /**
     * 获取属性ID
     *
     * @return PROPERTY_ID - 属性ID
     */
    public String getPropertyId() {
        return propertyId;
    }

    /**
     * 设置属性ID
     *
     * @param propertyId 属性ID
     */
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId == null ? null : propertyId.trim();
    }

    /**
     * @return PROPERTY_VAlUE_ID
     */
    public String getPropertyValueId() {
        return propertyValueId;
    }

    /**
     * @param propertyValueId
     */
    public void setPropertyValueId(String propertyValueId) {
        this.propertyValueId = propertyValueId == null ? null : propertyValueId.trim();
    }

    /**
     * @return DISABLE
     */
    public Byte getDisable() {
        return disable;
    }

    /**
     * @param disable
     */
    public void setDisable(Byte disable) {
        this.disable = disable;
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
        this.icon = icon == null ? null : icon.trim();
    }
}