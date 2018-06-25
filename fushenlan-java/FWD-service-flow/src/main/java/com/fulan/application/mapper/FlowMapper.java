package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.flow.domain.Flow;
import com.fulan.api.flow.vo.FlowPersonnelVO;
import com.fulan.api.flow.vo.FlowVO;
import com.fulan.api.flow.vo.PersonnelAddVo;

/**
 * <p>
 * 面试流程 Mapper 接口
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@Mapper
public interface FlowMapper extends BaseMapper<Flow> {

	
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
     * PersonnelSearchbyagentCode
     * 我的增员
     * @warn(注意事项 – 可选)
     * @param page
     * @param agentCode
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<PersonnelAddVo> PersonnelSearchbyagentCode(Page<PersonnelAddVo> page,
    													@Param("orgId") String orgId,
    													@Param("agentCode")String agentCode,
    													@Param("keyWord")String keyWord, 
													    @Param("pageNo")int pageNo, 
													    @Param("pageSize")int pageSize);
    /**
     * PersonnelpaperSearchbyParam
     * 我的面试
     * @warn(注意事项 – 可选)
     * @param page
     * @param agentCode
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<PersonnelAddVo> PersonnelpaperSearchbyParam(Page<PersonnelAddVo> page,
    													@Param("orgId") String orgId,
														@Param("agentCode")String agentCode,
														@Param("keyWord")String keyWord, 
													    @Param("pageNo")int pageNo, 
													    @Param("pageSize")int pageSize);
	
    
    /**
     * PersonnelSignSearchbyParam
     * 我的签约
     * @warn(注意事项 – 可选)
     * @param page
     * @param orgId
     * @param agentCode
     * @param keyWord
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<PersonnelAddVo> PersonnelSignSearchbyParam(Page<PersonnelAddVo> page,
			@Param("orgId") String orgId,
			@Param("agentCode")String agentCode,
			@Param("keyWord")String keyWord, 
		    @Param("pageNo")int pageNo, 
		    @Param("pageSize")int pageSize);
    
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
	
	
}
