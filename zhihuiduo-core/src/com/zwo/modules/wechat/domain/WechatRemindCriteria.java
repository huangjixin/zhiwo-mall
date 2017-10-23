package com.zwo.modules.wechat.domain;

import java.util.ArrayList;
import java.util.List;

public class WechatRemindCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WechatRemindCriteria() {
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

        public Criteria andIfRemindOnOrderIsNull() {
            addCriterion("IF_REMIND_ON_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderIsNotNull() {
            addCriterion("IF_REMIND_ON_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_ORDER =", value, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderNotEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_ORDER <>", value, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderGreaterThan(Integer value) {
            addCriterion("IF_REMIND_ON_ORDER >", value, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_ORDER >=", value, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderLessThan(Integer value) {
            addCriterion("IF_REMIND_ON_ORDER <", value, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderLessThanOrEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_ORDER <=", value, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderIn(List<Integer> values) {
            addCriterion("IF_REMIND_ON_ORDER in", values, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderNotIn(List<Integer> values) {
            addCriterion("IF_REMIND_ON_ORDER not in", values, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderBetween(Integer value1, Integer value2) {
            addCriterion("IF_REMIND_ON_ORDER between", value1, value2, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("IF_REMIND_ON_ORDER not between", value1, value2, "ifRemindOnOrder");
            return (Criteria) this;
        }

        public Criteria andRemindContentIsNull() {
            addCriterion("REMIND_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andRemindContentIsNotNull() {
            addCriterion("REMIND_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andRemindContentEqualTo(String value) {
            addCriterion("REMIND_CONTENT =", value, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentNotEqualTo(String value) {
            addCriterion("REMIND_CONTENT <>", value, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentGreaterThan(String value) {
            addCriterion("REMIND_CONTENT >", value, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentGreaterThanOrEqualTo(String value) {
            addCriterion("REMIND_CONTENT >=", value, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentLessThan(String value) {
            addCriterion("REMIND_CONTENT <", value, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentLessThanOrEqualTo(String value) {
            addCriterion("REMIND_CONTENT <=", value, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentLike(String value) {
            addCriterion("REMIND_CONTENT like", value, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentNotLike(String value) {
            addCriterion("REMIND_CONTENT not like", value, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentIn(List<String> values) {
            addCriterion("REMIND_CONTENT in", values, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentNotIn(List<String> values) {
            addCriterion("REMIND_CONTENT not in", values, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentBetween(String value1, String value2) {
            addCriterion("REMIND_CONTENT between", value1, value2, "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentNotBetween(String value1, String value2) {
            addCriterion("REMIND_CONTENT not between", value1, value2, "remindContent");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendIsNull() {
            addCriterion("IF_REMIND_ON_SEND is null");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendIsNotNull() {
            addCriterion("IF_REMIND_ON_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_SEND =", value, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendNotEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_SEND <>", value, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendGreaterThan(Integer value) {
            addCriterion("IF_REMIND_ON_SEND >", value, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendGreaterThanOrEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_SEND >=", value, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendLessThan(Integer value) {
            addCriterion("IF_REMIND_ON_SEND <", value, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendLessThanOrEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_SEND <=", value, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendIn(List<Integer> values) {
            addCriterion("IF_REMIND_ON_SEND in", values, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendNotIn(List<Integer> values) {
            addCriterion("IF_REMIND_ON_SEND not in", values, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendBetween(Integer value1, Integer value2) {
            addCriterion("IF_REMIND_ON_SEND between", value1, value2, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnSendNotBetween(Integer value1, Integer value2) {
            addCriterion("IF_REMIND_ON_SEND not between", value1, value2, "ifRemindOnSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendIsNull() {
            addCriterion("REMIND_CONTENT_SEND is null");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendIsNotNull() {
            addCriterion("REMIND_CONTENT_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendEqualTo(String value) {
            addCriterion("REMIND_CONTENT_SEND =", value, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendNotEqualTo(String value) {
            addCriterion("REMIND_CONTENT_SEND <>", value, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendGreaterThan(String value) {
            addCriterion("REMIND_CONTENT_SEND >", value, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendGreaterThanOrEqualTo(String value) {
            addCriterion("REMIND_CONTENT_SEND >=", value, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendLessThan(String value) {
            addCriterion("REMIND_CONTENT_SEND <", value, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendLessThanOrEqualTo(String value) {
            addCriterion("REMIND_CONTENT_SEND <=", value, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendLike(String value) {
            addCriterion("REMIND_CONTENT_SEND like", value, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendNotLike(String value) {
            addCriterion("REMIND_CONTENT_SEND not like", value, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendIn(List<String> values) {
            addCriterion("REMIND_CONTENT_SEND in", values, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendNotIn(List<String> values) {
            addCriterion("REMIND_CONTENT_SEND not in", values, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendBetween(String value1, String value2) {
            addCriterion("REMIND_CONTENT_SEND between", value1, value2, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendNotBetween(String value1, String value2) {
            addCriterion("REMIND_CONTENT_SEND not between", value1, value2, "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelIsNull() {
            addCriterion("IF_REMIND_ON_CANCEL is null");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelIsNotNull() {
            addCriterion("IF_REMIND_ON_CANCEL is not null");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_CANCEL =", value, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelNotEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_CANCEL <>", value, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelGreaterThan(Integer value) {
            addCriterion("IF_REMIND_ON_CANCEL >", value, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelGreaterThanOrEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_CANCEL >=", value, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelLessThan(Integer value) {
            addCriterion("IF_REMIND_ON_CANCEL <", value, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelLessThanOrEqualTo(Integer value) {
            addCriterion("IF_REMIND_ON_CANCEL <=", value, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelIn(List<Integer> values) {
            addCriterion("IF_REMIND_ON_CANCEL in", values, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelNotIn(List<Integer> values) {
            addCriterion("IF_REMIND_ON_CANCEL not in", values, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelBetween(Integer value1, Integer value2) {
            addCriterion("IF_REMIND_ON_CANCEL between", value1, value2, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andIfRemindOnCancelNotBetween(Integer value1, Integer value2) {
            addCriterion("IF_REMIND_ON_CANCEL not between", value1, value2, "ifRemindOnCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelIsNull() {
            addCriterion("REMIND_CONTENT_CANCEL is null");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelIsNotNull() {
            addCriterion("REMIND_CONTENT_CANCEL is not null");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelEqualTo(String value) {
            addCriterion("REMIND_CONTENT_CANCEL =", value, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelNotEqualTo(String value) {
            addCriterion("REMIND_CONTENT_CANCEL <>", value, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelGreaterThan(String value) {
            addCriterion("REMIND_CONTENT_CANCEL >", value, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelGreaterThanOrEqualTo(String value) {
            addCriterion("REMIND_CONTENT_CANCEL >=", value, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelLessThan(String value) {
            addCriterion("REMIND_CONTENT_CANCEL <", value, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelLessThanOrEqualTo(String value) {
            addCriterion("REMIND_CONTENT_CANCEL <=", value, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelLike(String value) {
            addCriterion("REMIND_CONTENT_CANCEL like", value, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelNotLike(String value) {
            addCriterion("REMIND_CONTENT_CANCEL not like", value, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelIn(List<String> values) {
            addCriterion("REMIND_CONTENT_CANCEL in", values, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelNotIn(List<String> values) {
            addCriterion("REMIND_CONTENT_CANCEL not in", values, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelBetween(String value1, String value2) {
            addCriterion("REMIND_CONTENT_CANCEL between", value1, value2, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelNotBetween(String value1, String value2) {
            addCriterion("REMIND_CONTENT_CANCEL not between", value1, value2, "remindContentCancel");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andRemindContentLikeInsensitive(String value) {
            addCriterion("upper(REMIND_CONTENT) like", value.toUpperCase(), "remindContent");
            return (Criteria) this;
        }

        public Criteria andRemindContentSendLikeInsensitive(String value) {
            addCriterion("upper(REMIND_CONTENT_SEND) like", value.toUpperCase(), "remindContentSend");
            return (Criteria) this;
        }

        public Criteria andRemindContentCancelLikeInsensitive(String value) {
            addCriterion("upper(REMIND_CONTENT_CANCEL) like", value.toUpperCase(), "remindContentCancel");
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