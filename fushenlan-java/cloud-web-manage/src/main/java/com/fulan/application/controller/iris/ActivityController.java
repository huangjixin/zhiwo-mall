package com.fulan.application.controller.iris;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fulan.api.calendar.domain.AttendanceObjects;
import com.fulan.api.information.domain.ActivityMemberMapping;
import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.api.information.service.ActivityService;
import com.fulan.api.information.vo.ActivityVo;
import com.fulan.api.information.vo.NewsManageVo;
import com.fulan.api.security.domain.Account;
import com.fulan.application.common.OutExcel;
import com.fulan.application.common.ReadExcel;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.file.SFTPUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;

@Controller
public class ActivityController {
	
	
	@Autowired
	private ActivityService  activityService;
	 
	
	/**
	 * 首页
	 * @param title
	 * @param status
	 * @param type
	 * @param actStartDate
	 * @param actEndDate
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/activity/activityList")
	public String listByPage(
			String title,
			String status,
			String type,
			String actStartDate,
			String actEndDate,
			Model model,String pageNo,String pageSize){
		if(""==pageNo || null ==pageNo){
    		pageNo="1";
    	}
    	if(""==pageSize || null ==pageSize){
    		pageSize="10";
    	}
    	if(""==title || null ==title){
    		title="";
    	}
    	if(""==status || null ==status){
    		status="";
    	}
    	if(""==type || null ==type){
    		type="";
    	}
    	PageInfo<ActivityVo> page =activityService.listactivity(title, type, status, actStartDate, actEndDate, Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        model.addAttribute("page", page);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("type", type);
        model.addAttribute("actStartDate", actStartDate);
        model.addAttribute("actEndDate", actEndDate);
		return "iris/activity/activity_list";
		
	}
	
	
	/**
	 * 分页
	 * @param title
	 * @param status
	 * @param type
	 * @param actStartDate
	 * @param actEndDate
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/activity/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<ActivityVo>> listByPageAjax(
			String title,
			String status,
			String type,
			String actStartDate,
			String actEndDate,
			Model model,String pageNo,String pageSize){
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageSize)?"10":pageSize;
		PageInfo<ActivityVo> pList =activityService.listactivity(title, type, status, actStartDate, actEndDate, Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		Response<PageInfo<ActivityVo>> response = new Response<>(Response.SUCCESS, null);		
		response.setData(pList);
		return response;
	}
	
	
	/**
	 * 新增修改页面
	 * @return
	 */
	@GetMapping("/manage/activity/edit")
	public String edit(Model model,String id,String type,String signUpSet){
		if(""!= id && null !=id){
			ActivityVo nvo = activityService.selectOneActivityById(id);
			 model.addAttribute("nvo", nvo);
			 model.addAttribute("id",nvo.getActivity().getId());
			 model.addAttribute("signUpSet", signUpSet);
		}
		if("2".equals(type)){
			PageInfo<ApplyDetail> activityApplyList = activityApplyList(null, null, null,id,signUpSet);
			 model.addAttribute("activityApplyList", activityApplyList);
			return "iris/activity/activity_view";
		}
		Account currentUser = SessionContextUtils.getCurrentUser();
		String companyId = currentUser.getCompanyId();
		model.addAttribute("companyId", companyId);
		return "iris/activity/activity_edit";
		
	}
	
	/**
	 * 导出Excel
	 * @return
	 * @throws IOException 
	 */
	@GetMapping("/manage/activity/exportExcel")
	@ResponseBody
	public Response<String> exportExcel(Model model,String id,String signUpSet,HttpServletResponse response) {
			response.setContentType("application/binary;charset=UTF-8");
			Response<String> resp = new Response<>(Response.SUCCESS, "导出成功");
			PageInfo<ApplyDetail> activityApplyList = activityApplyList(null, null, null,id,signUpSet);
			try{
				ServletOutputStream out=response.getOutputStream();
				String fileName=new String(("报名人员 "+ new SimpleDateFormat("yyyy-MM-dd").format(new Date())).getBytes(),"UTF-8");
				response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");
				OutExcel.excelOut(activityApplyList.getRecords(),out);
			}catch(Exception e){
				resp.setCode(Response.ERROR);
				resp.setMsg("导出失败");
			}
			
			return resp;
		
	}
	
	/**
	 * 分页查询报名情况
	 * 
	 */
	 PageInfo<ApplyDetail> activityApplyList(
			String acounyName,
			String pageNo,
			String pageSize,
			String id,
			String signUpSet){
		if(""==pageNo || null ==pageNo){
    		pageNo="1";
    	}
    	if(""==pageSize || null ==pageSize){
    		pageSize="10";
    	}
    	
    	PageInfo<ApplyDetail> page =activityService.activityApplyList(acounyName, Integer.parseInt(pageNo),Integer.parseInt(pageSize),id,signUpSet);
		return page;
		
	}
	
