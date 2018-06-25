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
 * 任务过期提醒表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "ExpiredAlarm", description = "任务过期提醒表")
@TableName("el_expired_alarm")

public class ExpiredAlarm implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "提醒编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "任务类型", name = "courseType")
    @TableField("course_type")
    private Integer courseType;

    @ApiModelProperty(value = "任务编号(公开课/学习计划/班级计划/岗位发展/必修任务)", name = "courseId")
    @TableField("course_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long courseId;

    @ApiModelProperty(value = "任务提醒序号", name = "seq")
    private Integer seq;

    @ApiModelProperty(value = "过期前多少天提醒", name = "beforeDay1")
    @TableField("before_day1")
    private Integer beforeDay1;

    @ApiModelProperty(value = "提醒日期", name = "remindDate")
    @TableField("remind_date")
    private Date remindDate;

    @ApiModelProperty(value = "提醒消息", name = "message")
    private String message;

    @ApiModelProperty(value = "用户编号", name = "userId")
    @TableField("user_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long userId;

    @ApiModelProperty(value = "用户名称", name = "userName")
    @TableField("user_name")
    private String userName;

    @TableField("gmt_create")
    private Date gmtCreate;

    @TableField("gmt_modified")
    private Date gmtModified;


}
