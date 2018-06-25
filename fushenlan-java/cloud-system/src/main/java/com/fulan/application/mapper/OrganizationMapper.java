package com.fulan.application.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.fulan.api.system.domain.Organization;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shengchenglong
 * @since 2018-01-04
 */

public interface OrganizationMapper extends BaseMapper<Organization> {

    /**
     * 获取以 id 为主键的子节点（循环层级包含孙节点）的id，以“,”分隔
     *
     * @param id                当前查找对象的父节点id
     * @param includeParent     是否包含父节点
     * @param layer             以父节点为 0 ，当前查找对象为 1，往下查找多少层
     * @param enabledSensitive  true：仅取可用的数据字典，false：不管可用不可用都取
     * @return
     */
    @Select("SELECT getOrgaChildList(#{id}, #{includeParent}, #{layer}, #{enabledSensitive})")
    String getDictChildList(@Param("id") Long id,
                            @Param("includeParent") Boolean includeParent,
                            @Param("layer") Integer layer,
                            @Param("enabledSensitive") Boolean enabledSensitive);
    @Delete("delete from organization")
    Boolean deleteAll();
}
