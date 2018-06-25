package com.fulan.application.achievement.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fulan.application.achievement.domain.FwdAgentInfo;
import com.fulan.application.achievement.mapper.FwdAgentInfoMapper;
import com.fulan.application.achievement.service.IAgentInfoService;

@Service
@Transactional
public class AgentInfoServiceImpl implements IAgentInfoService{

	private static String BASE_MESSAGE = "保存头像业务类AgentInfoServiceImpl";
	private static Logger logger = Logger.getLogger(AgentInfoServiceImpl.class);

	@Autowired
	private FwdAgentInfoMapper agentInfoMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IAddressService#save(com.fulan.application.
	 * oa.domain.FwdAgentInfo)
	 */
	@Override
	public int save(FwdAgentInfo agentInfo) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"保存头像开始，传入的参数是："+agentInfo.toString());
		}
		int result = agentInfoMapper.insert(agentInfo);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"保存头像技术，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.FwdAgentInfo#delete(int)
	 */
	@Override
	public int delete(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"删除头像开始，传入的参数id是："+id);
		}
		int result = agentInfoMapper.deleteByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"删除头像结束术，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.FwdAgentInfo#update(com.fulan.application
	 * .oa.domain.IAgentInfoService)
	 */
	@Override
	public int update(FwdAgentInfo agentInfo) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"更新头像开始，传入的参数是："+agentInfo.toString());
		}
		int result = agentInfoMapper.updateByPrimaryKey(agentInfo);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"更新头像结束，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.FwdAgentInfo#selectAll()
	 */
	@Override
	public List<FwdAgentInfo> selectAll() {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"查询所有代理人信息（名称，头像等相关信息）开始");
		}
		List<FwdAgentInfo> achievementTarget = agentInfoMapper.selectByExample(null);
		if (logger.isInfoEnabled()) {
			String msg = achievementTarget.size()+"";
			logger.info(BASE_MESSAGE+"查询所有代理人信息结束，结果的条目数是："+msg);
		}
		return achievementTarget;
	}

}
