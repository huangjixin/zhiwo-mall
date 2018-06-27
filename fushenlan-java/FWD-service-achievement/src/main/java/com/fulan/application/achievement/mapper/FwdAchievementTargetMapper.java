package com.fulan.application.achievement.mapper;

import com.fulan.application.achievement.domain.FwdAchievementTarget;
import com.fulan.application.achievement.domain.FwdAchievementTargetExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface FwdAchievementTargetMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int countByExample(FwdAchievementTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int deleteByExample(FwdAchievementTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int insert(FwdAchievementTarget record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int insertSelective(FwdAchievementTarget record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    List<FwdAchievementTarget> selectByExample(FwdAchievementTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    FwdAchievementTarget selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int updateByExampleSelective(@Param("record") FwdAchievementTarget record, @Param("example") FwdAchievementTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int updateByExample(@Param("record") FwdAchievementTarget record, @Param("example") FwdAchievementTargetExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int updateByPrimaryKeySelective(FwdAchievementTarget record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table fwd_achievement_target
     *
     * @mbggenerated Wed May 23 17:26:10 CST 2018
     */
    int updateByPrimaryKey(FwdAchievementTarget record);
}