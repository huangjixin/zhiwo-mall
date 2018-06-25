package com.fulan.application.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.paper.domain.el.Paper;
import com.fulan.api.paper.vo.ElPaperVo;
import com.fulan.api.paper.vo.PaperManageMinVo;
import com.fulan.api.paper.vo.PaperPlanNameVo;

@Mapper
public interface ElPaperMapper extends BaseMapper<Paper>{
	List<ElPaperVo> listForManage(Page<ElPaperVo> page,@Param("type") String type,@Param("pubType") String pubType,@Param("submitter") String submitter,@Param("createUser") String createUser,@Param("name") String name,
			  @Param("groupId") String groupId,@Param("tagId") String tagId,
			  int pageNo, int pageSize);
	
	Integer listForManageCount(@Param("type") String type,@Param("pubType") String pubType,@Param("submitter") String submitter,@Param("createUser") String createUser,@Param("name") String name,
			@Param("groupId") String groupId,@Param("tagId") String tagId);
	
	Integer deleBatchRelation(List<Long> list);
	
	Integer deleBatchRelationEPQS(List<Long> list);
	
	List<ElPaperVo> waitForReview(Page<ElPaperVo> page,@Param("name") String name,@Param("paperState") String paperState,@Param("userName") String userName,
			@Param("groupId") String groupId,@Param("tagId") String tagId,
			int pageNo, int pageSize);
	
	Integer waitForReviewCount(@Param("name") String name,@Param("paperState") String paperState,@Param("userName") String userName,
			@Param("groupId") String groupId,@Param("tagId") String tagId);
	
	List<ElPaperVo> seleByIdVo(String id);
	
	List<ElPaperVo> seleByIdPublic(String id);
	
	int deleteShar(String CourseId);
	
	int share(Map<String, String> parms);
	
	int shareOther(Map<String, String> parms);
	
	List<ElPaperVo> seleByIdWaitFor(String id);
	
	ElPaperVo seleByIdWaitForManage(@Param("id")String id,@Param("userId")String userId,@Param("examNum")String examNum,@Param("planCourseId")String planCourseId);
	
	List<PaperManageMinVo> selectBycId(@Param("cId")Long cId);
	
	List<Paper> seleByGroupId(@Param("submitter")Integer submitter,@Param("name") String name,@Param("type") Integer type);

	Integer updateByIdList(@Param("idList")List<Long> idList,@Param("isShare")String isShare);

	List<PaperPlanNameVo> selectPlanName(List<Long> idList);
	
	/**
	 *后台列表查询 
	 * @param map
	 * @return
	 */
	List<ElPaperVo>listPaperByPage(Page<ElPaperVo> page,Map<String,Object> map);
	
	Integer listPaperCount(Map<String,Object> map); 
}
