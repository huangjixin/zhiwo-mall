package com.zwo.modules.mall.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "pr_product_property")
public class PrProductProperty implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "PRODUCT_ID")
    private String productId;

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
        this.name = name == null ? null : name.trim();
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
        this.code = code == null ? null : code.trim();
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
        this.description = description == null ? null : description.trim();
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
        this.productId = productId == null ? null : productId.trim();
    }
}