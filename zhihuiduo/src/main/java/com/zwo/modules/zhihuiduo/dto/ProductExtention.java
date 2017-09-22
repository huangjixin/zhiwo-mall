package com.zwo.modules.zhihuiduo.dto;

import com.zwo.modules.mall.domain.PrProduct;

public class ProductExtention extends PrProduct {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String buyButtonText = "去参团";
	private String eventDesc = "2人团";
	private String soldQuantity = "15565件";

	public String getBuyButtonText() {
		return buyButtonText;
	}

	public void setBuyButtonText(String buyButtonText) {
		this.buyButtonText = buyButtonText;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public String getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(String soldQuantity) {
		this.soldQuantity = soldQuantity;
	}
}
