package com.fulan.application.service.impl;

import com.fulan.api.plan.domain.ClassPlanManager;
import com.fulan.application.mapper.ClassPlanManagerMapper;
import com.fulan.application.service.ClassPlanManagerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * <p>
 * 负责人 服务实现类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-18
 */
@Service
@Transactional
public class ClassPlanManagerServiceImpl extends ServiceImpl<ClassPlanManagerMapper, ClassPlanManager> implements ClassPlanManagerService{

}
