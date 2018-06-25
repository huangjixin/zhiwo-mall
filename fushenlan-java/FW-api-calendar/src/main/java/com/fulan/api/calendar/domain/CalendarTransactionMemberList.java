package com.fulan.api.calendar.domain;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fulan.application.util.json.LongJsonDeserializer;
import com.fulan.application.util.json.LongJsonSerializer;
import com.fulan.application.util.util.IdAnnon;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@Api(tags = "CalendarTransactionMemberList", description = "参会人员")
@TableName("iris_transaction_member_list")
public class CalendarTransactionMemberList implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参会人员id", name = "id")
    @JsonSerialize(using = LongJsonSerializer.class)
    @JsonDeserialize(using = LongJsonDeserializer.class)
    @IdAnnon
    private Long id;

    @ApiModelProperty(value = "日常事务编号", name = "transactionId")
    private Long transactionId;

    @ApiModelProperty(value = "参会人员工号/代理人编号", name = "agentCode")
    private String agentCode;

    @ApiModelProperty(value = "与会人员姓名", name = "personalName")
    private String personalName;

    @ApiModelProperty(value = "参会人员手机号", name = "mobile")
    private String mobile;

}