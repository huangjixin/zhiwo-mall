package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.AttendanceHistory;
import com.fulan.api.calendar.domain.AttendanceObjects;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.api.calendar.vo.AttendanceRulesVo;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.application.config.Contant;
import com.fulan.application.mapper.AttendanceHistoryMapper;
import com.fulan.application.mapper.AttendanceObjectsMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.AttendanceHistoryService;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;
import com.fulan.application.util.util.JsonUtils;
import com.fulan.application.util.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AttendanceHistoryServiceImpl extends ServiceImpl<AttendanceObjectsMapper, AttendanceObjects> implements AttendanceHistoryService {

	@Autowired
	private AttendanceHistoryMapper attendanceHistoryMapper;

	@Override
	public void addAttendanceHistory(AttendanceHistory attendanceHistory) {
		long id = Idfactory.generate();
		attendanceHistory.setId(id);
		attendanceHistory.setCode("1");
		attendanceHistory.setCheckDate(new Date());
		attendanceHistory.setCheckTime(new Date());
		attendanceHistoryMapper.addAttendanceHistory(attendanceHistory);
	}

	@Override
	public List<Map<String,Object>> selectAllHistory(String calendarDate,String agentCode,String orgId,String companyId) {
		if (StringUtils.isEmptry(calendarDate)){
			//为空默认获取当月
			calendarDate = DateUtil.toDay(new Date());
		}
		List<Map<String,Object>> attendanceHistories = attendanceHistoryMapper.selectAllHistory(agentCode, orgId, calendarDate,companyId);
		return attendanceHistories;
	}

	@Override
	public Integer selectCountByCalendarDate(String calendarDate,String agentCode,String orgId) {
		if (StringUtils.isEmptry(calendarDate)){
			//为空默认获取当月
			calendarDate = DateUtil.toDay(new Date());
		}
		return attendanceHistoryMapper.selectCountByCalendarDate(agentCode,orgId,calendarDate);
	}

	@Override
	public PageInfo<AttendanceHistory> selectAttendanceResultByParams(Page<AttendanceHistory> page,String startDate, String endDate, String keyWord,
			int pageNo, int pageSize) {
		PageInfo<AttendanceHistory> pageInfo = new PageInfo<AttendanceHistory>();
		int total = attendanceHistoryMapper.selectAttendanceResultCountByParams(startDate, endDate, keyWord);
		pageInfo.setRecords(attendanceHistoryMapper.selectAttendanceResultByParams(page, startDate, endDate, keyWord,pageNo,pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}
}
