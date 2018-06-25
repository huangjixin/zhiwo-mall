package com.fulan.api.message.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "SmsSystemChannelVO", description = "短信业务系统通道关联")
public class SmsSystemChannelVO extends SmsPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信业务系统名称", name = "systemName")
    private String  systemName;

    @ApiModelProperty(value = "短信通道名称", name = "channelName")
    private Long channelName;

}