package com.fulan.api.security.vo;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.security.domain.Resource;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoleAndResourceVo{
	/*
	 * id
	 */
	@JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;
	/*
	 * 角色名称
	 */
	private String roleName;
	/*
	 * 是否开通
	 */
	private Boolean enabled;
	
	/*
	 * 角色父级
	 */
	@ApiModelProperty(value = "上级")
	private String parentId;
	
	
	private List<Resource> resource;
}
