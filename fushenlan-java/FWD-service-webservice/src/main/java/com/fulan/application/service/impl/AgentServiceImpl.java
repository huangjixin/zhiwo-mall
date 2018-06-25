package com.fulan.application.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fulan.api.agent.vo.Address;
import com.fulan.api.agent.vo.Agent;
import com.fulan.api.agent.vo.AgentReport;
import com.fulan.api.agent.vo.Branch;
import com.fulan.api.agent.vo.Contact;
import com.fulan.api.agent.vo.Customer;
import com.fulan.api.agent.vo.ErrorMessage;
import com.fulan.api.agent.vo.License;
import com.fulan.api.agent.vo.Req;
import com.fulan.api.agent.vo.ResultBranchs;
import com.fulan.api.agent.vo.ResultCustomer;
import com.fulan.api.agent.vo.ResultModel;
import com.fulan.api.system.domain.Organization;
import com.fulan.api.system.service.OrganizationService;
import com.fulan.application.service.AgentService;
import com.fulan.application.service.ResumeClient;
import com.fulan.application.util.domain.Response;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
    private ResumeClient resumeClient;
	@Autowired
	private OrganizationService organizationService;

    @Override
	public Response<ResultModel> getAgent(String userId,String token,Req agentInfoVo) {
    	Response<ResultModel> resp = new Response<ResultModel>(Response.SUCCESS, "查找代理人信息成功");
    	String jsonStr = resumeClient.getAgent(userId,token,agentInfoVo);
    	// JSON转换
    	JSONObject jsonObj = JSONObject.fromObject(jsonStr); 
    	Object objectAgent = jsonObj.get("agent");
    	Agent agent = (Agent)JSONObject.toBean((JSONObject) objectAgent, Agent.class);
    	Object objectBranchs = jsonObj.get("branchs");
    	List<Branch> branchs = (List<Branch>) objectBranchs;
    	Object objectAddresses = jsonObj.get("addresses");
    	List<Address> addresses = (List<Address>) objectAddresses;
    	Object objectAgentReport = jsonObj.get("agentReport");
    	List<AgentReport> agentReport = (List<AgentReport>) objectAgentReport;
    	Object objectContacts = jsonObj.get("contacts");
    	List<Contact> contacts = (List<Contact>) objectContacts;
    	Object objectErrorMessage = jsonObj.get("errorMessage");
    	List<ErrorMessage> errorMessage = (List<ErrorMessage>) objectErrorMessage;
    	Object objectLicenses = jsonObj.get("licenses");
    	List<License> licenses = (List<License>) objectLicenses;
    	String message = (String) jsonObj.get("message");
    	String status = (String) jsonObj.get("status");
    	
    	ResultModel resultModel = new ResultModel();
		resultModel.setAgent(agent);
		resultModel.setBranchs(branchs);	
		resultModel.setAddresses(addresses);
		resultModel.setAgentReport(agentReport);
		resultModel.setLicenses(licenses);
		resultModel.setContacts(contacts);
		resultModel.setErrorMessage(errorMessage);
		resultModel.setMessage(message);
		resultModel.setStatus(status);
    	resp.setData(resultModel);
        return resp;
    }

	@Override
	public Response<ResultBranchs> getBranch(String userId, String token,
			Req agentInfoVo) {
    	Response<ResultBranchs> resp = new Response<ResultBranchs>(Response.SUCCESS, "查找机构信息成功");
    	String jsonStr = resumeClient.getBranch(userId,token,agentInfoVo);
    	// JSON转换
    	JSONObject jsonObj = JSONObject.fromObject(jsonStr); 
    	JSONArray branchs = (JSONArray) jsonObj.get("branchs");
    	Object objectErrorMessage = jsonObj.get("errorMessage");
    	List<ErrorMessage> errorMessage = (List<ErrorMessage>) objectErrorMessage;
    	String message = (String) jsonObj.get("message");
    	String status = (String) jsonObj.get("status");
    	//删除组织机构表里面的所有已经存在的机构数据
	    boolean flag = organizationService.deleteAll().getData();
	    System.out.println("====================="+flag);
	    //将分支机构数据插入到组织机构表中
    	Organization organization = new Organization();
    	for(int i=0;i<branchs.size();i++){
    		JSONObject branch = (JSONObject) branchs.get(i);
    		String branchID = (String) branch.get("branchID");
			String branchName = (String) branch.get("branchName");
    		String parentID = (String) branch.get("parentID");
    		organization.setCode(branchID);
    		organization.setCnName(branchName);
    		organization.setEnabled(true);
    		organization.setId(branchID);
    		organization.setParentId(parentID);
    		organizationService.insertFromDms(organization);
    	}
    	ResultBranchs resultBranchs = new ResultBranchs();
    	resultBranchs.setBranchs(branchs);	
    	resultBranchs.setErrorMessage(errorMessage);
    	resultBranchs.setMessage(message);
    	resultBranchs.setStatus(status);
    	resp.setData(resultBranchs);
        return resp;
    }
	
	@Override
	public Response<ResultCustomer> getCustomerList(String userId, String token,
			Req agentInfoVo) {
    	Response<ResultCustomer> resp = new Response<ResultCustomer>(Response.SUCCESS, "查找客户信息成功");
    	String jsonStr = resumeClient.getCustomerList(userId,token,agentInfoVo);
    	// JSON转换
    	JSONObject jsonObj = JSONObject.fromObject(jsonStr); 
    	Object objectCustomerList = jsonObj.get("customerList");
    	List<Customer> customerList = (List<Customer>) objectCustomerList;
    	Object objectErrorMessage = jsonObj.get("errorMessage");
    	List<ErrorMessage> errorMessage = (List<ErrorMessage>) objectErrorMessage;
    	String message = (String) jsonObj.get("message");
    	String status = (String) jsonObj.get("status");
    	
    	ResultCustomer resultCustomer = new ResultCustomer();
    	resultCustomer.setCustomerList(customerList);	
    	resultCustomer.setErrorMessage(errorMessage);
    	resultCustomer.setMessage(message);
    	resultCustomer.setStatus(status);
    	resp.setData(resultCustomer);
        return resp;
    }

}
