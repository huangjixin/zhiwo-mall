package com.fulan.application.controller.iris;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fulan.api.information.domain.ActivityMemberMapping;
import com.fulan.api.information.service.ElNewsManageService;
import com.fulan.api.information.vo.NewsManageVo;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.vo.CompulsoryCplanManageVo;
import com.fulan.api.security.domain.Account;
import com.fulan.application.common.ReadExcel;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.file.SFTPUtil;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;


@Controller
public class NewsController {
	
	@Autowired
	private ElNewsManageService elNewsManageService;
	/**
	 * 列表查询
	 * @param name
	 * @param tagId
	 * @param state
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/news/newsList")
	public String listByPage(
			String title,
			String status,
			String type,
			String satrtTime,
			String endTime,
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
    	
    	PageInfo<NewsManageVo> page =elNewsManageService.ListNew(title, type, status, satrtTime, endTime,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
        model.addAttribute("page", page);
        model.addAttribute("title", title);
        model.addAttribute("status", status);
        model.addAttribute("type", type);
        model.addAttribute("satrtTime", satrtTime);
        model.addAttribute("endTime", endTime);
		return "iris/information/information_list";
		
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
	@GetMapping("/manage/news/listByPageAjax")
	@ResponseBody
	public Response<PageInfo<NewsManageVo>> listByPageAjax(
			String title,
			String status,
			String type,
			String satrtTime,
			String endTime,
			Model model,String pageNo,String pageSize){
		pageNo = StringUtil.isEmpty(pageNo)?"1":pageNo;
		pageSize =  StringUtil.isEmpty(pageNo)?"10":pageSize;
		PageInfo<NewsManageVo> pList =elNewsManageService.ListNew(title, type, status,satrtTime, endTime,Integer.parseInt(pageNo),Integer.parseInt(pageSize));
		Response<PageInfo<NewsManageVo>> response = new Response<>(Response.SUCCESS, null);		
		response.setData(pList);
		return response;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@PostMapping("/manage/news/deleteNew")
	@ResponseBody
	public Response<String> deleteNew(String id){
		return elNewsManageService.deleteNews(id);
		
	}
	/**
	 * 新增修改页面
	 * @return
	 */
	@GetMapping("/manage/news/edit")
	public String edit(Model model,String id){
		if(""!= id || null !=id){
			NewsManageVo nvo =elNewsManageService.selectOneNewsById(id);
			 model.addAttribute("nvo", nvo);
		}
		Account currentUser = SessionContextUtils.getCurrentUser();
		String companyId = currentUser.getCompanyId();
		model.addAttribute("companyId", companyId);
		return "iris/information/information_edit";
		
	}
	
	
	/**
	 * 发布  撤回
	 * @param id
	 * @param status
	 * @return
	 */
	@PostMapping("/manage/news/release")
	@ResponseBody
	public Response<String> release(String id ,String status){
		return elNewsManageService.updateStatus(id, status);
		
	}
	
	
	/**
	 * 解析人员附件并返回agentId列表
	 */
	@RequestMapping("/manage/news/resolveActivity")
	@ResponseBody
	public Response<List<String>> resolveActivity(HttpServletRequest request){
		//根据代理人姓名返回agentId
		
		Response<List<String>> response= new Response<List<String>>();
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
		        ArrayList<String> agentDetailList = ReadExcel.excelIn(inputStream,null);
				sftp.logout();
		        response.setData(agentDetailList);
		} catch (Exception e) {
			response.setCode(Response.ERROR);
			response.setMsg("导入失败，请重试");
		}
		return response;
	}
	
	
	
	/**
	 * 新增修改咨询
	 * @param model
	 * @param newsManageVo
	 * @param pathId
	 * @param FilePathId
	 * @return
	 */
	@PostMapping("/manage/news/saveNews")
    @ResponseBody
    public Response<String> saveNews(Model model , NewsManageVo newsManageVo,String pathId ,String FilePathId,String sendType){
		if("1".equals(sendType)){
			newsManageVo.setScheduleTime(new Date());
			newsManageVo.setStatus(2);
		}
		if(null !=newsManageVo.getId()){
			newsManageVo.setSendType(Integer.parseInt(sendType));
			newsManageVo.setModifyUser(SessionContextUtils.getCurrentUserId());
			return elNewsManageService.updateNews(newsManageVo, pathId, FilePathId);
		}else{
			newsManageVo.setSendType(Integer.parseInt(sendType));
			newsManageVo.setCreateUser(SessionContextUtils.getCurrentUserId());
			return elNewsManageService.insertNews(newsManageVo, pathId, FilePathId);
		}
    }
	
}
