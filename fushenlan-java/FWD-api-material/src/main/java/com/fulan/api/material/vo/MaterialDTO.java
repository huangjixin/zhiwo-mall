package com.fulan.api.material.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资料表，讲师，管理员共用
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "MaterialDTO", description = "资料返回模型，讲师，管理员共用")
@TableName("materialDTO")
public class MaterialDTO implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "资料编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "资料名称", name = "name")
    private String name;

    @ApiModelProperty(value = "资料类型(视频/SCORM/PPT/WORD/EXCEL)", name = "type")
    private Integer type;

    @ApiModelProperty(value = "资料说明", name = "description")
    private String description;

    @ApiModelProperty(value = "一级分类(用户组)", name = "groupId")
    @TableField("group_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long groupId;

    @ApiModelProperty(value = "二级分类(用户组下面的标签)", name = "tagId")
    @TableField("tag_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long tagId;

    @ApiModelProperty(value = "是否分享(1 是， 0 否)", name = "isShare")
    @TableField("is_share")
    private Integer isShare;

    @ApiModelProperty(value = "是否关联计划(1 是， 0 否)", name = "isRelatePlan")
    @TableField("is_relate_plan")
    private Integer isRelatePlan;

    @ApiModelProperty(value = "提交者", name = "createUser")
    @TableField("create_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @ApiModelProperty(value = "上传时间", name = "gmtCreate")
    @TableField("gmt_create")
    private Date gmtCreate;

    @ApiModelProperty(value = "修改人", name = "modifyUser")
    @TableField("modify_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long modifyUser;

    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField("gmt_modified")
    private Date gmtModified;

    @ApiModelProperty(value = "课程名", name = "courseName")
    @TableField("courseName")
    private String courseName;

    @ApiModelProperty(value = "计划名", name = "planName")
    @TableField("planName")
    private String planName;

    @ApiModelProperty(value = "资料名关键字", name = "keyWord")
    @TableField("keyWord")
    private String keyWord;

    @ApiModelProperty(value = "开始时间", name = "startTime")
    @TableField("startTime")
    private Date startTime;

    @ApiModelProperty(value = "结束时间", name = "endTime")
    @TableField("endTime")
    private Date endTime;


}
