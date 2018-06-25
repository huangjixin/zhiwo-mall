package com.fulan.api.paper.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Api(tags = "PaperManageMinVo", description = "试卷和课程中间表")
@Data
public class PaperManageMinVo {
    
	@ApiModelProperty(value = "中间表id" ,name="id")
	private String id;
	
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
	private String name ;
}
