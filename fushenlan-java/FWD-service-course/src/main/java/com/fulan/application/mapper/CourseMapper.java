package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseShare;
import com.fulan.api.course.vo.CourseAndPlanVo;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.course.vo.CourseVo;
import com.fulan.api.material.domain.Material;

/**
 * <p>
 *  基础课程 接口
 * </p>
 *
 * @author Hedge
 * @since 2018-01-19
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
	
	List<CourseCMSVo> courseManageSearch(Page<CourseCMSVo> page,@Param("keyWord") String keyWord, 
							    		@Param("type") String type, 
							    		@Param("submitter") String submitter,
							    		@Param("pubType") String pubType,
							    		@Param("isOnline") String isOnline, 
							    		@Param("groupId") String groupId,
							    		@Param("tagId") String tagId, 
							    		@Param("uploadTimeBegin") String uploadTimeBegin, 
							    		@Param("uploadTimeEnd") String uploadTimeEnd,
							    		@Param("pageNo") int pageNo, 
							    		@Param("pageSize") int pageSize);
	
	List<CourseCMSVo> courseManageSearch(@Param("keyWord") String keyWord, 
    		@Param("type") String type, 
    		@Param("submitter") String submitter,
    		@Param("pubType") String pubType,
    		@Param("isOnline") String isOnline, 
    		@Param("groupId") String groupId,
    		@Param("tagId") String tagId, 
    		@Param("uploadTimeBegin") String uploadTimeBegin, 
    		@Param("uploadTimeEnd") String uploadTimeEnd);
	
  int courseManageSearchCount(@Param("keyWord") String keyWord, 
					  		@Param("type") String type, 
					  		@Param("submitter") String submitter,
					  		@Param("pubType") String pubType,
					  		@Param("isOnline") String isOnline, 
					  		@Param("groupId") String groupId,
					  		@Param("tagId") String tagId, 
					  		@Param("uploadTimeBegin") String uploadTimeBegin, 
					  		@Param("uploadTimeEnd") String uploadTimeEnd);
  CourseVo checkCourseVoInfo(String id);
  int updateByPrimaryKeySelective(Course record);
  int insertSelective(Course record);
  int deleteRelation(List<String> asList);
  int deleteRelationById(String id);
  int selectCourseAndPlanRe(String id);
  
  int share(Map<String, String> parms);
  int deleteShar(String CourseId);
  
  /**
	 * 查询和该课程有关的所有计划
	 * @return
	 */
	public List<CourseAndPlanVo> findElClassPlanByCourseId(@Param("courseId") Long courseId);
  
  /**
   * 查询公共课中的线上课程列表  不包含已达包成学习计划 不做分页
   * @param map
   * @return
   */
  List<Course> OnlineCourseNotStudy(Map<String,String> map); 
  
  List<CourseManageVo> selectByCId( @Param("cId")Long cId);

  List<String> getAllShared(String courseId);
  
  List<Material> getMaterialList(@Param("id") String id );

  
  List<CourseShare> listCourseShareByCourseId(@Param("courseIdStr")String courseIdStr);

  int getCourseCount(Map<String, Object> paramMap);

  List<CourseCMSVo> listCourseByPage(Page<CourseCMSVo> page,
        Map<String, Object> paramMap);
  
}
