package com.fulan.application.oa.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fulan.application.achievement.service.AchAgentClient;
import com.fulan.application.achievement.vo.CommonQueryRepsonse;
import com.fulan.application.achievement.vo.ErrorMessage;
import com.fulan.application.achievement.vo.QueryAgentHistoryIncomeResponse;
import com.fulan.application.oa.constant.ApplyAction;
import com.fulan.application.oa.domain.FwdOaApplyForm;
import com.fulan.application.oa.domain.FwdOaFormAttachment;
import com.fulan.application.oa.service.IApplyFormService;
import com.fulan.application.oa.service.IAttachmentService;
import com.fulan.application.oa.service.OaAgentClient;
import com.fulan.application.oa.service.WorkFlowService;
import com.fulan.application.oa.vo.ApprovalRecordVo;
import com.fulan.application.oa.vo.OAApplyFormVo;
import com.fulan.application.oa.vo.OAApplyFormVoParameter;
import com.fulan.application.oa.vo.OaAgentDto;
import com.fulan.application.oa.vo.OaReqParamAgentCodeDto;
import com.fulan.application.oa.vo.OaRespAgentGroupInfoDto;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.ApiParam;

/**
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/applyForm")
public class ApplyFormController {

	private static final Logger logger = LoggerFactory.getLogger(ApplyFormController.class);

	@Autowired
	private IApplyFormService applyFormService;

	@Autowired
	private WorkFlowService workFlowService;

	@Autowired
	private IAttachmentService attachmentService;
	
	@Autowired
	private OaAgentClient oaAgentClient;

	// 暂时写在D盘，后续应该配置成为注入值。
	@Value("${uploadPath}")
	private String uploadPath;

	/**
	 * @Description:待我审批
	 * @param agentCode
	 * @param pageSize
	 * @param curPage
	 * @return
	 */
	@GetMapping("/approval/pending")
	public Response<PageInfo<OAApplyFormVo>> getPendingApproval(@RequestParam(value = "agentCode") String agentCode,
			@RequestParam(value = "types",required = false) String[] types,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "curPage", defaultValue = "1") Integer curPage) {
		if (StringUtils.isBlank(agentCode)) {
			return new Response(Response.ERROR, "参数错误");
		}

		List<OAApplyFormVo> pendingApprovalList = null;
		try {
			pendingApprovalList = applyFormService.findPendingApproval(agentCode,types, pageSize, curPage);
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			return new Response(Response.ERROR, e.getMessage());
		}

		PageInfo<OAApplyFormVo> pageInfo = new PageInfo<OAApplyFormVo>();
		pageInfo.setPageNo(curPage);
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageRecords(pendingApprovalList.size());
		pageInfo.setRecords(pendingApprovalList);

		Response<PageInfo<OAApplyFormVo>> response = new Response<PageInfo<OAApplyFormVo>>(Response.SUCCESS,
				Response.SUCCESS_MESSAGE);
		response.setData(pageInfo);
		return response;
	}

	/**
	 * @Description:我已审批
	 * @param agentCode
	 * @param pageSize
	 * @param curPage
	 * @return
	 */
	@GetMapping("/approval/processed")
	public Response<PageInfo<OAApplyFormVo>> getProcessedApproval(@RequestParam(value = "agentCode") String agentCode,
			@RequestParam(value = "types",required = false) String[] types,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "curPage", defaultValue = "1") Integer curPage) {
		if (StringUtils.isBlank(agentCode)) {
			return new Response(Response.ERROR, "参数错误");
		}

		List<OAApplyFormVo> processedApprovalList = null;
		try {
			processedApprovalList = applyFormService.findProcessedApproval(agentCode,types, pageSize, curPage);
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			return new Response(Response.ERROR, e.getMessage());
		}

		PageInfo<OAApplyFormVo> pageInfo = new PageInfo<OAApplyFormVo>();
		pageInfo.setPageNo(curPage);
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageRecords(processedApprovalList.size());
		pageInfo.setRecords(processedApprovalList);

		Response<PageInfo<OAApplyFormVo>> response = new Response<PageInfo<OAApplyFormVo>>(Response.SUCCESS,
				Response.SUCCESS_MESSAGE);
		response.setData(pageInfo);
		return response;
	}

	/**
	 * @Description:处理审批
	 * @param agentCode
	 * @param actionCode
	 * @param formId
	 * @param comment
	 * @return
	 */
	@PostMapping("/handleApproval")
	public Response handleApproval(@RequestParam(value = "agentCode") String agentCode,
			@ApiParam(name = "action", value = "agree,reject", required = true) @RequestParam(value = "action") String actionCode,
			@RequestParam(value = "formId") Integer formId,
			@RequestParam(value = "comment", required = false) String comment,
			@RequestParam(value = "nextAssignee", required = false) String nextAssignee) {

		ApplyAction action = ApplyAction.getAction(actionCode);
		if (StringUtils.isBlank(agentCode) || formId == null || action == null) {
			return new Response(Response.ERROR, "参数错误");
		}
		try {
			if (!applyFormService.isApplyFormActive(formId)) {
				return new Response(Response.ERROR, "申请不存在或已处理");
			}
			if (!workFlowService.hasApprovalAccess(agentCode, formId)) {
				return new Response(Response.ERROR, "没有权限处理");
			}
			applyFormService.handleApproval(formId, action, agentCode, comment, nextAssignee);
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			return new Response(Response.ERROR, e.getMessage());
		}

		return new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
	}

	/**
	 * @Description:申请详情中的审批记录
	 * @param formId
	 * @return
	 */
	@GetMapping("/{formId}/approvalRecord")
	public Response<List<ApprovalRecordVo>> getApprovalRecord(@PathVariable("formId") Integer formId) {
		if (formId == null) {
			return new Response(Response.ERROR, "参数错误");
		}
		List<ApprovalRecordVo> recordList = null;
		try {
			recordList = workFlowService.findApprovalRecord(formId.toString());
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			return new Response(Response.ERROR, e.getMessage());
		}
		Response<List<ApprovalRecordVo>> response = new Response<List<ApprovalRecordVo>>(Response.SUCCESS,
				Response.SUCCESS_MESSAGE);
		response.setData(recordList);
		return response;
	}

	/**
	 * 保存数据
	 * 
	 * @param save
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	private int save(FwdOaApplyForm applyForm) {
		applyForm.setCreateDatetime(new Date());
		int result = applyFormService.save(applyForm);
		return result;
	}

	/**
	 * 删除数据
	 * 
	 * @param delete
	 * @return
	 */
	// @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	private int delete(int id) {
		int result = applyFormService.delete(id);
		return result;
	}

	/**
	 * 修改数据
	 * 
	 * @param update
	 * @return
	 */
	// @RequestMapping(value = "/update", method = RequestMethod.POST)
	private int update(FwdOaApplyForm applyForm) {
		int result = applyFormService.update(applyForm);
		return result;
	}

	/**
	 * 查询数据
	 * 
	 * @param selectAll
	 * @return
	 */
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	private List<FwdOaApplyForm> selectAll() {
		List<FwdOaApplyForm> fwdOaApplyFormList = applyFormService.selectAll();
		return fwdOaApplyFormList;
	}

	/**
	 * 查询数据
	 * 
	 * @param selectOAApplyFormVo
	 * @return
	 */
	@RequestMapping(value = "/selectOAApplyFormVo", method = RequestMethod.GET)
	private List<OAApplyFormVo> selectOAApplyFormVo(@RequestParam(name = "type", required = false) String type,
			@RequestParam(name = "status", required = false) Integer status,
			@RequestParam(name = "agentCode", required = true) String agentCode,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "size", required = false) Integer size,
			@RequestParam(name = "ids", required = false) Integer[] ids,
			@RequestParam(name = "types", required = false) String[] types) {
		Integer start = 0; 
		// 默认分页参数
		if (page == null) {
			page = 1;
		}
		
		if (size == null) {
			size = 10;
		}
		
		start  = (page-1) * size;
		
		OAApplyFormVoParameter oAApplyFormVoParameter = new OAApplyFormVoParameter();
		FwdOaApplyForm applyForm = new FwdOaApplyForm();
		applyForm.setStatus(status);
		applyForm.setAgentCode(agentCode);
		applyForm.setType(type);
		oAApplyFormVoParameter.setFwdOaApplyForm(applyForm);
		oAApplyFormVoParameter.setStart(start);
		oAApplyFormVoParameter.setSize(size);
		oAApplyFormVoParameter.setIds(ids);
		oAApplyFormVoParameter.setTypes(types);
		List<OAApplyFormVo> oAApplyFormVo = applyFormService.selectOAApplyFormVo(oAApplyFormVoParameter);
		return oAApplyFormVo;
	}

	/**
	 * 单个文件上传
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/upload")
	public String upload(@RequestParam(value = "agentCode", required = true) String agentCode,
			@RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes redirectAttributes, HttpServletRequest request,
			HttpServletResponse response) {

		String strDirPath = request.getSession().getServletContext().getRealPath("/");
		System.out.println(strDirPath);
		strDirPath = this.uploadPath + File.separator;
		// 把上传的文件写入到upload/images/{agentCode}/文件名 (先判断一下文件是否存在，不存在则先创建)

		// 新建一个附件对象，把相应信息例如path物理路径URL写进去外键等，保存进去数据库。

		// 处理上传文件
		if (file != null) {
			String oldFileName = file.getOriginalFilename();
			// 文件后缀
			String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
			// 获取文件提交路径(服务器)
			// 把上传的文件写入到upload/images/{agentCode}/文件名 (先判断一下文件是否存在，不存在则先创建)
			String path = strDirPath + "upload" + File.separator + "images" + File.separator + agentCode;
			File filePath = new File(path);
			if (!filePath.exists()) {
				boolean establish = filePath.mkdirs();
				if (!establish) {
					System.out.println("创建失败");
				}
			}
			// 文件重命名
			// 时间(毫秒数)+随机数+suffix
			// 1970-1-1~今天 System.currentTimeMillis();
			String newFileName = System.currentTimeMillis() + new Random().nextInt(1000000) + suffix;
			// 上传文件 java.io.File
			File targetFile = new File(filePath, newFileName);
			try {
				file.transferTo(targetFile);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			// 新建一个附件对象，把相应信息例如path物理路径URL写进去外键等，保存进去数据库，
			FwdOaFormAttachment oaFormAttachment = new FwdOaFormAttachment();
			String url = "upload/images/" + agentCode + "/" + newFileName;
			oaFormAttachment.setPath(targetFile.toString());
			oaFormAttachment.setUrl(url);
			int count = attachmentService.save(oaFormAttachment);
			if (count < 1) {
				System.out.println("oaFormAttachment失败");
			}
		} else {
			System.out.println("applyForm失败");
		}
		// 启动申请流程。
		return "success";

	}

	/**
	 * 单个文件上传
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveSingleForm")
	public ErrorMessage saveSingleForm(@Validated FwdOaApplyForm applyForm, BindingResult result,
			@RequestParam(value = "file", required = false) MultipartFile file, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		applyForm.setCreateDatetime(new Date());
		ErrorMessage errorMessage = new ErrorMessage();
		// applyForm.setId(1000);
//		if (result.hasErrors()) {
//			errorMessage.setState("0");
//			errorMessage.setErrorMessage("表单有必填字段未填！");
//			return errorMessage;
//		}
		String strDirPath = this.uploadPath + File.separator;
		try {
			applyFormService.saveForm(strDirPath, file, applyForm);
			errorMessage.setState("1");
		} catch (Exception e) {
			errorMessage.setState("0");
			errorMessage.setErrorMessage("error:" + e.getMessage());
			e.printStackTrace();
		}

		return errorMessage;
	}

	/**
	 * 多个文件上传
	 * 
	 * @param file
	 * @param redirectAttributes
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveMultipleForm")
	public Response saveMultipleForm(@Validated FwdOaApplyForm applyForm, BindingResult result,
			@RequestParam("files") CommonsMultipartFile[] files, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		applyForm.setCreateDatetime(new Date());
		ErrorMessage errorMessage = new ErrorMessage();
		// applyForm.setId(1000);
		if (result.hasErrors()) {
			Response response = new Response(Response.ERROR, "表单有必填字段未填！");
			return response;
		}
		
		String strDirPath = this.uploadPath + File.separator;
		try {
			applyFormService.saveMultipleForm(strDirPath, files, applyForm);
		} catch (Exception e) {
			Response response = new Response(Response.ERROR, "保存出错！");
			e.printStackTrace();
			return response;
		}
		
		// 启动申请流程。
		return new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
	}

	/**
	 * 多个文件上传 基于base64
	 *
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/saveMultipleFormBase64")
	public Response saveMultipleFormBase64(@RequestBody FwdOaApplyForm applyForm) {
		if (applyForm != null && StringUtils.isBlank(applyForm.getAgentCode())) {
			Response response = new Response(Response.ERROR, "表单有必填字段未填！");
			return response;
		}

		applyForm.setCreateDatetime(new Date());
		String strDirPath = this.uploadPath + File.separator;
		try {
			applyFormService.saveMultipleFormBase64(strDirPath, applyForm.getFiles(), applyForm);
		} catch (Exception e) {
			Response response = new Response(Response.ERROR, "保存出错！");
			e.printStackTrace();
			return response;
		}

		// 启动申请流程。
		return new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
	}

	/**
	 * 根据ID查询数据
	 * 
	 * @param selectById
	 * @return
	 */
	@RequestMapping(value = "/selectById/{id}", method = RequestMethod.GET)
	private FwdOaApplyForm selectById(@PathVariable(value = "id") Integer id) {
		FwdOaApplyForm oaApplyForm = applyFormService.selectById(id);
		return oaApplyForm;
	}

	/**
	 * 读取bpmn文件
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/getTaskInfosByName/{name}", method = RequestMethod.GET)
	private Response getTaskInfosByName(@RequestParam(value = "name", required = true) String name) {
		List<Map<String, String>> list = null;
		try {
			list = applyFormService.getTaskInfosByName(name);
		} catch (Exception e) {
			Response response = new Response(Response.ERROR, e.getMessage());
//			response.setData();
//			response.setMsg(e.getMessage());
			e.printStackTrace();
			return response;
		}
		Response response = new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
		response.setData(list);
		return response;
	}

	
	/**
	 * 根据传入进来的文件名，找到类路径下的模型文件，再遍历模型文件，
	 * 如果发现传进来的UserTask信息有修改，则重新写入文件。
	 * @param name
	 * @param nodeMap
	 * @return
	 */
	@RequestMapping(value = "/updateTaskInfosByName", method = RequestMethod.POST)
	@ResponseBody
	private Response updateTaskInfosByName(@RequestParam(value = "name", required = true) String name,
			@RequestBody List<Map<String, String>> nodeMap) {
		try {
			applyFormService.updateTaskInfosByName(name, nodeMap);
		} catch (Exception e) {
			Response response = new Response(Response.ERROR, e.getMessage());
			e.printStackTrace();
			return response;
		}
		
		Response response = new Response(Response.SUCCESS, "保存成功");
		return response;
	}
	

	/**
	 * 返回晉升路綫
	 * 
	 * @param selectById
	 * @return
	 */
	@RequestMapping(value = "/selectPromotion", method = RequestMethod.GET)
	private List<String> selectPromotion(@RequestParam(value = "agentCode") String agentCode ) {
		List<String> promotions = new ArrayList<String>();
		promotions.add("AM");
		promotions.add("SD");
		return promotions;
	}
	
	/**
	 * 返回復效人員接口
	 * 
	 * @param selectById
	 * @return
	 */
	@RequestMapping(value = "/selectComplexEffect", method = RequestMethod.GET)
	private Response<List<OaAgentDto>> selectComplexEffect(@RequestParam(value = "agentCode") String agentCode ) {
		List<OaAgentDto> promotions = new ArrayList<OaAgentDto>();
		Response<List<OaAgentDto>> response = null;
		try {
			OaReqParamAgentCodeDto oaReqParamAgentCodeDto = new OaReqParamAgentCodeDto(agentCode);
			CommonQueryRepsonse<OaRespAgentGroupInfoDto> resp = oaAgentClient.queryAgentGroupInfo(oaReqParamAgentCodeDto);
			OaRespAgentGroupInfoDto agentGroupInfo = resp.getResponse();
			if(agentGroupInfo!=null) {
				for (OaAgentDto dto: agentGroupInfo.getGroupList()) {
					if("Y".equals(dto.getSubIsAtWork())) {
						promotions.add(dto);
					}
				}
				response.setData(promotions);
			}
			
			String statusCode = resp.getStatus().getStatusCode();
			String statusMessage = resp.getStatus().getStatusMessage();
			if("01".equals(statusCode))
				response = new Response<List<OaAgentDto>>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
			else {
				response = new Response<List<OaAgentDto>>(Response.ERROR,statusMessage);
			}
			
		} catch (Exception e) {
			logger.error("Unknow Error", e);
			return new Response<List<OaAgentDto>>(Response.ERROR, e.getMessage());
		}
		return response;
	}
}
