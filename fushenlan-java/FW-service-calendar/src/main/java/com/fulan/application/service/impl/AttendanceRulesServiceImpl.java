package com.fulan.application.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.AttendanceObjects;
import com.fulan.api.calendar.domain.AttendanceRules;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.domain.CalendarDailyTransaction;
import com.fulan.api.calendar.vo.AttendanceRulesVo;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.application.mapper.AttendanceObjectsMapper;
import com.fulan.application.mapper.AttendanceRulesMapper;
import com.fulan.application.mapper.CalendarBookMapper;
import com.fulan.application.mapper.CalendarDailyTransactionMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.AttendanceRulesService;
import com.fulan.application.session.SessionUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

@Service
@Transactional
public class AttendanceRulesServiceImpl extends ServiceImpl<AttendanceRulesMapper, AttendanceRules> implements AttendanceRulesService{
	
	@Autowired
	private AttendanceRulesMapper attendanceRulesMapper; 
	
	@Autowired
	private AttendanceObjectsMapper attendanceObjectsMapper;
	
	@Autowired
	private CalendarBookMapper calendarBookMapper;
	
	@Autowired
	private CalendarDailyTransactionMapper calendarDailyTransactionMapper;
	
	@Autowired
	private AccountService accountService;
	
	/* 
	 * 查询考勤规则
	 */
	@Override
	public Response<AttendanceRulesVo> getAttendanceRulesById(long attendanceRulesId) {
		//查询考勤规则
		AttendanceRules attendanceRules = attendanceRulesMapper.selectById(attendanceRulesId);
		//查询考勤对象
		List<AttendanceObjects> attendanceObjects = attendanceObjectsMapper.selectList(new EntityWrapper<AttendanceObjects>().eq("rule_id", attendanceRulesId));
		//查询日历及详情
		CalendarBook calendarBook = calendarBookMapper.selectById(attendanceRules.getCalendarId());
		List<CalendarDailyTransaction> calendarDailyTransactionList = calendarDailyTransactionMapper.selectList(new EntityWrapper<CalendarDailyTransaction>().eq("calendar_id", attendanceRules.getCalendarId()));
		//组装返回Vo
		AttendanceRulesVo attendanceRulesVo = new AttendanceRulesVo();
		attendanceRulesVo.setAttendanceObjectsList(attendanceObjects);
		attendanceRulesVo.setAttendanceRules(attendanceRules);
		attendanceRulesVo.setCalendarBook(calendarBook);
		attendanceRulesVo.setDailyTransactionList(calendarDailyTransactionList);
		Response<AttendanceRulesVo> resp = new Response<AttendanceRulesVo>(Response.SUCCESS,"查询考勤成功");
		resp.setData(attendanceRulesVo);
		return resp;
	}

