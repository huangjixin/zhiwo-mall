package com.fulan.api.information.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
 * 报名详情
 * </p>
 *
 * @author fulan123
 * @since 2018-04-09
 */
@Data
@Api(tags = "ApplyDetail", description = "报名详情")
@TableName("iris_apply_detail")

public class ApplyDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键/编号", name = "id")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "活动编码", name = "activityId")
	@TableField("activity_id")
	private Long activityId;


	@ApiModelProperty(value = "报名人/工号", name = "agentCode")
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
	
	//报名状态  0未报名，1已报名
	private String status="0";
	

	@ApiModelProperty(value = "职级名称", name = "levelName")
	@TableField("agent_code")
	private String level_name;

	@ApiModelProperty(value = "报名时间 存储报名的具体年月日", name = "applyDate")
	@TableField("apply_date")
	private Date applyDate;

}
