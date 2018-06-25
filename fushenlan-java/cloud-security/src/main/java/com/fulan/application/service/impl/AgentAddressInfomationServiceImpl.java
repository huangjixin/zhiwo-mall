package com.fulan.application.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.security.domain.AgentAddressInfomation;
import com.fulan.application.mapper.AgentAddressInfomationMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.AgentAddressInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentAddressInfomationServiceImpl extends ServiceImpl<AgentAddressInfomationMapper, AgentAddressInfomation> implements AgentAddressInfomationService{

    @Autowired
    private AgentAddressInfomationMapper addressInfomationMapper;

    public int deleteByPrimaryKey(String agentId){
        return addressInfomationMapper.deleteByPrimaryKey(agentId);
    }

    public void save(List<AgentAddressInfomation> record){
        GenerateVOUtil.generate(record);
        insertBatch(record);
    }

    public AgentAddressInfomation selectByPrimaryKey(Long agentAddrId){
        return addressInfomationMapper.selectByPrimaryKey(agentAddrId);
    }

    public int updateByPrimaryKey(AgentAddressInfomation record){
        return addressInfomationMapper.updateByPrimaryKey(record);
    }
}