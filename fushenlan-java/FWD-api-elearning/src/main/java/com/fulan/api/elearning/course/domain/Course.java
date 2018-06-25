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
 * 基础课程
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Course", description = "基础课程")
@TableName("el_course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;


@ApiModelProperty(value = "课程编号",name="id")
	private Long id;

@ApiModelProperty(value = "课程名称",name="name")
	private String name;

@ApiModelProperty(value = "线上/线下（1 表示线上， 0 表示线下）",name="isOnline")
	@TableField("is_online")
	private Integer isOnline;

@ApiModelProperty(value = "课程说明",name="description")
	private String description;

@ApiModelProperty(value = "课程缩略图路径",name="bannerPath")
	@TableField("banner_path")
	private String bannerPath;

@ApiModelProperty(value = "视频/SCORM/PPT/WORD/EXCEL",name="type")
	private Integer type;

@ApiModelProperty(value = "用户组/角色编号",name="groupId")
	@TableField("group_id")
	private Long groupId;

@ApiModelProperty(value = "用户组/角色下的标签编号",name="tagId")
	@TableField("tag_id")
	private Long tagId;

@ApiModelProperty(value = "至少学习分钟数",name="learningTime")
	@TableField("learning_time")
	private Integer learningTime;

@ApiModelProperty(value = "课程讲师",name="lecturer")
	private Long lecturer;

@ApiModelProperty(value = "是否公开 1 表示是， 0 表示否，默认否",name="isShare")
	@TableField("is_share")
	private Integer isShare;

@ApiModelProperty(value = "是否关联计划 1 表示是， 0 表示否，默认否",name="isRelatePlan")
	@TableField("is_relate_plan")
	private Integer isRelatePlan;

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
