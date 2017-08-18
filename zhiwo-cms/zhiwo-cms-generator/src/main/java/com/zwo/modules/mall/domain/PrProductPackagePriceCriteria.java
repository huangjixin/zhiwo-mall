package com.zwo.modules.mall.domain;

import java.util.ArrayList;
import java.util.List;

public class PrProductPackagePriceCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrProductPackagePriceCriteria() {
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

        public Criteria andPropertiesIsNull() {
            addCriterion("PROPERTIES is null");
            return (Criteria) this;
        }

        public Criteria andPropertiesIsNotNull() {
            addCriterion("PROPERTIES is not null");
            return (Criteria) this;
        }

        public Criteria andPropertiesEqualTo(String value) {
            addCriterion("PROPERTIES =", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesNotEqualTo(String value) {
            addCriterion("PROPERTIES <>", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesGreaterThan(String value) {
            addCriterion("PROPERTIES >", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesGreaterThanOrEqualTo(String value) {
            addCriterion("PROPERTIES >=", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesLessThan(String value) {
            addCriterion("PROPERTIES <", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesLessThanOrEqualTo(String value) {
            addCriterion("PROPERTIES <=", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesLike(String value) {
            addCriterion("PROPERTIES like", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesNotLike(String value) {
            addCriterion("PROPERTIES not like", value, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesIn(List<String> values) {
            addCriterion("PROPERTIES in", values, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesNotIn(List<String> values) {
            addCriterion("PROPERTIES not in", values, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesBetween(String value1, String value2) {
            addCriterion("PROPERTIES between", value1, value2, "properties");
            return (Criteria) this;
        }

        public Criteria andPropertiesNotBetween(String value1, String value2) {
            addCriterion("PROPERTIES not between", value1, value2, "properties");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceIsNull() {
            addCriterion("INDEPENDENT_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceIsNotNull() {
            addCriterion("INDEPENDENT_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceEqualTo(Double value) {
            addCriterion("INDEPENDENT_PRICE =", value, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceNotEqualTo(Double value) {
            addCriterion("INDEPENDENT_PRICE <>", value, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceGreaterThan(Double value) {
            addCriterion("INDEPENDENT_PRICE >", value, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("INDEPENDENT_PRICE >=", value, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceLessThan(Double value) {
            addCriterion("INDEPENDENT_PRICE <", value, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceLessThanOrEqualTo(Double value) {
            addCriterion("INDEPENDENT_PRICE <=", value, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceIn(List<Double> values) {
            addCriterion("INDEPENDENT_PRICE in", values, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceNotIn(List<Double> values) {
            addCriterion("INDEPENDENT_PRICE not in", values, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceBetween(Double value1, Double value2) {
            addCriterion("INDEPENDENT_PRICE between", value1, value2, "independentPrice");
            return (Criteria) this;
        }

        public Criteria andIndependentPriceNotBetween(Double value1, Double value2) {
            addCriterion("INDEPENDENT_PRICE not between", value1, value2, "independentPrice");
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

        public Criteria andGourpPriceIsNull() {
            addCriterion("GOURP_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andGourpPriceIsNotNull() {
            addCriterion("GOURP_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andGourpPriceEqualTo(String value) {
            addCriterion("GOURP_PRICE =", value, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceNotEqualTo(String value) {
            addCriterion("GOURP_PRICE <>", value, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceGreaterThan(String value) {
            addCriterion("GOURP_PRICE >", value, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceGreaterThanOrEqualTo(String value) {
            addCriterion("GOURP_PRICE >=", value, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceLessThan(String value) {
            addCriterion("GOURP_PRICE <", value, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceLessThanOrEqualTo(String value) {
            addCriterion("GOURP_PRICE <=", value, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceLike(String value) {
            addCriterion("GOURP_PRICE like", value, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceNotLike(String value) {
            addCriterion("GOURP_PRICE not like", value, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceIn(List<String> values) {
            addCriterion("GOURP_PRICE in", values, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceNotIn(List<String> values) {
            addCriterion("GOURP_PRICE not in", values, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceBetween(String value1, String value2) {
            addCriterion("GOURP_PRICE between", value1, value2, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andGourpPriceNotBetween(String value1, String value2) {
            addCriterion("GOURP_PRICE not between", value1, value2, "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andPropertyIdIsNull() {
            addCriterion("PROPERTY_ID is null");
            return (Criteria) this;
        }

        public Criteria andPropertyIdIsNotNull() {
            addCriterion("PROPERTY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyIdEqualTo(String value) {
            addCriterion("PROPERTY_ID =", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotEqualTo(String value) {
            addCriterion("PROPERTY_ID <>", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdGreaterThan(String value) {
            addCriterion("PROPERTY_ID >", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROPERTY_ID >=", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdLessThan(String value) {
            addCriterion("PROPERTY_ID <", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdLessThanOrEqualTo(String value) {
            addCriterion("PROPERTY_ID <=", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdLike(String value) {
            addCriterion("PROPERTY_ID like", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotLike(String value) {
            addCriterion("PROPERTY_ID not like", value, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdIn(List<String> values) {
            addCriterion("PROPERTY_ID in", values, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotIn(List<String> values) {
            addCriterion("PROPERTY_ID not in", values, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdBetween(String value1, String value2) {
            addCriterion("PROPERTY_ID between", value1, value2, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyIdNotBetween(String value1, String value2) {
            addCriterion("PROPERTY_ID not between", value1, value2, "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdIsNull() {
            addCriterion("PROPERTY_VAlUE_ID is null");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdIsNotNull() {
            addCriterion("PROPERTY_VAlUE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdEqualTo(String value) {
            addCriterion("PROPERTY_VAlUE_ID =", value, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdNotEqualTo(String value) {
            addCriterion("PROPERTY_VAlUE_ID <>", value, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdGreaterThan(String value) {
            addCriterion("PROPERTY_VAlUE_ID >", value, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdGreaterThanOrEqualTo(String value) {
            addCriterion("PROPERTY_VAlUE_ID >=", value, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdLessThan(String value) {
            addCriterion("PROPERTY_VAlUE_ID <", value, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdLessThanOrEqualTo(String value) {
            addCriterion("PROPERTY_VAlUE_ID <=", value, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdLike(String value) {
            addCriterion("PROPERTY_VAlUE_ID like", value, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdNotLike(String value) {
            addCriterion("PROPERTY_VAlUE_ID not like", value, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdIn(List<String> values) {
            addCriterion("PROPERTY_VAlUE_ID in", values, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdNotIn(List<String> values) {
            addCriterion("PROPERTY_VAlUE_ID not in", values, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdBetween(String value1, String value2) {
            addCriterion("PROPERTY_VAlUE_ID between", value1, value2, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdNotBetween(String value1, String value2) {
            addCriterion("PROPERTY_VAlUE_ID not between", value1, value2, "propertyValueId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andPropertiesLikeInsensitive(String value) {
            addCriterion("upper(PROPERTIES) like", value.toUpperCase(), "properties");
            return (Criteria) this;
        }

        public Criteria andProductIdLikeInsensitive(String value) {
            addCriterion("upper(PRODUCT_ID) like", value.toUpperCase(), "productId");
            return (Criteria) this;
        }

        public Criteria andGourpPriceLikeInsensitive(String value) {
            addCriterion("upper(GOURP_PRICE) like", value.toUpperCase(), "gourpPrice");
            return (Criteria) this;
        }

        public Criteria andPropertyIdLikeInsensitive(String value) {
            addCriterion("upper(PROPERTY_ID) like", value.toUpperCase(), "propertyId");
            return (Criteria) this;
        }

        public Criteria andPropertyValueIdLikeInsensitive(String value) {
            addCriterion("upper(PROPERTY_VAlUE_ID) like", value.toUpperCase(), "propertyValueId");
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