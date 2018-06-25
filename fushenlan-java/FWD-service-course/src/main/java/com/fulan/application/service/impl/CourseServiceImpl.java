package com.fulan.application.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.domain.CourseLecturer;
import com.fulan.api.course.domain.CourseMaterial;
import com.fulan.api.course.domain.CourseShare;
import com.fulan.api.course.vo.CourseAndPlanVo;
import com.fulan.api.course.vo.CourseCMSVo;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.course.vo.CourseVo;
import com.fulan.api.material.domain.Material;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.mapper.CourseLecturerMapper;
import com.fulan.application.mapper.CourseMapper;
import com.fulan.application.mapper.CourseMaterialMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.CourseService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.listUtil.ListUtils;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

/**
 * <p>
 * 基础课程服务实现类
 * </p>
 *
 * @author Hedge
 * @since 2018-01-19
 */
@Service
@Transactional
public class CourseServiceImpl extends ServiceImpl<CourseMapper,Course> implements CourseService{
    @Autowired
    private CourseMapper courseMapper;
    
    @Autowired
    private CourseLecturerMapper courseLecturerMapper;
    
    @Autowired
    CourseMaterialMapper courseMaterialMapper;
    
    @Autowired
    AttachmentService attachmentService;
    
	@Override
	public PageInfo<CourseCMSVo> listByPage(Page<CourseCMSVo> page,String keyWord, String type, String submitter,String pubType, String isOnline, String groupId,
			String tagId, String uploadTimeBegin, String uploadTimeEnd, int pageNo, int pageSize) {
		PageInfo<CourseCMSVo> pageInfo = new PageInfo<CourseCMSVo>();
		int total = courseMapper.courseManageSearchCount(keyWord, type, submitter, pubType, isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd);
		pageInfo.setRecords(courseMapper.courseManageSearch(page, keyWord, type, submitter,pubType,isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd, pageNo, pageSize));
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(PageUtil.getPages(pageSize,total));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}
	
	@Override
	public List<CourseCMSVo> courseManageByPublic(String keyWord, String type, String submitter, String pubType,
			String isOnline, String groupId, String tagId, String uploadTimeBegin, String uploadTimeEnd) {
		return courseMapper.courseManageSearch(keyWord, type, submitter, pubType, isOnline, groupId, tagId, uploadTimeBegin, uploadTimeEnd);
	}
	@Override
	public CourseVo checkCourseVoInfo(String id) {
		return courseMapper.checkCourseVoInfo(id);
	}
	@Override
	@Transactional
	public Response<String> handData(Course course) {
		Response<String> resp=new Response<String>(Response.SUCCESS, "课程操作成功");
		int result =0;
//		int co = 100/0;
		Long id = course.getId();
		if(null != id){
				course.setGmtModified(new Date());
				//修改课程信息
				result = courseMapper.updateByPrimaryKeySelective(course);
		}else{
			id = Idfactory.generate();
			course.setId(id);
			course.setGmtCreate(new Date());
			course.setIsShare(0);//默认不分享
			course.setIsRelatePlan(0);//默认不关联计划
			//添加课程
			result = courseMapper.insertSelective(course);
		}
		if(result==0) {
			resp.setCode(Response.ERROR);
			resp.setMsg("课程操作失败");
			return resp;
		}
		return resp;
	}
	
