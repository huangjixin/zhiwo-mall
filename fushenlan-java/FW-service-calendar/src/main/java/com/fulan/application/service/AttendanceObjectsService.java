package com.fulan.application.service;

import java.util.List;

import com.fulan.api.calendar.domain.AttendanceObjects;

public interface AttendanceObjectsService {

	void addAttendanceObjectsList(List<AttendanceObjects> attendanceObjectsList);

	List<AttendanceObjects> getListByAttendanceRulesId(Long id);

}
