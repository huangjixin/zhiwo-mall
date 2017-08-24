package com.zwo.modules.fileupload.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.zwo.modules.cms.service.ICmsAssetsService;
import com.zwo.modules.mall.domain.PrImage;
import com.zwo.modules.mall.service.IPrImageService;
import com.zwo.modules.system.domain.TbUserAssets;
import com.zwo.modules.system.service.ITbUserAssetsService;

@Scope("prototype")
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
	public String userAssetsUpload(
			@RequestParam(value = "file", required = false) CommonsMultipartFile[] files,
			String HTTP_CONTENT_DISPOSITION, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model uiModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TbUserAssets> userAssets = new ArrayList<TbUserAssets>();
		Calendar date = Calendar.getInstance();
		String rootDir = httpServletRequest.getSession().getServletContext().getRealPath("/");
//		rootDir = "D:"+File.separator;
		
		String url = "images/uassets/" + date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/"
				+ date.get(Calendar.DAY_OF_MONTH);
		String uploadPath = rootDir + "images" + File.separator + "uassets";
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
				assets.setUrl(url + "/" + name);
				assets.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
				userAssetsService.insertSelective(assets);
				userAssets.add(assets);
			}
		}
		map.put("assets", userAssets);
		String result = JSON.toJSONString(map);
		// userAssetsService.batchInsert(userAssets);
		return result;
	}
	

	@RequestMapping(value = "prAssets")
	@ResponseBody
	public String proAssetsUpload(
			@RequestParam String productId,
			@RequestParam(value = "file", required = false) CommonsMultipartFile[] files,
			@RequestParam(value = "imgWidth", required = false)  String imgWidth,
			@RequestParam(value = "imgHeight", required = false)  String imgHeight,
			String HTTP_CONTENT_DISPOSITION, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model uiModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PrImage> proAssets = new ArrayList<PrImage>();
		Calendar date = Calendar.getInstance();
		String rootDir = httpServletRequest.getSession().getServletContext().getRealPath("/");
//		rootDir = "D:"+File.separator;
		String url = "images/passets/" + date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/"
				+ date.get(Calendar.DAY_OF_MONTH);
		String uploadPath = rootDir + "images" + File.separator + "passets";
		uploadPath = uploadPath + File.separator + date.get(Calendar.YEAR) + File.separator
				+ (date.get(Calendar.MONTH) + 1) + File.separator + date.get(Calendar.DAY_OF_MONTH);

		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				String name = files[i].getOriginalFilename();
				int index = name.indexOf(".");
				String datetimeName = new Date().getTime() + ((int) Math.random() * 10000) + "";
				name =  datetimeName + name.substring(index, name.length());
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
				
				PrImage assets = new PrImage();
				assets.setIsDefault(false);
				assets.setProductId(productId);
				assets.setName(name);
				assets.setLocation(uploadPath + File.separator + name);
				assets.setUrl(url + "/" + name);
				assets.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
				imageService.insertSelective(assets);
				proAssets.add(assets);
			}
		}
		map.put("assets", proAssets);
		String result = JSON.toJSONString(map);
		// userAssetsService.batchInsert(userAssets);
		return result;
	}
	
	/**
	 * 商品轮播图上传
	 * @param productId
	 * @param files
	 * @param HTTP_CONTENT_DISPOSITION
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param uiModel
	 * @return
	 */
	@RequestMapping(value = "prSwiperAssets")
	@ResponseBody
	public String proSwiperAssetsUpload(
			@RequestParam String productId,
			@RequestParam(value = "file", required = false) CommonsMultipartFile[] files,
			String HTTP_CONTENT_DISPOSITION, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model uiModel) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<PrImage> proAssets = new ArrayList<PrImage>();
		Calendar date = Calendar.getInstance();
		String rootDir = httpServletRequest.getSession().getServletContext().getRealPath("/");
//		rootDir = "D:"+File.separator;
		String url = "images/passets/" + date.get(Calendar.YEAR) + "/" + (date.get(Calendar.MONTH) + 1) + "/"
				+ date.get(Calendar.DAY_OF_MONTH);
		String uploadPath = rootDir + "images" + File.separator + "passets";
		uploadPath = uploadPath + File.separator + date.get(Calendar.YEAR) + File.separator
				+ (date.get(Calendar.MONTH) + 1) + File.separator + date.get(Calendar.DAY_OF_MONTH);
		
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				String name = files[i].getOriginalFilename();
				int index = name.indexOf(".");
				String datetimeName = new Date().getTime() + ((int) Math.random() * 10000) + "";
				name =  datetimeName + name.substring(index, name.length());
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
				
				PrImage assets = new PrImage();
				assets.setProductId(productId);
				//true为轮播图。
				assets.setIsDefault(true);
				assets.setName(name);
				assets.setLocation(uploadPath + File.separator + name);
				assets.setUrl(url + "/" + name);
				assets.setId(System.currentTimeMillis() + "" + Math.round(Math.random() * 99));
				imageService.insertSelective(assets);
				proAssets.add(assets);
			}
		}
		map.put("assets", proAssets);
		String result = JSON.toJSONString(map);
		// userAssetsService.batchInsert(userAssets);
		return result;
	}

}
