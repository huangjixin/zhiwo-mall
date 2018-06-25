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
 * 课程提问回复表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PlanQuestion", description = "课程提问回复表")
@TableName("el_plan_question")

public class PlanQuestion implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "问题编号",name="id")
	private Long id;

@ApiModelProperty(value = "公开课程和线下活动的编号",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "公开课/线下课程",name="courseType")
	@TableField("course_type")
	private Integer courseType;

@ApiModelProperty(value = "问题描述",name="quesDesc")
	@TableField("ques_desc")
	private String quesDesc;

@ApiModelProperty(value = "开启/隐藏（1 表示开启， 0 表示隐藏）",name="isOpen")
	@TableField("is_open")
	private Integer isOpen;

@ApiModelProperty(value = "回复人",name="answerUser")
	@TableField("answer_user")
	private Long answerUser;

@ApiModelProperty(value = "回复内容",name="quesAnswer")
	@TableField("ques_answer")
	private String quesAnswer;

@ApiModelProperty(value = "回复时间",name="answerTime")
	@TableField("answer_time")
	private Date answerTime;

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
