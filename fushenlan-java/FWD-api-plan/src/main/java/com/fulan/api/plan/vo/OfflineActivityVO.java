package com.fulan.api.plan.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 线下活动
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "OfflineActivity", description = "线下活动")

public class OfflineActivityVO implements Serializable {

    private static final long serialVersionUID = 1L;


	@ApiModelProperty(value = "活动编号",name="id")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	@ApiModelProperty(value = "课程id编号",name="courseId")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long courseId;



@ApiModelProperty(value = "活动名称",name="name")
	private String name;



		@ApiModelProperty(value = "活动缩略图",name="bannerPath")
		private String bannerPath;


@ApiModelProperty(value = "可获积分",name="rewardPoint")
	private Integer rewardPoint;

	@ApiModelProperty(value = "讲师id",name="gmtModified")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long lecturer;

	@ApiModelProperty(value = "报名人数限制",name="limitEnter")
	private Integer limitEnter;

	@ApiModelProperty(value = "报名数",name="signUpNumber")
	private Integer signUpNumber;

	@ApiModelProperty(value = "课程开始时间",name="courseStartTime")
	private Date courseStartTime;

	/*@ApiModelProperty(value = "签到时间",name="signStartTime")
	private Date signStartTime;*/

	@ApiModelProperty(value = "课程说明",name="courseDescription")
	private String courseDescription;

	@ApiModelProperty(value = "是否收藏，有值的话就是收藏",name="courseCoolectId")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long courseCollectId;

	@ApiModelProperty(value = "是否签到，有值的话就是签到",name="enterId")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long enterId;

	@ApiModelProperty(value = "是否报名，有值的话就是报名了",name="signId")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long signId;

	@ApiModelProperty(value = "课程结束时间",name="courseEndTime")
	private Date courseEndTime;

	@ApiModelProperty(value = "资格名称",name="qualificationName")
	private String qualificationName;

	@ApiModelProperty(value = "证书名称",name="certificateName")
	private String certificateName;

	@ApiModelProperty(value = "证书路径",name="certificatePath")
	private String certificatePath;


	@ApiModelProperty(value = "报名开始时间",name="enterStartTime")
	private Date enterStartTime;

	@ApiModelProperty(value = "报名开始时间",name="enterEndTime")
	private Date enterEndTime;


	@ApiModelProperty(value = "活动开放时间类型-0长期有效，1指定时间", name = "openClassType")
	private Integer openClassType;

	@ApiModelProperty(value = "开始时间 当open_class_type为1时有效", name = "startDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startDate;

	@ApiModelProperty(value = "结束日期 当open_class_type为1时有效", name = "endDate")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endDate;

	@ApiModelProperty(value = "报名开始时间", name = "enterStartDate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date enterStartDate;

	@ApiModelProperty(value = "报名结束时间", name = "enterEndDate")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date enterEndDate;

	@ApiModelProperty(value = "报名类型- 是否需要报名，1需要，0不需要", name = "isNeedEnter")
	private Integer isNeedEnter;

	@ApiModelProperty(value = "签到类型- 是否需要签到，1需要，0不需要", name = "isNeedSign")
	private Integer isNeedSign;

	@ApiModelProperty(value = "签到类型-不需要报名也能签到，1能，0不能", name = "isNoneedEnter")
	private Integer isNoneedEnter;

	@ApiModelProperty(value = "签到时间类型-1课程开始前，2课程开始前到课程开始后，3课程开始后", name = "signTimeType")
	private Integer signTimeType;

	@ApiModelProperty(value = "课程开始前", name = "beforeBegin")
	private Integer beforeBegin;

	@ApiModelProperty(value = "课程开始后", name = "afterBegin")
	private Integer afterBegin;

	@ApiModelProperty(value = "上课地点", name = "address")
	private String address;

	@ApiModelProperty(value = "服务器当前时间",name="severTime")
	private Date severTime =new Date();

	@ApiModelProperty(value = "上课开始时间", name = "startTime")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long startTime;

	@ApiModelProperty(value = "上课结束时间", name = "endTime")
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long endTime;







}
