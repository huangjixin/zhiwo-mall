package com.fulan.api.plan.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

@Data
public class ClassPlanVo implements Serializable,Comparable<ClassPlanVo> {
	
	private static final long serialVersionUID = 1L;
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long id;

	private String name;

	private String code;

	private String description;
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long groupId;
	@JsonSerialize(using = LongJsonSerializer.class)
	@JsonDeserialize(using = LongJsonDeserializer.class)
	private Long tagId;

	private Integer isTrainingNew;

	private Integer isTrainingAgent;

	private Integer isTrainingStaff;
	
	private Integer isSign;

	private Integer userLimit;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date endDate;

	@ApiModelProperty(value = "是否需要按照顺序学习（0:不需要。 1:需要）",name="isSeq")
	private Integer isSeq;

	private Integer isFree;

	private Integer exchangePoint;

	private Integer isRewardPoint;

	private Integer isRewardCertification;

	private Integer isRewardQualification;

	private Integer rewardPoint;

    private Long certificationId;

    private Long qualificationId;

	private Integer isAllowExpired;

	private Integer state;

	private Integer isExpiredAlarm;

	private Long createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date gmtCreate;

	private Long modifyUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date gmtModified;
	
	private String classNum;

	private Integer isFinished;

	private Integer planType;
	
	private Integer openRange;

	private List<PlanCourseVo> planCourses;

	private String learningProgress;//学习进度

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;
		ClassPlanVo that = (ClassPlanVo) o;
		return Objects.equals(isFinished, that.isFinished);
	}

	@Override
	public int hashCode() {

		return Objects.hash(super.hashCode(), isFinished);
	}

	@Override
	public int compareTo(ClassPlanVo o) {
		return compare(this.isFinished,o.isFinished);
	}

	/**
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static int compare(Integer param1, Integer param2) {
		return (param1 > param2 ? 1 :
				(param1 == param2 ? 0 : -1));
	}
}
