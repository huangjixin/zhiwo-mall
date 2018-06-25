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
@Api(tags = "CalendarDailyTransaction", description = "日历详情")
@TableName("iris_calendar_daily_transaction")
public class CalendarDailyTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "日历详情id", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "日历id", name = "calendarId")
    private Long calendarId;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "日历时间", name = "day")
    private Date day;

    @ApiModelProperty(value = "星期几", name = "dayOfWeek")
    private Integer dayOfWeek;

    @ApiModelProperty(value = "类型,1_节假日(法定节假日和双/单休),2_工作日", name = "transationType")
    private Integer transationType;
    
    @ApiModelProperty(value = "类型,0_基础考勤,1_活动考勤", name = "attendanceType")
    private Integer attendanceType;

}