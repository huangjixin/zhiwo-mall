package com.fulan.api.message.vo;

import com.fulan.api.message.domain.SmsTemplate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
@Api(tags = "SmsBusinessVO", description = "短信业务")
public class SmsBusinessVO implements Serializable {

    public enum Type{
        FOUR_CODE(),SIX_CODE()
    }

    /**
     * 定时状态 0 不发: 1 发
     */
    public static final Integer TASK_STATUS = 1;

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "手机号码,多个以逗号分隔", name = "phones")
    private String phones;

    @ApiModelProperty(value = "模板编码", name = "businessId")
    private String businessId;

    @ApiModelProperty(value = "系统来源", name = "resourceId")
    private String sourceId;

    @ApiModelProperty(value = "键值对", name = "parameter")
    private Map<String, Object> parameter;

    @ApiModelProperty(value = "键值对", name = "newParam")
    private Map<String, String> newParam;

    @ApiModelProperty(value = "内容", name = "content")
    private String content;

    @ApiModelProperty(value = "通道的类型(主,备)", name = "channelType")
    private SmsTemplate.Type channelType;

    @ApiModelProperty(value = "类型", name = "type")
    private String type;

    @ApiModelProperty(value = "发送类型", name = "sendType")
    private Integer sendType = 0;

    @ApiModelProperty(value = "通道对应的模板id", name = "tempId")
    private String tempId;

    @ApiModelProperty(value = "定时发送状态{0 立即发送 :1 定时发送}", name = "taskStatus")
    private Integer taskStatus = 0;

    @ApiModelProperty(value = "定时发送时间", name = "taskTime")
    private String taskTime;

}