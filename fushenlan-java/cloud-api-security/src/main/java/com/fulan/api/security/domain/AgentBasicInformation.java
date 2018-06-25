package com.fulan.api.security.domain;

import com.fulan.application.util.util.IdAnnon;

import java.io.Serializable;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/1/24 20:14
 */
public class AgentBasicInformation implements Serializable {

    @IdAnnon
    private Long basicId;

    private String agentId;

    private String agentName;

    private String channelCd;

    private String agentStatusCd;

    private String designationCd;

    private String joiningDt;

    private String agentClass;

    private String laCompanyCode;

    private String laBranchCode;

    private String laPolicyBranchSeq;

    public Long getBasicId() {
        return basicId;
    }

    public void setBasicId(Long basicId) {
        this.basicId = basicId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getChannelCd() {
        return channelCd;
    }

    public void setChannelCd(String channelCd) {
        this.channelCd = channelCd;
    }

    public String getAgentStatusCd() {
        return agentStatusCd;
    }

    public void setAgentStatusCd(String agentStatusCd) {
        this.agentStatusCd = agentStatusCd;
    }

    public String getDesignationCd() {
        return designationCd;
    }

    public void setDesignationCd(String designationCd) {
        this.designationCd = designationCd;
    }

    public String getJoiningDt() {
        return joiningDt;
    }

    public void setJoiningDt(String joiningDt) {
        this.joiningDt = joiningDt;
    }

    public String getAgentClass() {
        return agentClass;
    }

    public void setAgentClass(String agentClass) {
        this.agentClass = agentClass;
    }

    public String getLaCompanyCode() {
        return laCompanyCode;
    }

    public void setLaCompanyCode(String laCompanyCode) {
        this.laCompanyCode = laCompanyCode;
    }

    public String getLaBranchCode() {
        return laBranchCode;
    }

    public void setLaBranchCode(String laBranchCode) {
        this.laBranchCode = laBranchCode;
    }

    public String getLaPolicyBranchSeq() {
        return laPolicyBranchSeq;
    }

    public void setLaPolicyBranchSeq(String laPolicyBranchSeq) {
        this.laPolicyBranchSeq = laPolicyBranchSeq;
    }

}
