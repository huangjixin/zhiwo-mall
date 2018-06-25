package com.fulan.application.custom.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.vo.*;
import com.fulan.api.security.domain.Account;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.StudyCenterService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.json.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端学习中心 前端控制器
 * </p>
 *
 * @author fulan123
 * @since 2018-01-23
 */
@RestController
@Api(tags = "StudyCenterApi", description = "前端学习中心接口")
@RequestMapping("/customer/studyCenter")
public class StudyCenterController {

	private Logger logger = LoggerFactory.getLogger(StudyCenterController.class);
	
	@Autowired
	private StudyCenterService studyCenterService;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 我的积分
	 * @return
	 */
	@ApiOperation(value = "我的积分", notes = "查询我的积分")
	@GetMapping(value = "/integral")
	public Response<SignIntegralVo> integral(){
		try {
			Account account = (Account) redisUtil.getUserInfo();
			SignIntegralVo integral = studyCenterService.getIntegral(account.getId());
			Response<SignIntegralVo> res = new Response<SignIntegralVo>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			res.setData(integral);
			logger.info("----------------------查询我的计划成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询我的计划失败------------------------");
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<SignIntegralVo>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 我的计划
	 * @return
	 */
	@ApiOperation(value = "我的计划", notes = "查询我的计划")
	@GetMapping(value = "/classStudyPlan")
	public Response<Page<StudyPlanVo>> classStudyPlan(
			@ApiParam(name = "pageNo", value = "当前页数") @RequestParam(name="pageNo",defaultValue="0")String pageNo,
			@ApiParam(name = "pageSize", value = "数量") @RequestParam(name="pageSize",defaultValue="0")String pageSize
	){
		try {
			Account account = (Account) redisUtil.getUserInfo();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("accountId", account.getId());
			map.put("postType", account.getPostType());
			map.put("pageNo", pageNo);
			map.put("pageSize", pageSize);
			Page<StudyPlanVo>  vo = studyCenterService.findMyClassPlanById(map);
			Response<Page<StudyPlanVo>> res = new Response<Page<StudyPlanVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			res.setData(vo);
			logger.info("----------------------查询我的计划成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询我的计划失败------------------------");
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<Page<StudyPlanVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 我的计划
	 * @return
	 */
	@ApiOperation(value = "我的计划", notes = "查询我的计划")
	@GetMapping(value = "/postStudyPlan")
	public Response<Page<StudyPlanVo>> postStudyPlan(
			@ApiParam(name = "pageNo", value = "当前页数") @RequestParam(name="pageNo",defaultValue="0")String pageNo,
			@ApiParam(name = "pageSize", value = "数量") @RequestParam(name="pageSize",defaultValue="0")String pageSize
	){
		try {
			Account account = (Account) redisUtil.getUserInfo();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("accountId", account.getId());
			map.put("postType", account.getPostType());
			map.put("pageNo", pageNo);
			map.put("pageSize", pageSize);
			Page<StudyPlanVo>  vo = studyCenterService.findMyPostPlanById(map);
			Response<Page<StudyPlanVo>> res = new Response<Page<StudyPlanVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			res.setData(vo);
			logger.info("----------------------查询我的计划成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询我的计划失败------------------------");
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<Page<StudyPlanVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 我的计划
	 * @return
	 */
	@ApiOperation(value = "我的计划", notes = "查询我的计划")
	@GetMapping(value = "/compStudyPlan")
	public Response<Page<StudyPlanVo>> compStudyPlan(
			@ApiParam(name = "pageNo", value = "当前页数") @RequestParam(name="pageNo",defaultValue="0")String pageNo,
			@ApiParam(name = "pageSize", value = "数量") @RequestParam(name="pageSize",defaultValue="0")String pageSize
	){
		try {
			Account account = (Account) redisUtil.getUserInfo();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("accountId", account.getId());
			map.put("postType", account.getPostType());
			map.put("pageNo", pageNo);
			map.put("pageSize", pageSize);
			Page<StudyPlanVo>  vo = studyCenterService.findMyCompPlanById(map);
			Response<Page<StudyPlanVo>> res = new Response<Page<StudyPlanVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			res.setData(vo);
			logger.info("----------------------查询我的计划成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询我的计划失败------------------------");
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<Page<StudyPlanVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}
	
	
	
	/**
	 * 学习课程详情
	 * @param planId
	 * @return
	 */
	@ApiOperation(value = "课程详情", notes = "查询课程详情")
	@GetMapping(value = "/mycourse")
	public Response<Object> getCourseDetail(
			@ApiParam(name = "planId", value = "查询条件-计划ID") @RequestParam(name="planId",defaultValue="0")Long planId,
			@ApiParam(name = "planType", value = "查询条件-计划类型") @RequestParam(name="planType",defaultValue="0")Long planType
			){
		try {
			Response<Object> res = new Response<Object>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			Account account = (Account) redisUtil.getUserInfo();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("planId", planId);
			map.put("accountId", account.getId());
			map.put("postType", account.getPostType());
			map.put("courseType", planType);
			Object courses = studyCenterService.getMyCourse(map);
			res.setData(courses);
			logger.info("----------------------查询课程详情成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询课程详情失败------------------------");
			e.printStackTrace();
			logger.error(e.getMessage());
			return new Response<Object>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}


	/**
	 * 收藏线下活动
	 * @param activityVO
	 * @return
	 */
	@ApiOperation(value = "收藏线下活动", notes = "收藏线下活动详情")
	@PostMapping(value = "/activityCollect")
	public Response<Page<ActivityCollectVo>> activityCollect(
			@ApiParam(name = "activityVo", value = "当前页数和显示多少条") @RequestBody ActivityVO activityVO
	){
		try {
			Response<Page<ActivityCollectVo>> res = new Response<Page<ActivityCollectVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			Page<ActivityCollectVo> page = studyCenterService.getActivityCollectByAccountId(activityVO);
			res.setData(page);
			logger.info("----------------------查询收藏线下活动成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询收藏线下活动失败------------------------");
			logger.error(e.getMessage());
			return new Response<Page<ActivityCollectVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}


	/**
	 * 收藏公开课程详情
	 * @param planRequestDto
	 * @return
	 */
	@ApiOperation(value = "收藏公开课程详情", notes = "收藏公开课程详情")
	@PostMapping(value = "/publicClassCollect")
	public Response<Page<PublicClassCollectVo>> publicClassCollect(
			@ApiParam("userId:用户Id<br>tagId:一级分类<br>childTagId:二级分类<br>name:名字搜索<br>pageNo:要跳转的页数<br>pageSize:每页条数" +
					"<br>pageSortFiled:排序字段名，默认：viewsNum<br>pageSortType:顺序：asc,倒叙：desc，默认：desc")
			@RequestBody PlanRequestDto planRequestDto
	){
		try {
			Response<Page<PublicClassCollectVo>> res = new Response<Page<PublicClassCollectVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			//公开课分页查询
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageNo",planRequestDto.getPageNo());
			paramMap.put("pageSize",planRequestDto.getPageSize());
//			paramMap.put("pageSortFiled",planRequestDto.getPageSortFiled());
//			paramMap.put("pageSortType",planRequestDto.getPageSortType());
			Page<PublicClassCollectVo> vo = studyCenterService.getPublicClassCollectByAccountId(paramMap);
			res.setData(vo);
			logger.info("----------------------查询收藏公开课程详情成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询收藏公开课程详情失败------------------------");
			logger.error(e.getMessage());
			return new Response<Page<PublicClassCollectVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 历史记录
	 * @param planRequestDto
	 * @return
	 */
	@ApiOperation(value = "历史记录", notes = "历史记录")
	@PostMapping(value = "/history")
	public Response<Page<HistoryVo>> history(
			@ApiParam("userId:用户Id<br>tagId:一级分类<br>childTagId:二级分类<br>name:名字搜索<br>pageNo:要跳转的页数<br>pageSize:每页条数" +
					"<br>pageSortFiled:排序字段名，默认：viewsNum<br>pageSortType:顺序：asc,倒叙：desc，默认：desc")
			@RequestBody PlanRequestDto planRequestDto
	){
		try {
			Response<Page<HistoryVo>> res = new Response<Page<HistoryVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			//公开课分页查询
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageNo",planRequestDto.getPageNo());
			paramMap.put("pageSize",planRequestDto.getPageSize());
			paramMap.put("pageSortFiled",planRequestDto.getPageSortFiled());
			paramMap.put("pageSortType",planRequestDto.getPageSortType());
			Page<HistoryVo> vo = studyCenterService.getHistoryRecord(paramMap);
			res.setData(vo);
			logger.info("----------------------查询历史记录成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询历史记录失败------------------------");
			logger.error(e.getMessage());
			return new Response<Page<HistoryVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 删除历史记录
	 * @param historyIds
	 * @return
	 */
	@ApiOperation(value = "删除历史记录", notes = "删除历史记录")
	@PostMapping(value = "/deleteHistory")
	public Response<Page<HistoryVo>> deleteHistory(
			@ApiParam(name = "historyIds" , value = "历史记录ID")@RequestBody List<Long> historyIds){
		try {
			Response<Page<HistoryVo>> res = new Response<Page<HistoryVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			System.out.println(historyIds);
			studyCenterService.deleteHistoryByIds(historyIds);
			logger.info("----------------------删除历史记录成功------------------------");
			return res;

		} catch (Exception e) {
			logger.error("----------------------删除历史记录失败------------------------");
			logger.error(e.getMessage());
			return new Response<Page<HistoryVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}


	/**
	 * 删除收藏
	 * @param collectIds
	 * @return
	 */
	@ApiOperation(value = "删除收藏", notes = "删除收藏")
	@PostMapping(value = "/deleteCollect")
	public Response deleteCollect(
			@ApiParam(name = "collectIds" , value = "收藏ID")@RequestBody List<Long>  collectIds){
		try {
			Response res = new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			studyCenterService.deleteCollectByIds(collectIds);
			logger.info("----------------------删除收藏成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------删除收藏失败------------------------");
			logger.error(e.getMessage());
			return new Response(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}


	/**
	 * 签到领取积分
	 * @return
	 */
	@ApiOperation(value = "签到领取积分", notes = "签到领取积分")
	@PostMapping(value = "/sign")
	public Response sign(){
		try {
			Response res = new Response(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			studyCenterService.sign();
			logger.info("----------------------签到领取积分成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------签到领取积分失败------------------------");
			logger.error(e.getMessage());
			return new Response(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

}
