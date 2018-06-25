package com.fulan.api.elearning.plan.condition;

import io.swagger.annotations.Api;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 基础课程视图
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "CourseVo", description = "课程视图类")
public class CourseVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4734460781324305398L;

	private Long courseId;//课程ID

	private String courseName;//课程名称

	private Integer isOnline;//是否上线

	private String descript;//描述

	private Integer isRelatePlan;//是否关联计划
	
	private Integer stage;//所属阶段
	
	private Integer isCompulsory;//是否必修

}
