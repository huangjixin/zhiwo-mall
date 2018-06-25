package com.fulan.application.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.material.domain.Material;
import com.fulan.api.material.vo.MaterialDTO;
import com.fulan.api.material.vo.MaterialVO;
import com.fulan.application.util.domain.Response;

/**
 * <p>
 * 资料表，讲师，管理员共用 服务类
 * </p>
 *
 * @author fulan123
 * @since 2018-01-18
 */
public interface IMaterialService extends IService<Material> {
    Response<String> save(Material material);

    Response<String> updateElMaterialById(Material material);

    MaterialDTO findById(Long id);

    Response<String> deleteBatch(MaterialVO list);

    Page<MaterialDTO> findMaterialByPage(MaterialVO materialVO, int pageNo, int pageSize);
}
