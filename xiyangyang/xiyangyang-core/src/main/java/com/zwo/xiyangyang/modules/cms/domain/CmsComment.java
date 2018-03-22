package com.zwo.xiyangyang.modules.cms.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cms_comment")
public class CmsComment {
    /**
     * id标志符
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    @Column(name = "MEMBER_ID")
    private String memberId;

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
     * 是否可用，0为可用，1表示不可用
     */
    @Column(name = "ENABLED")
    private Boolean enabled;

    /**
     * 创建人
     */
    @Column(name = "CREATOR")
    private String creator;

    /**
     * 更新人
     */
    @Column(name = "UPDATER")
    private String updater;

    /**
     * 路径
     */
    @Column(name = "PATH")
    private String path;

    /**
     * 图标
     */
    @Column(name = "ICON")
    private String icon;

    /**
     * 父类ID
     */
    @Column(name = "PARENT_ID")
    private String parentId;

    /**
     * 父类IDS
     */
    @Column(name = "PARENTIDS")
    private String parentids;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    @Column(name = "CMS_DOCUMENT_ID")
    private String cmsDocumentId;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "LEVEL")
    private Integer level;

    /**
     * 获取id标志符
     *
     * @return ID - id标志符
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id标志符
     *
     * @param id id标志符
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取组织结构表ID，该字段用于过滤数据，不做外键关联
     *
     * @return MEMBER_ID - 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置组织结构表ID，该字段用于过滤数据，不做外键关联
     *
     * @param memberId 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
     * 获取是否可用，0为可用，1表示不可用
     *
     * @return ENABLED - 是否可用，0为可用，1表示不可用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用，0为可用，1表示不可用
     *
     * @param enabled 是否可用，0为可用，1表示不可用
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * 获取创建人
     *
     * @return CREATOR - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取更新人
     *
     * @return UPDATER - 更新人
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * 设置更新人
     *
     * @param updater 更新人
     */
    public void setUpdater(String updater) {
        this.updater = updater;
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
        this.path = path;
    }

    /**
     * 获取图标
     *
     * @return ICON - 图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取父类ID
     *
     * @return PARENT_ID - 父类ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置父类ID
     *
     * @param parentId 父类ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取父类IDS
     *
     * @return PARENTIDS - 父类IDS
     */
    public String getParentids() {
        return parentids;
    }

    /**
     * 设置父类IDS
     *
     * @param parentids 父类IDS
     */
    public void setParentids(String parentids) {
        this.parentids = parentids;
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
     * @return CMS_DOCUMENT_ID
     */
    public String getCmsDocumentId() {
        return cmsDocumentId;
    }

    /**
     * @param cmsDocumentId
     */
    public void setCmsDocumentId(String cmsDocumentId) {
        this.cmsDocumentId = cmsDocumentId;
    }

    /**
     * @return CONTENT
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return LEVEL
     */
    public Integer getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(Integer level) {
        this.level = level;
    }
}