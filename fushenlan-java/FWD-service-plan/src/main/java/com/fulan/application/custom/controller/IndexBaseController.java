package com.fulan.application.custom.controller;


import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.fulan.api.plan.domain.ClassPlan;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.PostDevelopment;
import com.fulan.api.plan.vo.*;
import com.fulan.api.security.domain.Account;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.IndexBaseService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.json.JsonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端首页 前端控制器
 * </p>
 *
 * @author fulan123
 * @since 2018-01-18
 */
@RestController
@Api(tags = "IndexBaseApi", description = "前端首页接口")
@RequestMapping("/customer/base")
public class IndexBaseController {

	private Logger logger = LoggerFactory.getLogger(IndexBaseController.class);

	@Autowired
	private IndexBaseService indexBaseService;

	@Autowired
	private RedisUtil redisUtil;

	/**
	 * 前端首页  - 查询岗位计划
	 * @return
	 */
	@ApiOperation(value = "岗位发展", notes = "查询岗位发展", response = PostDevelopment.class)
	@GetMapping(value = "/post/list")
	public Response<PostDevelopmentVo> findPostByAccountId(){
		try {
			logger.info("----------------------查询岗位发展开始------------------------");
			Response<PostDevelopmentVo> res = new Response<PostDevelopmentVo>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			Map<String,Object> map = new HashMap<String,Object>();
			Account account = (Account) redisUtil.getUserInfo();
			map.put("postType",account.getPostType());
			map.put("accountId",account.getId());
			map.put("courseType", CommenConstant.EL_COURSE_TYPE_POSTDEVELOPMENT);
			PostDevelopmentVo plan = indexBaseService.findPostDevelopmentByAccount(map);
			logger.info("----------------------查询岗位发展成功------------------------");
			res.setData(plan);
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询岗位发展失败------------------------");
			logger.error(e.toString());
			return new Response<PostDevelopmentVo>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * 前端首页  - 查询班级计划
	 * @return
	 */
	@ApiOperation(value = "班级计划", notes = "查询班级计划", response = ClassPlan.class)
	@GetMapping(value = "/classplan/list")
	public Response<ClassPlanTopVo> findCPByAccountId(){
		try {
			logger.info("----------------------查询班级计划开始------------------------");			Response<ClassPlanTopVo> res = new Response<ClassPlanTopVo>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			Map<String,Object> map = new HashMap<String,Object>();
			Long id = (Long) redisUtil.getUserId();
			map.put("accountId",id);
			map.put("courseType", CommenConstant.EL_COURSE_TYPE_CLASSPLAN);
			ClassPlanTopVo plans = indexBaseService.findClassPlanByAccount(map);
			logger.info("----------------------查询班级计划成功------------------------");
			res.setData(plans);
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询班级计划失败------------------------");
			logger.error(e.toString());
			return new Response<ClassPlanTopVo>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * 前端首页  - 查询必修任务
	 * @return
	 */
	@ApiOperation(value = "必修任务", notes = "查询必修任务", response = CompulsoryCplan.class)
	@GetMapping(value = "/certify/list")
	public Response<CompulsoryPlanTopVo> findCertifyByAccountId(
			@ApiParam("dictCode:字典项")@RequestParam(value = "dictCode",required = true)
					String dictCode){
		try {
			logger.info("----------------------查询必修任务开始------------------------");
			Response<CompulsoryPlanTopVo> res = new Response<CompulsoryPlanTopVo>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			Map<String,Object> map = new HashMap<String,Object>();
			Long id = (Long) redisUtil.getUserId();
			map.put("accountId",id);
			map.put("courseType",CommenConstant.EL_COURSE_TYPE_COMPULSORYCPLAN);
			map.put("dictCode",dictCode);
			CompulsoryPlanTopVo plans = indexBaseService.findCompulsoryCplanByAccount(map);
			logger.info("----------------------查询必修任务成功------------------------");
			res.setData(plans);
			return res;
		} catch (Exception e) {
			logger.error("----------------------查询必修任务失败------------------------");
			logger.error(e.toString());
			return new Response<CompulsoryPlanTopVo>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}


	/**
	 * 资质考核通过奖励
	 * @param appraiseDto
	 * @return
	 */
	@ApiOperation(value = "资质考核通过奖励", notes = "资质考核通过奖励")
	@PostMapping(value = "/appraise")
	public Response<String> appraise(
			@ApiParam(name = "appraiseDto", value = "查询条件") @RequestBody AppraiseDto appraiseDto
	){
		try {
			Response<String> res = new Response<String>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			indexBaseService.appraise(appraiseDto);
			logger.info("----------------------资质考核通过获取奖励成功------------------------");
			return res;
		} catch (Exception e) {
			logger.error("----------------------资质考核通过获取奖励失败------------------------");
			logger.error(e.toString());
			return new Response<String>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}


	/**
	 * 即将过期
	 * @return
	 */
	@ApiOperation(value = "即将过期", notes = "即将过期")
	@PostMapping(value = "/expire")
	public Response<List<RemindingExpireVo>> expire(){
		try {
			Response<List<RemindingExpireVo>> res = new Response<List<RemindingExpireVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			Long id = (Long) redisUtil.getUserId();
			List<RemindingExpireVo> list = indexBaseService.getRemindingExpire(id);
			logger.info("----------------------获取即将过期成功------------------------");
			res.setData(list);
			return res;
		} catch (Exception e) {
			logger.error("----------------------获取即将过期失败------------------------");
			logger.error(e.toString());
			return new Response<List<RemindingExpireVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}

	/**
	 * 热门公开课程
	 * @return
	 */
	@ApiOperation(value = "热门公开课程", notes = "热门公开课程")
	@GetMapping(value = "/hotCourses")
	public Response<List<HotPublicClassVo>> getHotPublicClass(){
		try {
			Response<List<HotPublicClassVo>> res = new Response<List<HotPublicClassVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			Long id = (Long) redisUtil.getUserId();
			List<HotPublicClassVo> list = indexBaseService.getHotPublicClass(id);
			logger.info("----------------------获取热门公开课程成功------------------------");
			res.setData(list);
			return res;
		} catch (Exception e) {
			logger.error("----------------------获取热门公开课程失败------------------------");
			logger.error(e.getMessage());
			return new Response<List<HotPublicClassVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}



	/**
	 * 获取班级报名情况
	 * @return
	 */
	@ApiOperation(value = "班级报名情况", notes = "班级报名情况")
	@PostMapping(value = "/getEnterDetail")
	public Response<List<ClassPlanVo>> getEnterDetail(){
		try {
			Response<List<ClassPlanVo>> res = new Response<List<ClassPlanVo>>(Response.SUCCESS, Response.SUCCESS_MESSAGE);
			Long id = (Long) redisUtil.getUserId();
			List<ClassPlanVo> list = indexBaseService.getEnterDetail(id);
			logger.info("----------------------获取班级报名情况成功------------------------");
			res.setData(list);
			return res;
		} catch (Exception e) {
			logger.error("----------------------获取班级报名情况失败------------------------");
			logger.error(e.getMessage());
			return new Response<List<ClassPlanVo>>(Response.ERROR, Response.ERROR_MESSAGE);
		}
	}



}

