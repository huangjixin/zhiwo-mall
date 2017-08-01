package com.zwo.modules.member.domain;

import java.util.ArrayList;
import java.util.List;

public class MemberProfitCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MemberProfitCriteria() {
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

        public Criteria andMemberIdIsNull() {
            addCriterion("MEMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("MEMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("MEMBER_ID =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("MEMBER_ID <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("MEMBER_ID >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_ID >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("MEMBER_ID <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_ID <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("MEMBER_ID like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("MEMBER_ID not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("MEMBER_ID in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("MEMBER_ID not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("MEMBER_ID between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("MEMBER_ID not between", value1, value2, "memberId");
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

        public Criteria andSalePriceIsNull() {
            addCriterion("SALE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andSalePriceIsNotNull() {
            addCriterion("SALE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andSalePriceEqualTo(Double value) {
            addCriterion("SALE_PRICE =", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotEqualTo(Double value) {
            addCriterion("SALE_PRICE <>", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThan(Double value) {
            addCriterion("SALE_PRICE >", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("SALE_PRICE >=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThan(Double value) {
            addCriterion("SALE_PRICE <", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceLessThanOrEqualTo(Double value) {
            addCriterion("SALE_PRICE <=", value, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceIn(List<Double> values) {
            addCriterion("SALE_PRICE in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotIn(List<Double> values) {
            addCriterion("SALE_PRICE not in", values, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceBetween(Double value1, Double value2) {
            addCriterion("SALE_PRICE between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andSalePriceNotBetween(Double value1, Double value2) {
            addCriterion("SALE_PRICE not between", value1, value2, "salePrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceIsNull() {
            addCriterion("DEAL_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andDealPriceIsNotNull() {
            addCriterion("DEAL_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andDealPriceEqualTo(Double value) {
            addCriterion("DEAL_PRICE =", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceNotEqualTo(Double value) {
            addCriterion("DEAL_PRICE <>", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceGreaterThan(Double value) {
            addCriterion("DEAL_PRICE >", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("DEAL_PRICE >=", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceLessThan(Double value) {
            addCriterion("DEAL_PRICE <", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceLessThanOrEqualTo(Double value) {
            addCriterion("DEAL_PRICE <=", value, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceIn(List<Double> values) {
            addCriterion("DEAL_PRICE in", values, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceNotIn(List<Double> values) {
            addCriterion("DEAL_PRICE not in", values, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceBetween(Double value1, Double value2) {
            addCriterion("DEAL_PRICE between", value1, value2, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andDealPriceNotBetween(Double value1, Double value2) {
            addCriterion("DEAL_PRICE not between", value1, value2, "dealPrice");
            return (Criteria) this;
        }

        public Criteria andProfitIsNull() {
            addCriterion("PROFIT is null");
            return (Criteria) this;
        }

        public Criteria andProfitIsNotNull() {
            addCriterion("PROFIT is not null");
            return (Criteria) this;
        }

        public Criteria andProfitEqualTo(Double value) {
            addCriterion("PROFIT =", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotEqualTo(Double value) {
            addCriterion("PROFIT <>", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThan(Double value) {
            addCriterion("PROFIT >", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThanOrEqualTo(Double value) {
            addCriterion("PROFIT >=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThan(Double value) {
            addCriterion("PROFIT <", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThanOrEqualTo(Double value) {
            addCriterion("PROFIT <=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitIn(List<Double> values) {
            addCriterion("PROFIT in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotIn(List<Double> values) {
            addCriterion("PROFIT not in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitBetween(Double value1, Double value2) {
            addCriterion("PROFIT between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotBetween(Double value1, Double value2) {
            addCriterion("PROFIT not between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andDistributionValueIsNull() {
            addCriterion("DISTRIBUTION_VALUE is null");
            return (Criteria) this;
        }

        public Criteria andDistributionValueIsNotNull() {
            addCriterion("DISTRIBUTION_VALUE is not null");
            return (Criteria) this;
        }

        public Criteria andDistributionValueEqualTo(Double value) {
            addCriterion("DISTRIBUTION_VALUE =", value, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueNotEqualTo(Double value) {
            addCriterion("DISTRIBUTION_VALUE <>", value, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueGreaterThan(Double value) {
            addCriterion("DISTRIBUTION_VALUE >", value, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueGreaterThanOrEqualTo(Double value) {
            addCriterion("DISTRIBUTION_VALUE >=", value, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueLessThan(Double value) {
            addCriterion("DISTRIBUTION_VALUE <", value, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueLessThanOrEqualTo(Double value) {
            addCriterion("DISTRIBUTION_VALUE <=", value, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueIn(List<Double> values) {
            addCriterion("DISTRIBUTION_VALUE in", values, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueNotIn(List<Double> values) {
            addCriterion("DISTRIBUTION_VALUE not in", values, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueBetween(Double value1, Double value2) {
            addCriterion("DISTRIBUTION_VALUE between", value1, value2, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andDistributionValueNotBetween(Double value1, Double value2) {
            addCriterion("DISTRIBUTION_VALUE not between", value1, value2, "distributionValue");
            return (Criteria) this;
        }

        public Criteria andRealProfitIsNull() {
            addCriterion("REAL_PROFIT is null");
            return (Criteria) this;
        }

        public Criteria andRealProfitIsNotNull() {
            addCriterion("REAL_PROFIT is not null");
            return (Criteria) this;
        }

        public Criteria andRealProfitEqualTo(Double value) {
            addCriterion("REAL_PROFIT =", value, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitNotEqualTo(Double value) {
            addCriterion("REAL_PROFIT <>", value, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitGreaterThan(Double value) {
            addCriterion("REAL_PROFIT >", value, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitGreaterThanOrEqualTo(Double value) {
            addCriterion("REAL_PROFIT >=", value, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitLessThan(Double value) {
            addCriterion("REAL_PROFIT <", value, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitLessThanOrEqualTo(Double value) {
            addCriterion("REAL_PROFIT <=", value, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitIn(List<Double> values) {
            addCriterion("REAL_PROFIT in", values, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitNotIn(List<Double> values) {
            addCriterion("REAL_PROFIT not in", values, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitBetween(Double value1, Double value2) {
            addCriterion("REAL_PROFIT between", value1, value2, "realProfit");
            return (Criteria) this;
        }

        public Criteria andRealProfitNotBetween(Double value1, Double value2) {
            addCriterion("REAL_PROFIT not between", value1, value2, "realProfit");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andMemberIdLikeInsensitive(String value) {
            addCriterion("upper(MEMBER_ID) like", value.toUpperCase(), "memberId");
            return (Criteria) this;
        }

        public Criteria andProductIdLikeInsensitive(String value) {
            addCriterion("upper(PRODUCT_ID) like", value.toUpperCase(), "productId");
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