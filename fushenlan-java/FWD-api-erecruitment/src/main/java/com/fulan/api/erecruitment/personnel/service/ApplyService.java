package com.fulan.api.erecruitment.personnel.service;

import com.fulan.api.erecruitment.personnel.domain.Apply;
import com.fulan.application.util.domain.Response;
import org.springframework.web.bind.annotation.PostMapping;
import com.baomidou.mybatisplus.service.IService;

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
	Response<String> save(String id,Apply apply);
	
}
