package com.fulan.application.manage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.message.domain.SmsBlackUser;
import com.fulan.api.message.domain.SmsParameterFactor;
import com.fulan.api.message.vo.SmsBusinessParameterVO;
import com.fulan.application.service.customer.SmsService;
import com.fulan.application.service.system.SmsFactorService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.spring.SpringUtil;
import com.fulan.application.util.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 短信因子控制层
 * @author: guiyang
 * @date: 2018/3/5 11:03
 */
@Api(tags = "SmsFactorApi", description = "短信因子控制层")
@Controller
@RequestMapping("/manage/factor")
public class SmsFactorController {

    private static final Logger logger = LoggerFactory.getLogger(SmsFactorController.class);

    @Autowired
    private SmsFactorService smsFactorService;

    /**
     * 短信因子查询
     */
    @ApiOperation(value = "短信因子查询", notes = "短信因子查询", response = Response.class)
    @PostMapping("selectSmsFactor")
    @ResponseBody
    public Response<Object> selectSmsFactor(){
        try {
            Response<Object> response = new Response<>(Response.SUCCESS, "短信因子查询成功");
            List<SmsParameterFactor> smsParameterFactors = smsFactorService.selectSmsFactor();
            response.setData(smsParameterFactors);
            return response;
        }catch (Exception e){
            logger.error("----短信因子查询失败----" + e.getMessage());
            e.printStackTrace();
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    /**
     * 短信因子添加
     */
    @ApiOperation(value = "短信因子添加", notes = "短信因子添加", response = Response.class)
    @PostMapping("saveSmsFactor")
    @ResponseBody
    public Response<String> saveSmsFactor(@RequestBody SmsParameterFactor smsParameterFactor){
        try {
            smsFactorService.saveSmsFactor(smsParameterFactor);
            return new Response<>(Response.SUCCESS, "短信因子添加成功");
        }catch (Exception e){
            logger.error("----短信因子添加成功失败----" + e.getMessage());
            e.printStackTrace();
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    /**
     * 短信因子添加
     */
    @ApiOperation(value = "短信因子修改", notes = "短信因子修改", response = Response.class)
    @PostMapping("updateSmsFactor")
    @ResponseBody
    public Response<String> updateSmsFactor(@RequestBody SmsParameterFactor smsParameterFactor){
        try {
            smsFactorService.updateSmsFactor(smsParameterFactor);
            return new Response<>(Response.SUCCESS, "短信因子修改成功");
        }catch (Exception e){
            logger.error("----短信因子修改成功失败----" + e.getMessage());
            e.printStackTrace();
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    /**
     * 短信因子删除
     */
    @ApiOperation(value = "短信因子删除", notes = "短信因子删除", response = Response.class)
    @PostMapping("deleteSmsFactor")
    @ResponseBody
    public Response<String> deleteSmsFactor(@RequestParam Long id){
        try {
            smsFactorService.deleteSmsFactor(id);
            return new Response<>(Response.SUCCESS, "短信因子删除成功");
        }catch (Exception e){
            logger.error("----短信因子删除失败----" + e.getMessage());
            e.printStackTrace();
            return new Response<>(Response.ERROR, e.getMessage());
        }
    }

    /**
	 * 查询参数列表
	 */
	@RequestMapping(value="/selectParameter",method=RequestMethod.GET)
	@ResponseBody
	public PageInfo<SmsParameterFactor> selectParameter(
            @RequestParam(value="pageNo",defaultValue="1") int pageNo,
            @RequestParam(value="pageSize",defaultValue="10") int pageSize){
		Page<SmsParameterFactor> page = new Page<SmsParameterFactor>(pageNo, pageSize);
		PageInfo<SmsParameterFactor> pageInfo = smsFactorService.selectParameter(page, pageNo, pageSize);
		return pageInfo;
	}
	
	
	
	/**
	 * 查询参数根据id
	 */
	@RequestMapping(value="/selectParameterById",method=RequestMethod.GET)
	@ResponseBody
	public SmsParameterFactor selectParameterById(@RequestParam String id){
		SmsParameterFactor smsParameterFactor=smsFactorService.selectParameterById(id);
		return smsParameterFactor;
	}
	
	/**
	 * 查询参数集合
	 */
	@RequestMapping(value="/listFactor",method=RequestMethod.GET)
	@ResponseBody
	public List<SmsParameterFactor> listFactor(){
		
		List<SmsParameterFactor> list=smsFactorService.listFactor();
		return list;
	}
}