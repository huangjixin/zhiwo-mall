package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.PlanQuestion;
import com.fulan.api.plan.vo.PlanQuestionDto;
import com.fulan.api.security.domain.Account;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.PlanQuestionMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.orm.page.PageUtil;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.PlanQuestionService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提问回复 服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
@Service
@Transactional
public class PlanQuestionServiceImpl extends ServiceImpl<PlanQuestionMapper, PlanQuestion> implements PlanQuestionService{

    @Autowired
    private PlanQuestionMapper planQuestionMapper;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Response<Boolean> insertPlanQuestion(PlanQuestion planQuestion) {
        Response<Boolean> res =new Response<Boolean>();
        try {
            planQuestion.setGmtCreate(new Date());
            planQuestion.setGmtModified(new Date());
            planQuestion.setId(idGenerator.generate());
            planQuestion.setIsOpen(CommenConstant.VALUE_YES);
            Account account = (Account) redisUtil.getUserInfo();
            planQuestion.setCreateUser(account.getId());
            planQuestion.setModifyUser(account.getId());
            planQuestion.setCreateName(account.getAccountName());
            planQuestionMapper.insert(planQuestion);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }

    @Override
    public Response<Boolean> updatePlanQuestion(PlanQuestion planQuestion) {
        Response<Boolean> res =new Response<Boolean>();
        try {
            planQuestionMapper.updateById(planQuestion);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }

    @Override
    public PageInfo<PlanQuestion> listByPage(Long courseId,Integer courseType, Integer pageNo, Integer pageSize, String pageSortFiled, String pageSortType) {
        Page<PlanQuestion> page = new Page<PlanQuestion>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        page.setAsc((null == pageSortType || "desc".equals(pageSortType) ? false : true));
        PlanQuestion planQuestion = new PlanQuestion();
        planQuestion.setCourseId(courseId);
        planQuestion.setCourseType(courseType);
        planQuestion.setIsOpen(CommenConstant.VALUE_YES);//提问开启的
        EntityWrapper<PlanQuestion> ew = new EntityWrapper<>(planQuestion);
        return PageUtil.versa(this.selectPage(page, ew));
    }

	@Override
	public PageInfo<Map<String,Object>> manageListByPage(Page<Map<String,Object>> page,Map<String,String> param) {
		PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
		pageInfo.setRecords(planQuestionMapper.manageListByPage(page, param));
		int total = planQuestionMapper.manageListByPageCount(param);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(com.fulan.application.util.page.PageUtil.getPages(page.getSize(),total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}

	@Override
	@Transactional
	public Response<Boolean> hideOrOpenAll(String[] id, String type, String isOpen) {
		List<String> list = Arrays.asList(id);
		Response<Boolean>  res= new Response<Boolean>();
		res.setData(planQuestionMapper.hideOrOpenAll(list,type,isOpen)>0);
		return res;
	}

    @Override
    public Page<PlanQuestionDto> searchPlanQuestion(Map<String, Object> paramMap) {
        Page<PlanQuestionDto> page = new Page<PlanQuestionDto>((Integer)paramMap.get("pageNo"), (Integer)paramMap.get("pageSize"));
        page.setRecords(planQuestionMapper.searchPlanQuestion(page,paramMap));
        return page;
    }
}
