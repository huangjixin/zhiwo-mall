package com.fulan.api.system.Vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fulan.api.system.domain.Organization;

@JsonIgnoreProperties
public class OrganizationVo extends  Organization{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Organization> organizations;
	public List<Organization> getOrganizations() {
		return organizations;
	}
	public void setOrganizations(List<Organization> organizations) {
		this.organizations = organizations;
	}

	
	
}
