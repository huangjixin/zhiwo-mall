//package com.fwd.customer.domain.port.util;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fwd.common.IdAnnon;
//import com.fwd.customer.domain.CustomerVO;
//import com.fwd.customer.domain.port.domain.CustomerInfoVo;
//import com.fwd.customer.domain.port.domain.create.AddressesInfoVo;
//import com.fwd.customer.domain.port.domain.create.CrsInfosInfoVo;
//import com.fwd.customer.domain.port.domain.create.CustomerCreateVo;
//import com.fwd.customer.domain.port.domain.create.SocialInsuranceInfoVo;
//import com.fwd.customer.domain.port.domain.update.CustomerUpdateVo;
//
//public class CustomerBeanUtil {
//	
//	 private static final Map<String,String> CITY = new HashMap<>();
//	    static {
//	        /*CITY.put("110000","110100");
//	        CITY.put("310000","310100");
//	        CITY.put("120000","120100");
//	        CITY.put("500000","500100");
//	        CITY.put("710000","710100");
//	        CITY.put("810000","810100");
//	        CITY.put("820000","820100");
//	        CITY.put("990000","990100");*/
//	        
//	        CITY.put("110100","110000");
//	        CITY.put("310100","310000");
//	        CITY.put("120100","120000");
//	        CITY.put("500100","500000");
//	        CITY.put("710100","710000");
//	        CITY.put("810100","810000");
//	        CITY.put("820100","820000");
//	        CITY.put("990100","990000");
//
//
//	    }
//
//	    private static final Map<String,String> AREA = new HashMap<>();
//	    static {
//	       /* AREA.put("710000","710101");
//	        AREA.put("810000","810101");
//	        AREA.put("820000","820101");
//	        AREA.put("990000","990101");*/
//	        
//	        AREA.put("710101","710000");
//	        AREA.put("810101","810000");
//	        AREA.put("820101","820000");
//	        AREA.put("990101","990000");
//
//	    }
//	public static CustomerVO newCustomerVO(){
//		CustomerVO customerVO = new CustomerVO();
//		customerVO.setCustomerId("");
//		customerVO.setCustomerType("");
//		customerVO.setCustomerStatus("");
//		customerVO.setIdentityType("");
//		customerVO.setIdentityCode("");
//		customerVO.setName("");
//		customerVO.setSex("");
//		customerVO.setBirthday(new Date());
//		customerVO.setEducation("");
//		customerVO.setNationality("");
//		customerVO.setCellphone("");
//		customerVO.setEmail("");
//		customerVO.setMaritalStatus("");
//		customerVO.setPersonalIncome(new BigDecimal("0"));
//		customerVO.setFamilyIncome(new BigDecimal("0"));
//		customerVO.setFamilyProcince("");
//		customerVO.setFamilyCity("");
//		customerVO.setFamilyArea("");
//		customerVO.setFamilyAddrDetail("");
//		customerVO.setFamilyPostcode("");
//		customerVO.setWorkPostcode("");
//		customerVO.setUnitName("");
//		customerVO.setWorkProcince("");
//		customerVO.setWorkCity("");
//		customerVO.setWorkArea("");
//		customerVO.setWorkAddrDetail("");
//		customerVO.setJobCode("");
//		customerVO.setIsSocialSecurity("");
//		customerVO.setSocialSecurityType("");
//		customerVO.setPostAddrId("");
//		customerVO.setIsCertificateValidate("");
//		customerVO.setCtfexpireDate(new Date());
//		customerVO.setIsPhoneValidate("");
//		customerVO.setLastNameSpell("");
//		customerVO.setFirstNameSpell("");
//		customerVO.setLastName("");
//		customerVO.setFirstName("");
//		customerVO.setRemark("");
//		customerVO.setFillera(Integer.valueOf("abc"));
//		customerVO.setFillerb("");
//		customerVO.setFillerc("");
//		customerVO.setCreateDate(new Date());
//		customerVO.setLastUpdateDate(new Date());
//		customerVO.setJobInfomation("");
//		customerVO.setWorkNationNality("");
//		customerVO.setFamilyNationNality("");
//		customerVO.setLeftImg("");
//		customerVO.setRightImg("");
//		customerVO.setFamilyCount(Integer.valueOf("abc"));
//		customerVO.setCoreCustomerId("");
//		customerVO.setIsHaveGreenCard("");
//		return null;
//	}
//	public static CustomerCreateVo createRequestBean(CustomerVO customerVO){
//		CustomerCreateVo customerBean = new CustomerCreateVo();
//		CustomerInfoVo customer = new CustomerInfoVo();
//		CrsInfosInfoVo crsInfosInfoVo = new CrsInfosInfoVo();
//		SocialInsuranceInfoVo socialInsuranceInfoVo = new SocialInsuranceInfoVo();
//		/* <---------- customer客户信息对象开始 ----------> */
//			/* <---------- addresses地址对象集合开始 ----------> */
//			List<AddressesInfoVo> addresses = new ArrayList<AddressesInfoVo>();
//				/* <---------- addressesInfoVo家庭地址对象开始 ----------> */
//				AddressesInfoVo addressesInfoVoR = new AddressesInfoVo();
//				addressesInfoVoR.setAddressLine1(customerVO.getFamilyAddrDetail());
//				addressesInfoVoR.setAddressLine2("string");
//				addressesInfoVoR.setAddressLine3("string");
//				addressesInfoVoR.setAddressLine4("string");
//				addressesInfoVoR.setAddressLine5("string");
//				addressesInfoVoR.setAddressType("R");
//				addressesInfoVoR.setAlternateContact("string");
//				addressesInfoVoR.setAlternateContactType("string");
//				//addressesInfoVoR.setCityCode("string");
//				addressesInfoVoR.setClientRelation("P");
//				addressesInfoVoR.setCountryCode(customerVO.getFamilyNationNality());
//				//addressesInfoVoR.setCountyCode("string");
//				addressesInfoVoR.setCustomerAddrId("string");
//				addressesInfoVoR.setCustomerId(customerVO.getCustomerId());
//				addressesInfoVoR.setEmail(customerVO.getEmail());
//				addressesInfoVoR.setFullAddress("string");
//				addressesInfoVoR.setMobileNumber(customerVO.getCellphone());
//				addressesInfoVoR.setProvinceCode(customerVO.getFamilyProcince());
//				 if(AREA.containsKey(customerVO.getFamilyArea())){
//					 addressesInfoVoR.setCountyCode(AREA.get(customerVO.getFamilyArea()));
//                 }else{
//                	 addressesInfoVoR.setCountyCode(customerVO.getFamilyArea());
//                 }
//				if(CITY.containsKey(customerVO.getFamilyCity())) {
//					addressesInfoVoR.setProvinceCode(CITY.get(customerVO.getFamilyCity()));
//                }else{
//                	addressesInfoVoR.setCityCode(customerVO.getFamilyCity());
//                }
//				List<String> systemIdsR = new ArrayList<String>();
//					systemIdsR.add("string");
//				addressesInfoVoR.setSystemIds(systemIdsR);
//				addressesInfoVoR.setZipCode(customerVO.getFamilyPostcode());
//				addresses.add(addressesInfoVoR);
//				/* <---------- addressesInfoVo家庭地址对象结束 ----------> */
//				/* <---------- addressesInfoVo工作地址对象开始 ----------> */
//				AddressesInfoVo addressesInfoVoB = new AddressesInfoVo();
//				addressesInfoVoB.setAddressLine1(customerVO.getWorkAddrDetail());
//				addressesInfoVoB.setAddressLine2("string");
//				addressesInfoVoB.setAddressLine3("string");
//				addressesInfoVoB.setAddressLine4("string");
//				addressesInfoVoB.setAddressLine5("string");
//				addressesInfoVoB.setAddressType("B");
//				addressesInfoVoB.setAlternateContact("string");
//				addressesInfoVoB.setAlternateContactType("string");
//				//addressesInfoVoB.setCityCode("string");
//				addressesInfoVoB.setClientRelation("P");
//				addressesInfoVoB.setCountryCode(customerVO.getWorkNationNality());
//				//addressesInfoVoB.setCountyCode("string");
//				addressesInfoVoB.setCustomerAddrId("string");
//				addressesInfoVoB.setCustomerId(customerVO.getCustomerId());
//				addressesInfoVoB.setEmail(customerVO.getEmail());
//				addressesInfoVoB.setFullAddress("string");
//				addressesInfoVoB.setMobileNumber(customerVO.getCellphone());
//				addressesInfoVoB.setProvinceCode(customerVO.getWorkProcince());
//				 if(AREA.containsKey(customerVO.getWorkArea())){
//					 addressesInfoVoR.setCountyCode(AREA.get(customerVO.getWorkArea()));
//                 }else{
//                	 addressesInfoVoR.setCountyCode(customerVO.getWorkArea());
//                 }
//				if (CITY.containsKey(customerVO.getWorkCity())) {
//					addressesInfoVoR.setProvinceCode(CITY.get(customerVO.getWorkCity()));
//				} else {
//					addressesInfoVoR.setCityCode(customerVO.getWorkCity());
//				}
//				List<String> systemIdsB = new ArrayList<String>();
//					systemIdsB.add("string");
//				addressesInfoVoB.setSystemIds(systemIdsB);
//				addressesInfoVoB.setZipCode(customerVO.getWorkPostcode());
//				/* <---------- addressesInfoVoB工作地址对象结束 ----------> */
//			addresses.add(addressesInfoVoB);
//			/* <---------- 地址对象集合结束 ----------> */
//		customer.setAddresses(addresses);
//		customer.setAgent("string");
//		customer.setAgentCode("string");
//		customer.setAlternateContact("string");
//		customer.setAlternateContactType("string");
//		customer.setAmlReportStatus("string");
//		customer.setAmla("string");
//		customer.setBankruptind("Y");
//		customer.setBirthCountry(customerVO.getNationality());
//		customer.setBirthDate(customerVO.getBirthday().toString());
//		customer.setBirthPlace("string");
//		customer.setBirthPlaceCode("string");
//		customer.setBlackListReason("string");
//		customer.setBusinessRegNo("string");
//		customer.setBusinessRegType("string");
//		customer.setCapital("string");
//		customer.setCategory("string");
//		customer.setCensusRegister("string");
//		customer.setClientType("P");
//		customer.setCompanyContact("string");
//		customer.setCompanyIndustry("string");
//		customer.setCompanyName("string");
//		customer.setCompanyName2("string");
//		customer.setCompanyName3("string");
//		customer.setCountryCode("string");
//			/* <----------crsInfos对象集合开始 ----------> */
//			List<CrsInfosInfoVo> crsInfos = new ArrayList<CrsInfosInfoVo>();
//				/* <----------crsInfosInfoVo对象开始 ----------> */
//				crsInfosInfoVo.setActiveStatus("string");
//				crsInfosInfoVo.setEndDate("string");
//				crsInfosInfoVo.setId("string");
//				crsInfosInfoVo.setIndicator("string");
//				crsInfosInfoVo.setNationality("string");
//				crsInfosInfoVo.setNoTin("string");
//				crsInfosInfoVo.setReasonFlag("string");
//				crsInfosInfoVo.setRemark1("string");
//				crsInfosInfoVo.setRemark2("string");
//				crsInfosInfoVo.setRemark3("string");
//				crsInfosInfoVo.setStartDate("string");
//				crsInfosInfoVo.setTaxId("string");
//				crsInfosInfoVo.setTaxIdOld("string");
//				/* <----------crsInfosInfoVo对象结束 ----------> */
//			crsInfos.add(crsInfosInfoVo);
//			/* <----------crsInfos对象集合结束 ----------> */
//		customer.setCrsInfos(crsInfos);
//		customer.setCustomerId(customerVO.getCoreCustomerId());
//		customer.setDateOfWork("string");
//		customer.setDirectmail("Y");
//		customer.setDocNo("string");
//		customer.setDrivingLicenseCode("string");
//		customer.setDrivingLicenseNo("string");
//		customer.setEducationCode(customerVO.getEducation());
//		customer.setFao("string");
//		customer.setFatca("string");
//		customer.setFaxNumber("string");
//		customer.setFirstName(customerVO.getFirstName());
//		customer.setFpmsClientNumber("string");
//		customer.setFullName(customerVO.getName());
//		customer.setGender(customerVO.getSex());
//		customer.setHouseholdBookletRegPlace1("string");
//		customer.setHouseholdBookletRegPlace2("string");
//		customer.setHouseholdBookletRegPlace3("string");
//		customer.setIdExpiryDate(customerVO.getCtfexpireDate().toString());
//		customer.setIdIssueDate("string");
//		customer.setIdentityNo(customerVO.getIdentityCode());
//		customer.setIdentityType(customerVO.getIdentityType());
//		customer.setInception("string");
//		customer.setIssueCountry("string");//国籍
//		customer.setIssuePlace("string");
//		customer.setJobPosition("string");//customerVO.getWorkProcince();customerVO.getWorkCity();customerVO.getWorkArea();customerVO.getWorkAddrDetail();
//		customer.setJoinDate("string");
//		customer.setLanguage("E");
//		customer.setLastName(customerVO.getLastName());
//		customer.setLegalRep("string");
//		customer.setLicenseNum("string");
//		customer.setMailing("Y");
//		customer.setMaritalStatus(customerVO.getMaritalStatus());
//		customer.setMarriageDate("string");
//		customer.setMiddleName("string");
//		customer.setMobileNumber("string");
//		customer.setNameFormat("string");
//		customer.setNatOfChina("string");
//		customer.setNationality(customerVO.getNationality());
//		customer.setNationality2("string");
//		customer.setNationality3("string");
//		customer.setOccupationCode(customerVO.getJobCode());
//		customer.setOldIdNo("string");
//		customer.setOriginCountry("string");
//		customer.setPhoneNumber1("string");
//		customer.setPhoneNumber2("string");
//		customer.setPreferName("string");
//		customer.setPreferName2("string");
//		customer.setPreferName3("string");
//		customer.setRegCountry("string");
//		customer.setRegPlace("string");
//		customer.setRiskRating("string");
//			/* <----------crsInfos对象开始 ----------> */
//			socialInsuranceInfoVo.setIndicator("string");
//			socialInsuranceInfoVo.setPaymentAddress1("string");
//			socialInsuranceInfoVo.setPaymentAddress2("string");
//			socialInsuranceInfoVo.setRegNum("string");
//			socialInsuranceInfoVo.setRemark("string");
//			/* <----------crsInfos对象开始 ----------> */
//		customer.setSocialInsurance(socialInsuranceInfoVo);
//		customer.setSourceOfEvidence("string");
//		customer.setStaffFlag("string");
//		customer.setStaffNo("string");
//		customer.setStatus("A");
//		customer.setTaxFlag("string");
//		customer.setTaxId("string");
//		customer.setTitle("MR");
//		customer.setVip("Y");
//		customer.setWorkingStatus("string");
//		customer.setWorkingTime("string");
//		/* <---------- customer客户信息对象结束 ----------> */
//			
//		/* <---------- 请求数据大对象开始 ----------> */
//		customerBean.setCustomer(customer);
//		customerBean.setTempCustomerId("string");//
//		/* <---------- 请求数据大对象结束 ----------> */
//		
//		return customerBean;
//		
//	}
//	
//	public static CustomerUpdateVo updateRequestBean(CustomerVO customerVO){
//		CustomerUpdateVo customerBean = new CustomerUpdateVo();
//		//CustomerCreateVo customerBean = new CustomerCreateVo();
//		CustomerInfoVo customer = new CustomerInfoVo();
//		CrsInfosInfoVo crsInfosInfoVo = new CrsInfosInfoVo();
//		SocialInsuranceInfoVo socialInsuranceInfoVo = new SocialInsuranceInfoVo();
//		/* <---------- customer客户信息对象开始 ----------> */
//			/* <---------- addresses地址对象集合开始 ----------> */
//			List<AddressesInfoVo> addresses = new ArrayList<AddressesInfoVo>();
//				/* <---------- addressesInfoVo家庭地址对象开始 ----------> */
//				AddressesInfoVo addressesInfoVoR = new AddressesInfoVo();
//				addressesInfoVoR.setAddressLine1(customerVO.getFamilyAddrDetail());
//				addressesInfoVoR.setAddressLine2("string");
//				addressesInfoVoR.setAddressLine3("string");
//				addressesInfoVoR.setAddressLine4("string");
//				addressesInfoVoR.setAddressLine5("string");
//				addressesInfoVoR.setAddressType("R");
//				addressesInfoVoR.setAlternateContact("string");
//				addressesInfoVoR.setAlternateContactType("string");
//				//addressesInfoVoR.setCityCode("string");
//				addressesInfoVoR.setClientRelation("P");
//				addressesInfoVoR.setCountryCode(customerVO.getFamilyNationNality());
//				//addressesInfoVoR.setCountyCode("string");
//				addressesInfoVoR.setCustomerAddrId("string");
//				addressesInfoVoR.setCustomerId(customerVO.getCustomerId());
//				addressesInfoVoR.setEmail(customerVO.getEmail());
//				addressesInfoVoR.setFullAddress("string");
//				addressesInfoVoR.setMobileNumber(customerVO.getCellphone());
//				addressesInfoVoR.setProvinceCode(customerVO.getFamilyProcince());
//				 if(AREA.containsKey(customerVO.getFamilyArea())){
//					 addressesInfoVoR.setCountyCode(AREA.get(customerVO.getFamilyArea()));
//                 }else{
//                	 addressesInfoVoR.setCountyCode(customerVO.getFamilyArea());
//                 }
//				if(CITY.containsKey(customerVO.getFamilyCity())) {
//					addressesInfoVoR.setProvinceCode(CITY.get(customerVO.getFamilyCity()));
//                }else{
//                	addressesInfoVoR.setCityCode(customerVO.getFamilyCity());
//                }
//				List<String> systemIdsR = new ArrayList<String>();
//					systemIdsR.add("string");
//				addressesInfoVoR.setSystemIds(systemIdsR);
//				addressesInfoVoR.setZipCode(customerVO.getFamilyPostcode());
//				addresses.add(addressesInfoVoR);
//				/* <---------- addressesInfoVo家庭地址对象结束 ----------> */
//				/* <---------- addressesInfoVo工作地址对象开始 ----------> */
//				AddressesInfoVo addressesInfoVoB = new AddressesInfoVo();
//				addressesInfoVoB.setAddressLine1(customerVO.getWorkAddrDetail());
//				addressesInfoVoB.setAddressLine2("string");
//				addressesInfoVoB.setAddressLine3("string");
//				addressesInfoVoB.setAddressLine4("string");
//				addressesInfoVoB.setAddressLine5("string");
//				addressesInfoVoB.setAddressType("B");
//				addressesInfoVoB.setAlternateContact("string");
//				addressesInfoVoB.setAlternateContactType("string");
//				//addressesInfoVoB.setCityCode("string");
//				addressesInfoVoB.setClientRelation("P");
//				addressesInfoVoB.setCountryCode(customerVO.getWorkNationNality());
//				//addressesInfoVoB.setCountyCode("string");
//				addressesInfoVoB.setCustomerAddrId("string");
//				addressesInfoVoB.setCustomerId(customerVO.getCustomerId());
//				addressesInfoVoB.setEmail(customerVO.getEmail());
//				addressesInfoVoB.setFullAddress("string");
//				addressesInfoVoB.setMobileNumber(customerVO.getCellphone());
//				addressesInfoVoB.setProvinceCode(customerVO.getWorkProcince());
//				 if(AREA.containsKey(customerVO.getWorkArea())){
//					 addressesInfoVoR.setCountyCode(AREA.get(customerVO.getWorkArea()));
//                 }else{
//                	 addressesInfoVoR.setCountyCode(customerVO.getWorkArea());
//                 }
//				if (CITY.containsKey(customerVO.getWorkCity())) {
//					addressesInfoVoR.setProvinceCode(CITY.get(customerVO.getWorkCity()));
//				} else {
//					addressesInfoVoR.setCityCode(customerVO.getWorkCity());
//				}
//				List<String> systemIdsB = new ArrayList<String>();
//					systemIdsB.add("string");
//				addressesInfoVoB.setSystemIds(systemIdsB);
//				addressesInfoVoB.setZipCode(customerVO.getWorkPostcode());
//				/* <---------- addressesInfoVoB工作地址对象结束 ----------> */
//			addresses.add(addressesInfoVoB);
//			/* <---------- 地址对象集合结束 ----------> */
//		customer.setAddresses(addresses);
//		customer.setAgent("string");
//		customer.setAgentCode("string");
//		customer.setAlternateContact("string");
//		customer.setAlternateContactType("string");
//		customer.setAmlReportStatus("string");
//		customer.setAmla("string");
//		customer.setBankruptind("Y");
//		customer.setBirthCountry(customerVO.getNationality());
//		customer.setBirthDate(customerVO.getBirthday().toString());
//		customer.setBirthPlace("string");
//		customer.setBirthPlaceCode("string");
//		customer.setBlackListReason("string");
//		customer.setBusinessRegNo("string");
//		customer.setBusinessRegType("string");
//		customer.setCapital("string");
//		customer.setCategory("string");
//		customer.setCensusRegister("string");
//		customer.setClientType("P");
//		customer.setCompanyContact("string");
//		customer.setCompanyIndustry("string");
//		customer.setCompanyName("string");
//		customer.setCompanyName2("string");
//		customer.setCompanyName3("string");
//		customer.setCountryCode("string");
//			/* <----------crsInfos对象集合开始 ----------> */
//			List<CrsInfosInfoVo> crsInfos = new ArrayList<CrsInfosInfoVo>();
//				/* <----------crsInfosInfoVo对象开始 ----------> */
//				crsInfosInfoVo.setActiveStatus("string");
//				crsInfosInfoVo.setEndDate("string");
//				crsInfosInfoVo.setId("string");
//				crsInfosInfoVo.setIndicator("string");
//				crsInfosInfoVo.setNationality("string");
//				crsInfosInfoVo.setNoTin("string");
//				crsInfosInfoVo.setReasonFlag("string");
//				crsInfosInfoVo.setRemark1("string");
//				crsInfosInfoVo.setRemark2("string");
//				crsInfosInfoVo.setRemark3("string");
//				crsInfosInfoVo.setStartDate("string");
//				crsInfosInfoVo.setTaxId("string");
//				crsInfosInfoVo.setTaxIdOld("string");
//				/* <----------crsInfosInfoVo对象结束 ----------> */
//			crsInfos.add(crsInfosInfoVo);
//			/* <----------crsInfos对象集合结束 ----------> */
//		customer.setCrsInfos(crsInfos);
//		customer.setCustomerId(customerVO.getCoreCustomerId());
//		customer.setDateOfWork("string");
//		customer.setDirectmail("Y");
//		customer.setDocNo("string");
//		customer.setDrivingLicenseCode("string");
//		customer.setDrivingLicenseNo("string");
//		customer.setEducationCode(customerVO.getEducation());
//		customer.setFao("string");
//		customer.setFatca("string");
//		customer.setFaxNumber("string");
//		customer.setFirstName(customerVO.getFirstName());
//		customer.setFpmsClientNumber("string");
//		customer.setFullName(customerVO.getName());
//		customer.setGender(customerVO.getSex());
//		customer.setHouseholdBookletRegPlace1("string");
//		customer.setHouseholdBookletRegPlace2("string");
//		customer.setHouseholdBookletRegPlace3("string");
//		customer.setIdExpiryDate(customerVO.getCtfexpireDate().toString());
//		customer.setIdIssueDate("string");
//		customer.setIdentityNo(customerVO.getIdentityCode());
//		customer.setIdentityType(customerVO.getIdentityType());
//		customer.setInception("string");
//		customer.setIssueCountry("string");//国籍
//		customer.setIssuePlace("string");
//		customer.setJobPosition("string");//customerVO.getWorkProcince();customerVO.getWorkCity();customerVO.getWorkArea();customerVO.getWorkAddrDetail();
//		customer.setJoinDate("string");
//		customer.setLanguage("E");
//		customer.setLastName(customerVO.getLastName());
//		customer.setLegalRep("string");
//		customer.setLicenseNum("string");
//		customer.setMailing("Y");
//		customer.setMaritalStatus(customerVO.getMaritalStatus());
//		customer.setMarriageDate("string");
//		customer.setMiddleName("string");
//		customer.setMobileNumber("string");
//		customer.setNameFormat("string");
//		customer.setNatOfChina("string");
//		customer.setNationality(customerVO.getNationality());
//		customer.setNationality2("string");
//		customer.setNationality3("string");
//		customer.setOccupationCode(customerVO.getJobCode());
//		customer.setOldIdNo("string");
//		customer.setOriginCountry("string");
//		customer.setPhoneNumber1("string");
//		customer.setPhoneNumber2("string");
//		customer.setPreferName("string");
//		customer.setPreferName2("string");
//		customer.setPreferName3("string");
//		customer.setRegCountry("string");
//		customer.setRegPlace("string");
//		customer.setRiskRating("string");
//			/* <----------crsInfos对象开始 ----------> */
//			socialInsuranceInfoVo.setIndicator("string");
//			socialInsuranceInfoVo.setPaymentAddress1("string");
//			socialInsuranceInfoVo.setPaymentAddress2("string");
//			socialInsuranceInfoVo.setRegNum("string");
//			socialInsuranceInfoVo.setRemark("string");
//			/* <----------crsInfos对象开始 ----------> */
//		customer.setSocialInsurance(socialInsuranceInfoVo);
//		customer.setSourceOfEvidence("string");
//		customer.setStaffFlag("string");
//		customer.setStaffNo("string");
//		customer.setStatus("A");
//		customer.setTaxFlag("string");
//		customer.setTaxId("string");
//		customer.setTitle("MR");
//		customer.setVip("Y");
//		customer.setWorkingStatus("string");
//		customer.setWorkingTime("string");
//		/* <---------- customer客户信息对象结束 ----------> */
//			
//		/* <---------- 请求数据大对象开始 ----------> */
//		customerBean.setCustomer(customer);
//		customerBean.setCustomerId("string");
//		customerBean.setDisablePartialUpdate("string");
//		customerBean.setSystemClientId("string");
//		/* <---------- 请求数据大对象结束 ----------> */
//		return customerBean;
//		
//	}
//
//}
