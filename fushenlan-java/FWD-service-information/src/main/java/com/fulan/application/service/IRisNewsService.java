package com.fulan.application.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.information.vo.NewsManageVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import java.util.List;

/**
 * @Description: 短信手机号码黑名单接口定义
 * @author: guiyang
 * @date: 2018/3/5 11:00
 */
public interface IRisNewsService {

	PageInfo<NewsManageVo> ListNew(Page<NewsManageVo> page, String title, String type, String status, String satrtTime,
			String endTime, int pageNo, int pageSize);

	Response<String> updateStatus(String id, String status);

	Response<String> deleteNews(String id);

	Response<String> insertNews(NewsManageVo newsManageVo,String pathId,String FilePathId);

	Response<String> updateNews(NewsManageVo newsManageVo,String pathId,String FilePathId);

	NewsManageVo selectOneNewsById(String id);
}