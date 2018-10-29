package com.yzhh.backstage.api.entity;

import java.util.ArrayList;
import java.util.List;

public class PositionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PositionExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLastAccessIsNull() {
            addCriterion("last_access is null");
            return (Criteria) this;
        }

        public Criteria andLastAccessIsNotNull() {
            addCriterion("last_access is not null");
            return (Criteria) this;
        }

        public Criteria andLastAccessEqualTo(Long value) {
            addCriterion("last_access =", value, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessNotEqualTo(Long value) {
            addCriterion("last_access <>", value, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessGreaterThan(Long value) {
            addCriterion("last_access >", value, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessGreaterThanOrEqualTo(Long value) {
            addCriterion("last_access >=", value, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessLessThan(Long value) {
            addCriterion("last_access <", value, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessLessThanOrEqualTo(Long value) {
            addCriterion("last_access <=", value, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessIn(List<Long> values) {
            addCriterion("last_access in", values, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessNotIn(List<Long> values) {
            addCriterion("last_access not in", values, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessBetween(Long value1, Long value2) {
            addCriterion("last_access between", value1, value2, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andLastAccessNotBetween(Long value1, Long value2) {
            addCriterion("last_access not between", value1, value2, "lastAccess");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Long value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Long value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Long value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Long value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Long value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Long value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Long> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Long> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Long value1, Long value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Long value1, Long value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNull() {
            addCriterion("release_date is null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIsNotNull() {
            addCriterion("release_date is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseDateEqualTo(Long value) {
            addCriterion("release_date =", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotEqualTo(Long value) {
            addCriterion("release_date <>", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThan(Long value) {
            addCriterion("release_date >", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateGreaterThanOrEqualTo(Long value) {
            addCriterion("release_date >=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThan(Long value) {
            addCriterion("release_date <", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateLessThanOrEqualTo(Long value) {
            addCriterion("release_date <=", value, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateIn(List<Long> values) {
            addCriterion("release_date in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotIn(List<Long> values) {
            addCriterion("release_date not in", values, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateBetween(Long value1, Long value2) {
            addCriterion("release_date between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andReleaseDateNotBetween(Long value1, Long value2) {
            addCriterion("release_date not between", value1, value2, "releaseDate");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }
        
        public Criteria andType2IsNull() {
            addCriterion("type2 is null");
            return (Criteria) this;
        }

        public Criteria andType2IsNotNull() {
            addCriterion("type2 is not null");
            return (Criteria) this;
        }

        public Criteria andType2EqualTo(String value) {
            addCriterion("type2 =", value, "type2");
            return (Criteria) this;
        }

        public Criteria andType2NotEqualTo(String value) {
            addCriterion("type2 <>", value, "type2");
            return (Criteria) this;
        }

        public Criteria andType2GreaterThan(String value) {
            addCriterion("type2 >", value, "type2");
            return (Criteria) this;
        }

        public Criteria andType2GreaterThanOrEqualTo(String value) {
            addCriterion("type2 >=", value, "type2");
            return (Criteria) this;
        }

        public Criteria andType2LessThan(String value) {
            addCriterion("type2 <", value, "type2");
            return (Criteria) this;
        }

        public Criteria andType2LessThanOrEqualTo(String value) {
            addCriterion("type2 <=", value, "type2");
            return (Criteria) this;
        }

        public Criteria andType2Like(String value) {
            addCriterion("type2 like", value, "type2");
            return (Criteria) this;
        }

        public Criteria andType2NotLike(String value) {
            addCriterion("type2 not like", value, "type2");
            return (Criteria) this;
        }

        public Criteria andType2In(List<String> values) {
            addCriterion("type2 in", values, "type2");
            return (Criteria) this;
        }

        public Criteria andType2NotIn(List<String> values) {
            addCriterion("type2 not in", values, "type2");
            return (Criteria) this;
        }

        public Criteria andType2Between(String value1, String value2) {
            addCriterion("type2 between", value1, value2, "type2");
            return (Criteria) this;
        }

        public Criteria andType2NotBetween(String value1, String value2) {
            addCriterion("type2 not between", value1, value2, "type2");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNull() {
            addCriterion("province is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIsNotNull() {
            addCriterion("province is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceEqualTo(String value) {
            addCriterion("province =", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotEqualTo(String value) {
            addCriterion("province <>", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThan(String value) {
            addCriterion("province >", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("province >=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThan(String value) {
            addCriterion("province <", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLessThanOrEqualTo(String value) {
            addCriterion("province <=", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceLike(String value) {
            addCriterion("province like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotLike(String value) {
            addCriterion("province not like", value, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceIn(List<String> values) {
            addCriterion("province in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotIn(List<String> values) {
            addCriterion("province not in", values, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceBetween(String value1, String value2) {
            addCriterion("province between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andProvinceNotBetween(String value1, String value2) {
            addCriterion("province not between", value1, value2, "province");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andPerpleNumIsNull() {
            addCriterion("perple_num is null");
            return (Criteria) this;
        }

        public Criteria andPerpleNumIsNotNull() {
            addCriterion("perple_num is not null");
            return (Criteria) this;
        }

        public Criteria andPerpleNumEqualTo(Integer value) {
            addCriterion("perple_num =", value, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumNotEqualTo(Integer value) {
            addCriterion("perple_num <>", value, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumGreaterThan(Integer value) {
            addCriterion("perple_num >", value, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("perple_num >=", value, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumLessThan(Integer value) {
            addCriterion("perple_num <", value, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumLessThanOrEqualTo(Integer value) {
            addCriterion("perple_num <=", value, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumIn(List<Integer> values) {
            addCriterion("perple_num in", values, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumNotIn(List<Integer> values) {
            addCriterion("perple_num not in", values, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumBetween(Integer value1, Integer value2) {
            addCriterion("perple_num between", value1, value2, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andPerpleNumNotBetween(Integer value1, Integer value2) {
            addCriterion("perple_num not between", value1, value2, "perpleNum");
            return (Criteria) this;
        }

        public Criteria andSeductionIsNull() {
            addCriterion("seduction is null");
            return (Criteria) this;
        }

        public Criteria andSeductionIsNotNull() {
            addCriterion("seduction is not null");
            return (Criteria) this;
        }

        public Criteria andSeductionEqualTo(String value) {
            addCriterion("seduction =", value, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionNotEqualTo(String value) {
            addCriterion("seduction <>", value, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionGreaterThan(String value) {
            addCriterion("seduction >", value, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionGreaterThanOrEqualTo(String value) {
            addCriterion("seduction >=", value, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionLessThan(String value) {
            addCriterion("seduction <", value, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionLessThanOrEqualTo(String value) {
            addCriterion("seduction <=", value, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionLike(String value) {
            addCriterion("seduction like", value, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionNotLike(String value) {
            addCriterion("seduction not like", value, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionIn(List<String> values) {
            addCriterion("seduction in", values, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionNotIn(List<String> values) {
            addCriterion("seduction not in", values, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionBetween(String value1, String value2) {
            addCriterion("seduction between", value1, value2, "seduction");
            return (Criteria) this;
        }

        public Criteria andSeductionNotBetween(String value1, String value2) {
            addCriterion("seduction not between", value1, value2, "seduction");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNull() {
            addCriterion("description is null");
            return (Criteria) this;
        }

        public Criteria andDescriptionIsNotNull() {
            addCriterion("description is not null");
            return (Criteria) this;
        }

        public Criteria andDescriptionEqualTo(String value) {
            addCriterion("description =", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotEqualTo(String value) {
            addCriterion("description <>", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThan(String value) {
            addCriterion("description >", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("description >=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThan(String value) {
            addCriterion("description <", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLessThanOrEqualTo(String value) {
            addCriterion("description <=", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionLike(String value) {
            addCriterion("description like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotLike(String value) {
            addCriterion("description not like", value, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionIn(List<String> values) {
            addCriterion("description in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotIn(List<String> values) {
            addCriterion("description not in", values, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionBetween(String value1, String value2) {
            addCriterion("description between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andDescriptionNotBetween(String value1, String value2) {
            addCriterion("description not between", value1, value2, "description");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(String value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(String value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(String value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(String value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(String value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(String value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLike(String value) {
            addCriterion("education like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotLike(String value) {
            addCriterion("education not like", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<String> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<String> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(String value1, String value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(String value1, String value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIsNull() {
            addCriterion("work_type is null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIsNotNull() {
            addCriterion("work_type is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTypeEqualTo(String value) {
            addCriterion("work_type =", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotEqualTo(String value) {
            addCriterion("work_type <>", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeGreaterThan(String value) {
            addCriterion("work_type >", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeGreaterThanOrEqualTo(String value) {
            addCriterion("work_type >=", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLessThan(String value) {
            addCriterion("work_type <", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLessThanOrEqualTo(String value) {
            addCriterion("work_type <=", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeLike(String value) {
            addCriterion("work_type like", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotLike(String value) {
            addCriterion("work_type not like", value, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeIn(List<String> values) {
            addCriterion("work_type in", values, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotIn(List<String> values) {
            addCriterion("work_type not in", values, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeBetween(String value1, String value2) {
            addCriterion("work_type between", value1, value2, "workType");
            return (Criteria) this;
        }

        public Criteria andWorkTypeNotBetween(String value1, String value2) {
            addCriterion("work_type not between", value1, value2, "workType");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeIsNull() {
            addCriterion("internship_time is null");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeIsNotNull() {
            addCriterion("internship_time is not null");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeEqualTo(String value) {
            addCriterion("internship_time =", value, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeNotEqualTo(String value) {
            addCriterion("internship_time <>", value, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeGreaterThan(String value) {
            addCriterion("internship_time >", value, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeGreaterThanOrEqualTo(String value) {
            addCriterion("internship_time >=", value, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeLessThan(String value) {
            addCriterion("internship_time <", value, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeLessThanOrEqualTo(String value) {
            addCriterion("internship_time <=", value, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeLike(String value) {
            addCriterion("internship_time like", value, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeNotLike(String value) {
            addCriterion("internship_time not like", value, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeIn(List<String> values) {
            addCriterion("internship_time in", values, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeNotIn(List<String> values) {
            addCriterion("internship_time not in", values, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeBetween(String value1, String value2) {
            addCriterion("internship_time between", value1, value2, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andInternshipTimeNotBetween(String value1, String value2) {
            addCriterion("internship_time not between", value1, value2, "internshipTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIsNull() {
            addCriterion("work_time is null");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIsNotNull() {
            addCriterion("work_time is not null");
            return (Criteria) this;
        }

        public Criteria andWorkTimeEqualTo(String value) {
            addCriterion("work_time =", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotEqualTo(String value) {
            addCriterion("work_time <>", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeGreaterThan(String value) {
            addCriterion("work_time >", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeGreaterThanOrEqualTo(String value) {
            addCriterion("work_time >=", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeLessThan(String value) {
            addCriterion("work_time <", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeLessThanOrEqualTo(String value) {
            addCriterion("work_time <=", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeLike(String value) {
            addCriterion("work_time like", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotLike(String value) {
            addCriterion("work_time not like", value, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeIn(List<String> values) {
            addCriterion("work_time in", values, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotIn(List<String> values) {
            addCriterion("work_time not in", values, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeBetween(String value1, String value2) {
            addCriterion("work_time between", value1, value2, "workTime");
            return (Criteria) this;
        }

        public Criteria andWorkTimeNotBetween(String value1, String value2) {
            addCriterion("work_time not between", value1, value2, "workTime");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceIsNull() {
            addCriterion("correction_chance is null");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceIsNotNull() {
            addCriterion("correction_chance is not null");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceEqualTo(String value) {
            addCriterion("correction_chance =", value, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceNotEqualTo(String value) {
            addCriterion("correction_chance <>", value, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceGreaterThan(String value) {
            addCriterion("correction_chance >", value, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceGreaterThanOrEqualTo(String value) {
            addCriterion("correction_chance >=", value, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceLessThan(String value) {
            addCriterion("correction_chance <", value, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceLessThanOrEqualTo(String value) {
            addCriterion("correction_chance <=", value, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceLike(String value) {
            addCriterion("correction_chance like", value, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceNotLike(String value) {
            addCriterion("correction_chance not like", value, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceIn(List<String> values) {
            addCriterion("correction_chance in", values, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceNotIn(List<String> values) {
            addCriterion("correction_chance not in", values, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceBetween(String value1, String value2) {
            addCriterion("correction_chance between", value1, value2, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andCorrectionChanceNotBetween(String value1, String value2) {
            addCriterion("correction_chance not between", value1, value2, "correctionChance");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNull() {
            addCriterion("deadline is null");
            return (Criteria) this;
        }

        public Criteria andDeadlineIsNotNull() {
            addCriterion("deadline is not null");
            return (Criteria) this;
        }

        public Criteria andDeadlineEqualTo(Long value) {
            addCriterion("deadline =", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotEqualTo(Long value) {
            addCriterion("deadline <>", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThan(Long value) {
            addCriterion("deadline >", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineGreaterThanOrEqualTo(Long value) {
            addCriterion("deadline >=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThan(Long value) {
            addCriterion("deadline <", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineLessThanOrEqualTo(Long value) {
            addCriterion("deadline <=", value, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineIn(List<Long> values) {
            addCriterion("deadline in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotIn(List<Long> values) {
            addCriterion("deadline not in", values, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineBetween(Long value1, Long value2) {
            addCriterion("deadline between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andDeadlineNotBetween(Long value1, Long value2) {
            addCriterion("deadline not between", value1, value2, "deadline");
            return (Criteria) this;
        }

        public Criteria andPerDiemIsNull() {
            addCriterion("per_diem is null");
            return (Criteria) this;
        }

        public Criteria andPerDiemIsNotNull() {
            addCriterion("per_diem is not null");
            return (Criteria) this;
        }

        public Criteria andPerDiemEqualTo(String value) {
            addCriterion("per_diem =", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemNotEqualTo(String value) {
            addCriterion("per_diem <>", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemGreaterThan(String value) {
            addCriterion("per_diem >", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemGreaterThanOrEqualTo(String value) {
            addCriterion("per_diem >=", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemLessThan(String value) {
            addCriterion("per_diem <", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemLessThanOrEqualTo(String value) {
            addCriterion("per_diem <=", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemLike(String value) {
            addCriterion("per_diem like", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemNotLike(String value) {
            addCriterion("per_diem not like", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemIn(List<String> values) {
            addCriterion("per_diem in", values, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemNotIn(List<String> values) {
            addCriterion("per_diem not in", values, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemBetween(String value1, String value2) {
            addCriterion("per_diem between", value1, value2, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemNotBetween(String value1, String value2) {
            addCriterion("per_diem not between", value1, value2, "perDiem");
            return (Criteria) this;
        }

        public Criteria andIsPressingIsNull() {
            addCriterion("is_pressing is null");
            return (Criteria) this;
        }

        public Criteria andIsPressingIsNotNull() {
            addCriterion("is_pressing is not null");
            return (Criteria) this;
        }

        public Criteria andIsPressingEqualTo(Boolean value) {
            addCriterion("is_pressing =", value, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingNotEqualTo(Boolean value) {
            addCriterion("is_pressing <>", value, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingGreaterThan(Boolean value) {
            addCriterion("is_pressing >", value, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_pressing >=", value, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingLessThan(Boolean value) {
            addCriterion("is_pressing <", value, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingLessThanOrEqualTo(Boolean value) {
            addCriterion("is_pressing <=", value, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingIn(List<Boolean> values) {
            addCriterion("is_pressing in", values, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingNotIn(List<Boolean> values) {
            addCriterion("is_pressing not in", values, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingBetween(Boolean value1, Boolean value2) {
            addCriterion("is_pressing between", value1, value2, "isPressing");
            return (Criteria) this;
        }

        public Criteria andIsPressingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_pressing not between", value1, value2, "isPressing");
            return (Criteria) this;
        }

        public Criteria andWorkDateIsNull() {
            addCriterion("work_date is null");
            return (Criteria) this;
        }

        public Criteria andWorkDateIsNotNull() {
            addCriterion("work_date is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDateEqualTo(String value) {
            addCriterion("work_date =", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotEqualTo(String value) {
            addCriterion("work_date <>", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateGreaterThan(String value) {
            addCriterion("work_date >", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateGreaterThanOrEqualTo(String value) {
            addCriterion("work_date >=", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLessThan(String value) {
            addCriterion("work_date <", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLessThanOrEqualTo(String value) {
            addCriterion("work_date <=", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateLike(String value) {
            addCriterion("work_date like", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotLike(String value) {
            addCriterion("work_date not like", value, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateIn(List<String> values) {
            addCriterion("work_date in", values, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotIn(List<String> values) {
            addCriterion("work_date not in", values, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateBetween(String value1, String value2) {
            addCriterion("work_date between", value1, value2, "workDate");
            return (Criteria) this;
        }

        public Criteria andWorkDateNotBetween(String value1, String value2) {
            addCriterion("work_date not between", value1, value2, "workDate");
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