package com.fulan.api.elearning.course.domain;

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
 * 基础课程分享表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "CourseShare", description = "基础课程分享表")
@TableName("el_course_share")

public class CourseShare implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "课程用户组关联编号",name="id")
	private Long id;

@ApiModelProperty(value = "课程编号",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "用户组编号",name="groupId")
	@TableField("group_id")
	private Long groupId;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
