package com.fulan.api.personnel.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.personnel.domain.ErTag;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 标签 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@FeignClient(name = "personnel")
public interface ErTagService extends IService<ErTag> {

	/**
	 * 给人打标签
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/ertag/addErTag")
	Response<String> addErTag(Long personnelId,Long tagId,@RequestParam(name="seq", required = false)Integer seq);
}
