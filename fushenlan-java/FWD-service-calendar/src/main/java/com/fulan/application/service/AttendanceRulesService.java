package com.fulan.application.service;

import com.fulan.api.calendar.domain.AttendanceRules;

public interface AttendanceRulesService {

	void addAttendanceRules(AttendanceRules attendanceRules);

	AttendanceRules getAttendanceRulesById(long attendanceRulesId);

}
