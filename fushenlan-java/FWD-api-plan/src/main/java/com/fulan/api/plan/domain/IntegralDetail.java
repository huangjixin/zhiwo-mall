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
 * 积分详情表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-25
 */
@Data
@Api(tags = "IntegralDetail", description = "积分详情表")
@TableName("el_integral_detail")
public class IntegralDetail implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "用户编号", name = "userId")
    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @ApiModelProperty(value = "用户名称", name = "userName")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "获取途径 1签到/2计划/3任务", name = "obtainType")
    @TableField("obtain_type")
    private Integer obtainType;

    @ApiModelProperty(value = "积分类型 1获得/2消费", name = "integralType")
    @TableField("integral_type")
    private Integer integralType;

    @ApiModelProperty(value = "积分数值", name = "integralNumber")
    @TableField("integral_number")
    private Integer integralNumber;

    @ApiModelProperty(value = "1签到/2计划/3任务 编号", name = "hostId")
    @TableField("host_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long hostId;

    @ApiModelProperty(value = "1签到/2计划/3任务 名称", name = "hostName")
    @TableField("host_name")
    private String hostName;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField("gmt_create")
    private Date gmtCreate;


}
