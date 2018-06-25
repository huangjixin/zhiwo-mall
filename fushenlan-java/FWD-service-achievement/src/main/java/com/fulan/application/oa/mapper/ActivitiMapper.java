package com.fulan.application.oa.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import org.apache.ibatis.type.JdbcType;

import com.fulan.application.oa.vo.ApprovalRecordVo;

@Mapper
public interface ActivitiMapper {
	
	List<ApprovalRecordVo> selectApprovalRecord(@Param("processInstanceId") String processInstanceId);
	 
	 
	List<Integer> selectProcessedApproval(@Param("userId") String userId, 
									   @Param("types") String types[], 
									   @Param("start") int start, 
									   @Param("pageSize") int pageSize);
	
	List<Integer> selectPendingApproval(@Param("userId") String userId, 
			   @Param("types") String types[], 
			   @Param("start") int start, 
			   @Param("pageSize") int pageSize);
}
