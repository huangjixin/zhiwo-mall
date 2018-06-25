package com.fulan.api.personnel.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.personnel.domain.Apply;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 计划申请 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
public interface ApplyService extends IService<Apply> {
	/**
	 * 在线增员-计划申请
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/personnel/addApply")
	Response<String> save(Apply apply);
	/**
	 * 人才详情-查看申請信息
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/personnel/getApply")
	Response<Apply> getApply(@RequestParam(name = "personnelId" , required = true) String personnelId);

}
