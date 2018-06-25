package com.fulan.application.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.plugins.Page;
import com.fulan.api.personnel.domain.Personnel;
import com.fulan.api.personnel.vo.PersonnelManageInfoVo;
import com.fulan.api.personnel.vo.PersonnelManageVo;
import com.fulan.api.personnel.vo.SearchPersonnelVo;
import com.fulan.application.service.PersonnelService;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

@RestController         
@RequestMapping(value ="/manage/personnel")
public class PersonnelController {
	
	@Autowired
	PersonnelService personnelService;
	
	
	
	
	@RequestMapping(value="/updatePersonnel",method=RequestMethod.POST)
	public   Response<Integer> updatePersonnel(@RequestBody Personnel personnel){
		
		return personnelService.updateBypersonnel(personnel);
	}
	
	

	@RequestMapping(value="/listByPage",method=RequestMethod.POST)
	public @ResponseBody PageInfo<PersonnelManageVo> listByPage(@RequestBody SearchPersonnelVo searchPersonnelVo){
		Page<PersonnelManageVo> page = new Page<>(searchPersonnelVo.getPageNo(), searchPersonnelVo.getPageSize());
		return	personnelService.listByPage(page,searchPersonnelVo);
	}
	
	@RequestMapping(value="/listByOtherPage",method=RequestMethod.GET)
	public @ResponseBody PageInfo<PersonnelManageVo> listByOtherPage(@RequestParam("keyWord") String keyWord,
																@RequestParam("pageNo") int pageNo,
																@RequestParam("pageSize") int pageSize){
		Page<PersonnelManageVo> page = new Page<>(pageNo, pageSize);
		return	personnelService.listByOtherPage(page,keyWord, pageNo, pageSize);
	}
	
	
	@RequestMapping(value="/listByOtherTwoPage",method=RequestMethod.GET)
	public @ResponseBody PageInfo<PersonnelManageVo> listByOtherTwoPage(@RequestParam("keyWord") String keyWord,
																@RequestParam("pageNo") int pageNo,
																@RequestParam("pageSize") int pageSize){
		Page<PersonnelManageVo> page = new Page<>(pageNo, pageSize);
		return	personnelService.listByOtherTwoPage(page, keyWord, pageNo, pageSize);
	}
	
	@ResponseBody
	@RequestMapping(value="/checkInfo" ,method=RequestMethod.GET)
	public  PersonnelManageInfoVo checkInfo(@RequestParam String id){
		if(null != id){
			return  personnelService.PersonnelCheck(id); 
		}else{
			return null;
		}
		
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/selectteamscalecountbyaccountId" ,method=RequestMethod.GET)
	public  int selectteamscalecountbyaccountId(@RequestParam List<Long> accountIds){
		return  personnelService.selectteamscalecountbyaccountId(accountIds); 
	}
	
	
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/selectteamSizebyaccountId" ,method=RequestMethod.GET)
	public  int selectteamSizebyaccountId(@RequestParam Long id){
		
		return  personnelService.selectteamSizebyaccountId(id); 
	}
	
	
	@RequestMapping(value="/updateCheckResult",method=RequestMethod.POST)
	public @ResponseBody String updateCheckResult(@RequestBody Personnel personnel){
		return personnelService.checkResult(personnel);
	}
	
	
}
