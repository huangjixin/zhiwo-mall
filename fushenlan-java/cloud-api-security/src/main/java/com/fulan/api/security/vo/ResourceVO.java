package com.fulan.api.security.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 账号VO类
 *
 */
@ApiModel(value = "resourceVO", description = "资源业务属性封装")
@Data
public class ResourceVO {

	@ApiModelProperty(value = "编号")
	private String id;
	
	@ApiModelProperty(value = "角色名")
	private String resourceName;
	
	@ApiModelProperty(value = "是否禁用")
	private Boolean enabled;
	
	@ApiModelProperty(value = "图标")
	private String icon;
	
	@ApiModelProperty(value = "资源类型 （M：菜单  B：按钮）")
	private String resourceType;
	
	@ApiModelProperty(value = "菜单路径")
	private String url;
	
	@ApiModelProperty(value = "父节点id")
	private String parentId;
	
	@ApiModelProperty(value = "层级")
	private String layer;
	
	@ApiModelProperty(value = "顺序")
	private String seq;
}