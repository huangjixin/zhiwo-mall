package com.fulan.api.security.domain;

import com.fulan.application.util.util.IdAnnon;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/1/24 20:14
 */
public class AgentBranchInfomation implements Serializable {

    @IdAnnon
    private Long agentBranchId;

    private String agentId;

    private String branchTier;

    private String branchType;

    private String branchCode;

    private String branchName;

    private String branchOrgNo;

    private String branchProvince;

    private String branchCity;

    private String branchDistrict;
    
    private Date createTime;
    
    private String createByName;
    
    private Long createById;
    
    private Date updateTime;
    
    private String updateByName;
    
    private Long updateById;

    public Long getAgentBranchId() {
        return agentBranchId;
    }

    public void setAgentBranchId(Long agentBranchId) {
        this.agentBranchId = agentBranchId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getBranchTier() {
        return branchTier;
    }

    public void setBranchTier(String branchTier) {
        this.branchTier = branchTier;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchOrgNo() {
        return branchOrgNo;
    }

    public void setBranchOrgNo(String branchOrgNo) {
        this.branchOrgNo = branchOrgNo;
    }

    public String getBranchProvince() {
        return branchProvince;
    }

    public void setBranchProvince(String branchProvince) {
        this.branchProvince = branchProvince;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchDistrict() {
        return branchDistrict;
    }

    public void setBranchDistrict(String branchDistrict) {
        this.branchDistrict = branchDistrict;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public String getBranchType() {
        return branchType;
    }

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}

	public Long getCreateById() {
		return createById;
	}

	public void setCreateById(Long createById) {
		this.createById = createById;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateByName() {
		return updateByName;
	}

	public void setUpdateByName(String updateByName) {
		this.updateByName = updateByName;
	}

	public Long getUpdateById() {
		return updateById;
	}

	public void setUpdateById(Long updateById) {
		this.updateById = updateById;
	}
	
}
