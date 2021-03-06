package com.zwo.modules.mall.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrProductCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PrProductCriteria() {
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

        public Criteria andStartTimeIsNull() {
            addCriterion("START_TIME is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("START_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("START_TIME =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("START_TIME <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("START_TIME >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("START_TIME >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("START_TIME <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("START_TIME <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("START_TIME in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("START_TIME not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("START_TIME between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("START_TIME not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("END_TIME is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("END_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("END_TIME =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("END_TIME <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("END_TIME >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("END_TIME >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("END_TIME <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("END_TIME <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("END_TIME in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("END_TIME not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("END_TIME between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("END_TIME not between", value1, value2, "endTime");
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

        public Criteria andIsDefaultIsNull() {
            addCriterion("IS_DEFAULT is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("IS_DEFAULT is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(Boolean value) {
            addCriterion("IS_DEFAULT =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(Boolean value) {
            addCriterion("IS_DEFAULT <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(Boolean value) {
            addCriterion("IS_DEFAULT >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(Boolean value) {
            addCriterion("IS_DEFAULT >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(Boolean value) {
            addCriterion("IS_DEFAULT <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(Boolean value) {
            addCriterion("IS_DEFAULT <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<Boolean> values) {
            addCriterion("IS_DEFAULT in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<Boolean> values) {
            addCriterion("IS_DEFAULT not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_DEFAULT between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(Boolean value1, Boolean value2) {
            addCriterion("IS_DEFAULT not between", value1, value2, "isDefault");
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

        public Criteria andCategoryIdIsNull() {
            addCriterion("CATEGORY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("CATEGORY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(String value) {
            addCriterion("CATEGORY_ID =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(String value) {
            addCriterion("CATEGORY_ID <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(String value) {
            addCriterion("CATEGORY_ID >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ID >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(String value) {
            addCriterion("CATEGORY_ID <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(String value) {
            addCriterion("CATEGORY_ID <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLike(String value) {
            addCriterion("CATEGORY_ID like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotLike(String value) {
            addCriterion("CATEGORY_ID not like", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<String> values) {
            addCriterion("CATEGORY_ID in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<String> values) {
            addCriterion("CATEGORY_ID not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(String value1, String value2) {
            addCriterion("CATEGORY_ID between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(String value1, String value2) {
            addCriterion("CATEGORY_ID not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andEnContentIsNull() {
            addCriterion("EN_CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andEnContentIsNotNull() {
            addCriterion("EN_CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andEnContentEqualTo(String value) {
            addCriterion("EN_CONTENT =", value, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentNotEqualTo(String value) {
            addCriterion("EN_CONTENT <>", value, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentGreaterThan(String value) {
            addCriterion("EN_CONTENT >", value, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentGreaterThanOrEqualTo(String value) {
            addCriterion("EN_CONTENT >=", value, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentLessThan(String value) {
            addCriterion("EN_CONTENT <", value, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentLessThanOrEqualTo(String value) {
            addCriterion("EN_CONTENT <=", value, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentLike(String value) {
            addCriterion("EN_CONTENT like", value, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentNotLike(String value) {
            addCriterion("EN_CONTENT not like", value, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentIn(List<String> values) {
            addCriterion("EN_CONTENT in", values, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentNotIn(List<String> values) {
            addCriterion("EN_CONTENT not in", values, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentBetween(String value1, String value2) {
            addCriterion("EN_CONTENT between", value1, value2, "enContent");
            return (Criteria) this;
        }

        public Criteria andEnContentNotBetween(String value1, String value2) {
            addCriterion("EN_CONTENT not between", value1, value2, "enContent");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionIsNull() {
            addCriterion("ALLOW_DISTRIBUTION is null");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionIsNotNull() {
            addCriterion("ALLOW_DISTRIBUTION is not null");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionEqualTo(Boolean value) {
            addCriterion("ALLOW_DISTRIBUTION =", value, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionNotEqualTo(Boolean value) {
            addCriterion("ALLOW_DISTRIBUTION <>", value, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionGreaterThan(Boolean value) {
            addCriterion("ALLOW_DISTRIBUTION >", value, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionGreaterThanOrEqualTo(Boolean value) {
            addCriterion("ALLOW_DISTRIBUTION >=", value, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionLessThan(Boolean value) {
            addCriterion("ALLOW_DISTRIBUTION <", value, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionLessThanOrEqualTo(Boolean value) {
            addCriterion("ALLOW_DISTRIBUTION <=", value, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionIn(List<Boolean> values) {
            addCriterion("ALLOW_DISTRIBUTION in", values, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionNotIn(List<Boolean> values) {
            addCriterion("ALLOW_DISTRIBUTION not in", values, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionBetween(Boolean value1, Boolean value2) {
            addCriterion("ALLOW_DISTRIBUTION between", value1, value2, "allowDistribution");
            return (Criteria) this;
        }

        public Criteria andAllowDistributionNotBetween(Boolean value1, Boolean value2) {
            addCriterion("ALLOW_DISTRIBUTION not between", value1, value2, "allowDistribution");
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

        public Criteria andShopIdIsNull() {
            addCriterion("SHOP_ID is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("SHOP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(String value) {
            addCriterion("SHOP_ID =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(String value) {
            addCriterion("SHOP_ID <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(String value) {
            addCriterion("SHOP_ID >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_ID >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(String value) {
            addCriterion("SHOP_ID <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(String value) {
            addCriterion("SHOP_ID <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLike(String value) {
            addCriterion("SHOP_ID like", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotLike(String value) {
            addCriterion("SHOP_ID not like", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<String> values) {
            addCriterion("SHOP_ID in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<String> values) {
            addCriterion("SHOP_ID not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(String value1, String value2) {
            addCriterion("SHOP_ID between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(String value1, String value2) {
            addCriterion("SHOP_ID not between", value1, value2, "shopId");
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

        public Criteria andPurchasingCostIsNull() {
            addCriterion("PURCHASING_COST is null");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostIsNotNull() {
            addCriterion("PURCHASING_COST is not null");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostEqualTo(Double value) {
            addCriterion("PURCHASING_COST =", value, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostNotEqualTo(Double value) {
            addCriterion("PURCHASING_COST <>", value, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostGreaterThan(Double value) {
            addCriterion("PURCHASING_COST >", value, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostGreaterThanOrEqualTo(Double value) {
            addCriterion("PURCHASING_COST >=", value, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostLessThan(Double value) {
            addCriterion("PURCHASING_COST <", value, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostLessThanOrEqualTo(Double value) {
            addCriterion("PURCHASING_COST <=", value, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostIn(List<Double> values) {
            addCriterion("PURCHASING_COST in", values, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostNotIn(List<Double> values) {
            addCriterion("PURCHASING_COST not in", values, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostBetween(Double value1, Double value2) {
            addCriterion("PURCHASING_COST between", value1, value2, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andPurchasingCostNotBetween(Double value1, Double value2) {
            addCriterion("PURCHASING_COST not between", value1, value2, "purchasingCost");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionIsNull() {
            addCriterion("DIST_INTRUEDUTION is null");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionIsNotNull() {
            addCriterion("DIST_INTRUEDUTION is not null");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionEqualTo(String value) {
            addCriterion("DIST_INTRUEDUTION =", value, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionNotEqualTo(String value) {
            addCriterion("DIST_INTRUEDUTION <>", value, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionGreaterThan(String value) {
            addCriterion("DIST_INTRUEDUTION >", value, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionGreaterThanOrEqualTo(String value) {
            addCriterion("DIST_INTRUEDUTION >=", value, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionLessThan(String value) {
            addCriterion("DIST_INTRUEDUTION <", value, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionLessThanOrEqualTo(String value) {
            addCriterion("DIST_INTRUEDUTION <=", value, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionLike(String value) {
            addCriterion("DIST_INTRUEDUTION like", value, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionNotLike(String value) {
            addCriterion("DIST_INTRUEDUTION not like", value, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionIn(List<String> values) {
            addCriterion("DIST_INTRUEDUTION in", values, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionNotIn(List<String> values) {
            addCriterion("DIST_INTRUEDUTION not in", values, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionBetween(String value1, String value2) {
            addCriterion("DIST_INTRUEDUTION between", value1, value2, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionNotBetween(String value1, String value2) {
            addCriterion("DIST_INTRUEDUTION not between", value1, value2, "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("SUPPLIER_ID is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("SUPPLIER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(String value) {
            addCriterion("SUPPLIER_ID =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(String value) {
            addCriterion("SUPPLIER_ID <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(String value) {
            addCriterion("SUPPLIER_ID >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(String value) {
            addCriterion("SUPPLIER_ID >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(String value) {
            addCriterion("SUPPLIER_ID <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(String value) {
            addCriterion("SUPPLIER_ID <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLike(String value) {
            addCriterion("SUPPLIER_ID like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotLike(String value) {
            addCriterion("SUPPLIER_ID not like", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<String> values) {
            addCriterion("SUPPLIER_ID in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<String> values) {
            addCriterion("SUPPLIER_ID not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(String value1, String value2) {
            addCriterion("SUPPLIER_ID between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(String value1, String value2) {
            addCriterion("SUPPLIER_ID not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceIsNull() {
            addCriterion("GOURP_SALE_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceIsNotNull() {
            addCriterion("GOURP_SALE_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceEqualTo(Double value) {
            addCriterion("GOURP_SALE_PRICE =", value, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceNotEqualTo(Double value) {
            addCriterion("GOURP_SALE_PRICE <>", value, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceGreaterThan(Double value) {
            addCriterion("GOURP_SALE_PRICE >", value, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceGreaterThanOrEqualTo(Double value) {
            addCriterion("GOURP_SALE_PRICE >=", value, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceLessThan(Double value) {
            addCriterion("GOURP_SALE_PRICE <", value, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceLessThanOrEqualTo(Double value) {
            addCriterion("GOURP_SALE_PRICE <=", value, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceIn(List<Double> values) {
            addCriterion("GOURP_SALE_PRICE in", values, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceNotIn(List<Double> values) {
            addCriterion("GOURP_SALE_PRICE not in", values, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceBetween(Double value1, Double value2) {
            addCriterion("GOURP_SALE_PRICE between", value1, value2, "gourpSalePrice");
            return (Criteria) this;
        }

        public Criteria andGourpSalePriceNotBetween(Double value1, Double value2) {
            addCriterion("GOURP_SALE_PRICE not between", value1, value2, "gourpSalePrice");
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

        public Criteria andNumberCountIsNull() {
            addCriterion("NUMBER_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andNumberCountIsNotNull() {
            addCriterion("NUMBER_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andNumberCountEqualTo(Integer value) {
            addCriterion("NUMBER_COUNT =", value, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountNotEqualTo(Integer value) {
            addCriterion("NUMBER_COUNT <>", value, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountGreaterThan(Integer value) {
            addCriterion("NUMBER_COUNT >", value, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("NUMBER_COUNT >=", value, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountLessThan(Integer value) {
            addCriterion("NUMBER_COUNT <", value, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountLessThanOrEqualTo(Integer value) {
            addCriterion("NUMBER_COUNT <=", value, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountIn(List<Integer> values) {
            addCriterion("NUMBER_COUNT in", values, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountNotIn(List<Integer> values) {
            addCriterion("NUMBER_COUNT not in", values, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountBetween(Integer value1, Integer value2) {
            addCriterion("NUMBER_COUNT between", value1, value2, "numberCount");
            return (Criteria) this;
        }

        public Criteria andNumberCountNotBetween(Integer value1, Integer value2) {
            addCriterion("NUMBER_COUNT not between", value1, value2, "numberCount");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionIsNull() {
            addCriterion("AUDIT_DESCRIPTION is null");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionIsNotNull() {
            addCriterion("AUDIT_DESCRIPTION is not null");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionEqualTo(String value) {
            addCriterion("AUDIT_DESCRIPTION =", value, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionNotEqualTo(String value) {
            addCriterion("AUDIT_DESCRIPTION <>", value, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionGreaterThan(String value) {
            addCriterion("AUDIT_DESCRIPTION >", value, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_DESCRIPTION >=", value, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionLessThan(String value) {
            addCriterion("AUDIT_DESCRIPTION <", value, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_DESCRIPTION <=", value, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionLike(String value) {
            addCriterion("AUDIT_DESCRIPTION like", value, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionNotLike(String value) {
            addCriterion("AUDIT_DESCRIPTION not like", value, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionIn(List<String> values) {
            addCriterion("AUDIT_DESCRIPTION in", values, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionNotIn(List<String> values) {
            addCriterion("AUDIT_DESCRIPTION not in", values, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionBetween(String value1, String value2) {
            addCriterion("AUDIT_DESCRIPTION between", value1, value2, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionNotBetween(String value1, String value2) {
            addCriterion("AUDIT_DESCRIPTION not between", value1, value2, "auditDescription");
            return (Criteria) this;
        }

        public Criteria andStorageIsNull() {
            addCriterion("STORAGE is null");
            return (Criteria) this;
        }

        public Criteria andStorageIsNotNull() {
            addCriterion("STORAGE is not null");
            return (Criteria) this;
        }

        public Criteria andStorageEqualTo(Integer value) {
            addCriterion("STORAGE =", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotEqualTo(Integer value) {
            addCriterion("STORAGE <>", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageGreaterThan(Integer value) {
            addCriterion("STORAGE >", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageGreaterThanOrEqualTo(Integer value) {
            addCriterion("STORAGE >=", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLessThan(Integer value) {
            addCriterion("STORAGE <", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageLessThanOrEqualTo(Integer value) {
            addCriterion("STORAGE <=", value, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageIn(List<Integer> values) {
            addCriterion("STORAGE in", values, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotIn(List<Integer> values) {
            addCriterion("STORAGE not in", values, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageBetween(Integer value1, Integer value2) {
            addCriterion("STORAGE between", value1, value2, "storage");
            return (Criteria) this;
        }

        public Criteria andStorageNotBetween(Integer value1, Integer value2) {
            addCriterion("STORAGE not between", value1, value2, "storage");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNull() {
            addCriterion("MARKET_PRICE is null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIsNotNull() {
            addCriterion("MARKET_PRICE is not null");
            return (Criteria) this;
        }

        public Criteria andMarketPriceEqualTo(Double value) {
            addCriterion("MARKET_PRICE =", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotEqualTo(Double value) {
            addCriterion("MARKET_PRICE <>", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThan(Double value) {
            addCriterion("MARKET_PRICE >", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceGreaterThanOrEqualTo(Double value) {
            addCriterion("MARKET_PRICE >=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThan(Double value) {
            addCriterion("MARKET_PRICE <", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceLessThanOrEqualTo(Double value) {
            addCriterion("MARKET_PRICE <=", value, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceIn(List<Double> values) {
            addCriterion("MARKET_PRICE in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotIn(List<Double> values) {
            addCriterion("MARKET_PRICE not in", values, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceBetween(Double value1, Double value2) {
            addCriterion("MARKET_PRICE between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andMarketPriceNotBetween(Double value1, Double value2) {
            addCriterion("MARKET_PRICE not between", value1, value2, "marketPrice");
            return (Criteria) this;
        }

        public Criteria andTransportFeeIsNull() {
            addCriterion("TRANSPORT_FEE is null");
            return (Criteria) this;
        }

        public Criteria andTransportFeeIsNotNull() {
            addCriterion("TRANSPORT_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andTransportFeeEqualTo(Double value) {
            addCriterion("TRANSPORT_FEE =", value, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeNotEqualTo(Double value) {
            addCriterion("TRANSPORT_FEE <>", value, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeGreaterThan(Double value) {
            addCriterion("TRANSPORT_FEE >", value, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("TRANSPORT_FEE >=", value, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeLessThan(Double value) {
            addCriterion("TRANSPORT_FEE <", value, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeLessThanOrEqualTo(Double value) {
            addCriterion("TRANSPORT_FEE <=", value, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeIn(List<Double> values) {
            addCriterion("TRANSPORT_FEE in", values, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeNotIn(List<Double> values) {
            addCriterion("TRANSPORT_FEE not in", values, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeBetween(Double value1, Double value2) {
            addCriterion("TRANSPORT_FEE between", value1, value2, "transportFee");
            return (Criteria) this;
        }

        public Criteria andTransportFeeNotBetween(Double value1, Double value2) {
            addCriterion("TRANSPORT_FEE not between", value1, value2, "transportFee");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNull() {
            addCriterion("SHOP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andShopNameIsNotNull() {
            addCriterion("SHOP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andShopNameEqualTo(String value) {
            addCriterion("SHOP_NAME =", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotEqualTo(String value) {
            addCriterion("SHOP_NAME <>", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThan(String value) {
            addCriterion("SHOP_NAME >", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME >=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThan(String value) {
            addCriterion("SHOP_NAME <", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLessThanOrEqualTo(String value) {
            addCriterion("SHOP_NAME <=", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameLike(String value) {
            addCriterion("SHOP_NAME like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotLike(String value) {
            addCriterion("SHOP_NAME not like", value, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameIn(List<String> values) {
            addCriterion("SHOP_NAME in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotIn(List<String> values) {
            addCriterion("SHOP_NAME not in", values, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameBetween(String value1, String value2) {
            addCriterion("SHOP_NAME between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopNameNotBetween(String value1, String value2) {
            addCriterion("SHOP_NAME not between", value1, value2, "shopName");
            return (Criteria) this;
        }

        public Criteria andShopIconIsNull() {
            addCriterion("SHOP_ICON is null");
            return (Criteria) this;
        }

        public Criteria andShopIconIsNotNull() {
            addCriterion("SHOP_ICON is not null");
            return (Criteria) this;
        }

        public Criteria andShopIconEqualTo(String value) {
            addCriterion("SHOP_ICON =", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconNotEqualTo(String value) {
            addCriterion("SHOP_ICON <>", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconGreaterThan(String value) {
            addCriterion("SHOP_ICON >", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconGreaterThanOrEqualTo(String value) {
            addCriterion("SHOP_ICON >=", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconLessThan(String value) {
            addCriterion("SHOP_ICON <", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconLessThanOrEqualTo(String value) {
            addCriterion("SHOP_ICON <=", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconLike(String value) {
            addCriterion("SHOP_ICON like", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconNotLike(String value) {
            addCriterion("SHOP_ICON not like", value, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconIn(List<String> values) {
            addCriterion("SHOP_ICON in", values, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconNotIn(List<String> values) {
            addCriterion("SHOP_ICON not in", values, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconBetween(String value1, String value2) {
            addCriterion("SHOP_ICON between", value1, value2, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andShopIconNotBetween(String value1, String value2) {
            addCriterion("SHOP_ICON not between", value1, value2, "shopIcon");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayIsNull() {
            addCriterion("IS_SENT_UNPAY is null");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayIsNotNull() {
            addCriterion("IS_SENT_UNPAY is not null");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayEqualTo(Integer value) {
            addCriterion("IS_SENT_UNPAY =", value, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayNotEqualTo(Integer value) {
            addCriterion("IS_SENT_UNPAY <>", value, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayGreaterThan(Integer value) {
            addCriterion("IS_SENT_UNPAY >", value, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_SENT_UNPAY >=", value, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayLessThan(Integer value) {
            addCriterion("IS_SENT_UNPAY <", value, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayLessThanOrEqualTo(Integer value) {
            addCriterion("IS_SENT_UNPAY <=", value, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayIn(List<Integer> values) {
            addCriterion("IS_SENT_UNPAY in", values, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayNotIn(List<Integer> values) {
            addCriterion("IS_SENT_UNPAY not in", values, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayBetween(Integer value1, Integer value2) {
            addCriterion("IS_SENT_UNPAY between", value1, value2, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andIsSentUnpayNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_SENT_UNPAY not between", value1, value2, "isSentUnpay");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNull() {
            addCriterion("CHECK_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("CHECK_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Integer value) {
            addCriterion("CHECK_STATUS =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Integer value) {
            addCriterion("CHECK_STATUS <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Integer value) {
            addCriterion("CHECK_STATUS >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("CHECK_STATUS >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Integer value) {
            addCriterion("CHECK_STATUS <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Integer value) {
            addCriterion("CHECK_STATUS <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Integer> values) {
            addCriterion("CHECK_STATUS in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Integer> values) {
            addCriterion("CHECK_STATUS not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Integer value1, Integer value2) {
            addCriterion("CHECK_STATUS between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("CHECK_STATUS not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andCreatorLikeInsensitive(String value) {
            addCriterion("upper(CREATOR) like", value.toUpperCase(), "creator");
            return (Criteria) this;
        }

        public Criteria andOrgIdLikeInsensitive(String value) {
            addCriterion("upper(ORG_ID) like", value.toUpperCase(), "orgId");
            return (Criteria) this;
        }

        public Criteria andUpdaterLikeInsensitive(String value) {
            addCriterion("upper(UPDATER) like", value.toUpperCase(), "updater");
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

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(DESCRIPTION) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andIconLikeInsensitive(String value) {
            addCriterion("upper(ICON) like", value.toUpperCase(), "icon");
            return (Criteria) this;
        }

        public Criteria andKeywordsLikeInsensitive(String value) {
            addCriterion("upper(KEYWORDS) like", value.toUpperCase(), "keywords");
            return (Criteria) this;
        }

        public Criteria andThumbnailLikeInsensitive(String value) {
            addCriterion("upper(THUMBNAIL) like", value.toUpperCase(), "thumbnail");
            return (Criteria) this;
        }

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(CODE) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLikeInsensitive(String value) {
            addCriterion("upper(CATEGORY_ID) like", value.toUpperCase(), "categoryId");
            return (Criteria) this;
        }

        public Criteria andContentLikeInsensitive(String value) {
            addCriterion("upper(CONTENT) like", value.toUpperCase(), "content");
            return (Criteria) this;
        }

        public Criteria andEnContentLikeInsensitive(String value) {
            addCriterion("upper(EN_CONTENT) like", value.toUpperCase(), "enContent");
            return (Criteria) this;
        }

        public Criteria andShopIdLikeInsensitive(String value) {
            addCriterion("upper(SHOP_ID) like", value.toUpperCase(), "shopId");
            return (Criteria) this;
        }

        public Criteria andUserIdLikeInsensitive(String value) {
            addCriterion("upper(USER_ID) like", value.toUpperCase(), "userId");
            return (Criteria) this;
        }

        public Criteria andDistIntruedutionLikeInsensitive(String value) {
            addCriterion("upper(DIST_INTRUEDUTION) like", value.toUpperCase(), "distIntruedution");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLikeInsensitive(String value) {
            addCriterion("upper(SUPPLIER_ID) like", value.toUpperCase(), "supplierId");
            return (Criteria) this;
        }

        public Criteria andAuditDescriptionLikeInsensitive(String value) {
            addCriterion("upper(AUDIT_DESCRIPTION) like", value.toUpperCase(), "auditDescription");
            return (Criteria) this;
        }

        public Criteria andStatusLikeInsensitive(String value) {
            addCriterion("upper(STATUS) like", value.toUpperCase(), "status");
            return (Criteria) this;
        }

        public Criteria andShopNameLikeInsensitive(String value) {
            addCriterion("upper(SHOP_NAME) like", value.toUpperCase(), "shopName");
            return (Criteria) this;
        }

        public Criteria andShopIconLikeInsensitive(String value) {
            addCriterion("upper(SHOP_ICON) like", value.toUpperCase(), "shopIcon");
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