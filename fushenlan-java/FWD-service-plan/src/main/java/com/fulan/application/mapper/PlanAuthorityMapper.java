package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.plan.domain.PlanAuthority;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程权限表 Mapper 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-01-30
 */
@Mapper
public interface PlanAuthorityMapper extends BaseMapper<PlanAuthority> {
    /**
     * 查看用户是否有该计划的权限
     * @param paramMap
     * @return
     */
    Integer hasAuthority(Map<String,Object> paramMap);
    
    int deleteByCourseId(Long courseId);
    
    List<PlanAuthority> selectByAssociateId(String id);

	List<PlanAuthority> selectByCourseId(String courseId);

}
