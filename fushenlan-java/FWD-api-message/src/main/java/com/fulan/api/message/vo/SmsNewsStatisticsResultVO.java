package com.fulan.api.message.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "SmsNewsStatisticsResultVO", description = "消息统计")
public class SmsNewsStatisticsResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "名称", name = "name")
    private String name;

    @ApiModelProperty(value = "请求总数量", name = "smsCount")
    private Long smsCount;

    @ApiModelProperty(value = "请求成功总数量", name = "smsSuccessCount")
    private Long smsSuccessCount;

    @ApiModelProperty(value = "请求失败总数量", name = "smsFailCount")
    private Long smsFailCount;

    @ApiModelProperty(value = "请求未知数量", name = "smsUnknownCount")
    private Long smsUnknownCount;

}