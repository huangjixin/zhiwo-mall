package com.fulan.application.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PlanQuestion;
import com.fulan.api.plan.domain.PublicClass;
import com.fulan.api.plan.vo.CompulsoryCplanManageVo;
import com.fulan.api.plan.vo.PublicClassInsertVo;
import com.fulan.api.plan.vo.PublicClassVo;
import com.fulan.api.plan.vo.PublicCourseDto;
import com.fulan.application.service.PlanCommentService;
import com.fulan.application.service.PlanQuestionService;
import com.fulan.application.service.PublicClassService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;
/**
 * 鍏紑璇�  鎺у埗鍣�
 * @author kang
 *
 */
@RestController
@RequestMapping("/manage/publicClass")
public class PublicClassController{
	
	@Autowired
	private PublicClassService publicClassService;
	@Autowired
	private PlanQuestionService planQuestionService;
	@Autowired
	private PlanCommentService planCommentService;
	/**
	 * 鎿嶄綔鍏紑璇� :閫夋嫨鐨勬槸璇剧▼闇�瑕佽璇剧▼鎵撳寘鎴愬涔犺鍒�  鍚屾椂缁存姢璇ュ涔犺鍒掑拰璇剧▼鐨勫叧绯伙紝
	 * 闇�灏嗗涔犺鍒抜d 缁存姢鍒皃ubClass琛ㄤ腑鐨刾lan_id
	 * 			:閫夋嫨鐨勬槸瀛︿範璁″垝	姝ゆ椂鍙渶瑕佸皢瀛︿範璁″垝鐨処d 缁存姢鍒皃ubClass琛ㄤ腑鐨刾lan_id
	 * @param param
	 * @param pubclass
	 * @return
	 */
	@RequestMapping(value="/creatPubClass",method=RequestMethod.POST)
	@ResponseBody
	public String  creatPubClass(@RequestBody PublicClassInsertVo publicClassInsertVo,
	        @RequestParam(name="fileId",required=false)Long fileId){
		/*PublicClass pubclass = new PublicClass();*/
		PublicClass pubclass=publicClassInsertVo.getPublicClass();
		List<PlanAuthority> planAuthorityList= publicClassInsertVo.getPlanAuthorityList();
		Map<String,String[]> map =new HashMap<>();
		map.put(publicClassInsertVo.getType(), publicClassInsertVo.getPlanOrCourseId());
		if(null == pubclass){
			return null;
		}
		/*Map<String,String> param = new HashMap<String, String>();
		param.put("2", "955326144768393217");*/
		String studyPlanName = publicClassInsertVo.getStudyPlanName();
		String studyPlanDescription = publicClassInsertVo.getStudyPlanDescription();
		if(!map.isEmpty()){
			return publicClassService.handPubClass(pubclass,map,planAuthorityList,
			        studyPlanName,studyPlanDescription, fileId);
		}else{
			return null;
		}
		//return null;
	}
	@RequestMapping(value="/updatePubClass",method=RequestMethod.POST)
	@ResponseBody
	public String  updatePubClass(@RequestBody PublicClassInsertVo publicClassInsertVo,
	        @RequestParam(name="fileId",required=false)Long fileId){
		PublicClass pubclass=publicClassInsertVo.getPublicClass();
		List<PlanAuthority> planAuthorityList= publicClassInsertVo.getPlanAuthorityList();
		if(null == pubclass){
			return null;
		}
		return publicClassService.handPubClass(pubclass,null,planAuthorityList,"","",fileId);
	}
	@RequestMapping(value="/pageList",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<PublicCourseDto> pageList(@RequestBody Map<String,Object> map ){
		Page<PublicCourseDto> page = 
				new Page<PublicCourseDto>();
		page.setSize((int) map.get("pageSize"));
		page.setCurrent((int) map.get("pageNo"));
		return publicClassService.manageListByPage(page, map);
	}
	@RequestMapping(value="/checkInfo",method=RequestMethod.GET)
	public @ResponseBody PublicClassVo checkPublicClassInfo(String id){
		if(!StringUtil.isEmpty(id)){
			return publicClassService.publicClassVoInFo(id);
		}
		return null;
	}
	// 鎻愰棶 鍒嗛〉
	@RequestMapping(value="/listQuestByPage",method=RequestMethod.POST)
	public @ResponseBody PageInfo<Map<String,Object>> listQuestByPage(@RequestBody Map<String,String> map)
	{
		Page<Map<String,Object>> page = 
				new Page<Map<String,Object>>();
		page.setSize(Integer.parseInt(map.get("pageSize")));
		page.setCurrent(Integer.parseInt(map.get("pageNo")));
		return planQuestionService.manageListByPage(page, map);
	}
	//淇敼 鎻愰棶
	@RequestMapping(value="/updateQues",method=RequestMethod.POST)
	public Response<Boolean> updateQues(@RequestBody PlanQuestion planQuestion){
		return planQuestionService.updatePlanQuestion(planQuestion);
	}
	// 璇勮 鍒嗛〉
	@RequestMapping(value="/listCommentByPage",method=RequestMethod.POST)
	public @ResponseBody PageInfo<Map<String,Object>> listCommentByPage(@RequestBody Map<String,String> map)
	{
		Page<Map<String,Object>> page = 
				new Page<Map<String,Object>>();
		page.setSize(Integer.parseInt(map.get("pageSize")));
		page.setCurrent(Integer.parseInt(map.get("pageNo")));
		return planCommentService.manageListByPage(page, map);
	}
	/**
	 * 鎵归噺寮�鍚垨鑰呴殣钘�  鐢ㄤ簬璇勮鎴栬�呮彁闂�(鍚屾牱閫傜敤浜庡崟涓暟鎹搷浣�)
	 * @param id 瑕佹搷浣滅殑鏁版嵁涓婚敭鏁扮粍
	 * @param type 鎻愰棶Question鍜岃瘎璁篊omment 
	 * @param isOpen 1锛氬紑鍚�/0锛氶殣钘�
	 * @return
	 */
	@RequestMapping(value="/hideOrOpenAll",method=RequestMethod.POST)
	@ResponseBody
	public Response<Boolean> hideOrOpenAll(@RequestParam("id")String[] id,
										   @RequestParam("type")String type,
										   @RequestParam("isOpen")String isOpen){
		Response<Boolean> res = new Response<>();
		if(null == id || StringUtil.isEmpty(type)){
			res.setCode(Response.ERROR);
			res.setMsg("鍙傛暟寮傚父");
			res.setData(false);
			return res;
		}
		return planQuestionService.hideOrOpenAll(id,type,isOpen);
		
	}
	
	/**
	 * 鎵归噺涓婃灦鎴栦笅鏋�
	 * @param state 1锛氫笂鏋�/2锛氫笅鏋�
	 * @param publicClassIds锛氬叕鍏辫id
	 * @return
	 */
	@RequestMapping(value="/batchShelves",method=RequestMethod.POST)
	@ResponseBody
	public Response<Boolean> batchShelves(@RequestParam("publicClassIds")String[] publicClassIds,
										   @RequestParam("state")String state){
		
		return publicClassService.batchShelves(publicClassIds,state);
		
	}
	
	
	
	@RequestMapping(value="/publicClassListByPage",method=RequestMethod.POST)
	@ResponseBody
	public PageInfo<PublicCourseDto> publicClassListByPage(Map<String,Object> map,Page<PublicCourseDto> page ){
		return publicClassService.managePublicListByPage(page, map);
	}
	
	@RequestMapping(value="/selectQuestionById",method=RequestMethod.GET)
	@ResponseBody
	public PlanQuestion selectQuestionById(@RequestParam(value="questionId")String questionId ){
		PlanQuestion planQuestion=planQuestionService.selectById(questionId);
		return planQuestion;
	}
	
	@RequestMapping(value="/updatePublicClass",method=RequestMethod.POST)
	@ResponseBody
	public String  updatePublicClass(@RequestBody PublicClass pubclass){
		
		return publicClassService.updatePublicClass(pubclass);
	}
	
	@RequestMapping(value="/selectByClassId",method=RequestMethod.GET)
 	public @ResponseBody PublicClass selectByClassId(@RequestParam String id){
 		return publicClassService.selectOne(id);
 	}
	
}
