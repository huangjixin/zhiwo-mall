package com.fulan.api.plan.domain;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.vo.CourseVo;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 岗位发展表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PostDevelopment", description = "岗位发展表")
@TableName("el_post_development")

public class PostDevelopment implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "岗位发展编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "岗位名称", name = "name")
    private String name;

    @ApiModelProperty(value = "岗位说明", name = "description")
    private String description;

    @ApiModelProperty(value = "是否允许过期后继续", name = "isAllowExpired")
    @TableField("is_allow_expired")
    private Integer isAllowExpired;

    @ApiModelProperty(value = "计划限制（岗位）", name = "limitTypes")
    @TableField("limit_types")
    private Integer limitTypes;

    @ApiModelProperty(value = "升级后天数（开始）", name = "startDays")
    @TableField("start_days")
    private Integer startDays;

    @ApiModelProperty(value = "升级后天数（结束）", name = "endDays")
    @TableField("end_days")
    private Integer endDays;

    @ApiModelProperty(value = "岗位等级", name = "postLevel")
    @TableField("post_level")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long postLevel;

    @ApiModelProperty(value = "计划状态", name = "state")
    private Integer state;

    @ApiModelProperty(value = "过期提醒", name = "isExpiredAlarm")
    @TableField("is_expired_alarm")
    private Integer isExpiredAlarm;

    @ApiModelProperty(value = "创建人", name = "createUser")
    @TableField("create_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @TableField("gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改人", name = "modifyUser")
    @TableField("modify_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long modifyUser;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField("gmt_modified")
    private Date gmtModified;
    
    @ApiModelProperty(value = "计划类型 (0:普通型计划 1:阶段性计划)",name="planType")
    @TableField("plan_type")
    private Integer planType;
    

    @ApiModelProperty(value = "是否需要按照顺序学习（0:不需要。 1:需要）",name="isSeq")
    @TableField("is_seq")
    private Integer isSeq;

}
