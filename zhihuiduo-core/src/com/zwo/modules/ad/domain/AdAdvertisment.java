package com.zwo.modules.ad.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "ad_advertisment")
public class AdAdvertisment implements Serializable {
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
     * 广告位置ID
     */
    @Column(name = "AD_POSTION_ID")
    private String adPostionId;

    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 广告开始时间
     */
    @Column(name = "START_TIME")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "END_TIME")
    private Date endTime;

    /**
     * 上传广告图片
     */
    @Column(name = "ICON")
    private String icon;

    /**
     * 图片网址
     */
    @Column(name = "URL")
    private String url;

    /**
     * 是否禁用，0表示可用，1表示禁用
     */
    @Column(name = "DISABLED")
    private Integer disabled;

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
     * 获取广告位置ID
     *
     * @return AD_POSTION_ID - 广告位置ID
     */
    public String getAdPostionId() {
        return adPostionId;
    }

    /**
     * 设置广告位置ID
     *
     * @param adPostionId 广告位置ID
     */
    public void setAdPostionId(String adPostionId) {
        this.adPostionId = adPostionId == null ? null : adPostionId.trim();
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
     * 获取广告开始时间
     *
     * @return START_TIME - 广告开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置广告开始时间
     *
     * @param startTime 广告开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     *
     * @return END_TIME - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取上传广告图片
     *
     * @return ICON - 上传广告图片
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置上传广告图片
     *
     * @param icon 上传广告图片
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    /**
     * 获取图片网址
     *
     * @return URL - 图片网址
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置图片网址
     *
     * @param url 图片网址
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 获取是否禁用，0表示可用，1表示禁用
     *
     * @return DISABLED - 是否禁用，0表示可用，1表示禁用
     */
    public Integer getDisabled() {
        return disabled;
    }

    /**
     * 设置是否禁用，0表示可用，1表示禁用
     *
     * @param disabled 是否禁用，0表示可用，1表示禁用
     */
    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }
}