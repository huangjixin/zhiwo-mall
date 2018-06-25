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
 * 基础课程资料中间表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "CourseMaterial", description = "基础课程资料中间表")
@TableName("el_course_material")

public class CourseMaterial implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "编号",name="id")
	private Long id;

@ApiModelProperty(value = "课程编号",name="courseId")
	@TableField("course_id")
	private Long courseId;

@ApiModelProperty(value = "课程课程关联类别 1表示课程课件，0表示课程材料",name="materialType")
	@TableField("material_type")
	private Integer materialType;

@ApiModelProperty(value = "资料编号",name="materialId")
	@TableField("material_id")
	private Long materialId;

@ApiModelProperty(value = "排序号",name="seq")
	private Integer seq;

@ApiModelProperty(value = "创建时间",name="gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

@ApiModelProperty(value = "修改时间",name="gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;



}
