package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.vo.CalendarBookDetailVo;
import com.fulan.api.calendar.vo.CalendarBookVo;
import com.fulan.application.mapper.CalendarBookMapper;
import com.fulan.application.service.CalendarBookService;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarBookServiceImpl extends ServiceImpl<CalendarBookMapper, CalendarBook> implements CalendarBookService {
	
	@Autowired 
	private CalendarBookMapper CalendarBookMapper;

	@Override
	public PageInfo<CalendarBookVo> findCalendarByName(String calendarName, int pageNo, int pageSize) {
		Page<CalendarBookVo> page = new Page<CalendarBookVo>(pageNo,pageSize); 
		PageInfo<CalendarBookVo> PageInfo = new PageInfo<CalendarBookVo>();
		int total = CalendarBookMapper.countCalenderByName(calendarName);
		//分页后数据
		PageInfo.setRecords(CalendarBookMapper.findCalenderByName(page,calendarName));
		PageInfo.setPageSize(page.getSize());
		PageInfo.setPageNo(page.getCurrent());
		PageInfo.setPageTotal(total);
		return PageInfo;
	}

	@Override
	public CalendarBookDetailVo findCalendarById(String calendarId) {
		if(!StringUtils.isEmptry(calendarId)){
			return CalendarBookMapper.selectCalendarBookDetail(Long.parseLong(calendarId));
		}
		return null;
	}



	@Override
	public int findMaxMatainMonth() {
		return CalendarBookMapper.findMaxMatainMonth();
	}

	@Override
	public CalendarBook findCalendarByCalendarDate(Integer calendarDate) {
		CalendarBook transaction = new CalendarBook();
		//transaction.setOrgId();
		transaction.setCalendarDate(calendarDate);
		return selectOne(new EntityWrapper<>(transaction));
	}

	@Override
	public void addCalendarBook(CalendarBook calendarBook) {
		CalendarBookMapper.insert(calendarBook);
	}
}