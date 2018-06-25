package com.fulan.application.service;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.PlanComment;
import com.fulan.api.plan.vo.PlanCommentDto;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import java.util.Map;

/**
 * <p>
 * 提问回复 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-25
 */
public interface PlanCommentService extends IService<PlanComment> {

    /**
     * 新增一条评论
     * @param planComment
     * @return
     */
    public Response<Boolean> insertPlanComment(PlanComment planComment);


    /**
     * 分页获取前端可查看的评论列表
     * @param planId
     * @param planType
     * @param pageNo
     * @param pageSize
     * @param pageSortFiled
     * @param pageSortType
     * @return
     */
    public PageInfo<PlanComment> listByPage(Long planId, Integer planType, Integer pageNo, Integer pageSize, String pageSortFiled, String pageSortType);
    /**
     * 后台 评论 分页查询  查询结果用map
     * @return
     */
     public PageInfo<Map<String,Object>> manageListByPage(Page<Map<String,Object>> page,Map<String,String> param);

    /**
     * 查询评论
     * @param paramMap
     * @return
     */
    Page<PlanCommentDto> searchPlanComment(Map<String,Object> paramMap );



}
