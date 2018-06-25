package com.fulan.api.plan.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.plan.domain.OfflineActivity;
import com.fulan.api.plan.vo.OfflineActivityVOPC;
import com.fulan.api.plan.vo.OfflineActivityVoFw;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "plan")
public interface OfflineActivityService {
	
	/**
	 * 首页分页列表显示
	 * @param name
	 * @param state
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/offlineActivity/pageList")
	public PageInfo<OfflineActivityVoFw> pageList(
			@RequestParam(value="name",required=false) String name, 
			@RequestParam(value="state",required=false) String state,
			@RequestParam(value="enterStartDate",required=false) String enterStartDate,
			@RequestParam(value="enterEndDate",required=false) String enterEndDate,
			@RequestParam(value="pageNo",defaultValue="0")int pageNo,
			@RequestParam(value="pageSize",defaultValue="10")int pageSize);
	
	/**
	 * 批量
	 * 上架下架或者删除操作
	 * @param type 值为"dele" 为删除操作
	 * @param state
	 * @param ids
	 * @return
	 */
	@PostMapping("/manage/offlineActivity/updateOrDele")
    public Response<String> updateOrDele(@RequestParam(value="type",required=false)String type,
    		@RequestParam(value="state",required=false)String state,
    		@RequestParam(value="ids",required=false)String ids);
	
	/**
	 * 新增和修改（主键id为空新增）
	 *
	 * @param offlineActivity
	 * @return
	 */
	@PostMapping("/manage/offlineActivity/insertOrUpdateFW")
    public Response<String> insertOrUpdateFW(@RequestBody OfflineActivityVOPC offlineActivityVOPC,
            @RequestParam(value="fileId",required=false) Long fileId);
	
	
	/**
	 *
	 * 修改或查看
	 * @param id 根据主键id去查询数据
	 * @param accountName type 为check 根据accountName模糊查询为用户名 报名列表
	 * @param type 为check 为查看
	 * @param pageNo  type 为check 报名列表分页
	 * @param pageSize type 为check 报名列表分页
	 * @return
	 */
	@GetMapping("/manage/offlineActivity/findByIdFW")
    public Map<String,Object> findByIdFW(
    		@RequestParam(value="id",required=false) String id,
    		@RequestParam(value="accountName",required=false) String accountName,
    		@RequestParam(value="isSign",required=false) String isSign,
    		@RequestParam(value="type",required=false) String type,
    		@RequestParam(value="pageNo",defaultValue="0") int pageNo,
    		@RequestParam(value="pageSize",defaultValue="10") int pageSize); 

}
