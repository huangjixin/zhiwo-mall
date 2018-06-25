package com.fulan.application.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsTemplate;
import com.fulan.api.message.vo.SmsTemplateResultVO;
import com.fulan.api.message.vo.SmsTemplateVO;
import com.fulan.application.service.system.SmsTemplateService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import com.fulan.application.util.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 消息模板管理控制层
 * @author: guiyang
 * @date: 2018/3/5 11:03
 */
@Api(tags = "SmsTemplateApi", description = "消息模板接口")
@Controller
@RequestMapping("/manage/smsTemplate")
public class SmsTemplateController {

    private static final Logger logger = LoggerFactory.getLogger(SmsTemplateController.class);

    @Autowired
    private SmsTemplateService smsTemplateService;

    @ApiOperation(value = "查询消息模板列表", notes = "查询消息模板列表", response = Response.class)
    @PostMapping("selectSmsTemplate")
    @ResponseBody
    public Page<SmsTemplateResultVO> selectSmsTemplate(SmsTemplateVO smsTemplateVO){
        try {
            return smsTemplateService.selectSmsTemplate(smsTemplateVO);
        }catch (Exception e){
            logger.error("----查询消息模板列表失败----");
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "查询消息模板详情", notes = "查询消息模板详情", response = Response.class)
    @PostMapping("selectSmsTemplateById")
    @ResponseBody
    public SmsTemplate selectSmsTemplateById(@RequestParam Long id){
        try {
            return smsTemplateService.selectSmsTemplateById(id);
        }catch (Exception e){
            logger.error("----查询消息模板详情失败----");
            e.printStackTrace();
            return null;
        }
    }

    @ApiOperation(value = "插入消息模板", notes = "插入消息模板", response = Response.class)
    @PostMapping("saveSmsTemplate")
    @ResponseBody
    public Response<String> saveSmsTemplate(@RequestBody SmsTemplate smsTemplate){
        try {
            if (StringUtils.isEmptry(smsTemplate.getCode())){
                return new Response<>(Response.ERROR,"请填写code码");
            }
            if (StringUtils.isEmptry(smsTemplate.getContent())){
                return new Response<>(Response.ERROR,"请填写模板内容");
            }
            smsTemplateService.saveSmsTemplate(smsTemplate);
        }catch (Exception e){
            logger.error("----插入消息模板失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR, e.getMessage());
        }
        return new Response<>(Response.SUCCESS,"插入消息模板成功");
    }

    @ApiOperation(value = "删除消息模板", notes = "删除消息模板", response = Response.class)
    @PostMapping("deleteSmsTemplateById")
    @ResponseBody
    public Response<String> deleteSmsTemplateById(@RequestParam Long id){
        try {
            smsTemplateService.deleteSmsTemplateById(id);
        }catch (Exception e){
            logger.error("----删除消息模板失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"删除消息模板失败,传入参数:id = "+id);
        }
        return new Response<>(Response.SUCCESS,"删除消息模板成功");
    }

    @ApiOperation(value = "修改消息模板", notes = "修改消息模板", response = Response.class)
    @PostMapping("updateSmsTemplate")
    @ResponseBody
    public Response<String> updateSmsTemplate(@RequestBody SmsTemplate smsTemplate){
        try {
            smsTemplateService.updateSmsTemplate(smsTemplate);
        }catch (Exception e){
            logger.error("----修改消息模板失败----");
            e.printStackTrace();
            return new Response<>(Response.ERROR,"修改消息模板失败");
        }
        return new Response<>(Response.SUCCESS,"修改消息模板成功");
    }
    
    
    
    /**
	 * 查询黑名单列表
	 */
	@RequestMapping(value="/selectTemplateList",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<SmsTemplateResultVO> selectTemplateList(
			@RequestParam(value="title",required=false) String title,
     		@RequestParam(value="code",required=false) String code,
     		@RequestParam(value="type",required=false) String type,
     		@RequestParam(value="masterCode",required=false) String masterCode,
     		@RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Page<SmsTemplateResultVO> page = new Page<SmsTemplateResultVO>(pageNo, pageSize);
		PageInfo<SmsTemplateResultVO> pageInfo = smsTemplateService.selectTemplateList(page, title,type,code,masterCode, pageNo, pageSize);
		return pageInfo;
	}
    
    
    
    
}