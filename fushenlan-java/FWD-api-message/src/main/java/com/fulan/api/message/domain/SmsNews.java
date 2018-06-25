package com.fulan.api.message.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@Api(tags = "SmsNews", description = "短信消息")
@TableName("sms_news")
public class SmsNews implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信消息编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "消息内容", name = "content")
    private String content;

    @ApiModelProperty(value = "手机号码", name = "phone")
    private String phone;

    @ApiModelProperty(value = "状态", name = "status")
    private Long status;

    @ApiModelProperty(value = "消息类型", name = "type")
    private String type;

    @ApiModelProperty(value = "返回结果", name = "result")
    private String result;

    @ApiModelProperty(value = "系统业务code", name = "systemCode")
    private String systemCode;

    @ApiModelProperty(value = "通道code", name = "channelCode")
    private String channelCode;

    @ApiModelProperty(value = "完成数量", name = "finishCount")
    private Integer finishCount;

    @ApiModelProperty(value = "目标数量", name = "targetCount")
    private Integer targetCount;

    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "发送时间", name = "sendTime")
    @TableField("send_time")
    private Date sendTime;

    @ApiModelProperty(value = "发送人id", name = "sendUserId")
    @TableField("send_user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long sendUserId;

    @ApiModelProperty(value = "创建时间", name = "createTime")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "创建人id", name = "createUserId")
    @TableField("create_user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUserId;

}