	/* 
	 * 新增或修改考勤规则
	 */
	@Override
	public Response<Integer> addOrUpdateAttendanceRulesVo(AttendanceRulesVo attendanceRulesVo) {
		//得到登录用户id和name
		long userId = attendanceRulesVo.getCurrentUserId();
		String userName = attendanceRulesVo.getCurrentUserName();
		
		//插入日历
		CalendarBook calendarBook = attendanceRulesVo.getCalendarBook();
		long calendarBookId = Idfactory.generate();
		calendarBook.setId(calendarBookId);
		calendarBook.setCreateUser(userId);
		calendarBook.setGmtTime(new Date());
		calendarBook.setModifyUser(userId);
		calendarBook.setGmtModified(new Date());
		calendarBookMapper.insertSelective(calendarBook);
		
		
		//插入或修改考勤规则
		AttendanceRules attendanceRules = attendanceRulesVo.getAttendanceRules();
		long attendanceId = Idfactory.generate();
		if(attendanceRules.getId() == null){
			//新增
			attendanceRules.setId(attendanceId);
			attendanceRules.setCreateUser(userId);
			attendanceRules.setModifyUser(userId);
			attendanceRules.setGmtTime(new Date());
			attendanceRules.setGmtModified(new Date());
			attendanceRules.setCalendarId(calendarBookId);
			attendanceRules.setCreateUserName(userName);
			attendanceRules.setModifyUserName(userName);
			this.insert(attendanceRules);
		}else{
			//修改
			attendanceId = attendanceRules.getId();
			//删除原来日历及日历详情及考勤对象
			calendarBookMapper.deleteById(attendanceRules.getCalendarId());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("calendar_id", attendanceRules.getCalendarId());
			calendarDailyTransactionMapper.deleteByMap(map);
			map.clear();
			map.put("rule_id", attendanceId);
			attendanceObjectsMapper.deleteByMap(map);
			
			attendanceRules.setGmtModified(new Date());
			attendanceRules.setModifyUser(userId);
			attendanceRules.setModifyUserName(userName);
			attendanceRules.setCalendarId(calendarBookId);
			this.insertOrUpdate(attendanceRules);
		}
		
		//插入考勤对象
		List<AttendanceObjects> attendanceObjectsList = attendanceRulesVo.getAttendanceObjectsList();
		if(attendanceObjectsList!=null&&attendanceObjectsList.size()!=0){
			for(AttendanceObjects attendanceObject:attendanceObjectsList){
				attendanceObject.setRuleId(attendanceId);
				attendanceObject.setId(Idfactory.generate());
				attendanceObjectsMapper.insert(attendanceObject);
			}
		}
		
		//插入日历详情
		List<CalendarDailyTransaction> dailyTransactionList = attendanceRulesVo.getDailyTransactionList();
		if(dailyTransactionList!=null&&dailyTransactionList.size()!=0){
			for(CalendarDailyTransaction calendarDailyTransaction:dailyTransactionList){
				//删除数据库中日期和考勤类型相同的数据
//				calendarDailyTransactionMapper.delete(new EntityWrapper<CalendarDailyTransaction>().eq("day", calendarDailyTransaction.getDay()).eq("attendance_type", calendarDailyTransaction.getAttendanceType()));
				//插入新数据
				calendarDailyTransaction.setCalendarId(calendarBookId);
				calendarDailyTransaction.setId(Idfactory.generate());
				calendarDailyTransactionMapper.insert(calendarDailyTransaction);
			}
		}
		Response<Integer> resp = new Response<Integer>(Response.SUCCESS,"新增或修改考勤规则成功");
		return resp;
	}
	
	
/*	public int insertSelective(AttendanceRules attendanceRules) {
		return attendanceRulesMapper.insertSelective(attendanceRules);
	}

	public List<AttendanceRules> selectAllAttendanceRoles() {
		
		return attendanceRulesMapper.selectAllAttendanceRules();
	}*/
	
	/* 
	 * 查询考勤规则
	 */
	@Override
	public PageInfo<AttendanceRules> selectAttendanceRulesByParams(Page<CourseCMSVo> page,Integer month, Integer rulesType, String keyWord,int pageNo,int pageSize) {
		PageInfo<AttendanceRules> pageInfo = new PageInfo<AttendanceRules>();
		int total = attendanceRulesMapper.selectAttendanceRulesCountByParams(month, rulesType, keyWord);
		pageInfo.setRecords(attendanceRulesMapper.selectAttendanceRulesByParams(page, month, rulesType, keyWord,pageNo,pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	public Response<Integer> deleteAttendanceRules(long attendanceRulesId) {
		//删除考勤规则及日历及日历详情及考勤对象
		AttendanceRules ar = attendanceRulesMapper.selectById(attendanceRulesId);
		Long calendarId = ar.getCalendarId();
		
		attendanceRulesMapper.deleteById(attendanceRulesId);
		calendarBookMapper.deleteById(calendarId);
		calendarDailyTransactionMapper.delete(new EntityWrapper<CalendarDailyTransaction>().eq("calendar_id", calendarId));
		attendanceObjectsMapper.delete(new EntityWrapper<AttendanceObjects>().eq("rule_id", attendanceRulesId));
		
		Response<Integer> resp = new Response<Integer>(Response.SUCCESS,"删除考勤规则成功");
		return resp;
	}



}
