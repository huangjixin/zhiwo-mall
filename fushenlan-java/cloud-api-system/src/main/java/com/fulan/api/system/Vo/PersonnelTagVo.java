package com.fulan.api.system.Vo;

import java.io.Serializable;
import java.util.List;

import com.fulan.api.system.domain.Tag;

import lombok.Data;

/**
 * <p>
 * 人才信息主表
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
@Data
public class PersonnelTagVo implements Serializable {

	AgentOrMustIncreasePersonnelPool personnel;
	
	List<Tag> tagList;
	
	

}
