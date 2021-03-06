package com.zwo.modules.member.dao;

import com.zwo.modules.member.domain.GroupPurcseMember;
import com.zwo.modules.member.domain.GroupPurcseMemberCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GroupPurcseMemberMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int countByExample(GroupPurcseMemberCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int deleteByExample(GroupPurcseMemberCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int insert(GroupPurcseMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int insertSelective(GroupPurcseMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    List<GroupPurcseMember> selectByExample(GroupPurcseMemberCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    GroupPurcseMember selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int updateByExampleSelective(@Param("record") GroupPurcseMember record, @Param("example") GroupPurcseMemberCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int updateByExample(@Param("record") GroupPurcseMember record, @Param("example") GroupPurcseMemberCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int updateByPrimaryKeySelective(GroupPurcseMember record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    int updateByPrimaryKey(GroupPurcseMember record);
}