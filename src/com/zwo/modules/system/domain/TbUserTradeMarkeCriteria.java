package com.zwo.modules.system.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbUserTradeMarkeCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbUserTradeMarkeCriteria() {
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

        public Criteria andMarkCodeIsNull() {
            addCriterion("MARK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andMarkCodeIsNotNull() {
            addCriterion("MARK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andMarkCodeEqualTo(String value) {
            addCriterion("MARK_CODE =", value, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeNotEqualTo(String value) {
            addCriterion("MARK_CODE <>", value, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeGreaterThan(String value) {
            addCriterion("MARK_CODE >", value, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeGreaterThanOrEqualTo(String value) {
            addCriterion("MARK_CODE >=", value, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeLessThan(String value) {
            addCriterion("MARK_CODE <", value, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeLessThanOrEqualTo(String value) {
            addCriterion("MARK_CODE <=", value, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeLike(String value) {
            addCriterion("MARK_CODE like", value, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeNotLike(String value) {
            addCriterion("MARK_CODE not like", value, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeIn(List<String> values) {
            addCriterion("MARK_CODE in", values, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeNotIn(List<String> values) {
            addCriterion("MARK_CODE not in", values, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeBetween(String value1, String value2) {
            addCriterion("MARK_CODE between", value1, value2, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeNotBetween(String value1, String value2) {
            addCriterion("MARK_CODE not between", value1, value2, "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthIsNull() {
            addCriterion("MARK_CODE_AUTH is null");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthIsNotNull() {
            addCriterion("MARK_CODE_AUTH is not null");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthEqualTo(String value) {
            addCriterion("MARK_CODE_AUTH =", value, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthNotEqualTo(String value) {
            addCriterion("MARK_CODE_AUTH <>", value, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthGreaterThan(String value) {
            addCriterion("MARK_CODE_AUTH >", value, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthGreaterThanOrEqualTo(String value) {
            addCriterion("MARK_CODE_AUTH >=", value, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthLessThan(String value) {
            addCriterion("MARK_CODE_AUTH <", value, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthLessThanOrEqualTo(String value) {
            addCriterion("MARK_CODE_AUTH <=", value, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthLike(String value) {
            addCriterion("MARK_CODE_AUTH like", value, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthNotLike(String value) {
            addCriterion("MARK_CODE_AUTH not like", value, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthIn(List<String> values) {
            addCriterion("MARK_CODE_AUTH in", values, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthNotIn(List<String> values) {
            addCriterion("MARK_CODE_AUTH not in", values, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthBetween(String value1, String value2) {
            addCriterion("MARK_CODE_AUTH between", value1, value2, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthNotBetween(String value1, String value2) {
            addCriterion("MARK_CODE_AUTH not between", value1, value2, "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIsNull() {
            addCriterion("BRAND_AUTH is null");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIsNotNull() {
            addCriterion("BRAND_AUTH is not null");
            return (Criteria) this;
        }

        public Criteria andBrandAuthEqualTo(String value) {
            addCriterion("BRAND_AUTH =", value, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthNotEqualTo(String value) {
            addCriterion("BRAND_AUTH <>", value, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthGreaterThan(String value) {
            addCriterion("BRAND_AUTH >", value, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthGreaterThanOrEqualTo(String value) {
            addCriterion("BRAND_AUTH >=", value, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthLessThan(String value) {
            addCriterion("BRAND_AUTH <", value, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthLessThanOrEqualTo(String value) {
            addCriterion("BRAND_AUTH <=", value, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthLike(String value) {
            addCriterion("BRAND_AUTH like", value, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthNotLike(String value) {
            addCriterion("BRAND_AUTH not like", value, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIn(List<String> values) {
            addCriterion("BRAND_AUTH in", values, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthNotIn(List<String> values) {
            addCriterion("BRAND_AUTH not in", values, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthBetween(String value1, String value2) {
            addCriterion("BRAND_AUTH between", value1, value2, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthNotBetween(String value1, String value2) {
            addCriterion("BRAND_AUTH not between", value1, value2, "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveIsNull() {
            addCriterion("BRAND_AUTH_INEFFITIVE is null");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveIsNotNull() {
            addCriterion("BRAND_AUTH_INEFFITIVE is not null");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveEqualTo(String value) {
            addCriterion("BRAND_AUTH_INEFFITIVE =", value, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveNotEqualTo(String value) {
            addCriterion("BRAND_AUTH_INEFFITIVE <>", value, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveGreaterThan(String value) {
            addCriterion("BRAND_AUTH_INEFFITIVE >", value, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveGreaterThanOrEqualTo(String value) {
            addCriterion("BRAND_AUTH_INEFFITIVE >=", value, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveLessThan(String value) {
            addCriterion("BRAND_AUTH_INEFFITIVE <", value, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveLessThanOrEqualTo(String value) {
            addCriterion("BRAND_AUTH_INEFFITIVE <=", value, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveLike(String value) {
            addCriterion("BRAND_AUTH_INEFFITIVE like", value, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveNotLike(String value) {
            addCriterion("BRAND_AUTH_INEFFITIVE not like", value, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveIn(List<String> values) {
            addCriterion("BRAND_AUTH_INEFFITIVE in", values, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveNotIn(List<String> values) {
            addCriterion("BRAND_AUTH_INEFFITIVE not in", values, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveBetween(String value1, String value2) {
            addCriterion("BRAND_AUTH_INEFFITIVE between", value1, value2, "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveNotBetween(String value1, String value2) {
            addCriterion("BRAND_AUTH_INEFFITIVE not between", value1, value2, "brandAuthIneffitive");
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

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andMarkCodeLikeInsensitive(String value) {
            addCriterion("upper(MARK_CODE) like", value.toUpperCase(), "markCode");
            return (Criteria) this;
        }

        public Criteria andMarkCodeAuthLikeInsensitive(String value) {
            addCriterion("upper(MARK_CODE_AUTH) like", value.toUpperCase(), "markCodeAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthLikeInsensitive(String value) {
            addCriterion("upper(BRAND_AUTH) like", value.toUpperCase(), "brandAuth");
            return (Criteria) this;
        }

        public Criteria andBrandAuthIneffitiveLikeInsensitive(String value) {
            addCriterion("upper(BRAND_AUTH_INEFFITIVE) like", value.toUpperCase(), "brandAuthIneffitive");
            return (Criteria) this;
        }

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(USER_ID) like", value.toUpperCase(), "userId");
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