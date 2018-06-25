package com.fulan.application.util.attachment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
/**
 * 
 * @Description:文件下载
 * @author meicunzhi@fulan.com.cn
 * @date 2017年8月7日
 */
public class FileDownload {
	
	public static boolean download(HttpServletResponse response, String filePath){
		if(StringUtils.isBlank(filePath) || response == null){
			return false;
		}
		
		BufferedInputStream is = null;
		OutputStream os = null;
		try{
			//设置响应头信息
			File file = new File(filePath);
			String mimeType =  new MimetypesFileTypeMap().getContentType(file);
			response.setContentType(mimeType + ";charset=UTF-8");
			long fileLength = file.length();
			response.setHeader("Content-Length", String.valueOf(fileLength));
			//转换中文字符串，防止出现乱码
			String newFileName = new String(file.getName().getBytes("gbk"), "ISO8859-1");
			response.setHeader("Content-disposition", "attachment; filename=" + newFileName);
			
			is = new BufferedInputStream(new FileInputStream(file));
			os = response.getOutputStream();
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = is.read(buff, 0, buff.length))) {
				os.write(buff, 0, bytesRead);
			}			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		} finally{
			try{
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();
				}
			}catch(Exception e1){
			}
		}
		
		return true;
	}
}
