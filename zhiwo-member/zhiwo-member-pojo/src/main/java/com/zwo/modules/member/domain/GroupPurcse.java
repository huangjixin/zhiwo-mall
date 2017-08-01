package com.zwo.modules.member.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "group_purcse")
public class GroupPurcse implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 创建日期
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /**
     * 更新日期
     */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    /**
     * 是否禁用
     */
    @Column(name = "DISABLE")
    private Boolean disable;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 会员ID
     */
    @Column(name = "MEMEBER_ID")
    private String memeberId;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    @Column(name = "PRODUCT_ID")
    private String productId;

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
     * 获取创建日期
     *
     * @return CREATE_DATE - 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取更新日期
     *
     * @return UPDATE_DATE - 更新日期
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置更新日期
     *
     * @param updateDate 更新日期
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取是否禁用
     *
     * @return DISABLE - 是否禁用
     */
    public Boolean getDisable() {
        return disable;
    }

    /**
     * 设置是否禁用
     *
     * @param disable 是否禁用
     */
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    /**
     * 获取描述
     *
     * @return DESCRIPTION - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取会员ID
     *
     * @return MEMEBER_ID - 会员ID
     */
    public String getMemeberId() {
        return memeberId;
    }

    /**
     * 设置会员ID
     *
     * @param memeberId 会员ID
     */
    public void setMemeberId(String memeberId) {
        this.memeberId = memeberId == null ? null : memeberId.trim();
    }

    /**
     * 获取排序
     *
     * @return SORT - 排序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序
     *
     * @param sort 排序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
}