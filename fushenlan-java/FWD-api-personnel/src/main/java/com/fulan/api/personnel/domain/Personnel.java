package com.fulan.api.personnel.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 人才信息主表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "Personnel", description = "人才信息主表")
@TableName("er_personnel")

public class Personnel implements Serializable {

    private static final long serialVersionUID = 1L;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "",name="id")
	@TableField("id")
	private Long id;
	
	@ApiModelProperty(value = "DMS返回的agentcode",name="agentCode")
	@TableField("agent_code")
	private String agentCode;

	@ApiModelProperty(value = "人才类型",name="personnelType")
	@TableField("personnel_type")
	private String personnelType;

	@ApiModelProperty(value = "人才状态",name="personnelStatus")
	@TableField("personnel_status")
	private String personnelStatus;

	
	@ApiModelProperty(value = "证件类型",name="identityType")
	@TableField("identity_type")
	private String identityType;

	@ApiModelProperty(value = "证件编码",name="identityCode")
	@TableField("identity_code")
	private String identityCode;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "证件有效期",name="ctfexpireDate")
	@TableField("ctfexpire_date")
	private Date ctfexpireDate;

	@ApiModelProperty(value = "姓名",name="name")
	private String name;
	
	@ApiModelProperty(value = "初审是否通过",name="trialResult")
	private String trialResult;

	@ApiModelProperty(value = "年龄",name="age")
	private Integer age;
	
	@ApiModelProperty(value = "性别",name="sex")
	private String sex;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "出生日期",name="birthday")
	private Date birthday;

	@ApiModelProperty(value = "民族",name="nation")
	private String nation;

	@ApiModelProperty(value = "政治面貌",name="political")
	private String political;

	@ApiModelProperty(value = "新人来源",name="source")
	private String source;

	@ApiModelProperty(value = "来源渠道",name="channel")
	private String channel;

	@ApiModelProperty(value = "学历",name="education")
	private String education;

	@ApiModelProperty(value = "手机号",name="cellphone")
	private String cellphone;

	@ApiModelProperty(value = "电子邮箱",name="email")
	private String email;

	@ApiModelProperty(value = "手机是否认证",name="isPhoneValidate")
	@TableField("is_phone_validate")
	private String isPhoneValidate;

	@ApiModelProperty(value = "是否有保险公司经验（1，有，2，无）",name="isInsuranceCompany")
	@TableField("is_insurance_company")
	private Integer isInsuranceCompany;

	@ApiModelProperty(value = "原保险机构名称",name="originalCompany")
	@TableField("original_company")
	private String originalCompany;

	@ApiModelProperty(value = "是否离职(1:是，0，否)",name="isQuit")
	@TableField("is_quit")
	private Integer isQuit;
	
	@ApiModelProperty(value = "离职是否满六个月（1，有，2，无）",name="isOverSix")
	@TableField("is_over_six")
	private Integer isOverSix;


	@ApiModelProperty(value = "本地工作时长",name="nativeWorkTime")
	@TableField("native_work_time")
	private String nativeWorkTime;
    
	@ApiModelProperty(value = "是否复核通过",name="checkResult")
	@TableField("check_result")
	private String checkResult;
	
	@ApiModelProperty(value = "是否复核通过",name="isCheck")
	@TableField("is_check")
	private String isCheck;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "",name="createTime")
	@TableField("create_time")
	private Date createTime;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@ApiModelProperty(value = "",name="createUser")
	@TableField("create_user")
	private Long createUser;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "",name="updateTime")
	@TableField("update_time")
	private Date updateTime;

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "",name="updateUser")
	@TableField("update_user")
	private Long updateUser;

	@ApiModelProperty(value = "国籍",name="country")
	@TableField("country")
	private String country;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "",name="departureDate")
	@TableField("departure_date")
	private Date departureDate;
	
	@ApiModelProperty(value = "核查意见",name="cheakOption")
	@TableField("cheak_option")
	private String cheakOption;
	
	@ApiModelProperty(value = "创建该增员人员分公司id",name="orgId")
	@TableField("org_id")
	private String orgId;

}
