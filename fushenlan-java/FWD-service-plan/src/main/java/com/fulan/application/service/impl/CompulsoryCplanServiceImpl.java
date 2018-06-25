package com.fulan.application.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.course.domain.Course;
import com.fulan.api.course.service.CourseService;
import com.fulan.api.course.vo.CourseManageVo;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.service.ElPaperManageService;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.paper.vo.PaperManageVo;
import com.fulan.api.plan.domain.CompulsoryCplan;
import com.fulan.api.plan.domain.ExpiredAlarm;
import com.fulan.api.plan.domain.PlanAuthority;
import com.fulan.api.plan.domain.PlanCourse;
import com.fulan.api.plan.domain.StudyPlan;
import com.fulan.api.plan.vo.CompulsoryCplanManageVo;
import com.fulan.api.plan.vo.CompulsoryCplanVo;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.service.AttachmentService;
import com.fulan.application.mapper.CompulsoryCplanMapper;
import com.fulan.application.mapper.ExpiredAlarmMapper;
import com.fulan.application.mapper.PlanAuthorityMapper;
import com.fulan.application.mapper.PlanCourseMapper;
import com.fulan.application.mapper.StudyPlanMapper;
import com.fulan.application.orm.id.Idfactory;
import com.fulan.application.service.CompulsoryCplanService;
import com.fulan.application.util.page.PageInfo;

@Service
public class CompulsoryCplanServiceImpl extends ServiceImpl<CompulsoryCplanMapper, CompulsoryCplan> implements CompulsoryCplanService{
    @Autowired
	private CompulsoryCplanMapper compulsoryCplanMapper;
    @Autowired
    private ExpiredAlarmMapper expiredAlarmMapper;
    @Autowired
    private StudyPlanMapper studyPlanMapper;
    @Autowired
    private PlanCourseMapper planCourseMapper;
    @Autowired
    private ElPaperManageService elPaperManageService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PlanAuthorityMapper planAuthorityMapper;
    
    @Autowired
    private AttachmentService attachmentService;
	
	private  int getPages(int size,int total) {
        int pages = 0;
		if (size == 0) {
            return 0;
        }
        pages =total / size;
        if (total % size != 0) {
            pages++;
        }
        return pages;
    }
	
	
	@Override
	public PageInfo<CompulsoryCplanVo> listByPage(Page<CompulsoryCplanVo> page, String name,String tagId,String state, int pageNo,
			int pageSize) {
		PageInfo<CompulsoryCplanVo> pageInfo = new PageInfo<CompulsoryCplanVo>();
		pageInfo.setRecords(compulsoryCplanMapper.compulsoryCplanSerch(page,  name, tagId, state, pageNo, pageSize));
		int count = compulsoryCplanMapper.compulsoryCplanSerchCount( name, tagId, state);
		pageInfo.setPageNo(page.getCurrent());
		pageInfo.setPageSize(page.getSize());
		pageInfo.setPageTotal(getPages(pageSize,count));
		pageInfo.setPageRecords(page.getTotal());
		return pageInfo;
	}


