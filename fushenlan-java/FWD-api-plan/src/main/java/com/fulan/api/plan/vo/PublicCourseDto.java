package com.fulan.api.plan.vo;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.PublicClass;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 计划与课程的详细
 * </p>
 *
 * @author Hedge
 * @since 2018-01-24
 */
@Data
public class PublicCourseDto extends PublicClass{
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "课程编号",name="courseId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long courseId;

    @ApiModelProperty(value = "课程名称",name="name")
    private String name;

    @ApiModelProperty(value = "课程说明",name="description")
    private String description;

    @ApiModelProperty(value = "课程缩略图路径",name="bannerPath")
    private String bannerPath;

    @ApiModelProperty(value = "视频/SCORM/PPT/WORD/EXCEL",name="type")
    private Integer type;

    @ApiModelProperty(value = "浏览量",name="viewsNum")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long viewsNum;

    @ApiModelProperty(value = "证书编号",name="certificationName")
    private String certificationName;

    @ApiModelProperty(value = "资格编号",name="qualificationName")
    private String qualificationName;

}
