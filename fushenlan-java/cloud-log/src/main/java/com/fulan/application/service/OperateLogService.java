package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.application.operatelog.domain.OperateLog;
import com.fulan.application.util.page.PageInfo;

public interface OperateLogService {

	void addSystemLog(OperateLog systemLog);
	
	List<OperateLog> selectByMap(Map<String,Object> map);
	/**
	 * 分页查询
	 * @param pageNo
	 * @param pageSize
	 * @param pageSortFiled
	 * @param pageSortType
	 * @return
	 */
	PageInfo<OperateLog> listByPage(Page<OperateLog> page, String requestIp , String requestUrl ,int pageNo, int pageSize);

}
