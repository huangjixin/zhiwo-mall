package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.information.domain.MessageDeptMapping;
import com.fulan.api.information.domain.News;
import com.fulan.api.information.vo.NewsManageVo;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.mapper.IRISManageNewsMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.IRisNewsService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description: IRIS咨询
 * @author: 
 * @date: 2018/3/5 11:02
 */
@Service
public class IRisNewsServiceImpl  implements IRisNewsService{
	@Autowired
	private IRISManageNewsMapper iRISNewsMapper;
	@Autowired
	private AttachmentService attachmentService;
	@Override
	public PageInfo<NewsManageVo> ListNew(Page<NewsManageVo> page, String title, String type, String status,
			String satrtTime, String endTime, int pageNo, int pageSize) {
		PageInfo<NewsManageVo> pageInfo = new PageInfo<>();
        int total = iRISNewsMapper.listForManageCount(title,type,status,satrtTime,endTime);
        pageInfo.setRecords(iRISNewsMapper.listForManage(page,title,type,status,satrtTime,endTime, pageNo, pageSize));
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
        pageInfo.setPageRecords((int) page.getTotal());
		return pageInfo;
	}


	@Override
	public Response<String> updateStatus(String id, String status) {
		Response<String> response = new Response<>(Response.SUCCESS, "修改成功");
		Date date = null;
		if(status.equals("2")){
			date=new Date();
		}
		Integer updateStatus = iRISNewsMapper.updateStatus(id,status,date);
		if(null !=updateStatus && updateStatus==0){
			response.setCode(Response.ERROR);
			response.setMsg("修改失败");
		}
		return response;
	}


	@Override
	public Response<String> deleteNews(String id) {
		Response<String> response = new Response<>(Response.SUCCESS, "删除成功");
		Integer num=iRISNewsMapper.deleteNews(id);
		if(null !=num && num==0){
			response.setCode(Response.ERROR);
			response.setMsg("删除失败");
		}
		return response;
	}


	@Override
	public Response<String> insertNews(NewsManageVo newsManageVo,String pathId,String FilePathId) {
		Response<String> response = new Response<String>(Response.SUCCESS,"咨询新增成功");
		Long id = Idfactory.generate();
		if(null !=pathId && ""!=pathId){
			Attachment	attachment=	attachmentService.selectById(Long.parseLong(pathId));
			attachment.setHostId(id);
			attachmentService.updateById(attachment);
		}
		if(null !=pathId && ""!=FilePathId){
			Attachment	attachment=	attachmentService.selectById(Long.parseLong(FilePathId));
			attachment.setHostId(id);
			attachmentService.updateById(attachment);
		}
		newsManageVo.setId(id);
		newsManageVo.setGmtCreate(new Date());
		newsManageVo.setGmtModified(new Date());
		int count = iRISNewsMapper.insertNews(newsManageVo);
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("咨询新增失败"); 
		}else{
			if(null != newsManageVo.getMessageDeptMapping() && newsManageVo.getMessageDeptMapping().size()>0 ){
				for (MessageDeptMapping qa : newsManageVo.getMessageDeptMapping()) {
					qa.setTargetType(newsManageVo.getMsgType());
					qa.setId(Idfactory.generate());
					qa.setNewsId(id);
					iRISNewsMapper.insertMessageDeptMapping(qa);
				}
			}
		}
		return response;
	}


	@Override
	public Response<String> updateNews(NewsManageVo newsManageVo,String pathId,String FilePathId) {
		Response<String> response = new Response<String>(Response.SUCCESS,"咨询修改成功");
		newsManageVo.setGmtModified(new Date());
		int count = iRISNewsMapper.updateNews(newsManageVo);
		if(null !=pathId && ""!=pathId){
			Attachment	attachment=	attachmentService.selectById(Long.parseLong(pathId));
			attachment.setHostId(newsManageVo.getId());
			attachmentService.updateById(attachment);
		}
		if(null !=pathId && ""!=FilePathId){
			Attachment	attachment=	attachmentService.selectById(Long.parseLong(FilePathId));
			attachment.setHostId(newsManageVo.getId());
			attachmentService.updateById(attachment);
		}
		if(count == 0){
			response.setCode(Response.ERROR);
			response.setMsg("咨询修改失败"); 
		}else{
			int de = iRISNewsMapper.deleMessageDeptMapping(newsManageVo.getId());
			if(null != newsManageVo.getMessageDeptMapping() && newsManageVo.getMessageDeptMapping().size()>0 ){
			for (MessageDeptMapping qa : newsManageVo.getMessageDeptMapping()) {
				qa.setId(Idfactory.generate());
				qa.setNewsId(newsManageVo.getId());
				qa.setTargetType(newsManageVo.getStatus());
				iRISNewsMapper.insertMessageDeptMapping(qa);
			}
			}
		}
		return response;
	}


	@Override
	public NewsManageVo selectOneNewsById(String id) {
		NewsManageVo newsManageVo=new NewsManageVo();
		News news=iRISNewsMapper.selectOneNewsById(id);
		List<MessageDeptMapping> data=iRISNewsMapper.selectMessageDeptMappingList(id);
		newsManageVo.setMessageDeptMapping(data);
		newsManageVo.setNews(news);
		return newsManageVo;
	}

    

}