package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.calendar.domain.CalendarTransaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CalendarTransactionMapper extends BaseMapper<CalendarTransaction> {
}