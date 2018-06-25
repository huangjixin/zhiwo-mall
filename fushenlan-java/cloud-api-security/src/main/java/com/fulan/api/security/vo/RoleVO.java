package com.fulan.api.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 账号VO类
 *
 */
@ApiModel(value = "roleVO", description = "角色业务属性封装")
@Data
public class RoleVO {
	
	@ApiModelProperty(value = "编号")
	private String id;
	
	@ApiModelProperty(value = "角色名")
	private String roleName;
	
	@ApiModelProperty(value = "是否禁用")
	private Boolean enabled;
	
	@ApiModelProperty(value = "上级")
	private String parentId;
}