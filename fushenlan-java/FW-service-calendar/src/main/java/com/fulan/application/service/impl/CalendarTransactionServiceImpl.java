package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.CalendarTransaction;
import com.fulan.api.calendar.domain.CalendarTransactionMemberList;
import com.fulan.api.calendar.vo.CalendarTransactionMemberListVO;
import com.fulan.application.mapper.CalendarTransactionMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.receive.RabbitmqConst;
import com.fulan.application.service.CalendarBookService;
import com.fulan.application.service.CalendarTransactionMemberListService;
import com.fulan.application.service.CalendarTransactionService;
import com.fulan.application.util.date.DateUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Service
public class CalendarTransactionServiceImpl extends ServiceImpl<CalendarTransactionMapper, CalendarTransaction> implements CalendarTransactionService {

	private static final Logger logger = LoggerFactory.getLogger(CalendarTransactionServiceImpl.class);

	@Autowired
	private CalendarBookService calendarBookService;
	@Autowired
	private CalendarTransactionMemberListService memberListService;
	@Autowired
	private CalendarTransactionMapper calendarTransactionMapper;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public List<CalendarTransaction> findTransactionByCalendarDate(String calendarDate,String agentCode) {
		if (StringUtils.isEmpty(calendarDate)){
			calendarDate = DateUtil.toDay(new Date());
		}
		return calendarTransactionMapper.findTransactionByCalendarDate(agentCode,calendarDate);
	}

