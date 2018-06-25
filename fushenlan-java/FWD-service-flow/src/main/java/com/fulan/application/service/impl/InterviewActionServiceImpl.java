package com.fulan.application.service.impl;


import com.fulan.api.flow.domain.InterviewAction;
import com.fulan.application.mapper.InterviewActionMapper;
import com.fulan.application.service.InterviewActionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 面试执行情况 服务实现类
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@Service
@Transactional
public class InterviewActionServiceImpl extends ServiceImpl<InterviewActionMapper, InterviewAction> implements InterviewActionService {

}