	@Override
	@Transactional
	public String deleteCompulsoryCplan(String ids) {
		try {
			String [] str = ids.split(",");
			for(int i=0;i<str.length;i++){
				planCourseMapper.deletePlanCourseId(str[i]);
				planAuthorityMapper.deleteByCourseId(Long.parseLong(str[i]));
				compulsoryCplanMapper.deleteByPrimaryKey(str[i]);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}


	@Override
	@Transactional
	public String disAbleCompulsoryCplan(String ids) {
		try {
			String [] str = ids.split(",");
			for(int i=0;i<str.length;i++){
				CompulsoryCplan compulsoryCplan =compulsoryCplanMapper.selectOne(str[i]);
                if(null!=compulsoryCplan){
                	compulsoryCplan.setState(1);
                	compulsoryCplanMapper.updateByPrimaryKeySelective1(compulsoryCplan);
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}


	@Override
	@Transactional
	public String enAbleCompulsoryCplan(String ids) {
		try {
			String [] str = ids.split(",");
			for(int i=0;i<str.length;i++){
				CompulsoryCplan compulsoryCplan =compulsoryCplanMapper.selectOne(str[i]);
                if(null!=compulsoryCplan){
                	compulsoryCplan.setState(0);
                	compulsoryCplanMapper.updateByPrimaryKeySelective1(compulsoryCplan);
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}


	@Override
	@Transactional
	public String saveCompulsoryCplan(CompulsoryCplanManageVo compulsoryCplanManageVo,String fileId ,String fOtherId) {
		if(null !=compulsoryCplanManageVo){
			CompulsoryCplan compulsoryCplan = compulsoryCplanManageVo.getCompulsoryCplan();
			if(null!=compulsoryCplan){
				Long id = compulsoryCplan.getId();
				List<ExpiredAlarm> expiredAlarmList=  compulsoryCplanManageVo.getExpiredAlarmList();
				List<PlanCourse> planCourseList = compulsoryCplanManageVo.getPlanCourseList();
				List<PlanAuthority> planAuthorityList = compulsoryCplanManageVo.getPlanAuthorityList();
				if(null==id){
					id = Idfactory.generate();
					compulsoryCplan.setId(id);
					compulsoryCplan.setState(1);
					compulsoryCplan.setGmtCreate(new Date());
					compulsoryCplan.setGmtModified(new Date());
					compulsoryCplanMapper.insert(compulsoryCplan);
					if(""!=fileId){
						Attachment	attachment=	attachmentService.selectById(Long.parseLong(fileId));
						if(null !=attachment){
							attachment.setHostId(id);
							attachmentService.updateById(attachment);
						}
					}
					if(""!=fOtherId){
						Attachment	attachment=	attachmentService.selectById(Long.parseLong(fOtherId));
						if(null !=attachment){
							attachment.setHostId(id);
							attachmentService.updateById(attachment);
						}
					}
				}else{
					compulsoryCplan.setGmtModified(new Date());
					compulsoryCplanMapper.updateByPrimaryKeySelective(compulsoryCplan);
					expiredAlarmMapper.deleteCourseId(compulsoryCplan.getId().toString());
					planCourseMapper.deletePlanCourseId(compulsoryCplan.getId().toString());
					planAuthorityMapper.deleteByCourseId(compulsoryCplan.getId());
					
					if(""!=fOtherId){
						Attachment	attachment=	attachmentService.selectById(Long.parseLong(fOtherId));
						attachment.setHostId(id);
						attachmentService.updateById(attachment);
					}
				}
				if(null !=expiredAlarmList){
					for(ExpiredAlarm expiredAlarm : expiredAlarmList){
						if(null != expiredAlarm.getBeforeDay1()){
							expiredAlarm.setId(Idfactory.generate());
							expiredAlarm.setCourseId(compulsoryCplan.getId());
							expiredAlarmMapper.insert(expiredAlarm);
						}
					}
				}
				if(null != planCourseList){
					for(PlanCourse planCourse : planCourseList){
						if(null != planCourse.getAssociateId()){
							planCourse.setId(Idfactory.generate());
							planCourse.setCourseType(4);
							planCourse.setPlanId(compulsoryCplan.getId());
							planCourseMapper.insert(planCourse);
						}
					}
				}
				if(null != planAuthorityList){
					for(PlanAuthority planAuthority  :planAuthorityList ){
						if( null !=planAuthority.getAuthorityType()){
							planAuthority.setId(Idfactory.generate());
							planAuthority.setCourseType(4);
							planAuthority.setCourseId(compulsoryCplan.getId());
							planAuthority.setGmtCreate(new Date());
							planAuthorityMapper.insert(planAuthority);
						}
					}
				}
			return id+"";
			}
		}
		return null;
	}


	@Override
	public CompulsoryCplanManageVo selectById(String id) {
		return compulsoryCplanMapper.selectById(Long.parseLong(id));
	}


	@Override
	public List<Course> selectByElspId(String type) {
		
		return studyPlanMapper.selectByElspId(type);
	}


	@Override
	public List<Paper> selectAllPaper() {
		return elPaperManageService.selectAllList();
	}


	@Override
	public List<CourseManageVo> selectByCId(String cId) {
		return courseService.selectByCId(cId);
	}

	@Override
	public List<PaperManageMinVo> selectBycId(String cId) {
		return elPaperManageService.selectBycId(cId);
	}


	@Override
	public CompulsoryCplan compulsoryCplanById(Long id) {
		return compulsoryCplanMapper.selectOne(String.valueOf(id));
	}


	@Override
	public String selectMaxByPlanId(String id) {
		
		return planCourseMapper.selectMaxByPlanId(id);
	}

}