	@Override
	public List<Map<String,Object>> findTransactionByCalendarDateNew(String agentCode,String calendarDate) {

		if (StringUtils.isEmpty(calendarDate)){
			calendarDate = DateUtil.toDay(new Date());
		}
		List<CalendarTransaction> calendarTransactionList =
				calendarTransactionMapper.findTransactionByCalendarDate(agentCode,calendarDate);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<CalendarTransaction> transactionList = new ArrayList<CalendarTransaction>();
		for(int i = 1 ;i<calendarTransactionList.size();i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("date",DateUtil.toMonthAndDay(calendarTransactionList.get(i-1).getDate()));
//			if(i==calendarTransactionList.size()-1) {
//				if (!calendarTransactionList.get(i - 1).getDate().equals(calendarTransactionList.get(i).getDate())) {
//					transactionList.add(calendarTransactionList.get(i - 1));
//					map.put("data", transactionList);
//					list.add(map);
//					Map<String,Object> map1 = new HashMap<String,Object>();
//					map1.put("date",DateUtil.toMonthAndDay(calendarTransactionList.get(i).getDate()));
//					List<CalendarTransaction> transactionList1 = new ArrayList<CalendarTransaction>();
//					transactionList1.add(calendarTransactionList.get(i));
//					map1.put("data", transactionList1);
//					list.add(map1);
//				} else {
//					transactionList.add(calendarTransactionList.get(i));
//					map.put("data", transactionList);
//					list.add(map);
//				}
//			}else {
				if (!calendarTransactionList.get(i - 1).getDate().equals(calendarTransactionList.get(i).getDate())) {
					transactionList.add(calendarTransactionList.get(i - 1));
					map.put("data", transactionList);
					list.add(map);
					map = new HashMap<String, Object>();
					map.put("date", DateUtil.toMonthAndDay(calendarTransactionList.get(i).getDate()));
					transactionList = new ArrayList<CalendarTransaction>();
				} else {
					transactionList.add(calendarTransactionList.get(i-1));
				}
			if(i==calendarTransactionList.size()-1) {
				transactionList.add(calendarTransactionList.get(i));
					map.put("data", transactionList);
					list.add(map);
			}
//			}
//
		}
		return list;
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
		try{
			//消息推送
			rabbitTemplate.convertAndSend(RabbitmqConst.IRIS_EXCHANGE_TOPIC,
					RabbitmqConst.QUEUE_TOPIC_IRIS_MESSAGE,memberListVO);
			logger.error("*************消息推送成功*******");
		}catch (Exception e){
			logger.error("*************消息推送失败*******"+e.getMessage());
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
	public PageInfo<CalendarTransaction> getTransactionByPage(Map<String, Object> transactionParams) {
		Page<CalendarTransaction> page = new Page<>((Integer)transactionParams.get("pageNo"), (Integer)transactionParams.get("pageSize"));
		page.setRecords(calendarTransactionMapper.selectTransactionByPage(page,transactionParams));
		int total = calendarTransactionMapper.selectAttendanceRulesCountByParams(transactionParams);
		PageInfo<CalendarTransaction> pageInfo = new PageInfo<CalendarTransaction>();
		pageInfo.setRecords(page.getRecords());
		pageInfo.setPageNo((Integer)transactionParams.get("pageNo"));
		pageInfo.setPageSize((Integer)transactionParams.get("pageSize"));
		pageInfo.setPageTotal(PageUtil.getPages((Integer)transactionParams.get("pageSize"),total));
		return pageInfo;
	}

	@Override
	public Response<Boolean> removeTransactionById(Long id) {
		try {
			if (StringUtils.isEmpty(id)) {
				return new Response<>(Response.ERROR, "id不能为空");
			}
			//删除转态
			int deleted = 1;
			CalendarTransaction calendarTransaction = this.getCalendarTransactionById(id).getData();
			calendarTransaction.setDeleted(deleted);
			int result = calendarTransactionMapper.updateById(calendarTransaction);
			if(result != 0){
				//删除成功
				Response<Boolean> response = new Response<>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
				return response;
			}else{
				//删除失败
				return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<>(Response.ERROR, Response.ERROR_MESSAGE);
		}

	}

	@Override
	public Response<Boolean> editTransaction(CalendarTransaction calendarTransaction) {
		try {
			if(StringUtils.isEmpty(calendarTransaction)){
				return new Response<Boolean>(Response.ERROR,"对象不能为空！");
			}
			int result = calendarTransactionMapper.updateById(calendarTransaction);
			if(result != 0){
				return new Response<Boolean>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			}else{
				return new Response<Boolean>(Response.ERROR,Response.ERROR_MESSAGE);

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<Boolean>(Response.ERROR,Response.ERROR_MESSAGE);
		}
	}

	@Override
	public Response<Boolean> addTransaction(CalendarTransaction calendarTransaction) {
		try {
			if(StringUtils.isEmpty(calendarTransaction)){
				return new Response<Boolean>(Response.ERROR,"对象不能为空！");
			}
			int result = calendarTransactionMapper.insert(calendarTransaction);
			if(result != 0){
				return new Response<Boolean>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			}else{
				return new Response<Boolean>(Response.ERROR,Response.ERROR_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<Boolean>(Response.ERROR,Response.ERROR_MESSAGE);
		}
	}

	@Override
	public Response<CalendarTransaction> getCalendarTransactionById(Long id) {
		try {
			if(StringUtils.isEmpty(id)){
				return new Response<CalendarTransaction>(Response.ERROR,"id不能为空！");
			}
			Response<CalendarTransaction> res = new Response<CalendarTransaction>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			res.setData(calendarTransactionMapper.selectById(id));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<CalendarTransaction>(Response.ERROR,Response.ERROR_MESSAGE);
		}
	}

	@Override
	public Response<List<CalendarTransaction>> findTransactionByAgentCode(String agentCode) {
		try {

			Map<String,Object> paramMap=new HashedMap();
			paramMap.put("agentCode",agentCode);
			Response<List<CalendarTransaction>> res = new Response<List<CalendarTransaction>>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			res.setData(calendarTransactionMapper.selectTransactionByAgents(paramMap));
			res.setCode(Response.SUCCESS);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<List<CalendarTransaction>>(Response.ERROR,Response.ERROR_MESSAGE);
		}
	}

	@Override
	public List<CalendarTransaction> getfindCustomerVisit(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return calendarTransactionMapper.findCustomerCalendarDate(params);
	}
	
	@Override
	public CalendarTransaction selectById(Long id) {
		return calendarTransactionMapper.selectCalendarTransactionById(id);
	}
}