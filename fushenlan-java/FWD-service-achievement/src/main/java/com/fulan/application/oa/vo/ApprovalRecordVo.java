package com.fulan.application.oa.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApprovalRecordVo {
	private Integer taskId;
	private String nodeName;
	@ApiModelProperty(value = "测试阶段，若配置为具体用户id，则分配给此id。若配置为角色，则分配一个随机id，格式为role:id",name="审批者id")
	private String userId;
	private String comment;
	private Date handleDate;
	@ApiModelProperty(value = "0：进行中，1：通过，2：拒绝，3：其他")
	private String state;
}
