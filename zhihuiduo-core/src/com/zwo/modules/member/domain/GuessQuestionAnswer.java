package com.zwo.modules.member.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

@Table(name = "guess_question_answer")
public class GuessQuestionAnswer implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "question_id")
    private String questionId;

    @Column(name = "question_options_id")
    private String questionOptionsId;

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
}