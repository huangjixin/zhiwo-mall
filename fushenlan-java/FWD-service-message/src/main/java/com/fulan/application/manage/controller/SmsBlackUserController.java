package com.fulan.application.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.api.message.vo.SmsBlackUserVO;
import com.fulan.application.service.system.SmsBlackUserService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 短信手机号码黑名单控制层
 * @author: guiyang
 * @date: 2018/3/5 11:03
 */
@Api(tags = "SmsBlackUserApi", description = "短信手机号码黑名单接口")
@Controller
@RequestMapping("/manage/smsBlackUser")
public class SmsBlackUserController {

    private static final Logger logger = LoggerFactory.getLogger(SmsBlackUserController.class);

    @Autowired
    private SmsBlackUserService smsBlackUserService;

    @ApiOperation(value = "查询黑名单列表", notes = "查询黑名单列表", response = Response.class)
    @PostMapping("querySmsBlackUser")
    @ResponseBody
    public Page<SmsBlackUser> querySmsBlackUser(@RequestBody SmsBlackUserVO smsBlackUserVO){
        try {
            return smsBlackUserService.selectSmsBlackUser(smsBlackUserVO);
        }catch (Exception e){
            logger.error("----查询黑名单列表失败----");
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "批量插入黑名单", notes = "批量插入黑名单", response = Response.class)
    @PostMapping("saveSmsBlackUser")
    @ResponseBody
    public Response<String> saveSmsBlackUser(@RequestBody List<SmsBlackUser> smsBlackUserList){
        try {
            smsBlackUserService.saveSmsBlackUser(smsBlackUserList);
        }catch (Exception e){
            logger.error("----批量插入黑名单失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"批量插入黑名单失败");
        }
        return new Response<>(Response.SUCCESS,"批量插入黑名单成功");
    }

    @ApiOperation(value = "删除黑名单", notes = "删除黑名单", response = Response.class)
    @PostMapping("deleteSmsBlackUserById")
    @ResponseBody
    public Response<String> deleteSmsBlackUserById(@RequestParam Long id){
        try {
            smsBlackUserService.deleteSmsBlackUserById(id);
        }catch (Exception e){
            logger.error("----删除黑名单失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"删除黑名单失败,传入参数:id = "+id);
        }
        return new Response<>(Response.SUCCESS,"删除黑名单成功");
    }

    @ApiOperation(value = "批量删除黑名单", notes = "批量删除黑名单", response = Response.class)
    @PostMapping("deleteBatchSmsBlackUser")
    @ResponseBody
    public Response<String> deleteBatchSmsBlackUser(@RequestBody List<Long> ids){
        try {
            smsBlackUserService.deleteBatchSmsBlackUser(ids);
        }catch (Exception e){
            logger.error("----批量删除黑名单失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"批量删除黑名单失败");
        }
        return new Response<>(Response.SUCCESS,"批量删除黑名单成功");
    }
    
    
    /**
	 * 查询黑名单列表
	 */
	@RequestMapping(value="/selectSmsList",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<SmsBlackUser> selectSmsList(
            @RequestParam(value="phone",required=false) String phone,
            @RequestParam(value="type",required=false) String type,
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Page<SmsBlackUser> page = new Page<SmsBlackUser>(pageNo, pageSize);
		PageInfo<SmsBlackUser> pageInfo = smsBlackUserService.selectSmsList(page, phone,type, pageNo, pageSize);
		return pageInfo;
	}
    
    
}