	@Override
	@Transactional
	public Response<String> handDatas(Course course,String lecturerId,String fileIds,String muchFields) {
		Response<String> resp=new Response<String>(Response.SUCCESS, "课程操作成功");
		int result =0;
//		int co = 100/0;
		Long id = course.getId();
		if(null != id){
			if(lecturerId!=null) {
				//修改课程和讲师关联表
				List<CourseLecturer> courseLecturers = new ArrayList<>();
				String[]  strs=lecturerId.split(",");
				for(int i=0,len=strs.length;i<len;i++){
					CourseLecturer courseLecturer = new CourseLecturer();
					courseLecturer.setId(Idfactory.generate());
					courseLecturer.setCourseId(course.getId());
					courseLecturer.setLecturerId(new Long(strs[i]));
				   courseLecturers.add(courseLecturer);
					
				}
				/*for (CourseLecturer string : courseLecturers) {
					System.err.println(string.getCourseId()+"&&&&"+string.getLecturerId());
				}*/
				CourseLecturer courseLecturer = new CourseLecturer();
				courseLecturer.setCourseId(course.getId());
				//获取该课程的所有讲师id
				List<CourseLecturer> findCourseLecturer = courseLecturerMapper.findCourseLecturer(courseLecturer );
				String lecturerIds ="";
				for (CourseLecturer courseLecturer2 : findCourseLecturer) {
					lecturerIds += courseLecturer2.getLecturerId()+",";
				}
				//当前讲师和修改后的讲师作比较,如果不同，做插入操作前先删除之前的讲师
				if(!lecturerIds.equals(lecturerId)) {
					courseLecturerMapper.deleteCourseLecturerByCourseId(course.getId());
					int inserBylist = courseLecturerMapper.inserBylist(courseLecturers );
					if(inserBylist==0) {
						resp.setCode(Response.ERROR);
						resp.setMsg("课程和讲师绑定失败");
						return resp;
					}
				}
			}
				course.setGmtModified(new Date());
				//修改课程信息
				result = courseMapper.updateByPrimaryKeySelective(course);
				
				//关联上传的附件
				updateAttachment(course.getId(),fileIds,muchFields);
			
			
		}else{
			id = Idfactory.generate();
			course.setId(id);
			course.setGmtCreate(new Date());
			course.setIsShare(0);//默认不分享
			course.setIsRelatePlan(0);//默认不关联计划
			//添加课程
			result = courseMapper.insertSelective(course);
			//关联上传的附件
            updateAttachment(id,fileIds,muchFields);
			
			if(lecturerId!=null) {
				//修改课程和讲师关联表
				List<CourseLecturer> courseLecturers = new ArrayList<>();
				String[]  strs=lecturerId.split(",");
				for(int i=0,len=strs.length;i<len;i++){
					CourseLecturer courseLecturer = new CourseLecturer();
					id = Idfactory.generate();
					courseLecturer.setId(id);
					courseLecturer.setCourseId(course.getId());
					courseLecturer.setLecturerId(new Long(strs[i]));
				   courseLecturers.add(courseLecturer);
					
				}
				/*for (CourseLecturer string : courseLecturers) {
					System.err.println(string.getCourseId()+"&&&&"+string.getLecturerId());
				}*/
				CourseLecturer courseLecturer = new CourseLecturer();
				courseLecturer.setCourseId(course.getId());
				//获取该课程的所有讲师id
				List<CourseLecturer> findCourseLecturer = courseLecturerMapper.findCourseLecturer(courseLecturer );
				String lecturerIds ="";
				for (CourseLecturer courseLecturer2 : findCourseLecturer) {
					lecturerIds += courseLecturer2.getLecturerId()+",";
				}
				//当前讲师和修改后的讲师作比较,如果不同，做插入操作前先删除之前的讲师
				if(!lecturerIds.equals(lecturerId)) {
					courseLecturerMapper.deleteCourseLecturerByCourseId(course.getId());
					int inserBylist = courseLecturerMapper.inserBylist(courseLecturers );
					if(inserBylist==0) {
						resp.setCode(Response.ERROR);
						resp.setMsg("课程和讲师绑定失败");
						return resp;
					}
				}
			}
		}
		
		if(result==0) {
			resp.setCode(Response.ERROR);
			resp.setMsg("课程操作失败");
			return resp;
		}
		resp.setData(course.getId().toString());
		return resp;
	}
	@Override
	@Transactional
	public boolean share(String[] groupIds, String[] courseIds) {
		try{
			List<String> mlis = Arrays.asList(courseIds);
			List<String> lis =	Arrays.asList(groupIds);
			if(!ListUtils.isEmpty(mlis)){
				for (String string : mlis) {
					courseMapper.deleteShar(string);
					Course course = new Course();
					course.setId(Long.valueOf(string));
					course.setIsShare(CommenConstant.VALUE_NO);
					courseMapper.updateByPrimaryKeySelective(course);
					if(!ListUtils.isEmpty(lis)){
						for (String strings : lis) {
							Map<String,String> parms = new HashMap<String,String>();
							parms.put("id", Idfactory.generate()+"");
							parms.put("courseId", string);
							parms.put("groupId", strings);
							courseMapper.share(parms);
						}
						course.setIsShare(CommenConstant.VALUE_YES);
						courseMapper.updateByPrimaryKeySelective(course);
					}
				}
				return true;
			}
		return false;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	@Override
	@Transactional
	public List<String> delete(String[] courseIds) {
		try{
			List<String> mlis = Arrays.asList(courseIds);
			List<String> ylis = new ArrayList<String>();
			if(!ListUtils.isEmpty(mlis)){
				for (String string : mlis) {
					int len = courseMapper.selectCourseAndPlanRe(string);
					if(len > 0){
						ylis.add(string);
					}else{
						courseMapper.deleteRelationById(string);
					}
				}
			}
			return ylis;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	
	}

	@Override
	public List<Course> OnlineCourseNotStudy(Map<String, String> map) {
		return courseMapper.OnlineCourseNotStudy(map);
	}
	
	@Override
	public List<CourseManageVo> selectByCId(String cId) {
		
		return courseMapper.selectByCId(Long.parseLong(cId));
	}

	@Override
	public List<CourseAndPlanVo> findElClassPlanByCourseId(Long courseId) {
		return courseMapper.findElClassPlanByCourseId(courseId);
	}

	@Override
	public Response<String> handDatas(Course course, String lecturerId, String materialId,String fileIds,String muchFields) {
		Response<String> resp=new Response<String>(Response.SUCCESS, "课程操作成功");
		int result =0;
//		int co = 100/0;
		Long id = course.getId();
		
		if(null != id){
			if(lecturerId!=null) {
				//修改课程和讲师关联表
				List<CourseLecturer> courseLecturers = new ArrayList<>();
				String[]  strs=lecturerId.split(",");
				for(int i=0,len=strs.length;i<len;i++){
					CourseLecturer courseLecturer = new CourseLecturer();
					courseLecturer.setId(Idfactory.generate());
					courseLecturer.setCourseId(course.getId());
					courseLecturer.setLecturerId(new Long(strs[i]));
				   courseLecturers.add(courseLecturer);
					
				}
				/*for (CourseLecturer string : courseLecturers) {
					System.err.println(string.getCourseId()+"&&&&"+string.getLecturerId());
				}*/
				CourseLecturer courseLecturer = new CourseLecturer();
				courseLecturer.setCourseId(course.getId());
				//获取该课程的所有讲师id
				List<CourseLecturer> findCourseLecturer = courseLecturerMapper.findCourseLecturer(courseLecturer );
				String lecturerIds ="";
				for (CourseLecturer courseLecturer2 : findCourseLecturer) {
					lecturerIds += courseLecturer2.getLecturerId()+",";
				}
				//当前讲师和修改后的讲师作比较,如果不同，做插入操作前先删除之前的讲师
				if(!lecturerIds.equals(lecturerId)) {
					courseLecturerMapper.deleteCourseLecturerByCourseId(course.getId());
					int inserBylist = courseLecturerMapper.inserBylist(courseLecturers );
					if(inserBylist==0) {
						resp.setCode(Response.ERROR);
						resp.setMsg("课程和讲师绑定失败");
						return resp;
					}
				}
			}
				course.setGmtModified(new Date());
				//修改课程信息
				result = courseMapper.updateByPrimaryKeySelective(course);
				
				updateAttachment(course.getId(),fileIds,muchFields);
		}else{
			id = Idfactory.generate();
			course.setId(id);
			course.setGmtCreate(new Date());
			course.setIsShare(0);//默认不分享
			course.setIsRelatePlan(0);//默认不关联计划
			//添加课程
			result = courseMapper.insertSelective(course);
			updateAttachment(id,fileIds,muchFields);
			
			if(lecturerId!=null) {
				//修改课程和讲师关联表
				List<CourseLecturer> courseLecturers = new ArrayList<>();
				String[]  strs=lecturerId.split(",");
				for(int i=0,len=strs.length;i<len;i++){
					CourseLecturer courseLecturer = new CourseLecturer();
					courseLecturer.setId(Idfactory.generate());
					courseLecturer.setCourseId(course.getId());
					courseLecturer.setLecturerId(new Long(strs[i]));
				   courseLecturers.add(courseLecturer);
					
				}
				/*for (CourseLecturer string : courseLecturers) {
					System.err.println(string.getCourseId()+"&&&&"+string.getLecturerId());
				}*/
				CourseLecturer courseLecturer = new CourseLecturer();
				courseLecturer.setCourseId(course.getId());
				//获取该课程的所有讲师id
				List<CourseLecturer> findCourseLecturer = courseLecturerMapper.findCourseLecturer(courseLecturer );
				String lecturerIds ="";
				for (CourseLecturer courseLecturer2 : findCourseLecturer) {
					lecturerIds += courseLecturer2.getLecturerId()+",";
				}
				//当前讲师和修改后的讲师作比较,如果不同，做插入操作前先删除之前的讲师
				if(!lecturerIds.equals(lecturerId)) {
					courseLecturerMapper.deleteCourseLecturerByCourseId(course.getId());
					int inserBylist = courseLecturerMapper.inserBylist(courseLecturers );
					if(inserBylist==0) {
						resp.setCode(Response.ERROR);
						resp.setMsg("课程和讲师绑定失败");
						return resp;
					}
				}
			}
		}
		
		 //重新关联 资料
		//先删除关联中间表
        Map<String,Object> columnMap = new HashMap<String, Object>();
        columnMap.put("course_id",id);
        courseMaterialMapper.deleteByMap(columnMap);
        if (materialId != null && !"".equals(materialId)) {
            //重新关联中间表
            CourseMaterial courseMaterial=new CourseMaterial();
            courseMaterial.setGmtCreate(new Date());
            courseMaterial.setMaterialType(CommenConstant.VALUE_COURSE_MATERIAL);
            courseMaterial.setCourseId(id);
            //将字符串分割
            String[]  materialIds=materialId.split(",");
            List<String> list=Arrays.asList(materialIds);
            for (String string : list) {
                courseMaterial.setId(Idfactory.generate());
                courseMaterial.setMaterialId(Long.parseLong(string));
                result=courseMaterialMapper.insert(courseMaterial);
            }
        }
        
		if(result==0) {
			resp.setCode(Response.ERROR);
			resp.setMsg("课程操作失败");
			return resp;
		}
		resp.setData(course.getId().toString());
		return resp;
	}

	@Override
	public List<String> getAllShared(String courseId) {
		// 
		return courseMapper.getAllShared(courseId);
	}

	
	
	public void updateAttachment (Long id,String fileIds,String muchFields) {
	  //关联上传的附件
        if (fileIds != null && !"".equals(fileIds)){
            String[] idArr = fileIds.split(",");
            for (int j = 0; j < idArr.length; j++) {
                Attachment am =  attachmentService.selectById(Long.parseLong(idArr[j]));
                am.setHostId(id);
                attachmentService.updateById(am);
            }
        }
        
      //关联多个附件
        if (muchFields != null && !"".equals(muchFields)){
            String[] idArr = muchFields.split(",");
            for (int j = 0; j < idArr.length; j++) {
                Attachment am =  attachmentService.selectById(Long.parseLong(idArr[j]));
                am.setHostId(id);
                attachmentService.updatebyattachmentId(am);
            }
        }
	}

	@Override
	public List<Material> getMaterialList(String id) {
		return courseMapper.getMaterialList(id);
	}

    @Override
    public List<CourseShare> listCourseShareByCourseId(String courseIdStr) {
        List<CourseShare> csList = courseMapper.listCourseShareByCourseId(courseIdStr);
        return csList;
    }

    @Override
    public PageInfo<CourseCMSVo> listCourseByPage(Map<String, Object> paramMap,
            Page<CourseCMSVo> page) {
        PageInfo<CourseCMSVo> pageInfo = new PageInfo<CourseCMSVo>();
        int total = courseMapper.getCourseCount(paramMap);
        pageInfo.setRecords(courseMapper.listCourseByPage(page,paramMap));
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        pageInfo.setPageTotal(PageUtil.getPages(page.getSize(),total));
        pageInfo.setPageRecords(page.getTotal());
        return pageInfo;    
    }
}
