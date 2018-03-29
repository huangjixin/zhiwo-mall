package com.zwo.xiyangyang.modules.cms.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.zwo.xiyangyang.modules.cms.dao.CmsDocumentMapper;
import com.zwo.xiyangyang.modules.cms.domain.CmsDocument;
import com.zwo.xiyangyang.modules.cms.service.ICmsDocumentService;
import com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl;

import tk.mybatis.mapper.common.Mapper;

@Service
@Lazy(true)
@Transactional(readOnly = false)
public class CmsDocumentServiceImpl extends BaseServiceImpl<CmsDocument> implements ICmsDocumentService {
	private static Logger logger = LoggerFactory.getLogger(CmsDocumentServiceImpl.class);
	
	@Override
	public Logger getLogger() {
		return logger;
	}
	@Autowired
	private CmsDocumentMapper commentMapper;

	@Override
	public Mapper<CmsDocument> getBaseMapper() {
		return commentMapper;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getImplClass() {
		return CmsDocumentServiceImpl.class;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getTypeClass() {
		return CmsDocument.class;
	}
	@Override
	protected String getBaseMessage() {
		return "CMS文章基础操作";
	}
	
	/** 
	 * 插入方法，对内容诸如"<"">"进行转换为html相对应的标签。
	 * (non-Javadoc)
	 * @see com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl#insert(java.lang.Object)
	 */
	@Override
	public int insert(CmsDocument record) {
		String content = record.getContent();
		if (!StringUtils.isEmpty(content)) {
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}

		int result = super.insert(record);
		return result;
	}
	
	/** 
	 * 插入方法，对内容诸如"<"">"进行转换为html相对应的标签。
	 * (non-Javadoc)
	 * @see com.zwo.xiyangyang.modules.core.service.impl.BaseServiceImpl#insert(java.lang.Object)
	 */
	@Override
	public int insertSelective(CmsDocument record) {
		String content = record.getContent();
		if (!StringUtils.isEmpty(content)) {
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}

		int result = super.insertSelective(record);
		return result;
	}
	
	/**
	 * 根据条件有选择的更新。
	 * @param record
	 * @return
	 */
	@Override
	public int updateByPrimaryKeySelective(CmsDocument record) {
		String content = record.getContent();
		if (!StringUtils.isEmpty(content)) {
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}

		int result = super.updateByPrimaryKeySelective(record);
		return result;
	}

	/**
	 * 根据ID进行记录更新。
	 * @param record
	 * @return
	 */
	@Override
	public int updateById(CmsDocument record) {
		String content = record.getContent();
		if (!StringUtils.isEmpty(content)) {
			content = HtmlUtils.htmlEscape(content);
			record.setContent(content);
		}

		int result = super.updateById(record);
		return result;	
	}
	
	@Override
	public CmsDocument selectByPrimaryKey(String id) {
		CmsDocument record = super.selectByPrimaryKey(id);
		
		String content = record.getContent();
		if (!StringUtils.isEmpty(content)) {
			content = HtmlUtils.htmlUnescape(content);
			record.setContent(content);
		}

		
		return record;
	}

}
