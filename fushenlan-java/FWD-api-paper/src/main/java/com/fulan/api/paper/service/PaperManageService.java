package com.fulan.api.paper.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fulan.api.paper.domain.er.Paper;
import com.fulan.api.paper.vo.ExamAccountVo;
import com.fulan.api.paper.vo.PaperDetailVo;
import com.fulan.api.paper.vo.PaperManageVo;
import com.fulan.api.paper.vo.PaperVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;

import io.swagger.annotations.ApiOperation;
@FeignClient(name = "paper")
public interface PaperManageService {
	/**
	 * 增员试卷管理列表 接口
	 * @author kang
	 * @param keyWord
	 * @param paperType
	 * @param orgId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/paper/paperManageSerch")
	PageInfo<PaperManageVo> paperManageSerch(
			@RequestParam(name="keyWord" ,required = false)String keyWord,
			@RequestParam(name="paperType",required = false) Integer paperType,
			@RequestParam(name="orgId",required = false) String orgId,
			@RequestParam(name = "pageNo", defaultValue = "1") int pageNo,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize
			);
	/**
	 * 
	 * @param keyWord
	 * @param paperType
	 * @param orgId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/manage/paper/paperManagePage")
	PageInfo<PaperManageVo> paperManageSerchOther(
			 @RequestParam(name = "keyWord" , required = false) String keyWord,
			 @RequestParam(name = "orgId" , required = false) String orgId,
			 @RequestParam(name = "pageNo",defaultValue="1")int pageNo,
			 @RequestParam(name = "pageSize",defaultValue="10")int pageSize
			);
	
	/**
	 * 增员试卷详情 接口
	 * @author kang
	 * @param paperId
	 * @return
	 */
	@PostMapping("/manage/paper/paperCheck")
	PaperVo paperCheck(@RequestBody Paper paper); 
	/**
	 * 新增/修改增员试卷 接口
	 * @author kang
	 * @param paperVo
	 * @return
	 */
	@PostMapping("/manage/paper/paperHandle")
	Response<String> paperHandle(@RequestBody PaperVo paperVo);
	/**
	 * 新增/修改增员试卷 接口ER
	 * @param paperVo
	 * @return
	 */
	@PostMapping("/manage/paper/addPaperVo")
	String savePaper(@RequestBody PaperVo paperVo);
	/**
	 * 根据id删除vo
	 * @param id
	 * @return
	 */
	@PostMapping("/manage/paper/deletePaperVo")
	boolean deletePaperVo(@RequestParam(name="id" ,required = false)String id);
		
	
	
	@PostMapping("/manage/paper/getPaperDetailVo")
	public Response<List<PaperDetailVo>> getPaperVo(@RequestBody Map<String,Object> map);
	
	
	@GetMapping("/manage/paper/getExamAccountVo")
	public List<ExamAccountVo> getExamAccountVo(
			@RequestParam(name="userName" ,required = false)String userName,
			@RequestParam(name="questionType",required = false) String questionType,
			@RequestParam(name="id",required = false) String id);
	
	
	@GetMapping("/manage/paper/getExamAccountOtherVo")
	public List<ExamAccountVo> getExamAccountOtherVo(
			@RequestParam(name="userName" ,required = false)String userName
			);
	
	
}
