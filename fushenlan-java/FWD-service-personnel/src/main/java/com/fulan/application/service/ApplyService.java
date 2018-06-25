package com.fulan.application.service;

import com.fulan.api.personnel.domain.Apply;
import com.fulan.api.personnel.vo.ApplyScan;
import com.fulan.application.util.domain.Response;

public interface ApplyService {
	
	/**
	 * 在线增员-计划申请
	 * @param account
	 * @return
	 */
	Response<String> save(Long personnelId,Apply apply);
	/**
	 * 查询数据库字段
	 * @param personnelId
	 * @return
	 */
	Response<Apply> getApply(String personnelId);
	/**
	 * 人才详情-根据personnelId查询申請計劃信息
	 * @param personnelId
	 * @return
	 */
	Response<ApplyScan> getScanApply(Long personnelId);
	/**
	 * 删除指定个人的计划申请信息
	 * @param personnelId
	 * @return
	 */
	Response<String> deleteByPersonelId(Long personnelId);
}
