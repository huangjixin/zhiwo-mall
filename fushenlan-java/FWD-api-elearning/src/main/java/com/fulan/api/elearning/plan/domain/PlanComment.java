package com.fulan.api.elearning.plan.domain;

import java.io.Serializable;

import java.math.BigDecimal;
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
 * 课程星级评分和评论
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PlanComment", description = "课程星级评分和评论")
@TableName("el_plan_comment")

public class PlanComment implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "评论编号",name="id")
	private Long id;

@ApiModelProperty(value = "公开课程和线下活动的编号",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "公开课/线下课程",name="courseType")
	@TableField("course_type")
	private Integer courseType;

@ApiModelProperty(value = "星级评论",name="star")
	private BigDecimal star;

@ApiModelProperty(value = "评论描述",name="comment")
	private String comment;

@ApiModelProperty(value = "开启/隐藏（1 表示开启， 0 表示隐藏），默认开启",name="isOpen")
	@TableField("is_open")
	private Integer isOpen;

@ApiModelProperty(value = "评论人",name="commentUser")
	@TableField("comment_user")
	private Long commentUser;

@ApiModelProperty(value = "评论时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改人",name="modifyUser")
	@TableField("modify_user")
	private Long modifyUser;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
