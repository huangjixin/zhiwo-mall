package com.fulan.api.message.vo;

import com.baomidou.mybatisplus.annotations.TableField;
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

@Data
@Api(tags = "SmsTemplateResultVO", description = "短信模板")
public class SmsTemplateResultVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "短信模板编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "编码", name = "code")
    private String code;

    @ApiModelProperty(value = "大类类型", name = "type")
    private String type;

    @ApiModelProperty(value = "参数类型", name = "parameterType")
    private String parameterType;

    @ApiModelProperty(value = "标题", name = "title")
    private String title;

    @ApiModelProperty(value = "内容", name = "content")
    private String content;

    @ApiModelProperty(value = "主通道code", name = "masterChannelCode")
    private String masterChannelCode;

    @ApiModelProperty(value = "主通道", name = "masterChannelName")
    private String masterChannelName;

    @ApiModelProperty(value = "备用通道code", name = "slaveChannelCode")
    private String slaveChannelCode;

    @ApiModelProperty(value = "备用通道", name = "slaveChannelName")
    private String slaveChannelName;

    @ApiModelProperty(value = "主模板状态", name = "masterStatus")
    private Integer masterStatus = 0;

    @ApiModelProperty(value = "备用模板状态", name = "slaveStatus")
    private Integer slaveStatus = 0;

    @ApiModelProperty(value = "重试次数", name = "retryCount")
    private Integer retryCount;

    @ApiModelProperty(value = "间隔时间", name = "intervalTime")
    private Long intervalTime;

    @ApiModelProperty(value = "间隔时间类型", name = "intervalTimeType")
    private Long intervalTimeType;

    @ApiModelProperty(value = "创建时间", name = "createTime")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "创建人id", name = "createUserId")
    @TableField("create_user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUserId;

    @ApiModelProperty(value = "修改时间", name = "updateTime")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "修改人id", name = "createUserId")
    @TableField("update_user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long updateUserId;
}