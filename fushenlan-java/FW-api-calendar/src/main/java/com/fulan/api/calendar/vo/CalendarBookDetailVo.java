package com.fulan.api.calendar.vo;

import java.io.Serializable;
import java.util.List;

import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;

import lombok.Data;
/**
 * <p>
 * 日历加每日详情Vo
 * </p>
 *
 */
@Data
public class CalendarBookDetailVo implements Serializable{

	private static final long serialVersionUID = 8601695495610970055L;
	private CalendarBook calendarBook;
	//日历每日详情
	private List<CalendarDailyTransaction> calendarDailyTransactionList;
}
