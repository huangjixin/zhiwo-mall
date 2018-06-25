package com.fulan.application.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.information.domain.Activity;
import com.fulan.api.information.domain.ActivityMemberMapping;
import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.api.information.vo.ActivityVo;

/**
 * <p>
 * 活动Mapper
 * </p>
 *
 */
@Mapper
public interface ActivityMapper extends BaseMapper<Activity>{

	int listForManageCount(
			@Param("title")   	String title,
			@Param("type")		String type,
			@Param("status")	String status,
			@Param("actStartDate")	String actStartDate,
			@Param("actEndDate")	String actEndDate);

	List<ActivityVo> listForManage(Page<ActivityVo> page,
			@Param("title")   	String title,
			@Param("type")		String type,
			@Param("status")	String status,
			@Param("actStartDate")	String actStartDate,
			@Param("actEndDate")	String actEndDate,
			@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize);

	Integer updateStatus(@Param("id")  String id, @Param("status")  String status,@Param("date")  Date date);

	Integer deleteActivity(String id);

	int insertActivity(ActivityVo activityVo);

	void insertActivityMemberMapping(ActivityMemberMapping qa);

	int updateActivity(ActivityVo activityVo);

	int deleActivityMemberMapping(Long id);

	Activity selectOneActivityById(String id);

	List<ActivityMemberMapping> selectActivityMemberMapping(String id);

	Long selectActivityCount(Long id);

	int activityApplyListCount(
			@Param("acounyName") String acounyName,
			@Param("id") String id);

	List<ApplyDetail> activityApplyListForManage(Page<ApplyDetail> page,
			@Param("acounyName") String acounyName, 
			@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize,
			@Param("id") String id);

	int selectActivityMamerCount(@Param("acounyName") String acounyName,
			@Param("id") String id,@Param("signUpSet") String signUpSet);

	List<ApplyDetail> selectActivityMamerForManage(Page<ApplyDetail> page,
			@Param("acounyName") String acounyName, 
			@Param("pageNo") int pageNo,
			@Param("pageSize") int pageSize,
			@Param("id") String id,
			@Param("signUpSet") String signUpSet);
	
	
}
