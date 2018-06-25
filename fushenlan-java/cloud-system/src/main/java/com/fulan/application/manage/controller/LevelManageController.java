package com.fulan.application.manage.controller;

import com.fulan.api.system.domain.Level;
import com.fulan.application.service.LevelService;
import com.fulan.application.util.domain.Response;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/manage/level")
public class LevelManageController {
    private static Logger logger = LoggerFactory.getLogger(LevelManageController.class);

    @Autowired
    private LevelService levelService;


    /**
     * 获取职位列表
     * @return
     */
    @ApiOperation(value = "获取职位列表", notes = "获取职位列表", response = Level.class)
    @RequestMapping(value = "/getLevelList")
    public Response<List<Level>> getCommentList() {
        Response<List<Level>> res =new Response<List<Level>>();
        try {
            logger.info("-------------------"+"获取职位列表"+"----------------------");
            //分页获取学习评价列表
            List<Level> levelList = levelService.getLevelList();
            res.setData(levelList);
            res.setCode(Response.SUCCESS);
            res.setMsg(Response.SUCCESS_MESSAGE);
        } catch (Exception e) {
            logger.error("", e);
            res.setCode(Response.ERROR);
            res.setMsg(Response.ERROR_MESSAGE);
            throw e;
        }
        logger.info("---------------------"+res.getMsg()+"---------------------");
        return res;
    }
    
    
    
    @GetMapping("/getLevelListDeve")
    @ResponseBody
    public List<Level>  getLevelListDeve(@RequestParam("type") Long type ){
    	List<Level> levelList=null;
    	 try {
    	logger.info("-------------------"+"获取职位列表"+"----------------------");
    	 levelList =levelService.getLevelListDeve(type);
    	 } catch (Exception e) {
        logger.error("", e);
         }
       return levelList;
    }
    
    
    @GetMapping("/selectOne")
    @ResponseBody
    public Level selectOne(@RequestParam("id") String id ){
		return levelService.selectById(id);
    	
    }
    
    @GetMapping("/selectByLevelCode")
    @ResponseBody
    public Level selectByLevelCode(@RequestParam("levelCode") String levelCode ){
		return levelService.selectByLevelCode(levelCode);
    	
    }
    
}
