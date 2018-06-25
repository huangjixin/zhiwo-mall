package com.fulan.api.system.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.system.domain.Dictionary;
import com.fulan.api.system.domain.Level;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 职业等级
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
@FeignClient(name = "system")
public interface LevelService {


    //查询职业集合
    @GetMapping(value="/manage/level/selectDictionaryById")
    public List<Level> selectDictionaryById(@RequestParam("id") Long id);
    
    
    @GetMapping("manage/level/getLevelListDeve")
   	List<Level> getLevelListDeve(@RequestParam("type") Long type);
    
    @GetMapping("manage/level/selectOne")
    Level selectOne(@RequestParam("id") String id);
    
    @GetMapping("manage/level/selectByLevelCode")
    Level selectByLevelCode(@RequestParam("levelCode") String levelCode);
    
    @GetMapping("/manage/level/getLevelList")
    Response<List<Level>> getLevelList();
    
}
