package com.fulan.application.custom.controller;


import com.fulan.api.paper.vo.ExamPaperVo;
import com.fulan.api.paper.vo.QuestionDto;
import com.fulan.api.paper.vo.ShortAnswerVo;
import com.fulan.application.service.PaperExamService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.json.JsonUtil;
import com.fulan.application.util.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "PaperExamApi", description = "学习平台-考试接口")
@RequestMapping("/customer/exam")
public class PaperExamController {


    private Logger logger = LoggerFactory.getLogger(PaperExamController.class);

    @Autowired
    private PaperExamService paperExamService;

    /**
     * 获取试卷
     * @param paperId
     * @return
     */
    @ApiOperation(value = "获取试卷", notes = "获取试卷", response = Response.class)
    @GetMapping(value = "/getPaper")
    public Response<ExamPaperVo> getPaper(
            @ApiParam(name = "paperId", value = "试卷Id", required = false) @RequestParam(name = "paperId") Long paperId
    ) {
        try {
            logger.info("--------------------获取试卷---------------------");
            Response<ExamPaperVo> response = new Response<ExamPaperVo>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
            ExamPaperVo vo =  paperExamService.getPaperExam(paperId);
            response.setData(vo);
            return response;
        } catch (Exception e) {
            logger.error("error", e);
            return new Response<>(Response.ERROR,Response.ERROR_MESSAGE);
        }
    }


    /**
     * 获取试卷
     * @param question
     * @return
     */
    @ApiOperation(value = "提交试卷", notes = "提交试卷", response = Response.class)
    @PostMapping(value = "/submit")
    public Response<ExamPaperVo> answer(
            @ApiParam(name = "question", value = "问题") @RequestBody QuestionDto question
    ) {
        try {
            logger.info("--------------------提交试卷---------------------");
            ShortAnswerVo vo = new ShortAnswerVo();
            //解析简答提答案
            if(question.getShortAnswers().equals("[]") || question.getShortAnswers().equals("")){
                question.setShortList(null);
            }else{
                List<ShortAnswerVo> list = JsonUtils.jsonToList(question.getShortAnswers(),ShortAnswerVo.class);
                question.setShortList(list);
            }
            Response<ExamPaperVo> response = new Response<ExamPaperVo>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
            paperExamService.submit(question);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            return new Response<ExamPaperVo>(Response.ERROR,Response.ERROR_MESSAGE);
        }
    }

    /**
     * 获取答案解析
     * @param paperId
     * @return
     */
    @ApiOperation(value = "获取答案解析", notes = "获取答案解析", response = Response.class)
    @GetMapping(value = "/analysis")
    public Response<ExamPaperVo>  analysis(
            @ApiParam(name = "paperId", value = "试卷Id") @RequestParam(name = "paperId",defaultValue = "0") Long paperId,
            @ApiParam(name = "planCourseId", value = "试卷计划中间表Id") @RequestParam(name = "planCourseId",defaultValue = "0") Long planCourseId
    ) {
        try {
            logger.info("--------------------获取答案解析---------------------");
            Response<ExamPaperVo> response = new Response<ExamPaperVo>(Response.SUCCESS,Response.SUCCESS_MESSAGE);
            //TODO 获取答案解析
            ExamPaperVo vo =  paperExamService.getAnalysis(paperId,planCourseId);
            response.setData(vo);
            return response;
        } catch (Exception e) {
            logger.error("error", e);
            return new Response<>(Response.ERROR,Response.ERROR_MESSAGE);
        }
    }
}
