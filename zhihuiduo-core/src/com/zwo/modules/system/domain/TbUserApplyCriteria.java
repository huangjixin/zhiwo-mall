package com.zwo.modules.system.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TbUserApplyCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TbUserApplyCriteria() {
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

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("USERNAME in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("USERNAME not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("PASSWORD =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("PASSWORD <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("PASSWORD >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("PASSWORD >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("PASSWORD <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("PASSWORD <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("PASSWORD like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("PASSWORD not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("PASSWORD in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("PASSWORD not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("PASSWORD between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("PASSWORD not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andLoginDateIsNull() {
            addCriterion("LOGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLoginDateIsNotNull() {
            addCriterion("LOGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLoginDateEqualTo(Date value) {
            addCriterion("LOGIN_DATE =", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotEqualTo(Date value) {
            addCriterion("LOGIN_DATE <>", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateGreaterThan(Date value) {
            addCriterion("LOGIN_DATE >", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LOGIN_DATE >=", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateLessThan(Date value) {
            addCriterion("LOGIN_DATE <", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateLessThanOrEqualTo(Date value) {
            addCriterion("LOGIN_DATE <=", value, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateIn(List<Date> values) {
            addCriterion("LOGIN_DATE in", values, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotIn(List<Date> values) {
            addCriterion("LOGIN_DATE not in", values, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateBetween(Date value1, Date value2) {
            addCriterion("LOGIN_DATE between", value1, value2, "loginDate");
            return (Criteria) this;
        }

        public Criteria andLoginDateNotBetween(Date value1, Date value2) {
            addCriterion("LOGIN_DATE not between", value1, value2, "loginDate");
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

        public Criteria andLastLoginDateIsNull() {
            addCriterion("LAST_LOGIN_DATE is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIsNotNull() {
            addCriterion("LAST_LOGIN_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateEqualTo(Date value) {
            addCriterion("LAST_LOGIN_DATE =", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotEqualTo(Date value) {
            addCriterion("LAST_LOGIN_DATE <>", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateGreaterThan(Date value) {
            addCriterion("LAST_LOGIN_DATE >", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_LOGIN_DATE >=", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateLessThan(Date value) {
            addCriterion("LAST_LOGIN_DATE <", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateLessThanOrEqualTo(Date value) {
            addCriterion("LAST_LOGIN_DATE <=", value, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateIn(List<Date> values) {
            addCriterion("LAST_LOGIN_DATE in", values, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotIn(List<Date> values) {
            addCriterion("LAST_LOGIN_DATE not in", values, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateBetween(Date value1, Date value2) {
            addCriterion("LAST_LOGIN_DATE between", value1, value2, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andLastLoginDateNotBetween(Date value1, Date value2) {
            addCriterion("LAST_LOGIN_DATE not between", value1, value2, "lastLoginDate");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneIsNull() {
            addCriterion("MOBIL_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneIsNotNull() {
            addCriterion("MOBIL_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneEqualTo(String value) {
            addCriterion("MOBIL_PHONE =", value, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneNotEqualTo(String value) {
            addCriterion("MOBIL_PHONE <>", value, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneGreaterThan(String value) {
            addCriterion("MOBIL_PHONE >", value, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("MOBIL_PHONE >=", value, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneLessThan(String value) {
            addCriterion("MOBIL_PHONE <", value, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneLessThanOrEqualTo(String value) {
            addCriterion("MOBIL_PHONE <=", value, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneLike(String value) {
            addCriterion("MOBIL_PHONE like", value, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneNotLike(String value) {
            addCriterion("MOBIL_PHONE not like", value, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneIn(List<String> values) {
            addCriterion("MOBIL_PHONE in", values, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneNotIn(List<String> values) {
            addCriterion("MOBIL_PHONE not in", values, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneBetween(String value1, String value2) {
            addCriterion("MOBIL_PHONE between", value1, value2, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneNotBetween(String value1, String value2) {
            addCriterion("MOBIL_PHONE not between", value1, value2, "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andDisableIsNull() {
            addCriterion("DISABLE is null");
            return (Criteria) this;
        }

        public Criteria andDisableIsNotNull() {
            addCriterion("DISABLE is not null");
            return (Criteria) this;
        }

        public Criteria andDisableEqualTo(Boolean value) {
            addCriterion("DISABLE =", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotEqualTo(Boolean value) {
            addCriterion("DISABLE <>", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThan(Boolean value) {
            addCriterion("DISABLE >", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("DISABLE >=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThan(Boolean value) {
            addCriterion("DISABLE <", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableLessThanOrEqualTo(Boolean value) {
            addCriterion("DISABLE <=", value, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableIn(List<Boolean> values) {
            addCriterion("DISABLE in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotIn(List<Boolean> values) {
            addCriterion("DISABLE not in", values, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableBetween(Boolean value1, Boolean value2) {
            addCriterion("DISABLE between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andDisableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("DISABLE not between", value1, value2, "disable");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("CREATOR is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("CREATOR is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(String value) {
            addCriterion("CREATOR =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(String value) {
            addCriterion("CREATOR <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(String value) {
            addCriterion("CREATOR >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("CREATOR >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(String value) {
            addCriterion("CREATOR <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(String value) {
            addCriterion("CREATOR <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLike(String value) {
            addCriterion("CREATOR like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotLike(String value) {
            addCriterion("CREATOR not like", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<String> values) {
            addCriterion("CREATOR in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<String> values) {
            addCriterion("CREATOR not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(String value1, String value2) {
            addCriterion("CREATOR between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(String value1, String value2) {
            addCriterion("CREATOR not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNull() {
            addCriterion("UPDATER is null");
            return (Criteria) this;
        }

        public Criteria andUpdaterIsNotNull() {
            addCriterion("UPDATER is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaterEqualTo(String value) {
            addCriterion("UPDATER =", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotEqualTo(String value) {
            addCriterion("UPDATER <>", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThan(String value) {
            addCriterion("UPDATER >", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATER >=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThan(String value) {
            addCriterion("UPDATER <", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLessThanOrEqualTo(String value) {
            addCriterion("UPDATER <=", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterLike(String value) {
            addCriterion("UPDATER like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotLike(String value) {
            addCriterion("UPDATER not like", value, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterIn(List<String> values) {
            addCriterion("UPDATER in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotIn(List<String> values) {
            addCriterion("UPDATER not in", values, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterBetween(String value1, String value2) {
            addCriterion("UPDATER between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andUpdaterNotBetween(String value1, String value2) {
            addCriterion("UPDATER not between", value1, value2, "updater");
            return (Criteria) this;
        }

        public Criteria andSexIsNull() {
            addCriterion("SEX is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("SEX is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(Boolean value) {
            addCriterion("SEX =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(Boolean value) {
            addCriterion("SEX <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(Boolean value) {
            addCriterion("SEX >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(Boolean value) {
            addCriterion("SEX >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(Boolean value) {
            addCriterion("SEX <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(Boolean value) {
            addCriterion("SEX <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<Boolean> values) {
            addCriterion("SEX in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<Boolean> values) {
            addCriterion("SEX not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(Boolean value1, Boolean value2) {
            addCriterion("SEX between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(Boolean value1, Boolean value2) {
            addCriterion("SEX not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andIconIsNull() {
            addCriterion("ICON is null");
            return (Criteria) this;
        }

        public Criteria andIconIsNotNull() {
            addCriterion("ICON is not null");
            return (Criteria) this;
        }

        public Criteria andIconEqualTo(String value) {
            addCriterion("ICON =", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotEqualTo(String value) {
            addCriterion("ICON <>", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThan(String value) {
            addCriterion("ICON >", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconGreaterThanOrEqualTo(String value) {
            addCriterion("ICON >=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThan(String value) {
            addCriterion("ICON <", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLessThanOrEqualTo(String value) {
            addCriterion("ICON <=", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconLike(String value) {
            addCriterion("ICON like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotLike(String value) {
            addCriterion("ICON not like", value, "icon");
            return (Criteria) this;
        }

        public Criteria andIconIn(List<String> values) {
            addCriterion("ICON in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotIn(List<String> values) {
            addCriterion("ICON not in", values, "icon");
            return (Criteria) this;
        }

        public Criteria andIconBetween(String value1, String value2) {
            addCriterion("ICON between", value1, value2, "icon");
            return (Criteria) this;
        }

        public Criteria andIconNotBetween(String value1, String value2) {
            addCriterion("ICON not between", value1, value2, "icon");
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

        public Criteria andAgeIsNull() {
            addCriterion("AGE is null");
            return (Criteria) this;
        }

        public Criteria andAgeIsNotNull() {
            addCriterion("AGE is not null");
            return (Criteria) this;
        }

        public Criteria andAgeEqualTo(Integer value) {
            addCriterion("AGE =", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotEqualTo(Integer value) {
            addCriterion("AGE <>", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThan(Integer value) {
            addCriterion("AGE >", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("AGE >=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThan(Integer value) {
            addCriterion("AGE <", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeLessThanOrEqualTo(Integer value) {
            addCriterion("AGE <=", value, "age");
            return (Criteria) this;
        }

        public Criteria andAgeIn(List<Integer> values) {
            addCriterion("AGE in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotIn(List<Integer> values) {
            addCriterion("AGE not in", values, "age");
            return (Criteria) this;
        }

        public Criteria andAgeBetween(Integer value1, Integer value2) {
            addCriterion("AGE between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("AGE not between", value1, value2, "age");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("WEIGHT is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("WEIGHT is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(Integer value) {
            addCriterion("WEIGHT =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(Integer value) {
            addCriterion("WEIGHT <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(Integer value) {
            addCriterion("WEIGHT >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("WEIGHT >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(Integer value) {
            addCriterion("WEIGHT <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(Integer value) {
            addCriterion("WEIGHT <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<Integer> values) {
            addCriterion("WEIGHT in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<Integer> values) {
            addCriterion("WEIGHT not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(Integer value1, Integer value2) {
            addCriterion("WEIGHT between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("WEIGHT not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("QQ is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("QQ is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("QQ =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("QQ <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("QQ >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("QQ >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("QQ <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("QQ <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("QQ like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("QQ not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("QQ in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("QQ not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("QQ between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("QQ not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNull() {
            addCriterion("WEIXIN is null");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNotNull() {
            addCriterion("WEIXIN is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinEqualTo(String value) {
            addCriterion("WEIXIN =", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotEqualTo(String value) {
            addCriterion("WEIXIN <>", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThan(String value) {
            addCriterion("WEIXIN >", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThanOrEqualTo(String value) {
            addCriterion("WEIXIN >=", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThan(String value) {
            addCriterion("WEIXIN <", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThanOrEqualTo(String value) {
            addCriterion("WEIXIN <=", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLike(String value) {
            addCriterion("WEIXIN like", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotLike(String value) {
            addCriterion("WEIXIN not like", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinIn(List<String> values) {
            addCriterion("WEIXIN in", values, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotIn(List<String> values) {
            addCriterion("WEIXIN not in", values, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinBetween(String value1, String value2) {
            addCriterion("WEIXIN between", value1, value2, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotBetween(String value1, String value2) {
            addCriterion("WEIXIN not between", value1, value2, "weixin");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNull() {
            addCriterion("REAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andRealNameIsNotNull() {
            addCriterion("REAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andRealNameEqualTo(String value) {
            addCriterion("REAL_NAME =", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotEqualTo(String value) {
            addCriterion("REAL_NAME <>", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThan(String value) {
            addCriterion("REAL_NAME >", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameGreaterThanOrEqualTo(String value) {
            addCriterion("REAL_NAME >=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThan(String value) {
            addCriterion("REAL_NAME <", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLessThanOrEqualTo(String value) {
            addCriterion("REAL_NAME <=", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameLike(String value) {
            addCriterion("REAL_NAME like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotLike(String value) {
            addCriterion("REAL_NAME not like", value, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameIn(List<String> values) {
            addCriterion("REAL_NAME in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotIn(List<String> values) {
            addCriterion("REAL_NAME not in", values, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameBetween(String value1, String value2) {
            addCriterion("REAL_NAME between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andRealNameNotBetween(String value1, String value2) {
            addCriterion("REAL_NAME not between", value1, value2, "realName");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("SORT is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("SORT is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("SORT =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("SORT <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("SORT >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("SORT >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("SORT <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("SORT <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("SORT in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("SORT not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("SORT between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("SORT not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNull() {
            addCriterion("ORG_ID is null");
            return (Criteria) this;
        }

        public Criteria andOrgIdIsNotNull() {
            addCriterion("ORG_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOrgIdEqualTo(String value) {
            addCriterion("ORG_ID =", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotEqualTo(String value) {
            addCriterion("ORG_ID <>", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThan(String value) {
            addCriterion("ORG_ID >", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_ID >=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThan(String value) {
            addCriterion("ORG_ID <", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLessThanOrEqualTo(String value) {
            addCriterion("ORG_ID <=", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdLike(String value) {
            addCriterion("ORG_ID like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotLike(String value) {
            addCriterion("ORG_ID not like", value, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdIn(List<String> values) {
            addCriterion("ORG_ID in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotIn(List<String> values) {
            addCriterion("ORG_ID not in", values, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdBetween(String value1, String value2) {
            addCriterion("ORG_ID between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andOrgIdNotBetween(String value1, String value2) {
            addCriterion("ORG_ID not between", value1, value2, "orgId");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("NICKNAME is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("NICKNAME is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("NICKNAME =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("NICKNAME <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("NICKNAME >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("NICKNAME >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("NICKNAME <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("NICKNAME <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("NICKNAME like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("NICKNAME not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("NICKNAME in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("NICKNAME not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("NICKNAME between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("NICKNAME not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNull() {
            addCriterion("LOGIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("LOGIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("LOGIN_NAME =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("LOGIN_NAME <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("LOGIN_NAME >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("LOGIN_NAME <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("LOGIN_NAME like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("LOGIN_NAME not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("LOGIN_NAME in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("LOGIN_NAME not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactIsNull() {
            addCriterion("EMERGECY_CONTACT is null");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactIsNotNull() {
            addCriterion("EMERGECY_CONTACT is not null");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactEqualTo(String value) {
            addCriterion("EMERGECY_CONTACT =", value, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactNotEqualTo(String value) {
            addCriterion("EMERGECY_CONTACT <>", value, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactGreaterThan(String value) {
            addCriterion("EMERGECY_CONTACT >", value, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactGreaterThanOrEqualTo(String value) {
            addCriterion("EMERGECY_CONTACT >=", value, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactLessThan(String value) {
            addCriterion("EMERGECY_CONTACT <", value, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactLessThanOrEqualTo(String value) {
            addCriterion("EMERGECY_CONTACT <=", value, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactLike(String value) {
            addCriterion("EMERGECY_CONTACT like", value, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactNotLike(String value) {
            addCriterion("EMERGECY_CONTACT not like", value, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactIn(List<String> values) {
            addCriterion("EMERGECY_CONTACT in", values, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactNotIn(List<String> values) {
            addCriterion("EMERGECY_CONTACT not in", values, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactBetween(String value1, String value2) {
            addCriterion("EMERGECY_CONTACT between", value1, value2, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactNotBetween(String value1, String value2) {
            addCriterion("EMERGECY_CONTACT not between", value1, value2, "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalIsNull() {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL is null");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalIsNotNull() {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL is not null");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalEqualTo(Boolean value) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL =", value, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalNotEqualTo(Boolean value) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL <>", value, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalGreaterThan(Boolean value) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL >", value, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL >=", value, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalLessThan(Boolean value) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL <", value, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL <=", value, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalIn(List<Boolean> values) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL in", values, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalNotIn(List<Boolean> values) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL not in", values, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL between", value1, value2, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIsCertificateInternationalNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_CERTIFICATE_INTERNATIONAL not between", value1, value2, "isCertificateInternational");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("ID_CARD is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("ID_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("ID_CARD =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("ID_CARD <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("ID_CARD >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("ID_CARD >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("ID_CARD <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("ID_CARD <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("ID_CARD like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("ID_CARD not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("ID_CARD in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("ID_CARD not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("ID_CARD between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("ID_CARD not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andCoopNameIsNull() {
            addCriterion("COOP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCoopNameIsNotNull() {
            addCriterion("COOP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCoopNameEqualTo(String value) {
            addCriterion("COOP_NAME =", value, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameNotEqualTo(String value) {
            addCriterion("COOP_NAME <>", value, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameGreaterThan(String value) {
            addCriterion("COOP_NAME >", value, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameGreaterThanOrEqualTo(String value) {
            addCriterion("COOP_NAME >=", value, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameLessThan(String value) {
            addCriterion("COOP_NAME <", value, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameLessThanOrEqualTo(String value) {
            addCriterion("COOP_NAME <=", value, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameLike(String value) {
            addCriterion("COOP_NAME like", value, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameNotLike(String value) {
            addCriterion("COOP_NAME not like", value, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameIn(List<String> values) {
            addCriterion("COOP_NAME in", values, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameNotIn(List<String> values) {
            addCriterion("COOP_NAME not in", values, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameBetween(String value1, String value2) {
            addCriterion("COOP_NAME between", value1, value2, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopNameNotBetween(String value1, String value2) {
            addCriterion("COOP_NAME not between", value1, value2, "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopAddressIsNull() {
            addCriterion("COOP_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andCoopAddressIsNotNull() {
            addCriterion("COOP_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andCoopAddressEqualTo(String value) {
            addCriterion("COOP_ADDRESS =", value, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressNotEqualTo(String value) {
            addCriterion("COOP_ADDRESS <>", value, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressGreaterThan(String value) {
            addCriterion("COOP_ADDRESS >", value, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressGreaterThanOrEqualTo(String value) {
            addCriterion("COOP_ADDRESS >=", value, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressLessThan(String value) {
            addCriterion("COOP_ADDRESS <", value, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressLessThanOrEqualTo(String value) {
            addCriterion("COOP_ADDRESS <=", value, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressLike(String value) {
            addCriterion("COOP_ADDRESS like", value, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressNotLike(String value) {
            addCriterion("COOP_ADDRESS not like", value, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressIn(List<String> values) {
            addCriterion("COOP_ADDRESS in", values, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressNotIn(List<String> values) {
            addCriterion("COOP_ADDRESS not in", values, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressBetween(String value1, String value2) {
            addCriterion("COOP_ADDRESS between", value1, value2, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andCoopAddressNotBetween(String value1, String value2) {
            addCriterion("COOP_ADDRESS not between", value1, value2, "coopAddress");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeIsNull() {
            addCriterion("BUSSINESS_LICENSE_CODE is null");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeIsNotNull() {
            addCriterion("BUSSINESS_LICENSE_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeEqualTo(String value) {
            addCriterion("BUSSINESS_LICENSE_CODE =", value, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeNotEqualTo(String value) {
            addCriterion("BUSSINESS_LICENSE_CODE <>", value, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeGreaterThan(String value) {
            addCriterion("BUSSINESS_LICENSE_CODE >", value, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BUSSINESS_LICENSE_CODE >=", value, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeLessThan(String value) {
            addCriterion("BUSSINESS_LICENSE_CODE <", value, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeLessThanOrEqualTo(String value) {
            addCriterion("BUSSINESS_LICENSE_CODE <=", value, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeLike(String value) {
            addCriterion("BUSSINESS_LICENSE_CODE like", value, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeNotLike(String value) {
            addCriterion("BUSSINESS_LICENSE_CODE not like", value, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeIn(List<String> values) {
            addCriterion("BUSSINESS_LICENSE_CODE in", values, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeNotIn(List<String> values) {
            addCriterion("BUSSINESS_LICENSE_CODE not in", values, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeBetween(String value1, String value2) {
            addCriterion("BUSSINESS_LICENSE_CODE between", value1, value2, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeNotBetween(String value1, String value2) {
            addCriterion("BUSSINESS_LICENSE_CODE not between", value1, value2, "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNull() {
            addCriterion("ORG_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("ORG_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("ORG_CODE =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("ORG_CODE <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("ORG_CODE >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CODE >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("ORG_CODE <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("ORG_CODE <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("ORG_CODE like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("ORG_CODE not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("ORG_CODE in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("ORG_CODE not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("ORG_CODE between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("ORG_CODE not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andTaxpayerIsNull() {
            addCriterion("TAXPAYER is null");
            return (Criteria) this;
        }

        public Criteria andTaxpayerIsNotNull() {
            addCriterion("TAXPAYER is not null");
            return (Criteria) this;
        }

        public Criteria andTaxpayerEqualTo(String value) {
            addCriterion("TAXPAYER =", value, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNotEqualTo(String value) {
            addCriterion("TAXPAYER <>", value, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerGreaterThan(String value) {
            addCriterion("TAXPAYER >", value, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerGreaterThanOrEqualTo(String value) {
            addCriterion("TAXPAYER >=", value, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerLessThan(String value) {
            addCriterion("TAXPAYER <", value, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerLessThanOrEqualTo(String value) {
            addCriterion("TAXPAYER <=", value, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerLike(String value) {
            addCriterion("TAXPAYER like", value, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNotLike(String value) {
            addCriterion("TAXPAYER not like", value, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerIn(List<String> values) {
            addCriterion("TAXPAYER in", values, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNotIn(List<String> values) {
            addCriterion("TAXPAYER not in", values, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerBetween(String value1, String value2) {
            addCriterion("TAXPAYER between", value1, value2, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andTaxpayerNotBetween(String value1, String value2) {
            addCriterion("TAXPAYER not between", value1, value2, "taxpayer");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeIsNull() {
            addCriterion("SOCIETY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeIsNotNull() {
            addCriterion("SOCIETY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeEqualTo(String value) {
            addCriterion("SOCIETY_CODE =", value, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeNotEqualTo(String value) {
            addCriterion("SOCIETY_CODE <>", value, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeGreaterThan(String value) {
            addCriterion("SOCIETY_CODE >", value, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("SOCIETY_CODE >=", value, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeLessThan(String value) {
            addCriterion("SOCIETY_CODE <", value, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeLessThanOrEqualTo(String value) {
            addCriterion("SOCIETY_CODE <=", value, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeLike(String value) {
            addCriterion("SOCIETY_CODE like", value, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeNotLike(String value) {
            addCriterion("SOCIETY_CODE not like", value, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeIn(List<String> values) {
            addCriterion("SOCIETY_CODE in", values, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeNotIn(List<String> values) {
            addCriterion("SOCIETY_CODE not in", values, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeBetween(String value1, String value2) {
            addCriterion("SOCIETY_CODE between", value1, value2, "societyCode");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeNotBetween(String value1, String value2) {
            addCriterion("SOCIETY_CODE not between", value1, value2, "societyCode");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1IsNull() {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1IsNotNull() {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1EqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 =", value, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1NotEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 <>", value, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1GreaterThan(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 >", value, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1GreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 >=", value, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1LessThan(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 <", value, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1LessThanOrEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 <=", value, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1Like(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 like", value, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1NotLike(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 not like", value, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1In(List<String> values) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 in", values, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1NotIn(List<String> values) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 not in", values, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1Between(String value1, String value2) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 between", value1, value2, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1NotBetween(String value1, String value2) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD1 not between", value1, value2, "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2IsNull() {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 is null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2IsNotNull() {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 is not null");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2EqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 =", value, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2NotEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 <>", value, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2GreaterThan(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 >", value, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2GreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 >=", value, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2LessThan(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 <", value, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2LessThanOrEqualTo(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 <=", value, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2Like(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 like", value, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2NotLike(String value) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 not like", value, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2In(List<String> values) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 in", values, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2NotIn(List<String> values) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 not in", values, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2Between(String value1, String value2) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 between", value1, value2, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2NotBetween(String value1, String value2) {
            addCriterion("LEGAL_REPRESENTATIVE_CARD2 not between", value1, value2, "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveIsNull() {
            addCriterion("LEGAL_IDCARD_EFFITIVE is null");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveIsNotNull() {
            addCriterion("LEGAL_IDCARD_EFFITIVE is not null");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveEqualTo(String value) {
            addCriterion("LEGAL_IDCARD_EFFITIVE =", value, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveNotEqualTo(String value) {
            addCriterion("LEGAL_IDCARD_EFFITIVE <>", value, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveGreaterThan(String value) {
            addCriterion("LEGAL_IDCARD_EFFITIVE >", value, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveGreaterThanOrEqualTo(String value) {
            addCriterion("LEGAL_IDCARD_EFFITIVE >=", value, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveLessThan(String value) {
            addCriterion("LEGAL_IDCARD_EFFITIVE <", value, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveLessThanOrEqualTo(String value) {
            addCriterion("LEGAL_IDCARD_EFFITIVE <=", value, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveLike(String value) {
            addCriterion("LEGAL_IDCARD_EFFITIVE like", value, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveNotLike(String value) {
            addCriterion("LEGAL_IDCARD_EFFITIVE not like", value, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveIn(List<String> values) {
            addCriterion("LEGAL_IDCARD_EFFITIVE in", values, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveNotIn(List<String> values) {
            addCriterion("LEGAL_IDCARD_EFFITIVE not in", values, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveBetween(String value1, String value2) {
            addCriterion("LEGAL_IDCARD_EFFITIVE between", value1, value2, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveNotBetween(String value1, String value2) {
            addCriterion("LEGAL_IDCARD_EFFITIVE not between", value1, value2, "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicIsNull() {
            addCriterion("BUSSINESS_LICENSE_PIC is null");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicIsNotNull() {
            addCriterion("BUSSINESS_LICENSE_PIC is not null");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicEqualTo(String value) {
            addCriterion("BUSSINESS_LICENSE_PIC =", value, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicNotEqualTo(String value) {
            addCriterion("BUSSINESS_LICENSE_PIC <>", value, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicGreaterThan(String value) {
            addCriterion("BUSSINESS_LICENSE_PIC >", value, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicGreaterThanOrEqualTo(String value) {
            addCriterion("BUSSINESS_LICENSE_PIC >=", value, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicLessThan(String value) {
            addCriterion("BUSSINESS_LICENSE_PIC <", value, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicLessThanOrEqualTo(String value) {
            addCriterion("BUSSINESS_LICENSE_PIC <=", value, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicLike(String value) {
            addCriterion("BUSSINESS_LICENSE_PIC like", value, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicNotLike(String value) {
            addCriterion("BUSSINESS_LICENSE_PIC not like", value, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicIn(List<String> values) {
            addCriterion("BUSSINESS_LICENSE_PIC in", values, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicNotIn(List<String> values) {
            addCriterion("BUSSINESS_LICENSE_PIC not in", values, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicBetween(String value1, String value2) {
            addCriterion("BUSSINESS_LICENSE_PIC between", value1, value2, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicNotBetween(String value1, String value2) {
            addCriterion("BUSSINESS_LICENSE_PIC not between", value1, value2, "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountIsNull() {
            addCriterion("LICENSE_FOR_OPENING_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountIsNotNull() {
            addCriterion("LICENSE_FOR_OPENING_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountEqualTo(String value) {
            addCriterion("LICENSE_FOR_OPENING_COUNT =", value, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountNotEqualTo(String value) {
            addCriterion("LICENSE_FOR_OPENING_COUNT <>", value, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountGreaterThan(String value) {
            addCriterion("LICENSE_FOR_OPENING_COUNT >", value, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountGreaterThanOrEqualTo(String value) {
            addCriterion("LICENSE_FOR_OPENING_COUNT >=", value, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountLessThan(String value) {
            addCriterion("LICENSE_FOR_OPENING_COUNT <", value, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountLessThanOrEqualTo(String value) {
            addCriterion("LICENSE_FOR_OPENING_COUNT <=", value, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountLike(String value) {
            addCriterion("LICENSE_FOR_OPENING_COUNT like", value, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountNotLike(String value) {
            addCriterion("LICENSE_FOR_OPENING_COUNT not like", value, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountIn(List<String> values) {
            addCriterion("LICENSE_FOR_OPENING_COUNT in", values, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountNotIn(List<String> values) {
            addCriterion("LICENSE_FOR_OPENING_COUNT not in", values, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountBetween(String value1, String value2) {
            addCriterion("LICENSE_FOR_OPENING_COUNT between", value1, value2, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountNotBetween(String value1, String value2) {
            addCriterion("LICENSE_FOR_OPENING_COUNT not between", value1, value2, "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdIsNull() {
            addCriterion("QUANTITY_REPORT_ID is null");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdIsNotNull() {
            addCriterion("QUANTITY_REPORT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdEqualTo(String value) {
            addCriterion("QUANTITY_REPORT_ID =", value, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdNotEqualTo(String value) {
            addCriterion("QUANTITY_REPORT_ID <>", value, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdGreaterThan(String value) {
            addCriterion("QUANTITY_REPORT_ID >", value, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdGreaterThanOrEqualTo(String value) {
            addCriterion("QUANTITY_REPORT_ID >=", value, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdLessThan(String value) {
            addCriterion("QUANTITY_REPORT_ID <", value, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdLessThanOrEqualTo(String value) {
            addCriterion("QUANTITY_REPORT_ID <=", value, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdLike(String value) {
            addCriterion("QUANTITY_REPORT_ID like", value, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdNotLike(String value) {
            addCriterion("QUANTITY_REPORT_ID not like", value, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdIn(List<String> values) {
            addCriterion("QUANTITY_REPORT_ID in", values, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdNotIn(List<String> values) {
            addCriterion("QUANTITY_REPORT_ID not in", values, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdBetween(String value1, String value2) {
            addCriterion("QUANTITY_REPORT_ID between", value1, value2, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdNotBetween(String value1, String value2) {
            addCriterion("QUANTITY_REPORT_ID not between", value1, value2, "quantityReportId");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andUsernameLikeInsensitive(String value) {
            addCriterion("upper(USERNAME) like", value.toUpperCase(), "username");
            return (Criteria) this;
        }

        public Criteria andPasswordLikeInsensitive(String value) {
            addCriterion("upper(PASSWORD) like", value.toUpperCase(), "password");
            return (Criteria) this;
        }

        public Criteria andEmailLikeInsensitive(String value) {
            addCriterion("upper(EMAIL) like", value.toUpperCase(), "email");
            return (Criteria) this;
        }

        public Criteria andMobilPhoneLikeInsensitive(String value) {
            addCriterion("upper(MOBIL_PHONE) like", value.toUpperCase(), "mobilPhone");
            return (Criteria) this;
        }

        public Criteria andCreatorLikeInsensitive(String value) {
            addCriterion("upper(CREATOR) like", value.toUpperCase(), "creator");
            return (Criteria) this;
        }

        public Criteria andUpdaterLikeInsensitive(String value) {
            addCriterion("upper(UPDATER) like", value.toUpperCase(), "updater");
            return (Criteria) this;
        }

        public Criteria andIconLikeInsensitive(String value) {
            addCriterion("upper(ICON) like", value.toUpperCase(), "icon");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(DESCRIPTION) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andQqLikeInsensitive(String value) {
            addCriterion("upper(QQ) like", value.toUpperCase(), "qq");
            return (Criteria) this;
        }

        public Criteria andWeixinLikeInsensitive(String value) {
            addCriterion("upper(WEIXIN) like", value.toUpperCase(), "weixin");
            return (Criteria) this;
        }

        public Criteria andRealNameLikeInsensitive(String value) {
            addCriterion("upper(REAL_NAME) like", value.toUpperCase(), "realName");
            return (Criteria) this;
        }

        public Criteria andOrgIdLikeInsensitive(String value) {
            addCriterion("upper(ORG_ID) like", value.toUpperCase(), "orgId");
            return (Criteria) this;
        }

        public Criteria andNicknameLikeInsensitive(String value) {
            addCriterion("upper(NICKNAME) like", value.toUpperCase(), "nickname");
            return (Criteria) this;
        }

        public Criteria andLoginNameLikeInsensitive(String value) {
            addCriterion("upper(LOGIN_NAME) like", value.toUpperCase(), "loginName");
            return (Criteria) this;
        }

        public Criteria andTypeLikeInsensitive(String value) {
            addCriterion("upper(TYPE) like", value.toUpperCase(), "type");
            return (Criteria) this;
        }

        public Criteria andEmergecyContactLikeInsensitive(String value) {
            addCriterion("upper(EMERGECY_CONTACT) like", value.toUpperCase(), "emergecyContact");
            return (Criteria) this;
        }

        public Criteria andIdCardLikeInsensitive(String value) {
            addCriterion("upper(ID_CARD) like", value.toUpperCase(), "idCard");
            return (Criteria) this;
        }

        public Criteria andCoopNameLikeInsensitive(String value) {
            addCriterion("upper(COOP_NAME) like", value.toUpperCase(), "coopName");
            return (Criteria) this;
        }

        public Criteria andCoopAddressLikeInsensitive(String value) {
            addCriterion("upper(COOP_ADDRESS) like", value.toUpperCase(), "coopAddress");
            return (Criteria) this;
        }

        public Criteria andBussinessLicenseCodeLikeInsensitive(String value) {
            addCriterion("upper(BUSSINESS_LICENSE_CODE) like", value.toUpperCase(), "bussinessLicenseCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLikeInsensitive(String value) {
            addCriterion("upper(ORG_CODE) like", value.toUpperCase(), "orgCode");
            return (Criteria) this;
        }

        public Criteria andTaxpayerLikeInsensitive(String value) {
            addCriterion("upper(TAXPAYER) like", value.toUpperCase(), "taxpayer");
            return (Criteria) this;
        }

        public Criteria andSocietyCodeLikeInsensitive(String value) {
            addCriterion("upper(SOCIETY_CODE) like", value.toUpperCase(), "societyCode");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard1LikeInsensitive(String value) {
            addCriterion("upper(LEGAL_REPRESENTATIVE_CARD1) like", value.toUpperCase(), "legalRepresentativeCard1");
            return (Criteria) this;
        }

        public Criteria andLegalRepresentativeCard2LikeInsensitive(String value) {
            addCriterion("upper(LEGAL_REPRESENTATIVE_CARD2) like", value.toUpperCase(), "legalRepresentativeCard2");
            return (Criteria) this;
        }

        public Criteria andLegalIdcardEffitiveLikeInsensitive(String value) {
            addCriterion("upper(LEGAL_IDCARD_EFFITIVE) like", value.toUpperCase(), "legalIdcardEffitive");
            return (Criteria) this;
        }

        public Criteria andBussinessLicensePicLikeInsensitive(String value) {
            addCriterion("upper(BUSSINESS_LICENSE_PIC) like", value.toUpperCase(), "bussinessLicensePic");
            return (Criteria) this;
        }

        public Criteria andLicenseForOpeningCountLikeInsensitive(String value) {
            addCriterion("upper(LICENSE_FOR_OPENING_COUNT) like", value.toUpperCase(), "licenseForOpeningCount");
            return (Criteria) this;
        }

        public Criteria andQuantityReportIdLikeInsensitive(String value) {
            addCriterion("upper(QUANTITY_REPORT_ID) like", value.toUpperCase(), "quantityReportId");
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