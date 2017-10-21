/**
 * 
 */
package com.zwo.modules.system.service;

import java.util.List;

import com.zwo.modules.system.domain.TbUserAssets;
import com.zwotech.modules.core.service.IBaseService;

/**
 * @author hjx
 *
 */
public interface ITbUserAssetsService extends IBaseService<TbUserAssets> {
	/**
     * 批量插入
     * @param list
     * @return
     */
    public int batchInsert(List<TbUserAssets> list);
}
