package com.zwo.modules.fileupload.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.zwo.modules.system.domain.TbUserAssets;
import com.zwo.modules.system.service.ITbUserAssetsService;

@Controller
@RequestMapping(value = "fileupload")
@Lazy(true)
public class FileUploadController {

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
	public Map<String, Object> upload(@RequestParam(value = "file", required = false) CommonsMultipartFile[] files,
			String HTTP_CONTENT_DISPOSITION, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model uiModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TbUserAssets> userAssets = new ArrayList<TbUserAssets>();
		Calendar date = Calendar.getInstance();
		String rootDir = httpServletRequest.getSession().getServletContext().getRealPath("/");
		String url = "userAssets/" +  date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1)
				+ "/" + date.get(Calendar.DAY_OF_MONTH);
		String uploadPath = rootDir + "userAssets";
		uploadPath = uploadPath + File.separator + date.get(Calendar.YEAR) + File.separator
				+ (date.get(Calendar.MONTH) + 1) + File.separator + date.get(Calendar.DAY_OF_MONTH);
		
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				String name = files[i].getOriginalFilename();
				int index = name.indexOf(".");

				name = new Date().getTime() + ((int) Math.random() * 10000) + "" + name.substring(index, name.length());
				File file = new File(uploadPath, name);

				if (!file.exists()) {// 目录不存在则直接创建
					file.mkdirs();
				}
				CommonsMultipartFile commonsMultipartFile = files[i];
				try {
					commonsMultipartFile.transferTo(file);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

				TbUserAssets assets = new TbUserAssets();
				assets.setName(name);
				assets.setPath(uploadPath + File.separator + name);
				assets.setUrl(url+"/"+name);
				assets.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
				userAssetsService.insertSelective(assets);
//				userAssets.add(assets);
			}
		}

//		userAssetsService.batchInsert(userAssets);
		return map;
	}
}
