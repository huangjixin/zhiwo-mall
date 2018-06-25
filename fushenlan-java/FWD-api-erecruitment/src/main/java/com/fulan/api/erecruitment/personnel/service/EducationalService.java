package com.fulan.api.erecruitment.personnel.service;

import com.fulan.api.erecruitment.personnel.domain.Educational;
import com.fulan.api.erecruitment.personnel.domain.FamilyMember;
import com.fulan.application.util.domain.Response;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 教育经历 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
public interface EducationalService extends IService<Educational> {
	/**
	 * 在线增员-教育经历
	 * @return
	 */
	@PostMapping("/customer/personnel/addEducational")
	Response<String> save(Educational educational);
	/**
	 * 人才详情-查找教育经历
	 * @return
	 */
	@PostMapping("/customer/personnel/getEducational")
	List<Educational> getEducational(@RequestParam(name = "personnelId" , required = false) Long personnelId);

}
