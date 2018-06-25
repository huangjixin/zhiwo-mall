package com.fulan.api.erecruitment.personnel.service;

import com.fulan.api.erecruitment.personnel.domain.Personnel;
import com.fulan.api.erecruitment.personnel.vo.PersonnelManageInfoVo;
import com.fulan.api.erecruitment.personnel.vo.PersonnelManageVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 人才信息主表 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@FeignClient(name = "erecruitment")
public interface PersonnelService  {
	
	@GetMapping(value = "/manage/personnel/listByPage")
	PageInfo<PersonnelManageVo> selectByPage(@RequestParam(name = "keyWord" , required = false) String keyWord,
											 @RequestParam(name = "pageNo",defaultValue="1")int pageNo,
											 @RequestParam(name = "pageSize",defaultValue="10")int pageSize);
			
	
	@GetMapping(value = "/manage/personnel/checkInfo")
	PersonnelManageInfoVo findById(@RequestParam(name = "id" , required = false) String id);
	
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
	Personnel getAgentPersonnel(@RequestParam(name = "personnelId" , required = true) String personnelId);
	/**
	 * 上传文件-授权处理
	 * @param file
	 * @return
	 */
	@PostMapping("/customer/personnel/uploadFile")
	String uploadFile(@RequestParam(value = "file", required = true)MultipartFile file);

}
