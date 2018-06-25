package com.fulan.application.mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.information.domain.MessageDeptMapping;
import com.fulan.api.information.domain.News;
import com.fulan.api.information.vo.NewsManageVo;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IRISManageNewsMapper extends BaseMapper<News>{

	int listForManageCount( @Param("title")   	String title,
							@Param("type")		String type,
							@Param("status")	String status,
							@Param("satrtTime")	String satrtTime,
							@Param("endTime")	String endTime);

	List<NewsManageVo> listForManage(Page<NewsManageVo> page,
									@Param("title")   	String title,
									@Param("type")		String type,
									@Param("status")	String status,
									@Param("satrtTime")	String satrtTime,
									@Param("endTime")	String endTime,
									@Param("pageNo")int pageNo, @Param("pageSize")int pageSize);

	Integer updateStatus(@Param("id") String id,@Param("status") String status,@Param("date")  Date date);

	Integer deleteNews(String id);

	int insertNews(NewsManageVo newsManageVo);

	void insertMessageDeptMapping(MessageDeptMapping qa);

	int updateNews(NewsManageVo newsManageVo);

	int deleMessageDeptMapping(Long id);

	News selectOneNewsById(String id);

	List<MessageDeptMapping> selectMessageDeptMappingList(String id);


}   