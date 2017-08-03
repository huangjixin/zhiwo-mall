/**
 * 
 */
package com.zwo.modules.cms.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zwo.modules.cms.domain.CmsDocument;
import com.zwo.modules.cms.domain.CmsDocumentCriteria;
import com.zwo.modules.cms.domain.CmsDocumentWithBLOBs;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface ICmsDocumentService extends IBaseService<CmsDocument> {
	
	int insert(CmsDocumentWithBLOBs record);

    int insertSelective(CmsDocumentWithBLOBs record);

    List<CmsDocumentWithBLOBs> selectByExampleWithBLOBs(CmsDocumentCriteria example);

    List<CmsDocument> selectByExample(CmsDocumentCriteria example);

    CmsDocumentWithBLOBs selectByPrimKey(String id);

    int updateByExampleSelective(CmsDocumentWithBLOBs record, CmsDocumentCriteria example);

    int updateByExampleWithBLOBs(CmsDocumentWithBLOBs record,CmsDocumentCriteria example);

    int updateByExample(CmsDocument record, CmsDocumentCriteria example);

    int updateByPrimaryKeySelective(CmsDocumentWithBLOBs record);
}
