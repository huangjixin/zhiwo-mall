package com.zwo.modules.member.domain;

import java.util.ArrayList;
import java.util.List;

public class GuessQuestionOptionsCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GuessQuestionOptionsCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("NAME is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andBetRateIsNull() {
            addCriterion("BET_RATE is null");
            return (Criteria) this;
        }

        public Criteria andBetRateIsNotNull() {
            addCriterion("BET_RATE is not null");
            return (Criteria) this;
        }

        public Criteria andBetRateEqualTo(Double value) {
            addCriterion("BET_RATE =", value, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateNotEqualTo(Double value) {
            addCriterion("BET_RATE <>", value, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateGreaterThan(Double value) {
            addCriterion("BET_RATE >", value, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateGreaterThanOrEqualTo(Double value) {
            addCriterion("BET_RATE >=", value, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateLessThan(Double value) {
            addCriterion("BET_RATE <", value, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateLessThanOrEqualTo(Double value) {
            addCriterion("BET_RATE <=", value, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateIn(List<Double> values) {
            addCriterion("BET_RATE in", values, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateNotIn(List<Double> values) {
            addCriterion("BET_RATE not in", values, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateBetween(Double value1, Double value2) {
            addCriterion("BET_RATE between", value1, value2, "betRate");
            return (Criteria) this;
        }

        public Criteria andBetRateNotBetween(Double value1, Double value2) {
            addCriterion("BET_RATE not between", value1, value2, "betRate");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdIsNull() {
            addCriterion("GUESS_QUESTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdIsNotNull() {
            addCriterion("GUESS_QUESTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdEqualTo(String value) {
            addCriterion("GUESS_QUESTION_ID =", value, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdNotEqualTo(String value) {
            addCriterion("GUESS_QUESTION_ID <>", value, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdGreaterThan(String value) {
            addCriterion("GUESS_QUESTION_ID >", value, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("GUESS_QUESTION_ID >=", value, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdLessThan(String value) {
            addCriterion("GUESS_QUESTION_ID <", value, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdLessThanOrEqualTo(String value) {
            addCriterion("GUESS_QUESTION_ID <=", value, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdLike(String value) {
            addCriterion("GUESS_QUESTION_ID like", value, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdNotLike(String value) {
            addCriterion("GUESS_QUESTION_ID not like", value, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdIn(List<String> values) {
            addCriterion("GUESS_QUESTION_ID in", values, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdNotIn(List<String> values) {
            addCriterion("GUESS_QUESTION_ID not in", values, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdBetween(String value1, String value2) {
            addCriterion("GUESS_QUESTION_ID between", value1, value2, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdNotBetween(String value1, String value2) {
            addCriterion("GUESS_QUESTION_ID not between", value1, value2, "guessQuestionId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(NAME) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andGuessQuestionIdLikeInsensitive(String value) {
            addCriterion("upper(GUESS_QUESTION_ID) like", value.toUpperCase(), "guessQuestionId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}