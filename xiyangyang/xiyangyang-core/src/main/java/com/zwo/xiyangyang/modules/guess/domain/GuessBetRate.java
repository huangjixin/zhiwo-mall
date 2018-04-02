package com.zwo.xiyangyang.modules.guess.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "guess_bet_rate")
public class GuessBetRate implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 竞猜赔率
     */
    @Column(name = "BET_RATE")
    private Double betRate;

    @Column(name = "COMBINE_ID")
    private String combineId;

    @Column(name = "SORT")
    private Integer sort;

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
     * 获取竞猜赔率
     *
     * @return BET_RATE - 竞猜赔率
     */
    public Double getBetRate() {
        return betRate;
    }

    /**
     * 设置竞猜赔率
     *
     * @param betRate 竞猜赔率
     */
    public void setBetRate(Double betRate) {
        this.betRate = betRate;
    }

    /**
     * @return COMBINE_ID
     */
    public String getCombineId() {
        return combineId;
    }

    /**
     * @param combineId
     */
    public void setCombineId(String combineId) {
        this.combineId = combineId == null ? null : combineId.trim();
    }

    /**
     * @return SORT
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }
}