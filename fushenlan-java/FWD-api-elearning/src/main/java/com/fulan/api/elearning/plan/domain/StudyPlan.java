package com.fulan.api.elearning.plan.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fulan.api.elearning.plan.condition.CourseVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 学习计划
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "StudyPlan", description = "学习计划")
@TableName("el_study_plan")
public class StudyPlan implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3502506068292458480L;

	@ApiModelProperty(value = "班级计划编号", name = "id")
	private Long id;

	@ApiModelProperty(value = "计划代码", name = "code")
	private String code;

	@ApiModelProperty(value = "班级计划名称", name = "name")
	private String name;

	@ApiModelProperty(value = "班级计划说明", name = "description")
	private String description;

	@ApiModelProperty(value = "一级分类", name = "groupId")
	@TableField("group_id")
	private Long groupId;

	@ApiModelProperty(value = "二级分类", name = "tagId")
	@TableField("tag_id")
	private Long tagId;

	@ApiModelProperty(value = "计划状态 启用/停用", name = "state")
	private Integer state;

	@ApiModelProperty(value = "计划阶段数", name = "stageNum")
	@TableField("stage_num")
	private Integer stageNum;

	@ApiModelProperty(value = "课程数量", name = "courseNum")
	@TableField("course_num")
	private Integer courseNum;

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
	
	private Set<CourseVo> courses;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getStageNum() {
		return stageNum;
	}

	public void setStageNum(Integer stageNum) {
		this.stageNum = stageNum;
	}

	public Integer getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Integer courseNum) {
		this.courseNum = courseNum;
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

	public Set<CourseVo> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseVo> courses) {
		this.courses = courses;
	}

}
