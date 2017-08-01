package com.zwo.modules.system.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "tb_user")
public class TbUser implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 用户名
     */
    @Column(name = "USERNAME")
    private String username;

    /**
     * 密码
     */
    @Column(name = "PASSWORD")
    private String password;

    /**
     * 登录日期
     */
    @Column(name = "LOGIN_DATE")
    private Date loginDate;

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
     * 上次登录日期
     */
    @Column(name = "LAST_LOGIN_DATE")
    private Date lastLoginDate;

    /**
     * 邮箱
     */
    @Column(name = "EMAIL")
    private String email;

    /**
     * 手机
     */
    @Column(name = "MOBIL_PHONE")
    private String mobilPhone;

    /**
     * 是否禁用
     */
    @Column(name = "DISABLE")
    private Boolean disable;

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
     * 性别
     */
    @Column(name = "SEX")
    private Boolean sex;

    /**
     * 头像
     */
    @Column(name = "ICON")
    private String icon;

    /**
     * 描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 身高
     */
    @Column(name = "AGE")
    private Integer age;

    /**
     * 体重
     */
    @Column(name = "WEIGHT")
    private Integer weight;

    /**
     * QQ
     */
    @Column(name = "QQ")
    private String qq;

    /**
     * 微信
     */
    @Column(name = "WEIXIN")
    private String weixin;

    /**
     * 实名
     */
    @Column(name = "REAL_NAME")
    private String realName;

    /**
     * 登录次数
     */
    @Column(name = "LOGIN_COUNT")
    private Integer loginCount;

    /**
     * 排序
     */
    @Column(name = "SORT")
    private Integer sort;

    /**
     * 组织结构表ID，该字段用于过滤数据，不做外键关联
     */
    @Column(name = "ORG_ID")
    private String orgId;

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

    @Column(name = "MEMBER_GROUP_ID")
    private String memberGroupId;

    /**
     * 昵称
     */
    @Column(name = "NICKNAME")
    private String nickname;

    /**
     * 登录名
     */
    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "USERGROUP_ID")
    private String usergroupId;

    /**
     * 紧急情况联系人
     */
    @Column(name = "EMERGECY_CONTACT")
    private String emergecyContact;

    /**
     * 是否是国内企业证件照,1表示是，0表示否
     */
    @Column(name = "IS_CERTIFICATE_INTERNATIONAL")
    private Boolean isCertificateInternational;

    /**
     * 身份证号码
     */
    @Column(name = "ID_CARD")
    private String idCard;

    /**
     * 公司名称
     */
    @Column(name = "COOP_NAME")
    private String coopName;

    /**
     * 公司经营地址
     */
    @Column(name = "COOP_ADDRESS")
    private String coopAddress;

    /**
     * 营业执照注册号
     */
    @Column(name = "BUSSINESS_LICENSE_CODE")
    private String bussinessLicenseCode;

    /**
     * 组织机构代码
     */
    @Column(name = "ORG_CODE")
    private String orgCode;

    /**
     * 纳税人识别码
     */
    @Column(name = "TAXPAYER")
    private String taxpayer;

    /**
     * 统一社会信用代码
     */
    @Column(name = "SOCIETY_CODE")
    private String societyCode;

    /**
     * 法定代表人身份证正面照片
     */
    @Column(name = "LEGAL_REPRESENTATIVE_CARD1")
    private String legalRepresentativeCard1;

    /**
     * 法定代表人身份证反面照片
     */
    @Column(name = "LEGAL_REPRESENTATIVE_CARD2")
    private String legalRepresentativeCard2;

    /**
     * 法定代表人身份证有效期
     */
    @Column(name = "LEGAL_IDCARD_EFFITIVE")
    private String legalIdcardEffitive;

    /**
     * 营业执照
     */
    @Column(name = "BUSSINESS_LICENSE_PIC")
    private String bussinessLicensePic;

    /**
     * 开户许可证
     */
    @Column(name = "LICENSE_FOR_OPENING_COUNT")
    private String licenseForOpeningCount;

    /**
     * 质检报告
     */
    @Column(name = "QUANTITY_REPORT_ID")
    private String quantityReportId;

    /**
     * 商户类型，cooperation表示企业类型，person表示个人
     */
    @Column(name = "TYPE")
    private String type;

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
     * 获取用户名
     *
     * @return USERNAME - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码
     *
     * @return PASSWORD - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取登录日期
     *
     * @return LOGIN_DATE - 登录日期
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * 设置登录日期
     *
     * @param loginDate 登录日期
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
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
     * 获取上次登录日期
     *
     * @return LAST_LOGIN_DATE - 上次登录日期
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * 设置上次登录日期
     *
     * @param lastLoginDate 上次登录日期
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * 获取邮箱
     *
     * @return EMAIL - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取手机
     *
     * @return MOBIL_PHONE - 手机
     */
    public String getMobilPhone() {
        return mobilPhone;
    }

    /**
     * 设置手机
     *
     * @param mobilPhone 手机
     */
    public void setMobilPhone(String mobilPhone) {
        this.mobilPhone = mobilPhone == null ? null : mobilPhone.trim();
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
     * 获取性别
     *
     * @return SEX - 性别
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * 获取头像
     *
     * @return ICON - 头像
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置头像
     *
     * @param icon 头像
     */
    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
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
     * 获取身高
     *
     * @return AGE - 身高
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置身高
     *
     * @param age 身高
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取体重
     *
     * @return WEIGHT - 体重
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * 设置体重
     *
     * @param weight 体重
     */
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    /**
     * 获取QQ
     *
     * @return QQ - QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ
     *
     * @param qq QQ
     */
    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    /**
     * 获取微信
     *
     * @return WEIXIN - 微信
     */
    public String getWeixin() {
        return weixin;
    }

    /**
     * 设置微信
     *
     * @param weixin 微信
     */
    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    /**
     * 获取实名
     *
     * @return REAL_NAME - 实名
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 设置实名
     *
     * @param realName 实名
     */
    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    /**
     * 获取登录次数
     *
     * @return LOGIN_COUNT - 登录次数
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * 设置登录次数
     *
     * @param loginCount 登录次数
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
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
     * @return MEMBER_GROUP_ID
     */
    public String getMemberGroupId() {
        return memberGroupId;
    }

    /**
     * @param memberGroupId
     */
    public void setMemberGroupId(String memberGroupId) {
        this.memberGroupId = memberGroupId == null ? null : memberGroupId.trim();
    }

    /**
     * 获取昵称
     *
     * @return NICKNAME - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取登录名
     *
     * @return LOGIN_NAME - 登录名
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名
     *
     * @param loginName 登录名
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * @return USERGROUP_ID
     */
    public String getUsergroupId() {
        return usergroupId;
    }

    /**
     * @param usergroupId
     */
    public void setUsergroupId(String usergroupId) {
        this.usergroupId = usergroupId == null ? null : usergroupId.trim();
    }

    /**
     * 获取紧急情况联系人
     *
     * @return EMERGECY_CONTACT - 紧急情况联系人
     */
    public String getEmergecyContact() {
        return emergecyContact;
    }

    /**
     * 设置紧急情况联系人
     *
     * @param emergecyContact 紧急情况联系人
     */
    public void setEmergecyContact(String emergecyContact) {
        this.emergecyContact = emergecyContact == null ? null : emergecyContact.trim();
    }

    /**
     * 获取是否是国内企业证件照,1表示是，0表示否
     *
     * @return IS_CERTIFICATE_INTERNATIONAL - 是否是国内企业证件照,1表示是，0表示否
     */
    public Boolean getIsCertificateInternational() {
        return isCertificateInternational;
    }

    /**
     * 设置是否是国内企业证件照,1表示是，0表示否
     *
     * @param isCertificateInternational 是否是国内企业证件照,1表示是，0表示否
     */
    public void setIsCertificateInternational(Boolean isCertificateInternational) {
        this.isCertificateInternational = isCertificateInternational;
    }

    /**
     * 获取身份证号码
     *
     * @return ID_CARD - 身份证号码
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置身份证号码
     *
     * @param idCard 身份证号码
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    /**
     * 获取公司名称
     *
     * @return COOP_NAME - 公司名称
     */
    public String getCoopName() {
        return coopName;
    }

    /**
     * 设置公司名称
     *
     * @param coopName 公司名称
     */
    public void setCoopName(String coopName) {
        this.coopName = coopName == null ? null : coopName.trim();
    }

    /**
     * 获取公司经营地址
     *
     * @return COOP_ADDRESS - 公司经营地址
     */
    public String getCoopAddress() {
        return coopAddress;
    }

    /**
     * 设置公司经营地址
     *
     * @param coopAddress 公司经营地址
     */
    public void setCoopAddress(String coopAddress) {
        this.coopAddress = coopAddress == null ? null : coopAddress.trim();
    }

    /**
     * 获取营业执照注册号
     *
     * @return BUSSINESS_LICENSE_CODE - 营业执照注册号
     */
    public String getBussinessLicenseCode() {
        return bussinessLicenseCode;
    }

    /**
     * 设置营业执照注册号
     *
     * @param bussinessLicenseCode 营业执照注册号
     */
    public void setBussinessLicenseCode(String bussinessLicenseCode) {
        this.bussinessLicenseCode = bussinessLicenseCode == null ? null : bussinessLicenseCode.trim();
    }

    /**
     * 获取组织机构代码
     *
     * @return ORG_CODE - 组织机构代码
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * 设置组织机构代码
     *
     * @param orgCode 组织机构代码
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    /**
     * 获取纳税人识别码
     *
     * @return TAXPAYER - 纳税人识别码
     */
    public String getTaxpayer() {
        return taxpayer;
    }

    /**
     * 设置纳税人识别码
     *
     * @param taxpayer 纳税人识别码
     */
    public void setTaxpayer(String taxpayer) {
        this.taxpayer = taxpayer == null ? null : taxpayer.trim();
    }

    /**
     * 获取统一社会信用代码
     *
     * @return SOCIETY_CODE - 统一社会信用代码
     */
    public String getSocietyCode() {
        return societyCode;
    }

    /**
     * 设置统一社会信用代码
     *
     * @param societyCode 统一社会信用代码
     */
    public void setSocietyCode(String societyCode) {
        this.societyCode = societyCode == null ? null : societyCode.trim();
    }

    /**
     * 获取法定代表人身份证正面照片
     *
     * @return LEGAL_REPRESENTATIVE_CARD1 - 法定代表人身份证正面照片
     */
    public String getLegalRepresentativeCard1() {
        return legalRepresentativeCard1;
    }

    /**
     * 设置法定代表人身份证正面照片
     *
     * @param legalRepresentativeCard1 法定代表人身份证正面照片
     */
    public void setLegalRepresentativeCard1(String legalRepresentativeCard1) {
        this.legalRepresentativeCard1 = legalRepresentativeCard1 == null ? null : legalRepresentativeCard1.trim();
    }

    /**
     * 获取法定代表人身份证反面照片
     *
     * @return LEGAL_REPRESENTATIVE_CARD2 - 法定代表人身份证反面照片
     */
    public String getLegalRepresentativeCard2() {
        return legalRepresentativeCard2;
    }

    /**
     * 设置法定代表人身份证反面照片
     *
     * @param legalRepresentativeCard2 法定代表人身份证反面照片
     */
    public void setLegalRepresentativeCard2(String legalRepresentativeCard2) {
        this.legalRepresentativeCard2 = legalRepresentativeCard2 == null ? null : legalRepresentativeCard2.trim();
    }

    /**
     * 获取法定代表人身份证有效期
     *
     * @return LEGAL_IDCARD_EFFITIVE - 法定代表人身份证有效期
     */
    public String getLegalIdcardEffitive() {
        return legalIdcardEffitive;
    }

    /**
     * 设置法定代表人身份证有效期
     *
     * @param legalIdcardEffitive 法定代表人身份证有效期
     */
    public void setLegalIdcardEffitive(String legalIdcardEffitive) {
        this.legalIdcardEffitive = legalIdcardEffitive == null ? null : legalIdcardEffitive.trim();
    }

    /**
     * 获取营业执照
     *
     * @return BUSSINESS_LICENSE_PIC - 营业执照
     */
    public String getBussinessLicensePic() {
        return bussinessLicensePic;
    }

    /**
     * 设置营业执照
     *
     * @param bussinessLicensePic 营业执照
     */
    public void setBussinessLicensePic(String bussinessLicensePic) {
        this.bussinessLicensePic = bussinessLicensePic == null ? null : bussinessLicensePic.trim();
    }

    /**
     * 获取开户许可证
     *
     * @return LICENSE_FOR_OPENING_COUNT - 开户许可证
     */
    public String getLicenseForOpeningCount() {
        return licenseForOpeningCount;
    }

    /**
     * 设置开户许可证
     *
     * @param licenseForOpeningCount 开户许可证
     */
    public void setLicenseForOpeningCount(String licenseForOpeningCount) {
        this.licenseForOpeningCount = licenseForOpeningCount == null ? null : licenseForOpeningCount.trim();
    }

    /**
     * 获取质检报告
     *
     * @return QUANTITY_REPORT_ID - 质检报告
     */
    public String getQuantityReportId() {
        return quantityReportId;
    }

    /**
     * 设置质检报告
     *
     * @param quantityReportId 质检报告
     */
    public void setQuantityReportId(String quantityReportId) {
        this.quantityReportId = quantityReportId == null ? null : quantityReportId.trim();
    }

    /**
     * 获取商户类型，cooperation表示企业类型，person表示个人
     *
     * @return TYPE - 商户类型，cooperation表示企业类型，person表示个人
     */
    public String getType() {
        return type;
    }

    /**
     * 设置商户类型，cooperation表示企业类型，person表示个人
     *
     * @param type 商户类型，cooperation表示企业类型，person表示个人
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }
}