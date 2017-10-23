package com.zwo.modules.webconfig.domain;

import java.util.ArrayList;
import java.util.List;

public class WebShopConfigCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WebShopConfigCriteria() {
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

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsIsNull() {
            addCriterion("KEY_WORLDS is null");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsIsNotNull() {
            addCriterion("KEY_WORLDS is not null");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsEqualTo(String value) {
            addCriterion("KEY_WORLDS =", value, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsNotEqualTo(String value) {
            addCriterion("KEY_WORLDS <>", value, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsGreaterThan(String value) {
            addCriterion("KEY_WORLDS >", value, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsGreaterThanOrEqualTo(String value) {
            addCriterion("KEY_WORLDS >=", value, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsLessThan(String value) {
            addCriterion("KEY_WORLDS <", value, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsLessThanOrEqualTo(String value) {
            addCriterion("KEY_WORLDS <=", value, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsLike(String value) {
            addCriterion("KEY_WORLDS like", value, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsNotLike(String value) {
            addCriterion("KEY_WORLDS not like", value, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsIn(List<String> values) {
            addCriterion("KEY_WORLDS in", values, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsNotIn(List<String> values) {
            addCriterion("KEY_WORLDS not in", values, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsBetween(String value1, String value2) {
            addCriterion("KEY_WORLDS between", value1, value2, "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsNotBetween(String value1, String value2) {
            addCriterion("KEY_WORLDS not between", value1, value2, "keyWorlds");
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

        public Criteria andTelephoneIsNull() {
            addCriterion("TELEPHONE is null");
            return (Criteria) this;
        }

        public Criteria andTelephoneIsNotNull() {
            addCriterion("TELEPHONE is not null");
            return (Criteria) this;
        }

        public Criteria andTelephoneEqualTo(String value) {
            addCriterion("TELEPHONE =", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotEqualTo(String value) {
            addCriterion("TELEPHONE <>", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThan(String value) {
            addCriterion("TELEPHONE >", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneGreaterThanOrEqualTo(String value) {
            addCriterion("TELEPHONE >=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThan(String value) {
            addCriterion("TELEPHONE <", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLessThanOrEqualTo(String value) {
            addCriterion("TELEPHONE <=", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneLike(String value) {
            addCriterion("TELEPHONE like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotLike(String value) {
            addCriterion("TELEPHONE not like", value, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneIn(List<String> values) {
            addCriterion("TELEPHONE in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotIn(List<String> values) {
            addCriterion("TELEPHONE not in", values, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneBetween(String value1, String value2) {
            addCriterion("TELEPHONE between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andTelephoneNotBetween(String value1, String value2) {
            addCriterion("TELEPHONE not between", value1, value2, "telephone");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeIsNull() {
            addCriterion("STATISTICS_CODE is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeIsNotNull() {
            addCriterion("STATISTICS_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeEqualTo(String value) {
            addCriterion("STATISTICS_CODE =", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeNotEqualTo(String value) {
            addCriterion("STATISTICS_CODE <>", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeGreaterThan(String value) {
            addCriterion("STATISTICS_CODE >", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("STATISTICS_CODE >=", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeLessThan(String value) {
            addCriterion("STATISTICS_CODE <", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeLessThanOrEqualTo(String value) {
            addCriterion("STATISTICS_CODE <=", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeLike(String value) {
            addCriterion("STATISTICS_CODE like", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeNotLike(String value) {
            addCriterion("STATISTICS_CODE not like", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeIn(List<String> values) {
            addCriterion("STATISTICS_CODE in", values, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeNotIn(List<String> values) {
            addCriterion("STATISTICS_CODE not in", values, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeBetween(String value1, String value2) {
            addCriterion("STATISTICS_CODE between", value1, value2, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeNotBetween(String value1, String value2) {
            addCriterion("STATISTICS_CODE not between", value1, value2, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtIsNull() {
            addCriterion("NUMBER_COMMEMT is null");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtIsNotNull() {
            addCriterion("NUMBER_COMMEMT is not null");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtEqualTo(Integer value) {
            addCriterion("NUMBER_COMMEMT =", value, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtNotEqualTo(Integer value) {
            addCriterion("NUMBER_COMMEMT <>", value, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtGreaterThan(Integer value) {
            addCriterion("NUMBER_COMMEMT >", value, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtGreaterThanOrEqualTo(Integer value) {
            addCriterion("NUMBER_COMMEMT >=", value, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtLessThan(Integer value) {
            addCriterion("NUMBER_COMMEMT <", value, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtLessThanOrEqualTo(Integer value) {
            addCriterion("NUMBER_COMMEMT <=", value, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtIn(List<Integer> values) {
            addCriterion("NUMBER_COMMEMT in", values, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtNotIn(List<Integer> values) {
            addCriterion("NUMBER_COMMEMT not in", values, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtBetween(Integer value1, Integer value2) {
            addCriterion("NUMBER_COMMEMT between", value1, value2, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andNumberCommemtNotBetween(Integer value1, Integer value2) {
            addCriterion("NUMBER_COMMEMT not between", value1, value2, "numberCommemt");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeIsNull() {
            addCriterion("INDEX_PAGESIZE is null");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeIsNotNull() {
            addCriterion("INDEX_PAGESIZE is not null");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeEqualTo(Integer value) {
            addCriterion("INDEX_PAGESIZE =", value, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeNotEqualTo(Integer value) {
            addCriterion("INDEX_PAGESIZE <>", value, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeGreaterThan(Integer value) {
            addCriterion("INDEX_PAGESIZE >", value, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("INDEX_PAGESIZE >=", value, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeLessThan(Integer value) {
            addCriterion("INDEX_PAGESIZE <", value, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeLessThanOrEqualTo(Integer value) {
            addCriterion("INDEX_PAGESIZE <=", value, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeIn(List<Integer> values) {
            addCriterion("INDEX_PAGESIZE in", values, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeNotIn(List<Integer> values) {
            addCriterion("INDEX_PAGESIZE not in", values, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeBetween(Integer value1, Integer value2) {
            addCriterion("INDEX_PAGESIZE between", value1, value2, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andIndexPagesizeNotBetween(Integer value1, Integer value2) {
            addCriterion("INDEX_PAGESIZE not between", value1, value2, "indexPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeIsNull() {
            addCriterion("CATEGORY_PAGESIZE is null");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeIsNotNull() {
            addCriterion("CATEGORY_PAGESIZE is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeEqualTo(Integer value) {
            addCriterion("CATEGORY_PAGESIZE =", value, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeNotEqualTo(Integer value) {
            addCriterion("CATEGORY_PAGESIZE <>", value, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeGreaterThan(Integer value) {
            addCriterion("CATEGORY_PAGESIZE >", value, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("CATEGORY_PAGESIZE >=", value, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeLessThan(Integer value) {
            addCriterion("CATEGORY_PAGESIZE <", value, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeLessThanOrEqualTo(Integer value) {
            addCriterion("CATEGORY_PAGESIZE <=", value, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeIn(List<Integer> values) {
            addCriterion("CATEGORY_PAGESIZE in", values, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeNotIn(List<Integer> values) {
            addCriterion("CATEGORY_PAGESIZE not in", values, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeBetween(Integer value1, Integer value2) {
            addCriterion("CATEGORY_PAGESIZE between", value1, value2, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andCategoryPagesizeNotBetween(Integer value1, Integer value2) {
            addCriterion("CATEGORY_PAGESIZE not between", value1, value2, "categoryPagesize");
            return (Criteria) this;
        }

        public Criteria andProImagePathIsNull() {
            addCriterion("PRO_IMAGE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andProImagePathIsNotNull() {
            addCriterion("PRO_IMAGE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andProImagePathEqualTo(String value) {
            addCriterion("PRO_IMAGE_PATH =", value, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathNotEqualTo(String value) {
            addCriterion("PRO_IMAGE_PATH <>", value, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathGreaterThan(String value) {
            addCriterion("PRO_IMAGE_PATH >", value, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathGreaterThanOrEqualTo(String value) {
            addCriterion("PRO_IMAGE_PATH >=", value, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathLessThan(String value) {
            addCriterion("PRO_IMAGE_PATH <", value, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathLessThanOrEqualTo(String value) {
            addCriterion("PRO_IMAGE_PATH <=", value, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathLike(String value) {
            addCriterion("PRO_IMAGE_PATH like", value, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathNotLike(String value) {
            addCriterion("PRO_IMAGE_PATH not like", value, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathIn(List<String> values) {
            addCriterion("PRO_IMAGE_PATH in", values, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathNotIn(List<String> values) {
            addCriterion("PRO_IMAGE_PATH not in", values, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathBetween(String value1, String value2) {
            addCriterion("PRO_IMAGE_PATH between", value1, value2, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andProImagePathNotBetween(String value1, String value2) {
            addCriterion("PRO_IMAGE_PATH not between", value1, value2, "proImagePath");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(NAME) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(TITLE) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andKeyWorldsLikeInsensitive(String value) {
            addCriterion("upper(KEY_WORLDS) like", value.toUpperCase(), "keyWorlds");
            return (Criteria) this;
        }

        public Criteria andDescriptionLikeInsensitive(String value) {
            addCriterion("upper(DESCRIPTION) like", value.toUpperCase(), "description");
            return (Criteria) this;
        }

        public Criteria andTelephoneLikeInsensitive(String value) {
            addCriterion("upper(TELEPHONE) like", value.toUpperCase(), "telephone");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeLikeInsensitive(String value) {
            addCriterion("upper(STATISTICS_CODE) like", value.toUpperCase(), "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andProImagePathLikeInsensitive(String value) {
            addCriterion("upper(PRO_IMAGE_PATH) like", value.toUpperCase(), "proImagePath");
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