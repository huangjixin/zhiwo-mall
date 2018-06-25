package com.fulan.application.service.impl;

import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.vo.*;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.api.system.domain.Organization;
import com.fulan.api.system.manage.ManageOrganizationService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.ElPaperMapper;
import com.fulan.application.mapper.PaperExamMapper;
import com.fulan.application.mapper.QuestionMapper;
import com.fulan.application.mapper.UserExamMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.PaperExamService;
import com.fulan.application.util.spring.SpringUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * yangzexu
 */
@Service
public class PaperExamServiceImpl implements PaperExamService{


    private Logger logger = LoggerFactory.getLogger(PaperExamServiceImpl.class);

    @Autowired
    private PaperExamMapper paperExamMapper;

    @Autowired
    private UserExamMapper userExamMapper;

    @Autowired
    private IdGenerator idGenerator;

    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
	private ElPaperMapper elPaperMapper;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private ManageOrganizationService manageOrganizationService;
    
    @Autowired
	private QuestionMapper questionMapper;

    @Override
    public ExamPaperVo getPaperExam(Long paperId) {
        logger.info("-----------------------获取试卷-------------------id："+paperId);
        ExamPaperVo vo = paperExamMapper.getPaperExam(paperId);
        List<QuestionTopVo> questionTopVos = new ArrayList<>();
        List<QuestionVo> singleChoiceVos = new ArrayList<>();
        List<QuestionVo> multipleChoiceVos = new ArrayList<>();
        List<QuestionVo> judgeVos = new ArrayList<>();
        List<QuestionVo> shortAnswers = new ArrayList<>();
        int singleScore = 0;
        int multipleScore = 0;
        int judgeScore = 0;
        int shortScore = 0;
        int singleNum = 0;
        int multipleNum = 0;
        int judgeNum = 0;
        int shortNum = 0;
       if(vo != null){
           for (QuestionVo questionVo :vo.getQuestions() ) {
               if(questionVo.getType().equals(CommenConstant.QUESTION_TYPE_0)){
                   singleScore += Integer.parseInt(questionVo.getQuestionScore());
                   ++singleNum;
                   singleChoiceVos.add(questionVo);
               }else if(questionVo.getType().equals(CommenConstant.QUESTION_TYPE_1)){
                   multipleScore += Integer.parseInt(questionVo.getQuestionScore());
                   multipleChoiceVos.add(questionVo);
                   ++multipleNum;
               }else if(questionVo.getType().equals(CommenConstant.QUESTION_TYPE_2)){
                   judgeScore += Integer.parseInt(questionVo.getQuestionScore());
                   judgeVos.add(questionVo);
                   ++judgeNum;
               }else if(questionVo.getType().equals(CommenConstant.QUESTION_TYPE_3)){
                   shortScore += Integer.parseInt(questionVo.getQuestionScore());
                   shortAnswers.add(questionVo);
                   ++shortNum;
               }
           }
           QuestionTopVo singleVo = new QuestionTopVo();
           QuestionTopVo multipleVo = new QuestionTopVo();
           QuestionTopVo judgeVo = new QuestionTopVo();
           QuestionTopVo shortVo = new QuestionTopVo();
           if(singleChoiceVos.size() > 0){
               singleVo.setQuestionType(CommenConstant.QUESTION_TYPE_0);
               singleVo.setQuestionTitle("单选题");
               singleVo.setQuestionTotalNum(singleNum);
               singleVo.setQuestionTotalScore(singleScore);
               singleVo.setQuestions(singleChoiceVos);
               questionTopVos.add(singleVo);
           }
           if(multipleChoiceVos.size() > 0){
               multipleVo.setQuestionType(CommenConstant.QUESTION_TYPE_1);
               multipleVo.setQuestionTitle("多选题");
               multipleVo.setQuestionTotalNum(multipleNum);
               multipleVo.setQuestionTotalScore(multipleScore);
               multipleVo.setQuestions(multipleChoiceVos);
               questionTopVos.add(multipleVo);
           }
           if(judgeVos.size() > 0){
               judgeVo.setQuestionType(CommenConstant.QUESTION_TYPE_2);
               judgeVo.setQuestionTitle("判断题");
               judgeVo.setQuestionTotalNum(judgeNum);
               judgeVo.setQuestionTotalScore(judgeScore);
               judgeVo.setQuestions(judgeVos);
               questionTopVos.add(judgeVo);
           }
           if(shortAnswers.size() > 0){
               shortVo.setQuestionType(CommenConstant.QUESTION_TYPE_3);
               shortVo.setQuestionTitle("简答题");
               shortVo.setQuestionTotalNum(shortNum);
               shortVo.setQuestionTotalScore(shortScore);
               shortVo.setQuestions(shortAnswers);
               questionTopVos.add(shortVo);
           }
           vo.setQuestions(null);
           vo.setQuestionTopVos(questionTopVos);
       }
        return vo;
    }

