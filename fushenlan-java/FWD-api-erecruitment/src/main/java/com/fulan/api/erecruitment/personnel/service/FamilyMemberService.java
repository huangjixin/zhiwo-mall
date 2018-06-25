package com.fulan.api.erecruitment.personnel.service;

import com.fulan.api.erecruitment.personnel.domain.FamilyMember;
import com.fulan.application.util.domain.Response;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 家庭成员 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-19
 */
public interface FamilyMemberService extends IService<FamilyMember> {

	/**
	 * 在线增员-家庭信息
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/personnel/addFamilyMember")
	Response<String> save(String id,FamilyMember familyMember);
	
	/**
	 * 人才详情-查找家庭信息
	 * @param account
	 * @return
	 */
	@PostMapping("/customer/personnel/getFamilyMember")
	Response<List<FamilyMember>> getFamilyMember(@RequestParam(name = "personnelId" , required = false) Long personnelId);

}
