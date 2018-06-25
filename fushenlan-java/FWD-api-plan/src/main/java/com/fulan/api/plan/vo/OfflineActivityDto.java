package com.fulan.api.plan.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.api.plan.domain.OfflineActivity;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程详情
 */
@Data
public class OfflineActivityDto extends OfflineActivity implements Serializable {

    private static final long serialVersionUID = 1L;

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

    @ApiModelProperty(value = "报名数",name="signUpNumber")
    private Integer signUpNumber;

    @ApiModelProperty(value = "资格名称",name="qualificationName")
    private String qualificationName;

    @ApiModelProperty(value = "证书名称",name="certificateName")
    private String certificateName;

    @ApiModelProperty(value = "证书路径",name="certificatePath")
    private String certificatePath;

    @ApiModelProperty(value = "报名开始时间", name = "enterStartTime")
    private Long enterStartTime;

    @ApiModelProperty(value = "报名结束时间", name = "enterEndTime")
    private Long enterEndTime;

    @ApiModelProperty(value = "上课开始时间", name = "startTime")
    private Long startTime;

    @ApiModelProperty(value = "上课结束时间", name = "endTime")
    private Long endTime;

    @ApiModelProperty(value = "是否可以报名1：可以，0：不可以", name = "hasAuthority")
    private Integer hasAuthority;


    @ApiModelProperty(value = "服务器当前时间",name="severTime")
    private Date severTime =new Date();

    @ApiModelProperty(value = "评论Id",name="commentId")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long commentId;


    public Long getEnterStartTime() {
        return this.getEnterStartDate()==null?null:this.getEnterStartDate().getTime();
    }

    public Long getEnterEndTime() {
        return this.getEnterEndDate()==null?null:this.getEnterEndDate().getTime();
    }

    public Long getStartTime() {
        return this.getStartDate()==null?null:this.getStartDate().getTime();
    }

    public Long getEndTime() {
        return this.getEndDate()==null?null:this.getEndDate().getTime();
    }
}
