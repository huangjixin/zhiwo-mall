package com.zwo.modules.wechat.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "wechat_remind")
public class WechatRemind implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 下单微信提醒,1是，0否
     */
    @Column(name = "IF_REMIND_ON_ORDER")
    private Integer ifRemindOnOrder;

    /**
     * 下单微信内容,例如恭喜你参团成功！
     */
    @Column(name = "REMIND_CONTENT")
    private String remindContent;

    /**
     * 发货微信提醒,1是，0否
     */
    @Column(name = "IF_REMIND_ON_SEND")
    private Integer ifRemindOnSend;

    /**
     * 发货微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     */
    @Column(name = "REMIND_CONTENT_SEND")
    private String remindContentSend;

    /**
     * 订单取消微信提醒,1是，0否
     */
    @Column(name = "IF_REMIND_ON_CANCEL")
    private Integer ifRemindOnCancel;

    /**
     * 订单取消，微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     */
    @Column(name = "REMIND_CONTENT_CANCEL")
    private String remindContentCancel;

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
     * 获取下单微信提醒,1是，0否
     *
     * @return IF_REMIND_ON_ORDER - 下单微信提醒,1是，0否
     */
    public Integer getIfRemindOnOrder() {
        return ifRemindOnOrder;
    }

    /**
     * 设置下单微信提醒,1是，0否
     *
     * @param ifRemindOnOrder 下单微信提醒,1是，0否
     */
    public void setIfRemindOnOrder(Integer ifRemindOnOrder) {
        this.ifRemindOnOrder = ifRemindOnOrder;
    }

    /**
     * 获取下单微信内容,例如恭喜你参团成功！
     *
     * @return REMIND_CONTENT - 下单微信内容,例如恭喜你参团成功！
     */
    public String getRemindContent() {
        return remindContent;
    }

    /**
     * 设置下单微信内容,例如恭喜你参团成功！
     *
     * @param remindContent 下单微信内容,例如恭喜你参团成功！
     */
    public void setRemindContent(String remindContent) {
        this.remindContent = remindContent == null ? null : remindContent.trim();
    }

    /**
     * 获取发货微信提醒,1是，0否
     *
     * @return IF_REMIND_ON_SEND - 发货微信提醒,1是，0否
     */
    public Integer getIfRemindOnSend() {
        return ifRemindOnSend;
    }

    /**
     * 设置发货微信提醒,1是，0否
     *
     * @param ifRemindOnSend 发货微信提醒,1是，0否
     */
    public void setIfRemindOnSend(Integer ifRemindOnSend) {
        this.ifRemindOnSend = ifRemindOnSend;
    }

    /**
     * 获取发货微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     *
     * @return REMIND_CONTENT_SEND - 发货微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     */
    public String getRemindContentSend() {
        return remindContentSend;
    }

    /**
     * 设置发货微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     *
     * @param remindContentSend 发货微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     */
    public void setRemindContentSend(String remindContentSend) {
        this.remindContentSend = remindContentSend == null ? null : remindContentSend.trim();
    }

    /**
     * 获取订单取消微信提醒,1是，0否
     *
     * @return IF_REMIND_ON_CANCEL - 订单取消微信提醒,1是，0否
     */
    public Integer getIfRemindOnCancel() {
        return ifRemindOnCancel;
    }

    /**
     * 设置订单取消微信提醒,1是，0否
     *
     * @param ifRemindOnCancel 订单取消微信提醒,1是，0否
     */
    public void setIfRemindOnCancel(Integer ifRemindOnCancel) {
        this.ifRemindOnCancel = ifRemindOnCancel;
    }

    /**
     * 获取订单取消，微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     *
     * @return REMIND_CONTENT_CANCEL - 订单取消，微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     */
    public String getRemindContentCancel() {
        return remindContentCancel;
    }

    /**
     * 设置订单取消，微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     *
     * @param remindContentCancel 订单取消，微信内容,例如您购买的商品（订单号：$order_sn）已经发货请注意查收！快递号：$shoping_code
     */
    public void setRemindContentCancel(String remindContentCancel) {
        this.remindContentCancel = remindContentCancel == null ? null : remindContentCancel.trim();
    }
}