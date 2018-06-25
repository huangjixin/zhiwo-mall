package com.fulan.api.plan.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 浏览历史表
 * </p>
 *
 * @author Hedge
 * @since 2018-01-30
 */
@Data
@Api(tags = "History", description = "浏览历史表")
@TableName("el_history")

public class History implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "系统编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "计划编号", name = "planId")
    @TableField("plan_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planId;

    @ApiModelProperty(value = "计划类型", name = "planType")
    @TableField("plan_type")
    private Integer planType;

    @ApiModelProperty(value = "用户编号", name = "userId")
    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @ApiModelProperty(value = "用户名称", name = "userName")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField("gmt_create")
    private Date gmtCreate;


}
