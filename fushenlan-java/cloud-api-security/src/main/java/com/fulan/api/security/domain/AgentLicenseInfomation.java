package com.fulan.api.security.domain;

import com.fulan.application.util.util.IdAnnon;

import java.io.Serializable;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/1/24 20:14
 */
public class AgentLicenseInfomation implements Serializable {

    @IdAnnon
    private Long agentLicenseId;

    private String agentId;

    private String licenseType;

    private String agentLicenseStatus;

    private String licenseNum;

    private String licenseFromDate;

    private String licenseToDate;

    public Long getAgentLicenseId() {
        return agentLicenseId;
    }

    public void setAgentLicenseId(Long agentLicenseId) {
        this.agentLicenseId = agentLicenseId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getAgentLicenseStatus() {
        return agentLicenseStatus;
    }

    public void setAgentLicenseStatus(String agentLicenseStatus) {
        this.agentLicenseStatus = agentLicenseStatus;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getLicenseFromDate() {
        return licenseFromDate;
    }

    public void setLicenseFromDate(String licenseFromDate) {
        this.licenseFromDate = licenseFromDate;
    }

    public String getLicenseToDate() {
        return licenseToDate;
    }

    public void setLicenseToDate(String licenseToDate) {
        this.licenseToDate = licenseToDate;
    }
}
