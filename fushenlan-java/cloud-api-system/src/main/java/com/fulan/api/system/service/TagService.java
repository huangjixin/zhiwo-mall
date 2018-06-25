package com.fulan.api.system.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.system.Vo.PersonnelTagVo;
import com.fulan.api.system.Vo.SingleTagVo;
import com.fulan.api.system.Vo.TagVo;
import com.fulan.api.system.domain.Tag;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@FeignClient(name = "system")
public interface TagService{

	@PostMapping("/manage/tag/insert")
	Boolean insertDetail(Tag tag);

	@PostMapping("/manage/tag/delete")
	String delete(@RequestParam(name = "id" , required = true)Long id);

	@PostMapping("/manage/tag/update")
	Boolean updateById(Tag tag);

	@GetMapping("/manage/tag/findById")
	Tag findById(@RequestParam(name = "id" , required = false) Long id);
	
	@GetMapping("/manage/tag/findByParentTagName")
	PageInfo<TagVo> findByParentTagName(
			@RequestParam(name = "parentTagName" , required = false) String parentTagName,
			@RequestParam(name = "catagory" , required = false) Integer catagory,
			@RequestParam(value="pageNo",required=false)Integer pageNo,
    		@RequestParam(value="pageSize",required=false)Integer pageSize
			);

	@GetMapping("/manage/tag/findByTagName")
	PageInfo<SingleTagVo> findByTagName(
			@RequestParam(name = "tagName" , required = false) String tagName,
			@RequestParam(name = "parentTagName" , required = false) String parentTagName,
			@RequestParam(name = "catagory" , required = false) Integer catagory,
			@RequestParam(value="pageNo",required=false)int pageNo,
    		@RequestParam(value="pageSize",required=false)int pageSize
			);
	
	@PostMapping("/manage/tag/findByCatagory")
	Response<List<TagVo>> findByCatagory(@RequestParam(name = "catagory" , required = false) Integer catagory);
	
	@PostMapping("/manage/tag/getPersonnelVoListByList")
	List<PersonnelTagVo> getPersonnelVoListByList(List<PersonnelTagVo> personnelVoList);
	
	
	@PostMapping("/manage/tag/findByparentId")
	Response<List<Tag>> findByparentId(@RequestParam(name = "parentId" , required = false) Long parentId,@RequestParam(name = "catagory" , required = false) Integer catagory);
	
	/**
	 * 根据用户Id查询关联的用户组下关联的标签
	 * @param accountId
	 * @return
	 */
	@GetMapping("/manage/tag/listByAccountId")
	List<Tag> listByAccountId(@RequestParam(name = "accountId" , required = false) Long accountId);
}
