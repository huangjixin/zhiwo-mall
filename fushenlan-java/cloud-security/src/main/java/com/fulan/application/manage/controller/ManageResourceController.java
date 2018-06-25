package com.fulan.application.manage.controller;


import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.api.security.domain.Resource;
import com.fulan.api.security.vo.ResourceListVO;
import com.fulan.application.service.ResourceService;
import com.fulan.application.util.domain.Response;
import com.fulan.core.monitoring.log.annotation.NoLog;

import io.swagger.annotations.ApiParam;

@NoLog
@RestController
@RequestMapping(value = "/manage/resourceService")
public class ManageResourceController {
	
	@Autowired
	ResourceService resourceService;
	
	private static final Logger logger = LoggerFactory.getLogger(ManageResourceController.class);
	/**
	 * 保存
	 * @param resource
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public Response<String> saveForManage(@RequestBody Resource resource){
		try {
/*			Resource resource = new Resource();
			BeanUtils.copyProperties(resourceVO, resource);
*/			return resourceService.saveForManage(resource);
		}
		catch(Exception e){
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"新增资源失败");
		}
	}
	
	/**
	 * 根据parentId 删除
	 * @param parentId
	 * @return
	 */
	@RequestMapping(value="/deleByParentId",method=RequestMethod.POST)
	public Response<String> deleByParentId(@RequestParam("parentId")Long parentId){
		try{
			return resourceService.deleByParentId(parentId);
		}catch(Exception e){
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"删除资源失败");
		}
	}
	
	/**
	 * 根据主键Id 删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/dele",method=RequestMethod.POST)
	public Response<String> deleteForManage(@RequestParam("id")Long id){
		try{
			int count = resourceService.deleteById(id);
			if(count>0){
				return new Response<String>(Response.SUCCESS,"删除资源成功");
			}else{
				return new Response<String>(Response.ERROR,"删除资源失败");
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"删除资源失败");
		}
	}
	
	/**
	 * 根据主键批量删除 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/deleByIds",method=RequestMethod.POST)
	public Response<String> deleByIds(@RequestParam("ids")String ids){
		try{
			return  resourceService.deleByIds(ids);
		}catch(Exception e){
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"删除资源失败");
		}
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public Response<String> updateForManage(@RequestBody Resource resource,@RequestParam(value = "type",defaultValue ="0") String type){
		try{
			return resourceService.updateForManage(resource,type);
		}catch(Exception e){
			logger.error(e.getMessage());
			return new Response<String>(Response.ERROR,"更新资源失败");
		}
	}
	
	/**
	 * 根据主键ID查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/sele",method=RequestMethod.POST)
	public Response<Resource> seleForManage(@RequestParam("id")Long id){
		try{
			Resource resource = resourceService.findById(id);
			if(null !=resource){
				Response<Resource> response = new Response<Resource>();
				response.setData(resource);
				return response;
			}else{
				return new Response<Resource>(Response.ERROR,"查询资源失败");
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			return new Response<Resource>(Response.ERROR,"查询资源失败");
		}
	}
	/*@ApiParam(name = "pageSortFiled", value = "排序字段名，默认：sort", required = false) @RequestParam(name = "pageSortFiled", defaultValue = "sort") String pageSortFiled,
    @ApiParam(name = "pageSortType", value = "顺序：asc,倒叙：desc，默认：asc", required = false) @RequestParam(name = "pageSortType", defaultValue = "asc") String pageSortType*/
	@RequestMapping(value="/seleAll",method=RequestMethod.POST)
	public Response<List<Resource>> seleAllForManage(@RequestBody Resource resource){
		try{
		Response<List<Resource>> response = new Response<List<Resource>>();
		response.setData(resourceService.listForManage(resource));
		return response;
		}catch(Exception e){
			logger.error(e.getMessage());
			return new Response<List<Resource>>(Response.ERROR,"查询资源失败");
		}
	}
	@RequestMapping(value="/seleByParentId",method=RequestMethod.POST)
	public Response<List<Resource>> seleByParentIdForManage(@RequestBody Resource resource){
		try{
			/*Resource resource = new Resource();
			BeanUtils.copyProperties(resourceVO, resource);*/
			Response<List<Resource>> response = new Response<List<Resource>>();
			response.setData(resourceService.listByparentId(resource));
			return response;
		}catch(Exception e){
			logger.error(e.getMessage());
			return new Response<List<Resource>>(Response.ERROR,"查询资源失败");
		}
	}
	
	@RequestMapping(value="/listByParentIdForManage",method=RequestMethod.GET)
	public List<ResourceListVO> listVoForManage(@RequestParam("id") String id,@RequestParam(value="accountId",required=false) Long accountId){
		List<ResourceListVO> resourceListVO = resourceService.listVoForManage(id,accountId);
		return resourceListVO;
	}
	@RequestMapping(value="/listPIdOrIdFM",method=RequestMethod.POST)
	public Response<List<Resource>> listPIdOrIdFM(@RequestParam("id") String id){
		Response<List<Resource>> response = new Response<>(Response.SUCCESS,"访问数据成功");
		List<Resource> list = resourceService.listPIdOrIdFM(id);
		response.setData(list);
		return response;
	}
}
