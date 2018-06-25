package com.fulan.application.achievement.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.achievement.domain.FwdAchievementTarget;
import com.fulan.application.achievement.service.IAchievementTargetService;
/**
 * 目标设定控制层
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/achievementTarget")
public class AchievementTargetController {

	@Autowired
	private IAchievementTargetService achievementTargetService;

	/**
	 * 保存数据
	 * 
	 * @param save
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	private int save(FwdAchievementTarget achievementTarget) {
		achievementTarget.setCreateDatetime(new Date());
		achievementTarget.setUpdateDatetime(new Date());
		int result = achievementTargetService.save(achievementTarget);
		return result;
	}

	/**
	 * 删除数据
	 * 
	 * @param delete
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	private int delete(@PathVariable(value = "id") Integer id) {
		int result = achievementTargetService.delete(id);
		return result;
	}

	/**
	 * 修改数据
	 * 
	 * @param update
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private int update(FwdAchievementTarget achievementTarget) {
		achievementTarget.setUpdateDatetime(new Date());
		int result = achievementTargetService.update(achievementTarget);
		return result;
	}

	/**
	 * 查询数据
	 * 
	 * @param selectAll
	 * @return
	 */
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	private List<FwdAchievementTarget> selectAll() {
		List<FwdAchievementTarget> achievementTargetList = achievementTargetService.selectAll();
		return achievementTargetList;
	}
}
