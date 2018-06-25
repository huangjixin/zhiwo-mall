package com.fulan.api.plan.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import lombok.Data;

@Data
public class OfflineActivityVoFw {
	/*
	 * 主键id
	 */
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	
	/*
	 * 用户id
	 */
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long userId;

	/*
	 * 星级评分
	 */
	private String star;
	
	/*
	 * 参与人数
	 */
	private Integer enter;
	/*
	 *课程id 
	 */
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long courseId;
	
	/*
	 *线下活动名称
	 */
	private String name;
	
	/*
	 *线下活动状态 
	 */
	private Integer state;
	/*
	 * openClassType为1的时候开始结束时间有效
	 */
	private Integer openClassType;
	/*
	 *线下活动开始时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date startDate;
	/*
	 *线下活动结束时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date endDate;
	
	/*
	 *报名时间
	 */
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date gmtCreate;
	
	/*
	 * 签到状态
	 */
	private String isSign;
	
}
