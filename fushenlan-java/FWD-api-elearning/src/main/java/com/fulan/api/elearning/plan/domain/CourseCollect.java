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
 * 课程/计划收藏表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-24
 */
@Data
@Api(tags = "CourseCollect", description = "课程/计划收藏表")
@TableName("el_course_collect")

public class CourseCollect implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "收藏编号",name="id")
	private Long id;

@ApiModelProperty(value = "课程编号/公开课和线下课程",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "1公开课2线下课程",name="courseType")
	@TableField("course_type")
	private Integer courseType;

@ApiModelProperty(value = "用户编号",name="userId")
	@TableField("user_id")
	private Long userId;

@ApiModelProperty(value = "用户名称",name="userName")
	@TableField("user_name")
	private String userName;

@ApiModelProperty(value = "收藏时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
