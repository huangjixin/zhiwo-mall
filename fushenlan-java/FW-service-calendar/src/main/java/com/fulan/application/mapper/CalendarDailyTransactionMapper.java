package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CalendarDailyTransactionMapper extends BaseMapper<CalendarDailyTransaction> {

    List<CalendarDailyTransaction> findCalendarByCalendarDate(@Param("agentCode")String agentCode,
                                            @Param("orgId")String orgId, @Param("calendarDate")String calendarDate);

    Integer selectCountByCalendarDate(@Param("agentCode")String agentCode,
                                    @Param("orgId")String orgId, @Param("calendarDate")String calendarDate);
}