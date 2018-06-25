package com.fulan.application.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.security.domain.AgentBasicInformation;
import com.fulan.application.mapper.AgentBasicInformationMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.AgentBasicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentBasicInformationServiceImpl extends ServiceImpl<AgentBasicInformationMapper, AgentBasicInformation> implements AgentBasicInformationService{
    @Autowired
    private AgentBasicInformationMapper basicInformationMapper;

    public int deleteByPrimaryKey(String agentId){
        return basicInformationMapper.deleteByPrimaryKey(agentId);
    }

    public void save(AgentBasicInformation record){
        GenerateVOUtil.generate(record);
        insertAllColumn(record);
    }

    public AgentBasicInformation selectByPrimaryKey(Long agentId){
        return basicInformationMapper.selectByPrimaryKey(agentId);
    }

    public int updateByPrimaryKey(AgentBasicInformation record){
        return basicInformationMapper.updateByPrimaryKey(record);
    }

	@Override
	public List<AgentBasicInformation> selectAllAgentBasicInformation() {
		return basicInformationMapper.selectAllAgentBasicInformation();
	}
}