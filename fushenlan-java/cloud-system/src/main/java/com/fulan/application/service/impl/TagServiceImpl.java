package com.fulan.application.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.system.Vo.PersonnelTagVo;
import com.fulan.api.system.Vo.SingleTagVo;
import com.fulan.api.system.Vo.TagVo;
import com.fulan.api.system.domain.Tag;
import com.fulan.application.mapper.TagMapper;
import com.fulan.application.service.TagService;
import com.fulan.application.util.page.PageInfo;
import com.fulan.application.util.page.PageUtil;

@Service
public class TagServiceImpl implements TagService {
	
	@Autowired
	TagMapper tagMapper;
	
	@Override
	public Boolean insertDetail(Tag tag) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		int ins=tagMapper.insert(tag);
		if(ins>0){
			flag=true;
		}
		return flag;
	}

	@Override
	public Boolean delete(Long ids) {
		// TODO Auto-generated method stub
		 // 待删除id
		Boolean flag=false;
		int ins=tagMapper.deleteById(ids);
		if(ins>0){
			flag=true;
		}
        return flag;
	}

	@Override
	public Boolean updateById(Tag tag) {
		Boolean flag=false;
		int ins=tagMapper.updateById(tag);
		if(ins>0){
			flag=true;
		}
		return flag;
	}

	public List<Tag> findBymoudle(){
		
		List<Tag> tarlist = tagMapper.findBymoudle();
		return tarlist;	
	} 
	
	
	@Override
	public Tag findById(Long id) {
		// TODO Auto-generated method stub
		return tagMapper.selectById(id);
	}

	@Override
	public List<Tag> findByCatagory(Integer catagory) {
		// TODO Auto-generated method stub
		return tagMapper.findByCatagory(catagory);
	}

	@Override
	public List<PersonnelTagVo> getPersonnelVoListByList(List<PersonnelTagVo> listPersonnelVo) {
		// TODO Auto-generated method stub
		for(int i=0;i<listPersonnelVo.size();i++ ){
			PersonnelTagVo personnelVo = listPersonnelVo.get(i);
			List<Tag> tagList = personnelVo.getTagList();
				for(int j=0;j<tagList.size();j++ ){
					Tag tag =  tagList.get(j);
					tag =tagMapper.selectById(tag.getId());
					tagList.set(j, tag);
				}
			personnelVo.setTagList(tagList);
			listPersonnelVo.set(i, personnelVo);
		}
		return listPersonnelVo;
	}

	
	
	public	List<Tag>  findByparentId(Long parentId,Integer catagory){
		return tagMapper.findByparentId(parentId,catagory);
	}

	@Override
	public PageInfo<SingleTagVo> findByTagName(Page<SingleTagVo> page,String tagName,String parentTagName,Integer catagory) {
		PageInfo<SingleTagVo> pageInfo = new PageInfo<SingleTagVo>();
        pageInfo.setRecords(tagMapper.findByTagName(page,tagName,parentTagName,catagory));
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        int total = tagMapper.countByTagName(tagName,parentTagName,catagory);
        pageInfo.setPageTotal(PageUtil.getPages(page.getSize(),total));
        return pageInfo;
	}

	@Override
	public PageInfo<TagVo> findByParentTagName(Page<TagVo> page,String parentTagName, Integer catagory) {
		PageInfo<TagVo> pageInfo = new PageInfo<TagVo>();
        pageInfo.setRecords(tagMapper.findByParentTagName(page,parentTagName,catagory));
        pageInfo.setPageNo(page.getCurrent());
        pageInfo.setPageSize(page.getSize());
        int total = tagMapper.countByParentTagName(parentTagName,catagory);
        pageInfo.setPageTotal(PageUtil.getPages(page.getSize(),total));
		return pageInfo;
	}
	@Override
    public List<Tag> listByIdList(List<Long> idList) {
        return tagMapper.selectBatchIds(idList);
    }
}
