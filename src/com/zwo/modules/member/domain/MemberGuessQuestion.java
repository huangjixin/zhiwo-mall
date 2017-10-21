package com.zwo.modules.member.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "member_guess_question")
public class MemberGuessQuestion implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 竞猜问题ID
     */
    @Column(name = "GUESS_QUESTION_ID")
    private String guessQuestionId;

    @Column(name = "MEMBER_ID")
    private String memberId;

    /**
     * 竞猜值
     */
    @Column(name = "GUESS_ACCOUNT")
    private Double guessAccount;

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
     * 获取竞猜问题ID
     *
     * @return GUESS_QUESTION_ID - 竞猜问题ID
     */
    public String getGuessQuestionId() {
        return guessQuestionId;
    }

    /**
     * 设置竞猜问题ID
     *
     * @param guessQuestionId 竞猜问题ID
     */
    public void setGuessQuestionId(String guessQuestionId) {
        this.guessQuestionId = guessQuestionId == null ? null : guessQuestionId.trim();
    }

    /**
     * @return MEMBER_ID
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * @param memberId
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    /**
     * 获取竞猜值
     *
     * @return GUESS_ACCOUNT - 竞猜值
     */
    public Double getGuessAccount() {
        return guessAccount;
    }

    /**
     * 设置竞猜值
     *
     * @param guessAccount 竞猜值
     */
    public void setGuessAccount(Double guessAccount) {
        this.guessAccount = guessAccount;
    }
}