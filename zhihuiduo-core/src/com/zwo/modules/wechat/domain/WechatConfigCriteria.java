package com.zwo.modules.wechat.domain;

import java.util.ArrayList;
import java.util.List;

public class WechatConfigCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WechatConfigCriteria() {
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

        public Criteria andDescriptionIsNull() {
            addCriterion("DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("DESCRIPTION =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("DESCRIPTION <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("DESCRIPTION >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("DESCRIPTION <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("DESCRIPTION <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("DESCRIPTION like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("DESCRIPTION not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("DESCRIPTION in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("DESCRIPTION not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("DESCRIPTION between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("DESCRIPTION not between", value1, value2, "description");
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

        public Criteria andTokenIsNull() {
            addCriterion("TOKEN is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("TOKEN is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(String value) {
            addCriterion("TOKEN =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(String value) {
            addCriterion("TOKEN <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(String value) {
            addCriterion("TOKEN >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(String value) {
            addCriterion("TOKEN >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(String value) {
            addCriterion("TOKEN <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(String value) {
            addCriterion("TOKEN <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLike(String value) {
            addCriterion("TOKEN like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotLike(String value) {
            addCriterion("TOKEN not like", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<String> values) {
            addCriterion("TOKEN in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<String> values) {
            addCriterion("TOKEN not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(String value1, String value2) {
            addCriterion("TOKEN between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(String value1, String value2) {
            addCriterion("TOKEN not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andAppidIsNull() {
            addCriterion("APPID is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("APPID is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("APPID =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("APPID <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("APPID >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("APPID >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("APPID <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("APPID <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("APPID like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("APPID not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("APPID in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("APPID not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("APPID between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("APPID not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppsecretIsNull() {
            addCriterion("APPSECRET is null");
            return (Criteria) this;
        }

        public Criteria andAppsecretIsNotNull() {
            addCriterion("APPSECRET is not null");
            return (Criteria) this;
        }

        public Criteria andAppsecretEqualTo(String value) {
            addCriterion("APPSECRET =", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotEqualTo(String value) {
            addCriterion("APPSECRET <>", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretGreaterThan(String value) {
            addCriterion("APPSECRET >", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretGreaterThanOrEqualTo(String value) {
            addCriterion("APPSECRET >=", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLessThan(String value) {
            addCriterion("APPSECRET <", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLessThanOrEqualTo(String value) {
            addCriterion("APPSECRET <=", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretLike(String value) {
            addCriterion("APPSECRET like", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotLike(String value) {
            addCriterion("APPSECRET not like", value, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretIn(List<String> values) {
            addCriterion("APPSECRET in", values, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotIn(List<String> values) {
            addCriterion("APPSECRET not in", values, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretBetween(String value1, String value2) {
            addCriterion("APPSECRET between", value1, value2, "appsecret");
            return (Criteria) this;
        }

        public Criteria andAppsecretNotBetween(String value1, String value2) {
            addCriterion("APPSECRET not between", value1, value2, "appsecret");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNull() {
            addCriterion("MCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andMchIdIsNotNull() {
            addCriterion("MCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMchIdEqualTo(String value) {
            addCriterion("MCH_ID =", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotEqualTo(String value) {
            addCriterion("MCH_ID <>", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThan(String value) {
            addCriterion("MCH_ID >", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdGreaterThanOrEqualTo(String value) {
            addCriterion("MCH_ID >=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThan(String value) {
            addCriterion("MCH_ID <", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLessThanOrEqualTo(String value) {
            addCriterion("MCH_ID <=", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdLike(String value) {
            addCriterion("MCH_ID like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotLike(String value) {
            addCriterion("MCH_ID not like", value, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdIn(List<String> values) {
            addCriterion("MCH_ID in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotIn(List<String> values) {
            addCriterion("MCH_ID not in", values, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdBetween(String value1, String value2) {
            addCriterion("MCH_ID between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andMchIdNotBetween(String value1, String value2) {
            addCriterion("MCH_ID not between", value1, value2, "mchId");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeIsNull() {
            addCriterion("REFRESH_TIME is null");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeIsNotNull() {
            addCriterion("REFRESH_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeEqualTo(Integer value) {
            addCriterion("REFRESH_TIME =", value, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeNotEqualTo(Integer value) {
            addCriterion("REFRESH_TIME <>", value, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeGreaterThan(Integer value) {
            addCriterion("REFRESH_TIME >", value, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("REFRESH_TIME >=", value, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeLessThan(Integer value) {
            addCriterion("REFRESH_TIME <", value, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeLessThanOrEqualTo(Integer value) {
            addCriterion("REFRESH_TIME <=", value, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeIn(List<Integer> values) {
            addCriterion("REFRESH_TIME in", values, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeNotIn(List<Integer> values) {
            addCriterion("REFRESH_TIME not in", values, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeBetween(Integer value1, Integer value2) {
            addCriterion("REFRESH_TIME between", value1, value2, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andRefreshTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("REFRESH_TIME not between", value1, value2, "refreshTime");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyIsNull() {
            addCriterion("ATTENDTION_REPLY is null");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyIsNotNull() {
            addCriterion("ATTENDTION_REPLY is not null");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyEqualTo(String value) {
            addCriterion("ATTENDTION_REPLY =", value, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyNotEqualTo(String value) {
            addCriterion("ATTENDTION_REPLY <>", value, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyGreaterThan(String value) {
            addCriterion("ATTENDTION_REPLY >", value, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyGreaterThanOrEqualTo(String value) {
            addCriterion("ATTENDTION_REPLY >=", value, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyLessThan(String value) {
            addCriterion("ATTENDTION_REPLY <", value, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyLessThanOrEqualTo(String value) {
            addCriterion("ATTENDTION_REPLY <=", value, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyLike(String value) {
            addCriterion("ATTENDTION_REPLY like", value, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyNotLike(String value) {
            addCriterion("ATTENDTION_REPLY not like", value, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyIn(List<String> values) {
            addCriterion("ATTENDTION_REPLY in", values, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyNotIn(List<String> values) {
            addCriterion("ATTENDTION_REPLY not in", values, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyBetween(String value1, String value2) {
            addCriterion("ATTENDTION_REPLY between", value1, value2, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyNotBetween(String value1, String value2) {
            addCriterion("ATTENDTION_REPLY not between", value1, value2, "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyIsNull() {
            addCriterion("BINDING_REPLY is null");
            return (Criteria) this;
        }

        public Criteria andBindingReplyIsNotNull() {
            addCriterion("BINDING_REPLY is not null");
            return (Criteria) this;
        }

        public Criteria andBindingReplyEqualTo(String value) {
            addCriterion("BINDING_REPLY =", value, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyNotEqualTo(String value) {
            addCriterion("BINDING_REPLY <>", value, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyGreaterThan(String value) {
            addCriterion("BINDING_REPLY >", value, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyGreaterThanOrEqualTo(String value) {
            addCriterion("BINDING_REPLY >=", value, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyLessThan(String value) {
            addCriterion("BINDING_REPLY <", value, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyLessThanOrEqualTo(String value) {
            addCriterion("BINDING_REPLY <=", value, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyLike(String value) {
            addCriterion("BINDING_REPLY like", value, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyNotLike(String value) {
            addCriterion("BINDING_REPLY not like", value, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyIn(List<String> values) {
            addCriterion("BINDING_REPLY in", values, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyNotIn(List<String> values) {
            addCriterion("BINDING_REPLY not in", values, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyBetween(String value1, String value2) {
            addCriterion("BINDING_REPLY between", value1, value2, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyNotBetween(String value1, String value2) {
            addCriterion("BINDING_REPLY not between", value1, value2, "bindingReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyIsNull() {
            addCriterion("AUTO_REPLY is null");
            return (Criteria) this;
        }

        public Criteria andAutoReplyIsNotNull() {
            addCriterion("AUTO_REPLY is not null");
            return (Criteria) this;
        }

        public Criteria andAutoReplyEqualTo(String value) {
            addCriterion("AUTO_REPLY =", value, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyNotEqualTo(String value) {
            addCriterion("AUTO_REPLY <>", value, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyGreaterThan(String value) {
            addCriterion("AUTO_REPLY >", value, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyGreaterThanOrEqualTo(String value) {
            addCriterion("AUTO_REPLY >=", value, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyLessThan(String value) {
            addCriterion("AUTO_REPLY <", value, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyLessThanOrEqualTo(String value) {
            addCriterion("AUTO_REPLY <=", value, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyLike(String value) {
            addCriterion("AUTO_REPLY like", value, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyNotLike(String value) {
            addCriterion("AUTO_REPLY not like", value, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyIn(List<String> values) {
            addCriterion("AUTO_REPLY in", values, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyNotIn(List<String> values) {
            addCriterion("AUTO_REPLY not in", values, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyBetween(String value1, String value2) {
            addCriterion("AUTO_REPLY between", value1, value2, "autoReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyNotBetween(String value1, String value2) {
            addCriterion("AUTO_REPLY not between", value1, value2, "autoReply");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(DESCRIPTION) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(NAME) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andTokenLikeInsensitive(String value) {
            addCriterion("upper(TOKEN) like", value.toUpperCase(), "token");
            return (Criteria) this;
        }

        public Criteria andAppidLikeInsensitive(String value) {
            addCriterion("upper(APPID) like", value.toUpperCase(), "appid");
            return (Criteria) this;
        }

        public Criteria andAppsecretLikeInsensitive(String value) {
            addCriterion("upper(APPSECRET) like", value.toUpperCase(), "appsecret");
            return (Criteria) this;
        }

        public Criteria andMchIdLikeInsensitive(String value) {
            addCriterion("upper(MCH_ID) like", value.toUpperCase(), "mchId");
            return (Criteria) this;
        }

        public Criteria andAttendtionReplyLikeInsensitive(String value) {
            addCriterion("upper(ATTENDTION_REPLY) like", value.toUpperCase(), "attendtionReply");
            return (Criteria) this;
        }

        public Criteria andBindingReplyLikeInsensitive(String value) {
            addCriterion("upper(BINDING_REPLY) like", value.toUpperCase(), "bindingReply");
            return (Criteria) this;
        }

        public Criteria andAutoReplyLikeInsensitive(String value) {
            addCriterion("upper(AUTO_REPLY) like", value.toUpperCase(), "autoReply");
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