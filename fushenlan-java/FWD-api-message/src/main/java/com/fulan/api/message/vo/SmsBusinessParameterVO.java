package com.fulan.api.message.vo;

import com.fulan.api.message.domain.SmsTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Api(tags = "SmsBusinessParameterVO", description = "短信请求参数")
public class SmsBusinessParameterVO extends SmsBusinessVO implements Serializable {
    /**
     * 定时状态 0 不发: 1 发
     */
    public static final Integer TASK_STATUS = 1;

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号码", name = "phones")
    private String phones;

    @ApiModelProperty(value = "内容", name = "content")
    private String content;

    @ApiModelProperty(value = "通道的类型", name = "channelType")
    private SmsTemplate.Type channelType;

    @ApiModelProperty(value = "通道对应的模板id", name = "tempId")
    private String tempId;

    @ApiModelProperty(value = "定时发送状态{0 立即发送 :1 定时发送}", name = "taskStatus")
    private Integer taskStatus = 0;

    @ApiModelProperty(value = "定时发送时间", name = "taskTime")
    private String taskTime;
}