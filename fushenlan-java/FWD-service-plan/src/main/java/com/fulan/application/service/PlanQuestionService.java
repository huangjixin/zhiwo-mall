package com.fulan.application.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.plan.domain.PlanQuestion;
import com.fulan.api.plan.vo.PlanQuestionDto;
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
public interface PlanQuestionService extends IService<PlanQuestion> {
    /**
     * 新增一条提问咨询
     * @param planQuestion
     * @return
     */
    public Response<Boolean> insertPlanQuestion(PlanQuestion planQuestion);

    /**
     * 更新一条提问咨询
     * @param planQuestion
     * @return
     */
    public Response<Boolean> updatePlanQuestion(PlanQuestion planQuestion);

    /**
     * 分页获取前端可查看的提问列表
     * @param courseId
     * @param courseType
     * @param pageNo
     * @param pageSize
     * @param pageSortFiled
     * @param pageSortType
     * @return
     */
    public PageInfo<PlanQuestion> listByPage(Long courseId,Integer courseType,Integer pageNo,Integer pageSize,String pageSortFiled,String pageSortType);
   /**
    * 后台  提问 分页查询  查询结果用map
    * @return
    */
    public PageInfo<Map<String,Object>> manageListByPage(Page<Map<String,Object>> page,Map<String,String> param);
	/**
	 * 批量开启/隐藏 评论或者提问
	 * @param id
	 * @param type
	 * @return
	 */
    public Response<Boolean> hideOrOpenAll(String[] id,String type,String isOpen);


    /**
     * 查询咨询
     * @param paramMap
     * @return
     */
    Page<PlanQuestionDto> searchPlanQuestion(Map<String,Object> paramMap );
}
