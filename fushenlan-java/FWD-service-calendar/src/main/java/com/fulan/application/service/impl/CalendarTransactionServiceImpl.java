package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.CalendarBook;
import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.api.calendar.domain.CalendarTransactionMemberList;
import com.fulan.api.calendar.vo.CalendarTransactionMemberListVO;
import com.fulan.application.mapper.CalendarTransactionMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.CalendarBookService;
import com.fulan.application.service.CalendarTransactionMemberListService;
import com.fulan.application.service.CalendarTransactionService;
import com.fulan.application.util.domain.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Service
public class CalendarTransactionServiceImpl extends ServiceImpl<CalendarTransactionMapper, CalendarTransaction> implements CalendarTransactionService {

    @Autowired
    private CalendarBookService calendarBookService;
    @Autowired
    private CalendarTransactionMemberListService memberListService;
    @Autowired
    private CalendarTransactionMapper calendarTransactionMapper;

    @Override
    public List<CalendarTransaction> findTransactionByCalendarDate(Integer calendarDate) {
        CalendarBook calendarBook = calendarBookService.findCalendarByCalendarDate(calendarDate);
        CalendarTransaction transaction = new CalendarTransaction();
        transaction.setCalendarId(calendarBook.getId());
        return selectList(new EntityWrapper<>(transaction));
    }

    @Override
    public void saveTransactionByCalendar(CalendarTransactionMemberListVO memberListVO) {
        CalendarTransaction transaction = memberListVO.getCalendarTransaction();
        Long id = GenerateVOUtil.generate(transaction);
        insert(transaction);
        List<CalendarTransactionMemberList> memberLists = memberListVO.getMemberLists();
        if (memberLists!=null&&memberLists.size()>0){
            for(CalendarTransactionMemberList calendarTransactionMemberList:memberLists){
                calendarTransactionMemberList.setTransactionId(id);
            }
            memberListService.saveCalendarTransactionMemberList(memberLists);
        }
    }

    @Override
    public CalendarTransactionMemberListVO findTransactionById(Long id) {
        CalendarTransactionMemberListVO transactionMemberListVO = new CalendarTransactionMemberListVO();
        transactionMemberListVO.setCalendarTransaction(selectById(id));
        transactionMemberListVO.setMemberLists(memberListService.selectByTransactionId(id));
        return transactionMemberListVO;
    }

   
	@Override
	public Page<CalendarTransaction> getTransactionByPage(Map<String, Object> transactionParams) {
		Page<CalendarTransaction> page = new Page<>((Integer)transactionParams.get("pageNo"), (Integer)transactionParams.get("pageSize"));
		page.setRecords(calendarTransactionMapper.selectTransactionByPage(page,transactionParams));
		return page;
	}

	@Override
	public Boolean removeTransactionById(Long id) {
		int result = calendarTransactionMapper.deleteById(id);
		return result != 0;
	}

	@Override
	public Response<Boolean> editTransaction(CalendarTransaction calendarTransaction) {
		if(ObjectUtils.isEmpty(calendarTransaction)){
			return new Response<Boolean>(Response.ERROR,"对象不能为空！");
		}
		int result = calendarTransactionMapper.updateById(calendarTransaction);
		if(result != 0){
			return new Response<Boolean>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		}
		return new Response<Boolean>(Response.ERROR,Response.ERROR_MESSAGE);
	}

	@Override
	public Response<Boolean> addTransaction(CalendarTransaction calendarTransaction) {
		if(ObjectUtils.isEmpty(calendarTransaction)){
			return new Response<Boolean>(Response.ERROR,"对象不能为空！");
		}
		int result = calendarTransactionMapper.insert(calendarTransaction);
		if(result != 0){
			return new Response<Boolean>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
		}
		return new Response<Boolean>(Response.ERROR,Response.ERROR_MESSAGE);
	}

}