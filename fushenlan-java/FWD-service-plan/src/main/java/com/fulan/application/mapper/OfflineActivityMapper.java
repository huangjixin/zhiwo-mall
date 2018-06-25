package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.plan.domain.OfflineActivity;
import com.fulan.api.plan.vo.OfflineActivityDto;
import com.fulan.api.plan.vo.OfflineActivityVoFw;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 线下活动 Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-02-01
 */
@Mapper
public interface OfflineActivityMapper extends BaseMapper<OfflineActivity> {

    /**
     * 根据条件查询公开课信息
     * @param paramMap
     * @return
     */
    public List<OfflineActivityDto>  searchOfflineActivity(Page<OfflineActivityDto> page, Map<String,Object> paramMap);

    /**
     * manage 分页列表
     * @param page
     * @param name
     * @param state
     * @param startDate
     * @param endDate
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<OfflineActivityVoFw> pageList(Page<OfflineActivityVoFw> page,
			@Param("name") String name,
            @Param("state") String state,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,
            @Param("pageNo") int pageNo,
            @Param("pageSize") int pageSize);
	
	/**
	 * manage 记录数
	 * @param name
	 * @param code
	 * @param tagId
	 * @return
	 */
	Integer pageListCount(@Param("name") String name,
            @Param("state") String state ,@Param("startDate")Date startDate,@Param("endDate")Date endDate);
	
	List<Long> seleByIdList(Map<String,Object> map);
	
	List<OfflineActivityVoFw> seleByCourseIdIsSign(@Param("id") String id,
			@Param("userIdList") List<Long> userIdList);
	
	Integer insertSelective(OfflineActivity offlineActivity);
}
