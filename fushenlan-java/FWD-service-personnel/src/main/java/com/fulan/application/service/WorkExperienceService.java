package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.domain.WorkExperience;
import com.fulan.application.util.domain.Response;

public interface WorkExperienceService {
	/**
	 * 在线增员-工作经历
	 * @param account
	 * @return
	 */
	Response<String> save(List<WorkExperience> workExperienceList,Personnel personnel);
	/**
	 * 人才详情-工作经历
	 * @param personnelId
	 * @return
	 */
	Response<Map<String,Object>> getWorkExperience(Long personnelId);
	/**
	 * 根据personnelId删除指定个人工作经历信息
	 * @param personnelId
	 * @return
	 */
	Response<String> deleteByPersonnelId(Long personnelId);
}
