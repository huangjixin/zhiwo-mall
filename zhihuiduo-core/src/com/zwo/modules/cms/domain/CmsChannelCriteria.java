package com.zwo.modules.cms.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CmsChannelCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CmsChannelCriteria() {
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

        public Criteria andEnNameIsNull() {
            addCriterion("EN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEnNameIsNotNull() {
            addCriterion("EN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEnNameEqualTo(String value) {
            addCriterion("EN_NAME =", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotEqualTo(String value) {
            addCriterion("EN_NAME <>", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThan(String value) {
            addCriterion("EN_NAME >", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameGreaterThanOrEqualTo(String value) {
            addCriterion("EN_NAME >=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThan(String value) {
            addCriterion("EN_NAME <", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLessThanOrEqualTo(String value) {
            addCriterion("EN_NAME <=", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameLike(String value) {
            addCriterion("EN_NAME like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotLike(String value) {
            addCriterion("EN_NAME not like", value, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameIn(List<String> values) {
            addCriterion("EN_NAME in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotIn(List<String> values) {
            addCriterion("EN_NAME not in", values, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameBetween(String value1, String value2) {
            addCriterion("EN_NAME between", value1, value2, "enName");
            return (Criteria) this;
        }

        public Criteria andEnNameNotBetween(String value1, String value2) {
            addCriterion("EN_NAME not between", value1, value2, "enName");
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

        public Criteria andIsTooicIsNull() {
            addCriterion("IS_TOOIC is null");
            return (Criteria) this;
        }

        public Criteria andIsTooicIsNotNull() {
            addCriterion("IS_TOOIC is not null");
            return (Criteria) this;
        }

        public Criteria andIsTooicEqualTo(Boolean value) {
            addCriterion("IS_TOOIC =", value, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicNotEqualTo(Boolean value) {
            addCriterion("IS_TOOIC <>", value, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicGreaterThan(Boolean value) {
            addCriterion("IS_TOOIC >", value, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_TOOIC >=", value, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicLessThan(Boolean value) {
            addCriterion("IS_TOOIC <", value, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_TOOIC <=", value, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicIn(List<Boolean> values) {
            addCriterion("IS_TOOIC in", values, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicNotIn(List<Boolean> values) {
            addCriterion("IS_TOOIC not in", values, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_TOOIC between", value1, value2, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsTooicNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_TOOIC not between", value1, value2, "isTooic");
            return (Criteria) this;
        }

        public Criteria andIsDisableIsNull() {
            addCriterion("IS_DISABLE is null");
            return (Criteria) this;
        }

        public Criteria andIsDisableIsNotNull() {
            addCriterion("IS_DISABLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDisableEqualTo(Boolean value) {
            addCriterion("IS_DISABLE =", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableNotEqualTo(Boolean value) {
            addCriterion("IS_DISABLE <>", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableGreaterThan(Boolean value) {
            addCriterion("IS_DISABLE >", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_DISABLE >=", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableLessThan(Boolean value) {
            addCriterion("IS_DISABLE <", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_DISABLE <=", value, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableIn(List<Boolean> values) {
            addCriterion("IS_DISABLE in", values, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableNotIn(List<Boolean> values) {
            addCriterion("IS_DISABLE not in", values, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_DISABLE between", value1, value2, "isDisable");
            return (Criteria) this;
        }

        public Criteria andIsDisableNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_DISABLE not between", value1, value2, "isDisable");
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

        public Criteria andPathIsNull() {
            addCriterion("PATH is null");
            return (Criteria) this;
        }

        public Criteria andPathIsNotNull() {
            addCriterion("PATH is not null");
            return (Criteria) this;
        }

        public Criteria andPathEqualTo(String value) {
            addCriterion("PATH =", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotEqualTo(String value) {
            addCriterion("PATH <>", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThan(String value) {
            addCriterion("PATH >", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathGreaterThanOrEqualTo(String value) {
            addCriterion("PATH >=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThan(String value) {
            addCriterion("PATH <", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLessThanOrEqualTo(String value) {
            addCriterion("PATH <=", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathLike(String value) {
            addCriterion("PATH like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotLike(String value) {
            addCriterion("PATH not like", value, "path");
            return (Criteria) this;
        }

        public Criteria andPathIn(List<String> values) {
            addCriterion("PATH in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotIn(List<String> values) {
            addCriterion("PATH not in", values, "path");
            return (Criteria) this;
        }

        public Criteria andPathBetween(String value1, String value2) {
            addCriterion("PATH between", value1, value2, "path");
            return (Criteria) this;
        }

        public Criteria andPathNotBetween(String value1, String value2) {
            addCriterion("PATH not between", value1, value2, "path");
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

        public Criteria andParentIdIsNull() {
            addCriterion("PARENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("PARENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("PARENT_ID =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("PARENT_ID <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("PARENT_ID >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARENT_ID >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("PARENT_ID <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("PARENT_ID <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLike(String value) {
            addCriterion("PARENT_ID like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotLike(String value) {
            addCriterion("PARENT_ID not like", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<String> values) {
            addCriterion("PARENT_ID in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("PARENT_ID not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("PARENT_ID between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("PARENT_ID not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentidsIsNull() {
            addCriterion("PARENTIDS is null");
            return (Criteria) this;
        }

        public Criteria andParentidsIsNotNull() {
            addCriterion("PARENTIDS is not null");
            return (Criteria) this;
        }

        public Criteria andParentidsEqualTo(String value) {
            addCriterion("PARENTIDS =", value, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsNotEqualTo(String value) {
            addCriterion("PARENTIDS <>", value, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsGreaterThan(String value) {
            addCriterion("PARENTIDS >", value, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsGreaterThanOrEqualTo(String value) {
            addCriterion("PARENTIDS >=", value, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsLessThan(String value) {
            addCriterion("PARENTIDS <", value, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsLessThanOrEqualTo(String value) {
            addCriterion("PARENTIDS <=", value, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsLike(String value) {
            addCriterion("PARENTIDS like", value, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsNotLike(String value) {
            addCriterion("PARENTIDS not like", value, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsIn(List<String> values) {
            addCriterion("PARENTIDS in", values, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsNotIn(List<String> values) {
            addCriterion("PARENTIDS not in", values, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsBetween(String value1, String value2) {
            addCriterion("PARENTIDS between", value1, value2, "parentids");
            return (Criteria) this;
        }

        public Criteria andParentidsNotBetween(String value1, String value2) {
            addCriterion("PARENTIDS not between", value1, value2, "parentids");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("CODE is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("CODE is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNull() {
            addCriterion("KEYWORDS is null");
            return (Criteria) this;
        }

        public Criteria andKeywordsIsNotNull() {
            addCriterion("KEYWORDS is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordsEqualTo(String value) {
            addCriterion("KEYWORDS =", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotEqualTo(String value) {
            addCriterion("KEYWORDS <>", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThan(String value) {
            addCriterion("KEYWORDS >", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsGreaterThanOrEqualTo(String value) {
            addCriterion("KEYWORDS >=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThan(String value) {
            addCriterion("KEYWORDS <", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLessThanOrEqualTo(String value) {
            addCriterion("KEYWORDS <=", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsLike(String value) {
            addCriterion("KEYWORDS like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotLike(String value) {
            addCriterion("KEYWORDS not like", value, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsIn(List<String> values) {
            addCriterion("KEYWORDS in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotIn(List<String> values) {
            addCriterion("KEYWORDS not in", values, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsBetween(String value1, String value2) {
            addCriterion("KEYWORDS between", value1, value2, "keywords");
            return (Criteria) this;
        }

        public Criteria andKeywordsNotBetween(String value1, String value2) {
            addCriterion("KEYWORDS not between", value1, value2, "keywords");
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

        public Criteria andThumbnailIsNull() {
            addCriterion("THUMBNAIL is null");
            return (Criteria) this;
        }

        public Criteria andThumbnailIsNotNull() {
            addCriterion("THUMBNAIL is not null");
            return (Criteria) this;
        }

        public Criteria andThumbnailEqualTo(String value) {
            addCriterion("THUMBNAIL =", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotEqualTo(String value) {
            addCriterion("THUMBNAIL <>", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailGreaterThan(String value) {
            addCriterion("THUMBNAIL >", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailGreaterThanOrEqualTo(String value) {
            addCriterion("THUMBNAIL >=", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLessThan(String value) {
            addCriterion("THUMBNAIL <", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLessThanOrEqualTo(String value) {
            addCriterion("THUMBNAIL <=", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailLike(String value) {
            addCriterion("THUMBNAIL like", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotLike(String value) {
            addCriterion("THUMBNAIL not like", value, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailIn(List<String> values) {
            addCriterion("THUMBNAIL in", values, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotIn(List<String> values) {
            addCriterion("THUMBNAIL not in", values, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailBetween(String value1, String value2) {
            addCriterion("THUMBNAIL between", value1, value2, "thumbnail");
            return (Criteria) this;
        }

        public Criteria andThumbnailNotBetween(String value1, String value2) {
            addCriterion("THUMBNAIL not between", value1, value2, "thumbnail");
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

        public Criteria andChannelTemplateIsNull() {
            addCriterion("CHANNEL_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateIsNotNull() {
            addCriterion("CHANNEL_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateEqualTo(String value) {
            addCriterion("CHANNEL_TEMPLATE =", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateNotEqualTo(String value) {
            addCriterion("CHANNEL_TEMPLATE <>", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateGreaterThan(String value) {
            addCriterion("CHANNEL_TEMPLATE >", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("CHANNEL_TEMPLATE >=", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateLessThan(String value) {
            addCriterion("CHANNEL_TEMPLATE <", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateLessThanOrEqualTo(String value) {
            addCriterion("CHANNEL_TEMPLATE <=", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateLike(String value) {
            addCriterion("CHANNEL_TEMPLATE like", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateNotLike(String value) {
            addCriterion("CHANNEL_TEMPLATE not like", value, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateIn(List<String> values) {
            addCriterion("CHANNEL_TEMPLATE in", values, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateNotIn(List<String> values) {
            addCriterion("CHANNEL_TEMPLATE not in", values, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateBetween(String value1, String value2) {
            addCriterion("CHANNEL_TEMPLATE between", value1, value2, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateNotBetween(String value1, String value2) {
            addCriterion("CHANNEL_TEMPLATE not between", value1, value2, "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateIsNull() {
            addCriterion("MCHANNEL_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateIsNotNull() {
            addCriterion("MCHANNEL_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateEqualTo(String value) {
            addCriterion("MCHANNEL_TEMPLATE =", value, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateNotEqualTo(String value) {
            addCriterion("MCHANNEL_TEMPLATE <>", value, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateGreaterThan(String value) {
            addCriterion("MCHANNEL_TEMPLATE >", value, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("MCHANNEL_TEMPLATE >=", value, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateLessThan(String value) {
            addCriterion("MCHANNEL_TEMPLATE <", value, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateLessThanOrEqualTo(String value) {
            addCriterion("MCHANNEL_TEMPLATE <=", value, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateLike(String value) {
            addCriterion("MCHANNEL_TEMPLATE like", value, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateNotLike(String value) {
            addCriterion("MCHANNEL_TEMPLATE not like", value, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateIn(List<String> values) {
            addCriterion("MCHANNEL_TEMPLATE in", values, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateNotIn(List<String> values) {
            addCriterion("MCHANNEL_TEMPLATE not in", values, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateBetween(String value1, String value2) {
            addCriterion("MCHANNEL_TEMPLATE between", value1, value2, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateNotBetween(String value1, String value2) {
            addCriterion("MCHANNEL_TEMPLATE not between", value1, value2, "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateIsNull() {
            addCriterion("JSP_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andJspTemplateIsNotNull() {
            addCriterion("JSP_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andJspTemplateEqualTo(String value) {
            addCriterion("JSP_TEMPLATE =", value, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateNotEqualTo(String value) {
            addCriterion("JSP_TEMPLATE <>", value, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateGreaterThan(String value) {
            addCriterion("JSP_TEMPLATE >", value, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("JSP_TEMPLATE >=", value, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateLessThan(String value) {
            addCriterion("JSP_TEMPLATE <", value, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateLessThanOrEqualTo(String value) {
            addCriterion("JSP_TEMPLATE <=", value, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateLike(String value) {
            addCriterion("JSP_TEMPLATE like", value, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateNotLike(String value) {
            addCriterion("JSP_TEMPLATE not like", value, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateIn(List<String> values) {
            addCriterion("JSP_TEMPLATE in", values, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateNotIn(List<String> values) {
            addCriterion("JSP_TEMPLATE not in", values, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateBetween(String value1, String value2) {
            addCriterion("JSP_TEMPLATE between", value1, value2, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateNotBetween(String value1, String value2) {
            addCriterion("JSP_TEMPLATE not between", value1, value2, "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateIsNull() {
            addCriterion("MOBILE_JSP_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateIsNotNull() {
            addCriterion("MOBILE_JSP_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateEqualTo(String value) {
            addCriterion("MOBILE_JSP_TEMPLATE =", value, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateNotEqualTo(String value) {
            addCriterion("MOBILE_JSP_TEMPLATE <>", value, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateGreaterThan(String value) {
            addCriterion("MOBILE_JSP_TEMPLATE >", value, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_JSP_TEMPLATE >=", value, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateLessThan(String value) {
            addCriterion("MOBILE_JSP_TEMPLATE <", value, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_JSP_TEMPLATE <=", value, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateLike(String value) {
            addCriterion("MOBILE_JSP_TEMPLATE like", value, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateNotLike(String value) {
            addCriterion("MOBILE_JSP_TEMPLATE not like", value, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateIn(List<String> values) {
            addCriterion("MOBILE_JSP_TEMPLATE in", values, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateNotIn(List<String> values) {
            addCriterion("MOBILE_JSP_TEMPLATE not in", values, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateBetween(String value1, String value2) {
            addCriterion("MOBILE_JSP_TEMPLATE between", value1, value2, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateNotBetween(String value1, String value2) {
            addCriterion("MOBILE_JSP_TEMPLATE not between", value1, value2, "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateIsNull() {
            addCriterion("FREEMARKER_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateIsNotNull() {
            addCriterion("FREEMARKER_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateEqualTo(String value) {
            addCriterion("FREEMARKER_TEMPLATE =", value, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateNotEqualTo(String value) {
            addCriterion("FREEMARKER_TEMPLATE <>", value, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateGreaterThan(String value) {
            addCriterion("FREEMARKER_TEMPLATE >", value, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("FREEMARKER_TEMPLATE >=", value, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateLessThan(String value) {
            addCriterion("FREEMARKER_TEMPLATE <", value, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateLessThanOrEqualTo(String value) {
            addCriterion("FREEMARKER_TEMPLATE <=", value, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateLike(String value) {
            addCriterion("FREEMARKER_TEMPLATE like", value, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateNotLike(String value) {
            addCriterion("FREEMARKER_TEMPLATE not like", value, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateIn(List<String> values) {
            addCriterion("FREEMARKER_TEMPLATE in", values, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateNotIn(List<String> values) {
            addCriterion("FREEMARKER_TEMPLATE not in", values, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateBetween(String value1, String value2) {
            addCriterion("FREEMARKER_TEMPLATE between", value1, value2, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateNotBetween(String value1, String value2) {
            addCriterion("FREEMARKER_TEMPLATE not between", value1, value2, "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateIsNull() {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateIsNotNull() {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateEqualTo(String value) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE =", value, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateNotEqualTo(String value) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE <>", value, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateGreaterThan(String value) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE >", value, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE >=", value, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateLessThan(String value) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE <", value, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateLessThanOrEqualTo(String value) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE <=", value, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateLike(String value) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE like", value, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateNotLike(String value) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE not like", value, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateIn(List<String> values) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE in", values, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateNotIn(List<String> values) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE not in", values, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateBetween(String value1, String value2) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE between", value1, value2, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateNotBetween(String value1, String value2) {
            addCriterion("MOBILE_FREEMARKER_TEMPLATE not between", value1, value2, "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateIsNull() {
            addCriterion("DOC_JSP_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateIsNotNull() {
            addCriterion("DOC_JSP_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateEqualTo(String value) {
            addCriterion("DOC_JSP_TEMPLATE =", value, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateNotEqualTo(String value) {
            addCriterion("DOC_JSP_TEMPLATE <>", value, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateGreaterThan(String value) {
            addCriterion("DOC_JSP_TEMPLATE >", value, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_JSP_TEMPLATE >=", value, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateLessThan(String value) {
            addCriterion("DOC_JSP_TEMPLATE <", value, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateLessThanOrEqualTo(String value) {
            addCriterion("DOC_JSP_TEMPLATE <=", value, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateLike(String value) {
            addCriterion("DOC_JSP_TEMPLATE like", value, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateNotLike(String value) {
            addCriterion("DOC_JSP_TEMPLATE not like", value, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateIn(List<String> values) {
            addCriterion("DOC_JSP_TEMPLATE in", values, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateNotIn(List<String> values) {
            addCriterion("DOC_JSP_TEMPLATE not in", values, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateBetween(String value1, String value2) {
            addCriterion("DOC_JSP_TEMPLATE between", value1, value2, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateNotBetween(String value1, String value2) {
            addCriterion("DOC_JSP_TEMPLATE not between", value1, value2, "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateIsNull() {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateIsNotNull() {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateEqualTo(String value) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE =", value, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateNotEqualTo(String value) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE <>", value, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateGreaterThan(String value) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE >", value, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE >=", value, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateLessThan(String value) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE <", value, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateLessThanOrEqualTo(String value) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE <=", value, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateLike(String value) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE like", value, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateNotLike(String value) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE not like", value, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateIn(List<String> values) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE in", values, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateNotIn(List<String> values) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE not in", values, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateBetween(String value1, String value2) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE between", value1, value2, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateNotBetween(String value1, String value2) {
            addCriterion("DOC_MOBILE__JSP_TEMPLATE not between", value1, value2, "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateIsNull() {
            addCriterion("DOC_FREEMARKER_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateIsNotNull() {
            addCriterion("DOC_FREEMARKER_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateEqualTo(String value) {
            addCriterion("DOC_FREEMARKER_TEMPLATE =", value, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateNotEqualTo(String value) {
            addCriterion("DOC_FREEMARKER_TEMPLATE <>", value, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateGreaterThan(String value) {
            addCriterion("DOC_FREEMARKER_TEMPLATE >", value, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_FREEMARKER_TEMPLATE >=", value, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateLessThan(String value) {
            addCriterion("DOC_FREEMARKER_TEMPLATE <", value, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateLessThanOrEqualTo(String value) {
            addCriterion("DOC_FREEMARKER_TEMPLATE <=", value, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateLike(String value) {
            addCriterion("DOC_FREEMARKER_TEMPLATE like", value, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateNotLike(String value) {
            addCriterion("DOC_FREEMARKER_TEMPLATE not like", value, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateIn(List<String> values) {
            addCriterion("DOC_FREEMARKER_TEMPLATE in", values, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateNotIn(List<String> values) {
            addCriterion("DOC_FREEMARKER_TEMPLATE not in", values, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateBetween(String value1, String value2) {
            addCriterion("DOC_FREEMARKER_TEMPLATE between", value1, value2, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateNotBetween(String value1, String value2) {
            addCriterion("DOC_FREEMARKER_TEMPLATE not between", value1, value2, "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateIsNull() {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE is null");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateIsNotNull() {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE is not null");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateEqualTo(String value) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE =", value, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateNotEqualTo(String value) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE <>", value, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateGreaterThan(String value) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE >", value, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateGreaterThanOrEqualTo(String value) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE >=", value, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateLessThan(String value) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE <", value, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateLessThanOrEqualTo(String value) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE <=", value, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateLike(String value) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE like", value, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateNotLike(String value) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE not like", value, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateIn(List<String> values) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE in", values, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateNotIn(List<String> values) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE not in", values, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateBetween(String value1, String value2) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE between", value1, value2, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateNotBetween(String value1, String value2) {
            addCriterion("DOC_MOBILE_FREEMARKER_TEMPLATE not between", value1, value2, "docMobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andEnNameLikeInsensitive(String value) {
            addCriterion("upper(EN_NAME) like", value.toUpperCase(), "enName");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(NAME) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andOrgIdLikeInsensitive(String value) {
            addCriterion("upper(ORG_ID) like", value.toUpperCase(), "orgId");
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

        public Criteria andPathLikeInsensitive(String value) {
            addCriterion("upper(PATH) like", value.toUpperCase(), "path");
            return (Criteria) this;
        }

        public Criteria andIconLikeInsensitive(String value) {
            addCriterion("upper(ICON) like", value.toUpperCase(), "icon");
            return (Criteria) this;
        }

        public Criteria andParentIdLikeInsensitive(String value) {
            addCriterion("upper(PARENT_ID) like", value.toUpperCase(), "parentId");
            return (Criteria) this;
        }

        public Criteria andParentidsLikeInsensitive(String value) {
            addCriterion("upper(PARENTIDS) like", value.toUpperCase(), "parentids");
            return (Criteria) this;
        }

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(CODE) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andKeywordsLikeInsensitive(String value) {
            addCriterion("upper(KEYWORDS) like", value.toUpperCase(), "keywords");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(DESCRIPTION) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andThumbnailLikeInsensitive(String value) {
            addCriterion("upper(THUMBNAIL) like", value.toUpperCase(), "thumbnail");
            return (Criteria) this;
        }

        public Criteria andChannelTemplateLikeInsensitive(String value) {
            addCriterion("upper(CHANNEL_TEMPLATE) like", value.toUpperCase(), "channelTemplate");
            return (Criteria) this;
        }

        public Criteria andMchannelTemplateLikeInsensitive(String value) {
            addCriterion("upper(MCHANNEL_TEMPLATE) like", value.toUpperCase(), "mchannelTemplate");
            return (Criteria) this;
        }

        public Criteria andJspTemplateLikeInsensitive(String value) {
            addCriterion("upper(JSP_TEMPLATE) like", value.toUpperCase(), "jspTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileJspTemplateLikeInsensitive(String value) {
            addCriterion("upper(MOBILE_JSP_TEMPLATE) like", value.toUpperCase(), "mobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andFreemarkerTemplateLikeInsensitive(String value) {
            addCriterion("upper(FREEMARKER_TEMPLATE) like", value.toUpperCase(), "freemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andMobileFreemarkerTemplateLikeInsensitive(String value) {
            addCriterion("upper(MOBILE_FREEMARKER_TEMPLATE) like", value.toUpperCase(), "mobileFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocJspTemplateLikeInsensitive(String value) {
            addCriterion("upper(DOC_JSP_TEMPLATE) like", value.toUpperCase(), "docJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileJspTemplateLikeInsensitive(String value) {
            addCriterion("upper(DOC_MOBILE__JSP_TEMPLATE) like", value.toUpperCase(), "docMobileJspTemplate");
            return (Criteria) this;
        }

        public Criteria andDocFreemarkerTemplateLikeInsensitive(String value) {
            addCriterion("upper(DOC_FREEMARKER_TEMPLATE) like", value.toUpperCase(), "docFreemarkerTemplate");
            return (Criteria) this;
        }

        public Criteria andDocMobileFreemarkerTemplateLikeInsensitive(String value) {
            addCriterion("upper(DOC_MOBILE_FREEMARKER_TEMPLATE) like", value.toUpperCase(), "docMobileFreemarkerTemplate");
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