package com.zwo.modules.ad.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "ad_assets")
public class AdAssets implements Serializable {
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
     * 路径
     */
    @Column(name = "PATH")
    private String path;

    /**
     * 地址
     */
    @Column(name = "URL")
    private String url;

    /**
     * 广告的外键ID，它没有关联到广告，而是一个类似于傀儡，这样做的好处是。
     */
    @Column(name = "ADVERTISEMENT_ID")
    private String advertisementId;

    /**
     * 真实的外键
     */
    @Column(name = "REAL_ADVERTISEMENT_ID")
    private String realAdvertisementId;

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
     * 获取路径
     *
     * @return PATH - 路径
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置路径
     *
     * @param path 路径
     */
    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    /**
     * 获取地址
     *
     * @return URL - 地址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置地址
     *
     * @param url 地址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取广告的外键ID，它没有关联到广告，而是一个类似于傀儡，这样做的好处是。
     *
     * @return ADVERTISEMENT_ID - 广告的外键ID，它没有关联到广告，而是一个类似于傀儡，这样做的好处是。
     */
    public String getAdvertisementId() {
        return advertisementId;
    }

    /**
     * 设置广告的外键ID，它没有关联到广告，而是一个类似于傀儡，这样做的好处是。
     *
     * @param advertisementId 广告的外键ID，它没有关联到广告，而是一个类似于傀儡，这样做的好处是。
     */
    public void setAdvertisementId(String advertisementId) {
        this.advertisementId = advertisementId == null ? null : advertisementId.trim();
    }

    /**
     * 获取真实的外键
     *
     * @return REAL_ADVERTISEMENT_ID - 真实的外键
     */
    public String getRealAdvertisementId() {
        return realAdvertisementId;
    }

    /**
     * 设置真实的外键
     *
     * @param realAdvertisementId 真实的外键
     */
    public void setRealAdvertisementId(String realAdvertisementId) {
        this.realAdvertisementId = realAdvertisementId == null ? null : realAdvertisementId.trim();
    }
}