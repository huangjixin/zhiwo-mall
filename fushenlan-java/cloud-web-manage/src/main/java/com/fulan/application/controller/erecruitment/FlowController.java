package com.fulan.application.controller.erecruitment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.flow.domain.Flow;
import com.fulan.api.flow.domain.FlowItem;
import com.fulan.api.flow.service.FlowActionService;
import com.fulan.api.flow.service.FlowItemService;
import com.fulan.api.flow.service.FlowService;
import com.fulan.api.flow.vo.FlowAcceptVo;
import com.fulan.api.flow.vo.FlowActionVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.domain.Organization;
import com.fulan.api.system.manage.ManageOrganizationService;
import com.fulan.api.system.service.DictionaryService;
import com.fulan.application.shrio.SessionContextUtils;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.util.JsonMsgBean;

@Controller
@RequestMapping("/manage/flow")
public class FlowController {
    
	@Autowired
	private FlowItemService flowItemService;
	
	@Autowired
	private FlowService flowService;
	
	@Autowired
	private FlowActionService flowActionService;
	
	@Autowired
	private ManageOrganizationService manageOrganizationService;
	
	@Autowired
	private DictionaryService dictionaryService;
	
	@GetMapping("/list")
	public String list(Model model,FlowItem flowItem,PageInfo<FlowItem> page){
		Response<PageInfo<FlowItem>> flowList = flowItemService.flowItemList(flowItem, page.getPageNo(), page.getPageSize(), page.getPageSortFiled(), page.getPageSortType());
		model.addAttribute("flowList", flowList);
		model.addAttribute("flowItem", flowItem);
		return "erecruitment/flow/flowItemManage";
	}
	
	@GetMapping("/addFlowItem")
	public String addFlowItem(Model model,String flowItemId,String operation){
		Response<FlowItem> flowItem = null;
		if(flowItemId!=null){
			flowItem =  flowItemService.getFlowItem(Long.valueOf(flowItemId));
		}
		model.addAttribute("flowItem", flowItem);
		model.addAttribute("operation", operation);
		return "erecruitment/flow/addFlowItem";
	}
	
	@GetMapping("/saveOrUpdateFlowItem")
	@ResponseBody
	public JsonMsgBean saveOrUpdateFlowItem(Model model,FlowItem flowItem){
		try {
			Response<Integer> result = flowItemService.addFlowItem(flowItem);
			return new JsonMsgBean("success", "1", "数据录入或更新成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new JsonMsgBean("fail", "0", "数据录入或更新失败");
		}
		
	}
	
	@GetMapping("/deleteFlowItem")
	@ResponseBody
	public JsonMsgBean deleteFlowItem(Model model,String flowItemId){
		try {
			flowItemService.deleteFlowItem(Long.valueOf(flowItemId));
			return new JsonMsgBean("success", "1", "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new JsonMsgBean("fail", "0", "删除失败");
		}
	}
	
	@GetMapping("/flowList")
	public String flowList(Model model,Flow flow,PageInfo<Flow> page){
		Account account = SessionContextUtils.getCurrentUser();
		flow.setOrgId(account.getCompanyId());
		Response<PageInfo<Flow>> flowList = flowService.flowList(flow, page.getPageNo(), page.getPageSize(), page.getPageSortFiled(), page.getPageSortType());
		Flow searchFlow = new Flow();
		searchFlow.setHeadFlag("Y");
		Flow headFlow = null;
		if("B0311".equals(account.getCompanyId())){
			
			searchFlow.setOrgId(account.getCompanyId());
			Response<PageInfo<Flow>> listFlow = flowService.flowList(searchFlow, 1, 10, page.getPageSortFiled(), page.getPageSortType());
			if(listFlow!=null&&listFlow.getData().getRecords().size()>0){
				headFlow = listFlow.getData().getRecords().get(0);
			}
		}
		//获取组织机构
		List<Organization> orgList = manageOrganizationService.getOrganizationList();
		model.addAttribute("flowList", flowList);
		model.addAttribute("flow", flow);
		model.addAttribute("headFlow", headFlow);
		model.addAttribute("orgList",orgList);
		return "erecruitment/flow/flowManage";
	}
	
	@GetMapping("/addFlow")
	public String addFlow(Model model,String flowId,String operation){
		Account account = SessionContextUtils.getCurrentUser();
		//查询基础流程信息
		Response<List<FlowItem>> flowItemList =  flowItemService.getItemFlowList();
		model.addAttribute("flowItemList", flowItemList.getData());
		if(flowId != null){
			//查询当前流程信息
			Response<List<FlowActionVo>> resActionList = new Response<List<FlowActionVo>>(Response.SUCCESS, "获取流程节点信息列表");
			if(!"".equals(flowId)&&flowId!=null){
				resActionList = flowActionService.getFlowAction(Long.valueOf(flowId));
			}
			model.addAttribute("aFlowList",resActionList.getData());
			//查询当前流程
			Flow flow = new Flow();
			flow.setId(Long.valueOf(flowId));
			Response<Flow> resflow = flowService.selectFlowById(flow);
			model.addAttribute("flow", resflow.getData());
		}
		//获取组织机构
		List<Organization> orgList = manageOrganizationService.getOrganizationList();
		model.addAttribute("orgList",orgList);
		
		//获取角色字典
		Response<Map<String,List<Dictionary>>> dictResp = dictionaryService.findByCodes("account_role");
		List<Dictionary> dictList =  dictResp.getData().get("account_role");
		model.addAttribute("pList",dictList);
		model.addAttribute("operation",operation);
		model.addAttribute("userInfo",account);
		return "erecruitment/flow/addFlow";
	}
	
	@RequestMapping("/saveOrUpdateFlow")
	@ResponseBody
	public JsonMsgBean saveOrUpdateFlow(Model model,@RequestBody FlowAcceptVo flowAcceptVo){
		try {
			Flow flow = flowAcceptVo.getFlow();
			//查询公司流程是否存在
			Flow searchFlow = new Flow();
			searchFlow.setOrgId(flow.getOrgId());
			Flow  resFLow = flowService.selectFlowById(searchFlow).getData();
			if(flow.getId() == null && resFLow != null){
				return new JsonMsgBean("fail", "0", "公司流程已存在");
			}
			
			Response<Integer> response = flowService.addFlow(flowAcceptVo);
			if("1".equals(response.getCode())){
				return new JsonMsgBean("success", "1", "数据录入或更新成功");
			}else{
				return new JsonMsgBean("success", "0", "数据录入或更新失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return new JsonMsgBean("fail", "0", "数据录入或更新失败");
		}
	}
	@GetMapping("/deleteFlow")
	@ResponseBody
	public JsonMsgBean deleteFlow(Model model,String flowId){
		try {
			flowService.deleteFlow(Long.valueOf(flowId));
			return new JsonMsgBean("success", "1", "删除成功");
		} catch (Exception e) {
			// TODO: handle exception
			return new JsonMsgBean("fail", "0", "删除失败");
		}
	}
}
