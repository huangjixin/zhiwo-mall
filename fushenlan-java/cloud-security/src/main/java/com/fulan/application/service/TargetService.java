package com.fulan.application.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fulan.api.security.domain.Target;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 代理人目标 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */

public interface TargetService  {

	 /**
     * 插入一条数据
     *
     * @param Target
     * @return
     */
	Response<String> insertDetail(Target target);
	
	
	/**
	 * selectMonthlytargetbyAgentCode
	 * 代理人月目标
	 * @warn(注意事项 – 可选)
	 * @param map
	 * @return
	 */
	Target selectMonthlytargetbyAgentCode(long accountId, String month);
	
	
	/**
	 * selectYeaytargetbyagentCode
	 * 代理人年目标
	 * @warn(注意事项 – 可选)
	 * @param map
	 * @return
	 */
	int  selectYeaytargetbyagentCode(long  agentCode);
	
	/**
	 * selectTeamSizebyagentCode
	 * 团队规模
	 * @warn(注意事项 – 可选)
	 * @param companyId
	 * @return
	 */
	List<Long>  selectallaccountIds(String orgId);
	
	
	void update(Target target);
	
	/**
	 * targetList
	 * 通过代理人id查找目标
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @return
	 */
	List<Target> selecttargetListbyaccountId(Long accountId,String targetYear);
	
}
