package com.fulan.application.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.plan.domain.PlanComment;
import com.fulan.api.plan.vo.PlanCommentDto;
import com.fulan.api.security.domain.Account;
import com.fulan.application.mapper.PlanCommentMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.orm.page.PageUtil;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.PlanCommentService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
@Service
@Transactional
public class PlanCommentServiceImpl extends ServiceImpl<PlanCommentMapper, PlanComment> implements PlanCommentService{


    @Autowired
    private PlanCommentMapper planCommentMapper;
    @Autowired
    private IdGenerator idGenerator;
    @Autowired
    private RedisUtil redisUtil;
    @Override
    public Response<Boolean> insertPlanComment(PlanComment planComment) {
        Response<Boolean> res =new Response<Boolean>();
        try {
            planComment.setId(idGenerator.generate());
            planComment.setGmtCreate(new Date());
            planComment.setGmtModified(new Date());
            planComment.setIsOpen(1);
            Account account = (Account) redisUtil.getUserInfo();
            planComment.setCommentUser(account.getId());
            planComment.setModifyUser(account.getId());
            planComment.setCreateName(account.getAccountName());
            planCommentMapper.insert(planComment);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
        }
        return res;
    }

    @Override
    public PageInfo<PlanComment> listByPage(Long planId, Integer planType, Integer pageNo, Integer pageSize, String pageSortFiled, String pageSortType) {
        Page<PlanComment> page = new Page<PlanComment>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        page.setAsc((null == pageSortType || "desc".equals(pageSortType) ? false : true));
        PlanComment planComment = new PlanComment();
        planComment.setCourseId(planId);
        planComment.setCourseType(planType);
        planComment.setIsOpen(1);//评论开启的
        EntityWrapper<PlanComment> ew = new EntityWrapper<>(planComment);
        return PageUtil.versa(this.selectPage(page, ew));
    }


    @Override
    public PageInfo<Map<String, Object>> manageListByPage(Page<Map<String, Object>> page, Map<String, String> param) {
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>();
        pageInfo.setRecords(planCommentMapper.manageListByPage(page, param));
        int total = planCommentMapper.manageListByPageCount(param);
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(com.fulan.application.util.page.PageUtil.getPages(page.getSize(),total));
        pageInfo.setPageRecords(page.getTotal());
        return pageInfo;
    }

    @Override
    public Page<PlanCommentDto> searchPlanComment(Map<String, Object> paramMap) {
        Page<PlanCommentDto> page = new Page<PlanCommentDto>((Integer)paramMap.get("pageNo"), (Integer)paramMap.get("pageSize"));
        page.setRecords(planCommentMapper.searchPlanComment(page,paramMap));
        return page;
    }
}
