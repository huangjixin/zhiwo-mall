package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.application.mapper.PlanAuthorityMapper;
import com.fulan.application.service.PlanAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程权限表 服务实现类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Service
public class PlanAuthorityServiceImpl extends ServiceImpl<PlanAuthorityMapper, PlanAuthority> implements PlanAuthorityService {

    @Autowired
    private PlanAuthorityMapper planAuthorityMapper;
    @Override
    public void insertPlanAuthority(PlanAuthority planAuthority) {
        planAuthorityMapper.insert(planAuthority);
    }

    @Override
    public Integer hasAuthority(Map<String, Object> paramMap) {
        return planAuthorityMapper.hasAuthority(paramMap);
    }

	@Override
	public List<PlanAuthority> selectByCourseId(String courseId) {
		if(null!=courseId&&""!=courseId){
			return planAuthorityMapper.selectByCourseId(courseId);
		}
		return null;
	}
}
