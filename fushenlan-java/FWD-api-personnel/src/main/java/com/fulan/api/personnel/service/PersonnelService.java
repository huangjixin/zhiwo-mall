package com.fulan.api.personnel.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.vo.PersonnelEducation;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;
import com.fulan.api.personnel.vo.PersonnelManageVo;
import com.fulan.api.personnel.vo.PersonnelPool;
import com.fulan.api.personnel.vo.SearchPersonnelVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 人才信息主表 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-1932005
 */
@FeignClient(name = "personnel")
public interface PersonnelService  {
	
	@PostMapping("/manage/personnel/updateCheckResult")
	String updateCheckResult(@RequestBody Personnel personnel);
	
	
	@PostMapping(value = "/manage/personnel/listByPage")
	PageInfo<PersonnelManageVo> selectByPage(@RequestBody SearchPersonnelVo searchPersonnelVo);
	
	@GetMapping(value = "/manage/personnel/listByOtherPage")
	PageInfo<PersonnelManageVo> selectByOtherPage(@RequestParam(name = "keyWord" , required = false) String keyWord,
											 @RequestParam(name = "pageNo",defaultValue="1")int pageNo,
											 @RequestParam(name = "pageSize",defaultValue="10")int pageSize);
	
	@GetMapping(value = "/manage/personnel/listByOtherTwoPage")
	PageInfo<PersonnelManageVo> listByOtherTwoPage(@RequestParam(name = "keyWord" , required = false) String keyWord,
											 @RequestParam(name = "pageNo",defaultValue="1")int pageNo,
											 @RequestParam(name = "pageSize",defaultValue="10")int pageSize);
	
	
	@GetMapping(value = "/manage/personnel/checkInfo")
	PersonnelManageInfoVo findById(@RequestParam(name = "id" , required = false) String id);
	
	/**
	 * selectteamSizebyaccountId
	 * 获取团队规模
	 * @warn(注意事项 – 可选)
	 * @param long1
	 * @return
	 */
	@GetMapping(value = "/manage/personnel/selectteamSizebyaccountId")
	int selectteamSizebyaccountId(@RequestParam(name = "id" , required = false) List<Long> long1);
	
	
	
	@GetMapping(value = "/manage/personnel/selectteamscalecountbyaccountId")
	int selectteamscalecountbyaccountId(@RequestParam(name = "accountIds" , required = false) List<Long> accountIds);
	
	
	/**
	 * updatePersonnel
	 * 跟新人才信息
	 * @warn(注意事项 – 可选)
	 * @param personnel
	 * @return
	 */
	@PostMapping(value = "/manage/personnel/updatePersonnel")
	Response<Integer> updatePersonnel(Personnel  personnel);
	
	
	/**
	 * 在线增员-保存个人信息
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/personnel/addPersonnel")
	Response<String> save(Personnel personnel);
	
	/**
	 * 人才详情-查找个人信息
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/personnel/getPersonnel")
	Response<Personnel> getPersonnel(@RequestParam(name = "personnelId" , required = true) String personnelId);
	
	/**
	 * 人才详情-查找个人信息-教育信息
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/personnel/getPersonnel")
	Response<PersonnelEducation> getPersonnelEdu(@RequestParam(name = "personnelId" , required = true) String personnelId);
	
	
	/**
	 * 根据证件编号查找个人信息
	 * @param identityCode
	 * @return
	 */
	@PostMapping("/customer/personnel/getPersonnelByIdentityCode")
	Personnel getPersonnelByIdentityCode(@RequestParam(name = "identityCode" , required = true) String identityCode);
	/**
	 * 代理人人才库
	 * 展示由当前代理人录入的准招募人才转为代理人的人才信息
	 * @param personnelId
	 * @return
	 */
	@PostMapping("/customer/personnel/getAgentPersonnel")
	Response<List<Personnel>> getAgentPersonnel(@RequestParam(name = "accountId" , required = true) String accountId);
	/**
	 * 准增员人才库
	 * 展示当前代理人录入的准招募人才信息
	 * @param personnelId
	 * @return
	 */
	@PostMapping("/customer/personnel/getAgentPersonnel")
	Response<List<Personnel>> getMustIncrease(@RequestParam(name = "accountId" , required = true) String accountId);

	/**
	 * 上传文件-授权处理
	 * @param file
	 * @return
	 */
	@PostMapping("/customer/personnel/uploadFile")
	Response<Boolean> uploadFile(@RequestParam(value = "file", required = true)File file,HttpServletRequest request) throws Exception;
	/**
	 * 人才库-查看页面
	 */
	@PostMapping("/customer/personnel/getPersonnelPool")
	Response<PersonnelPool> getPersonnelPool(@RequestParam(name = "personnelId" , required = true) String personnelId);
	/**
	 * 代理人信息-更新代理人信息
	 * @param personnelStatus 代理人状态 0:准增员  1:在线增员 2：初审面试 3：甄选面试 4：决定面试 5：培训报名 6：培训考试 7：签约'
	 * @param personnelId 代理人id
	 * @param orgId 代理人分公司id
	 */
	@PostMapping("/customer/personnel/updatePersonnel")
	Response<Integer> updatePersonnel(@RequestParam(value = "personnelStatus",required=false) String personnelStatus,
			  @RequestParam(value = "personnelId",required=true) String personnelId,
			  @RequestParam(value = "orgId",required=false) String orgId);
}