    @Override
    public ExamPaperVo submit(QuestionDto question) {
        logger.info("-----------------------提交试卷------------------------");
        ExamPaperVo vo = paperExamMapper.getPaperExam(question.getPaperId());
        Map<String,Object> map = new HashMap<>();
        Long id = (Long) redisUtil.getUserId();
        map.put("accountId",id);
        map.put("planCourseId",question.getPlanCourseId());
        //获取用户的测试次数
        Integer maxExamNum = paperExamMapper.getMaxExamNum(map);
        if(maxExamNum != null && maxExamNum>0){
            saveAndCalcExam(vo,question,maxExamNum+1);
        }else{
            saveAndCalcExam(vo,question,1);
        }
        return vo;
    }


    @Override
    public ExamPaperVo getAnalysis(Long paperId,Long planCourseId) {
        logger.info("-----------------------获取答案解析------------------------");
        Map<String,Object> map = new HashMap<>();
        map.put("paperId",paperId);
        map.put("planCourseId",planCourseId);
        Long id = (Long) redisUtil.getUserId();
        map.put("accountId",id);
        ExamPaperVo vo = getPaperExam(paperId);
        Integer examNum = paperExamMapper.getExamNum(map);
        vo.setIsMaxExam(examNum);
        Integer score = 0 ;
        for (QuestionTopVo topVo :vo.getQuestionTopVos() ) {
            if(topVo.getQuestions() != null && topVo.getQuestions().size() > 0){
                    for(QuestionVo question : topVo.getQuestions()){
                        map.put("questionId",question.getId());
                        //试卷解析对象
                        ExamPaperVo analysisVo = paperExamMapper.getAnalysis(map);
                        //测试剩余次数
                        vo.setTestNum(analysisVo.getTestNum());
                        if(analysisVo.getQuestions() != null && analysisVo.getQuestions().size() > 0){
                            question.setAnalysis(analysisVo.getQuestions().get(0).getAnalysis());
                            question.setUserAnswer(analysisVo.getQuestions().get(0).getUserAnswer());
                        }
                        if(topVo.getQuestionType().equals(CommenConstant.QUESTION_TYPE_0)){
                            List<QuestionAnswer> singleList = new ArrayList<>();
                            for (QuestionAnswer questionAnswer : question.getQuestionAnswer()) {
                                if(questionAnswer.getIsRight().equals(1)){
                                    singleList.add(questionAnswer);
                                }
                            }
                            question.setQuestionAnswer(null);
                            question.setQuestionAnswer(singleList);
                            //用户单选得分
                            if(question.getUserAnswer() != null && question.getUserAnswer().size()>0){
                                QuestionAnswerVo qav = question.getUserAnswer().get(0);
                                vo.setSingleUserScore(vo.getSingleUserScore()+qav.getScore());
                                if(qav.getScore() > 0){
                                    question.setIsRight(CommenConstant.ANSWER_RIGHT);
                                }else{
                                    question.setIsRight(CommenConstant.ANSWER_NOT_RIGHT);
                                }
                            }else{
                                question.setIsRight(CommenConstant.ANSWER_NOT_RIGHT);
                            }
                        }else if(topVo.getQuestionType().equals(CommenConstant.QUESTION_TYPE_1)){

                            List<QuestionAnswer> singleList = new ArrayList<>();
                            for (QuestionAnswer questionAnswer : question.getQuestionAnswer()) {
                                if(questionAnswer.getIsRight().equals(1)){
                                    singleList.add(questionAnswer);
                                }
                            }
                            question.setQuestionAnswer(null);
                            question.setQuestionAnswer(singleList);
                            //用户多选得分
                            if(question.getUserAnswer() != null && question.getUserAnswer().size()>0){
                                QuestionAnswerVo qav = question.getUserAnswer().get(0);
                                vo.setMultipleUserScore(vo.getMultipleUserScore()+qav.getScore());
                                if(qav.getScore() > 0){
                                    question.setIsRight(CommenConstant.ANSWER_RIGHT);
                                }else{
                                    question.setIsRight(CommenConstant.ANSWER_NOT_RIGHT);
                                }
                            }else{
                                question.setIsRight(CommenConstant.ANSWER_NOT_RIGHT);
                            }
                        }else if(topVo.getQuestionType().equals(CommenConstant.QUESTION_TYPE_2)){
                            //用户判断得分
                            if(question.getUserAnswer() != null && question.getUserAnswer().size()>0){
                                QuestionAnswerVo qav = question.getUserAnswer().get(0);
                                vo.setJudgeUserScore(vo.getJudgeUserScore()+qav.getScore());
                                if(qav.getScore() > 0){
                                    question.setIsRight(CommenConstant.ANSWER_RIGHT);
                                }else{
                                    question.setIsRight(CommenConstant.ANSWER_NOT_RIGHT);
                                }
                            }else{
                                question.setIsRight(CommenConstant.ANSWER_NOT_RIGHT);
                            }
                        }else if(topVo.getQuestionType().equals(CommenConstant.QUESTION_TYPE_3)){
                            //用户简答得分
                            if(question.getUserAnswer() != null && question.getUserAnswer().size()>0){
                                QuestionAnswerVo qav = question.getUserAnswer().get(0);
                                if(qav.getScore() != null){
                                    vo.setShortUserScore(vo.getShortUserScore()+qav.getScore());
                                    if(qav.getScore() > 0){
                                        question.setIsRight(CommenConstant.ANSWER_RIGHT);
                                    }else{
                                        question.setIsRight(CommenConstant.ANSWER_NOT_RIGHT);
                                    }
                                }else{
                                    vo.setShortUserScore(null);
                                }
                            }
                        }
                    }
            }

        }
        if(vo != null){
            if(vo.getShortUserScore() != null){
                score =  vo.getShortUserScore() + vo.getSingleUserScore() + vo.getJudgeUserScore() + vo.getMultipleUserScore();
            }else{
                score =  vo.getSingleUserScore() + vo.getJudgeUserScore() + vo.getMultipleUserScore();
            }
            vo.setUserScore(score);
            if(vo.getPassMark() <= score){
                vo.setIsPassExam(CommenConstant.EXAM_PASS);
            }else{
                vo.setIsPassExam(CommenConstant.EXAM_FAILED);
            }
        }


        return vo;
    }


