package com.fulan.api.calendar.domain;

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
 * 考勤对象设置
 * </p>
 *
 * @author fulan123
 * @since 2018-04-09
 */
@Data
@Api(tags = "AttendanceObjects", description = "考勤对象设置")
@TableName("iris_attendance_objects")

public class AttendanceObjects implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键/编号", name = "id")
	private Long id;

	@ApiModelProperty(value = "规则编号", name = "ruleId")
	@TableField("rule_id")
	private Long ruleId;

	@ApiModelProperty(value = "组织编号", name = "orgId")
	@TableField("org_id")
	private String orgId;
	
	@ApiModelProperty(value = "所在公司ID", name = "companyId")
	@TableField("company_id")
	private String companyId;

	@ApiModelProperty(value = "1_所有人     2_指定部门   3_指定人员", name = "attendanceObjectType")
	@TableField("attendance_object_type")
	private Integer attendanceObjectType;

	@TableField("agent_code")
	private String agentCode;

}
