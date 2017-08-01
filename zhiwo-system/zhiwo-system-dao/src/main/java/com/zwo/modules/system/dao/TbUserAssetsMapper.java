package com.zwo.modules.system.dao;

import com.zwo.modules.system.domain.TbUserAssets;
import com.zwo.modules.system.domain.TbUserAssetsCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TbUserAssetsMapper extends Mapper<TbUserAssets> {
    int countByExample(TbUserAssetsCriteria example);

    int deleteByExample(TbUserAssetsCriteria example);

    List<TbUserAssets> selectByExample(TbUserAssetsCriteria example);

    int updateByExampleSelective(@Param("record") TbUserAssets record, @Param("example") TbUserAssetsCriteria example);

    int updateByExample(@Param("record") TbUserAssets record, @Param("example") TbUserAssetsCriteria example);
}