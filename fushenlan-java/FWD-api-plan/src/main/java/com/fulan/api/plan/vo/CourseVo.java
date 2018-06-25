package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 基础课程
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "CourseVo", description = "基础课程")
public class CourseVo implements Serializable {

	private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "课程编号",name="id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	@ApiModelProperty(value = "课程计划中间表编号",name="id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long planCourseId;

	@ApiModelProperty(value = "课程名称",name="name")
	private String name;

	@ApiModelProperty(value = "线上/线下（1 表示线上， 0 表示线下）",name="isOnline")
	private Integer isOnline;

	@ApiModelProperty(value = "课程说明",name="description")
	private String description;

	@ApiModelProperty(value = "课程缩略图路径",name="bannerPath")
	private String bannerPath;

	@ApiModelProperty(value = "视频/SCORM/PPT/WORD/EXCEL",name="type")
	private String type;

	@ApiModelProperty(value = "用户组/角色编号",name="groupId")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long groupId;

	@ApiModelProperty(value = "用户组/角色下的标签编号",name="tagId")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long tagId;

	@ApiModelProperty(value = "至少学习分钟数",name="learningTime")
	private Integer learningTime;

	@ApiModelProperty(value = "课程讲师",name="lecturer")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long lecturer;

	@ApiModelProperty(value = "是否公开 1 表示是， 0 表示否，默认否",name="isShare")
	private Integer isShare;

	@ApiModelProperty(value = "是否关联计划 1 表示是， 0 表示否，默认否",name="isRelatePlan")
	private Integer isRelatePlan;

	@ApiModelProperty(value = "创建人",name="createUser")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long createUser;

	@ApiModelProperty(value = "创建时间",name="gmtCreate")
	private Date gmtCreate;

	@ApiModelProperty(value = "修改人",name="modifyUser")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long modifyUser;

	@ApiModelProperty(value = "修改时间",name="gmtModified")
	private Date gmtModified;

	@ApiModelProperty(value = "是否必修-0/1（1表示必修）", name = "isCompulsory")
	private Integer isCompulsory;

	private Integer courseType;//课程类型


	private Date startTime;//开始时间

	private Date endTime;//结束时间

	private Integer learningStatus;//考试状态 or 课程学习状态  该字段作为各阶段是否可以考试判断凭据

	private String learnProgress; //学习进度（不用）

	private Integer isAllowExam = 0;//是否允许考试（不用）

	private Integer isAllowStudy = 0;//是否允许学习

	private Integer paperState;//阅卷状态

	private Integer isMaxExam;//是否达到最大测试次数（不用）

	private Integer isExam;//是否测试（不用）

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		CourseVo courseVo = (CourseVo) o;
		return Objects.equals(type, courseVo.type);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), type);
	}
}
