package com.fulan.application.util.freemarker;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtils {

	private static Logger logger = LoggerFactory.getLogger(FreemarkerUtils.class);

	/**
	 * 创建ZIP文件
	 * 
	 * @param sourcePath
	 *            文件或文件夹路径
	 * @param zipPath
	 *            生成的zip文件存在路径（包括文件名）
	 */
	public static void createZip(String sourcePath, String zipPath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			writeZip(new File(sourcePath), "", zos);
		} catch (FileNotFoundException e) {
			logger.error("创建ZIP文件失败", e);
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				logger.error("创建ZIP文件失败", e);
			}

		}
	}

	private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
		if (file.exists()) {
			logger.info("IMG_NAME：" + file.getName());
			if (file.isDirectory()) {// 处理文件夹
				parentPath += file.getName() + File.separator;
				File[] files = file.listFiles();
				for (File f : files) {
					writeZip(f, parentPath, zos);
				}
			} else {
				FileInputStream fis = null;
				DataInputStream dis = null;
				try {
					fis = new FileInputStream(file);
					dis = new DataInputStream(new BufferedInputStream(fis));
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}

				} catch (Exception e) {
					logger.error("创建ZIP文件失败", e);
				} finally {
					try {
						if (dis != null) {
							dis.close();
						}
					} catch (IOException e) {
						logger.error("创建ZIP文件失败", e);
					}
				}
			}
		}
	}

	/**
	 * 将字符串写到XML文件中
	 * 
	 * @param xml
	 * @param Filepath
	 */
	public void writeToFile(String xml, String Filepath) {
		logger.error("xml FilePath::" + xml + "<>" + Filepath);
		OutputStream outputStream = null;
		OutputStreamWriter oStreamWriter = null;
		BufferedWriter utput = null;
		try {
			outputStream = new FileOutputStream(new File(Filepath));
			oStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			utput = new BufferedWriter(oStreamWriter);
			utput.write(xml);
		} catch (Exception e) {
			logger.error("writeToFile", e);
		} finally {
			try {
				if (utput != null) {
					utput.close();
				}
				if (oStreamWriter != null) {
					oStreamWriter.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				logger.error("writeToFile IO close exception ", e);
			}
		}
	}

	/**
	 * 指尖直供影像目录XML
	 * 
	 * @param content
	 * @param templateFileName      报文模板文件名
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public String getUserRequestXmlByContentAndTemplateFile(Map<String, Object> content, String templateFileName) {
		String templatePath = "/templates/"; // 报文模板路径
		String requestXml = "";// 请求报文
		try {
			Configuration config = new Configuration();
			// 方式1 绝对路径
			// config.setDirectoryForTemplateLoading(new
			// File("D:/apache-worksapce/MyEclipseNewWork2014/JavaTool/resources/templete"));
			// Template template = config.getTemplate(templateFileName ,"UTF-8");// 报文模板
			// 方式2-最常用 利用classloader
			config.setClassForTemplateLoading(this.getClass(), "/");
			Template template = config.getTemplate(templatePath + templateFileName, "UTF-8");// 报文模板
			// 方式3 需要 servletContext
			// config.setServletContextForTemplateLoading("", "/ftl"); //就是/WebRoot/ftl目录。
			// Template template = config.getTemplate(templateFileName ,"UTF-8");// 报文模板

			// 设置模板参数
			requestXml = FreeMarkerTemplateUtils.processTemplateIntoString(template, content);

			if (requestXml == null || "".equals(requestXml)) {
				return null;
			}
		} catch (Exception e) {
			logger.error("getUserRequestXmlByContentAndTemplateFile", e);
		}

		logger.info("freemarker-requestXml::" + requestXml);

		return requestXml;
	}

}
