package com.fulan.api.message.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "SmsNewsInfo", description = "短信消息详情")
@TableName("sms_news_info")
public class SmsNewsInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信消息详情编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "短信消息id", name = "smsNewsId")
    private Long smsNewsId;

    @ApiModelProperty(value = "通道消息id", name = "msgId")
    private String msgId;

    @ApiModelProperty(value = "手机号码", name = "phone")
    private String phone;

    @ApiModelProperty(value = "状态", name = "status")
    private Long status;

    @ApiModelProperty(value = "客户id", name = "customerId")
    private String customerId;

    @ApiModelProperty(value = "回复内容", name = "replyContent")
    private String replyContent;

    @ApiModelProperty(value = "接受号码", name = "receivePhone")
    private String receivePhone;

}