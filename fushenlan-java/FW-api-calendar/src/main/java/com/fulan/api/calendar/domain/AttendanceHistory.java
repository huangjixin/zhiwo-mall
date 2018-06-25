package com.fulan.api.calendar.domain;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.Version;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 考勤打卡记录表
 * </p>
 *
 * @author fulan123
 * @since 2018-04-09
 */
@Data
@Api(tags = "AttendanceHistory", description = "考勤打卡记录表")
@TableName("iris_attendance_history")

public class AttendanceHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键/编号", name = "id")
	private Long id;

	@ApiModelProperty(value = "编码", name = "code")
	private String code;

	@DateTimeFormat(pattern="HH:mm:ss")
	@JsonFormat(pattern="HH:mm:ss",timezone="GMT+8")
	@ApiModelProperty(value = "签到时间", name = "checkTime")
	private Date checkTime;

	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	@ApiModelProperty(value = "签到时间", name = "checkDate")
	private Date checkDate;

	@ApiModelProperty(value = "代理人工号", name = "agentCode")
	private String agentCode;
	
	@ApiModelProperty(value = "代理人姓名", name = "agentName")
	private String agentName;

}
