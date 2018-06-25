package com.fulan.api.course.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;

/**
 * <p>
 * 基础课程
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
public class CourseCMSVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "课程编号", name = "id")
    private Long id;

    @ApiModelProperty(value = "课程名称", name = "name")
    private String name;

    @ApiModelProperty(value = "线上/线下（1 表示线上， 0 表示线下）", name = "isOnline")
    private Integer isOnline;

    @ApiModelProperty(value = "课程说明", name = "description")
    private String description;

    @ApiModelProperty(value = "课程缩略图路径", name = "bannerPath")
    private String bannerPath;

    @ApiModelProperty(value = "视频/SCORM/PPT/WORD/EXCEL", name = "type")
    private Integer type;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "用户组/角色编号", name = "groupId")
    private Long groupId;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "用户组/角色下的标签编号", name = "tagId")
    private Long tagId;

    @ApiModelProperty(value = "至少学习分钟数", name = "learningTime")
    private Integer learningTime;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "课程讲师", name = "lecturer")
    private Long lecturer;

    @ApiModelProperty(value = "是否公开 1 表示是， 0 表示否，默认否", name = "isShare")
    private Integer isShare;

    @ApiModelProperty(value = "是否关联计划 1 表示是， 0 表示否，默认否", name = "isRelatePlan")
    private Integer isRelatePlan;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "创建人", name = "createUser")
    private Long createUser;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date gmtCreate;

    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @ApiModelProperty(value = "修改人", name = "modifyUser")
    private Long modifyUser;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    private Date gmtModified;
    
    private String tagName; //标签名称 （二级分类）
    
    private String groupName; //用户组名称(一级分类)


}
