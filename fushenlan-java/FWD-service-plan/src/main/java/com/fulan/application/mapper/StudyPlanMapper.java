package com.fulan.application.mapper;

import java.util.List;

import com.fulan.api.course.domain.Course;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.vo.StudyPlanVvo;
import com.fulan.application.util.page.PageInfo;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>
 * 学习计划 Mapper 接口
 * </p>
 *
 * @author fulan123
 * @since 2018-01-18
 */
@Mapper
public interface StudyPlanMapper extends BaseMapper<StudyPlan>{

    /**
     * 分页查询对象
     * @param page
     * @param code
     * @param name
     * @param tagId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<StudyPlan> findPlanByPage(Page<StudyPlan> page, @Param("code") String code, @Param("name") String name, @Param("tagId") String tagId, @Param("pageNo") int pageNo, @Param("pageSize") int pageSize);

    /**
     * 通过ID批量删除学习计划
     * @param planIds
     */
    public void deletePlanBatchByIds(@Param("planIds") Long[] planIds);
    /**
     * 通过关联Id批量删除课程
     * @param planIds
     */
    public void deletePlanCourseByIds(List<Long> planIds);
    
    /**
     * 根据id查询学习计划详情
     * @param id
     * @return
     */
    public StudyPlan findPlanDetailById(@Param("id") Long id);
    
    /**
     * 通过ID批量删除学习计划
     * @param planIds
     */
    public void deletePlanStudyByIds(List<Long> ids);
    
    public List<StudyPlan> studyPlanList(StudyPlan studyPlan);
    
    public List<StudyPlan> planList(@Param("keyWord") String keyWord,//计划关键字
    		@Param("code")String code,//计划代码
    		@Param("tagId")String tagId,//二级分类
    		@Param("uploadTimeBegin")String uploadTimeBegin,//上传时间
    		@Param("uploadTimeEnd")String uploadTimeEnd//结束时间
			);
    
    public StudyPlan selectPlanById(Long id);
    
    //根据id对studyPlan进行更新
    public void  updateStudyPlan(StudyPlan studyPlan);
    
    /**
     * 安搜索条件查找
     * @param studyPlan 
     * @return
     */
    public List<StudyPlan> studyPlanCollection(StudyPlan studyPlan);
    
    /**
     * 查找学习计划表是否被关联
     * @param id
     * @return
     */
    public  Integer planCourseSerach(Long id);
    
    List<Course> selectByElspId(@Param("type") String type);
    

    List<StudyPlanVvo> studyPlanManageSearch(RowBounds rowBounds, @Param("keyWord")String keyWord, 
																			    @Param("pageNo")int pageNo, 
																			    @Param("pageSize")int pageSize);

    int studyPlanManageSearchCount(@Param("keyWord")String keyWord); 
    
    //查询出所有的数据
    List<StudyPlanVvo> studyPlansSearch(Page<StudyPlanVvo> page, @Param("name")String name, @Param("code")String code, @Param("tagId")String tagId,  @Param("pageNo")int pageNo, @Param("pageSize") int pageSize);
    
    //查询出表记录数
    int stuPlanNum(@Param("name")String name,@Param("code")String code,@Param("tagId")String tagId); 
    
   /* public PageInfo<StudyPlan> listPlan(Page<StudyPlan> page,String name,String code,String tagId, int pageNo, int pageSize);*/
    
}
