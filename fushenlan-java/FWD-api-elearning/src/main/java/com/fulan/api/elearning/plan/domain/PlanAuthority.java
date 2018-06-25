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
 * 课程权限表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PlanAuthority", description = "课程权限表")
@TableName("el_plan_authority")

public class PlanAuthority implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "任务类型(公开课/学习计划/班级计划/岗位发展/必修任务公开课/学习计划/班级计划/岗位发展/必修任务)",name="courseType")
	@TableField("course_type")
	private Integer courseType;

@ApiModelProperty(value = "任务编号",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "机构",name="organization")
	private Long organization;

@ApiModelProperty(value = "权限类型(1指定分类，2指定用户，3上传名单)",name="authorityType")
	@TableField("authority_type")
	private Integer authorityType;

@ApiModelProperty(value = "关联编号(分类编号/用户编号/上传名单编号)",name="associateId")
	@TableField("associate_id")
	private Long associateId;

@ApiModelProperty(value = "创建人",name="createUser")
	@TableField("create_user")
	private Long createUser;

	@TableField("gmt_create")
	private Date gmtCreate;

	@TableField("modify_user")
	private Long modifyUser;

	@TableField("gmt_modified")
	private Date gmtModified;



}
