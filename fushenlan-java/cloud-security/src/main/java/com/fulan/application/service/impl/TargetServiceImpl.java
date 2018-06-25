package com.fulan.application.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.security.domain.Target;
import com.fulan.application.mapper.TargetMapper;
import com.fulan.application.orm.id.GenerateVOUtil;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.TargetService;
import com.fulan.application.util.domain.Response;


/**
 * <p>
 * 代理人目标 服务实现类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Service
public class TargetServiceImpl extends ServiceImpl<TargetMapper, Target> implements TargetService {

	
	/*@Autowired
	private IdGenerator idGenerator;
	*/
	@Autowired
    private TargetMapper targetMapper;
	
	
	@Override
	public Response<String>  insertDetail(Target target) {
		
			Target erTargetExample = new Target();
			
			erTargetExample.setAccountId(target.getAccountId());//代理人id
			erTargetExample.setTargetTime(target.getTargetTime());// 目标时间
		
		 int num = targetMapper.selectCount(new EntityWrapper<Target>(erTargetExample));
		
		 if(num >0){
			
			 	baseMapper.updateById(target);
			 	Response<String> resp=new Response<String>(Response.SUCCESS, "更新代理人目标成功");
				resp.setCode(Response.SUCCESS);
				return resp;
		 }else{ 
			 // 设置id、设置默认可用
			 	//target.setId( idGenerator.generate()); 	
			 	GenerateVOUtil.generate(target);
			 	targetMapper.insert(target);
			 	Response<String> resp=new Response<String>(Response.SUCCESS, "新增代理人目标成功");
				resp.setCode(Response.SUCCESS);
				return resp;
		 }
	}

	
	public  void update(Target target){
		
		baseMapper.updateById(target);
	}
	



	@Override
	public Target selectMonthlytargetbyAgentCode(long accountId, String month) {
		
		Target t = new Target();
		t.setAccountId(accountId);//代理人id 
		t.setTargetTime(month);
		return targetMapper.selectOne(t);	
	}


	/**
	 * 
	 * 查询代理人年目标
	 * @see com.fulan.erecruitment.target.service.TargetService#selectYeaytargetbyagentCode(java.lang.String)
	 */
	@Override
	public int selectYeaytargetbyagentCode(long accountId) {
		
		int yeaytarget  = targetMapper.selectYeaytargetbyagentCode(accountId);
		return yeaytarget;
	}
		

	@Override
	public List<Long>  selectallaccountIds(String orgId) {
		
		List<Long> TeamSize  = targetMapper.selectallaccountIds(orgId);
		return TeamSize;
	}
		
	
	/**
	 * targetList
	 * 通过代理人id查找目标
	 * @warn(注意事项 – 可选)
	 * @param accountId
	 * @return
	 */
	public List<Target> selecttargetListbyaccountId(Long accountId,String targetYear){
		
		Map<String,Object> columnMap=new HashMap<>();
		columnMap.put("account_id", accountId);
		columnMap.put("target_year", targetYear);
		List<Target> targetlist=targetMapper.selectByMap(columnMap);

		return targetlist;
	}
}
