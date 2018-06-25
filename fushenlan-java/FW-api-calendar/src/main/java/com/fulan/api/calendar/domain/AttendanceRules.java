package com.fulan.api.calendar.domain;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
 * 考勤规则管理
 * </p>
 *
 * @author fulan123
 * @since 2018-04-09
 */
@Data
@Api(tags = "AttendanceRules", description = "考勤规则管理")
@TableName("iris_attendance_rules")

public class AttendanceRules implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键/编号", name = "id")
	private Long id;

	private String code;

	@TableField("rule_name")
	private String ruleName;

	@ApiModelProperty(value = "规则说明", name = "description")
	private String description;

	@ApiModelProperty(value = "1_普通考勤规则,2_活动考勤规则", name = "rulesType")
	@TableField("rules_type")
	private Integer rulesType;

	@ApiModelProperty(value = "日历编号", name = "calendarId")
	@TableField("calendar_id")
	private Long calendarId;
	
	@DateTimeFormat(pattern="hh:mm")
    @JsonFormat(pattern="hh:mm",timezone="GMT+8")
	@ApiModelProperty(value = "有效签到时间(08:00-10:30)", name = "validSignInTime")
	@TableField("valid_sign_in_time")
	private Date validSignInTime;

	@TableField("valid_sign_up_time")
	private Date validSignUpTime;

	@ApiModelProperty(value = "1_所有人     2_指定部门   3_指定人员", name = "attendanceObjectType")
	@TableField("attendance_object_type")
	private Integer attendanceObjectType;

	@ApiModelProperty(value = "规则开始日期", name = "startDateOfRule")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@TableField("start_date_of_rule")
	private Date startDateOfRule;

	@ApiModelProperty(value = "规则结束日期", name = "endDateOfRule")
	@TableField("end_date_of_rule")
	private Date endDateOfRule;

	@ApiModelProperty(value = "0_未启用,1_已启用", name = "status")
	private Integer status;

	@ApiModelProperty(value = "备注", name = "remark")
	private String remark;

	@ApiModelProperty(value = "创建时间", name = "gmtTime")
	@TableField("gmt_time")
	private Date gmtTime;

	@ApiModelProperty(value = "创建人", name = "createUser")
	@TableField("create_user")
	private Long createUser;
	
	@ApiModelProperty(value = "创建人姓名", name = "createUserName")
	@TableField("create_user_name")
	private String createUserName;

	@ApiModelProperty(value = "修改时间", name = "gmtModified")
	@TableField("gmt_modified")
	private Date gmtModified;

	@ApiModelProperty(value = "修改人", name = "modifyUser")
	@TableField("modify_user")
	private Long modifyUser;
	
	@ApiModelProperty(value = "修改人姓名", name = "modifyUserName")
	@TableField("modify_user_name")
	private String modifyUserName;

	private Integer deleted;

	private String bak1;

	private String bak2;

	private String bak3;

	private String bak4;

	private String bak5;

}
