package com.fulan.api.paper.service;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * <p>
 * 题目详细内容 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@FeignClient(name = "erecruitment")
public interface PaperCustomerService {
}
