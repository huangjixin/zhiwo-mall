package com.fulan.application.receive;

import com.fulan.api.security.domain.AgentMainDTO;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/1/23 15:06
 */
@Component
public class AgentInfoReceiver {

    private Logger logger = LoggerFactory.getLogger(AgentInfoReceiver.class);

    @Autowired
    private AgentBasicInformationService basicInformationService;
    @Autowired
    private AgentAddressInfomationService addressInfomationService;
    @Autowired
    private AgentBranchInfomationService branchInfomationService;
    @Autowired
    private AgentContactInformationService contactInformationService;
    @Autowired
    private AgentLicenseInfomationService licenseInfomationService;

    @RabbitListener(queues = RabbitmqConst.QUEUE_TOPIC_ER_AGENT)
    public void process(AgentMainDTO agentDTO){
       try {
           basicInformationService.deleteByPrimaryKey(agentDTO.getAgent().getAgentId());
           addressInfomationService.deleteByPrimaryKey(agentDTO.getAgent().getAgentId());
           branchInfomationService.deleteByPrimaryKey(agentDTO.getAgent().getAgentId());
           contactInformationService.deleteByPrimaryKey(agentDTO.getAgent().getAgentId());
           licenseInfomationService.deleteByPrimaryKey(agentDTO.getAgent().getAgentId());
           logger.info("{-----代理人信息删除完成-----}");

           basicInformationService.save(agentDTO.getAgent());
           addressInfomationService.save(agentDTO.getAddresses());
           branchInfomationService.save(agentDTO.getBranchs());
           contactInformationService.save(agentDTO.getContacts());
           licenseInfomationService.save(agentDTO.getLicenses());
           logger.info("{-----代理人信息添加完成-----}");
       }catch (Exception e){
           logger.info("{-----代理人信息处理异常-----}");
           e.printStackTrace();
       }
    }

}
