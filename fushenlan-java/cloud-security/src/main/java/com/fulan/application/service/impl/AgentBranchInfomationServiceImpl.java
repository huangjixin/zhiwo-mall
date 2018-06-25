package com.fulan.application.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.security.domain.AgentBranchInfomation;
import com.fulan.application.mapper.AgentBranchInfomationMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.AgentBranchInfomationService;
import com.fulan.application.util.domain.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AgentBranchInfomationServiceImpl extends ServiceImpl<AgentBranchInfomationMapper, AgentBranchInfomation> implements AgentBranchInfomationService{

    @Autowired
    private AgentBranchInfomationMapper branchInfomationMapper;

    public int deleteByPrimaryKey(String agentId){
       return branchInfomationMapper.deleteByPrimaryKey(agentId);
    }

    public void save(List<AgentBranchInfomation> record){
        GenerateVOUtil.generate(record);
        insertBatch(record);
    }

    public AgentBranchInfomation selectByPrimaryKey(Long agentBranchId){
        return branchInfomationMapper.selectByPrimaryKey(agentBranchId);
    }

    public int updateByPrimaryKey(AgentBranchInfomation record){
        return branchInfomationMapper.updateByPrimaryKey(record);
    }
	@Override
	public Response<AgentBranchInfomation> getAgentBranchInfoById(Long agentId) {
		
		Response<AgentBranchInfomation> resp = new Response<AgentBranchInfomation>(Response.SUCCESS, "在线增员-申请审核预览查看");
		AgentBranchInfomation agentBranchInfomation = branchInfomationMapper.getAgentBranchInfoById(agentId);
		resp.setData(agentBranchInfomation);
		return resp;
	}

	@Override
	public List<AgentBranchInfomation> selectAllAgentBranchInformation() {
		return branchInfomationMapper.selectAllAgentBranchInformation();
	}
}