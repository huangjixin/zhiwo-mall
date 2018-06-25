package com.fulan.application.controller.elearning;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fulan.api.course.domain.Course;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.service.CompulsoryCplanService;
import com.fulan.api.plan.vo.CompulsoryCplanManageVo;
import com.fulan.api.plan.vo.CompulsoryCplanVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.domain.Organization;
import com.fulan.api.system.manage.ManageOrganizationService;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.api.system.service.DictionaryService;
import com.fulan.application.common.CommenConstant;
import com.fulan.application.common.ExcleUtil;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.file.SFTPUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
public class CompulsoryCplanController {
     
	@Autowired
	private CompulsoryCplanService compulsoryCplanService;
	
	@Autowired
    private ManageOrganizationService manageOrganizationService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@Autowired
	CommenConstant commenConstant;
	
	
	
	
	@GetMapping("/manage/elearning/compulsoryCplanList")
	public String listByPage(
			String name,
			String tagId,
			String state,
			Model model,String pageNo,String pageSize){
		if(""==pageNo || null ==pageNo){
    		pageNo="1";
    	}
    	if(""==pageSize || null ==pageSize){
    		pageSize="10";
    	}
    	if(""==name || null ==name){
    		name="";
    	}
    	if(""==tagId || null ==tagId){
    		tagId="";
    	}
    	if(""==state || null ==state){
    		state="";
    	}
        PageInfo<CompulsoryCplanVo>  page = compulsoryCplanService.compulsoryCplanManageSerch(name, tagId, state,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        model.addAttribute("page", page);
        model.addAttribute("name", name);
        model.addAttribute("tagId", tagId);
        model.addAttribute("state", state);
		return "elearning/compulsoryCplan/compulsoryCplan";
		
	}
	/**
	 * 获取分页内容
	 * @param name
	 * @param tagId
	 * @param state
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/elearning/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<CompulsoryCplanVo>> listByPageAjax(
			String name,
			String tagId,
			String state,
			String pageNo,String pageSize){
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
		PageInfo<CompulsoryCplanVo>  compulsoryCplanVoList = compulsoryCplanService.compulsoryCplanManageSerch(name, tagId, state,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		Response<PageInfo<CompulsoryCplanVo>> response = new Response<>(Response.SUCCESS, null);		
		response.setData(compulsoryCplanVoList);
		return response;
		
	}
	
	
	
	@PostMapping("/manage/elearning/deletetrs")
    @ResponseBody
    public String dVo(String ids){
		return compulsoryCplanService.deleteCompulsoryCplan(ids);
    }
	
	@PostMapping("/manage/elearning/disable")
    @ResponseBody
    public String disAbleCompulsoryCplan(String ids){
		return compulsoryCplanService.disAbleCompulsoryCplan(ids);
    }
	@PostMapping("/manage/elearning/enable")
    @ResponseBody
    public String enAbleCompulsoryCplan(String ids){
		return compulsoryCplanService.enAbleCompulsoryCplan(ids);
    }
	@GetMapping("/manage/elearning/edit")
	public String edit(Model model,String id){
		if(!StringUtil.isEmpty(id)){
			CompulsoryCplanManageVo ccvo = compulsoryCplanService.compulsoryCplanCheck(id);
			model.addAttribute("ccvo", ccvo);
			List<CourseManageVo> cpList = compulsoryCplanService.selectByPlanId(id);
			String str = compulsoryCplanService.selectMaxByPlanId(id);
			model.addAttribute("str", str);
			model.addAttribute("cpList", cpList);
			List<Attachment> aList = attachmentService.selectByHostId(id);
			if(aList.size()>0){
				model.addAttribute("at", aList.get(0));
			}
			/*List<CourseManageVo> cVTwoList = compulsoryCplanService.selectByPlanOtherId(id);
			model.addAttribute("cVTwoList", cVTwoList);
			int lg = cpList.size();
			model.addAttribute("lg", lg);*/
			
		}
		List<Dictionary>  dList= dictionaryService.selcetChildListDictionary("compulsoryCplan_index");
		model.addAttribute("dList", dList);
		return "elearning/compulsoryCplan/compulsoryCplan_edit";
		
	}
	
	@PostMapping("/manage/elearning/saveCompulsoryCplan")
    @ResponseBody
    public String saveCompulsoryCplanO(Model model ,CompulsoryCplanManageVo CompulsoryCplanManageVo,String fileId ,String fOtherId){
		CompulsoryCplan compulsoryCplan = CompulsoryCplanManageVo.getCompulsoryCplan();
		compulsoryCplan.setCreateUser(SessionContextUtils.getCurrentUserId());
		CompulsoryCplanManageVo.setCompulsoryCplan(compulsoryCplan);
		if("undefined".equals(fileId)){
			fileId="";
    	}
    	String str = compulsoryCplanService.saveCompulsoryCplanManageVo(CompulsoryCplanManageVo,fileId,fOtherId);
    	return str;
    }
	
	@GetMapping("/manage/elearning/selectOne")
	public ModelAndView chooseOther(String type,String scId){
		ModelAndView mv = new ModelAndView();
		List<Course> studyPlanList = compulsoryCplanService.selectByElspId(type);
		mv.addObject("studyPlanList", studyPlanList);
		mv.addObject("scId", scId);
		mv.setViewName("elearning/compulsoryCplan/learnPlan_manage");
		return mv ;
	}
	
	@GetMapping("/manage/elearning/selectLineOne")
	public ModelAndView selectLineOne(String type,String scId){
		ModelAndView mv = new ModelAndView();
		List<Course> studyPlanList = compulsoryCplanService.selectByElspId(type);
		mv.addObject("studyPlanList", studyPlanList);
		mv.addObject("scId", scId);
		mv.setViewName("elearning/compulsoryCplan/learnPlan_NotInline");
		return mv ;
	}
	
	@GetMapping("/manage/elearning/selectPaper")
	public ModelAndView selectByAllList(String scId){
		ModelAndView mv = new ModelAndView();
		List<Paper> paperList = compulsoryCplanService.selectByAllPaperList();
		mv.addObject("paperList", paperList);
		mv.addObject("scId", scId);
		mv.setViewName("elearning/compulsoryCplan/paper");
		return mv ;
	}
	
	@GetMapping("/manage/elearning/choosePerson")
	public ModelAndView selectCertificate(@RequestParam(name="accountName",required = false )String accountName,
			                              @RequestParam(name="mobile",required = false)String mobile,
			                              @RequestParam(name="personIds",required = false )String personIds,
			                              @RequestParam(name="hostId",required = false )String hostId){
		ModelAndView mv = new ModelAndView();
		List<Account> accountList = accountService.selectByAll(accountName, mobile);
		
		mv.addObject("accountList", accountList);
		mv.addObject("personIds", personIds);
		mv.setViewName("elearning/compulsoryCplan/personnel_restrictions");
		return mv;
	}
	
	@RequestMapping("/manage/elearning/getAllOrganizationList")
	@ResponseBody
    public List<Organization> getOrganization(Model model){
		List<Organization> list=manageOrganizationService.getOrganizationList();
    	return  list;
    }
	
	
	
	
	@RequestMapping(value ="/manage/elearning/getOrganizationListByParentId", method = RequestMethod.POST)
	@ResponseBody
    public Response<List<Account>> getOrganizationListByParentId(Model model,HttpServletRequest request){
    	String companyId = request.getParameter("id");
    	Response<List<Account>> res = new Response<List<Account>>();
    	List<Account> accountList = accountService.selectByCompanyId(companyId);
    	res.setData(accountList);
    	return  res;
    }
	
	
	
	@RequestMapping(value ="/manage/elearning/choosePersonOther", method = RequestMethod.POST)
	@ResponseBody
    public Response<List<Account>> getAccountList(@RequestParam(name="accountName",required = false )String accountName,
			@RequestParam(name="mobile",required = false)String mobile){
    	Response<List<Account>> res = new Response<List<Account>>();
    	List<Account> accountList = accountService.selectByAll(accountName, mobile);
    	res.setData(accountList);
    	return res;
    }
	
	
	/**
	 * 导入account数据
	 * @throws IOException 
	 */
	@RequestMapping(value ="/manage/elearning/upLode", method = RequestMethod.POST)
	@ResponseBody
    public Response<List<Account>> upLodeSms(HttpServletRequest request){
			String url = request.getParameter("url");
		Response<List<Account>> response= new Response<List<Account>>();
		try {
			 MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		        //获取上传文件   name值 必须为"fileName"：
		        MultipartFile file = mRequest.getFile("fileName");
		        String name=file.getOriginalFilename();
		        if(!name.substring(name.indexOf(".")+1, name.length()).equals("xls")){
		        	response.setCode(Response.ERROR);
		        	response.setMsg("请选择xls格式文件");
		        	return response;
		        }
		        //这里放上传附件的路径
		        
		        
		        SFTPUtil sftp = new SFTPUtil(commenConstant.getFtpuploadusername(), commenConstant.getFtpuploadpassword(), commenConstant.getFtpuploadhost(), commenConstant.getFtpuploadport());
		        sftp.login();
				String[] dataList = new String[]{"id","accountName","mobile","email"};//需要传的字段与实体类对应上
				Map resultMap = ExcleUtil.getList(sftp.get("/home/apps/upload/"+url), dataList, Account.class);
				List<Account> accountList = (List<Account>)resultMap.get("resultBody");
				sftp.logout();
		        response.setData(accountList);
		} catch (Exception e) {
			response.setCode(Response.ERROR);
			response.setMsg("导入失败，请重试");
		}
		
		return response;
	}
	
	private void deleteFile(File file){
	     if(file.isDirectory()){
	          File[] files = file.listFiles();
	          for(int i=0; i<files.length; i++){
	               deleteFile(files[i]);
	          }
	      }
	     file.delete();
	}
	
	
	public static void main(String[] args){
		/*SFTPUtil sftp = new SFTPUtil(commenConstant.getFtpuploadusername(), commenConstant.getFtpuploadpassword(), commenConstant.getFtpuploadhost(), commenConstant.getFtpuploadport());
        sftp.login();
		String[] dataList = new String[]{"id","accountName","mobile","email"};//需要传的字段与实体类对应上
		Map resultMap = ExcleUtil.getList(sftp.get("/home/apps/upload/2018-14/981084589449543680.xls"), dataList, Account.class);
		List<Account> accountList = (List<Account>)resultMap.get("resultBody");
		System.out.println(accountList.size());
		sftp.logout();*/
			
			
	}
	
	/*@GetMapping("/manage/elearning/certificate")
	public ModelAndView selectCertificate(){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("elearning/compulsoryCplan/certificate");
		return mv;
	}*/
}
