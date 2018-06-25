package com.fulan.api.information.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import lombok.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 活动指定人员中间表
 * </p>
 *
 * @author fulan123
 * @since 2018-04-09
 */
@Data
@Api(tags = "ActivityMemberMapping", description = "活动指定人员中间表")
@TableName("iris_activity_member_mapping")

public class ActivityMemberMapping implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键/编号", name = "id")
	private Long id;

	@ApiModelProperty(value = "活动编码(外键来源)", name = "activityId")
	@TableField("activity_id")
	private Long activityId;

	@ApiModelProperty(value = "代理人编号", name = "agentCode")
	@TableField("agent_code")
	private String agentCode;

	@ApiModelProperty(value = "用户姓名", name = "acountName")
	@TableField("acount_name")
	private String acountName;
	
	@ApiModelProperty(value = "总公司id", name = "companyId")
	@TableField("company_id")
	private String companyId;
	
	@ApiModelProperty(value = "总公司名称", name = "companyName")
	@TableField("company_name")
	private String companyName;
	
	@ApiModelProperty(value = "分公司id", name = "branchCompanyId")
	@TableField("branch_company_id")
	private String branchCompanyId;
	
	@ApiModelProperty(value = "分公司名称", name = "branchCompanyName")
	@TableField("branch_company_name")
	private String branchCompanyName;
	
	@ApiModelProperty(value = "部门id", name = "orgId")
	@TableField("org_id")
	private String orgId;
	
	@ApiModelProperty(value = "部门名称", name = "orgName")
	@TableField("org_name")
	private String orgName;
	
	
	@ApiModelProperty(value = "职级Id", name = "levelId")
	@TableField("level_id")
	private String levelId;
	
	@ApiModelProperty(value = "职级名称", name = "levelName")
	@TableField("level_name")
	private String levelName;

	@ApiModelProperty(value = "1_指定代理人 2_指定部门", name = "targetType")
	@TableField("target_type")
	private Integer targetType;

}
