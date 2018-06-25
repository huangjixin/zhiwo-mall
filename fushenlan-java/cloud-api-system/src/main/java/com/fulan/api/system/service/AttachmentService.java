package com.fulan.api.system.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.fulan.api.system.domain.Attachment;
import com.fulan.application.util.domain.Response;

@FeignClient(name = "system")
public interface AttachmentService {
	
	 @PostMapping("/attachment/uploadFiles")
	 List<Attachment> upload(HttpServletRequest request);
	 
	 @PostMapping("/attachment/insert")
	 Boolean insert(@RequestBody Attachment attachment);
	 
	 @PostMapping(value = "/attachment/update")
	 Boolean updateById(@RequestBody Attachment attachment);
	 
	 @PostMapping(value = "/attachment/updatebyattachmentId")
	 Boolean updatebyattachmentId(@RequestBody Attachment attachment);

	 @PostMapping(value = "/attachment/delete")
	 Boolean updateIsDeleteById(@RequestParam(name="id") Long id);

	 @GetMapping(value = "/attachment/find")
	 Attachment selectById(@RequestParam(name="id") Long id);

	 @GetMapping(value = "/attachment/selectByHostId")
	 List<Attachment> selectByHostId(@RequestParam(name="id") String id);

	 @GetMapping(value = "/attachment/file/find")
	 Response<List<String>>  findFileByTableAndHostId(@RequestParam(name="category") Integer category,@RequestParam(name="hostId") Long hostId);
	 
	 @GetMapping(value = "/attachment/file/img")
	 String  findImgByTableAndHostId(@RequestParam(name="category") Integer category,@RequestParam(name="hostId") Long hostId);
	 
	 
	 @GetMapping(value = "/attachment/filesingle/find")
	 String findFileBytypeAndHostId(@RequestParam(name="category") Integer category,@RequestParam(name="hostId") Long hostId);
	
	 @GetMapping(value = "/attachment/file/findbyparms")
	 List<Attachment> findbyparms(@RequestParam(name="category") Integer category,@RequestParam(name="hostId") Long hostId);
	 
	 //pad前端使用
	 @GetMapping(value = "/attachment/file/findbyparmsForPad")
	 Response<List<Attachment>> findbyparmsForPad(@RequestParam(name="category") Integer category,@RequestParam(name="hostId") Long hostId);
	 
	 
	 /*
	 @GetMapping(value = "/attachment/download")
	 void  download(HttpServletRequest request, HttpServletResponse response,String path);*/
}
