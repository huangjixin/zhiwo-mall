package com.zwo.modules.mall.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

public class PrProductPackagePrice implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pr_product_package_price.ID
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pr_product_package_price.PROPERTIES
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    private String properties;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pr_product_package_price.INDEPENDENT_PRICE
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    private Double independentPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pr_product_package_price.PRODUCT_ID
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    private String productId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column pr_product_package_price.GOURP_PRICE
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    private String gourpPrice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table pr_product_package_price
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pr_product_package_price.ID
     *
     * @return the value of pr_product_package_price.ID
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pr_product_package_price.ID
     *
     * @param id the value for pr_product_package_price.ID
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pr_product_package_price.PROPERTIES
     *
     * @return the value of pr_product_package_price.PROPERTIES
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public String getProperties() {
        return properties;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pr_product_package_price.PROPERTIES
     *
     * @param properties the value for pr_product_package_price.PROPERTIES
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pr_product_package_price.INDEPENDENT_PRICE
     *
     * @return the value of pr_product_package_price.INDEPENDENT_PRICE
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public Double getIndependentPrice() {
        return independentPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pr_product_package_price.INDEPENDENT_PRICE
     *
     * @param independentPrice the value for pr_product_package_price.INDEPENDENT_PRICE
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public void setIndependentPrice(Double independentPrice) {
        this.independentPrice = independentPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pr_product_package_price.PRODUCT_ID
     *
     * @return the value of pr_product_package_price.PRODUCT_ID
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public String getProductId() {
        return productId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pr_product_package_price.PRODUCT_ID
     *
     * @param productId the value for pr_product_package_price.PRODUCT_ID
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column pr_product_package_price.GOURP_PRICE
     *
     * @return the value of pr_product_package_price.GOURP_PRICE
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public String getGourpPrice() {
        return gourpPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column pr_product_package_price.GOURP_PRICE
     *
     * @param gourpPrice the value for pr_product_package_price.GOURP_PRICE
     *
     * @mbggenerated Sat Aug 05 10:56:51 CST 2017
     */
    public void setGourpPrice(String gourpPrice) {
        this.gourpPrice = gourpPrice == null ? null : gourpPrice.trim();
    }
}