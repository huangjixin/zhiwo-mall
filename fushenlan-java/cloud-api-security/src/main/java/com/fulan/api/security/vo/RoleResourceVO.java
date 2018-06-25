package com.fulan.api.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色资源 VO类
 *
 */
@ApiModel(value = "accountRoleVO", description = "角色资源业务属性封装")
@Data
public class RoleResourceVO {
	
	@ApiModelProperty(value = "编号")
	private String id;
	
	@ApiModelProperty(value = "角色Id")
	private Long roleId;
	
	@ApiModelProperty(value = "资源Id")
	private Long resourceId;
}