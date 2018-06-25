package com.fulan.application.service;

import com.fulan.api.agent.vo.VTag;
import com.fulan.application.util.domain.Response;

/**
 * ClassName:FlagService
 * Date:     2018/4/10
 * @author Lycol
 * @version 1.0
 * @since JDK 1.8
 */
public interface FlagService {

     /**
      * 根据代理人编号查询他的标签
      * @param agentCode
      * @return
      */
     String searchFlagsByAgentCode(String agentCode);

     /**
      * <p>回写标签至Smart系统</p>
      * @param agentCode
      * @param ruleJson eg.{"minAge":"","maxAge":"","sex":["F"],"categoryCode":[10001,10002,10003],"planCode":[10001,10002,10003]}
      * @return
      */
     String addFlags(String agentCode,String name,String ruleJson);


     /**
      * 根据标签ID删除标签
      * @param agentCode
      * @return
      */
     String delFlag(String tagId);
}
