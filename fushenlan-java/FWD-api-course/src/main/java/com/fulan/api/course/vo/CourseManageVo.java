package com.fulan.api.course.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(tags = "CourseManageVo", description = "课程和中间表")
@Data
public class CourseManageVo { 
	 
	@ApiModelProperty(value = "中间表id" ,name="id")
	private String id;
	
	@ApiModelProperty(value = "中间表isLine" ,name="isLine")
	private String isLine;
	
	@ApiModelProperty(value = "中间表planId" ,name="planId")
	private Long planId;
	
	@ApiModelProperty(value = "中间表seq" ,name="seq")
	private Integer seq;
	
	@ApiModelProperty(value = "中间表courseType" ,name="courseType")
	private Integer courseType;
	
	@ApiModelProperty(value = "中间表associateId" ,name="associateId")
	private Long associateId;
	
	@ApiModelProperty(value = "中间表associateType" ,name="associateType")
	private Integer associateType;
	
	@ApiModelProperty(value = "coutse表的name" ,name="name")
	private String name;
	
	@ApiModelProperty(value = "中间表isCompulsory" ,name="isCompulsory")
	private String isCompulsory;
	
	@ApiModelProperty(value = "中间表stage" ,name="stage")
	private Integer stage;
	
	
	
	

}
