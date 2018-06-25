package com.fulan.api.personnel.service;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.personnel.domain.Educational;
import com.fulan.application.util.domain.Response;

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
	Response<String> save(List<Educational> educationalList);
	/**
	 * 人才详情-查找教育经历
	 * @return
	 */
	@PostMapping("/customer/personnel/getEducational")
	List<Educational> getEducational(@RequestParam(name = "personnelId" , required = false) Long personnelId);

}
