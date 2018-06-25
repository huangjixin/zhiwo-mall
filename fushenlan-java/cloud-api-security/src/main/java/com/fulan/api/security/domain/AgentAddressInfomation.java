package com.fulan.api.security.domain;

import com.fulan.application.util.util.IdAnnon;

import java.io.Serializable;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/1/24 20:14
 */
public class AgentAddressInfomation implements Serializable {

    @IdAnnon
    private Long agentAddrId;

    private String agentId;

    private String addrType;

    private String addrCountry;

    private String addrProvince;

    private String addrCity;

    private String addrDist;

    private String addrAddress1;

    private String addrAddress2;

    private String addrPostCode;

    public Long getAgentAddrId() {
        return agentAddrId;
    }

    public void setAgentAddrId(Long agentAddrId) {
        this.agentAddrId = agentAddrId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public String getAddrCountry() {
        return addrCountry;
    }

    public void setAddrCountry(String addrCountry) {
        this.addrCountry = addrCountry;
    }

    public String getAddrProvince() {
        return addrProvince;
    }

    public void setAddrProvince(String addrProvince) {
        this.addrProvince = addrProvince;
    }

    public String getAddrCity() {
        return addrCity;
    }

    public void setAddrCity(String addrCity) {
        this.addrCity = addrCity;
    }

    public String getAddrDist() {
        return addrDist;
    }

    public void setAddrDist(String addrDist) {
        this.addrDist = addrDist;
    }

    public String getAddrAddress1() {
        return addrAddress1;
    }

    public void setAddrAddress1(String addrAddress1) {
        this.addrAddress1 = addrAddress1;
    }

    public String getAddrAddress2() {
        return addrAddress2;
    }

    public void setAddrAddress2(String addrAddress2) {
        this.addrAddress2 = addrAddress2;
    }

    public String getAddrPostCode() {
        return addrPostCode;
    }

    public void setAddrPostCode(String addrPostCode) {
        this.addrPostCode = addrPostCode;
    }
}
