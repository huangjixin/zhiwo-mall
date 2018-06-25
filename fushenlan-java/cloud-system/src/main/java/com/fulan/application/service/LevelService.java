package com.fulan.application.service;


import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.system.domain.Level;

import java.util.List;

public interface LevelService extends IService<Level> {
    List<Level> getLevelList();
    
    /**
	 * 查询职业集合
	 * @return
	 */
	public List<Level> getLevelListDeve(Long type);
	
	Level selectOne(String id);
	
	Level selectByLevelCode(String levelCode);
	
}
