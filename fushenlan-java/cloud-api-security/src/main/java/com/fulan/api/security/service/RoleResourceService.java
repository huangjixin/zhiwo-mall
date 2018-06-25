package com.fulan.api.security.service;import java.util.List;import org.springframework.cloud.netflix.feign.FeignClient;import org.springframework.web.bind.annotation.PostMapping;import org.springframework.web.bind.annotation.RequestBody;import org.springframework.web.bind.annotation.RequestParam;import com.fulan.api.security.domain.Resource;import com.fulan.api.security.domain.RoleResource;import com.fulan.application.util.domain.Response;import com.fulan.application.util.page.PageInfo;@FeignClient(name = "security")public interface RoleResourceService {	/**	 * 根据资源id查找	 * @param resourceId	 * @return	 */	@PostMapping("/roleResource/findByResourceId")	List<RoleResource> findByResourceId(@RequestParam("resourceId") Long resourceId);	/**	 * 根据id查找	 * @param id	 * @return	 */	@PostMapping("/roleResourceService/findById")	RoleResource findById(@RequestParam("id") Long id);	/**	 * 根据角色id查找	 * @param roleId	 * @return	 */	@PostMapping("/roleResourceService/findByRoleId")	List<RoleResource> findByRoleId(@RequestParam("roleId") Long roleId);	/**	 * 新增	 * @param roleResource	 * @return	 */	@PostMapping("/roleResource/add")	Response<String> save(@RequestBody RoleResource roleResource);	/**	 * 根据id删除	 * @param id	 * @return	 */	@PostMapping("/roleResource/delete")	int deleteById(@RequestParam("id") Long id);	/**	 * 分页查询角色资源	 * @param pageNo	 * @param pageSize	 * @param roleResource	 * @return	 */	@PostMapping("/roleResource/listByPage")	PageInfo<RoleResource> listByPage(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize,@RequestBody RoleResource roleResource);		/**	 * 根据资源id查找	 * @param resourceId	 * @return 	 */	@PostMapping("/manage/roleResourceService/findByResourceId")	Response<RoleResource> findByResourceIdFM(@RequestParam("resourceId")Long resourceId);	/**	 * 根据id查找	 * @param id	 * @return	 */	@PostMapping("/manage/roleResourceService/findById")	Response<RoleResource> findByIdFM(@RequestParam("id") Long id);	/**	 * 根据角色id查找	 * @param roleId	 * @return	 */	@PostMapping("/manage/roleResourceService/findByRoleId")	Response<List<RoleResource>> findByRoleIdFM(@RequestParam("roleId") Long roleId);	/**	 * 批量新增	 * @param roleResource	 * @return	 */	@PostMapping("/manage/roleResourceService/addBatch")	Response<String> saveBatch(@RequestParam("roleId")String roleId,@RequestParam("resourceIds")String resourceIds);		/**	 * 新增	 * @param roleResource	 * @return	 */	@PostMapping("/manage/roleResourceService/add")	Response<String> saveFM(@RequestBody RoleResource roleResource);	/**	 * 根据id删除	 * @param id	 * @return	 */	@PostMapping("/manage/roleResourceService/deleteBatch")	Response<String> deleteByIdBatch(@RequestParam("ids") String ids);		/**	 * 根据id批量删除	 * @param id	 * @return	 */	@PostMapping("/manage/roleResourceService/delete")	Response<String> deleteByIdFM(@RequestParam("id") Long id);	/**	 * 分页查询角色资源	 * @param pageNo	 * @param pageSize	 * @param roleResource	 * @return	 */	/*@PostMapping("/manage/roleResourceService/listByPage")	PageInfo<RoleResource> listByPage(Integer pageNo,Integer pageSize,RoleResource roleResource);*/		@PostMapping("/manage/roleResourceService/seleByRoleId")	Response<List<Resource>> seleAllForManage(@RequestBody RoleResource roleResource,			@RequestParam(name = "pageSortFiled") String pageSortFiled,            @RequestParam(name = "pageSortType") String pageSortType);}