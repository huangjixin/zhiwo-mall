package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.el.PaperQuesShare;
import com.fulan.application.mapper.ElPaperMapper;
import com.fulan.application.mapper.PaperQuesShareMapper;
import com.fulan.application.mapper.QuestionMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.PaperQuesShareService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
@Service
public class PaperQuesShareServiceImpl implements PaperQuesShareService {

	@Autowired
	private PaperQuesShareMapper paperQuesShareMapper;
	
	@Autowired
	private ElPaperMapper elPaperMapper;
	
	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
    private IdGenerator idGenerator;
	
	@Override
	@Transactional
	public Response<String> saveForManage(PaperQuesShare paperQuesShare) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题保存成功");
		int count = paperQuesShareMapper.insert(paperQuesShare);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题保存失败");
		}
		return response;
	}

	@Override
	@Transactional
	public Response<String> deleteForManage(Long id) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题删除成功");
		int count = paperQuesShareMapper.deleteById(id);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题删除失败");
		}
		return response;
	}

	@Override
	@Transactional
	public Response<String> deleteBatchIdsForManage(String ids) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题删除成功");
		List<Long> idList = new ArrayList<Long>();
		String[] idArr = ids.split(",");
		for(String id : idArr){
			idList.add(Long.valueOf(id));
		}
		int count = paperQuesShareMapper.deleteBatchIds(idList);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题删除失败");
		}
		return response;
	}

	@Override
	@Transactional
	public Response<String> updateForManage(PaperQuesShare paperQuesShare) {
		Response<String> response = new Response<String>(Response.SUCCESS,"试题更新成功");
		int count = paperQuesShareMapper.updateById(paperQuesShare);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("试题更新失败");
		}
		return response;
	}

	@Override
	public Response<List<PaperQuesShare>> listForManage(PaperQuesShare paperQuesShare) {
		//CollectionUtils.isNotEmpty
		Response<List<PaperQuesShare>> response = new Response<List<PaperQuesShare>>(Response.SUCCESS,"查询数据成功");
		EntityWrapper<PaperQuesShare> wrapper=new EntityWrapper<>(paperQuesShare);
		List<PaperQuesShare> list = paperQuesShareMapper.selectList(wrapper);
		if(CollectionUtils.isNotEmpty(list)){
			response.setData(list);
		}else{
			response.setCode(Response.ERROR);
			response.setMsg("查询数据失败");
		}
		return response;
	}

	@Override
	public Response<PaperQuesShare> seleByIdForManage(Long id) {
		Response<PaperQuesShare> response = new Response<PaperQuesShare>(Response.SUCCESS,"查询数据成功");
		PaperQuesShare paperQuesShare = paperQuesShareMapper.selectById(id);
		if(null != paperQuesShare){
			response.setData(paperQuesShare);
		}else{
			response.setCode(Response.ERROR);
			response.setMsg("查询数据失败");
		}
		return response;
	}

	@Override
	public Response<Integer> seleCountForManage(PaperQuesShare paperQuesShare) {
		Response<Integer> response = new Response<Integer>(Response.SUCCESS,"查询数据成功");
		int count = paperQuesShareMapper.updateById(paperQuesShare);
		response.setData(count);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("查询数据失败");
		}
		return response;
	}

	@Override
	public PageInfo<PaperQuesShare> listByPage(Page<PaperQuesShare> page, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 批量新增
	 */
	@Override
	@Transactional
	public Response<Integer> saveBatch(String hostIds, String type, String groupIds,String isShare) {
		Response<Integer> response = new Response<>(Response.SUCCESS,"保存成功");
		List<Long> hostIdList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		int count = 0;
		String[] hostIdArr = hostIds.split(",");
		List<Map<String,Object>> listMap = new ArrayList<>();
		String[] groupIdArr = groupIds.split(",");
		for (String hostId : hostIdArr) {
			PaperQuesShare paperQuesShare = new PaperQuesShare();
			EntityWrapper<PaperQuesShare> wrapper = new EntityWrapper<>(paperQuesShare);
			paperQuesShare.setHostId(Long.valueOf(hostId));
			List<PaperQuesShare> listP = paperQuesShareMapper.selectList(wrapper);
			if(CollectionUtils.isNotEmpty(listP)){
				count = paperQuesShareMapper.delete(wrapper);
				if(count == 0){
					response.setCode(Response.ERROR);
					response.setMsg("保存失败");
					return response;
				}
			}
			hostIdList.add(Long.valueOf(hostId));
			for(String groupId:groupIdArr){
				long id = idGenerator.generate();
				Map<String,Object> mapIG = new HashMap<>();
				mapIG.put("id", id);
				mapIG.put("hostId", hostId);
				mapIG.put("groupId", groupId);
				listMap.add(mapIG);
			}
		}
		if("1".equals(isShare)){
			map.put("type", type);
			map.put("listMap", listMap);
			map.put("gmtCreate", new Date());
			response.setData(paperQuesShareMapper.saveBatch(map));
		}
		if("1".equals(type)){
			questionMapper.updateByIdList(hostIdList, isShare);
		}else{
			elPaperMapper.updateByIdList(hostIdList, isShare);
		}
		return response;
	}
	/**
	 * 查询试题已分享的范围
	 */
	@Override
	public List<Long> selectShareQuestion(String id) {
		Long idl=null;
		if(null!=id && id!=""){
			idl=Long.valueOf(id);
		}
		List<Long> list=paperQuesShareMapper.selectShareQuestion(idl);
		return list;
	}
	/**
	 * 查询试卷已分享的范围
	 */
	@Override
	public List<Long> selectSharePaper(String id) {
		Long idl=null;
		if(null!=id && id!=""){
			idl=Long.valueOf(id);
		}
		List<Long> list=paperQuesShareMapper.selectSharePaper(idl);
		return list;
	}

}
