package com.fulan.api.plan.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 课程大纲课程数据对象（供前端使用）
 * </p>
 *
 * @author Hedge
 * @since 2018-01-24
 */
@Data
public class PlanCourseDto extends PlanCourse implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "课程名称",name="name")
    private String name;

    @ApiModelProperty(value = "课程说明",name="description")
    private String description;

    @ApiModelProperty(value = "课程缩略图路径",name="bannerPath")
    private String bannerPath;

    @ApiModelProperty(value = "视频/SCORM/PPT/WORD/EXCEL",name="type")
    private Integer type;

    @ApiModelProperty(value = "至少学习分钟数",name="learningTime")
    private Integer learningTime;

    @ApiModelProperty(value = "课程讲师",name="lecturer")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long lecturer;
    @ApiModelProperty(value = "是否完成学习",name="finishFlag")
    private Integer finishFlag;

}
