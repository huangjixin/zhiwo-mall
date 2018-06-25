package com.fulan.application.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.system.domain.Level;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LevelMapper extends BaseMapper<Level> {
    List<Level> getLevelList();
    
    List<Level> getLevelListDeve(Long type);
    
    Level selectByLevelCode(@Param("levelCode") String levelCode);
    

}
