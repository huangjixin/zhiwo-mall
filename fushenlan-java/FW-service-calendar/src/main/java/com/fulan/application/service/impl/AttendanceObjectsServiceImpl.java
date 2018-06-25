package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.calendar.domain.AttendanceObjects;
import com.fulan.application.mapper.AttendanceObjectsMapper;
import com.fulan.application.service.AttendanceObjectsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AttendanceObjectsServiceImpl extends ServiceImpl<AttendanceObjectsMapper, AttendanceObjects> implements AttendanceObjectsService{
 
	@Override
	public void addAttendanceObjectsList(List<AttendanceObjects> attendanceObjectsList) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public List<AttendanceObjects> getListByAttendanceRulesId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
