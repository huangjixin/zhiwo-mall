package com.fulan.application.manage.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.domain.AccountManageVo;
import com.fulan.api.security.domain.AccountRole;
import com.fulan.api.security.domain.Resource;
import com.fulan.api.security.domain.RoleResource;
import com.fulan.api.security.vo.AccountOffVo;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.orm.page.PageUtil;
import com.fulan.application.service.AccountRoleService;
import com.fulan.application.service.AccountService;
import com.fulan.application.service.ResourceService;
import com.fulan.application.service.RoleResourceService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * 用户后台控制器
 * </p>
 *
 * @author zhouyun
 * @since 2018-01-17
 */
@Api(tags = "AccountManage", description = "用户后台接口")
@RestController
@RequestMapping("/manageAccount")
public class ManageAccountController {

	private static final Logger logger = LoggerFactory.getLogger(ManageAccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountRoleService accountRoleService;
	
	@Autowired
	private RoleResourceService roleResourceService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	IdGenerator idGenerator;
	

	@ApiOperation(value = "用户", notes = "用户赋权", response = Response.class)
	@PostMapping("/insertAccountRole")
	public Response<String> insertAccountRole(@RequestParam("accountId") Long accountId,  @RequestParam("roleIds") Long[] roleIds) {
		Response<String> response=new Response<String>(Response.SUCCESS, "添加账号角色成功");
		/*try {
			List<AccountRole> findByAccountId = accountRoleService.findByAccountId(accountRole.getAccountId());
			new Response<String>(Response.SUCCESS, "查询用户赋权成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "查询用户赋权失败");
		}*/
		try {
			accountRoleService.deleteAccountId(accountId);
			//return new Response<String>(Response.SUCCESS, "删除用户赋权成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "删除用户赋权失败");
		}
		
		try {
			List<AccountRole> list = new ArrayList<>();
			for (int i = 0; i < roleIds.length; i++) {
				Long generate = idGenerator.generate();
				AccountRole accountRole = new AccountRole();
				accountRole.setId(generate+i);
				accountRole.setAccountId(accountId);
				accountRole.setRoleId(roleIds[i]);
				list.add(accountRole);
			}
			
			accountRoleService.inserBylist(list);
			return response;
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "新增用户赋权失败");
		}

	}

