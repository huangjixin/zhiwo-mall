package com.fulan.api.plan.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotations.TableField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@Data
public class PlanCourseVoQ implements Serializable{
	private static final long serialVersionUID = -6637713763663812755L;

	private Long id;

	private String name;

	private Long planId;

	private Integer courseType;

	private Long associateId;

	private Integer associateType;

	private Integer isCompulsory;

	private Integer stage;

	private Integer seq;

	private Date startTime;

	private Date endTime;

	private String signAddress;

	private Long createUser;

	private Date gmtCreate;

	private Long modifyUser;

	private Date gmtModified;

	private Integer paperStatus;//考试状态

	private Integer courseStatus;//课程状态

	private String currentTime;//当前系统时间

	private String startDate;//开始时间

	private String endDate;//结束时间
	
	private String isStage;

	
	
	private String desName;//试卷活课程名称
}
