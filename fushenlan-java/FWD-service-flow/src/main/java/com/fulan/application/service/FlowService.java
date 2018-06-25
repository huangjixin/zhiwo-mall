package com.fulan.application.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fulan.api.flow.domain.Flow;
import com.fulan.api.flow.vo.FlowAcceptVo;
import com.fulan.api.flow.vo.FlowPersonnelVO;
import com.fulan.api.flow.vo.FlowVO;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 面试流程 服务类
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
public interface FlowService  {

	
	
	
	
	/**
	 * flowNodebyParam
	 * 通过人才ID和分公司ID查询流程节点和用走过那些流程
	 * @warn(注意事项 – 可选)
	 * @param personnelId
	 * @param orgId
	 * @return
	 */
	List<FlowVO>	flowNodebyParam (@Param("personnelId")String personnelId, 
									 @Param("orgId")String orgId);
	
	
	
	
	 /**
	    * flowPersonnelDetail
	    * 面试详情
	    * @warn(注意事项 – 可选)
	    * @param personnelId
	    * @param flowItemId
	    * @return
	    */
	 List<FlowPersonnelVO> flowPersonnelDetail(@Param("personnelId")Long personnelId,
												  @Param("flowItemId")Long flowItemId
														   );
	 
	 /**
	 * 流程管理列表
	 * @param flowItem
	 * @param page
	 * @return Page<FlowItem>
	 * **/
      Response<PageInfo<Flow>> listByPage(Flow flow,int pageNo, int pageSize, String pageSortFiled, String pageSortType);
      
      Response<Integer>  insertOrUpdateRecord(FlowAcceptVo flowAcceptVo);
      
      Response<Integer>  deleteFlow(long flowId);
      
      Response<Flow>  selectFlowById(Flow flow);
      
}
