package com.zwo.xiyangyang.modules.pr.domain;

import java.util.ArrayList;
import java.util.List;

public class PrValuePriceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrValuePriceExample() {
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

        public Criteria andProductIdIsNull() {
            addCriterion("PRODUCT_ID is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("PRODUCT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(String value) {
            addCriterion("PRODUCT_ID =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(String value) {
            addCriterion("PRODUCT_ID <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(String value) {
            addCriterion("PRODUCT_ID >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(String value) {
            addCriterion("PRODUCT_ID <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(String value) {
            addCriterion("PRODUCT_ID <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLike(String value) {
            addCriterion("PRODUCT_ID like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotLike(String value) {
            addCriterion("PRODUCT_ID not like", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<String> values) {
            addCriterion("PRODUCT_ID in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<String> values) {
            addCriterion("PRODUCT_ID not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(String value1, String value2) {
            addCriterion("PRODUCT_ID not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andValueIdIsNull() {
            addCriterion("VALUE_ID is null");
            return (Criteria) this;
        }

        public Criteria andValueIdIsNotNull() {
            addCriterion("VALUE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andValueIdEqualTo(String value) {
            addCriterion("VALUE_ID =", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotEqualTo(String value) {
            addCriterion("VALUE_ID <>", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdGreaterThan(String value) {
            addCriterion("VALUE_ID >", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdGreaterThanOrEqualTo(String value) {
            addCriterion("VALUE_ID >=", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdLessThan(String value) {
            addCriterion("VALUE_ID <", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdLessThanOrEqualTo(String value) {
            addCriterion("VALUE_ID <=", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdLike(String value) {
            addCriterion("VALUE_ID like", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotLike(String value) {
            addCriterion("VALUE_ID not like", value, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdIn(List<String> values) {
            addCriterion("VALUE_ID in", values, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotIn(List<String> values) {
            addCriterion("VALUE_ID not in", values, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdBetween(String value1, String value2) {
            addCriterion("VALUE_ID between", value1, value2, "valueId");
            return (Criteria) this;
        }

        public Criteria andValueIdNotBetween(String value1, String value2) {
            addCriterion("VALUE_ID not between", value1, value2, "valueId");
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

        public Criteria andProductIdLikeInsensitive(String value) {
            addCriterion("upper(PRODUCT_ID) like", value.toUpperCase(), "productId");
            return (Criteria) this;
        }

        public Criteria andValueIdLikeInsensitive(String value) {
            addCriterion("upper(VALUE_ID) like", value.toUpperCase(), "valueId");
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