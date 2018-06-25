package com.fulan.api.elearning.plan.domain;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

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


@ApiModelProperty(value = "岗位发展编号",name="id")
	private Long id;

@ApiModelProperty(value = "岗位名称",name="name")
	private String name;

@ApiModelProperty(value = "岗位说明",name="description")
	private String description;

@ApiModelProperty(value = "是否允许过期后继续",name="isAllowExpired")
	@TableField("is_allow_expired")
	private Integer isAllowExpired;

@ApiModelProperty(value = "计划限制（岗位）",name="limitTypes")
	@TableField("limit_types")
	private Integer limitTypes;

@ApiModelProperty(value = "升级后天数（开始）",name="startDays")
	@TableField("start_days")
	private Integer startDays;

@ApiModelProperty(value = "升级后天数（结束）",name="endDays")
	@TableField("end_days")
	private Integer endDays;

@ApiModelProperty(value = "岗位等级",name="postLevel")
	@TableField("post_level")
	private Long postLevel;

@ApiModelProperty(value = "计划状态",name="state")
	private Integer state;

@ApiModelProperty(value = "过期提醒",name="isExpiredAlarm")
	@TableField("is_expired_alarm")
	private Integer isExpiredAlarm;

@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	private Long createUser;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改人",name="modifyUser")
	@TableField("modify_user")
	private Long modifyUser;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
