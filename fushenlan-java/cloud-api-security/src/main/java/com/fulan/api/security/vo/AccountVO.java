package com.fulan.api.security.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 账号VO类
 *
 */
@ApiModel(value = "accountVO", description = "账号业务属性封装")
@Data
public class AccountVO {

	@ApiModelProperty(value = "编号")
	private String id;
	
	@ApiModelProperty(value = "登录名")
	private String accountName;

	@ApiModelProperty(value = "代理人所属系统")
	private String systemType;

	@ApiModelProperty(value = "是否开通")
	private Boolean enabled;

	@ApiModelProperty(value = "是否锁定")
	private Boolean locked;

	@ApiModelProperty(value = "座机号")
	private String telephone;

	@ApiModelProperty(value = "手机号")
	private String mobile;

	@ApiModelProperty(value = "邮箱")
	private String email;
	
	@ApiModelProperty(value = "账号类型")
	private String accountType;
	
	@ApiModelProperty(value = "密码")
	private String password;
	
	@ApiModelProperty(value = "公司id")
	private String companyId;
	
	@ApiModelProperty(value = "对应营销处ID")
	private String orgId;

	@ApiModelProperty(value = "职级（1.总监。2.主管。3.代理人。4.内勤）")
	private String postType;
	
	@ApiModelProperty(value = "团队规模")
	private Long teamSize;
	
	@ApiModelProperty(value = "公司名称")
	private String companyName;
	
	@ApiModelProperty(value = "营销处名字")
	private String orgName;
	
	@ApiModelProperty(value = "总公司id")
	private String generalCompanyId;
	
	@ApiModelProperty(value = "总公司名称")
	private String generalCompanyName;
	
	@JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "直属主管")
	private Long belongSuperior;
	
	@JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "直属总监")
	private Long belongDirector;
	
	@JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "上级")
	private Long Superior;
	
	
	
	@ApiModelProperty(value = "创建时间")
	private String createTime;
	
}