package com.fulan.api.erecruitment.agent.service;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.erecruitment.agent.domain.Target;
import com.fulan.api.erecruitment.personnel.vo.PersonnelAddVo;
import com.fulan.api.security.domain.Account;
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
@FeignClient(name = "target" )
public interface TargetService  {

	
	/**
	 * insertDetail
	 * 新增代理人目标
	 * @warn(注意事项 – 可选)
	 * @param target
	 * @return
	 */
	@PostMapping("/manage/target/add")
	Response<String> insertDetail(@RequestParam(name="target")Target target);
	
	/**
	 * findMonthlybyAgentCode
	 * 查询代理人月目标
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @param month
	 * @return
	 */
	@GetMapping("/manage/target/findMonthlybyagentCode")
	Response<Target> findMonthlybyAgentCode(@RequestParam(name="accountId") long  accountId, @RequestParam(name="month") String month);

	/**
	 * findYeaybyagentCode
	 * 查询代理人年目标
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @return
	 */
	@GetMapping("/manage/target/findYeaybyagentCode")
	Response<Integer> findYeaybyagentCode(@RequestParam(name="accountId") long  accountId);

	
	/**
	 * findbyaccountId
	 * 查询代理人
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @return
	 */
	@PostMapping("/manage/target/findbyaccountId")
	Response<Account> findbyaccountId(@RequestParam(name="accountId") long  accountId);
	
	
	/**
	 * findPersonnelbyagentCode
	 * 我的增员
	 * @warn(注意事项 – 可选)
	 * @param agentCode
	 * @param keyWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/target/findPersonnelbyagentCode")
	Response<Page<PersonnelAddVo>>  findPersonnelbyagentCode(
			   @RequestParam(name="agentCode") String agentCode,
			   @RequestParam(name = "keyWord")String keyWord,
		       @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
	
	
	
	/**
	 * PersonnelpaperSearchbyParam
	 * 我的面试
	 * @warn(注意事项 – 可选)
	 * @param agentCode
	 * @param keyWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/target/PersonnelpaperSearchbyParam")	
	Response<Page<PersonnelAddVo>> PersonnelpaperSearchbyParam(
			   @RequestParam(name="agentCode") String agentCode,
			   @RequestParam(name = "keyWord")String keyWord,
		       @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
	
	
	/**
	 * newsmessage
	 * 最新动态
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@PostMapping("/manage/target/newsmessage")
	 Response<Page<Message>>  newsmessage( 
			   @RequestParam(name="accountId") Long accountId,
		       @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
	           @RequestParam(name = "pageSize", defaultValue = "10") int pageSize);
	
}
