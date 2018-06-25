package com.fulan.api.calendar.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@Api(tags = "CalendarBook", description = "日历")
@TableName("iris_calendar_book")
public class CalendarBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日历id", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "编码", name = "code")
    private String code;

    @ApiModelProperty(value = "日历名称", name = "calendarName")
    private String calendarName;
    
    @ApiModelProperty(value = "用途", name = "purpose")
    private String purpose;

    @ApiModelProperty(value = "是否用于所有子公司(0_代表不适用,1_代表适用)", name = "isGlobalScope")
    private Integer isGlobalScope;

    @ApiModelProperty(value = "组织编号", name = "orgId")
    private String orgId;

    @ApiModelProperty(value = "操作人", name = "operateUser")
    private Long operateUser;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "操作时间", name = "operateDate")
    private Date operateDate;

    @ApiModelProperty(value = "备注", name = "remark")
    private String remark;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间", name = "gmtTime")
    private Date gmtTime;

    @ApiModelProperty(value = "创建人id", name = "createUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间", name = "gmtModified")
    private Date gmtModified;

    @ApiModelProperty(value = "修改人id", name = "modifyUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    private Long modifyUser;

    @ApiModelProperty(value = "是否删除", name = "deleted")
    private Integer deleted;

    private String bak1;

    private String bak2;

    private String bak3;

    private String bak4;

    private String bak5;

}