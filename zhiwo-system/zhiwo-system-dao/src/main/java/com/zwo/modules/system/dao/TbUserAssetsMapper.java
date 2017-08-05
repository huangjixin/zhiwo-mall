package com.zwo.modules.system.dao;

import java.util.List;

import com.zwo.modules.system.domain.TbUserAssets;

import tk.mybatis.mapper.common.Mapper;

public interface TbUserAssetsMapper extends Mapper<TbUserAssets> {
    /**
     * 批量插入
     * @param list
     * @return
     */
    public int batchInsert(List<TbUserAssets> list);
}