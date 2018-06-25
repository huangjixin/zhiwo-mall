package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.PaperQuesShare;
import com.fulan.api.paper.domain.el.Question;
import com.fulan.api.paper.domain.el.QuestionAnswer;
import com.fulan.api.paper.vo.QuestionVo;
import com.fulan.api.security.domain.Account;
import com.fulan.api.security.service.AccountService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.ElPaperMapper;
import com.fulan.application.mapper.PaperQuesShareMapper;
import com.fulan.application.mapper.QuestionAnswerMapper;
import com.fulan.application.mapper.QuestionMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.QuestionService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.listUtil.ListUtils;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService{
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private QuestionAnswerMapper questionAnswerMapper;
	
	@Autowired
	private PaperQuesShareMapper paperQuesShareMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ElPaperMapper elPaperMapper;

	/**
	 * 试题保存
	 * 接收Question
	 */
	@Override
	@Transactional
	public Response<String> saveForManage(QuestionVo questionVo) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题保存成功");
		Long id = Idfactory.generate();
		questionVo.setId(id);
		int count = questionMapper.saveQuestion(questionVo);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题保存失败"); 
		}else{
			for (QuestionAnswer qa : questionVo.getQuestionAnswer()) {
				qa.setId(Idfactory.generate());
				qa.setQuestionId(id);
				qa.setQuestionType(questionVo.getType());
				questionAnswerMapper.insert(qa);
			}
		}
		return response;
	}
	
	/**
	 * 根据id删除试题
	 */
	@Override
	@Transactional
	public Response<String> deleteForManage(Long id) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题删除成功");
		QuestionAnswer questionAnswer = new QuestionAnswer();
		questionAnswer.setQuestionId(id);
		EntityWrapper<QuestionAnswer> wrapper = new EntityWrapper<>(questionAnswer);
		int count =  questionAnswerMapper.delete(wrapper);
		PaperQuesShare paperQuesShare = new PaperQuesShare();
		paperQuesShare.setHostId(id);
		EntityWrapper<PaperQuesShare> wrapperPQS = new EntityWrapper<>(paperQuesShare);
		count = paperQuesShareMapper.delete(wrapperPQS);
		count = questionMapper.deleteById(id);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题删除失败");
		}
		return response;
	}

	/**
	 * 批量删除试题
	 */
	@Override
	@Transactional
	public Response<String> deleteBatchIdsForManage(String ids) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题删除成功");
		List<Long> idList = new ArrayList<Long>();
		String[] idArr = ids.split(",");
		for(String id : idArr){
			idList.add(Long.valueOf(id));
		}
		int count = questionAnswerMapper.deleBatchIds(idList);
		count = paperQuesShareMapper.deleBatchIds(idList);
		count = questionMapper.deleteBatchIds(idList);
		System.err.println("###############count:"+count);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题删除失败");
		}
		return response;
	}

	/**
	 * 试题更新
	 */
	@Override
	@Transactional
	public Response<String> updateForManage(QuestionVo questionVo) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题更新成功");
		questionMapper.updateQuestion(questionVo);
		questionAnswerMapper.deleAnswerById(questionVo.getId());
		
		for (QuestionAnswer qa : questionVo.getQuestionAnswer()) {
			questionAnswerMapper.insert(qa);
		}
		return response;
	}

	/**
	 * 查询试题列表
	 * 公共试题库   
	 * 私人试题库 
	 * pubType 管理员公共库/讲师私人库 1，讲师公共库2，	 
	 * submitter	管理员 登录 不传值 null
	 * 				讲师登录  传讲师的group_id 
	 */
	@Override
	public PageInfo<Question> listForManage(Page<Question> page,String type,String pubType,String submitter, String createUser,String content,
			  String groupId, String tagId,
			  int pageNo, int pageSize) {
		PageInfo<Question> pageInfo = new PageInfo<Question>();
		int total = questionMapper.listForManageCount(type,pubType,submitter,createUser,content,groupId,tagId);
		pageInfo.setRecords(questionMapper.listForManage(page,type,pubType,submitter,createUser,content,groupId,tagId, pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	/**
	 * 根据Id查询试题
	 */
	@Override
	public Response<Question> seleByIdForManage(Long id) {
		Response<Question> response = new Response<Question>(Response.SUCCESS,"查询数据成功");
		Question question = this.selectById(id);
		if(null != question){
			response.setData(question);
		}else{
			response.setCode(Response.ERROR);
			response.setMsg("查询数据失败");
		}
		return response;
	}

	/**
	 * 记录数
	 */
	@Override
	public Response<Integer> seleCountForManage(Question question) {
		Response<Integer> response = new Response<Integer>(Response.SUCCESS,"查询数据成功");
		int count = questionMapper.updateById(question);
		response.setData(count);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("查询数据失败");
		}
		return response;
	}

	@Override
	public Response<List<Question>> seleRandomForManage(List<Map<String,Object>> mapList) {
		Response<List<Question>> response = new Response<>();
		List<Question> questionList = new ArrayList<>();
		for (Map<String,Object> map : mapList) {
			questionList.addAll(questionMapper.seleRandomForManage(map));
		}
		response.setCode(Response.SUCCESS);
		response.setMsg("访问数据成功");
		response.setData(questionList);
		return response;
	}

	@Override
	public QuestionVo questionAndAnswer(String id) {
		return questionMapper.questionAndAnswer(id);
	}

	@Override
	@Transactional
	public Response<String> deleById(Long id) {
		Response<String> response = new Response<>(Response.SUCCESS,"删除数据成功");
		int count = questionMapper.deleById(id);
		if(count>0){
			return response;
		}
		response.setCode(Response.ERROR);
		response.setMsg("删除数据失败");
		
		return response;
	}

	@Override
	public List<Map<String, String>> isNotRelation(String ids) {
		List<Long> idList = new ArrayList<Long>();
		String[] idArr = ids.split(",");
		for(String id : idArr){
			idList.add(Long.valueOf(id));
		}
		return questionMapper.isNotRelation(idList);
	}

	@Override
	public List<QuestionVo> seleByIdList(List<Long> idList) {
		List<QuestionVo> questionVo = questionMapper.seleByIdList(idList);
		return questionVo;
	}

	@Override
	public List<Question> listForOtherManage(String type, String tagId,String keyWord,String createUser) {
		
		Account account = null;
    	if(StringUtils.isNotEmpty(createUser)){
    		account = accountService.findByName(createUser);
    	}
    	Long userId = null;
    	if(null != account){
    		userId = account.getId();
    	}
    	if(userId!=null){
    		createUser = userId.toString();
    	}
		return questionMapper.listForOtherManage(type, tagId,keyWord,createUser);
	}

	@Override
	public List<String> selectPaperName(String ids) {
		List<Long> idList = new ArrayList<Long>();
		String[] idArr = ids.split(",");
		for(String id : idArr){
			idList.add(Long.valueOf(id));
		}
		
		return questionMapper.selectPaperName(idList);
	}

	@Override
	@Transactional
	public boolean shareQuestion(String[] groupIds, String[] questionIds) {
		try{
			List<String> mlis = Arrays.asList(questionIds);
			List<String> lis =	Arrays.asList(groupIds);
			if(!ListUtils.isEmpty(mlis)){
				for (String string : mlis) {
					elPaperMapper.deleteShar(string);
					Question question = new Question();
					question.setId(Long.valueOf(string));
					question.setIsShare(CommenConstant.VALUE_NO);
					questionMapper.updateById(question);
					if(!ListUtils.isEmpty(lis)){
						for (String strings : lis) {
							Map<String,String> parms = new HashMap<String,String>();
							parms.put("id", Idfactory.generate()+"");
							parms.put("hostId", string);
							parms.put("groupId", strings);
							parms.put("type", "1");
							elPaperMapper.shareOther(parms);
						}
						question.setIsShare(CommenConstant.VALUE_YES);
						questionMapper.updateById(question);
					}
				}
				return true;
			}
		return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	
	
}
