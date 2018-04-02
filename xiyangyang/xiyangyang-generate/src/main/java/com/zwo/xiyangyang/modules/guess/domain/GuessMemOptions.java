package com.zwo.xiyangyang.modules.guess.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Table(name = "guess_mem_options")
public class GuessMemOptions implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 名称竞猜选项ID
     */
    @Column(name = "OPTION_ID")
    private String optionId;

    /**
     * 会员ID
     */
    @Column(name = "MEM_ID")
    private String memId;

    /**
     * 竞猜问题ID
     */
    @Column(name = "QUESTION_ID")
    private String questionId;

    /**
     * 下注数量
     */
    @Column(name = "BET_VALUE")
    private Integer betValue;

    /**
     * 创建日期
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "GUESS_OPTIONS_COMBINE_ID")
    private String guessOptionsCombineId;

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
     * 获取名称竞猜选项ID
     *
     * @return OPTION_ID - 名称竞猜选项ID
     */
    public String getOptionId() {
        return optionId;
    }

    /**
     * 设置名称竞猜选项ID
     *
     * @param optionId 名称竞猜选项ID
     */
    public void setOptionId(String optionId) {
        this.optionId = optionId == null ? null : optionId.trim();
    }

    /**
     * 获取会员ID
     *
     * @return MEM_ID - 会员ID
     */
    public String getMemId() {
        return memId;
    }

    /**
     * 设置会员ID
     *
     * @param memId 会员ID
     */
    public void setMemId(String memId) {
        this.memId = memId == null ? null : memId.trim();
    }

    /**
     * 获取竞猜问题ID
     *
     * @return QUESTION_ID - 竞猜问题ID
     */
    public String getQuestionId() {
        return questionId;
    }

    /**
     * 设置竞猜问题ID
     *
     * @param questionId 竞猜问题ID
     */
    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    /**
     * 获取下注数量
     *
     * @return BET_VALUE - 下注数量
     */
    public Integer getBetValue() {
        return betValue;
    }

    /**
     * 设置下注数量
     *
     * @param betValue 下注数量
     */
    public void setBetValue(Integer betValue) {
        this.betValue = betValue;
    }

    /**
     * 获取创建日期
     *
     * @return CREATE_DATE - 创建日期
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建日期
     *
     * @param createDate 创建日期
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return GUESS_OPTIONS_COMBINE_ID
     */
    public String getGuessOptionsCombineId() {
        return guessOptionsCombineId;
    }

    /**
     * @param guessOptionsCombineId
     */
    public void setGuessOptionsCombineId(String guessOptionsCombineId) {
        this.guessOptionsCombineId = guessOptionsCombineId == null ? null : guessOptionsCombineId.trim();
    }
}