package com.fulan.application.controller.erecruitment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.paper.domain.er.Paper;
import com.fulan.api.paper.service.PaperManageService;
import com.fulan.api.paper.vo.PaperItemVo;
import com.fulan.api.paper.vo.PaperManageVo;
import com.fulan.api.paper.vo.PaperVo;
import com.fulan.api.plan.vo.CompulsoryCplanVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.system.domain.Organization;
import com.fulan.api.system.manage.ManageOrganizationService;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
public class PaperController {
    @Autowired   
	private PaperManageService paperManageService;
    
	@Autowired
	private ManageOrganizationService manageOrganizationService;
    
    @GetMapping("/manage/erecruitment/paperList")
	public String listByPage(Model model , String keyWord,String orgId,String pageNo,String pageSize){
    	Account account = SessionContextUtils.getCurrentUser();
    	if(""==pageNo || null ==pageNo){
    		pageNo="1";
    	}
    	if(""==pageSize || null ==pageSize){
    		pageSize="10";
    	}
    	if(""==keyWord || null ==keyWord){
    		keyWord="";
    	}
    	if(""==orgId || null ==orgId){
    		orgId="";
    	}
    	if(!"B0311".equals(account.getCompanyId())){
    		orgId = account.getCompanyId();
    	}
    	PageInfo<PaperManageVo>  page =  paperManageService.paperManageSerchOther(keyWord,orgId, Integer.parseInt(pageNo),Integer.parseInt(pageSize));
    	PageInfo<PaperManageVo>  orgPage =  paperManageService.paperManageSerchOther(keyWord,"B0311", Integer.parseInt(pageNo),Integer.parseInt(pageSize));
    	model.addAttribute("page", page);
    	model.addAttribute("keyWord", keyWord);
    	model.addAttribute("orgId", orgId);
    	model.addAttribute("userInfo", account);
    	model.addAttribute("orgPage", orgPage.getRecords());
    	//获取组织机构
		List<Organization> orgList = manageOrganizationService.getOrganizationList();
		model.addAttribute("orgList",orgList);
    	return "paper/paper";
    }
    
    
    /**
     * 分页获取内容
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping("/manage/erecruitment/listByPageAjax")
	@ResponseBody
    public Response<PageInfo<PaperManageVo>> listByPageAjax(String keyWord,String orgId,String pageNo,String pageSize){
    	pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
		PageInfo<PaperManageVo>  paperManageVoList =  paperManageService.paperManageSerchOther(keyWord,orgId, Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		Response<PageInfo<PaperManageVo>> response = new Response<>(Response.SUCCESS, null);
		response.setData(paperManageVoList);
		return response;
    	
    }
    
    
    @GetMapping("/manage/erecruitment/check")
	public String check(Model model , String paperId){
    	Paper searchPaper = new Paper();
    	searchPaper.setId(Long.valueOf(paperId));
    	PaperVo pvo = paperManageService.paperCheck(searchPaper);
    	List<PaperItemVo> paperItemVoList =  pvo.getPaperItemVo();
		List<PaperItemVo> paperItemVoJcList = new ArrayList<>();
		List<PaperItemVo> paperItemVoFjList = new ArrayList<>();
		for(PaperItemVo paperItemVo : paperItemVoList){
			if("1".equals(paperItemVo.getPaperItem().getPaperType().toString())){
				paperItemVoJcList.add(paperItemVo);
			}else if("2".equals(paperItemVo.getPaperItem().getPaperType().toString())){
				paperItemVoFjList.add(paperItemVo);
			}
		}
		model.addAttribute("paperItemVoJcList", paperItemVoJcList);
		model.addAttribute("paperItemVoFjList", paperItemVoFjList);
    	model.addAttribute("pvo", pvo);
    	//获取组织机构
		List<Organization> orgList = manageOrganizationService.getOrganizationList();
		model.addAttribute("orgList",orgList);
		return "paper/paper_detail";
    }
    /*
     * @param paperType 1:初审 2:甄选 3:决定
     * */
    @GetMapping("/manage/erecruitment/edit")
	public String edit(Model model , Paper paper){
    	Account account = SessionContextUtils.getCurrentUser();
    	List<PaperItemVo> paperItemVoJcList = new ArrayList<>();
		List<PaperItemVo> paperItemVoFjList = new ArrayList<>();
    	if(paper.getId()!=null&&!StringUtil.isEmpty(String.valueOf(paper.getId()))){
    		Paper searchPaper = new Paper();
    		searchPaper.setId(paper.getId());
    		PaperVo pvo = paperManageService.paperCheck(searchPaper);
    		List<PaperItemVo> paperItemVoList =  pvo.getPaperItemVo();
    		for(PaperItemVo paperItemVo : paperItemVoList){
    			if("1".equals(paperItemVo.getPaperItem().getPaperType().toString())){
    				//paperItemVoJcList.add(paperItemVo);
    			}else if("2".equals(paperItemVo.getPaperItem().getPaperType().toString())){
    				paperItemVoFjList.add(paperItemVo);
    			}
    		}
    		if(null != paperItemVoJcList){
    			int pLt = paperItemVoJcList.size();
    			model.addAttribute("pLt", pLt);
    		}
    		model.addAttribute("paperItemVoFjList", paperItemVoFjList);
        	model.addAttribute("pvo", pvo);
    	}
    	Paper searchZgsPaper = new Paper();
    	searchZgsPaper.setPaperType(paper.getPaperType());
    	PaperVo zgsPvo = paperManageService.paperCheck(searchZgsPaper);
    	List<PaperItemVo> paperZgsItemVoList =  zgsPvo.getPaperItemVo();
    	if(paperZgsItemVoList.size()>0){
    		for(PaperItemVo paperItemVo : paperZgsItemVoList){
    			if("1".equals(paperItemVo.getPaperItem().getPaperType().toString())){
    				paperItemVoJcList.add(paperItemVo);
    			}
    		}
    	}
    	model.addAttribute("paperItemVoJcList", paperItemVoJcList);
    	//获取组织机构
		List<Organization> orgList = manageOrganizationService.getOrganizationList();
		model.addAttribute("userInfo", account);
		model.addAttribute("orgList",orgList);
		model.addAttribute("paperType",paper.getPaperType());
		return "paper/paper_edit";
    }
    
    @PostMapping("/manage/erecruitment/savePaper")
    @ResponseBody
    public String savePa(Model model ,PaperVo paperVo){
    	String str = paperManageService.savePaper( paperVo);
    	return str;
    }
    
    @PostMapping("/manage/erecruitment/deleteVo")
    @ResponseBody
    public boolean dVo(String id){
		return paperManageService.deletePaperVo(id);
    	
    }
}
