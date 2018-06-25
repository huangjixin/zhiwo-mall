package com.fulan.api.calendar.domain;

import com.baomidou.mybatisplus.annotations.TableField;
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
@Api(tags = "CalendarTransaction", description = "日历事务处理")
@TableName("iris_calendar_transaction")
public class

CalendarTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "事务id", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "日历编号", name = "id")
    @TableField("calendar_id")
    private Long calendarId;

    @ApiModelProperty(value = "主题", name = "theme")
    @TableField("theme")
    private String theme;

    @ApiModelProperty(value = "代理人编号", name = "agentCode")
    @TableField("agent_code")
    private String agentCode;

    @ApiModelProperty(value = "1.会议邀请;2.客户拜访;3.日常备忘;4.消息列表;5.其他", name = "transactionType")
    @TableField("transaction_type")
    private Byte transactionType;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @ApiModelProperty(value = "日期", name = "date")
    @TableField("date")
    private Date date;

    @ApiModelProperty(value = "具体哪一天(比如28)", name = "day")
    @TableField("day")
    private Integer day;


    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "事务将来开始时间", name = "transactionFutureStartTime")
    @TableField("transaction_future_start_time")
    private Date transactionFutureStartTime;

    
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "事务将来结束时间", name = "transactionFutureEndTime")
    @TableField("transaction_future_end_time")
    private Date transactionFutureEndTime;

    @ApiModelProperty(value = "用于事务类型为拜访客户时使用的字段", name = "transationStage")
    @TableField("transation_stage")
    private Integer transationStage;

    @ApiModelProperty(value = "事务描述", name = "transactionDesc")
    @TableField("transaction_desc")
    private String transactionDesc;

    @ApiModelProperty(value = "事务发生地址", name = "transactionAddress")
    @TableField("transaction_address")
    private String transactionAddress;

    @ApiModelProperty(value = "事务处理结果(用于拜访客户时事务的处理结果)", name = "transactionResult")
    @TableField("transaction_result")
    private String transactionResult;

    @ApiModelProperty(value = "事务关联客户", name = "transactionRefCustomer")
    @TableField("transaction_ref_customer")
    private Long transactionRefCustomer;

    @ApiModelProperty(value = "事务关联客户姓名", name = "customerName")
    @TableField("customer_name")
    private String customerName;

    @ApiModelProperty(value = "备注", name = "remark")
    @TableField("remark")
    private String remark;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "创建时间", name = "gmtTime")
    @TableField("gmt_time")
    private Date gmtTime;

    @ApiModelProperty(value = "创建人id", name = "createUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @TableField("create_user")
    private Long createUser;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @ApiModelProperty(value = "修改时间", name = "gmtModified")
    @TableField("gmt_modified")
    private Date gmtModified;

    @ApiModelProperty(value = "修改人id", name = "modifyUser")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @TableField("modify_user")
    private Long modifyUser;

    @ApiModelProperty(value = "是否删除", name = "deleted")
    @TableField("deleted")
    private Integer deleted;
    
    @ApiModelProperty(value = "消息状态:未发布_0,已发布_1,已撤回_2", name = "state")
    @TableField("state")
    private String state;
    
    @ApiModelProperty(value = "1_即使发送       2_定时发送", name = "sendType")
    @TableField("send_type")
    private Byte sendType;
    
    @ApiModelProperty(value = "1：所有人（创建人所在公司，总公司则其下所有分公司人员有效）2：指定人员关联iris_transaction_member_list'",name = "specifyType")
    private String specifyType;
    
    @ApiModelProperty(value = "消息创建人公司ID，可能为分公司ID或总公司ID" ,name = "companyId")
    private String companyId;
    
}