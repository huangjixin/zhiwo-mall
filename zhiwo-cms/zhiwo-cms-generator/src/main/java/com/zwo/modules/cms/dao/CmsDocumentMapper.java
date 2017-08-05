package com.zwo.modules.cms.dao;

import com.zwo.modules.cms.domain.CmsDocument;
import com.zwo.modules.cms.domain.CmsDocumentCriteria;
import com.zwo.modules.cms.domain.CmsDocumentWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsDocumentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int countByExample(CmsDocumentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int deleteByExample(CmsDocumentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int insert(CmsDocumentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int insertSelective(CmsDocumentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    List<CmsDocumentWithBLOBs> selectByExampleWithBLOBs(CmsDocumentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    List<CmsDocument> selectByExample(CmsDocumentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    CmsDocumentWithBLOBs selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int updateByExampleSelective(@Param("record") CmsDocumentWithBLOBs record, @Param("example") CmsDocumentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int updateByExampleWithBLOBs(@Param("record") CmsDocumentWithBLOBs record, @Param("example") CmsDocumentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int updateByExample(@Param("record") CmsDocument record, @Param("example") CmsDocumentCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int updateByPrimaryKeySelective(CmsDocumentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int updateByPrimaryKeyWithBLOBs(CmsDocumentWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table cms_document
     *
     * @mbggenerated Sat Aug 05 10:48:52 CST 2017
     */
    int updateByPrimaryKey(CmsDocument record);
}