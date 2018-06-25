package com.fulan.api.security.service;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.security.domain.Message;
import com.fulan.application.util.page.PageInfo;
import com.baomidou.mybatisplus.plugins.Page;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-23
 */
@FeignClient(name = "security")
public interface MessageService  {

	
	/**
	 * findbyaccountId
	 * 通过accountId 查找消息
	 *  页数、条数、排序字段、排序方式
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @param pageNo
	 * @param pageSize
	 * @param pageSortFiled
	 * @param pageSortType
	 * @return
	 */
	@PostMapping("/mamage/messageService/findbyaccountId")
	Page<Message>   findbyaccountId(@RequestParam(name="accountId")Long accountId, @RequestParam(name="pageInfo") PageInfo pageInfo);
	
	@PostMapping("/mamage/messageService/addMessage")
	Page<Integer>   addMessage(@RequestParam(name="accountId")Long accountId, @RequestParam(name="content") String content,@RequestParam(name="type") String type,@RequestParam(name="personnelId")Long personnelId);
	
}
