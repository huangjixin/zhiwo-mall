package com.fulan.api.achievement.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "TeamPerformanceVo", description = "团队视图类")
public class TeamPerformanceVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String name;//团队成员名称
	
	private Integer rank;//排名

}
