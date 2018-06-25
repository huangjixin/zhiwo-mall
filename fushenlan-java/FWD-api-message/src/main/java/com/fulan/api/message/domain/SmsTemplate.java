package com.fulan.api.message.domain;

import com.baomidou.mybatisplus.annotations.TableField;
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
import java.util.Date;

@Data
@Api(tags = "SmsTemplate", description = "短信模板")
@TableName("sms_template")
public class SmsTemplate implements Serializable {

    public enum Type{
        MASTER,SLAVE
    }
    private static final long serialVersionUID = 1L;

    public static final Integer PASSING = 0;//审核中
    public static final Integer PASS = 1;//审核通过状态
    public static final Integer NO_PASS = 2;//审核不通过状态

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

    @ApiModelProperty(value = "主模板状态", name = "masterStatus")
    private Integer masterStatus;

    @ApiModelProperty(value = "备用模板状态", name = "slaveStatus")
    private Integer slaveStatus;

    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

    @ApiModelProperty(value = "主通道Code", name = "masterChannelCode")
    private String masterChannelCode;

    @ApiModelProperty(value = "备用通道Code", name = "slaveChannelCode")
    private String slaveChannelCode;

    @ApiModelProperty(value = "重试次数", name = "retryCount")
    private Integer retryCount;

    @ApiModelProperty(value = "间隔时间", name = "intervalTime")
    private Long intervalTime;

    @ApiModelProperty(value = "间隔时间类型", name = "intervalTimeType")
    private Long intervalTimeType;

    @ApiModelProperty(value = "主通道模板id", name = "masterTempId")
    private String masterTempId;

    @ApiModelProperty(value = "备用通道模板id", name = "slaveTempId")
    private String slaveTempId;

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