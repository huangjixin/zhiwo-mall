package com.zwo.modules.member.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "group_purcse_member")
public class GroupPurcseMember implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 团ID
     */
    @Column(name = "GROUP_PURCSE_ID")
    private String groupPurcseId;

    /**
     * 会员ID
     */
    @Column(name = "MEMBER_ID")
    private String memberId;

    private static final long serialVersionUID = 1L;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取团ID
     *
     * @return GROUP_PURCSE_ID - 团ID
     */
    public String getGroupPurcseId() {
        return groupPurcseId;
    }

    /**
     * 设置团ID
     *
     * @param groupPurcseId 团ID
     */
    public void setGroupPurcseId(String groupPurcseId) {
        this.groupPurcseId = groupPurcseId == null ? null : groupPurcseId.trim();
    }

    /**
     * 获取会员ID
     *
     * @return MEMBER_ID - 会员ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * 设置会员ID
     *
     * @param memberId 会员ID
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }
}