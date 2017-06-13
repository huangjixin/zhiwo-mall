package com.zwo.modules.cms.dao;

import com.zwo.modules.cms.domain.CmsAssets;
import com.zwo.modules.cms.domain.CmsAssetsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CmsAssetsMapper extends Mapper<CmsAssets> {
    int countByExample(CmsAssetsCriteria example);

    int deleteByExample(CmsAssetsCriteria example);

    List<CmsAssets> selectByExample(CmsAssetsCriteria example);

    int updateByExampleSelective(@Param("record") CmsAssets record, @Param("example") CmsAssetsCriteria example);

    int updateByExample(@Param("record") CmsAssets record, @Param("example") CmsAssetsCriteria example);
}