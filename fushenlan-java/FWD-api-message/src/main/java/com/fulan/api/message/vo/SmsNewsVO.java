package com.fulan.api.message.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "SmsNewsVO", description = "消息池")
public class SmsNewsVO extends SmsPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号码", name = "phone")
    private String phone;

    @ApiModelProperty(value = "业务系统code", name = "systemCode")
    private String systemCode;

    @ApiModelProperty(value = "业务系统", name = "systemName")
    private String systemName;

    @ApiModelProperty(value = "通道code", name = "channelCode")
    private String channelCode;

    @ApiModelProperty(value = "通道", name = "channelName")
    private String channelName;

    @ApiModelProperty(value = "内容", name = "content")
    private String content;

    @ApiModelProperty(value = "发送状态", name = "status")
    private Long status;

    @ApiModelProperty(value = "消息类型", name = "type")
    private String type;

}