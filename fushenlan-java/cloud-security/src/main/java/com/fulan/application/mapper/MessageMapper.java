package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.Message;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chenzhuang123
 * @since 2018-01-23
 */
@Repository
public interface MessageMapper extends BaseMapper<Message> {
	@Select("select * from message where account_id=#{accountId} order by create_time desc LIMIT 6")
	List<Message> selectByAccountId(long accountId);
}
