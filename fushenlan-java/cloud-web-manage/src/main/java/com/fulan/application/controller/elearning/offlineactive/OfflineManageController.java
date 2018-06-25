package com.fulan.application.controller.elearning.offlineactive;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fulan.api.plan.domain.OfflineActivity;
import com.fulan.api.plan.service.OfflineActivityService;
import com.fulan.api.plan.vo.OfflineActivityVOPC;
import com.fulan.api.plan.vo.OfflineActivityVoFw;
import com.fulan.api.plan.vo.StudyPlanVvo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.security.vo.AccountOffVo;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.rabbitmq.client.impl.AMQImpl.Access.Request;


@Controller
@RequestMapping("/manage/offlineManage")
public class OfflineManageController {
	@Autowired
	private OfflineActivityService offlineActivityService;
	

	@Autowired
	private AccountService accountService;
	
	@Autowired
    private AttachmentService attachmentService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String homePage(){
		return "forward:/manage/offlineManage/planListByPage";
	}
	
	 //跳转的页面
	 @RequestMapping("/skipPage")
	 public String skipPage(String pageName){
	 return "elearning/offlineactive/"+pageName;
     }
	 
	//首页面
	@RequestMapping(value="/planListByPage")
	public String listByPage(@RequestParam(value="name",required=false)String name,
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value="enterStartDate",required=false)String enterStartDate,
			@RequestParam(value="enterEndDate",required=false)String enterEndDate,
			@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize, Model model){
		PageInfo<OfflineActivityVoFw> OfflineActivityList=offlineActivityService.pageList(name, state, enterStartDate, enterEndDate, Integer.parseInt(pageNo),Integer.parseInt(pageSize));
	   	List<OfflineActivityVoFw> offlineActivityList = OfflineActivityList.getRecords();
	   	model.addAttribute("name", name);
	   	model.addAttribute("state", state);
	   	model.addAttribute("enterStartDate", enterStartDate);
	 	model.addAttribute("enterEndDate", enterEndDate);
	 	model.addAttribute("offlineActivityList", offlineActivityList);
	 	model.addAttribute("pageList", OfflineActivityList);
	   	return "elearning/offlineactive/offline_manage";
		}
	
	//首页面
	@RequestMapping("/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<OfflineActivityVoFw>> listByPageAjax(@RequestParam(value="name",required=false)String name,
			@RequestParam(value="state",required=false)String state,
			@RequestParam(value="enterStartDate",required=false)String enterStartDate,
			@RequestParam(value="enterEndDate",required=false)String enterEndDate,
			@RequestParam(value="pageNo",required=false,defaultValue="1")String pageNo,
			@RequestParam(value="pageSize",required=false,defaultValue="10")String pageSize, Model model){
		
		PageInfo<OfflineActivityVoFw> offlineActivityList=offlineActivityService.pageList(name, state, enterStartDate,
			    enterEndDate,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		Response<PageInfo<OfflineActivityVoFw>> response = new Response<>(Response.SUCCESS, null);
		response.setData(offlineActivityList);
		return response;
	}
	
	
	@RequestMapping("/updateOrDele")
	@ResponseBody
	 public Response<String> updateOrDele(@RequestParam(value="type",required=false)String type,
	    		@RequestParam(value="state",required=false)String state,
	    		@RequestParam(value="ids",required=false)String ids){
		return offlineActivityService.updateOrDele(type, state, ids);
		
	}
	
	//添加线下课程
	@RequestMapping("/addCourse")
	public String addCourse(String online,String idList,Model model){
		model.addAttribute("online", online);
		model.addAttribute("idList", idList);
		return "elearning/offlineactive/onlineCourse_add";
		
	}
	
	//新增或修改
	@RequestMapping("/insertOrUpdate")
	public String insertOrUpdate(OfflineActivityVOPC offlineActivityVOPC, Long fileId){//课程开始时间，结束时间
		OfflineActivity offlineActivity=offlineActivityVOPC.getOfflineActivity();
		if(offlineActivity.getIsNeedEnter()==null){
			offlineActivity.setIsNeedEnter(0);
		}
		if(offlineActivity.getIsNeedSign()==null){
			offlineActivity.setIsNeedSign(0);
		}
		if(offlineActivity.getIsNoneedEnter()==null){
			offlineActivity.setIsNoneedEnter(0);
		}
		
		Long id = SessionContextUtils.getLoginUserId();
		
		offlineActivityVOPC.setStartTime(offlineActivityVOPC.getOfflineActivity().getEndDate());
		offlineActivityVOPC.setEndTime(offlineActivityVOPC.getOfflineActivity().getStartDate());
		if(null == offlineActivityVOPC.getOfflineActivity().getId()){
			offlineActivityVOPC.getOfflineActivity().setCreateUser(id);
		}else{
			offlineActivityVOPC.getOfflineActivity().setModifyUser(id);
		}
		Response<String> res= offlineActivityService.insertOrUpdateFW(offlineActivityVOPC, fileId);
		return "redirect:/manage/offlineManage/planListByPage";
	}

	  //根据id查找详细信息(即进入查看页面)
		@RequestMapping("/findByid")
	 	public String findByid(String id,String state,Model model){
			String isSign=null;
			String accountName=null;
			String type=null;
			int pageNo=1;
			int pageSize=10;
			Map<String, Object> map=offlineActivityService.findByIdFW(id, accountName, isSign, type, pageNo, pageSize);
			Object offlineActivity =  map.get("offlineActivity");
			model.addAttribute("id", id);
			model.addAttribute("state", state);
			model.addAttribute("planCourse", map.get("planCourse"));
			model.addAttribute("planAuthorityList",map.get("planAuthorityList"));
			model.addAttribute("offlineActivity", offlineActivity);
			return "elearning/offlineactive/seeOffline";
		}
		
		 //根据id查找详细信息(即进入修改页面)
		@RequestMapping("/editByid")
	 	public String editByid(String id,Model model){
			String isSign=null;
			String accountName=null;
			String type=null;
			int pageNo=1;
			int pageSize=10;
			Map<String, Object> map=offlineActivityService.findByIdFW(id, accountName, isSign, type, pageNo, pageSize);
			Object offlineActivity=  map.get("offlineActivity");
			Object planCourse=map.get("planCourse");
			model.addAttribute("planAuthorityList",map.get("planAuthorityList"));
			model.addAttribute("planCourse", planCourse);
			model.addAttribute("id", id);
			model.addAttribute("offlineActivity", offlineActivity);
			
			//线下活动缩略图
            List<Attachment> attachments = attachmentService.findbyparms(CommenConstant.EL_THUMBNAIL, Long.parseLong(id));  
            if (attachments != null && attachments.size() > 0) {
                model.addAttribute("smallImage",attachments.get(0));
            }
			return "elearning/offlineactive/offline_addEdit";
		}
		
		
		//根据accountName来查找用户信息
		@RequestMapping("/findByAccountName")
		@ResponseBody
		public Map<String, Object> findByAccountName(String id,String accountName,String type,String isSign,Model model){
			int pageNo=1;
			int pageSize=5;
			Map<String, Object> map=offlineActivityService.findByIdFW(id, accountName,isSign, type, pageNo, pageSize);
			return map;
		}
		
		
		//人员限制
		@RequestMapping("/choosePerson")
		public ModelAndView selectCertificate(@RequestParam(name="accountName",required = false )String accountName,
				@RequestParam(name="mobile",required = false)String mobile,@RequestParam(name="personIds",required = false )String personIds){
			ModelAndView mv = new ModelAndView();
			List<Account> accountList = accountService.selectByAll(accountName, mobile);
			mv.addObject("accountList", accountList);
			mv.addObject("personIds", personIds);
			mv.setViewName("elearning/offlineactive/personnel_restrictions");
			return mv;
		}
		
	
}