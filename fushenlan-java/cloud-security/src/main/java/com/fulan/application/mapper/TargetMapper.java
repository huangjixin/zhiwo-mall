package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.Target;

/**
 * <p>
 * 代理人目标 Mapper 接口
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Mapper
public interface TargetMapper extends BaseMapper<Target> {

	/**
	 * selectYeaytargetbyagentCode
	 * 通过代理人code查找代理人年目标
	 * @warn(注意事项 – 可选)
	 * @param map
	 * @return
	 */
	@Select(" select sum(t.target_value)  from  target  t  where t.account_id=#{accountId}")
	int selectYeaytargetbyagentCode(@Param("accountId")long accountId);
	
	
	@Select(" select  a.id  from   account  a where  a.org_id  = #{orgId}")
	List<Long>  selectallaccountIds(@Param("orgId")String orgId);
	
	
}
