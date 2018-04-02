package com.zwo.xiyangyang.modules.guess.domain;

import java.util.ArrayList;
import java.util.List;

public class GuessOptionsCombineExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GuessOptionsCombineExample() {
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

        public Criteria andOptionsIdIsNull() {
            addCriterion("OPTIONS_ID is null");
            return (Criteria) this;
        }

        public Criteria andOptionsIdIsNotNull() {
            addCriterion("OPTIONS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOptionsIdEqualTo(String value) {
            addCriterion("OPTIONS_ID =", value, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdNotEqualTo(String value) {
            addCriterion("OPTIONS_ID <>", value, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdGreaterThan(String value) {
            addCriterion("OPTIONS_ID >", value, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPTIONS_ID >=", value, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdLessThan(String value) {
            addCriterion("OPTIONS_ID <", value, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdLessThanOrEqualTo(String value) {
            addCriterion("OPTIONS_ID <=", value, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdLike(String value) {
            addCriterion("OPTIONS_ID like", value, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdNotLike(String value) {
            addCriterion("OPTIONS_ID not like", value, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdIn(List<String> values) {
            addCriterion("OPTIONS_ID in", values, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdNotIn(List<String> values) {
            addCriterion("OPTIONS_ID not in", values, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdBetween(String value1, String value2) {
            addCriterion("OPTIONS_ID between", value1, value2, "optionsId");
            return (Criteria) this;
        }

        public Criteria andOptionsIdNotBetween(String value1, String value2) {
            addCriterion("OPTIONS_ID not between", value1, value2, "optionsId");
            return (Criteria) this;
        }

        public Criteria andCombineIdIsNull() {
            addCriterion("COMBINE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCombineIdIsNotNull() {
            addCriterion("COMBINE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCombineIdEqualTo(String value) {
            addCriterion("COMBINE_ID =", value, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdNotEqualTo(String value) {
            addCriterion("COMBINE_ID <>", value, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdGreaterThan(String value) {
            addCriterion("COMBINE_ID >", value, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMBINE_ID >=", value, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdLessThan(String value) {
            addCriterion("COMBINE_ID <", value, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdLessThanOrEqualTo(String value) {
            addCriterion("COMBINE_ID <=", value, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdLike(String value) {
            addCriterion("COMBINE_ID like", value, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdNotLike(String value) {
            addCriterion("COMBINE_ID not like", value, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdIn(List<String> values) {
            addCriterion("COMBINE_ID in", values, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdNotIn(List<String> values) {
            addCriterion("COMBINE_ID not in", values, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdBetween(String value1, String value2) {
            addCriterion("COMBINE_ID between", value1, value2, "combineId");
            return (Criteria) this;
        }

        public Criteria andCombineIdNotBetween(String value1, String value2) {
            addCriterion("COMBINE_ID not between", value1, value2, "combineId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andOptionsIdLikeInsensitive(String value) {
            addCriterion("upper(OPTIONS_ID) like", value.toUpperCase(), "optionsId");
            return (Criteria) this;
        }

        public Criteria andCombineIdLikeInsensitive(String value) {
            addCriterion("upper(COMBINE_ID) like", value.toUpperCase(), "combineId");
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