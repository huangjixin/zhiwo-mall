package com.zwo.modules.member.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "member_profit")
public class MemberProfit implements Serializable {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "PRODUCT_ID")
    private String productId;

    /**
     * 销售价
     */
    @Column(name = "SALE_PRICE")
    private Double salePrice;

    /**
     * 成交价
     */
    @Column(name = "DEAL_PRICE")
    private Double dealPrice;

    /**
     * 盈利
     */
    @Column(name = "PROFIT")
    private Double profit;

    /**
     * 分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     */
    @Column(name = "DISTRIBUTION_VALUE")
    private Double distributionValue;

    @Column(name = "REAL_PROFIT")
    private Double realProfit;

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
     * @return MEMBER_ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
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
     * 获取销售价
     *
     * @return SALE_PRICE - 销售价
     */
    public Double getSalePrice() {
        return salePrice;
    }

    /**
     * 设置销售价
     *
     * @param salePrice 销售价
     */
    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * 获取成交价
     *
     * @return DEAL_PRICE - 成交价
     */
    public Double getDealPrice() {
        return dealPrice;
    }

    /**
     * 设置成交价
     *
     * @param dealPrice 成交价
     */
    public void setDealPrice(Double dealPrice) {
        this.dealPrice = dealPrice;
    }

    /**
     * 获取盈利
     *
     * @return PROFIT - 盈利
     */
    public Double getProfit() {
        return profit;
    }

    /**
     * 设置盈利
     *
     * @param profit 盈利
     */
    public void setProfit(Double profit) {
        this.profit = profit;
    }

    /**
     * 获取分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     *
     * @return DISTRIBUTION_VALUE - 分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     */
    public Double getDistributionValue() {
        return distributionValue;
    }

    /**
     * 设置分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     *
     * @param distributionValue 分销让利值,是指该商品允许分销以后,销售出去给分销者的钱
     */
    public void setDistributionValue(Double distributionValue) {
        this.distributionValue = distributionValue;
    }

    /**
     * @return REAL_PROFIT
     */
    public Double getRealProfit() {
        return realProfit;
    }

    /**
     * @param realProfit
     */
    public void setRealProfit(Double realProfit) {
        this.realProfit = realProfit;
    }
}