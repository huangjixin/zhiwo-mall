package com.fulan.application.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.fulan.api.system.domain.Level;
import com.fulan.application.mapper.LevelMapper;
import com.fulan.application.service.LevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImpl extends ServiceImpl<LevelMapper, Level> implements LevelService{
    @Autowired
    private LevelMapper levelMapper;

    @Override
    public List<Level> getLevelList() {
        return levelMapper.getLevelList();
    }

	@Override
	public List<Level> getLevelListDeve(Long type) {
		return levelMapper.getLevelListDeve(type);
	}

	@Override
	public Level selectOne(String id) {
		
		return levelMapper.selectById(id);
	}

	@Override
	public Level selectByLevelCode(String levelCode) {
		
		return levelMapper.selectByLevelCode(levelCode);
	}
    

}
