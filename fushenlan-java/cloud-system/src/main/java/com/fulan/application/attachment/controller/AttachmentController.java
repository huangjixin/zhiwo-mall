package com.fulan.application.attachment.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.fulan.api.system.domain.Attachment;
import com.fulan.api.system.domain.Dictionary;
import com.fulan.application.context.CommenConstant;
import com.fulan.application.controller.DictionaryController;
import com.fulan.application.orm.id.IdGenerator;
import com.fulan.application.service.AttachmentService;
import com.fulan.application.service.DictionaryService;
import com.fulan.application.util.attachment.FileDownload;
import com.fulan.application.util.attachment.FileUtil;
import com.fulan.application.util.domain.Response;
import com.fulan.application.util.file.SFTPUtil;
import com.fulan.application.util.str.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Api(tags = "AttachmentApi", description = "附件接口")
@RestController
@RequestMapping(value="/attachment")
public class AttachmentController {
	 private static final Logger LOG = LoggerFactory.getLogger(AttachmentController.class);
	//public static String dictionary.getValue() = "/home/upload";
	
	 @Autowired
     private AttachmentService attachmentService;
	 
	 @Autowired
	 IdGenerator idG;
	 
	 @Autowired
	 HttpServletRequest request; 
	 
	 @Autowired
	 HttpServletResponse response;
	 
	 @Autowired
	 private DictionaryService dictionaryService;
	 
	 @Value("${ftpuploadusername}")
	 public  String ftpuploadusername;
	 
	 @Value("${ftpuploadpassword}")
	 public  String ftpuploadpassword;
	 
	 @Value("${ftpuploadhost}")
	 public  String ftpuploadhost;
	 
	 @Value("${ftpuploadport}")
	 public  Integer ftpuploadport;
	 
	 
	 /**
	  * 根据hostid查询附件
	  * @param hostId
	  * @return
	  */
	 @GetMapping(value = "selectByHostId")
	 public  @ResponseBody List<Attachment> selectByHostId(@RequestParam String id){
		 
		return attachmentService.selectByhostId(id);
		 
	 }
	 
	 
	/**
	 * 附件上传
	 * 
	 * @param request
	 * @throws IOException
	 */
	@ApiOperation(value = "附件上传", notes = "上传附件", response = Response.class)
	@PostMapping(value = "/uploadFiles")
	@ResponseBody
	public  Response<List<Attachment>> upload(HttpServletRequest request) throws IOException {
	try {
		Dictionary  dictionary= dictionaryService.findByCode(CommenConstant.IMAGEDIR);
		
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		String category=request.getParameter("category");
		String HostId=request.getParameter("hostId");
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		
		 SFTPUtil sftp = new SFTPUtil(ftpuploadusername,ftpuploadpassword,ftpuploadhost, ftpuploadport);  
		  sftp.login(); 
		File file = new File(dictionary.getValue());//查字典表找出文件存放路径
		if (!file.exists()) {
			file.mkdirs();
		}
		List<Attachment> uploadList = new ArrayList<Attachment>(); // 上传文件之后保存到附件表
		Iterator<Map.Entry<String, MultipartFile>> it = fileMap.entrySet().iterator();
		String newName = "";
		String filePath = "";
		List<Attachment> attachmentlist  = null;
		if(!StringUtil.isEmpty(HostId)&&!StringUtil.isEmpty(category)){
			attachmentlist =  attachmentService.findFileByTableAndHostId(Integer.parseInt(category), Long.parseLong(HostId));	
		}
		if(attachmentlist !=null){
			for(Attachment attachment :attachmentlist){
				attachment.setIsDelete(0);
				attachmentService.updateById(attachment);
			}	
		}
			
		while (it.hasNext()) {
			Attachment upload = new Attachment();
			Map.Entry<String, MultipartFile> entry = it.next();
			MultipartFile mFile = entry.getValue();
			if (mFile.getSize() != 0 && !"".equals(mFile.getName())) {
				newName = idG.generate() + "." + FileUtil.getFileExtName(mFile.getOriginalFilename()); // 上传文件存放公共路径
				filePath = "/" + FileUtil.getYearWeekFolder() + "/" ; // 上传文件在公共路径中详细路径
				File fileLocal = new File(dictionary.getValue()  + filePath);
				if (!fileLocal.exists()) {
					fileLocal.mkdirs();
				}
				// 创建附件
				upload.setId(idG.generate());
				upload.setTableName("");//宿主表名
				if(HostId!=null && !"".equals(HostId)){
					upload.setHostId(Long.parseLong(HostId));//宿主id
				}
	
				upload.setName(newName);//附件名称
				upload.setSize(mFile.getSize());//附件大小
				upload.setPath("http://"+ftpuploadhost+":"+CommenConstant.nginxechoport +"/"+CommenConstant.mediaPlay+ filePath+ newName);//附件路径
				upload.setDescription("");
				upload.setFileExt(FileUtil.getFileExtName(mFile.getOriginalFilename()));
				upload.setOriginalName(mFile.getOriginalFilename());
				upload.setSeq(1);
	
				if(category!=null && !"".equals(category)){
					upload.setCategory(Integer.parseInt(category));//宿主id
				}
				
				
				upload.setUrl(filePath + newName);
				upload.setIsDelete(1);
				upload.setGmtCreate(new Date());
				//保存附件信息
				uploadList.add(upload);
				sftp.upload(dictionary.getValue()  + filePath, newName, mFile.getInputStream());  

				//write(mFile.getInputStream(), new FileOutputStream(dictionary.getValue() + filePath + newName));
			}
		}
		 sftp.logout();  
		
		
		attachmentService.save(uploadList);
		
		Response<List<Attachment>> resp = new Response<List<Attachment>>(Response.SUCCESS, "上传成功");
		resp.setCode("1");
		resp.setData(uploadList);
		return resp;
	  }catch (Exception e) {
			LOG.error(e.getMessage()); 
			return new Response<List<Attachment>>(Response.ERROR, "上传失败");
		}
		//return uploadList;
	}
	
	
	

