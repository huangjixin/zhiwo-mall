package com.fulan.application.index.controller;

import com.fulan.application.agent.controller.AgentController;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.log.annotation.NoLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Description:
 * @author: guiyang
 * @date: 2018/4/18 14:15
 */
@NoLog
@Api(tags = "Index", description = "首页照会，客户经营，移动审批，回执回访查询")
@RestController
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(AgentController.class);

    @ApiOperation(value = "首页照会，客户经营，移动审批，回执回访查询", notes = "首页照会，客户经营，移动审批，回执回访查询", response = Response.class)
    @RequestMapping(value = "/index",produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
    public Response<String> index(
    		@ApiParam(name="type",value="首页业绩Type(note,customerManagement,mobileApproval,receiptVisit),默认（note）")
    		@RequestParam(value="type",required = false,defaultValue="note") String type) {
        try {
            Response<String> response = new Response<>(Response.SUCCESS,"查询成功");
            InputStream is=this.getClass().getResourceAsStream("/jsonpage/index.json");
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String s;
            String result = "";
            while((s=br.readLine())!=null){
                result = result + s;
            }
            JSONObject jsonObject = JSONObject.fromObject(result);
            String obj = jsonObject.get(type).toString();
            response.setData(obj);
            return response;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new Response<>(Response.ERROR,"查看首页信息失败");
        }
    }
}