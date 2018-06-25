package com.fulan.api.elearning.plan.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 计划与课程中间表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PlanCourse", description = "计划与课程中间表")
@TableName("el_plan_course")

public class PlanCourse implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6637713763663812755L;


	@ApiModelProperty(value = "计划课程关联id", name = "id")
	private Long id;

	@ApiModelProperty(value = "计划id", name = "courseId")
	@TableField("course_id")
	private Long courseId;

	@ApiModelProperty(value = "计划类型-公开课/学习计划/班级计划/岗位发展/必修任务", name = "courseType")
	@TableField("course_type")
	private Integer courseType;

	@ApiModelProperty(value = "基础课程编号", name = "associateId")
	@TableField("associate_id")
	private Long associateId;

	@ApiModelProperty(value = "关联类别-试卷/线上课程/线下课程", name = "associateType")
	@TableField("associate_type")
	private Integer associateType;

	@ApiModelProperty(value = "是否必修-0/1（1表示必修）", name = "isCompulsory")
	@TableField("is_compulsory")
	private Integer isCompulsory;

	@ApiModelProperty(value = "所属阶段", name = "stage")
	private Integer stage;

	@ApiModelProperty(value = "排序", name = "seq")
	private Integer seq;

	@ApiModelProperty(value = "开始时间", name = "startTime")
	@TableField("start_time")
	private Date startTime;

	@ApiModelProperty(value = "截止时间", name = "endTime")
	@TableField("end_time")
	private Date endTime;

	@ApiModelProperty(value = "签到地点", name = "signAddress")
	@TableField("sign_address")
	private String signAddress;

	@ApiModelProperty(value = "创建人", name = "createUser")
	@TableField("create_user")
	private Long createUser;

	@ApiModelProperty(value = "创建时间", name = "gmtCreate")
	@TableField("gmt_create")
	private Date gmtCreate;

	@ApiModelProperty(value = "修改人", name = "modifyUser")
	@TableField("modify_user")
	private Long modifyUser;

	@ApiModelProperty(value = "修改时间", name = "gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public Long getAssociateId() {
		return associateId;
	}

	public void setAssociateId(Long associateId) {
		this.associateId = associateId;
	}

	public Integer getAssociateType() {
		return associateType;
	}

	public void setAssociateType(Integer associateType) {
		this.associateType = associateType;
	}

	public Integer getIsCompulsory() {
		return isCompulsory;
	}

	public void setIsCompulsory(Integer isCompulsory) {
		this.isCompulsory = isCompulsory;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getSignAddress() {
		return signAddress;
	}

	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}

	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Long getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Long modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
}
