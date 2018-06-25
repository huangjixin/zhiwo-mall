package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import lombok.Data;
/**
 * 
 * @author Qiguohui
 *
 */
@Data
@Api(tags = "StudyPlanVoQ", description = "学习计划")
public class StudyPlanVoQ {
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	private String code;
	private String name;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long groupId;//一级分类

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long tagId;//二级分类

	private Integer associated;//是否被关联
	private Integer courseNum;//课程数量
	private Integer stageNum;//计划阶段数
	private Integer state;//计划状态
	
	
	
	
}
