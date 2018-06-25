package com.fulan.api.agent.vo;

import java.io.Serializable;

public class Address implements Serializable {

	 /**
	 * 获取代理人信息接口返回实体
	 */
	private static final long serialVersionUID = 1L;
	 private String addrAddress1;
     private String addrAddress2;
     private String addrCity;
     private String addrCountry;
     private String addrDist;
     private String addrPostCode;
     private String addrProvince;
     private String addrType;
     
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
	public String getAddrCity() {
		return addrCity;
	}
	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}
	public String getAddrCountry() {
		return addrCountry;
	}
	public void setAddrCountry(String addrCountry) {
		this.addrCountry = addrCountry;
	}
	public String getAddrDist() {
		return addrDist;
	}
	public void setAddrDist(String addrDist) {
		this.addrDist = addrDist;
	}
	public String getAddrPostCode() {
		return addrPostCode;
	}
	public void setAddrPostCode(String addrPostCode) {
		this.addrPostCode = addrPostCode;
	}
	public String getAddrProvince() {
		return addrProvince;
	}
	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}
	public String getAddrType() {
		return addrType;
	}
	public void setAddrType(String addrType) {
		this.addrType = addrType;
	}

    	
}
