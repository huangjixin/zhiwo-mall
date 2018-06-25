package com.fulan.application.custom.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.*;
import com.fulan.api.plan.vo.*;
import com.fulan.api.security.domain.Account;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.*;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 公开课前端控制器
 * </p>
 *
 * @author Hedge
 * @since 2018-01-23
 */
@Api(tags = "PublicClassApi", description = "公开课接口")
@RestController
@RequestMapping("/customer/publicClass")
public class PublicClassCustomerController {

    private static final Logger logger = LoggerFactory.getLogger(PublicClassCustomerController.class);
    @Autowired
    private PublicClassService publicClassService;

    @Autowired
    private PlanQuestionService planQuestionService;

    @Autowired
    private PlanCommentService planCommentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private PlanCollectService planCollectService;

    @Autowired
    private PlanCourseService planCourseService;
    @Autowired
    private HistoryService historyService;

    @Autowired
    private PlanAuthorityService planAuthorityService;
    @Autowired
    private LearningProgressService learningProgressService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 分页查看公开课
     * @param planRequestDto
     * @return
     */
    @ApiOperation(value = "分页查询公开课", notes = "分页查询公开课", response = PublicCourseDto.class)
    @PostMapping(value = "/list")
    public Response<Page<PublicCourseDto>> listByPage(
            @ApiParam("tagId:一级分类<br>childTagId:二级分类<br>name:名字搜索<br>pageNo:要跳转的页数<br>pageSize:每页条数" +
                    "<br>pageSortFiled:排序字段名，默认：viewsNum<br>pageSortType:顺序：asc,倒叙：desc，默认：desc")
            @RequestBody PlanRequestDto planRequestDto) {
        Response<Page<PublicCourseDto>> res =new Response<Page<PublicCourseDto>>();
        try {
            logger.info("--------------------查询公开课---------------------");
            //公开课分页查询
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("tagId",planRequestDto.getTagId());
            paramMap.put("childTagId",planRequestDto.getChildTagId());
            paramMap.put("name",planRequestDto.getName());
            paramMap.put("pageNo",planRequestDto.getPageNo());
            paramMap.put("pageSize",planRequestDto.getPageSize());
            paramMap.put("pageSortFiled",planRequestDto.getPageSortFiled());
            paramMap.put("pageSortType",planRequestDto.getPageSortType());
            Page<PublicCourseDto> publicClassPage = publicClassService.searchPublicClass(paramMap);
            res.setData(publicClassPage);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        logger.info("---------------------"+res.getMsg()+"---------------------");
        return res;
    }

    /**
     * 课程详情
     * @param planRequestDto
     * @return
     */
    @ApiOperation(value = "课程详情", notes = "查看课程详情", response = PlanDetailDto.class)
    @PostMapping(value = "/detail")
    public Response<PlanDetailDto> getCourseDetail(
            @ApiParam("planId:计划Id,required<br>courseId:课程Id,required<br>planType:计划类型-1公开课/2学习计划/5班级计划/3岗位发展/4必修任务required")
            @RequestBody PlanRequestDto planRequestDto) {
        Response<PlanDetailDto> res =new Response<PlanDetailDto>();
        try {
            logger.info("--------------------查询课程详情---------------------");
            PlanDetailDto planDetailDto = publicClassService.getPlanDetailDto(planRequestDto.getPlanId(),planRequestDto.getCourseId(),planRequestDto.getPlanType(),(Long)redisUtil.getUserId());
            //记录浏览记录
            History history = new History();
            history.setPlanId(planRequestDto.getPlanId());
            history.setPlanType(planRequestDto.getPlanType());
            Account account = (Account)redisUtil.getUserInfo();
            history.setUserId(account.getId());
            history.setUserName(account.getAccountName());
            historyService.insertHistory(history);
            res.setData(planDetailDto);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        return res;

    }


    /**
     * 获取课程大纲
     * @param planRequestDto
     * @return
     */
    @ApiOperation(value = "课程大纲", notes = "课程大纲", response = PlanCoursePaperDto.class)
    @PostMapping(value = "/getCourseList")
    public Response<Map<String,List<PlanCoursePaperDto>>> getCourseList(
            @ApiParam("planId:计划Id,required<br>planType:计划类型-1公开课/2学习计划/5班级计划/3岗位发展/4必修任务required")
            @RequestBody PlanRequestDto planRequestDto) {
        Response<Map<String,List<PlanCoursePaperDto>>> res =new Response<Map<String,List<PlanCoursePaperDto>>>();
        try {
            logger.info("------------------"+"获取课程大纲"+"-----------------------");
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("planId",planRequestDto.getPlanId());
            paramMap.put("courseType",planRequestDto.getPlanType());
            paramMap.put("isSeq",planRequestDto.getIsSeq());
            paramMap.put("userId",redisUtil.getUserId());
            Map<String,List<PlanCoursePaperDto>> coursePaperListMap = planCourseService.getCoursePaperListByPlan(paramMap);
            res.setData(coursePaperListMap);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        logger.info("---------------------"+res.getMsg()+"---------------------");
        return res;
    }


    /**
     * 分页获取学习评价列表
     * @param planRequestDto
     * @return
     */
    @ApiOperation(value = "学习评价列表", notes = "分页获取学习评价列表", response = PlanCommentDto.class)
    @PostMapping(value = "/getCommentList")
    public Response<Page<PlanCommentDto>> getCommentList(
            @ApiParam("planId:计划Id,required<br>planType:计划类型-1公开课/2学习计划/5班级计划/3岗位发展/4必修任务required<br>pageNo:要跳转的页数<br>pageSize:每页条数" +
                    "<br>pageSortFiled:排序字段名，默认：gmtCreate<br>pageSortType:顺序：asc,倒叙：desc，默认：desc")
            @RequestBody PlanRequestDto planRequestDto) {
        Response<Page<PlanCommentDto>> res =new Response<Page<PlanCommentDto>>();
        try {
            logger.info("-------------------"+"获取学习评价列表"+"----------------------");
            Map<String,Object> paramMap = new HashMap<String, Object>();
           paramMap.put("userId",redisUtil.getUserId());
            paramMap.put("planId", planRequestDto.getPlanId());
            paramMap.put("isOpen", CommenConstant.VALUE_YES);
            paramMap.put("pageNo",planRequestDto.getPageNo());
            paramMap.put("pageSize",planRequestDto.getPageSize());
            //分页获取学习评价列表
            Page<PlanCommentDto> planCommentPage = planCommentService.searchPlanComment(paramMap);
            res.setData(planCommentPage);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        logger.info("---------------------"+res.getMsg()+"---------------------");
        return res;
    }


    /**
     * 分页获取课程咨询列表
     * @param planRequestDto
     * @return
     */
    @ApiOperation(value = "课程咨询列表", notes = "分页获取课程咨询列表", response = PlanQuestionDto.class)
    @PostMapping(value = "/getPlanQuestionList")
    public Response<Page<PlanQuestionDto>> getPlanQuestionList(
            @ApiParam("planId:计划Id,required<br>planType:计划类型-1公开课/2学习计划/5班级计划/3岗位发展/4必修任务required<br>pageNo:要跳转的页数<br>pageSize:每页条数" +
                    "<br>pageSortFiled:排序字段名，默认：gmtCreate<br>pageSortType:顺序：asc,倒叙：desc，默认：desc")
            @RequestBody PlanRequestDto planRequestDto) {
        Response<Page<PlanQuestionDto>> res =new Response<Page<PlanQuestionDto>>();
        try {
            logger.info("-------------------"+"分页获取课程咨询列表"+"----------------------");
            Map<String,Object> paramMap = new HashMap<String, Object>();
            paramMap.put("userId",redisUtil.getUserId());
            paramMap.put("isOpen", CommenConstant.VALUE_YES);
            paramMap.put("planId", planRequestDto.getPlanId());
            paramMap.put("pageNo",planRequestDto.getPageNo());
            paramMap.put("pageSize",planRequestDto.getPageSize());
            //获取课程咨询列表
            Page<PlanQuestionDto> planQuestionPage =
                    planQuestionService.searchPlanQuestion(paramMap);
            res.setData(planQuestionPage);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        logger.info("---------------------"+res.getMsg()+"---------------------");
        return res;
    }


    /**
     * 我要提问
     * @param planQuestion
     * @return
     */
    @ApiOperation(value = "我要提问", notes = "我要提问", response = Response.class)
    @PostMapping(value = "/question")
    public Response<Boolean> question(
            @ApiParam("courseId:计划Id,required<br>" +
                    "courseType:计划类型-1公开课/2学习计划/5班级计划/3岗位发展/4必修任务required<br>" +
                    "quesDesc:问题描述 required")
            @RequestBody PlanQuestion planQuestion) {
        try {
            logger.info("-------------------"+"我要提问"+"----------------------");
            // 新增课程咨询
            Response<Boolean> response= planQuestionService.insertPlanQuestion(planQuestion);
            logger.info("---------------------"+response.getMsg()+"---------------------");
            return response;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    /**
     * 我要评论
     * @param planComment
     * @return
     */
    @ApiOperation(value = "我要评论", notes = "我要评论", response = Response.class)
    @PostMapping(value = "/comment")
    public Response<Boolean> comment(
            @ApiParam("courseId:计划Id,required<br>" +
                    "courseType:计划类型-1公开课/2学习计划/5班级计划/3岗位发展/4必修任务required<br>" +
                    "comment:评论内容,required;<br>star:星级")
            @RequestBody PlanComment planComment) {
        try {
            logger.info("-------------------"+"我要评论"+"----------------------");
            //新增课程评论
            Response<Boolean> response= planCommentService.insertPlanComment(planComment);
            logger.info("---------------------"+response.getMsg()+"---------------------");
            return response;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    /**
     * 课程收藏
     * @param planCollect
     * @return
     */
    @ApiOperation(value = "课程收藏", notes = "课程收藏", response = PlanCollect.class)
    @PostMapping(value = "/collect")
    public Response<PlanCollect> collect(
            @ApiParam("courseId:planId,required<br>" +
                    "planType:计划类型-1公开课/2学习计划/5班级计划/3岗位发展/4必修任务required<br>")
            @RequestBody PlanCollect planCollect) {
        try {
            logger.info("-------------------"+"课程收藏"+"----------------------");
            //新增收藏记录
            Response<PlanCollect> response = planCollectService.insertPlanCollect(planCollect);
            logger.info("---------------------"+response.getMsg()+"---------------------");
            return response;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    /**
     * 课程取消收藏
     * @param collectId
     * @return
     */
    @ApiOperation(value = "课程取消收藏", notes = "课程取消收藏", response = Response.class)
    @PostMapping(value = "/noCollect")
    public Response<Boolean> collect(
            @ApiParam(name = "collectId", value = "收藏Id",required = true)
            @RequestParam(value = "collectId",required = true) Long collectId) {
        try {
            logger.info("-------------------"+"课程取消收藏"+"----------------------");
            //删除收藏记录
            Response<Boolean> response =  planCollectService.deletePlanCollect(collectId);
            logger.info("---------------------"+response.getMsg()+"---------------------");
            return response;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }


    /**
     * 课程评论点赞
     * @param like
     * @return
     */
    @ApiOperation(value = "评论点赞", notes = "评论点赞", response = Like.class)
    @PostMapping(value = "/like")
    public Response<Like> like(
            @ApiParam(name = "hostId:评论或者问答的编号,required<br>planId:计划编号<br>" +
                    "type:点赞类型 ,required, 1评论，2问答")
            @RequestBody Like like) {
        try {
            logger.info("-------------------"+"评论点赞"+"----------------------");
            //新增点赞记录
            Response<Like> response= likeService.insertLike(like);
            logger.info("---------------------"+response.getMsg()+"---------------------");
            return response;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }


    /**
     * 课程评论取消点赞
     * @param likeId
     * @return
     */
    @ApiOperation(value = "评论取消点赞", notes = "评论取消点赞", response = Response.class)
    @PostMapping(value = "/noLike")
    public Response<Boolean> noLike(
            @ApiParam(name = "likeId", value = "点赞编号")
            @RequestParam(value = "likeId",required = true)  Long likeId) {
        try {
            logger.info("-------------------"+"评论取消点赞"+"----------------------");
            //新增点赞记录或者删除点赞记录
            Response<Boolean> response= likeService.deleteLike(likeId);
            logger.info("---------------------"+response.getMsg()+"---------------------");
            return response;
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }

    /**
     *
     * 记录播放时长（包括考虑课程学完/也可能是考核完）
     * @param learningProgress
     * @return
     */
    @ApiOperation(value = "记录播放详情（包含课程奖励）", notes = "记录播放详情（包含课程奖励）", response = Response.class)
    @PostMapping(value = "/learningTime")
    public Response<Boolean> learningTime(
            @ApiParam("planId:计划Id,required<br>courseId:课程Id,required<br>" +
                    "learningTime,此次播放时长,,required<br>" +
                    "type:计划类型-1公开课/2学习计划/5班级计划/3岗位发展/4必修任务required")
            @RequestBody  LearningProgress learningProgress) {
        try {
            logger.info("-------------------"+"记录播放时长"+"----------------------");
            //TODO 考虑到课程学习完结需要触发的情况（积分奖励、证书）
            return learningProgressService.insertLearningProgress(learningProgress);
        } catch (Exception e) {
            logger.error("", e);
            throw e;
        }
    }
}
