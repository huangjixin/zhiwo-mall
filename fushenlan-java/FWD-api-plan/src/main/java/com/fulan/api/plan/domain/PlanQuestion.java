package com.fulan.api.plan.domain;

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
 * 课程提问回复表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
@Api(tags = "PlanQuestion", description = "课程提问回复表")
@TableName("el_plan_question")

public class PlanQuestion implements Serializable {

    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "问题编号", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long id;

    @ApiModelProperty(value = "公开课程和线下活动的编号", name = "courseId")
    @TableField("course_id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long courseId;

    @ApiModelProperty(value = "公开课/线下课程", name = "courseType")
    @TableField("course_type")
    private Integer courseType;

    @ApiModelProperty(value = "问题描述", name = "quesDesc")
    @TableField("ques_desc")
    private String quesDesc;

    @ApiModelProperty(value = "开启/隐藏（1 表示开启， 0 表示隐藏）", name = "isOpen")
    @TableField("is_open")
    private Integer isOpen;

    @ApiModelProperty(value = "回复人", name = "answerUser")
    @TableField("answer_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long answerUser;

    @ApiModelProperty(value = "回复人姓名", name = "answerUserName")
    @TableField("answer_user_name")
    private String answerUserName;

    @ApiModelProperty(value = "回复内容", name = "quesAnswer")
    @TableField("ques_answer")
    private String quesAnswer;

    @ApiModelProperty(value = "回复时间", name = "answerTime")
    @TableField("answer_time")
    private Date answerTime;
    @ApiModelProperty(value = "创建人姓名", name = "createName")
    @TableField("create_name")
    private String createName;

    @ApiModelProperty(value = "创建人", name = "createUser")
    @TableField("create_user")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @ApiModelProperty(value = "创建时间", name = "gmtCreate")
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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getQuesDesc() {
        return quesDesc;
    }

    public void setQuesDesc(String quesDesc) {
        this.quesDesc = quesDesc;
    }

    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }

    public Long getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(Long answerUser) {
        this.answerUser = answerUser;
    }

    public String getQuesAnswer() {
        return quesAnswer;
    }

    public void setQuesAnswer(String quesAnswer) {
        this.quesAnswer = quesAnswer;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(Long modifyUser) {
        this.modifyUser = modifyUser;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
}
