package com.fulan.api.message.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/3/21 10:03
 */
@Data
@Api(tags = "ReplyMessageVO", description = "用户回复消息回调")
public class ReplyMessageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 极光返回数据
     */
    @ApiModelProperty(value = "手机号码", name = "phone")
    private String phone;

    @ApiModelProperty(value = "回复时间", name = "replyTime")
    private Date replyTime;

    @ApiModelProperty(value = "回复内容", name = "content")
    private String content;

    /**
     * 移通返回数据
     */
    @ApiModelProperty(value = "操作命令 ", name = "command")
    private String command;

    @ApiModelProperty(value = "SP的ID", name = "spid")
    private String spid;

    @ApiModelProperty(value = "SP密码 ", name = "sppassword")
    private String sppassword;

    @ApiModelProperty(value = "MO消息ID ", name = "momsgid")
    private String momsgid;

    @ApiModelProperty(value = "源地址", name = "sa")
    private String sa;

    @ApiModelProperty(value = "目标地址 ", name = "da")
    private String da;

    @ApiModelProperty(value = "消息编码格式 ", name = "dc")
    private String dc;

    @ApiModelProperty(value = "消息内容", name = "sm")
    private String sm;
}