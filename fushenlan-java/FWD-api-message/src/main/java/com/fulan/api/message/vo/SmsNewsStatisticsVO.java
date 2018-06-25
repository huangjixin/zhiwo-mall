package com.fulan.api.message.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "SmsNewsStatisticsVO", description = "消息统计")
public class SmsNewsStatisticsVO extends SmsPageVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "查询参数", name = "statisticsParameter")
    private String statisticsParameter;

    @ApiModelProperty(value = "业务系统code", name = "systemCode")
    private String systemCode;

    @ApiModelProperty(value = "通道code", name = "channelCode")
    private String channelCode;

}