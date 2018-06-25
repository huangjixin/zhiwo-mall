package com.fulan.api.plan.vo;

import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Api(tags = "CompulsoryCplanVo", description = "必修课管理")
@Data
public class CompulsoryCplanVo {
	
	@ApiModelProperty(value = "必修课主键",name="id")
	private String id;
	
	@ApiModelProperty(value = "任务代码",name="code")
	private String code;
	
	@ApiModelProperty(value = "任务名称",name="name")
	private String name;
	
	@ApiModelProperty(value = "二级分类",name="tagId")
	private Long tagId;
	
	@ApiModelProperty(value = "计划状态",name="state")
	private Integer state;
	
	@ApiModelProperty(value = "关联编号(分类编号/用户编号/上传名单编号)",name="associateId")
	private Long associateId;
	
	@ApiModelProperty(value = "开始日期",name="startDate")
	private Date startDate;

    @ApiModelProperty(value = "结束日期",name="endDate")
	private Date endDate;
	
}
