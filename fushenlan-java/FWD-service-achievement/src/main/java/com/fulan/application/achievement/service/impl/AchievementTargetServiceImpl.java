package com.fulan.application.achievement.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.application.achievement.domain.FwdAchievementTarget;
import com.fulan.application.achievement.mapper.FwdAchievementTargetMapper;
import com.fulan.application.achievement.service.IAchievementTargetService;

@Service
@Transactional
public class AchievementTargetServiceImpl implements IAchievementTargetService{

	private static String BASE_MESSAGE = "目标设定业务类AchievementTargetServiceImpl";
	private static Logger logger = Logger.getLogger(AchievementTargetServiceImpl.class);

	@Autowired
	private FwdAchievementTargetMapper achievementTargetMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IAddressService#save(com.fulan.application.
	 * oa.domain.FwdAchievementTarget)
	 */
	@Override
	public int save(FwdAchievementTarget achievementTarget) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"保存目标设定开始，传入的参数是："+achievementTarget.toString());
		}
		int result = achievementTargetMapper.insert(achievementTarget);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"保存目标设定技术，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IAddressService#delete(int)
	 */
	@Override
	public int delete(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"删除目标设定开始，传入的参数id是："+id);
		}
		int result = achievementTargetMapper.deleteByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"删除目标设定结束术，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IAddressService#update(com.fulan.application
	 * .oa.domain.FwdAchievementTarget)
	 */
	@Override
	public int update(FwdAchievementTarget achievementTarget) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"更新目标设定开始，传入的参数是："+achievementTarget.toString());
		}
		int result = achievementTargetMapper.updateByPrimaryKey(achievementTarget);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"更新目标设定结束，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IAddressService#selectAll()
	 */
	@Override
	public List<FwdAchievementTarget> selectAll() {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"查询所有目标设定开始");
		}
		List<FwdAchievementTarget> achievementTarget = achievementTargetMapper.selectByExample(null);
		if (logger.isInfoEnabled()) {
			String msg = achievementTarget.size()+"";
			logger.info(BASE_MESSAGE+"保存目标设定技术，结果的条目数是："+msg);
		}
		return achievementTarget;
	}

}
