package com.zwo.xiyangyang.modules.pr.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "pr_value_price")
public class PrValuePrice implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "VALUE_ID")
    private String valueId;

    /**
     * 联合VALUE_ID,是多个value_id的联合，例如123 1234 456。
     */
    @Column(name = "COMBINE_ID")
    private String combineId;

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

    /**
     * @return VALUE_ID
     */
    public String getValueId() {
        return valueId;
    }

    /**
     * @param valueId
     */
    public void setValueId(String valueId) {
        this.valueId = valueId == null ? null : valueId.trim();
    }

    /**
     * 获取联合VALUE_ID,是多个value_id的联合，例如123 1234 456。
     *
     * @return COMBINE_ID - 联合VALUE_ID,是多个value_id的联合，例如123 1234 456。
     */
    public String getCombineId() {
        return combineId;
    }

    /**
     * 设置联合VALUE_ID,是多个value_id的联合，例如123 1234 456。
     *
     * @param combineId 联合VALUE_ID,是多个value_id的联合，例如123 1234 456。
     */
    public void setCombineId(String combineId) {
        this.combineId = combineId == null ? null : combineId.trim();
    }
}