package com.fulan.api.calendar.vo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
/**
 *考勤结果列表制定页
 * @author gaoyufei
 *
 */
@Data
public class AttendanceHistoryVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Date checkTime;//打卡时间
	
	private Date checkDate;//打卡时间
	
	private String agentCode;//工号
	
	private String name;//考勤人
	
	private String companyName;//分公司
	
	private String levelName;//职级

}
