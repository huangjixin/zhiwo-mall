package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.api.message.vo.SmsBlackUserVO;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SmsBlackUserMapper extends BaseMapper<SmsBlackUser>{

    List<SmsBlackUser> selectUserByPage(SmsBlackUserVO blackUserVO);

	int listForManageCount(@Param("phone") String phone,@Param("type") String type);

	List<SmsBlackUser> listForManage( Page<SmsBlackUser> page,@Param("phone") String phone,@Param("type") String type, @Param("pageNo") int pageNo,@Param("pageSize") int pageSize);

}