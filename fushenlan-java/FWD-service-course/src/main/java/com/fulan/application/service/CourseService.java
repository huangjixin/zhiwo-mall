package com.fulan.application.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseShare;
import com.fulan.api.course.vo.CourseAndPlanVo;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.course.vo.CourseVo;
import com.fulan.api.material.domain.Material;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 基础课程服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-19
 */
public interface CourseService extends IService<Course>{
	/**
     * 
     * @param keyWord
     * @param type
     * @param submitter	管理员 登录 不传值 null
     * 					讲师登录  传讲师的group_id 
     * 
     * @param pubType	管理员公共库/讲师私人库 1，讲师公共库2， 
     * @param isOnline
     * @param groupId
     * @param tagId
     * @param uploadTimeBegin
     * @param uploadTimeEnd
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<CourseCMSVo> listByPage(Page<CourseCMSVo> page,String keyWord,String type,String submitter,String pubType,String isOnline,
							  String groupId,String tagId,String uploadTimeBegin,
							  String uploadTimeEnd,
							  int pageNo, int pageSize);
    
    
    
    //公用查询接口 
    List<CourseCMSVo> courseManageByPublic(String keyWord, String type, String submitter,String pubType,String isOnline, String groupId,
			   String tagId, String uploadTimeBegin, String uploadTimeEnd);
    //根据主键  查询课程信息 /  包含课程材料和课件  以及该课程以分享的用户组
    CourseVo checkCourseVoInfo(String id);
    // 新增/修改课程信息  
    Response<String> handData(Course course);
    // 新增/修改课程信息 (manage)
    Response<String> handDatas(Course course,String lecturerId,String fileIds,String muchFields);
    // 批量分享/单个分享 
    boolean share(String[] groupIds, String[] courseIds);
    //返回不能被删除项的id集合
    List<String> delete(String[] courseIds);
    /**
     * 查询公共课中的线上课程列表  不包含已达包成学习计划 不做分页
     * @param map
     * @return
     */
    List<Course> OnlineCourseNotStudy(Map<String,String> map); 
    
    List<CourseManageVo> selectByCId(String cId);
    
    /**
	 * 查询和该课程有关的所有计划
	 * @return
	 */
	public List<CourseAndPlanVo> findElClassPlanByCourseId(@Param("courseId") Long courseId);



	Response<String> handDatas(Course course, String lecturerId, String materialId,String fileIds,String muchFields);



	List<String> getAllShared(String courseId);
	
	List<Material> getMaterialList(String id );


	/**
	 * 根据多个courseId查询 分享表
	 * @param courseIdStr  格式: courseId1,courseId2
	 * @return
	 */
    List<CourseShare> listCourseShareByCourseId(String courseIdStr);


    
    /**
     * 分页查询
     * @param paramMap
     * @param page
     * @return
     */
    PageInfo<CourseCMSVo> listCourseByPage(Map<String, Object> paramMap,
            Page<CourseCMSVo> page);
}

