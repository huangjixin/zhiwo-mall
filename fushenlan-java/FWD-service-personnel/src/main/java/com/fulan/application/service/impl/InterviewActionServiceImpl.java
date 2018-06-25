package com.fulan.application.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.personnel.domain.InterviewAction;
import com.fulan.api.personnel.service.InterviewActionService;
import com.fulan.application.mapper.InterviewActionMapper;

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

	@Autowired
	InterviewActionMapper interviewActionMapper;
	@Override
	public int insertInterviewAction(InterviewAction interviewAction) {
		// TODO Auto-generated method stub
		return interviewActionMapper.insert(interviewAction);
	}

}
