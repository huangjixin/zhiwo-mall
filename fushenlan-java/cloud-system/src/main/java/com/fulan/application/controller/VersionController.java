package com.fulan.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.system.domain.Version;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.VersionService;
import com.fulan.application.util.domain.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags = "VersionApi", description = "版本接口")
@RestController
@RequestMapping("/version")
public class VersionController {

	private static final Logger LOG = LoggerFactory.getLogger(DictionaryController.class);
	
	@Autowired
	private VersionService versionService;
	
	@Autowired
	 IdGenerator idG;
	/**
     * 新增版本
     *
     * @param dictionary
     * @return
     */
    @ApiOperation(value = "新增版本", notes = "新增一个版本", response = Response.class)
    @PostMapping("/insert")
    public Response<Boolean> insert(
            @ApiParam(name = "version",value = "不能为空字段：</br>versionCode")
            @RequestBody Version version) {
        try {
        	version.setId(idG.generate());
            return versionService.insert(version);
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<Boolean>(Response.ERROR,"失败");
        }
    }
    
    /**
     * 删除单个版本
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个版本", notes = "删除单个版本", response = Response.class)
    @PostMapping("/delete")
    public Response<String> delete(@RequestParam("id") Long id) {
        try {
        	Response<String> resp=new Response<String>(Response.SUCCESS, "删除成功");
        	Boolean flag=versionService.delete(id);
        	if(flag){
        		return resp;
        	}else{
        		 return new Response<String>(Response.ERROR,"删除失败");
        	}
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<String>(Response.ERROR,"删除失败");
        }
    }
    
    /**
     * 修改版本
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "修改版本", notes = "根据id修改版本", response = Response.class)
    @PostMapping("/update")
    public Response<Boolean> update(@ApiParam("id不可不传") @RequestBody Version version) {
        try {
            return versionService.updateById(version);
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<Boolean>(Response.ERROR,"修改失败");
        }
    }
    
    /**
     * 查询版本
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询版本", notes = "根据id查询版本", response = Response.class)
    @ApiImplicitParams({
   	  @ApiImplicitParam(name="id",value="版本id",dataType="long", paramType = "query",example="1")})
    @GetMapping("/findById")
    public Response<Version> findById(@RequestParam("id") Long id) {
        try {
            return versionService.findById(id);
        } catch (Exception e) {
            LOG.error("", e);
            return new Response<Version>(Response.ERROR,"查询失败");
        }
    }
    
    /**
     * 获取数据库中最新的版本号,包括安卓和IOS
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "获取数据库中最新的版本号", notes = "获取最新的版本号", response = Response.class)
    @GetMapping("/theNewstVersion")
    @ApiImplicitParams({
     	  @ApiImplicitParam(name="type",value="版本type,1:安卓，2:IOS",dataType="int", paramType = "query",example="1")})
    public Response<String> theNewstVersion(@RequestParam("type") int type) {
    	 try {
    		 return versionService.theNewstVersion(type);
    	 } catch (Exception e) {
             LOG.error("", e);
             return new Response<String>(Response.ERROR,"查询失败");
         }
    }
}
