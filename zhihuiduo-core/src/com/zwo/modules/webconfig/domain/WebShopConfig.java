package com.zwo.modules.webconfig.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "web_shop_config")
public class WebShopConfig implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商店名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 商店标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 商店关键字
     */
    @Column(name = "KEY_WORLDS")
    private String keyWorlds;

    /**
     * 商店描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 客服电话
     */
    @Column(name = "TELEPHONE")
    private String telephone;

    @Column(name = "STATISTICS_CODE")
    private String statisticsCode;

    /**
     * 显示在商品详情页的每页用户评论数量,默认5
     */
    @Column(name = "NUMBER_COMMEMT")
    private Integer numberCommemt;

    /**
     * 商品首页列表分页的数量
     */
    @Column(name = "INDEX_PAGESIZE")
    private Integer indexPagesize;

    /**
     * 商品分类页列表的数量
     */
    @Column(name = "CATEGORY_PAGESIZE")
    private Integer categoryPagesize;

    /**
     * 设置微信端读取商品图片的路径,例如：http://您的域名
     */
    @Column(name = "PRO_IMAGE_PATH")
    private String proImagePath;

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
     * 获取商店名称
     *
     * @return NAME - 商店名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置商店名称
     *
     * @param name 商店名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取商店标题
     *
     * @return TITLE - 商店标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置商店标题
     *
     * @param title 商店标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取商店关键字
     *
     * @return KEY_WORLDS - 商店关键字
     */
    public String getKeyWorlds() {
        return keyWorlds;
    }

    /**
     * 设置商店关键字
     *
     * @param keyWorlds 商店关键字
     */
    public void setKeyWorlds(String keyWorlds) {
        this.keyWorlds = keyWorlds == null ? null : keyWorlds.trim();
    }

    /**
     * 获取商店描述
     *
     * @return DESCRIPTION - 商店描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置商店描述
     *
     * @param description 商店描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取客服电话
     *
     * @return TELEPHONE - 客服电话
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * 设置客服电话
     *
     * @param telephone 客服电话
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * @return STATISTICS_CODE
     */
    public String getStatisticsCode() {
        return statisticsCode;
    }

    /**
     * @param statisticsCode
     */
    public void setStatisticsCode(String statisticsCode) {
        this.statisticsCode = statisticsCode == null ? null : statisticsCode.trim();
    }

    /**
     * 获取显示在商品详情页的每页用户评论数量,默认5
     *
     * @return NUMBER_COMMEMT - 显示在商品详情页的每页用户评论数量,默认5
     */
    public Integer getNumberCommemt() {
        return numberCommemt;
    }

    /**
     * 设置显示在商品详情页的每页用户评论数量,默认5
     *
     * @param numberCommemt 显示在商品详情页的每页用户评论数量,默认5
     */
    public void setNumberCommemt(Integer numberCommemt) {
        this.numberCommemt = numberCommemt;
    }

    /**
     * 获取商品首页列表分页的数量
     *
     * @return INDEX_PAGESIZE - 商品首页列表分页的数量
     */
    public Integer getIndexPagesize() {
        return indexPagesize;
    }

    /**
     * 设置商品首页列表分页的数量
     *
     * @param indexPagesize 商品首页列表分页的数量
     */
    public void setIndexPagesize(Integer indexPagesize) {
        this.indexPagesize = indexPagesize;
    }

    /**
     * 获取商品分类页列表的数量
     *
     * @return CATEGORY_PAGESIZE - 商品分类页列表的数量
     */
    public Integer getCategoryPagesize() {
        return categoryPagesize;
    }

    /**
     * 设置商品分类页列表的数量
     *
     * @param categoryPagesize 商品分类页列表的数量
     */
    public void setCategoryPagesize(Integer categoryPagesize) {
        this.categoryPagesize = categoryPagesize;
    }

    /**
     * 获取设置微信端读取商品图片的路径,例如：http://您的域名
     *
     * @return PRO_IMAGE_PATH - 设置微信端读取商品图片的路径,例如：http://您的域名
     */
    public String getProImagePath() {
        return proImagePath;
    }

    /**
     * 设置设置微信端读取商品图片的路径,例如：http://您的域名
     *
     * @param proImagePath 设置微信端读取商品图片的路径,例如：http://您的域名
     */
    public void setProImagePath(String proImagePath) {
        this.proImagePath = proImagePath == null ? null : proImagePath.trim();
    }
}