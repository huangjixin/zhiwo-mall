package com.fulan.application.manage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseMaterial;
import com.fulan.api.course.domain.CourseShare;
import com.fulan.api.course.vo.CourseAndPlanVo;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.course.vo.CourseVo;
import com.fulan.api.material.domain.Material;
import com.fulan.application.service.CourseMaterialService;
import com.fulan.application.service.CourseService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 *
 */
@RestController
@RequestMapping("/manage/course")
public class CourseManageController {
	private static final Logger logger = LoggerFactory.getLogger(CourseManageController.class);
	
	

	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseMaterialService courseMaterialService; 
	//基础课程关理 
	@RequestMapping("courseManage")
	public @ResponseBody PageInfo<CourseCMSVo> courseManage(String keyWord, String type, String submitter,String pubType,String isOnline, String groupId,
												   String tagId, String uploadTimeBegin, String uploadTimeEnd,int pageNo, int pageSize){
		Page<CourseCMSVo> page = new Page<CourseCMSVo>(pageNo, pageSize);
		return 	courseService.listByPage(page,keyWord, type, submitter, pubType,isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd, pageNo, pageSize);
	}
	//公用查询接口 
	@RequestMapping("courseManageByPublic")
	public @ResponseBody List<CourseCMSVo> courseManageByPublic(String keyWord, String type, String submitter,String pubType,String isOnline, String groupId,
													   String tagId, String uploadTimeBegin, String uploadTimeEnd){
		return 	courseService.courseManageByPublic(keyWord, type, submitter, pubType,isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd);
	}
	//分享
	@RequestMapping("shareCourse")
	public @ResponseBody Response<Boolean> shareCourse(String[] groupIds,String[] courseIds){
		Response<Boolean> res = new Response<>();
		res.setData(courseService.share(groupIds, courseIds));	
		return res;
	}
	//更新/新增
	@RequestMapping(value="handCourse",method=RequestMethod.POST)
	public @ResponseBody Response<String> handCourse(@RequestBody Course course){
		Response<String> returnStr = courseService.handData(course);
		return returnStr;
	}
	
	@RequestMapping(value="handCourses",method=RequestMethod.POST)
	public @ResponseBody Response<String> handCourses(@RequestBody Course course,@RequestParam(value="lecturerId", required=false)String lecturerId,
	        @RequestParam(value="fileIds", required=false)String fileIds,@RequestParam(value="muchfileIds", required=false)String muchfileIds){
		Response<String> returnStr = courseService.handDatas(course,lecturerId,fileIds,muchfileIds);
		return returnStr;
	}
	@RequestMapping("deleteCourse")
	public @ResponseBody Response<List<String>> deleteCourse(String[] courseIds){
		Response<List<String>> res = new Response<List<String>>();
		res.setData(courseService.delete(courseIds));
		return res;
	}
	@RequestMapping("handCourseVo")
	public @ResponseBody Response<String> handCourseVo(CourseVo vo){
		return null;
		
	}
	@RequestMapping(value="selectCourseByMap",method=RequestMethod.GET)
	public  @ResponseBody List<Course> selectCourseByMap(@RequestParam Map<String,Object> map){
		return courseService.selectByMap(map);
	}
	@RequestMapping(value="selectCourseOne",method=RequestMethod.GET)
	public @ResponseBody Course selectCourseOne(String id){
		return courseService.selectById(id);
	}
	@RequestMapping(value="checkCourseVoInfo",method=RequestMethod.GET)
	public @ResponseBody CourseVo checkCourseVoInfo(String id){
		return courseService.checkCourseVoInfo(id);
	}
	@RequestMapping(value="onlineCourseNotStudy",method=RequestMethod.GET)
	public @ResponseBody List<Course> OnlineCourseNotStudy(@RequestParam Map<String,String> map){
		return courseService.OnlineCourseNotStudy(map);
	}
	@RequestMapping(value="selectByCId",method=RequestMethod.GET)
	public @ResponseBody List<CourseManageVo> selectByCId(@RequestParam String cId){
		return courseService.selectByCId(cId);
	}
	/**
	 * 查询和该课程有关的所有计划
	 * @return
	 */
	@RequestMapping(value="findElClassPlanByCourseId",method=RequestMethod.GET)
	public List<CourseAndPlanVo> findElClassPlanByCourseId(@RequestParam Long courseId){
		return courseService.findElClassPlanByCourseId(courseId);
	}
	
	@RequestMapping(value="handCoursesWithMaterial",method=RequestMethod.POST)
	public @ResponseBody Response<String> handCoursesWithMaterial(@RequestBody Course course,@RequestParam(value="lecturerId", required=false)String lecturerId,@RequestParam(value="materialId", required=false)String materialId,
	        @RequestParam(value="fileIds", required=false)String fileIds,@RequestParam(value="muchfileIds", required=false)String muchfileIds){
		Response<String> returnStr = courseService.handDatas(course,lecturerId,materialId,fileIds,muchfileIds);
		return returnStr;
	}
	
	@RequestMapping(value="getAllShared",method=RequestMethod.GET)
	public @ResponseBody List<String> getAllShared(@RequestParam(value="courseId",required=false) String courseId){
		return courseService.getAllShared(courseId);
	}
	
	@RequestMapping(value="getMaterialList",method=RequestMethod.GET)
	public @ResponseBody List<Material> getMaterialList(@RequestParam(value="id",required=false) String id){
		return courseService.getMaterialList(id);
		
	}
	
	@GetMapping(value = "/listCourseMaterialByCourseId")  
    public @ResponseBody List<CourseMaterial> listCourseMaterialByCourseId(@RequestParam(value="courseId")String courseId){
	    Map<String,Object> columnMap = new HashMap<String, Object>();
	    columnMap.put("course_id", "courseId");
	    return courseMaterialService.selectByMap(columnMap);
	}
	
	@GetMapping(value = "/listCourseShareByCourseId") 
    public @ResponseBody List<CourseShare> listCourseShareByCourseId(@RequestParam(value="courseIdStr", required=false) String courseIdStr) {
	    try {
	        return courseService.listCourseShareByCourseId(courseIdStr);
        } catch (Exception e) {
            logger.error("查询课程分享表异常:",e);
            return null;
        }
	}
	
	@PostMapping(value = "/listCourseByPage")
    public @ResponseBody PageInfo<CourseCMSVo> listCourseByPage(@RequestBody Map<String, Object> paramMap,
            @RequestParam(value="pageNo", required=false) int pageNo, 
            @RequestParam(value="pageSize", required=false) int pageSize) {
	    try {
	        Page<CourseCMSVo> page = new Page<CourseCMSVo>(pageNo, pageSize);
            return courseService.listCourseByPage(paramMap,page);
        } catch (Exception e) {
            logger.error("查询课程异常:");
            e.printStackTrace();
            return null;
        }
	}
}
