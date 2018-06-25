package com.fulan.api.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 账号角色VO类
 *
 */
@ApiModel(value = "accountRoleVO", description = "账号角色业务属性封装")
@Data
public class AccountRoleVO {
	
	@ApiModelProperty(value = "编号")
	private String id;
	
	@ApiModelProperty(value = "角色Id")
	private Long roleId;
	
	@ApiModelProperty(value = "账号Id")
	private Long accountId;
}