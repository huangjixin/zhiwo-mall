package com.fulan.api.plan.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.course.domain.Course;
import com.fulan.api.material.domain.Material;
import com.fulan.api.material.vo.MaterialAttachDto;
import com.fulan.api.plan.domain.LearningProgress;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.security.domain.Account;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 公开课数据对象（供前端使用）
 * </p>
 *
 * @author Hedge
 * @since 2018-01-24
 */
@Data
public class PlanDetailDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "计划名称",name="name")
    private String  name;

    @ApiModelProperty(value = "计划编号", name = "planId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long planId;

    @ApiModelProperty(value = "计划说明",name="description")
    private String description;

    @ApiModelProperty(value = "否需积分兑换0/1（1:免费）",name="isFree")
    private Integer isFree;

    @ApiModelProperty(value = "兑换所需积分",name="exchangePoint")
    private Integer exchangePoint;

    @ApiModelProperty(value = "是否奖励积分 0/1（1表示奖励）",name="isRewardPoint")
    private Integer isRewardPoint;

    @ApiModelProperty(value = "是否奖励证书 0/1（1表示奖励）",name="isRewardCertification")
    private Integer isRewardCertification;

    @ApiModelProperty(value = "是否奖励资格 0/1（1表示奖励）",name="isRewardQualification")
    private Integer isRewardQualification;

    @ApiModelProperty(value = "可获积分",name="rewardPoint")
    private Integer rewardPoint;

    @ApiModelProperty(value = "证书路径",name="certificationName")
    private String certificationName;

    @ApiModelProperty(value = "资格",name="qualificationName")
    private String qualificationName;

    @ApiModelProperty(value = "是否开放问答", name = "isQuestion")
    private Integer isQuestion;

    @ApiModelProperty(value = "是否收藏",name="courseCollectId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long courseCollectId;


    @ApiModelProperty(value = "评论Id",name="commentId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long commentId;

    @ApiModelProperty(value = "线下活动详情",name="offlineActivityDto")
    private OfflineActivityDto offlineActivityDto;

    @ApiModelProperty(value = "相关学习资料",name="materialList")
    private List<MaterialAttachDto> materialList;
    @ApiModelProperty(value = "相关讲师列表",name="lecturerList")
    private List<Account> lecturerList;

    @ApiModelProperty(value = "是否需要按照顺序学习（0:不需要。 1:需要）",name="isSeq")
    private Integer isSeq;

    @ApiModelProperty(value = "公开课相关课程",name="planCourseDtoList")
    private List<PublicCourseDto> publicCourseDtoList;

    @ApiModelProperty(value = "线下活动相关列表",name="offlineActivityDtoList")
    private List<OfflineActivityDto> offlineActivityDtoList;

    @ApiModelProperty(value = "浏览量",name="viewsNum")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long viewsNum;

    @ApiModelProperty(value = "最新播放记录",name="learningProgress")
    private String playPath;

    @ApiModelProperty(value = "最新播放记录",name="learningProgress")
    private LearningProgress learningProgress;

    @ApiModelProperty(value = "第一个课程信息",name="firstPlanCourse")
    private PlanCourse firstPlanCourse;

    @ApiModelProperty(value = "课程计划中间表信息",name="planCourse")
    private PlanCourse planCourse;


    @ApiModelProperty(value = "课程信息",name="course")
    private Course course;




}
