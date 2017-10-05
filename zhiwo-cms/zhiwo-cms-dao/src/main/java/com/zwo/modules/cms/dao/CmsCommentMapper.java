package com.zwo.modules.cms.dao;

import com.zwo.modules.cms.domain.CmsComment;
import com.zwo.modules.cms.domain.CmsCommentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CmsCommentMapper extends Mapper<CmsComment> {
    int countByExample(CmsCommentCriteria example);

    int deleteByExample(CmsCommentCriteria example);

    List<CmsComment> selectByExample(CmsCommentCriteria example);

    int updateByExampleSelective(@Param("record") CmsComment record, @Param("example") CmsCommentCriteria example);

    int updateByExample(@Param("record") CmsComment record, @Param("example") CmsCommentCriteria example);
}