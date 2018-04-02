package com.zwo.xiyangyang.modules.guess.domain;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "guess_options_combine")
public class GuessOptionsCombine implements Serializable {
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 竞猜问题ID
     */
    @Column(name = "OPTIONS_ID")
    private String optionsId;

    /**
     * 联合Id
     */
    @Column(name = "COMBINE_ID")
    private String combineId;

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
     * @return OPTIONS_ID - 竞猜问题ID
     */
    public String getOptionsId() {
        return optionsId;
    }

    /**
     * 设置竞猜问题ID
     *
     * @param optionsId 竞猜问题ID
     */
    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId == null ? null : optionsId.trim();
    }

    /**
     * 获取联合Id
     *
     * @return COMBINE_ID - 联合Id
     */
    public String getCombineId() {
        return combineId;
    }

    /**
     * 设置联合Id
     *
     * @param combineId 联合Id
     */
    public void setCombineId(String combineId) {
        this.combineId = combineId == null ? null : combineId.trim();
    }
}