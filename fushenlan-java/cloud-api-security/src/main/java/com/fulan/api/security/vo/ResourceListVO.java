package com.fulan.api.security.vo;

import java.util.List;

import com.fulan.api.security.domain.Resource;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResourceListVO {
	private String id;
	
	private String resourceName;
	
	private Boolean enabled;
	
	private String icon;
	
	private String resourceType;

	private String url;
	
	private String parentId;
	
	private String layer;
	
	private String seq;
	
	List<Resource> list;
}
