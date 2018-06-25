/**
 * 
 */
package com.fulan.application.oa.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fulan.application.oa.domain.FwdOaFormAttachment;
import com.fulan.application.oa.mapper.FwdOaFormAttachmentMapper;
import com.fulan.application.oa.service.IAttachmentService;

/**
 * @author 黄记新 TonyO
 *
 */
@Service
@Transactional
public class AttachmentServiceImpl implements IAttachmentService {
	
	private static String BASE_MESSAGE = "附件业务类AttachmentServiceImpl";
	private static Logger logger = Logger.getLogger(AttachmentServiceImpl.class);

	@Autowired
	private FwdOaFormAttachmentMapper attachmentMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IFormAttachmentService#save(com.fulan.
	 * application.oa.domain.FwdOaFormAttachment)
	 */
	@Override
	public int save(FwdOaFormAttachment mobile) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "保存附件开始，传入的参数是：" + mobile.toString());
		}
		int result = attachmentMapper.insertSelective(mobile);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "保存附件结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IFormAttachmentService#delete(int)
	 */
	@Override
	public int delete(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE + "删除附件开始，传入的参数id是：" + id);
		}
		int result = attachmentMapper.deleteByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			String msg = result == 1 ? "成功" : "失败";
			logger.info(BASE_MESSAGE + "删除附件结束，结果是：" + msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fulan.application.oa.service.IFormAttachmentService#update(com.fulan.
	 * application.oa.domain.FwdOaFormAttachment)
	 */
	@Override
	public int update(FwdOaFormAttachment mobile) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"更新附件开始，传入的参数是："+mobile.toString());
		}
		int result = attachmentMapper.updateByPrimaryKey(mobile);
		if (logger.isInfoEnabled()) {
			String msg = result==1?"成功":"失败";
			logger.info(BASE_MESSAGE+"更新附件结束，结果是："+msg);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.fulan.application.oa.service.IFormAttachmentService#selectAll()
	 */
	@Override
	public List<FwdOaFormAttachment> selectAll() {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"查询所有附件开始");
		}
		List<FwdOaFormAttachment> oaFormAttachmentList= attachmentMapper.selectByExample(null);
		if (logger.isInfoEnabled()) {
			String msg = oaFormAttachmentList.size()+"";
			logger.info(BASE_MESSAGE+"保存附件，结果的条目数是："+msg);
		}
		return oaFormAttachmentList;
	}
	
	@Override
	public FwdOaFormAttachment selectById(int id) {
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"查询根据id查询附件开始");
		}
		FwdOaFormAttachment oaFormAttachment = attachmentMapper.selectByPrimaryKey(id);
		if (logger.isInfoEnabled()) {
			logger.info(BASE_MESSAGE+"根据id查询附件数据，结果是："+JSONObject.toJSONString(oaFormAttachment));
		}
		return oaFormAttachment;
	}
}
