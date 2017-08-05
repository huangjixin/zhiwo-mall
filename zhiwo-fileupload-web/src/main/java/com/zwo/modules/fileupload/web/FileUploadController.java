package com.zwo.modules.fileupload.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zwo.modules.cms.service.ICmsAssetsService;
import com.zwo.modules.mall.service.IPrImageService;
import com.zwo.modules.system.service.ITbUserAssetsService;

@Controller
@RequestMapping(value = "fileupload")
@Lazy(true)
public class FileUploadController{
	
	@Autowired
	@Lazy(true)
	private ITbUserAssetsService userAssetsService;
	
	@Autowired
	@Lazy(true)
	private IPrImageService imageService;
	
	@Autowired
	@Lazy(true)
	private ICmsAssetsService cmsAssetsService;
	
	@RequestMapping(value = "userAssets")
	@ResponseBody
	public Map<String, Object> upload(
			@RequestParam(value = "file", required = false) CommonsMultipartFile[] files, String HTTP_CONTENT_DISPOSITION, 
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model uiModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ("application/octet-stream".equals(httpServletRequest
				.getContentType())) {// HTML 5 上传
		} else {
			Map<String, Object> map0 = new HashMap<String, Object>();
		}
		return map;
	}
}
