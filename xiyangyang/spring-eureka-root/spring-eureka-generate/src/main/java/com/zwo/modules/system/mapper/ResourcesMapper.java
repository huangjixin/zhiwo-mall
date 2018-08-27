package com.zwo.modules.system.mapper;

import com.zwo.modules.system.domain.Resources;
import com.zwo.modules.system.domain.ResourcesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourcesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    long countByExample(ResourcesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    int deleteByExample(ResourcesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    int insert(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    int insertSelective(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    List<Resources> selectByExample(ResourcesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    Resources selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    int updateByPrimaryKeySelective(Resources record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_resources
     *
     * @mbg.generated Mon Aug 27 16:43:03 CST 2018
     */
    int updateByPrimaryKey(Resources record);
}