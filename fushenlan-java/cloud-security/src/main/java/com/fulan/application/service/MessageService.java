package com.fulan.application.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.security.domain.Message;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-23
 */
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
	
	Page<Message>   findbyaccountId(@RequestParam(name="accountId")Long accountId, int pageNo, int pageSize);
	
	List<Message>   findbyaccountId(@RequestParam(name="accountId")Long accountId);
	/**
	 * 添加新的消息
	 * @param message
	 * */
	Response<Integer> addMessage(long accountId,String content,String type,long personnelId);
	
	

}
