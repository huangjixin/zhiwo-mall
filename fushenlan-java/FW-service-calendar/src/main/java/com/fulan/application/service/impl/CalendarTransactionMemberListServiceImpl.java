package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.CalendarTransactionMemberList;
import com.fulan.application.mapper.CalendarTransactionMemberListMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.CalendarTransactionMemberListService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarTransactionMemberListServiceImpl extends ServiceImpl<CalendarTransactionMemberListMapper, CalendarTransactionMemberList> implements CalendarTransactionMemberListService {

    @Override
    public List<CalendarTransactionMemberList> selectByTransactionId(Long transactionId) {
        CalendarTransactionMemberList memberList = new CalendarTransactionMemberList();
        memberList.setTransactionId(transactionId);
        return selectList(new EntityWrapper<>(memberList));
    }

    @Override
    public void saveCalendarTransactionMemberList(List<CalendarTransactionMemberList> memberList){
        GenerateVOUtil.generate(memberList);
        insertBatch(memberList);
    }

    @Override
    public void deleteByTransactionId(Long transactionId) {
        CalendarTransactionMemberList memberList = new CalendarTransactionMemberList();
        memberList.setTransactionId(transactionId);
        delete(new EntityWrapper<>(memberList));
    }
}