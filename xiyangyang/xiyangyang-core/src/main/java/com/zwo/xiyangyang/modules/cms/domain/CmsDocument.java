package com.zwo.xiyangyang.modules.cms.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cms_document")
public class CmsDocument {
    /**
     * id标志符
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 用户ID，不做外键关联
     */
    @Column(name = "USER_ID")
    private String userId;

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
     * 是否为专题
     */
    @Column(name = "IS_TOOIC")
    private Boolean isTooic;

    /**
     * 是否可用
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
     * 代码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 关键字
     */
    @Column(name = "KEYWORDS")
    private String keywords;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 缩略图
     */
    @Column(name = "THUMBNAIL")
    private String thumbnail;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 频道模板路径
     */
    @Column(name = "CHANNEL_TEMPLATE")
    private String channelTemplate;

    /**
     * 移动频道模板路径
     */
    @Column(name = "MCHANNEL_TEMPLATE")
    private String mchannelTemplate;

    /**
     * 英文标题
     */
    @Column(name = "TITLE_EN")
    private String titleEn;

    /**
     * 标题
     */
    @Column(name = "TITLE")
    private String title;

    /**
     * 英文副标题
     */
    @Column(name = "SUB_TITLE_EN")
    private String subTitleEn;

    /**
     * 副标题
     */
    @Column(name = "SUB_TITLE")
    private String subTitle;

    /**
     * 是否推荐到首页
     */
    @Column(name = "TO_INDEX")
    private Boolean toIndex;

    /**
     * 是否推荐到频道首页
     */
    @Column(name = "TO_CHANNEL_INDEX")
    private Boolean toChannelIndex;

    @Column(name = "CMS_CATEGORY_ID")
    private String cmsCategoryId;

    /**
     * 作者
     */
    @Column(name = "AUTHOR")
    private String author;

    /**
     * 内容
     */
    @Column(name = "CONTENT")
    private String content;

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
        this.name = name;
    }

    /**
     * 获取用户ID，不做外键关联
     *
     * @return USER_ID - 用户ID，不做外键关联
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID，不做外键关联
     *
     * @param userId 用户ID，不做外键关联
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * 获取是否为专题
     *
     * @return IS_TOOIC - 是否为专题
     */
    public Boolean getIsTooic() {
        return isTooic;
    }

    /**
     * 设置是否为专题
     *
     * @param isTooic 是否为专题
     */
    public void setIsTooic(Boolean isTooic) {
        this.isTooic = isTooic;
    }

    /**
     * 获取是否可用
     *
     * @return ENABLED - 是否可用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否可用
     *
     * @param enabled 是否可用
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
     * 获取代码
     *
     * @return CODE - 代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置代码
     *
     * @param code 代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取关键字
     *
     * @return KEYWORDS - 关键字
     */
    public String getKeywords() {
        return keywords;
    }

    /**
     * 设置关键字
     *
     * @param keywords 关键字
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
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
        this.description = description;
    }

    /**
     * 获取缩略图
     *
     * @return THUMBNAIL - 缩略图
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 设置缩略图
     *
     * @param thumbnail 缩略图
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
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
     * 获取频道模板路径
     *
     * @return CHANNEL_TEMPLATE - 频道模板路径
     */
    public String getChannelTemplate() {
        return channelTemplate;
    }

    /**
     * 设置频道模板路径
     *
     * @param channelTemplate 频道模板路径
     */
    public void setChannelTemplate(String channelTemplate) {
        this.channelTemplate = channelTemplate;
    }

    /**
     * 获取移动频道模板路径
     *
     * @return MCHANNEL_TEMPLATE - 移动频道模板路径
     */
    public String getMchannelTemplate() {
        return mchannelTemplate;
    }

    /**
     * 设置移动频道模板路径
     *
     * @param mchannelTemplate 移动频道模板路径
     */
    public void setMchannelTemplate(String mchannelTemplate) {
        this.mchannelTemplate = mchannelTemplate;
    }

    /**
     * 获取英文标题
     *
     * @return TITLE_EN - 英文标题
     */
    public String getTitleEn() {
        return titleEn;
    }

    /**
     * 设置英文标题
     *
     * @param titleEn 英文标题
     */
    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    /**
     * 获取标题
     *
     * @return TITLE - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取英文副标题
     *
     * @return SUB_TITLE_EN - 英文副标题
     */
    public String getSubTitleEn() {
        return subTitleEn;
    }

    /**
     * 设置英文副标题
     *
     * @param subTitleEn 英文副标题
     */
    public void setSubTitleEn(String subTitleEn) {
        this.subTitleEn = subTitleEn;
    }

    /**
     * 获取副标题
     *
     * @return SUB_TITLE - 副标题
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 设置副标题
     *
     * @param subTitle 副标题
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 获取是否推荐到首页
     *
     * @return TO_INDEX - 是否推荐到首页
     */
    public Boolean getToIndex() {
        return toIndex;
    }

    /**
     * 设置是否推荐到首页
     *
     * @param toIndex 是否推荐到首页
     */
    public void setToIndex(Boolean toIndex) {
        this.toIndex = toIndex;
    }

    /**
     * 获取是否推荐到频道首页
     *
     * @return TO_CHANNEL_INDEX - 是否推荐到频道首页
     */
    public Boolean getToChannelIndex() {
        return toChannelIndex;
    }

    /**
     * 设置是否推荐到频道首页
     *
     * @param toChannelIndex 是否推荐到频道首页
     */
    public void setToChannelIndex(Boolean toChannelIndex) {
        this.toChannelIndex = toChannelIndex;
    }

    /**
     * @return CMS_CATEGORY_ID
     */
    public String getCmsCategoryId() {
        return cmsCategoryId;
    }

    /**
     * @param cmsCategoryId
     */
    public void setCmsCategoryId(String cmsCategoryId) {
        this.cmsCategoryId = cmsCategoryId;
    }

    /**
     * 获取作者
     *
     * @return AUTHOR - 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置作者
     *
     * @param author 作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取内容
     *
     * @return CONTENT - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}