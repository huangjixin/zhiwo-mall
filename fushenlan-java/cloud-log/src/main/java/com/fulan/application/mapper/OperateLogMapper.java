package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.application.operatelog.domain.OperateLog;

@Mapper
public interface OperateLogMapper extends BaseMapper<OperateLog>{
	Integer insert(OperateLog operateLog);
	
	
	List<OperateLog> operateLogSerch(RowBounds rowBounds,
			   @Param("requestIp")String requestIp,
			   @Param("requestUrl")String requestUrl, 
			   @Param("pageNo")int pageNo, 
			   @Param("pageSize")int pageSize);
	
	int operateLogSerchCount(@Param("requestIp")String requestIp,@Param("requestUrl")String requestUrl);
}