	 /**
		 * 分页
		 * @param title
		 * @param status
		 * @param type
		 * @param actStartDate
		 * @param actEndDate
		 * @param model
		 * @param pageNo
		 * @param pageSize
		 * @return
		 */
		@GetMapping("/manage/activity/activityApplyListAjax")
		@ResponseBody 
		public Response<PageInfo<ApplyDetail>> activityApplyListAjax(
				String acounyName,String pageNo,String pageSize,String id,String signUpSet){
			pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
			pageSize =  StringUtil.isEmpty(pageSize)?"10":pageSize;
			PageInfo<ApplyDetail> page =activityService.activityApplyList(acounyName, Integer.parseInt(pageNo),Integer.parseInt(pageSize),id,signUpSet);
			Response<PageInfo<ApplyDetail>> response = new Response<>(Response.SUCCESS, null);		
			response.setData(page);
			return response;
		}
	
	
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@PostMapping("/manage/activity/deleteAct")
	@ResponseBody
	public Response<String> deleteNew(String id){
		return activityService.deleteActivity(id);
		
	}
	
	
	
	/**
	 * 发布  撤回
	 * @param id
	 * @param status
	 * @return
	 */
	@PostMapping("/manage/activity/release")
	@ResponseBody
	public Response<String> release(String id ,String status){
		return activityService.updateStatus(id, status);
		
	}
	
	
	/**
	 * 解析人员附件并返回agentId列表
	 */
	@RequestMapping("/manage/activity/resolveActivity")
	@ResponseBody
	public Response<List<ActivityMemberMapping>> resolveActivity(HttpServletRequest request){
		//根据代理人姓名返回agentId
		
		Response<List<ActivityMemberMapping>> response= new Response<List<ActivityMemberMapping>>();
		String url = request.getParameter("url");
		try {
			 MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		        //获取上传文件   name值 必须为"fileName"：
		        MultipartFile file = mRequest.getFile("fileName");
		        String name=file.getOriginalFilename();
		        if(!name.substring(name.indexOf(".")+1, name.length()).equals("xls")&&!name.substring(name.indexOf(".")+1, name.length()).equals("xlsx")){
		        	response.setCode(Response.ERROR);
		        	response.setMsg("请选择xls或xlsx格式文件");
		        	return response;
		        }
		        //从服务器读取文件流
		        SFTPUtil sftp = new SFTPUtil(CommenConstant.ftpuploadusername, CommenConstant.ftpuploadpassword, CommenConstant.ftpuploadhost, CommenConstant.ftpuploadport);
		        sftp.login();
		        InputStream inputStream = sftp.get("/home/apps/upload/"+url);
		        //将文件流转换为list 代理人信息格式为(agentCode companyId orgId)
		        ArrayList<String> agentDetailList = ReadExcel.excelIn(inputStream,5);
		        ArrayList<ActivityMemberMapping> agentList = new  ArrayList<ActivityMemberMapping>();
				//将代理人信息转换为AttendanceObjects对象
				if(agentDetailList!=null&&agentDetailList.size()!=0){
					for(int i = 1;i < (agentDetailList.size()/6);i++){
						//根据提供信息查询该人的id和orgId
						ActivityMemberMapping ao = new ActivityMemberMapping();
						ao.setAcountName(agentDetailList.get(i*6));
						ao.setAgentCode(agentDetailList.get(i*6+1));
						ao.setLevelName(agentDetailList.get(i*6+2));
						ao.setCompanyName(agentDetailList.get(i*6+3));
						ao.setBranchCompanyName(agentDetailList.get(i*6+4));
						ao.setOrgName(agentDetailList.get(i*6+5));
						agentList.add(ao);
					}
				}
				//流获取完毕后再登出
				sftp.logout();
		        response.setData(agentList);
		} catch (Exception e) {
			response.setCode(Response.ERROR);
			response.setMsg("导入失败，请重试");
		}
		return response;
	}
	
	
	
	/**
	 * 新增修改活动
	 * @param model
	 * @param newsManageVo
	 * @param pathId
	 * @param FilePathId
	 * @return
	 */
	@PostMapping("/manage/activity/saveActivity")
    @ResponseBody
    public Response<String> saveActivity(Model model , ActivityVo activityVo,String pathId ,String FilePathId,String sendType){
		if("1".equals(sendType)){
			activityVo.setTiming(new Date());
			activityVo.setStatus(2);
		}if("3".equals(sendType)){
			activityVo.setStatus(0);
		}
		if(null !=activityVo.getId()){
			activityVo.setSendType(Integer.parseInt(sendType));
			activityVo.setCreateUser(SessionContextUtils.getCurrentUserId());
			return activityService.updateActivity(activityVo, pathId, FilePathId);
		}else{
			activityVo.setSendType(Integer.parseInt(sendType));
			activityVo.setModifyUser(SessionContextUtils.getCurrentUserId());
			return activityService.insertActivity(activityVo, pathId, FilePathId);
		}
    }
	
	
}
