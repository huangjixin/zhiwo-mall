package com.fulan.api.erecruitment.personnel.service;

import com.fulan.api.erecruitment.personnel.domain.WorkExperience;
import com.fulan.application.util.domain.Response;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 工作经历 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
public interface WorkExperienceService extends IService<WorkExperience> {
	/**
	 * 在线增员-工作经历
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/personnel/addWorkExperience")
	Response<String> save(WorkExperience workExperience);
	/**
	 * 人才详情-查找工作经历
	 * @return
	 */
	@PostMapping("/customer/personnel/getWorkExperience")
	Response<List<WorkExperience>> getWorkExperience(@RequestParam(name = "personnelId" , required = false) Long personnelId);

}