	/**
	 * 写入数据
	 * 
	 * @param in
	 * @param out
	 * @throws IOException
	 */
	public static void write(InputStream in, OutputStream out) throws IOException {
		try {
			byte[] buffer = new byte[1024];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
			}
			out.flush();
		} finally {
			try {
				in.close();
			} catch (IOException ex) {
			}
			try {
				out.close();
			} catch (IOException ex) {
			}
		}
	}
	 /**
     * 新增附件信息
     *
     * @param attachment
     * @return
     */
    @ApiOperation(value = "新增附件信息", notes = "新增一个附件信息", response = Response.class)
    @PostMapping("/insert")
    public Boolean insert(
            @ApiParam(
                    name = "attachment",
                    value = "不能为空字段：</br>path</br>"
            )
            @RequestBody Attachment attachment) {
    	attachment.setId(idG.generate());
    	attachment.setIsDelete(1);
        try {
            return attachmentService.insert(attachment);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }

    }
    /**
     * 更新附件信息
     * 唯一附件
     * @param dictionary
     * @return
     */
    @ApiOperation(value = "更新附件信息", notes = "根据id更新附件信息", response = Response.class)
    @PostMapping(value = "/update")
    public Boolean update(@ApiParam("id不可不传") @RequestBody Attachment attachment) {
        try {
            return attachmentService.updateById(attachment);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
     * updatebyattachmentId
     * 
     * @warn(注意事项 – 可选)
     * @param attachment
     * @return
     */
    @ApiOperation(value = "更新附件信息", notes = "根据id更新附件信息", response = Response.class)
    @PostMapping(value = "/updatebyattachmentId")
    public Boolean updatebyattachmentId(@ApiParam("id不可不传") @RequestBody Attachment attachment) {
        try {
            return attachmentService.updateByattachmentId(attachment);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    
    
    /**
     * 删除单个附件信息------逻辑删除 将is_delete修改成0；
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除单个附件信息", notes = "删除单个附件信息", response = Response.class)
    @PostMapping("/delete")
    public Boolean delete(@RequestParam("id") Long id) {
        try {
        	Attachment attach=findById(id);
        	if(attach!=null){
        		deleteFile(attach.getUrl());
        	}
            return attachmentService.updateIsDeleteById(id);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    /**
     * 根据id查找附件信息
     *
     * @param id 附件Id
     * @return
     */
    @ApiOperation(value = "根据id查找附件信息", notes = "根据id查找单个附件", response = Response.class)
    @ApiImplicitParams({
     	  @ApiImplicitParam(name="id",value="附件id",dataType="long", paramType = "query",example="1")})
    @GetMapping(value = "/find")
    public Attachment findById(@RequestParam("id") Long id) {
        try {
            return attachmentService.selectById(id);
        } catch (Exception e) {
            LOG.error("", e);
            throw e;
        }
    }
    
    /**
	 * 附件下载
	 * 
	 */
    @ApiOperation(value = "附件下载", notes = "根据path下载附件", response = Response.class)
	@GetMapping(value = "/download")
    @ApiImplicitParams({
   	  @ApiImplicitParam(name="path",value="附件路径",dataType="String", paramType = "query",example="/files/2018-03/954161516792250368.jpg")})
	public void download(HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			request.setCharacterEncoding("UTF-8");
			if (request.getMethod().equals("GET")) {
				path = new String(path.getBytes("ISO8859-1"), "UTF-8");
			}
			boolean flag = FileDownload.download(response, path);
			// 传入路径不存在
			if (!flag) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
		if (file.delete()) {
		 System.out.println("删除单个文件" + fileName + "成功！");
		return true;
		} else {
		System.out.println("删除单个文件" + fileName + "失败！");
		return false;
		}
		} else {
		System.out.println("删除单个文件失败：" + fileName + "不存在！");
		 return false;
		}
	 }
    
    
    @ApiOperation(value = "根据category和hostId查找单个附件", notes = "根据category和hostId查找单个附件", response = Response.class)
    @ApiImplicitParams({
    @ApiImplicitParam(name="category",value="附件类型",dataType="int", paramType = "query",example="1"),
    @ApiImplicitParam(name="hostId",value="关联编号",dataType="long", paramType = "query",example="1")})
    @GetMapping(value = "/filesingle/find")
    public String findFileBytypeAndHostId(@RequestParam("category") Integer category,@RequestParam("hostId") Long hostId) {
        try {
        	List<Attachment> attachmentlist=attachmentService.findFileByTableAndHostId(category,hostId);
        	String str = "";
        	if(attachmentlist.size()==0){
        		str= "无此图片";
        	}else{
        		//存在图片时
        		if(attachmentlist.size()==1){
            		str = attachmentlist.get(0).getPath();	
            	}
        	}
			return str;
        } catch (Exception e) {
        	e.printStackTrace();
        	LOG.error(e.getMessage());
	            throw e;
        }
    }
    
    
    /**
     * 根据category附件类型和hostId关联编号显示附件
     *
     * @param id 附件Id
     * @return
     */
    @ApiOperation(value = "根据category和hostId查找附件", notes = "根据category和hostId查找附件", response = Response.class)
    @ApiImplicitParams({
    @ApiImplicitParam(name="category",value="附件类型",dataType="int", paramType = "query",example="1"),
    @ApiImplicitParam(name="hostId",value="关联编号",dataType="long", paramType = "query",example="1")})
    @GetMapping(value = "/file/find")
    public Response<List<String>>  findFileByTableAndHostId(@RequestParam("category") Integer category,@RequestParam("hostId") Long hostId) {
    	try {
        	List<Attachment> attachmentlist=attachmentService.findFileByTableAndHostId(category,hostId);
        	List<String>  list = new   ArrayList<String>();
        	if(attachmentlist.size()==0){
        		list.add("无此图片");
        		Response<List<String>>  resp =new Response<List<String>>(Response.SUCCESS, "无此图片");
    			resp.setCode("1");
    			resp.setData(list);
    			return resp;
        	}else{
        	//存在图片情况
        	if(attachmentlist.size()==1){
        		String str = attachmentlist.get(0).getPath() ;
        		list.add(str);	
        	}else{
        		for( Attachment  attachment:attachmentlist){
        			String str =attachment.getPath() ;
            		list.add(str);	
        		}
        	}
        	Response<List<String>>  resp =new Response<List<String>>(Response.SUCCESS, "回显成功");
			resp.setCode("1");
			resp.setData(list);
			return resp;
        	}
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	LOG.error(e.getMessage());
			return new Response<List<String>>(Response.ERROR, "回显失败");
        }
    }
    
    
    /**
     *根据id，类型查询图片路径
     */
    @GetMapping(value = "/file/img")
    public String  findImgByTableAndHostId(@RequestParam("category") Integer category,@RequestParam("hostId") Long hostId) {
    	try {
        	List<Attachment> attachmentlist=attachmentService.findFileByTableAndHostId(category,hostId);
        	List<String>  list = new   ArrayList<String>();
        	//存在图片情况
        	if(attachmentlist.size()==1){
        		String str = attachmentlist.get(0).getPath() ;
        		list.add(str);	
        	}else{
        		for( Attachment  attachment:attachmentlist){
        			String str =attachment.getPath() ;
            		list.add(str);	
        		}
        	}
        	Response<List<String>>  resp =new Response<List<String>>(Response.SUCCESS, "回显成功");
			resp.setCode("1");
			resp.setData(list);
			return resp.getData().get(0);
        } catch (Exception e) {
        	e.printStackTrace();
        	LOG.error(e.getMessage());
			return "";
        }
    }
    
    
    
    /**
     * 根据category附件类型和hostId关联编号显示附件
     *
     * @param id 附件Id
     * @return
     */
    @ApiOperation(value = "根据category和hostId查找附件", notes = "根据category和hostId查找附件", response = Response.class)
    @ApiImplicitParams({
    @ApiImplicitParam(name="category",value="附件类型",dataType="int", paramType = "query",example="1"),
    @ApiImplicitParam(name="hostId",value="关联编号",dataType="long", paramType = "query",example="1")})
    @GetMapping(value = "/file/findbyparms")
    public List<Attachment>  findbyparms(@RequestParam("category") Integer category,@RequestParam("hostId") Long hostId) {
    	try {
        	List<Attachment> attachmentlist=attachmentService.findFileByTableAndHostId(category,hostId);
			return attachmentlist;	
        } catch (Exception e) {
        	e.printStackTrace();
        	LOG.error(e.getMessage());
           throw e;
        }
    }
    
    /**
     * 根据category附件类型和hostId关联编号显示附件(前端使用)
     *
     * @param id 附件Id
     * @return
     */
    @ApiOperation(value = "根据category和hostId查找附件", notes = "根据category和hostId查找附件", response = Response.class)
    @ApiImplicitParams({
    @ApiImplicitParam(name="category",value="附件类型",dataType="int", paramType = "query",example="1"),
    @ApiImplicitParam(name="hostId",value="关联编号",dataType="long", paramType = "query",example="1")})
    @GetMapping(value = "/file/findbyparmsForPad")
    public Response<List<Attachment>>  findbyparmsForPad(@RequestParam("category") Integer category,@RequestParam("hostId") Long hostId) {
    	try {
    		Response<List<Attachment>> resp = new Response<List<Attachment>>(Response.SUCCESS, "图片查询成功");
        	List<Attachment> attachmentlist=attachmentService.findFileByTableAndHostId(category,hostId);
        	resp.setData(attachmentlist);
			return resp;	
        } catch (Exception e) {
        	e.printStackTrace();
        	LOG.error(e.getMessage());
        	return new Response<List<Attachment>>(Response.ERROR, "回显失败");
        }
    }
    
    
    
    @ApiOperation(value = "根据路径查找附件", notes = "根据路径查找附件", response = Response.class)
    @GetMapping(value = "/file/findbypath")
    public Response<String> findFileByTableAndHostId(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
        	Response<String>  resp =new Response<String>(Response.SUCCESS, "回显成功");
        	String str = ftpuploadhost+":"+CommenConstant.nginxechoport +"/"+CommenConstant.mediaPlay+"/"+path ;
			resp.setCode("1");
			resp.setData(str);
        	return  resp;
        	
        } catch (Exception e) {
        	e.printStackTrace();
        	LOG.error(e.getMessage());
			return new Response<String>(Response.ERROR, "回显失败");
        }
    }
    
    @ApiOperation(value = "更新关联上传文件信息", notes = "更新文件信息", response = Response.class)
	@RequestMapping(value = "/updateFileInfo", produces = { "application/json;charset=utf-8" }, method = RequestMethod.POST)
	public Response<String> updateFileInfo(@RequestBody List<Attachment> attachmentList) {
		try {
			for(Attachment attachment:attachmentList){
				attachmentService.updateById(attachment);
			}
			return new Response<String>(Response.SUCCESS,"文件信息更新成功");
		} catch (Exception e) {
			return new Response<String>(Response.ERROR,"文件信息更新失败");
		}
	}
    
    
}
