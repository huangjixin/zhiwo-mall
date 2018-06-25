package com.fulan.application.oa.mapper;

import com.fulan.application.oa.domain.FwdOaBankCard;
import com.fulan.application.oa.domain.FwdOaBankCardExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FwdOaBankCardMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int countByExample(FwdOaBankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int deleteByExample(FwdOaBankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int insert(FwdOaBankCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int insertSelective(FwdOaBankCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    List<FwdOaBankCard> selectByExample(FwdOaBankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    FwdOaBankCard selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int updateByExampleSelective(@Param("record") FwdOaBankCard record, @Param("example") FwdOaBankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int updateByExample(@Param("record") FwdOaBankCard record, @Param("example") FwdOaBankCardExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int updateByPrimaryKeySelective(FwdOaBankCard record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_oa_bank_card
     *
     * @mbggenerated Tue May 22 14:05:31 CST 2018
     */
    int updateByPrimaryKey(FwdOaBankCard record);
}