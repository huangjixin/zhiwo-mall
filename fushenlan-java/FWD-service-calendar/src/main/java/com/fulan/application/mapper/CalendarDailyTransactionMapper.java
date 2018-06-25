package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarDailyTransactionMapper extends BaseMapper<CalendarDailyTransaction> {
}