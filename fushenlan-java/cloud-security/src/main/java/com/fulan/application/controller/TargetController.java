package com.fulan.application.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.security.domain.Account;
import com.fulan.api.security.domain.Message;
import com.fulan.api.security.domain.Target;
import com.fulan.api.security.vo.AccountVO;
import com.fulan.api.system.domain.Organization;
import com.fulan.api.system.service.OrganizationService;
import com.fulan.application.mapper.AccountMapper;
import com.fulan.application.service.AccountService;
import com.fulan.application.service.MessageService;
import com.fulan.application.service.TargetService;
import com.fulan.application.util.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 代理人目标 前端控制器
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Api(tags = "targetApi", description = "代理人目标接口")
@RestController
@RequestMapping("/customer/target")
public class TargetController {
	
	
	@Autowired
	TargetService targetService;
	
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	MessageService messageService;
	
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	OrganizationService  organizationService;

	private static final Logger logger = LoggerFactory.getLogger(TargetController.class);


	
	
	@ApiOperation(value = "新增代理人目标", notes = "新增代理人目标", response = Response.class)
	@RequestMapping(value = "/addandupdate",  method = RequestMethod.POST)
	public Response<String>  addandupdateTarget(
			@ApiParam(name = "targetlist",value = "target  代理人目标实体")@RequestBody List<Target> targetlist) {
		try {
			Response<String> resp=new Response<String>();
			for(Target target : targetlist){
				resp =	targetService.insertDetail(target);
			}
			return resp;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增代理人目标失败");
		}
	}
	
	

	
	
	
	@ApiOperation(value = "查询代理人目标", notes = "查询代理人目标", response = Response.class)
	@RequestMapping(value = "/selectbyaccountId",  method = RequestMethod.POST)
	public Response<List<Target>>  selectbyaccountId(
			 @ApiParam(name = "accountId", value = "代理人id") @RequestParam(required=false) Long  accountId,
			 @ApiParam(name = "targetYear", value = "目标年份targetYear") @RequestParam(required=false) String  targetYear) {
		try {
			List<Target> list = targetService.selecttargetListbyaccountId(accountId,targetYear);	
			Response<List<Target>> resp=new Response<List<Target>>(Response.SUCCESS, "查询代理人目标成功");
			resp.setData(list);
			return resp;
		} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage());
				return new Response<List<Target>>(Response.ERROR,"查询代理人目标失败");
				
		}
	}
	
	
	
	@RequestMapping(value = "/selectallaccountIds",  method = RequestMethod.GET)
	public List<Long>  selectallaccountIds(
			 @ApiParam(name = "orgId", value = "代理人orgId") @RequestParam(required=false) String  orgId
			) {
		
		List<Long> accountIds = targetService.selectallaccountIds(orgId);
		return  accountIds;
	}
	
	
	
	
	@ApiOperation(value = "最新动态", notes = "最新动态", response = Response.class)
	@RequestMapping(value = "/newsmessage", method = RequestMethod.POST)
	public Response<List<Message>>  newsmessage(
			  @ApiParam(name = "accountId", value = "代理人id") @RequestParam(required=false) Long accountId) {
				
		try {
			Response<List<Message>> resp=new Response<List<Message>>(Response.SUCCESS, "最新动态获取成功");	
			
			List<Message>  pageinfomessage= messageService.findbyaccountId(accountId);
			resp.setCode("1");
			resp.setData(pageinfomessage);		
			return resp;	
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<Message>>(Response.ERROR,"最新动态获取失败");
		}
	}
	
	
}

