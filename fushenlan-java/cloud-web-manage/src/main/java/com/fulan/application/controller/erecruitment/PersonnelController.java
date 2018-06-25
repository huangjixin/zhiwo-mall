package com.fulan.application.controller.erecruitment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fulan.api.agent.domain.Customer;
import com.fulan.api.agent.service.CustomerService;
import com.fulan.api.agent.vo.CustomerSearchParm;
import com.fulan.api.agent.vo.CustomerVo;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.service.PersonnelService;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;
import com.fulan.api.personnel.vo.PersonnelManageVo;
import com.fulan.api.personnel.vo.SearchPersonnelVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.api.system.service.DictionaryService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;
import com.fulan.application.util.util.CardTypeConvert;

@Controller
public class PersonnelController {
	
	@Autowired
	private AttachmentService attachmentService;
    
	@Autowired
	private PersonnelService personnelService;

	@Autowired
	private CustomerService customerService;
	@Autowired
	private DictionaryService dictionaryService;
	
	@GetMapping("/manage/erecruitment/personnelList")
	public String list(Model model ,SearchPersonnelVo searchPersonnelVo){
		Account account = SessionContextUtils.getCurrentUser();
    	searchPersonnelVo.setSearchType(0);
    	searchPersonnelVo.setOrgId(account.getCompanyId());
		PageInfo<PersonnelManageVo> page  = personnelService.selectByPage(searchPersonnelVo);
		model.addAttribute("page", page);
		model.addAttribute("keyWord", searchPersonnelVo.getKeyWord());
		return "personnel/personnel";
		
	}
	
	
	/**
	 * 获取分页内容
	 * @param keyWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/personnel/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<PersonnelManageVo>> listByPageAjax(SearchPersonnelVo searchPersonnelVo){
		Account account = SessionContextUtils.getCurrentUser();
    	searchPersonnelVo.setSearchType(0);
    	searchPersonnelVo.setOrgId(account.getCompanyId());
		PageInfo<PersonnelManageVo> personnelManageVoList  = personnelService.selectByPage(searchPersonnelVo);
		Response<PageInfo<PersonnelManageVo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(personnelManageVoList);
		return response;
		
	}
	
	
	
	
	@GetMapping("/manage/erecruitment/OtherpersonnelList")
	public String listByOtherPage(Model model ,SearchPersonnelVo searchPersonnelVo){
		Account account = SessionContextUtils.getCurrentUser();
    	searchPersonnelVo.setSearchType(1);
    	searchPersonnelVo.setOrgId(account.getCompanyId());
		PageInfo<PersonnelManageVo> page  = personnelService.selectByPage(searchPersonnelVo);
		model.addAttribute("page", page);
		model.addAttribute("keyWord", searchPersonnelVo.getKeyWord());
		return "personnel/personnelOtherTwo";
		
	}
	
	/**
	 * 获取分页内容
	 * @param keyWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/personnel/listByOtherPageAjax")
	@ResponseBody
	public Response<PageInfo<PersonnelManageVo>> listByOtherPageAjax(SearchPersonnelVo searchPersonnelVo){
		Account account = SessionContextUtils.getCurrentUser();
    	searchPersonnelVo.setSearchType(1);
    	searchPersonnelVo.setOrgId(account.getCompanyId());
		PageInfo<PersonnelManageVo> personnelManageVoList  = personnelService.selectByPage(searchPersonnelVo);
		Response<PageInfo<PersonnelManageVo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(personnelManageVoList);
		return response;
		
	}
	
	
	
	
	/**
	 * 复核的
	 * @param model
	 * @param keyWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/erecruitment/personnelOtherList")
	public String listOther(Model model ,SearchPersonnelVo searchPersonnelVo){
		Account account = SessionContextUtils.getCurrentUser();
    	searchPersonnelVo.setSearchType(2);
    	searchPersonnelVo.setOrgId(account.getCompanyId());
		PageInfo<PersonnelManageVo> page  = personnelService.selectByPage(searchPersonnelVo);
		model.addAttribute("page", page);
		model.addAttribute("keyWord", searchPersonnelVo.getKeyWord());
		return "personnel/personnelOther";
		
	}
	
	/**
	 * 复核的获取分页内容
	 * @param keyWord
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/personnel/listByOtherTwoPageAjax")
	@ResponseBody
	public Response<PageInfo<PersonnelManageVo>> listByOtherTwoPageAjax(SearchPersonnelVo searchPersonnelVo){
		Account account = SessionContextUtils.getCurrentUser();
    	searchPersonnelVo.setSearchType(2);
    	searchPersonnelVo.setOrgId(account.getCompanyId());
		PageInfo<PersonnelManageVo> personnelManageVoList  = personnelService.selectByPage(searchPersonnelVo);
		Response<PageInfo<PersonnelManageVo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(personnelManageVoList);
		return response;
		
	}
	
	
	@GetMapping("/manage/erecruitment/findId")
	public String findById(Model model , String id){
		PersonnelManageInfoVo person = personnelService.findById(id);
		//获取字典url
		Response<Dictionary> dictionary = dictionaryService.findByCodeDictionary("uploadPath");
		String fileId = dictionary.getData().getValue();
		model.addAttribute("person", person);
		model.addAttribute("fileIp", fileId);
		return "personnel/personnel_detail";
	}
	/**
	 * 复核的
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping("/manage/erecruitment/findOtherId")
	public String findByOtherId(Model model , String id){
		PersonnelManageInfoVo person = personnelService.findById(id);
		//获取字典url
		model.addAttribute("person", person);
		Personnel personnel ;
		int cardTypePositive = CommenConstant.ER_IDEN_PHOTO_POSITIVE;
		int cardTypeBack = CommenConstant.ER_IDEN_PHOTO_REVERSE;
		
		if(person!=null && person.getPersonnel()!=null){
			personnel = person.getPersonnel();
			cardTypePositive = CardTypeConvert.transformPositive(personnel.getIdentityType());
			cardTypeBack = CardTypeConvert.transformBack(personnel.getIdentityType());
		}
		
	   String path1=attachmentService.findImgByTableAndHostId(cardTypePositive, person.getPersonnel().getId());
	   String path2=attachmentService.findImgByTableAndHostId(cardTypeBack, person.getPersonnel().getId());
	   String path4=attachmentService.findImgByTableAndHostId(CommenConstant.ER_EDUCATION_PROVE, person.getPersonnel().getId());
	   String path5=attachmentService.findImgByTableAndHostId(CommenConstant.ER_XUEXIN_PROVE, person.getPersonnel().getId());
	   String path13=attachmentService.findImgByTableAndHostId(CommenConstant.ER_FOREIGNPERSON_JOB_PROVE, person.getPersonnel().getId());
	   model.addAttribute("path1", path1);
	   model.addAttribute("path2", path2);
	   model.addAttribute("path4", path4);
	   model.addAttribute("path5", path5);
	   model.addAttribute("path13", path13);
	   return "personnel/personnelOther_detail";
	}
	
	@GetMapping("/manage/erecruitment/getdmsinfo")
	public String getdmsinfo(Model model, String personnelid, String personnelidentityType,
			String personnelidentityCode, String personnelname, String personnelsex) {
		//TODO token 等参数需从配置文件获取
		CustomerSearchParm customer = new CustomerSearchParm();
		customer.setGender(personnelsex);
		customer.setName("牛魔王");
		customer.setIdNumber("310104198004097779");
		//customer.setIdType(personnelidentityType);

		String customerlist = customerService.search("dev", "LA", "34c6fceca75e456f25e7e99531e2425c6c1de443", customer)
				.getData();

		JSONObject jsonObject1 = JSON.parseObject(customerlist);
		String data = jsonObject1.getString("customerList");
		JSONArray jsonObjects = JSON.parseArray(data);
		List<Customer> list = new ArrayList<Customer>();
		for (int i = 0; i < jsonObjects.size(); i++) {
			Customer c = new Customer();
			String cus = ((JSONObject) jsonObjects.get(i)).getString("customer");
			JSONObject jsonObt = JSON.parseObject(cus);
			c.setIdentityType(jsonObt.getString("identityType"));
			c.setLastName(jsonObt.getString("lastName"));
			c.setIdentityNo(jsonObt.get("identityNo").toString());
			c.setGender(jsonObt.getString("gender"));
			c.setCustomerId(jsonObt.getString("customerId"));
			list.add(c);
		}

		model.addAttribute("customerlist", list);
		model.addAttribute("personnelidentityType", personnelidentityType);
		model.addAttribute("personnelidentityCode", personnelidentityCode);
		model.addAttribute("personnelname", personnelname);
		model.addAttribute("personnelsex", personnelsex);
		model.addAttribute("personnelid", personnelid);

		return "personnel/choice_dmsinfo";
	}

	/**
	 * updatedmsinfo 本地系统数据跟新到DMS系统中
	 * 
	 * @warn(注意事项 – 可选)
	 * @param model
	 * @param personnelid
	 * @param identityType
	 * @param identityNo
	 * @param lastName
	 * @param gender
	 * @param customerId
	 * @return
	 */
	@PostMapping("/manage/erecruitment/dmsupdate")
	@ResponseBody
	public String updatedmsinfo(Model model, String personnelid, String identityType, String identityNo,
			String lastName, String gender, String customerId) {
		//TODO token 等参数需从配置文件获取
		CustomerVo customerVo = new CustomerVo();

		Customer customer = new Customer();
		customer.setIdentityType(identityType);
		customer.setIdentityNo(identityNo);
		customer.setGender(gender);
		customer.setLastName(lastName);
		customerVo.setCustomer(customer);
		String customerlist;
		if(customerId == null){
			customerlist= customerService.create("dev", "LA", "34c6fceca75e456f25e7e99531e2425c6c1de443", customerVo).getData();
		}else{
			customer.setCustomerId(customerId);
			customerlist = customerService
					.update("dev", "LA", "34c6fceca75e456f25e7e99531e2425c6c1de443", customerVo).getData();
		}
		return customerlist;
	}

