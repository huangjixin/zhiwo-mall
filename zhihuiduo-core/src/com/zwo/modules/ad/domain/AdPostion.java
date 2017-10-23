package com.zwo.modules.ad.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "ad_postion")
public class AdPostion implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    @Column(name = "SORT")
    private Integer sort;

    /**
     * 商品种类ID
     */
    @Column(name = "PR_CATEGORY_ID")
    private String prCategoryId;

    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 广告类型先分两种，一种是头部轮播图，值为0，一种是插入滚动图，值为1，每个种类头部轮播图只有一个，但是滚动图有多个
     */
    @Column(name = "TYPE")
    private Integer type;

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
     * 获取名称
     *
     * @return NAME - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return SORT
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取商品种类ID
     *
     * @return PR_CATEGORY_ID - 商品种类ID
     */
    public String getPrCategoryId() {
        return prCategoryId;
    }

    /**
     * 设置商品种类ID
     *
     * @param prCategoryId 商品种类ID
     */
    public void setPrCategoryId(String prCategoryId) {
        this.prCategoryId = prCategoryId == null ? null : prCategoryId.trim();
    }

    /**
     * @return DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取广告类型先分两种，一种是头部轮播图，值为0，一种是插入滚动图，值为1，每个种类头部轮播图只有一个，但是滚动图有多个
     *
     * @return TYPE - 广告类型先分两种，一种是头部轮播图，值为0，一种是插入滚动图，值为1，每个种类头部轮播图只有一个，但是滚动图有多个
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置广告类型先分两种，一种是头部轮播图，值为0，一种是插入滚动图，值为1，每个种类头部轮播图只有一个，但是滚动图有多个
     *
     * @param type 广告类型先分两种，一种是头部轮播图，值为0，一种是插入滚动图，值为1，每个种类头部轮播图只有一个，但是滚动图有多个
     */
    public void setType(Integer type) {
        this.type = type;
    }
}