	@ApiOperation(value = "用户", notes = "新增用户", response = Response.class)
	@PostMapping("/insert")
	public Response<String> insert(
			@ApiParam(name = "manageAccount", value = "不能为空字段：</br>account_name</br>password</br>company_id</br>") @RequestBody Account account) {
		try {
			return accountService.insertAccount(account);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "新增用户失败");
		}

	}

	@ApiOperation(value = "删除用户", notes = "删除用户", response = Response.class)
	@GetMapping(value = "/deleteAccount")
	public Response<String> deleteAccount(@RequestParam(required = true) Long id) {
		try {
			//先删除账户下的所有角色
			accountRoleService.deleteAccountId(id);
			//accountService.deleteAccountById(id);
			//根据id找到对象
			Account account = accountService.findById(id);
			account.setDelete(true);
			accountService.updateAccount(account );
			return new Response<String>(Response.SUCCESS, "删除用户成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "删除用户失败");
		}

	}

	@ApiOperation(value = "更新用户", notes = "更新用户", response = Response.class)
	@PostMapping(value = "/updateAccount")
	public Response<String> updateAccount(@RequestBody Account account) {
		try {
			return accountService.updateAccount(account);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "更新用户失败");
		}

	}

	@ApiOperation(value = "更新用户密码", notes = "更新用户密码", response = Response.class)
	@PostMapping(value = "/updatePassword")
	public Response<String> updatePassword(@RequestParam(required = true) Long id,
			@RequestParam(required = true) String password) {
		try {
			accountService.updatePasswordById(password, id);
			return new Response<String>(Response.SUCCESS, "更新密码成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "更新密码失败");
		}
	}

	/**
	 * 根据id重置用户密码（admin123）
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "重置用户密码", notes = "重置用户密码", response = Response.class)
	@PostMapping(value = "/resetPassword")
	public Response<String> resetPassword(@RequestParam(required = true) Long id) {
		try {
			accountService.updatePasswordById("admin123", id);
			return new Response<String>(Response.SUCCESS, "重置密码成功");
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR, "重置密码失败");
		}
	}

	/**
	 * 根据id查找单个用户
	 *
	 * @param id
	 *            用户Id
	 * @return
	 */
	@ApiOperation(value = "用户", notes = "根据id查找单个用户", response = Response.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户id", dataType = "long", paramType = "query", example = "1") })
	@PostMapping(value = "/find")
	public Account findById(@RequestParam("id") Long id) {
		try {
			return accountService.findById(id);
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		}

	}
	
	/**
	 * 根据id查找单个用户
	 *
	 * @param id
	 *            用户Id
	 * @return
	 */
	@PostMapping(value = "/findByAccountId")
	public List<Account> findByAccountId(@RequestParam("accountId") List<Long> accountId) {
		try {
			return accountService.findByAccountId(accountId);
		} catch (Exception e) {
			logger.error("", e);
			throw e;
		}

	}
	 /**
     * 分页查询数据字典
     *
     * @param id      主键id  （精确匹配）
     * @param pageNo        要跳转的页数
     * @param pageSize      每页条数
     * @param pageSortFiled 排序字段名
     * @param pageSortType  顺序：asc,倒叙：desc
     * @param type  区分是谁调用该接口
     * @return
     */
	@ApiOperation(value = "查询用户列表", notes = "查询用户列表", response = Response.class)
	@PostMapping(value = "/listByPage")
    public PageInfo<Account> listByPage(
            @ApiParam(name = "id", value = "用户Id, 不传则默认查询所有用户") @RequestParam(name = "id", defaultValue = "0")Long id,
            @ApiParam(name = "locked", value = "用户locked 是否加入黑名单,默认是可用") @RequestParam(name = "locked", defaultValue = "0")boolean locked,

            
            @ApiParam(name = "pageNo", value = "要跳转的页数") @RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @ApiParam(name = "pageSize", value = "每页条数，默认：10", required = false) @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
            @ApiParam(name = "pageSortFiled", value = "排序字段名，默认：id", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "id") String pageSortFiled,
            @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType,
            @ApiParam(name = "type", value = "区分是谁调用该接口") @RequestParam(name = "type", defaultValue = "1") String type
    ) {
        try {
            return PageUtil.versa(accountService.listByPage(id,locked, pageNo, pageSize, pageSortFiled, pageSortType,type));
        } catch (Exception e) {
        	logger.error("", e);
            throw e;
        }
    }
	@GetMapping(value="/selectRoles")
	public List<Long> selectRoles(@RequestParam("accountId") Long accountId){
		List<Long> roles = new ArrayList<>();
		roles = accountService.selectRoles(accountId);
		return roles;
	}
	
	@GetMapping(value="/selectResources")
	public List<Resource> findRoleAndResource(@RequestParam("roleId") Long roleId){
		List<RoleResource> roleResources = new ArrayList<>();
		List<Resource> resources = new ArrayList<>();
		roleResources = roleResourceService.findByRoleId(roleId);
		for (RoleResource roleResource : roleResources) {
			Resource resource = new Resource();
			resource.setId(roleResource.getResourceId());
			resources = resourceService.listByparentId(resource );
		}
		return resources;
	}
	@GetMapping(value="/findByAccountId")
	public List<AccountRole> findByAccountId(@RequestParam("accountId") Long accountId){
		List<AccountRole> findByAccountId = accountRoleService.findByAccountId(accountId);
		return findByAccountId;
	}
	
	@ApiOperation(value = "根据角色id查询账户", notes = "根据角色id查询账户", response = Response.class)
	@GetMapping(value = "/findByRoleId")
	public List<Account> findByRoleId(@RequestParam(required = true) Long roleId) {
			List<Account> findByRoleId = accountService.findByRoleId(roleId);
			return findByRoleId;
	}
	
	@PostMapping(value = "/findByRoleIdAndName")
	public List<Account> findByRoleIdAndName(@RequestParam("roleId") Long roleId,@RequestParam(value="name",required=false)String name) {
		List<Account> findByRoleId = accountService.findByRoleId(roleId);
		return findByRoleId;
	}
	
	@GetMapping(value="/findByAccountIdandTime")
	public String  findByAccountIdandTime(@RequestParam("accountId") Long accountId,@RequestParam("searchMonth") Long searchMonth){
		
		String currentNum =  accountService.selectentryNumbyparams(accountId, searchMonth);
		return currentNum;
	}
	
	
	@GetMapping(value="/selectYeaytargetbyaccountId")
	public String selectYeaytargetbyaccountId(@RequestParam("accountId") Long accountId){
		
		String currentNum =  accountService.selectYeaytargetbyaccountId(accountId);
		return currentNum;
	}
	
	
	@GetMapping(value="/selectaccountIdsbycompanyId")
	public List<Long> selectteamYeaytargetbycompanyId(@RequestParam("companyId") String companyId){
		
		List<Long> currentNum =  accountService.selectaccountIdsbycompanyId(companyId);
		return currentNum;
	}
	
	
	@GetMapping(value="/selectteamMouthtargetbyaccountIds")
	public String selectteamMouthtargetbyaccountIds(@RequestParam("accountIds") List<String> accountIds,@RequestParam("searchMonth") Long searchMonth){
		
		
		String currentNum= accountService.selectteamMouthtargetbyaccountIds(accountIds,searchMonth);
		return currentNum;
	}
	
	@GetMapping(value="/selectteamYeaycountbyaccountIds")
	public String selectteamyeartargetbyaccountIds(@RequestParam("accountIds") List<String> accountIds){
		
		
		String currentNum= accountService.selectteamyeartargetbyaccountIds(accountIds);
		return currentNum;
	}
	
	@GetMapping(value="/selectAccountRoleManageVo")
	public List<String> selectAccountRoleManageVo(@RequestParam("accountId") String accountId){
		return accountRoleService.selectAccountRoleManageVo(Long.parseLong(accountId));
		
	}
	
	@GetMapping(value="/selectByAccountId")
	public List<String> selectByAccountId(@RequestParam("accountId") String accountId){
		
		return accountRoleService.selectByAccountId(Long.parseLong(accountId));
	}
	
	/*@RequestMapping(value="/selectByAll",method=RequestMethod.GET)*/
	@GetMapping(value="/selectByAll")
    public List<Account> selectByAll(@RequestParam(name="accountName",required = false) String accountName , 
 			@RequestParam(name="mobile",required = false) String mobile){
		return accountService.selectByAll(accountName, mobile);
	}
	
	@GetMapping(value="/selectByCompanyId")
    public List<Account> selectByCompanyId(
 			@RequestParam(name="companyId",required = false) String companyId){
		return accountService.selectByCompanyId(companyId);
	}
	
	@GetMapping(value="/selectByNameOff")
	public PageInfo<AccountOffVo> selectByCompanyId(
			@RequestParam(name="idList",required=false) List<Long> idList,
			@RequestParam(name="name",required=false)String name,
			@RequestParam(value="pageNo",defaultValue="1") int pageNo,
	        @RequestParam(value="pageSize",defaultValue="10") int pageSize
			){
		Page<AccountOffVo> page = new Page<AccountOffVo>(pageNo, pageSize);
		PageInfo<AccountOffVo> pageInfo = accountService.selectByNameOff(page,idList, name, pageNo, pageSize);
		return pageInfo;
	}
	
	@PostMapping(value="/findByName")
	public  Account findByName(@RequestParam(name="accountName") String accountName){
		
		return accountService.findByName(accountName);
		
	}
	
	
    @RequestMapping(value="/listByPages",method=RequestMethod.GET)
	public  @ResponseBody PageInfo<AccountManageVo> listByPage(  
			@RequestParam(name="accountName") String accountName , 
			@RequestParam(name="telephone") String telephone,
			@RequestParam("pageNo") int pageNo,
			@RequestParam("pageSize") int pageSize){
		    Page<AccountManageVo> page = new Page<AccountManageVo>(pageNo, pageSize);
		return	accountService.listByPages(page, accountName, telephone, pageNo, pageSize);
	}
    
    @PostMapping(value="/findByRoleType")
    public Response<List<Account>> findByRoleType(
    		@RequestParam("type") String type,
    		@RequestParam("PID") Long PID,
    		@RequestParam(value="name",required=false) String name){
    	try {
    		return accountService.findByRoleType(type, PID, name);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new Response<List<Account>>(Response.ERROR,"访问失败");
		}
    }
    
    
    @ApiOperation(value = "根据角色id查询账户", notes = "根据角色id查询账户", response = Response.class)
	@GetMapping(value = "/findByOtherRoleId")
	public List<Account> findByOtherRoleId(@RequestParam(name = "roleId") String roleId,
			@RequestParam(name="accountName") String accountName,
			@RequestParam(name ="mobile") String mobile) {
			return accountService.findByOtherRoleId(roleId, accountName, mobile);
	}
	
    
    @RequestMapping(value="/listByPagesNotInUserGroup",method=RequestMethod.GET)
    public  @ResponseBody PageInfo<AccountManageVo> listByPagesNotInUserGroup(  
            @RequestParam(name="accountName") String accountName , 
            @RequestParam(name="telephone") String telephone,
            @RequestParam("pageNo") int pageNo,
            @RequestParam("pageSize") int pageSize){
            Page<AccountManageVo> page = new Page<AccountManageVo>(pageNo, pageSize);
        return  accountService.listByPagesNotInUserGroup(page, accountName, telephone, pageNo, pageSize);
    }
}
