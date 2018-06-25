package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.domain.el.PaperQuestion;
import com.fulan.api.paper.domain.el.UserExam;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.paper.vo.PaperPlanNameVo;
import com.fulan.api.paper.vo.PaperQuestionVo;
import com.fulan.api.security.service.AccountService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.ElPaperMapper;
import com.fulan.application.mapper.PaperQuestionMapper;
import com.fulan.application.mapper.UserExamMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.ElPaperService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.listUtil.ListUtils;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

@Service
public class ElPaperServiceImpl extends ServiceImpl<ElPaperMapper, Paper> implements ElPaperService {
	

	@Autowired
	private ElPaperMapper elPaperMapper;
	
	@Autowired
	private PaperQuestionMapper paperQuestionMapper;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private UserExamMapper userExamMapper;
	
	/*@Override
	public Response<String> saveForManage(Paper paper,PaperQuestion[] paperQuestion) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试卷保存成功");
		GenerateVOUtil.generate(paper);
		Long id = paper.getId();
		int count = elPaperMapper.insert(paper);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试卷保存失败");
		}else{
			for (PaperQuestion pq : paperQuestion) {
				GenerateVOUtil.generate(pq);
				pq.setPaperId(Long.valueOf(id));
				paperQuestionMapper.insert(pq);
			}
		}
		return response;
	}*/
	@Override
	@Transactional
	public Response<String> saveForManage(PaperQuestionVo paperQuestionVo) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试卷保存成功");
		/*GenerateVOUtil.generate(paperQuestionVo.getPaper());*/
		paperQuestionVo.getPaper().setId(Idfactory.generate());
		/*Long id = paperQuestionVo.getPaper().getId();*/
		Long id =paperQuestionVo.getPaper().getId();
		int count = elPaperMapper.insert(paperQuestionVo.getPaper());
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试卷保存失败");
		}else{
			if(null!=paperQuestionVo.getPaperQuestionList() ){
				for(PaperQuestion paperQuestion :paperQuestionVo.getPaperQuestionList()){
					if(null != paperQuestion){
						GenerateVOUtil.generate(paperQuestion);
						paperQuestion.setPaperId(id);
						paperQuestionMapper.insert(paperQuestion);
					}
					
				}
			}
			
		}
		return response;
	}

	@Override
	@Transactional
	public Response<String> deleteForManage(Long id) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试卷删除成功");
		PaperQuestion paperQuestion = new PaperQuestion();
		paperQuestion.setPaperId(id);
		EntityWrapper<PaperQuestion> wrapper = new EntityWrapper<>(paperQuestion);
		int count = paperQuestionMapper.selectCount(wrapper);
		if(count>0){
			paperQuestionMapper.delete(wrapper);
		}
		count = elPaperMapper.deleteById(id);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试卷删除失败");
		}
		return response;
	}

	@Override
	@Transactional
	public Response<String> deleteBatchIdsForManage(String ids) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试卷删除成功");
		List<Long> idList = new ArrayList<Long>();
		String[] idArr = ids.split(",");
		for(String id : idArr){
			idList.add(Long.valueOf(id));
		}
		
		int count = elPaperMapper.deleBatchRelation(idList);
		count = elPaperMapper.deleBatchRelationEPQS(idList);
		count = elPaperMapper.deleteBatchIds(idList);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试卷删除失败");
		}
		return response;
	}

	@Override
	@Transactional
	public Response<String> updateForManage(PaperQuestionVo paperQuestionVo) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试卷更新成功");
		int count = elPaperMapper.updateById(paperQuestionVo.getPaper());
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试卷更新失败");
		}else{
			PaperQuestion paq = new PaperQuestion();
			paq.setPaperId(paperQuestionVo.getPaper().getId());
			EntityWrapper<PaperQuestion> wrapper = new EntityWrapper<>(paq);
			count = paperQuestionMapper.selectCount(wrapper);
			if(count>0){
				paperQuestionMapper.delete(wrapper);
			}
			if(null!=paperQuestionVo.getPaperQuestionList() ){
			for (PaperQuestion pq : paperQuestionVo.getPaperQuestionList()) {
				if(null != pq){
				GenerateVOUtil.generate(pq);
				pq.setPaperId(paperQuestionVo.getPaper().getId());
				paperQuestionMapper.insert(pq);
				}
			}
			}
		}
		return response;
	}

	@Override
	public PageInfo<ElPaperVo> listForManage(Page<ElPaperVo> page, String type, String pubType, String submitter,
			String createUser, String name, String groupId, String tagId, int pageNo, int pageSize) {
		PageInfo<ElPaperVo> pageInfo = new PageInfo<ElPaperVo>();
		int total = elPaperMapper.listForManageCount(type,pubType,submitter,createUser,name,groupId,tagId);
		pageInfo.setRecords(elPaperMapper.listForManage(page,type,pubType,submitter,createUser,name,groupId,tagId, pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	public Response<Paper> seleByIdForManage(Long id) {
		Response<Paper> response = new Response<Paper>(Response.SUCCESS,"查询数据成功");
		Paper paper = this.selectById(id);
		if(null != paper){
			response.setData(paper);
		}else{
			response.setCode(Response.ERROR);
			response.setMsg("查询数据失败");
		}
		return response;
	}

	@Override
	public PageInfo<ElPaperVo> waitForReview(Page<ElPaperVo> page, String name, String paperState, String userName,
			String groupId, String tagId, int pageNo, int pageSize) {
	    Map<String,Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("name", name);
	    paramMap.put("paperState", paperState);
	    paramMap.put("userName", userName);
	    paramMap.put("groupId", groupId);
	    paramMap.put("tagId", tagId);
		PageInfo<ElPaperVo> pageInfo = new PageInfo<ElPaperVo>();
		int total = elPaperMapper.listPaperCount(paramMap);
		pageInfo.setRecords(elPaperMapper.listPaperByPage(page,paramMap));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	public List<ElPaperVo> seleByIdVo(String id) {
		return elPaperMapper.seleByIdVo(id);
	}

	@Override
	public Response<String> saveUEU(List<UserExam> list){
		Response<String> response = new Response<String>(Response.SUCCESS,"更新数据成功");
		int count = 0;
		for(UserExam userExam : list){
			count = userExamMapper.updateById(userExam);
		}
		if(count>0){
			UserExam userExam = new UserExam();
			userExam.setPaperId(list.get(0).getPaperId());
			userExam.setExamNum(null);
			EntityWrapper<UserExam> wrapper = new EntityWrapper<>(userExam);
			UserExam ue = new UserExam();
			ue.setPaperState(1);
			count = userExamMapper.update(ue, wrapper);
		}
		return response;
	}

	@Override
	public Response<List<Paper>> listObject(Paper paper) {
		Response<List<Paper>> response = new Response<>(Response.SUCCESS,"查询数据成功");
		EntityWrapper<Paper> wrapper = new EntityWrapper<>(paper);
		List<Paper> list = elPaperMapper.selectList(wrapper);
		response.setData(list);
		return response;
	}
	
	@Override
	public List<Paper> paperList() {
		return elPaperMapper.selectList(null);
	}

	@Override
	public List<ElPaperVo> seleByIdPublic(String id) {
		return elPaperMapper.seleByIdPublic(id);
	}
	@Override
	public ElPaperVo seleByIdWaitFor(String id,String userId,String examNum,String planCourseId) {
		return elPaperMapper.seleByIdWaitForManage(id,userId,examNum ,planCourseId);
	}

	@Override
	public List<PaperManageMinVo> seleBycId(String cId) {
		return elPaperMapper.selectBycId(Long.parseLong(cId));
	}

	@Override
	public Response<List<Paper>> seleByGroupId(Integer submitter ,String name, Integer type) {
		Response<List<Paper>> response = new Response<>(Response.SUCCESS,"查询数据成功");
		response.setData(elPaperMapper.seleByGroupId(submitter ,name, type));
		return response;
	}

	@Override
	public List<PaperPlanNameVo> selectPlanName(String ids) {
		List<Long> idList = new ArrayList<Long>();
		String[] idArr = ids.split(",");
		for(String id : idArr){
			idList.add(Long.valueOf(id));
		}
		return elPaperMapper.selectPlanName(idList);
	}

	@Override
	@Transactional
	public boolean sharePaper(String[] groupIds, String[] paperIds) {
		try{
			List<String> mlis = Arrays.asList(paperIds);
			List<String> lis =	Arrays.asList(groupIds);
			if(!ListUtils.isEmpty(mlis)){
				for (String string : mlis) {
					elPaperMapper.deleteShar(string);
					Paper paper = new Paper();
					paper.setId(Long.valueOf(string));
					paper.setIsShare(CommenConstant.VALUE_NO);
					elPaperMapper.updateById(paper);
					if(!ListUtils.isEmpty(lis)){
						for (String strings : lis) {
							Map<String,String> parms = new HashMap<String,String>();
							parms.put("id", Idfactory.generate()+"");
							parms.put("hostId", string);
							parms.put("groupId", strings);
							parms.put("type", "2");
							elPaperMapper.shareOther(parms);
						}
						paper.setIsShare(CommenConstant.VALUE_YES);
						elPaperMapper.updateById(paper);
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