    public void saveAndCalcExam(ExamPaperVo vo,QuestionDto question,int examNum){
        UserExam userExam = new UserExam();
        int score=0;//得分   {'3':'','7':''}
        userExam.setPaperId(question.getPaperId());
        userExam.setPlanCourseId(question.getPlanCourseId());
        //已阅卷
        if(vo.getIsArtificialMark() != null && vo.getIsArtificialMark() == 1){
            userExam.setPaperState(CommenConstant.NOT_ARTIFICIAL_MARK);
        }else{
            userExam.setPaperState(CommenConstant.ARTIFICIAL_MARK);
        }
        Account account = (Account) redisUtil.getUserInfo();
        //用户ID
        userExam.setUserId(account.getId());
        //用户名称
        userExam.setUserName(account.getAccountName());
        Map<Long,Object> answerMap =  getAnswser(vo.getQuestions());
        Map<Long,Object> questionMap =  getQuestion(vo.getQuestions());
        //单选题
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(question.getSingleChoiceVos()) && !question.getSingleChoiceVos().equals("{}")){
            //每道题的答案
            String[] singles = question.getSingleChoiceVos().split(",");
            for (String single :singles) {
                if(StringUtils.isNotEmpty(single)){
                    userExam.setId(idGenerator.generate());
                    String[] quesAnswer = single.split(":");
                    //问题ID
                    String questionId = quesAnswer[0].split("\'")[1];
                    //对应问题答案 --用户填写
                    String[] answerStr =quesAnswer[1].split("\'");
                    String userAnswer ;
                    if(answerStr.length == 0){
                        userAnswer = "";
                    }else{
                        userAnswer = answerStr[1];
                    }
                    //问题ID
                    userExam.setQuestionId(Long.parseLong(questionId));
                    //单选题
                    userExam.setQuestionType(CommenConstant.QUESTION_TYPE_0);
                    //问题对象
                    QuestionVo ques = (QuestionVo) questionMap.get(Long.parseLong(questionId));
                    for(QuestionAnswer quesAns : ques.getQuestionAnswer()){
                        if(quesAns.getAnswer().equals(userAnswer)){
                            //用户选择答案的答案ID
                            userExam.setAnswerId(quesAns.getId());
                            //排序
                            userExam.setAnswerSeq(quesAns.getSeq());
                            break;
                        }else{
                            //用户选择答案的答案ID
                            userExam.setAnswerId(null);
                            //排序
                            userExam.setAnswerSeq(null);
                        }
                    }
                    //问题分数
                    userExam.setQuestionScore(Integer.parseInt(ques.getQuestionScore()));
                    //用户答案
                    userExam.setAnswer(userAnswer);
                    if(answerMap.get(Long.parseLong(questionId)) != null && userAnswer.equals(answerMap.get(Long.parseLong(questionId)).toString().trim())){
                        score  += Integer.parseInt(ques.getQuestionScore());
                        userExam.setScore(Integer.parseInt(ques.getQuestionScore()));
                    }else{
                        userExam.setScore(0);
                    }
                    userExam.setExamNum(examNum);
                    userExam.setGmtCreate(new Date());
                    userExamMapper.insert(userExam);
                }
            }
        }
        //多选题
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(question.getMultipleChoiceVos())  && !question.getMultipleChoiceVos().equals("{}")){
            //每道题的答案
            String[] multiples = question.getMultipleChoiceVos().split(",");
            for (String multiple :multiples) {
                if (StringUtils.isNotEmpty(multiple)) {
                    String[] quesAnswer = multiple.split(":");
                    //问题ID
                    String questionId = quesAnswer[0].split("\'")[1];
                    //对应问题答案 --用户填写
                    String[] answerStr = quesAnswer[1].split("\'");
                    String userAnswer;
                    if (answerStr.length == 0) {
                        userAnswer = "";
                    } else {
                        userAnswer = answerStr[1];
                    }
                    //问题ID
                    userExam.setQuestionId(Long.parseLong(questionId));
                    //多选题
                    userExam.setQuestionType(CommenConstant.QUESTION_TYPE_1);
                    //问题对象
                    QuestionVo ques = (QuestionVo) questionMap.get(Long.parseLong(questionId));
                    //问题分数
                    userExam.setQuestionScore(Integer.parseInt(ques.getQuestionScore()));
                    //获取用户选择的每一个答案
                    String[] answers = userAnswer.split("_");
                    String result = answerMap.get(Long.parseLong(questionId)).toString();
                    //数据库答案集合
                    String[] ansResult = result.split(",");
                    //入库操作
                    userExam.setId(idGenerator.generate());
                    //用户答案
                    userExam.setAnswer(userAnswer.replaceAll("_",","));
                    userExam.setExamNum(examNum);
                    userExam.setGmtCreate(new Date());
                    //判断是否正确
                    Arrays.sort(answers);
                    Arrays.sort(ansResult);
                    if (Arrays.deepEquals(answers,ansResult)) {
                            score += Integer.parseInt(ques.getQuestionScore());
                            userExam.setScore(Integer.parseInt(ques.getQuestionScore()));
                    } else {
                        userExam.setScore(0);
                    }
                    //用户选择答案的答案ID
                    userExam.setAnswerId(null);
                    //排序
                    userExam.setAnswerSeq(null);
                    userExamMapper.insert(userExam);
                }
            }
        }
        //判断题
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(question.getJudgeVos())   && !question.getJudgeVos().equals("{}")){
            //每道题的答案
            String[] judges = question.getJudgeVos().split(",");
            for (String judge :judges) {
                if (StringUtils.isNotEmpty(judge)) {
                    userExam.setId(idGenerator.generate());
                    String[] quesAnswer = judge.split(":");
                    //问题ID
                    String questionId = quesAnswer[0].split("\'")[1];
                    //对应问题答案 --用户填写
                    String[] answerStr = quesAnswer[1].split("\'");
                    String userAnswer;
                    if (answerStr.length == 0) {
                        userAnswer = "";
                    } else {
                        userAnswer = answerStr[1];
                    }
                    //问题ID
                    userExam.setQuestionId(Long.parseLong(questionId));
                    //判断题
                    userExam.setQuestionType(CommenConstant.QUESTION_TYPE_2);
                    //问题对象
                    QuestionVo ques = (QuestionVo) questionMap.get(Long.parseLong(questionId));
                    for (QuestionAnswer quesAns : ques.getQuestionAnswer()) {
                        if (quesAns.getAnswer()!=null&&quesAns.getAnswer().equals(userAnswer)) {
                            //用户选择答案的答案ID
                            userExam.setAnswerId(quesAns.getId());
                            //排序
                            userExam.setAnswerSeq(quesAns.getSeq());
                            break;
                        }else{
                            //用户选择答案的答案ID
                            userExam.setAnswerId(null);
                            //排序
                            userExam.setAnswerSeq(null);
                        }
                    }
                    //问题分数
                    userExam.setQuestionScore(Integer.parseInt(ques.getQuestionScore()));
                    //用户答案
                    userExam.setAnswer(userAnswer);
                    if (answerMap.get(Long.parseLong(questionId)) != null && userAnswer.equals(answerMap.get(Long.parseLong(questionId)).toString().trim())) {
                        score += Integer.parseInt(ques.getQuestionScore());
                        userExam.setScore(Integer.parseInt(ques.getQuestionScore()));
                    } else {
                        userExam.setScore(0);
                    }
                    userExam.setExamNum(examNum);
                    userExam.setGmtCreate(new Date());
                    userExamMapper.insert(userExam);
                }
            }
        }
        //简答题
        if(question.getShortList() != null && question.getShortList().size() > 0){
            //每道题的答案
            for (ShortAnswerVo shortVo :question.getShortList()) {
                userExam.setId(idGenerator.generate());
                //问题ID
                String questionId = shortVo.getId();
                //对应问题答案 --用户填写
                String userAnswer = shortVo.getAnswer();
                //问题ID
                userExam.setQuestionId(Long.parseLong(questionId));
                //单选题
                userExam.setQuestionType(CommenConstant.QUESTION_TYPE_3);
                //问题对象
                QuestionVo ques = (QuestionVo) questionMap.get(Long.parseLong(questionId));
                for(QuestionAnswer quesAns : ques.getQuestionAnswer()){
                    if(quesAns.getAnswer()!=null&&quesAns.getAnswer().equals(userAnswer)){
                        //用户选择答案的答案ID
                        userExam.setAnswerId(quesAns.getId());
                        //排序
                        userExam.setAnswerSeq(quesAns.getSeq());
                        break;
                    }else{
                        //用户选择答案的答案ID
                        userExam.setAnswerId(null);
                        //排序
                        userExam.setAnswerSeq(null);
                    }
                }
                //问题分数
                userExam.setQuestionScore(Integer.parseInt(ques.getQuestionScore()));
                //用户答案
                userExam.setAnswer(userAnswer);
//                if(userAnswer.equals(answerMap.get(Long.parseLong(questionId)).toString().trim())){
//                    score  += Integer.parseInt(ques.getQuestionScore());
//                    //0：自动阅卷   1：人工阅卷
//                    if(vo.getIsArtificialMark().equals(0)){
//                        userExam.setScore(Integer.parseInt(ques.getQuestionScore()));
//                    }else{
//                        userExam.setScore(0);
//                    }
//                }else{
//
//                }
                userExam.setScore(null);
                userExam.setExamNum(examNum);
                userExam.setGmtCreate(new Date());
                userExamMapper.insert(userExam);
            }
        }
    }

    /**
     * 获取考题
     * @param questionVos
     * @return
     */
    private Map<Long,Object> getQuestion(List<QuestionVo> questionVos){
        logger.info("-----------------------获取考题，封装到map------------------------");
        Map<Long,Object> map = new HashMap<>();
        for (QuestionVo vo : questionVos) {
            map.put(vo.getId(),vo);
        }
        return map;
    }

    /**
     * 获取考题答案
     * @param questionVos
     * @return
     */
    private Map<Long,Object> getAnswser(List<QuestionVo> questionVos){
        logger.info("-----------------------获取考题答案，封装到map------------------------");
        Map<Long,Object> map = new HashMap<>();
        for (QuestionVo vo: questionVos) {
            if(vo.getType().equals(CommenConstant.QUESTION_TYPE_0)){//单选题
                //遍历该问题 下所有答案
                for (QuestionAnswer answer : vo.getQuestionAnswer()) {
                    if(answer.getIsRight().equals(1)){//正确答案
                        map.put(vo.getId(),answer.getAnswer());
                    }
                }
            }else if(vo.getType().equals(CommenConstant.QUESTION_TYPE_1)){//多选题
                String str = "";
                int i=0;
                for (QuestionAnswer answer : vo.getQuestionAnswer()) {
                    if(answer.getIsRight().equals(1)){//正确答案
                        if(i == 0){
                            str = answer.getOption()+"."+answer.getAnswer();
                        }else{
                            str = str+","+answer.getOption()+"."+answer.getAnswer();
                        }
                       i++;
                    }
                }
                map.put(vo.getId(),str);
            }else if(vo.getType().equals(CommenConstant.QUESTION_TYPE_2) || vo.getType().equals(CommenConstant.QUESTION_TYPE_3)){//判断题  问答提
                for (QuestionAnswer answer : vo.getQuestionAnswer()) {
                    if(answer.getIsRight()!=null&&answer.getIsRight().equals(1)){//正确答案
                        map.put(vo.getId(),answer.getAnswer());
                    }
                }
            }
        }
        return map;
    }
    
    
    
	@Override
	public List<ExamAccountVo> getExamAccountVo(String userName,String questionType,String id) {
		 List<ExamAccountVo> uList = paperExamMapper.selectExamAccount(userName, questionType, id);
		 for(ExamAccountVo eVo : uList){
			 Paper paper = elPaperMapper.selectById(eVo.getPaperId());
			 if(null !=paper){
				int passMark=  paper.getPassMark();
				if(eVo.getScore()>=passMark){
					eVo.setPass("0");      //通过
				}else{
					eVo.setPass("1");      //不通过
				}
			 }
			 Account account = accountService.findByIdForManage( eVo.getUserId());
			 if(null!=account){
				 if(null != account.getPostType()){
					 eVo.setPostType(account.getPostType().toString());
					 String companyId = account.getCompanyId();
					 Organization   organization =null; 
					 if(null !=companyId && companyId !=""){
						 organization=  manageOrganizationService.getOrganizationById(companyId);
					 }
					
				     if(null!=organization){
				    	 eVo.setCnName(organization.getCode());
				     }
				 }
			 }
		 }
		return uList;
	}


	@Override
	public List<ExamAccountVo> getExamAccountOtherVo(String userName) {
		 List<ExamAccountVo> uList = paperExamMapper.selectExamAccountSearch(userName);
		 for(ExamAccountVo eVo : uList){
			 Paper paper = elPaperMapper.selectById(eVo.getPaperId());
			 Question question = questionMapper.selectById(eVo.getQuestionId());
			 if(null!=question){
				 eVo.setContent(question.getContent());
			 }
			 if(null !=paper){
				int passMark=  paper.getPassMark();
				if(eVo.getScore()>=passMark){
					eVo.setPass("0");      //通过
					eVo.setPassMark(passMark);
				}else{
					eVo.setPass("1");      //不通过
					eVo.setPassMark(passMark);
				}
				eVo.setFullMark(paper.getFullMark());
				eVo.setName(paper.getName());
			 }
			 Account account = accountService.findByIdForManage( eVo.getUserId());
			 if(null!=account){
				 eVo.setPostType(account.getPostType().toString());
				 Organization  organization=  manageOrganizationService.getOrganizationById(account.getCompanyId());
			     if(null!=organization){
			    	 eVo.setCnName(organization.getCode());
			     }
			 }
		 }
		return uList;
	}


}
