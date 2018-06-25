package com.fulan.api.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 根据名称模糊查询
 *
 */
@ApiModel(value = "AccountFuzzyQueryVO", description = "根据名称模糊查询code和name")
@Data
public class AccountFuzzyQueryVO {
	
	@ApiModelProperty(value = "账户id")
	private String id;
	
	@ApiModelProperty(value = "账户名称")
	private String name;

}