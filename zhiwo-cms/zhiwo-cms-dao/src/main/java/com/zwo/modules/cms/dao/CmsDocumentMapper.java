package com.zwo.modules.cms.dao;

import com.zwo.modules.cms.domain.CmsDocument;
import com.zwo.modules.cms.domain.CmsDocumentCriteria;
import com.zwo.modules.cms.domain.CmsDocumentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CmsDocumentMapper extends Mapper<CmsDocument> {
    int countByExample(CmsDocumentCriteria example);

    int deleteByExample(CmsDocumentCriteria example);

    List<CmsDocumentWithBLOBs> selectByExampleWithBLOBs(CmsDocumentCriteria example);

    List<CmsDocument> selectByExample(CmsDocumentCriteria example);

    int updateByExampleSelective(@Param("record") CmsDocumentWithBLOBs record, @Param("example") CmsDocumentCriteria example);

    int updateByExampleWithBLOBs(@Param("record") CmsDocumentWithBLOBs record, @Param("example") CmsDocumentCriteria example);

    int updateByExample(@Param("record") CmsDocument record, @Param("example") CmsDocumentCriteria example);
}