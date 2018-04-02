package com.zwo.xiyangyang.modules.pr.domain;

import java.util.List;

import javax.persistence.*;

@Table(name = "pr_value_price")
public class PrValuePrice implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
	
	@Transient
	private PrProduct product;
	
	@Transient
	private PrPropertyValue propertyValue;
	
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PRODUCT_ID")
    private String productId;

    @Column(name = "VALUE_ID")
    private String valueId;


    @Transient
    private List<PrPrice> prices;
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
        this.valueId = valueId;
    }

	public PrProduct getProduct() {
		return product;
	}

	public void setProduct(PrProduct product) {
		this.product = product;
	}

	public PrPropertyValue getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(PrPropertyValue propertyValue) {
		this.propertyValue = propertyValue;
	}

	public List<PrPrice> getPrices() {
		return prices;
	}

	public void setPrices(List<PrPrice> prices) {
		this.prices = prices;
	}
}