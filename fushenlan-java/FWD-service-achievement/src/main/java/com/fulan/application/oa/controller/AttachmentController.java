package com.fulan.application.oa.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fulan.application.oa.domain.FwdOaFormAttachment;
import com.fulan.application.oa.service.IAttachmentService;

/**
 * 
 * @author 曾文明
 *
 */
@RestController
@RequestMapping("/attachment")
public class AttachmentController {

	@Autowired
	private IAttachmentService attachmentService;

	// 暂时写在D盘，后续应该配置成为注入值。
	@Value("${uploadPath}")
	private String uploadPath;

	
	/**
	 * 根据ID获取实体类。
	 * @param id
	 * @param agentCode
	 * @param request
	 * @param response
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/getImage/{id}")
	public void getImage(@PathVariable(value = "id") String id,
			@RequestParam(name = "agentCode", required = true) String agentCode, HttpServletRequest request,
			HttpServletResponse response) {
		// 真正业务应该是查询id对应的记录，路径是this.uploadPath+File.separator+记录的物理路径
		String fileName = this.uploadPath + File.separator + "upload" + File.separator + "images" + File.separator
				+ agentCode;
		fileName += File.separator + "1.jpg";
		File file = new File(fileName);

		// 判断文件是否存在如果不存在就返回默认图标
		if (!(file.exists() && file.canRead())) {
			// 把file对象new一个，指向默认的图片
			return;
		}

		FileInputStream inputStream = null;
		OutputStream stream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			int length = inputStream.read(data);
			//此处的后缀名要判断一下。
			response.setContentType("image/png");
			stream = response.getOutputStream();
			stream.write(data);
			stream.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				if (stream != null)
					stream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 保存数据
	 * 
	 * @param save
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	private int save(FwdOaFormAttachment attachment) {
		attachment.setCreateDatetime(new Date());
		int result = attachmentService.save(attachment);
		return result;
	}

	/**
	 * 删除数据
	 * 
	 * @param delete
	 * @return
	 */
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	private int delete(int id) {
		int result = attachmentService.delete(id);
		return result;
	}

	/**
	 * 修改数据
	 * 
	 * @param update
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	private int update(FwdOaFormAttachment oaFormAttachment) {
		int result = attachmentService.update(oaFormAttachment);
		return result;
	}

	/**
	 * 查询数据
	 * 
	 * @param selectAll
	 * @return
	 */
	@RequestMapping(value = "/selectAll", method = RequestMethod.GET)
	private List<FwdOaFormAttachment> selectAll() {
		List<FwdOaFormAttachment> fwdOaFormAttachmentLsit = attachmentService.selectAll();
		return fwdOaFormAttachmentLsit;
	}

	/**
	 * 根据ID查询数据
	 * 
	 * @param selectById
	 * @return
	 */
	@RequestMapping(value = "/selectById", method = RequestMethod.GET)
	private FwdOaFormAttachment selectById(@PathVariable(value = "id") Integer id) {
		FwdOaFormAttachment oaFormAttachment = attachmentService.selectById(id);
		return oaFormAttachment;
	}
}
