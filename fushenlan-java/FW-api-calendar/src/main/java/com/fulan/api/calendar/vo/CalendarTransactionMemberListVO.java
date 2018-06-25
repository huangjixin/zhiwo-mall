package com.fulan.api.calendar.vo;

import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.api.calendar.domain.CalendarTransactionMemberList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Api(tags = "CalendarTransactionMemberListVO", description = "事务VO")
public class CalendarTransactionMemberListVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "参会人员集合", name = "memberLists")
    private List<CalendarTransactionMemberList> memberLists;

    @ApiModelProperty(value = "事物", name = "calendarTransaction")
    private CalendarTransaction calendarTransaction;
    
    
}