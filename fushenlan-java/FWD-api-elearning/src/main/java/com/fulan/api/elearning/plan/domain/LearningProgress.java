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
 * 学习进度
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "LearningProgress", description = "学习进度")
@TableName("el_learning_progress")

public class LearningProgress implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "课程/ 计划编号",name="planId")
	@TableField("plan_id")
	private Long planId;

@ApiModelProperty(value = "公开课/学习计划/班级计划/岗位发展/必修任务",name="type")
	private Integer type;

@ApiModelProperty(value = "基础课程编号",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "学习时间",name="learningTime")
	@TableField("learning_time")
	private Integer learningTime;

@ApiModelProperty(value = "用户编号",name="userId")
	@TableField("user_id")
	private Long userId;

@ApiModelProperty(value = "用户名称",name="userName")
	@TableField("user_name")
	private String userName;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
