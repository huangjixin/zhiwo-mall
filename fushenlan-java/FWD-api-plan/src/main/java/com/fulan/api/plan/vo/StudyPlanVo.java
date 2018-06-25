package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 我的计划视图
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "StudyPlanVo", description = "我的计划视图类")
public class StudyPlanVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5376445240469294296L;
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;//计划ID

	private String name;//计划名称

	private Date endDate;//结束日期

	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long currentTime;//当前时间

	private String endTime;

	private Integer isExpiredAlarm;//是否启用提醒
	
	private Integer isRewardPoint;//是否奖励积分

	private String isRewardCertification;//是否奖励证书

	private String isRewardQualification;//是否奖励资格

	private String learningProgress;//学习进度

	private Integer planType;//计划类型

}
