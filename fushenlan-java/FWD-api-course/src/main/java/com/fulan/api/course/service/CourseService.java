package com.fulan.api.course.service;


import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseMaterial;
import com.fulan.api.course.domain.CourseLecturer;
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
 * 基础课程 服务类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-19
 */
@FeignClient(name="course"/*,configuration=MyConfiguration.class*/)
public interface CourseService {
    @PostMapping("/course/insert")
    int insert(@RequestBody Course course);

    @PostMapping("/course/delete")
    int delete(@RequestParam("id") Long id);

    @PostMapping("/course/batch/delete")
    int deleteBacth(Long[] id);

    @PostMapping(value = "/course/update")
    int update(@RequestBody Course course);

    @GetMapping(value = "/course/find")
    Course findById(@RequestParam("id") Long id);
    
    @GetMapping(value="/manage/course/checkCourseVoInfo")
    CourseVo checkCourseVoInfo(@RequestParam("id") String id); 
    @GetMapping(value="/manage/course/selectCourseOne")
    Course selectCourseOne(@RequestParam("id") String id);
    @GetMapping(value="/manage/course/onlineCourseNotStudy")
    List<Course> onlineCourseNotStudy(@RequestParam Map<String,String> map); 
    @PostMapping(value="/manage/course/handCourse")
    Response<String> handCourse(@RequestBody Course course);
    @PostMapping(value="/manage/course/handCourses")
    Response<String> handCourses(@RequestBody Course course,@RequestParam("lecturerId")String lecturerId,
            @RequestParam(value="fileIds", required=false)String fileIds,
            @RequestParam(value="muchfileIds", required=false)String muchfileIds);
    @PostMapping(value="/manage/course/deleteCourse")
    Response<List<String>> deleteBatchCourse(@RequestParam(value="courseIds",required=false) String[] courseIds);
    
    @PostMapping(value = "/manage/course/shareCourse")
	Response<Boolean> doShareCourses(@RequestParam(value="groupIds")String[] groupIds, 
			 @RequestParam(value="courseIds")String[] courseIds);
    
    @GetMapping(value = "/manage/course/selectCourseByMap")
    List<Course> selectCourseByMap(@RequestParam(value="map") Map<String,Object> map);
    
    @GetMapping(value = "/manage/course/courseManage")
	PageInfo<CourseCMSVo> courseListByPage(
			@RequestParam(value="keyWord",required=false) String keyWord,//课程关键字
			@RequestParam(value="type",required=false) String type,//类型
			@RequestParam(value="submitter",required=false) String submitter,//提交人
			@RequestParam(value="pubType",required=false) String pubType,
			@RequestParam(value="isOnline",required=false) String isOnline,//线上线下
			@RequestParam(value="groupId",required=false)  String groupId,//用户组/角色编号
			@RequestParam(value="tagId",required=false)   String tagId,//用户组/角色下的标签编号
			@RequestParam(value="uploadTimeBegin",required=false)   String uploadTimeBegin,//上传时间
			@RequestParam(value="uploadTimeEnd",required=false)   String uploadTimeEnd,//结束时间
            @RequestParam(value="pageNo",required=false) int pageNo,
            @RequestParam(value="pageSize",required=false) int pageSize);
    
    @GetMapping(value = "/manage/course/courseManageByPublic")
	List<Course> courseManageByPublic(
			@RequestParam(value="keyWord",required=false) String keyWord,//课程关键字
			@RequestParam(value="type",required=false) String type,//类型
			@RequestParam(value="submitter",required=false) String submitter,//提交人
			@RequestParam(value="pubType",defaultValue="1") String pubType,
			@RequestParam(value="isOnline",required=false) String isOnline,//线上线下
			@RequestParam(value="groupId",required=false)  String groupId,//用户组/角色编号
			@RequestParam(value="tagId",required=false)   String tagId,//用户组/角色下的标签编号
			@RequestParam(value="uploadTimeBegin",required=false)   String uploadTimeBegin,//上传时间
			@RequestParam(value="uploadTimeEnd",required=false)   String uploadTimeEnd//结束时间
           );
    
    @GetMapping(value="/manage/course/findElClassPlanByCourseId")
    List<CourseAndPlanVo> findElClassPlanByCourseId(@RequestParam("courseId") Long courseId);
    
    @GetMapping(value="/manage/course/selectByCId")
    List<CourseManageVo> selectByCId(@RequestParam("cId") String cId);
    
    @PostMapping(value="/manage/course/handCoursesWithMaterial")
    Response<String> handCourses(@RequestBody Course course,@RequestParam("lecturerId")String lecturerId,
            @RequestParam("materialId")String materialId,@RequestParam(value="fileIds", required=false)String fileIds,
            @RequestParam(value="muchfileIds", required=false)String muchfileIds);
	
	@GetMapping(value = "/manage/course/getAllShared")	
	List<String> getAllShared(@RequestParam(value="courseId")String courseId);
	
	@GetMapping(value = "/manage/course/getMaterialList")	
	List<Material> getMaterialList(@RequestParam(value="id")String id);
	
	@PostMapping(value="/manage/courseLecturer/findCourseLecturer")
	List<CourseLecturer> findCourseLecturer(@RequestParam(value="courseId") Long courseId,@RequestParam(value="lecturerId") Long lecturerId);
	
	@GetMapping(value = "/manage/course/listCourseMaterialByCourseId")  
	List<CourseMaterial> listCourseMaterialByCourseId(@RequestParam(value="courseId")String courseId);
	
	@GetMapping(value = "/manage/course/listCourseShareByCourseId") 
    List<CourseShare> listCourseShareByCourseId(@RequestParam(value="courseIdStr", required=false) String courseIdStr);
	
	/**
	 * 后台分页查询
	 * @param paramMap
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@PostMapping(value = "/manage/course/listCourseByPage")
    PageInfo<CourseCMSVo> listCourseByPage(@RequestBody Map<String, Object> paramMap,
            @RequestParam(value="pageNo", required=false) int pageNo, 
            @RequestParam(value="pageSize", required=false) int pageSize);
}
