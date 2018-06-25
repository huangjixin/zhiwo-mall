package com.fulan.application.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.flow.domain.Flow;
import com.fulan.api.flow.domain.FlowAction;
import com.fulan.api.flow.vo.FlowAcceptVo;
import com.fulan.api.flow.vo.FlowPersonnelVO;
import com.fulan.api.flow.vo.FlowVO;
import com.fulan.api.security.domain.Account;
import com.fulan.application.mapper.FlowActionMapper;
import com.fulan.application.mapper.FlowMapper;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.FlowService;
import com.fulan.application.session.SessionUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

/**
 * <p>
 * 面试流程 服务实现类
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-24
 */
@Service
@Transactional
public class FlowServiceImpl extends ServiceImpl<FlowMapper, Flow> implements FlowService {

	@Autowired
	FlowMapper flowMapper;
	
	@Autowired
	FlowActionMapper flowActionMapper;
	
	@Autowired
	IdGenerator idGenerator;
	
	@Autowired
	RedisUtil redisUtil;
	
	
	/**
	 * flowNodebyParam
	 * 通过人才ID和分公司ID查询流程节点和用走过那些流程
	 * @warn(注意事项 – 可选)
	 * @param personnelId
	 * @param orgId
	 * @return
	 */
	public List<FlowVO>	flowNodebyParam (String personnelId, String orgId){
		
		
		return flowMapper.flowNodebyParam(personnelId, orgId);
	}
	
	
	
	 /**
	    * flowPersonnelDetail
	    * 面试详情
	    * @warn(注意事项 – 可选)
	    * @param personnelId
	    * @param flowItemId
	    * @return
	    */
	public   List<FlowPersonnelVO> flowPersonnelDetail(@Param("personnelId")Long personnelId,
												  @Param("flowItemId")Long flowItemId
														   ){
		return flowMapper.flowPersonnelDetail(personnelId, flowItemId);
	}



	@Override
	public Response<PageInfo<Flow>> listByPage(Flow flow, int pageNo, int pageSize, String pageSortFiled,
			String pageSortType) {
		// TODO Auto-generated method stub
		
		
		Flow searchFlow = new Flow();
		/*if(flow != null&& flow.getFlowName() != null&&!"".equals(flow.getFlowName())){
			searchFlow.setFlowName(flow.getFlowName());
		}*/
		
        Page<Flow> page = new Page<Flow>(pageNo, pageSize);
        page.setOrderByField(pageSortFiled);
        page.setAsc(pageSortType.equals("asc") ? true : false);
        EntityWrapper<Flow> ew = new EntityWrapper<Flow>(searchFlow);
        if (StringUtils.isNotEmpty(flow.getFlowName())) {
            ew.like("flow_name",flow.getFlowName() );
        }
        if (StringUtils.isNotEmpty(flow.getHeadFlag())) {
            ew.eq("head_flag", flow.getHeadFlag());
        }else{
        	ew.eq("head_flag", 'N');
        }
        if(!"B0311".equals(flow.getOrgId())){
        	ew.eq("org_id", flow.getOrgId());
		}
        Response<PageInfo<Flow>> resp=new Response<PageInfo<Flow>>(Response.SUCCESS, "获取流程信息列表");
        PageInfo<Flow> pageInfo = new PageInfo<Flow>();
        Page<Flow> flowPage = this.selectPage(page, ew);
        pageInfo.setRecords(flowPage.getRecords());
        pageInfo.setPageSize(pageSize);
        pageInfo.setPageTotal(flowPage.getPages());
        pageInfo.setPageNo(flowPage.getCurrent());
        resp.setData(pageInfo);
        return resp;
	}



	@Override
	public Response<Integer> insertOrUpdateRecord(FlowAcceptVo flowAcceptVo) {
		// TODO Auto-generated method stub
		Flow flow = flowAcceptVo.getFlow();
		Long flowId = idGenerator.generate();
		if(flow.getId()!=null){
			flow.setUpdateTime(new Date());
			flow.setUpdateUser((long)201882300);
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("flow_id", flow.getId());
			flowActionMapper.deleteByMap(columnMap);
			flowId = flow.getId();
		}else{
			flow.setHeadFlag("N");
			flow.setUpdateTime(new Date());
			flow.setUpdateUser((long)201882300);
			flow.setCreateTime(new Date());
			flow.setCreateUser((long)201882300);
			flow.setId(flowId);
		}
		this.insertOrUpdate(flow);
		List<FlowAction>  flowActionList = flowAcceptVo.getFlowActionList();
		for(FlowAction fa : flowActionList){
			
			fa.setCreateTime(new Date());
			fa.setCreateUser((long)201882300);
			fa.setUpdateTime(new Date());
			fa.setUpdateUser((long)201882300);
			
			fa.setId(idGenerator.generate());
			fa.setFlowId(flowId);
			fa.setCreateTime(new Date());
			flowActionMapper.insertSelective(fa);
		}
		Response<Integer> resp=new Response<Integer>(Response.SUCCESS, "插入数据");
		return resp;
	}



	@Override
	public Response<Integer> deleteFlow(long flowId) {
		// TODO Auto-generated method stub
		flowMapper.deleteById(flowId);
		Map<String,Object> columnMap= new HashMap<String,Object>();
		columnMap.put("flow_id", flowId);
		flowActionMapper.deleteByMap(columnMap);
		Response<Integer> resp=new Response<Integer>(Response.SUCCESS, "删除数据");
		return resp;
	}



	@Override
	public Response<Flow> selectFlowById(Flow flow) {
		// TODO Auto-generated method stub
		Response<Flow> resp=new Response<Flow>(Response.SUCCESS, "查询流程数据");
		resp.setData(flowMapper.selectOne(flow));
		return resp;
	}
}
