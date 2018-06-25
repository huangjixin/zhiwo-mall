package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.calendar.domain.AttendanceHistory;
import com.fulan.api.course.vo.CourseCMSVo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttendanceHistoryMapper extends BaseMapper<AttendanceHistory> {

    List<Map<String,Object>> selectAllHistory(@Param("agentCode")String agentCode,
                                              @Param("orgId")String orgId,
                                              @Param("calendarDate")String calendarDate,
                                              @Param("companyId")String companyId);

    Integer selectCountByCalendarDate(@Param("agentCode")String agentCode,
                                       @Param("orgId")String orgId, @Param("calendarDate")String calendarDate);
    
    List<AttendanceHistory> selectAttendanceResultByParams(Page<AttendanceHistory> page,@Param("startDate")String startDate,
            @Param("endDate")String endDate, @Param("keyWord")String keyWord,@Param("pageNo")int pageNo,@Param("pageSize")int pageSize);
    
    int selectAttendanceResultCountByParams(@Param("startDate")String startDate,@Param("endDate")String endDate, @Param("keyWord")String keyWord);

    void addAttendanceHistory(AttendanceHistory attendanceHistory) ;
}