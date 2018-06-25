package com.fulan.application.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.vo.*;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import feign.Param;

import java.util.Map;

public interface OfflineActivityService {
	
	public PageInfo<OfflineActivityVoFw> pageList(Page<OfflineActivityVoFw> page,
			String name, String state,String enterStartDate,String enterEndDate,int pageNo, int pageSize);
	
    public Response<String> updateOrDele(@Param("type")String type,@Param("state")String state,@Param("ids")String ids);
    
    public Response<String> insertOrUpdateFW(OfflineActivityVOPC offlineActivityVOPC, Long fileId);
    
    public Map<String,Object> findByIdFW(@Param("id")String id,
    		@Param("accountName")String accountName,
    		@Param("isSign")String isSign,
    		@Param("type")String type,
    		@Param("pageNo")int pageNo,
    		@Param("pageSize")int pageSize);


	/**
	 * 查询线下活动
	 * @param paramMap
	 * @return
	 */
	Page<OfflineActivityDto> searchOfflineActivity(Map<String,Object> paramMap );
}
