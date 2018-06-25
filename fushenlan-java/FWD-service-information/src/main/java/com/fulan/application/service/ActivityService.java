package com.fulan.application.service;


import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.information.domain.ApplyDetail;
import com.fulan.api.information.vo.ActivityVo;
import com.fulan.api.information.vo.NewsManageVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 活动接口
 * </p>
 *
 */

@Service
public interface ActivityService {

	PageInfo<ActivityVo> listactivity(Page<ActivityVo> page, String title, String type, String status, String actStartDate,
			String actEndDate, int pageNo, int pageSize);

	Response<String> updateStatus(String id, String status);

	Response<String> deleteActivity(String id);

	Response<String> insertActivity(ActivityVo activityVo, String pathId, String filePathId);

	Response<String> updateActivity(ActivityVo activityVo, String pathId, String FilePathId);

	ActivityVo selectOneActivityById(String id);

	PageInfo<ApplyDetail> activityApplyList(Page<ApplyDetail> page, String acounyName, int pageNo, int pageSize,String id,String signUpSet);
	

}
