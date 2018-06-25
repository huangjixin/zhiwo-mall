package com.fulan.api.security.vo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * 代理职级类
 *
 */

@Data
public class AccountAgentVo implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private String agentId;//代理人id
	
	private String accountId;//账户id

	private String postType;//职级id
	
	private String levelName;//职级名
	
	
}
