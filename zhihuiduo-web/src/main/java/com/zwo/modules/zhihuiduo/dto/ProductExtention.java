package com.zwo.modules.zhihuiduo.dto;

import java.util.List;

import com.github.pagehelper.DatagridPage;
import com.zwo.modules.mall.domain.OrderTrade;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.mall.domain.PrProductPackagePrice;
import com.zwo.modules.mall.domain.PrProductProperty;
import com.zwo.modules.mall.domain.PrProductPropertyValue;
import com.zwo.modules.mall.domain.PrProductWithBLOBs;
import com.zwo.modules.member.domain.GroupPurcse;
import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwo.modules.member.domain.Member;
import com.zwo.modules.member.domain.MemberAddress;
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
	
	//用于下单
	private Integer buyNum;
	private String packagePriceId;
	private String proValues;
	private String dealPrice;
	private String mode;
	private String groupPurcseId;
	
	//用于支付
	private MemberAddress defautAddress;
	
	//用于地址列表编辑或者支付的地址列表
	private List<MemberAddress> memberAddresses;

	//用于支付的订单。
	private OrderTrade order;
	
	private Member member;
	
	private DatagridPage<PrProduct> productPage;
	
	
	//首页轮播图。
	private List indexSwipers;
	
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

	public MemberAddress getDefautAddress() {
		return defautAddress;
	}

	public void setDefautAddress(MemberAddress defautAddress) {
		this.defautAddress = defautAddress;
	}

	public List<MemberAddress> getMemberAddresses() {
		return memberAddresses;
	}

	public void setMemberAddresses(List<MemberAddress> memberAddresses) {
		this.memberAddresses = memberAddresses;
	}

	public OrderTrade getOrder() {
		return order;
	}

	public void setOrder(OrderTrade order) {
		this.order = order;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public String getPackagePriceId() {
		return packagePriceId;
	}

	public void setPackagePriceId(String packagePriceId) {
		this.packagePriceId = packagePriceId;
	}

	public String getProValues() {
		return proValues;
	}

	public void setProValues(String proValues) {
		this.proValues = proValues;
	}

	public String getDealPrice() {
		return dealPrice;
	}

	public void setDealPrice(String dealPrice) {
		this.dealPrice = dealPrice;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getGroupPurcseId() {
		return groupPurcseId;
	}

	public void setGroupPurcseId(String groupPurcseId) {
		this.groupPurcseId = groupPurcseId;
	}

	public DatagridPage<PrProduct> getProductPage() {
		return productPage;
	}

	public void setProductPage(DatagridPage<PrProduct> productPage) {
		this.productPage = productPage;
	}

	public List getIndexSwipers() {
		return indexSwipers;
	}

	public void setIndexSwipers(List indexSwipers) {
		this.indexSwipers = indexSwipers;
	}
}
