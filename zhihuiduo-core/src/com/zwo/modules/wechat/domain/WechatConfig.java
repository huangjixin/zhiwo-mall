package com.zwo.modules.wechat.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "wechat_config")
public class WechatConfig implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 商店描述
     */
    @Column(name = "DESCRIPTION")
    private String description;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 此处“Token”内容与对接的微信公众号中的Token值一致。
     */
    @Column(name = "TOKEN")
    private String token;

    /**
     * 微信分配的公众账号ID 
     */
    @Column(name = "APPID")
    private String appid;

    /**
     * 微信密钥
     */
    @Column(name = "APPSECRET")
    private String appsecret;

    /**
     * 微信支付分配的商户号
     */
    @Column(name = "MCH_ID")
    private String mchId;

    /**
     * 后台access_token刷新时间，单位为秒
     */
    @Column(name = "REFRESH_TIME")
    private Integer refreshTime;

    /**
     * 关注回复内容
     */
    @Column(name = "ATTENDTION_REPLY")
    private String attendtionReply;

    /**
     * 绑定后回复内容回复内容
     */
    @Column(name = "BINDING_REPLY")
    private String bindingReply;

    /**
     * 自动回复
     */
    @Column(name = "AUTO_REPLY")
    private String autoReply;

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
     * 获取此处“Token”内容与对接的微信公众号中的Token值一致。
     *
     * @return TOKEN - 此处“Token”内容与对接的微信公众号中的Token值一致。
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置此处“Token”内容与对接的微信公众号中的Token值一致。
     *
     * @param token 此处“Token”内容与对接的微信公众号中的Token值一致。
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * 获取微信分配的公众账号ID 
     *
     * @return APPID - 微信分配的公众账号ID 
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 设置微信分配的公众账号ID 
     *
     * @param appid 微信分配的公众账号ID 
     */
    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    /**
     * 获取微信密钥
     *
     * @return APPSECRET - 微信密钥
     */
    public String getAppsecret() {
        return appsecret;
    }

    /**
     * 设置微信密钥
     *
     * @param appsecret 微信密钥
     */
    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret == null ? null : appsecret.trim();
    }

    /**
     * 获取微信支付分配的商户号
     *
     * @return MCH_ID - 微信支付分配的商户号
     */
    public String getMchId() {
        return mchId;
    }

    /**
     * 设置微信支付分配的商户号
     *
     * @param mchId 微信支付分配的商户号
     */
    public void setMchId(String mchId) {
        this.mchId = mchId == null ? null : mchId.trim();
    }

    /**
     * 获取后台access_token刷新时间，单位为秒
     *
     * @return REFRESH_TIME - 后台access_token刷新时间，单位为秒
     */
    public Integer getRefreshTime() {
        return refreshTime;
    }

    /**
     * 设置后台access_token刷新时间，单位为秒
     *
     * @param refreshTime 后台access_token刷新时间，单位为秒
     */
    public void setRefreshTime(Integer refreshTime) {
        this.refreshTime = refreshTime;
    }

    /**
     * 获取关注回复内容
     *
     * @return ATTENDTION_REPLY - 关注回复内容
     */
    public String getAttendtionReply() {
        return attendtionReply;
    }

    /**
     * 设置关注回复内容
     *
     * @param attendtionReply 关注回复内容
     */
    public void setAttendtionReply(String attendtionReply) {
        this.attendtionReply = attendtionReply == null ? null : attendtionReply.trim();
    }

    /**
     * 获取绑定后回复内容回复内容
     *
     * @return BINDING_REPLY - 绑定后回复内容回复内容
     */
    public String getBindingReply() {
        return bindingReply;
    }

    /**
     * 设置绑定后回复内容回复内容
     *
     * @param bindingReply 绑定后回复内容回复内容
     */
    public void setBindingReply(String bindingReply) {
        this.bindingReply = bindingReply == null ? null : bindingReply.trim();
    }

    /**
     * 获取自动回复
     *
     * @return AUTO_REPLY - 自动回复
     */
    public String getAutoReply() {
        return autoReply;
    }

    /**
     * 设置自动回复
     *
     * @param autoReply 自动回复
     */
    public void setAutoReply(String autoReply) {
        this.autoReply = autoReply == null ? null : autoReply.trim();
    }
}