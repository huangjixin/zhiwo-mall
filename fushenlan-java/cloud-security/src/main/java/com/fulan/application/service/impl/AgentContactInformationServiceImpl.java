package com.fulan.application.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.security.domain.AgentContactInformation;
import com.fulan.application.mapper.AgentContactInformationMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.AgentContactInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentContactInformationServiceImpl  extends ServiceImpl<AgentContactInformationMapper, AgentContactInformation> implements AgentContactInformationService{

    @Autowired
    private AgentContactInformationMapper contactInformationMapper;

    public int deleteByPrimaryKey(String agentId){
       return contactInformationMapper.deleteByPrimaryKey(agentId);
   }

    public void save(List<AgentContactInformation> record){
        GenerateVOUtil.generate(record);
        insertBatch(record);
    }

    public AgentContactInformation selectByPrimaryKey(Long agentContactId){
        return contactInformationMapper.selectByPrimaryKey(agentContactId);
    }

    public int updateByPrimaryKey(AgentContactInformation record){
        return contactInformationMapper.updateByPrimaryKey(record);
    }
}