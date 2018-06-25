package com.fulan.application.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.fulan.api.system.domain.Organization;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */
public interface OrganizationService extends IService<Organization> {

    boolean insertDetail(Organization organization);

    /**
     * 批量删除，删除包括所有子节点
     *
     * @param ids
     * @return
     */
    boolean batchDelete(Long[] ids);
    /**
     * 清空
     * @return
     */
    boolean deleteAll();
    /**
     * dms插入组织机构
     * @return
     */
    boolean insertFromDms(Organization organization);
    /**
     * 列出所有根节点
     *
     * @return
     */
    List<Organization> listRootOrganization();

    /**
     * 获取以 id 为主键的子节点（循环层级包含孙节点）的id，以“,”分隔
     *
     * @param id            当前查找对象的父节点id
     * @param includeParent 是否包含父节点
     * @param layer         以父节点为 0 ，当前查找对象为 1，往下查找多少层
     * @return
     */
    List<Organization> listChildren(long id, Boolean includeParent, Integer layer);


    /**
     * 根据code查询
     *
     * @param code
     * @return
     */
    Organization findByCode(String code);

    /**
     * 分页，条件查询
     *
     * @param parentId
     * @param code
     * @param cnName
     * @param enName
     * @param pageNo
     * @param pageSize
     * @param pageSortFiled
     * @param pageSortType
     * @return
     */
    Page<Organization> listByPage(Long parentId, String code, String cnName, String enName, int pageNo, int pageSize, String pageSortFiled, String pageSortType);

    /**
     * 检查是否有子节点
     *
     * @param id
     * @return
     */
    boolean chedkHasChild(Long id);

    /**
     * 检查code是否已经存在
     *
     * @param code
     * @return
     */
    boolean checkCode(String code);
}
