package com.zwo.xiyangyang.modules.mem.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.zwo.xiyangyang.modules.guess.domain.GuessAccount;

@Table(name = "mem_member")
public class MemMember implements java.io.Serializable {
	private List<GuessAccount> guessAccount;
	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
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

    @Column(name = "MEM_CATEGORY_ID")
    private String memCategoryId;

    /**
     * 昵称
     */
    @Column(name = "NICKNAME")
    private String nickname;

    /**
     * 微信的open_id
     */
    @Column(name = "OPEN_ID")
    private String openId;

    /**
     * 是否绑定微信
     */
    @Column(name = "BINDING_WECHAT")
    private Boolean bindingWechat;

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
        this.id = id;
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
        this.username = username;
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
        this.password = password;
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
        this.email = email;
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
        this.mobilPhone = mobilPhone;
    }

    /**
     * 获取是否禁用
     *
     * @return ENABLED - 是否禁用
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置是否禁用
     *
     * @param enabled 是否禁用
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
        this.icon = icon;
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
        this.qq = qq;
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
        this.weixin = weixin;
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
        this.realName = realName;
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
        this.orgId = orgId;
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
     * @return MEM_CATEGORY_ID
     */
    public String getMemCategoryId() {
        return memCategoryId;
    }

    /**
     * @param memCategoryId
     */
    public void setMemCategoryId(String memCategoryId) {
        this.memCategoryId = memCategoryId;
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
        this.nickname = nickname;
    }

    /**
     * 获取微信的open_id
     *
     * @return OPEN_ID - 微信的open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置微信的open_id
     *
     * @param openId 微信的open_id
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取是否绑定微信
     *
     * @return BINDING_WECHAT - 是否绑定微信
     */
    public Boolean getBindingWechat() {
        return bindingWechat;
    }

    /**
     * 设置是否绑定微信
     *
     * @param bindingWechat 是否绑定微信
     */
    public void setBindingWechat(Boolean bindingWechat) {
        this.bindingWechat = bindingWechat;
    }

	public List<GuessAccount> getGuessAccount() {
		return guessAccount;
	}

	public void setGuessAccount(List<GuessAccount> guessAccount) {
		this.guessAccount = guessAccount;
	}

}