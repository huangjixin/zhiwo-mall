package com.fulan.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.system.Vo.OrganizationVo;
import com.fulan.api.system.domain.Organization;

public interface ManageOrganizationMapper  extends BaseMapper<Organization>{

	List<OrganizationVo> selectOrganizationVo(String id);

	List<Organization> selectList();

	List<Organization> selectListByParentId(Long parentId);

	Organization checkCodeByParentId(Organization organization);

	String getfirstId();

	List<Organization> firstListByParentId(@Param(value = "parentId")String parentId);

}
