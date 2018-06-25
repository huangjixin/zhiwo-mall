package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.vo.PersonnelAddVo;
import com.fulan.api.personnel.vo.PersonnelEducation;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;
import com.fulan.api.personnel.vo.PersonnelManageVo;
import com.fulan.api.personnel.vo.PersonnelPool;
import com.fulan.api.personnel.vo.SearchPersonnelVo;
import com.fulan.api.system.Vo.PersonnelTagVo;

@Mapper
public interface PersonnelMapper extends BaseMapper<Personnel>{
    int deleteByPrimaryKey(Long id);


    int insertSelective(Personnel record);

    Personnel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Personnel record);

    int updateByPrimaryKey(Personnel record);
    
    List<PersonnelManageVo> PersonnelManageOtherSearch(RowBounds rowBounds, @Param("keyWord")String keyWord, 
		    @Param("pageNo")int pageNo, 
		    @Param("pageSize")int pageSize);
    
    //复核的查询所有
    List<PersonnelManageVo> PersonnelManageOtherTwoSearch(RowBounds rowBounds, @Param("keyWord")String keyWord, 
		    @Param("pageNo")int pageNo, 
		    @Param("pageSize")int pageSize);
    //复核的查询所有
    int personnelManageOtherTwoSearchCount(@Param("keyWord")String keyWord); 
    
    List<PersonnelManageVo> PersonnelManageSearch(RowBounds rowBounds,@Param("condition") SearchPersonnelVo searchPersonnelVo);
    
    int personnelManageOtherSearchCount(@Param("keyWord")String keyWord); 
    
    int PersonnelManageSearchCount(SearchPersonnelVo searchPersonnelVo); 
    /**
     * PersonnelSearchbyagentCode
     * 我的增员
     * @warn(注意事项 – 可选)
     * @param page
     * @param agentCode
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<PersonnelAddVo> PersonnelSearchbyagentCode(Page<PersonnelAddVo> page,
    													@Param("agentCode")String agentCode,
    													@Param("keyWord")String keyWord, 
													    @Param("pageNo")int pageNo, 
													    @Param("pageSize")int pageSize);
    /**
     * PersonnelpaperSearchbyParam
     * 我的面试
     * @warn(注意事项 – 可选)
     * @param page
     * @param agentCode
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<PersonnelAddVo> PersonnelpaperSearchbyParam(Page<PersonnelAddVo> page,
														@Param("agentCode")String agentCode,
														@Param("keyWord")String keyWord, 
													    @Param("pageNo")int pageNo, 
													    @Param("pageSize")int pageSize);
    
    PersonnelManageInfoVo checkPersonnelManageInfoById(Long personnelId);
    
    Personnel selectByPersonnelId(Long personnelId);
    
    PersonnelEducation selectEduByPersonnelId(Long personnelId);
    
    Personnel getPersonnelByIdentityCode(String identityCode);
    
    List<Personnel> getAgentPersonnel(Long accountId);
    
    List<PersonnelTagVo> getMustIncrease(Page<PersonnelTagVo> page,
    		@Param("accountId")Long accountId,
    		@Param("keyWord")String keyWord);
    
    
    PersonnelTagVo getMustIncreaseedit(@Param("accountId")String accountId,
    								   @Param("personnelId")String personnelId);
    
    
    
    List<Personnel> getIncreaseing(Long accountId);
    
    PersonnelPool getPersonnelPool(Long personnelId);
   
    int updateByPersonnelId(Personnel personnel);
    
   // selectteamSizebyaccountId
	@Select(" 	SELECT COUNT(1)  FROM er_personnel pel WHERE pel.create_user in #{accountIds} AND pel.agent_code IS NOT NULL")
	int selectteamSizebyaccountId(@Param("accountIds")Long accountIds);
    
    
    /**
     * selectTeamSizebyagentCode
     * 查询代理人某一个月入职人数
     * @warn(注意事项 – 可选)
     * @param accountId
     * @param searchMonth
     * @return
     */
	@Select(" 	SELECT COUNT(1)  FROM er_personnel pel WHERE pel.create_user = #{accountId} AND pel.agent_code IS NOT NULL AND MONTH (pel.create_time) =  #{searchMonth} and year(pel.create_time) = year(NOW())")
	int selectentryNumbyparams(@Param("accountId")Long accountId, @Param("searchMonth")Long searchMonth);
    
	  /**
     * selectTeamSizebyagentCode
     * 查询代理人某一年入职人数
     * @warn(注意事项 – 可选)
     * @param accountId
     * @param searchMonth
     * @return
     */
	@Select(" 	SELECT COUNT(1)  FROM er_personnel pel WHERE pel.create_user = #{accountId} AND pel.agent_code IS NOT NULL AND year(pel.create_time) = year(NOW())")
	int selectYeaytargetbyaccountId(@Param("accountId")Long accountId);
    
	
	/**
	 * selectteamscalecountbyaccountId
	 * 团队规模
	 * @warn(注意事项 – 可选)
	 * @param accountIds
	 * @return
	 */
	int selectteamscalecountbyaccountId(@Param("accountIds")List<Long> accountIds);
	
	
	/**
	 * selectteamYeaycountbyaccountId
	 * 团队年目标
	 * @warn(注意事项 – 可选)
	 * @param accountIds
	 * @return
	 */
	 int selectteamYeaycountbyaccountId(@Param("accountIds")List<Long> accountIds);
	    
	  /**
	   *   selectteamMouthcountbyaccountId
	   * 团队月目标
	   * @warn(注意事项 – 可选)
	   * @param accountIds
	   * @param searchMonth
	   * @return
	   */
	 int selectteamMouthcountbyaccountId(@Param("accountIds")List<Long> accountIds,@Param("searchMonth")Long searchMonth);
	    
	/**
	 * rankingbyaccountId
	 * 分公司排名
	 * @warn(注意事项 – 可选)
	 * @param accountIds
	 * @param accountId
	 * @return
	 */
	 String rankingbyaccountId(@Param("accountIds")List<Long> accountIds,@Param("accountId")Long accountId);
	 
	 
	 int updatePersonnelStatusByPersonnelId(Personnel record);
	
}