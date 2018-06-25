package com.fulan.api.security.domain;

import com.fulan.application.util.util.IdAnnon;

import java.io.Serializable;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/1/24 20:14
 */
public class AgentContactInformation implements Serializable {

    @IdAnnon
    private Long agentContactId;

    private String agentId;

    private String contactType;

    private String contactNum;

    public Long getAgentContactId() {
        return agentContactId;
    }


    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public void setAgentContactId(Long agentContactId) {
        this.agentContactId = agentContactId;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}
