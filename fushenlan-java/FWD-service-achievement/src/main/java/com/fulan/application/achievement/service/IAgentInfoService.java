package com.fulan.application.achievement.service;

import java.util.List;

import com.fulan.application.achievement.domain.FwdAgentInfo;

public interface IAgentInfoService {
	/**
	 * 新增一个保存头像
	 * @param agentInfo
	 * @return
	 */
	int save(FwdAgentInfo agentInfo);
	/**
	 * 删除一个保存头像
	 * @param agentInfo
	 * @return
	 */
	int delete(int id);
	/**
	 * 修改保存头像
	 * @param agentInfo
	 * @return
	 */
	int update(FwdAgentInfo agentInfo);
	/**
	 * 查询全部列表
	 * @param agentInfo
	 * @return
	 */
	List<FwdAgentInfo> selectAll();
}
