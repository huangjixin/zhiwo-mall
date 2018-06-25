package com.fulan.api.plan.vo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import lombok.Data;

@Data
public class StudyPlanVvo implements Serializable{
private static final long serialVersionUID = 3502506068292458480L;

@JsonSerialize(using = LongJsonSerializer.class)
@JsonDeserialize(using = LongJsonDeserializer.class)
private Long id; 

private String code;
private String name;
private Long groupId;
private String groupName;
private String tagName;
private Long tagId;
private String isAssociate;//是否被关联
private Integer courseNum;//课程数
private Integer stageNum;//计划阶段数
private Integer state;//学习计划状态(启用或禁用)


}