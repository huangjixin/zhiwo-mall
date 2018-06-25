package com.fulan.application.service;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.system.Vo.PersonnelTagVo;
import com.fulan.api.system.Vo.SingleTagVo;
import com.fulan.api.system.Vo.TagVo;
import com.fulan.api.system.domain.Tag;
import com.fulan.application.util.page.PageInfo;

public interface TagService {	

	Boolean insertDetail(Tag tag);
	
	Boolean delete(Long ids);

	Boolean updateById(Tag tag);

	Tag findById(Long id);
	
	PageInfo<SingleTagVo> findByTagName(Page<SingleTagVo> page,String tagName,String parentTagName,Integer catagory);

	List<Tag> findBymoudle();

	List<Tag> findByCatagory(Integer catagory);

	List<PersonnelTagVo> getPersonnelVoListByList(List<PersonnelTagVo> listPersonnelVo);
	
	List<Tag>  findByparentId(Long parentId,Integer catagory);
	
	List<Tag> listByIdList(List<Long>idList);
	
	PageInfo<TagVo> findByParentTagName(Page<TagVo> page,String parentTagName,Integer catagory);

}
