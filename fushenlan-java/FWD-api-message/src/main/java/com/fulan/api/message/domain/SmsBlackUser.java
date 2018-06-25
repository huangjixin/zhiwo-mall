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
@Api(tags = "SmsBlackUser", description = "短信黑名单")
@TableName("sms_black_user")
public class SmsBlackUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "黑名单编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "手机号码", name = "phone")
    private String phone;

    @ApiModelProperty(value = "客户id", name = "customerId")
    @TableField("customer_id")
    private String customerId;

    @ApiModelProperty(value = "黑名单类型", name = "type")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "备注", name = "remark")
    @TableField("remark")
    private String remark;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间", name = "createTime")
    @TableField("create_time")
    private Date createTime;

    @ApiModelProperty(value = "创建人id", name = "createUserId")
    @TableField("create_user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUserId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "修改时间", name = "updateTime")
    @TableField("update_time")
    private Date updateTime;

    @ApiModelProperty(value = "修改人id", name = "createUserId")
    @TableField("update_user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long updateUserId;

}