	/**
	 * locupdate dms 系统数据更新到本系统
	 * 
	 * @warn(注意事项 – 可选)
	 * @param model
	 * @param personnelid
	 * @param identityType
	 * @param identityNo
	 * @param lastName
	 * @param gender
	 * @param customerId
	 * @return
	 */
	@PostMapping("/manage/erecruitment/locupdate")
	@ResponseBody
	public int locupdate(Model model, String personnelid, String identityType, String identityNo, String lastName,
			String gender, String customerId) {

		Personnel personnel = new Personnel();
		personnel.setIdentityCode(identityNo);
		personnel.setIdentityType(identityType);

		String sex;
		if (gender.equals("M")) {
			sex = "1";
		} else {
			sex = "2";
		}
		personnel.setSex(sex);
		personnel.setName(lastName);
		personnel.setId(Long.valueOf(personnelid));

		int num = personnelService.updatePersonnel(personnel).getData();
		
		return num;
	}
	
	
	
	
	@PostMapping("/manage/erecruitment/pass")
    @ResponseBody
    public String updateCheckResult(Personnel personnel ){
		return personnelService.updateCheckResult(personnel);
    }
	
	@GetMapping("/manage/erecruitment/uploadFail")
	public void uploadFail(String category ,String hostId){
		attachmentService.findFileByTableAndHostId(Integer.parseInt(category), Long.parseLong(hostId));
	}
}
