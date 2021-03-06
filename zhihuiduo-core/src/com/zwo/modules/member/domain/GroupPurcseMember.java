package com.zwo.modules.member.domain;

import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

public class GroupPurcseMember implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_purcse_member.ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_purcse_member.GROUP_PURCSE_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    private String groupPurcseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_purcse_member.MEMBER_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    private String memberId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_purcse_member.MEMBER_NAME
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    private String memberName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_purcse_member.MEMBER_ICON
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    private String memberIcon;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column group_purcse_member.MEMBER_OPEN_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    private String memberOpenId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table group_purcse_member
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_purcse_member.ID
     *
     * @return the value of group_purcse_member.ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_purcse_member.ID
     *
     * @param id the value for group_purcse_member.ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_purcse_member.GROUP_PURCSE_ID
     *
     * @return the value of group_purcse_member.GROUP_PURCSE_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public String getGroupPurcseId() {
        return groupPurcseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_purcse_member.GROUP_PURCSE_ID
     *
     * @param groupPurcseId the value for group_purcse_member.GROUP_PURCSE_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public void setGroupPurcseId(String groupPurcseId) {
        this.groupPurcseId = groupPurcseId == null ? null : groupPurcseId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_purcse_member.MEMBER_ID
     *
     * @return the value of group_purcse_member.MEMBER_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_purcse_member.MEMBER_ID
     *
     * @param memberId the value for group_purcse_member.MEMBER_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_purcse_member.MEMBER_NAME
     *
     * @return the value of group_purcse_member.MEMBER_NAME
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public String getMemberName() {
        return memberName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_purcse_member.MEMBER_NAME
     *
     * @param memberName the value for group_purcse_member.MEMBER_NAME
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_purcse_member.MEMBER_ICON
     *
     * @return the value of group_purcse_member.MEMBER_ICON
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public String getMemberIcon() {
        return memberIcon;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_purcse_member.MEMBER_ICON
     *
     * @param memberIcon the value for group_purcse_member.MEMBER_ICON
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public void setMemberIcon(String memberIcon) {
        this.memberIcon = memberIcon == null ? null : memberIcon.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column group_purcse_member.MEMBER_OPEN_ID
     *
     * @return the value of group_purcse_member.MEMBER_OPEN_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public String getMemberOpenId() {
        return memberOpenId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column group_purcse_member.MEMBER_OPEN_ID
     *
     * @param memberOpenId the value for group_purcse_member.MEMBER_OPEN_ID
     *
     * @mbggenerated Wed Sep 13 09:19:02 CST 2017
     */
    public void setMemberOpenId(String memberOpenId) {
        this.memberOpenId = memberOpenId == null ? null : memberOpenId.trim();
    }
}