package com.zwo.modules.cms.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "cms_channel")
public class CmsChannel implements Serializable {
    /**
     * id标志符
     */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 英文名称
     */
    @Column(name = "EN_NAME")
    private String enName;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    @Column(name = "ORG_ID")
    private String orgId;

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
    @Column(name = "IS_DISABLE")
    private Boolean isDisable;

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
     * JSP模板路径
     */
    @Column(name = "JSP_TEMPLATE")
    private String jspTemplate;

    /**
     * 手机端JSP模板
     */
    @Column(name = "MOBILE_JSP_TEMPLATE")
    private String mobileJspTemplate;

    /**
     * FREEMARKER静态模板
     */
    @Column(name = "FREEMARKER_TEMPLATE")
    private String freemarkerTemplate;

    /**
     * 手机端FREEMARKER静态模板
     */
    @Column(name = "MOBILE_FREEMARKER_TEMPLATE")
    private String mobileFreemarkerTemplate;

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
     * 文档JSP模板路径
     */
    @Column(name = "DOC_JSP_TEMPLATE")
    private String docJspTemplate;

    /**
     * 文档手机端JSP模板路基
     */
    @Column(name = "DOC_MOBILE__JSP_TEMPLATE")
    private String docMobileJspTemplate;

    /**
     * 文档FREEMARKER静态模板
     */
    @Column(name = "DOC_FREEMARKER_TEMPLATE")
    private String docFreemarkerTemplate;

    /**
     * 文档手机端FREEMARKER静态模板
     */
    @Column(name = "DOC_MOBILE_FREEMARKER_TEMPLATE")
    private String docMobileFreemarkerTemplate;

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

    private static final long serialVersionUID = 1L;

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
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取英文名称
     *
     * @return EN_NAME - 英文名称
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置英文名称
     *
     * @param enName 英文名称
     */
    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
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
     * 获取组织结构表ID，该字段用于过滤数据，不做外键关联
     *
     * @return ORG_ID - 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置组织结构表ID，该字段用于过滤数据，不做外键关联
     *
     * @param orgId 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
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
     * @return IS_DISABLE - 是否可用
     */
    public Boolean getIsDisable() {
        return isDisable;
    }

    /**
     * 设置是否可用
     *
     * @param isDisable 是否可用
     */
    public void setIsDisable(Boolean isDisable) {
        this.isDisable = isDisable;
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
        this.creator = creator == null ? null : creator.trim();
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
        this.updater = updater == null ? null : updater.trim();
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
     * 获取JSP模板路径
     *
     * @return JSP_TEMPLATE - JSP模板路径
     */
    public String getJspTemplate() {
        return jspTemplate;
    }

    /**
     * 设置JSP模板路径
     *
     * @param jspTemplate JSP模板路径
     */
    public void setJspTemplate(String jspTemplate) {
        this.jspTemplate = jspTemplate == null ? null : jspTemplate.trim();
    }

    /**
     * 获取手机端JSP模板
     *
     * @return MOBILE_JSP_TEMPLATE - 手机端JSP模板
     */
    public String getMobileJspTemplate() {
        return mobileJspTemplate;
    }

    /**
     * 设置手机端JSP模板
     *
     * @param mobileJspTemplate 手机端JSP模板
     */
    public void setMobileJspTemplate(String mobileJspTemplate) {
        this.mobileJspTemplate = mobileJspTemplate == null ? null : mobileJspTemplate.trim();
    }

    /**
     * 获取FREEMARKER静态模板
     *
     * @return FREEMARKER_TEMPLATE - FREEMARKER静态模板
     */
    public String getFreemarkerTemplate() {
        return freemarkerTemplate;
    }

    /**
     * 设置FREEMARKER静态模板
     *
     * @param freemarkerTemplate FREEMARKER静态模板
     */
    public void setFreemarkerTemplate(String freemarkerTemplate) {
        this.freemarkerTemplate = freemarkerTemplate == null ? null : freemarkerTemplate.trim();
    }

    /**
     * 获取手机端FREEMARKER静态模板
     *
     * @return MOBILE_FREEMARKER_TEMPLATE - 手机端FREEMARKER静态模板
     */
    public String getMobileFreemarkerTemplate() {
        return mobileFreemarkerTemplate;
    }

    /**
     * 设置手机端FREEMARKER静态模板
     *
     * @param mobileFreemarkerTemplate 手机端FREEMARKER静态模板
     */
    public void setMobileFreemarkerTemplate(String mobileFreemarkerTemplate) {
        this.mobileFreemarkerTemplate = mobileFreemarkerTemplate == null ? null : mobileFreemarkerTemplate.trim();
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
        this.icon = icon == null ? null : icon.trim();
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
        this.parentId = parentId == null ? null : parentId.trim();
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
        this.parentids = parentids == null ? null : parentids.trim();
    }

    /**
     * 获取文档JSP模板路径
     *
     * @return DOC_JSP_TEMPLATE - 文档JSP模板路径
     */
    public String getDocJspTemplate() {
        return docJspTemplate;
    }

    /**
     * 设置文档JSP模板路径
     *
     * @param docJspTemplate 文档JSP模板路径
     */
    public void setDocJspTemplate(String docJspTemplate) {
        this.docJspTemplate = docJspTemplate == null ? null : docJspTemplate.trim();
    }

    /**
     * 获取文档手机端JSP模板路基
     *
     * @return DOC_MOBILE__JSP_TEMPLATE - 文档手机端JSP模板路基
     */
    public String getDocMobileJspTemplate() {
        return docMobileJspTemplate;
    }

    /**
     * 设置文档手机端JSP模板路基
     *
     * @param docMobileJspTemplate 文档手机端JSP模板路基
     */
    public void setDocMobileJspTemplate(String docMobileJspTemplate) {
        this.docMobileJspTemplate = docMobileJspTemplate == null ? null : docMobileJspTemplate.trim();
    }

    /**
     * 获取文档FREEMARKER静态模板
     *
     * @return DOC_FREEMARKER_TEMPLATE - 文档FREEMARKER静态模板
     */
    public String getDocFreemarkerTemplate() {
        return docFreemarkerTemplate;
    }

    /**
     * 设置文档FREEMARKER静态模板
     *
     * @param docFreemarkerTemplate 文档FREEMARKER静态模板
     */
    public void setDocFreemarkerTemplate(String docFreemarkerTemplate) {
        this.docFreemarkerTemplate = docFreemarkerTemplate == null ? null : docFreemarkerTemplate.trim();
    }

    /**
     * 获取文档手机端FREEMARKER静态模板
     *
     * @return DOC_MOBILE_FREEMARKER_TEMPLATE - 文档手机端FREEMARKER静态模板
     */
    public String getDocMobileFreemarkerTemplate() {
        return docMobileFreemarkerTemplate;
    }

    /**
     * 设置文档手机端FREEMARKER静态模板
     *
     * @param docMobileFreemarkerTemplate 文档手机端FREEMARKER静态模板
     */
    public void setDocMobileFreemarkerTemplate(String docMobileFreemarkerTemplate) {
        this.docMobileFreemarkerTemplate = docMobileFreemarkerTemplate == null ? null : docMobileFreemarkerTemplate.trim();
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
        this.code = code == null ? null : code.trim();
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
        this.keywords = keywords == null ? null : keywords.trim();
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
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
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
        this.channelTemplate = channelTemplate == null ? null : channelTemplate.trim();
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
        this.mchannelTemplate = mchannelTemplate == null ? null : mchannelTemplate.trim();
    }
}