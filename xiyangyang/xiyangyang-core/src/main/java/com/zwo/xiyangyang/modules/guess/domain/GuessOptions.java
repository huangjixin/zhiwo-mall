package com.zwo.xiyangyang.modules.guess.domain;

import javax.persistence.*;

import lombok.Data;

@Data
@Table(name = "guess_options")
public class GuessOptions implements java.io.Serializable {

	/**
	 * @Fields serialVersionUID : 默认系列化版本UID  
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 赔率
     */
    @Column(name = "BET_RATE")
    private Double betRate;

    /**
     * 竞猜问题ID
     */
    @Column(name = "GUESS_QUESTION_ID")
    private String guessQuestionId;

    /**
     * 正式的外键ID
     */
    @Column(name = "REAL_QUESTION_ID")
    private String realQuestionId;

    @Column(name = "IS_RIGHT")
    private Boolean isRight;

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
        this.id = id;
    }

    /**
     * 获取名称
     *
     * @return NAME - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取赔率
     *
     * @return BET_RATE - 赔率
     */
    public Double getBetRate() {
        return betRate;
    }

    /**
     * 设置赔率
     *
     * @param betRate 赔率
     */
    public void setBetRate(Double betRate) {
        this.betRate = betRate;
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
        this.guessQuestionId = guessQuestionId;
    }

    /**
     * 获取正式的外键ID
     *
     * @return REAL_QUESTION_ID - 正式的外键ID
     */
    public String getRealQuestionId() {
        return realQuestionId;
    }

    /**
     * 设置正式的外键ID
     *
     * @param realQuestionId 正式的外键ID
     */
    public void setRealQuestionId(String realQuestionId) {
        this.realQuestionId = realQuestionId;
    }

    /**
     * @return IS_RIGHT
     */
    public Boolean getIsRight() {
        return isRight;
    }

    /**
     * @param isRight
     */
    public void setIsRight(Boolean isRight) {
        this.isRight = isRight;
    }
}