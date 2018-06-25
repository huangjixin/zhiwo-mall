package com.fulan.api.security.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/1/24 20:14
 */
public class AgentMainDTO implements Serializable {

    private String status;
    private AgentBasicInformation agent;
    private Long accountId;
    private List<AgentAddressInfomation> addresses;
    private List<AgentBranchInfomation> branchs;
    private List<AgentContactInformation> contacts;
    private List<AgentLicenseInfomation> licenses;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public AgentBasicInformation getAgent() {
        return agent;
    }

    public void setAgent(AgentBasicInformation agent) {
        this.agent = agent;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public List<AgentAddressInfomation> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AgentAddressInfomation> addresses) {
        this.addresses = addresses;
    }

    public List<AgentBranchInfomation> getBranchs() {
        return branchs;
    }

    public void setBranchs(List<AgentBranchInfomation> branchs) {
        this.branchs = branchs;
    }

    public List<AgentContactInformation> getContacts() {
        return contacts;
    }

    public void setContacts(List<AgentContactInformation> contacts) {
        this.contacts = contacts;
    }

    public List<AgentLicenseInfomation> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<AgentLicenseInfomation> licenses) {
        this.licenses = licenses;
    }
}
