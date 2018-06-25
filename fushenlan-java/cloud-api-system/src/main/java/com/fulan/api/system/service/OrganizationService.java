package com.fulan.api.system.service;

import com.fulan.api.system.domain.Organization;
import com.fulan.application.util.domain.Response;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@FeignClient(name = "system")
public interface OrganizationService {


    /**
     * 根据code查询
     *
     * @param code
     * @return
     */
	@GetMapping("organization/find")
	Response<Organization> findById(@RequestParam("id") String id);
	/**
	 * 清空
	 * @return
	 */
	@PostMapping("organization/deleteAll")
	Response<Boolean> deleteAll();
	/**
	 * 插入组织
	 * @param organization
	 * @return
	 */
	@GetMapping("organization/insertFromDms")
	Response<Boolean> insertFromDms(Organization organization);
}
