package com.fulan.application.custom.controller;

import com.fulan.api.security.domain.Account;
import com.fulan.api.system.domain.Level;
import com.fulan.application.redis.RedisUtil;
import com.fulan.application.service.LevelService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.cat.ServerStarterListenerForCat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "LevelApi", description = "职级")
@RestController
@RequestMapping(value = "/customer/level")
public class LevelController {
    private static Logger logger = LoggerFactory.getLogger(LevelController.class);

    @Autowired
    private LevelService levelService;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 获取职位列表
     * @return
     */
    @ApiOperation(value = "获取职位列表", notes = "获取职位列表", response = Level.class)
    @GetMapping(value = "/getLevelList")
    public Response<List<Level>> getCommentList() {
        Response<List<Level>> res =new Response<List<Level>>();
        try {
            logger.info("-------------------"+"获取职位列表"+"----------------------");
            Account account = (Account) redisUtil.getUserInfo();
            List<Level> levelList =levelService.getLevelListDeve(Long.parseLong(account.getAccountType()));
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

}

