package com.fulan.application.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.er.Paper;
import com.fulan.api.paper.domain.er.PaperItem;
import com.fulan.api.paper.domain.er.PaperItemInfo;
import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.paper.vo.PaperItemVo;
import com.fulan.api.paper.vo.PaperManageVo;
import com.fulan.api.paper.vo.PaperVo;
import com.fulan.application.mapper.PaperItemInfoMapper;
import com.fulan.application.mapper.PaperItemMapper;
import com.fulan.application.mapper.PaperMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.PaperService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.listUtil.ListUtils;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.str.StringUtil;
@Service
public class PaperServiceImpl implements PaperService {
	
	@Autowired
	private PaperMapper paperMapper;
	@Autowired
	private PaperItemMapper  paperItemMapper;
	@Autowired
	private PaperItemInfoMapper paperItemInfoMapper;
    
	private  int getPages(int size,int total) {
        int pages = 0;
		if (size == 0) {
            return 0;
        }
        pages =total / size;
        if (total % size != 0) {
            pages++;
        }
        return pages;
    }
	
	@Override
	public PageInfo<PaperManageVo> listByPage(Page<PaperManageVo> page ,String keyWord,String orgId, int pageNo, int pageSize) {
		PageInfo<PaperManageVo> pageInfo = new PageInfo<PaperManageVo>();
		pageInfo.setRecords(paperMapper.paperManageSerch(page,keyWord,orgId,pageNo,pageSize));
		Map<String,Object> searchMap = new HashMap<String,Object>();
		searchMap.put("keyWord", keyWord);
		searchMap.put("orgId", orgId);
		int total  = paperMapper.paperManageSerchCount(searchMap);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	public PaperVo paperCheck(Paper paper) {
		PaperVo paperVo = paperMapper.paperCheckById(paper);
		return paperVo;
	}

	@Override
	@Transactional
	public String paperHandle(PaperVo paperVo) {
		if(null != paperVo){
			Paper paper = paperVo.getPaper();
			if(null != paper){
				Long id = paper.getId();
				List<PaperItemVo> paperItemVo = paperVo.getPaperItemVo();
				if(null == id){ //新增
					id = Idfactory.generate();
					paper.setId(id);
					paperMapper.insertAllColumn(paper);
				}else{ //修改
					paperMapper.updateAllColumnById(paper);
					paperItemInfoMapper.deletePaperItemVoByPaperId(id);
				}	
				if(!ListUtils.isEmpty(paperItemVo)){
					for (PaperItemVo paperItemVo2 : paperItemVo) {
						PaperItem paperItem = paperItemVo2.getPaperItem();
						if(null != paperItem){
							paperItem.setId(Idfactory.generate());
							paperItem.setPaperId(paper.getId());
							paperItemMapper.insertAllColumn(paperItem);
							List<PaperItemInfo> paperItemInfo = paperItemVo2.getPaperItemInfo();
							if(!ListUtils.isEmpty(paperItemInfo)){
								for (PaperItemInfo paperItemInfo2 : paperItemInfo) {
									if(!StringUtil.isEmpty(paperItemInfo2.getPaperContent())){
										paperItemInfo2.setId(Idfactory.generate());
										paperItemInfo2.setPaperItemId(paperItem.getId());
										paperItemInfoMapper.insertAllColumn(paperItemInfo2);
									}
									
									
								}
							}
						}
					}
				}
				return id+"";
			}
			return null;
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deletePaperVo(String id) {
		try{
			paperMapper.deletePaperVo(id);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Response<List<PaperDetailVo>> selectPaperDetailList(Map<String,Object> map) {
		// TODO Auto-generated method stub
		Response<List<PaperDetailVo>> response = new Response<List<PaperDetailVo>>(Response.SUCCESS,"试题结果信息获取成功");
		response.setData(paperMapper.selectPaperDetailList(map));
		return response;
	}

	@Override
	public Response<List<PaperVo>> getPaperByPaperType(Map searchMap) {
		// TODO Auto-generated method stub
		Response<List<PaperVo>> response = new Response<List<PaperVo>>(Response.SUCCESS,"试题信息获取成功");
		response.setData(paperMapper.getPaperByPaperType(searchMap));
		return response;
	}
}
