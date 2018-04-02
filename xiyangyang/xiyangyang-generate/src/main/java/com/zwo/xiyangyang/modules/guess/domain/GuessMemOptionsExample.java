package com.zwo.xiyangyang.modules.guess.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuessMemOptionsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GuessMemOptionsExample() {
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

        public Criteria andOptionIdIsNull() {
            addCriterion("OPTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andOptionIdIsNotNull() {
            addCriterion("OPTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOptionIdEqualTo(String value) {
            addCriterion("OPTION_ID =", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdNotEqualTo(String value) {
            addCriterion("OPTION_ID <>", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdGreaterThan(String value) {
            addCriterion("OPTION_ID >", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPTION_ID >=", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdLessThan(String value) {
            addCriterion("OPTION_ID <", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdLessThanOrEqualTo(String value) {
            addCriterion("OPTION_ID <=", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdLike(String value) {
            addCriterion("OPTION_ID like", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdNotLike(String value) {
            addCriterion("OPTION_ID not like", value, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdIn(List<String> values) {
            addCriterion("OPTION_ID in", values, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdNotIn(List<String> values) {
            addCriterion("OPTION_ID not in", values, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdBetween(String value1, String value2) {
            addCriterion("OPTION_ID between", value1, value2, "optionId");
            return (Criteria) this;
        }

        public Criteria andOptionIdNotBetween(String value1, String value2) {
            addCriterion("OPTION_ID not between", value1, value2, "optionId");
            return (Criteria) this;
        }

        public Criteria andMemIdIsNull() {
            addCriterion("MEM_ID is null");
            return (Criteria) this;
        }

        public Criteria andMemIdIsNotNull() {
            addCriterion("MEM_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMemIdEqualTo(String value) {
            addCriterion("MEM_ID =", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdNotEqualTo(String value) {
            addCriterion("MEM_ID <>", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdGreaterThan(String value) {
            addCriterion("MEM_ID >", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEM_ID >=", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdLessThan(String value) {
            addCriterion("MEM_ID <", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdLessThanOrEqualTo(String value) {
            addCriterion("MEM_ID <=", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdLike(String value) {
            addCriterion("MEM_ID like", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdNotLike(String value) {
            addCriterion("MEM_ID not like", value, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdIn(List<String> values) {
            addCriterion("MEM_ID in", values, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdNotIn(List<String> values) {
            addCriterion("MEM_ID not in", values, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdBetween(String value1, String value2) {
            addCriterion("MEM_ID between", value1, value2, "memId");
            return (Criteria) this;
        }

        public Criteria andMemIdNotBetween(String value1, String value2) {
            addCriterion("MEM_ID not between", value1, value2, "memId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNull() {
            addCriterion("QUESTION_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIsNotNull() {
            addCriterion("QUESTION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuestionIdEqualTo(String value) {
            addCriterion("QUESTION_ID =", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotEqualTo(String value) {
            addCriterion("QUESTION_ID <>", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThan(String value) {
            addCriterion("QUESTION_ID >", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUESTION_ID >=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThan(String value) {
            addCriterion("QUESTION_ID <", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLessThanOrEqualTo(String value) {
            addCriterion("QUESTION_ID <=", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLike(String value) {
            addCriterion("QUESTION_ID like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotLike(String value) {
            addCriterion("QUESTION_ID not like", value, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdIn(List<String> values) {
            addCriterion("QUESTION_ID in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotIn(List<String> values) {
            addCriterion("QUESTION_ID not in", values, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdBetween(String value1, String value2) {
            addCriterion("QUESTION_ID between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdNotBetween(String value1, String value2) {
            addCriterion("QUESTION_ID not between", value1, value2, "questionId");
            return (Criteria) this;
        }

        public Criteria andBetValueIsNull() {
            addCriterion("BET_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andBetValueIsNotNull() {
            addCriterion("BET_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andBetValueEqualTo(Integer value) {
            addCriterion("BET_VALUE =", value, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueNotEqualTo(Integer value) {
            addCriterion("BET_VALUE <>", value, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueGreaterThan(Integer value) {
            addCriterion("BET_VALUE >", value, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("BET_VALUE >=", value, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueLessThan(Integer value) {
            addCriterion("BET_VALUE <", value, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueLessThanOrEqualTo(Integer value) {
            addCriterion("BET_VALUE <=", value, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueIn(List<Integer> values) {
            addCriterion("BET_VALUE in", values, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueNotIn(List<Integer> values) {
            addCriterion("BET_VALUE not in", values, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueBetween(Integer value1, Integer value2) {
            addCriterion("BET_VALUE between", value1, value2, "betValue");
            return (Criteria) this;
        }

        public Criteria andBetValueNotBetween(Integer value1, Integer value2) {
            addCriterion("BET_VALUE not between", value1, value2, "betValue");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdIsNull() {
            addCriterion("GUESS_OPTIONS_COMBINE_ID is null");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdIsNotNull() {
            addCriterion("GUESS_OPTIONS_COMBINE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdEqualTo(String value) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID =", value, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdNotEqualTo(String value) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID <>", value, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdGreaterThan(String value) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID >", value, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdGreaterThanOrEqualTo(String value) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID >=", value, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdLessThan(String value) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID <", value, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdLessThanOrEqualTo(String value) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID <=", value, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdLike(String value) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID like", value, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdNotLike(String value) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID not like", value, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdIn(List<String> values) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID in", values, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdNotIn(List<String> values) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID not in", values, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdBetween(String value1, String value2) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID between", value1, value2, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdNotBetween(String value1, String value2) {
            addCriterion("GUESS_OPTIONS_COMBINE_ID not between", value1, value2, "guessOptionsCombineId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andOptionIdLikeInsensitive(String value) {
            addCriterion("upper(OPTION_ID) like", value.toUpperCase(), "optionId");
            return (Criteria) this;
        }

        public Criteria andMemIdLikeInsensitive(String value) {
            addCriterion("upper(MEM_ID) like", value.toUpperCase(), "memId");
            return (Criteria) this;
        }

        public Criteria andQuestionIdLikeInsensitive(String value) {
            addCriterion("upper(QUESTION_ID) like", value.toUpperCase(), "questionId");
            return (Criteria) this;
        }

        public Criteria andGuessOptionsCombineIdLikeInsensitive(String value) {
            addCriterion("upper(GUESS_OPTIONS_COMBINE_ID) like", value.toUpperCase(), "guessOptionsCombineId");
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