/**
 * Project Name:FWD-api-flow
 * File Name:InterviewerDetialVo.java
 * Package Name:com.fulan.api.flow.vo
 * Date:2018年2月7日上午10:54:11
 * Copyright (c) 上海复深蓝软件股份有限公司.
 *
*/

package com.fulan.api.flow.vo;
/**
 * ClassName:InterviewerDetialVo
 * Reason:	 TODO ADD REASON
 * Date:     2018年2月7日 上午10:54:11 
 * @author   chen.zhuang
 * @version  
 * @since    JDK 1.8 
 */

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@Api(tags = "InterviewerDetialVo", description = "面试官信息封装")
public class InterviewerDetialVo {
	
	
	@ApiModelProperty(value = "面试官名字", name = "processingName")
	private  String processingName ;
	
	@ApiModelProperty(value = "分公司名", name = "orgName")
	private  String orgName ;
	
	
	@ApiModelProperty(value = "职位", name = "postType")
	private  String postType ;
	
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "团队规模", name = "teamSize")
	private  Long  teamSize;
	
	@ApiModelProperty(value = "面试结果(1,通过，2不通过，3，更改计划)", name = "processingStatus")
	private  String processingStatus;
	
	@ApiModelProperty(value = "面试描述", name = "processingDesc")
	private  String processingDesc;
	
	
	
}

