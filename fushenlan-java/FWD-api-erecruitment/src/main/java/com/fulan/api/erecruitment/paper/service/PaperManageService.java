package com.fulan.api.erecruitment.paper.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.erecruitment.paper.vo.PaperManageVo;
import com.fulan.api.erecruitment.paper.vo.PaperVo;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.page.PageInfo;
@FeignClient(name = "erecruitment")
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
	 * 增员试卷详情 接口
	 * @author kang
	 * @param paperId
	 * @return
	 */
	@GetMapping("/manage/paper/paperCheck")
	PaperVo paperCheck(@RequestParam(name="paperId" ,required = false)String paperId); 
	/**
	 * 新增/修改增员试卷 接口
	 * @author kang
	 * @param paperVo
	 * @return
	 */
	@PostMapping("/manage/paper/paperHandle")
	Response<String> paperHandle(@RequestBody PaperVo paperVo);
}
