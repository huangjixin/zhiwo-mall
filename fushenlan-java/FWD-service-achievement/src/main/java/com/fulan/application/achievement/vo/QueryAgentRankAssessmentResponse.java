package com.fulan.application.achievement.vo;

import java.util.List;

import lombok.Data;

/**
 * 代理人职级维持考核指标实际值查询接口 
 * 
 * 代理人职级晋级考核指标实际值查询接口
 * 
 *  响应参数
 * @author FWDuser
 *
 */
@Data
public class QueryAgentRankAssessmentResponse {
    
     private AgentGrade agentGrade; //代理人职位
     
     private AgentGrade[] promotionDirect;
     
     private String assessPeriodFrom;
     
     private String assessPeriodTo;
     
     private int nextAssessDay;
     
     private int nextAssessHour;
     
     private int nextAssessMinus;
     
     private List<RankAssessmentIndicator> indicatorList;  //  指标列表值       DMS提供封装的数据

}
