package com.zwo.modules.member.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "guess_question_memanswer")
public class GuessQuestionMemanswer implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "MEMBER_ID")
    private String memberId;

    @Column(name = "question_id")
    private String questionId;

    @Column(name = "question_options_id")
    private String questionOptionsId;

    /**
     * 是否已经支付，默认为0为未结算，为1则已经结算。
     */
    private Integer payed;

    /**
     * 竞猜的智惠豆数量
     */
    @Column(name = "bet_num")
    private Integer betNum;

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
     * @return question_id
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    /**
     * @return question_options_id
     */
    public String getQuestionOptionsId() {
        return questionOptionsId;
    }

    /**
     * @param questionOptionsId
     */
    public void setQuestionOptionsId(String questionOptionsId) {
        this.questionOptionsId = questionOptionsId == null ? null : questionOptionsId.trim();
    }

    /**
     * 获取是否已经支付，默认为0为未结算，为1则已经结算。
     *
     * @return payed - 是否已经支付，默认为0为未结算，为1则已经结算。
     */
    public Integer getPayed() {
        return payed;
    }

    /**
     * 设置是否已经支付，默认为0为未结算，为1则已经结算。
     *
     * @param payed 是否已经支付，默认为0为未结算，为1则已经结算。
     */
    public void setPayed(Integer payed) {
        this.payed = payed;
    }

    /**
     * 获取竞猜的智惠豆数量
     *
     * @return bet_num - 竞猜的智惠豆数量
     */
    public Integer getBetNum() {
        return betNum;
    }

    /**
     * 设置竞猜的智惠豆数量
     *
     * @param betNum 竞猜的智惠豆数量
     */
    public void setBetNum(Integer betNum) {
        this.betNum = betNum;
    }
}