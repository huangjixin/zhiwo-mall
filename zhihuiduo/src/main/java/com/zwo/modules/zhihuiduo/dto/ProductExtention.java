package com.zwo.modules.zhihuiduo.dto;

import java.util.List;

import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductPackagePrice;
import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
import com.zwo.modules.member.domain.GroupPurcse;
import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwo.modules.shop.domain.Shop;

public class ProductExtention extends PrProductWithBLOBs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String buyButtonText = "去参团";
	private String eventDesc = "2人团";
	private String soldQuantity = "15565";

	private List<PrProductProperty> properties;
	private List<PrProductPropertyValue> productPropertyValues;
	private List<PrProductPackagePrice> packagePrices;

	private GroupPurcse groupPurcse;
	private List<GroupPurcseMember> groupPurcseMembers;

	private List<GroupPurcse> groupPurcses;

	private List<PrProduct> goodsList;

	private List<PrImage> swpierImages;

	private Shop shop;
	
	public GroupPurcse getGroupPurcse() {
		return groupPurcse;
	}

	public void setGroupPurcse(GroupPurcse groupPurcse) {
		this.groupPurcse = groupPurcse;
	}

	public List<GroupPurcseMember> getGroupPurcseMembers() {
		return groupPurcseMembers;
	}

	public void setGroupPurcseMembers(List<GroupPurcseMember> groupPurcseMembers) {
		this.groupPurcseMembers = groupPurcseMembers;
	}

	public List<PrProductProperty> getProperties() {
		return properties;
	}

	public void setProperties(List<PrProductProperty> properties) {
		this.properties = properties;
	}

	public List<PrProductPropertyValue> getProductPropertyValues() {
		return productPropertyValues;
	}

	public void setProductPropertyValues(
			List<PrProductPropertyValue> productPropertyValues) {
		this.productPropertyValues = productPropertyValues;
	}

	public List<PrProductPackagePrice> getPackagePrices() {
		return packagePrices;
	}

	public void setPackagePrices(List<PrProductPackagePrice> packagePrices) {
		this.packagePrices = packagePrices;
	}

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

	public List<GroupPurcse> getGroupPurcses() {
		return groupPurcses;
	}

	public void setGroupPurcses(List<GroupPurcse> groupPurcses) {
		this.groupPurcses = groupPurcses;
	}

	public List<PrProduct> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<PrProduct> goodsList) {
		this.goodsList = goodsList;
	}

	public List<PrImage> getSwpierImages() {
		return swpierImages;
	}

	public void setSwpierImages(List<PrImage> swpierImages) {
		this.swpierImages = swpierImages;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
