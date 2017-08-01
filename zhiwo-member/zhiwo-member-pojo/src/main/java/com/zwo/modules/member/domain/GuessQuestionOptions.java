package com.zwo.modules.member.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "guess_question_options")
public class GuessQuestionOptions implements Serializable {
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
        this.name = name == null ? null : name.trim();
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
        this.guessQuestionId = guessQuestionId == null ? null : guessQuestionId.trim();
    }
}