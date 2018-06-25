package com.fulan.application.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.security.domain.TagTarget;

@Repository
public interface GroupTagMapper extends BaseMapper<TagTarget> {
    
    int deleteTagTarget(@Param("hostId") Long hostId,@Param("type")Integer type);
    TagTarget findByTagId(@Param("tagId")Long tagId);
}
