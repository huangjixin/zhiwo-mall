package com.zwo.modules.zhihuiduo.dto;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.zwo.modules.mall.domain.PrCategory;
import com.zwo.modules.mall.domain.PrProduct;
import com.zwo.modules.member.domain.GroupPurcse;

public class MainData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<PrCategory> categorys;
	private List<PrProduct> products;
	private List<GroupPurcse> groupPurcses;
	
	private PageInfo<ProductExtention> productExtentionPage;

	public List<PrCategory> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<PrCategory> categorys) {
		this.categorys = categorys;
	}

	public PageInfo<ProductExtention> getProductExtentionPage() {
		return productExtentionPage;
	}

	public void setProductExtentionPage(
			PageInfo<ProductExtention> productExtentionPage) {
		this.productExtentionPage = productExtentionPage;
	}

	public List<PrProduct> getProducts() {
		return products;
	}

	public void setProducts(List<PrProduct> products) {
		this.products = products;
	}

	public List<GroupPurcse> getGroupPurcses() {
		return groupPurcses;
	}

	public void setGroupPurcses(List<GroupPurcse> groupPurcses) {
		this.groupPurcses = groupPurcses;
	}

}
