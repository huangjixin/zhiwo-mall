package com.fulan.application.achievement.service;

import java.util.List;

import com.fulan.application.achievement.domain.FwdAchievementTarget;

public interface IAchievementTargetService {
	/**
	 * 新增一个目标设定
	 * @param achievementTarget
	 * @return
	 */
	int save(FwdAchievementTarget achievementTarget);
	/**
	 * 删除一个目标设定
	 * @param achievementTarget
	 * @return
	 */
	int delete(int id);
	/**
	 * 修改目标设定
	 * @param achievementTarget
	 * @return
	 */
	int update(FwdAchievementTarget achievementTarget);
	/**
	 * 查询全部列表
	 * @param achievementTarget
	 * @return
	 */
	List<FwdAchievementTarget> selectAll();
}
