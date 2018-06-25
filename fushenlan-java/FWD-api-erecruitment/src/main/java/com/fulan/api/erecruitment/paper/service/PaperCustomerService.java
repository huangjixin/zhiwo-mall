package com.fulan.api.erecruitment.paper.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.erecruitment.paper.vo.PaperVo;

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
	/**
	 * 增员试卷获取
	 * @param 
	 * @return
	 */
	@GetMapping("/customer/paper/")
	PaperVo getPaperExam(String paperType); 
}
