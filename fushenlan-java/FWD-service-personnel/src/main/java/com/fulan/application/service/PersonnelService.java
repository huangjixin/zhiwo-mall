package com.fulan.application.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.personnel.domain.InterviewAction;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.vo.PersonnelEducation;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;
import com.fulan.api.personnel.vo.PersonnelManageVo;
import com.fulan.api.personnel.vo.PersonnelPool;
import com.fulan.api.personnel.vo.PersonnelVo;
import com.fulan.api.personnel.vo.SearchPersonnelVo;
import com.fulan.api.system.Vo.PersonnelTagVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

public interface PersonnelService {
	
	
	String checkResult(Personnel personnel);
	
	// 后台人才管理分页查询
	PageInfo<PersonnelManageVo> listByPage(Page<PersonnelManageVo> page,SearchPersonnelVo searchPersonnelVo);
	
	// 后台人才管理分页查询
	PageInfo<PersonnelManageVo> listByOtherPage(Page<PersonnelManageVo> page,String keyWord,int pageNo, int pageSize);
	
	//复核的
	PageInfo<PersonnelManageVo> listByOtherTwoPage(Page<PersonnelManageVo> page,String keyWord,int pageNo, int pageSize);
	
	
	
	// 后台根据人才主键查询详情
	PersonnelManageInfoVo PersonnelCheck(String paperId);
	
	/**
	 * 团队规模
	 */
	int selectteamSizebyaccountId(Long id);
	
	
	/**
	 * 在线增员-个人信息
	 * @param account
	 * @return
	 */
	Response<String> save(PersonnelVo personnelVo);
	/**
	 * 人才详情-根据personnelId查询个人信息
	 * @param personnelId
	 * @return
	 */
	Response<Personnel> getPersonnel(Long personnelId);
	/**
	 * 人才详情-根据personnelId查询个人教育信息
	 * @param personnelId
	 * @return
	 */
	Response<PersonnelEducation> getPersonnelEdu(Long personnelId);
	/**
	 * 人才详情-根据identityCode查询个人信息
	 * @param identityCode  证件编号
	 * @return
	 */
	Response<Personnel> getPersonnelByIdentityCode(String identityCode);
	/**
	 * 代理人人才库
	 * @param personnelId
	 * @return
	 */
	Response<List<Personnel>> getAgentPersonnel(String accountId);
	/**
	 * 准增员人才库
	 * @param personnelId
	 * @return
	 */
	List<PersonnelTagVo> getMustIncrease(Page<PersonnelTagVo> page,String accountId,String keyWord);
	
	List<PersonnelTagVo> getMustIncreaseedit(String accountId, String personnelId);
	/**
	 * 增员中人才库
	 * @param accountId
	 * @return
	 */
	Response<List<Personnel>> getIncreaseing(String accountId);
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	Response<Boolean> uploadFile(File file,HttpServletRequest request) throws Exception;
	/**
	 * 人才库-查看
	 * @param personnelId
	 * @return
	 */
	Response<PersonnelPool> getPersonnelPool(String personnelId);
	
	 /**
     * selectTeamSizebyagentCode
     * 查询某一个月入职人数
     * @warn(注意事项 – 可选)
     * @param accountId
     * @param searchMonth
     * @return
     */
	int selectentryNumbyparams(@Param("accountId")Long accountId, @Param("searchMonth")Long searchMonth);
    
	
	/**
	 * updateBypersonnel
	 * 跟新个人信息
	 * @warn(注意事项 – 可选)
	 * @param personnel
	 * @return
	 */
	Response<Integer> updateBypersonnel(Personnel personnel);
	
	  /**
     * selectTeamSizebyagentCode
     * 查询代理人某一年入职人数
     * @warn(注意事项 – 可选)
     * @param accountId
     * @param searchMonth
     * @return
     */
	int selectYeaytargetbyaccountId(@Param("accountId")Long accountId);
	   
	
	/**
	 * selectteamYeaycountbyaccountId
	 * 团队年目标
	 * @warn(注意事项 – 可选)
	 * @param accountIds
	 * @return
	 */
	 int selectteamYeaycountbyaccountId(@Param("accountIds")List<Long> accountIds);
	
	 
	 /**
	  * selectteamscalecountbyaccountId
	  * 团队规模
	  * @warn(注意事项 – 可选)
	  * @param accountIds
	  * @return
	  */
	 int  selectteamscalecountbyaccountId(@Param("accountIds")List<Long> accountIds);
	 
	 
	  /**
	   *   selectteamMouthcountbyaccountId
	   * 团队月目标
	   * @warn(注意事项 – 可选)
	   * @param accountIds
	   * @param searchMonth
	   * @return
	   */
	 int selectteamMouthcountbyaccountId(@Param("accountIds")List<Long> accountIds,@Param("searchMonth")Long searchMonth);
	   
	 
	 
	 
	 String rankingbyaccountId(@Param("accountIds")List<Long> accountIds,@Param("accountId")Long accountId);
	 
	 /*
	  * 更新代理人录入信息
	  * */
	 int updatePersonnel(Personnel personnel,InterviewAction interviewAction,String orgId,int step);
	 /**
	  * 根据personnel的id删除个人信息
	  * @param id
	  */
	 Response<String> deleteById(Long id);
	
	 
	 Response<String> saveandtag(PersonnelVo personnelVo);
	 
	 Response<String> updatepersonnelandtag(PersonnelVo personnelVo);
}

