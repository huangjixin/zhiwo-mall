package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.er.Paper;
import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.paper.vo.PaperManageVo;
import com.fulan.api.paper.vo.PaperVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

public interface PaperService {
    // 后台增员试卷管理分页查询
	PageInfo<PaperManageVo> listByPage(Page<PaperManageVo> page ,String keyWord,String orgId,int pageNo, int pageSize);
	// 后台根据试卷主键查询详情
	PaperVo paperCheck(Paper paper);
	// 后台根据试卷Vo 修改/新增
	String paperHandle(PaperVo paperVo);
	// 后台删除试卷
	boolean deletePaperVo (String id);
	
	/**
	 * 在线增员-查询代理人历史答题详情
	 * */
	Response<List<PaperDetailVo>> selectPaperDetailList(Map<String,Object> map);
	/**
	 * 在线增员-试题详情
	 * */
	Response<List<PaperVo>> getPaperByPaperType(Map map);
}
