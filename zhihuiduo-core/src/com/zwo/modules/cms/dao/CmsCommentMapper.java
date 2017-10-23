package com.zwo.modules.cms.dao;

import com.zwo.modules.cms.domain.CmsComment;
import com.zwo.modules.cms.domain.CmsCommentCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CmsCommentMapper extends Mapper<CmsComment> {
}