package com.yzhh.backstage.api.entity;

import java.util.ArrayList;
import java.util.List;

public class ResumeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ResumeExample() {
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

        public Criteria andJobSeekerIdIsNull() {
            addCriterion("job_seeker_id is null");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdIsNotNull() {
            addCriterion("job_seeker_id is not null");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdEqualTo(Long value) {
            addCriterion("job_seeker_id =", value, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdNotEqualTo(Long value) {
            addCriterion("job_seeker_id <>", value, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdGreaterThan(Long value) {
            addCriterion("job_seeker_id >", value, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("job_seeker_id >=", value, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdLessThan(Long value) {
            addCriterion("job_seeker_id <", value, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdLessThanOrEqualTo(Long value) {
            addCriterion("job_seeker_id <=", value, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdIn(List<Long> values) {
            addCriterion("job_seeker_id in", values, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdNotIn(List<Long> values) {
            addCriterion("job_seeker_id not in", values, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdBetween(Long value1, Long value2) {
            addCriterion("job_seeker_id between", value1, value2, "jobSeekerId");
            return (Criteria) this;
        }

        public Criteria andJobSeekerIdNotBetween(Long value1, Long value2) {
            addCriterion("job_seeker_id not between", value1, value2, "jobSeekerId");
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

        public Criteria andIsDefaultIsNull() {
            addCriterion("is_default is null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIsNotNull() {
            addCriterion("is_default is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefaultEqualTo(Integer value) {
            addCriterion("is_default =", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotEqualTo(Integer value) {
            addCriterion("is_default <>", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThan(Integer value) {
            addCriterion("is_default >", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_default >=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThan(Integer value) {
            addCriterion("is_default <", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultLessThanOrEqualTo(Integer value) {
            addCriterion("is_default <=", value, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultIn(List<Integer> values) {
            addCriterion("is_default in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotIn(List<Integer> values) {
            addCriterion("is_default not in", values, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultBetween(Integer value1, Integer value2) {
            addCriterion("is_default between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andIsDefaultNotBetween(Integer value1, Integer value2) {
            addCriterion("is_default not between", value1, value2, "isDefault");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameIsNull() {
            addCriterion("wish_position_name is null");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameIsNotNull() {
            addCriterion("wish_position_name is not null");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameEqualTo(String value) {
            addCriterion("wish_position_name =", value, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameNotEqualTo(String value) {
            addCriterion("wish_position_name <>", value, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameGreaterThan(String value) {
            addCriterion("wish_position_name >", value, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameGreaterThanOrEqualTo(String value) {
            addCriterion("wish_position_name >=", value, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameLessThan(String value) {
            addCriterion("wish_position_name <", value, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameLessThanOrEqualTo(String value) {
            addCriterion("wish_position_name <=", value, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameLike(String value) {
            addCriterion("wish_position_name like", value, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameNotLike(String value) {
            addCriterion("wish_position_name not like", value, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameIn(List<String> values) {
            addCriterion("wish_position_name in", values, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameNotIn(List<String> values) {
            addCriterion("wish_position_name not in", values, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameBetween(String value1, String value2) {
            addCriterion("wish_position_name between", value1, value2, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishPositionNameNotBetween(String value1, String value2) {
            addCriterion("wish_position_name not between", value1, value2, "wishPositionName");
            return (Criteria) this;
        }

        public Criteria andWishCityIsNull() {
            addCriterion("wish_city is null");
            return (Criteria) this;
        }

        public Criteria andWishCityIsNotNull() {
            addCriterion("wish_city is not null");
            return (Criteria) this;
        }

        public Criteria andWishCityEqualTo(String value) {
            addCriterion("wish_city =", value, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityNotEqualTo(String value) {
            addCriterion("wish_city <>", value, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityGreaterThan(String value) {
            addCriterion("wish_city >", value, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityGreaterThanOrEqualTo(String value) {
            addCriterion("wish_city >=", value, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityLessThan(String value) {
            addCriterion("wish_city <", value, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityLessThanOrEqualTo(String value) {
            addCriterion("wish_city <=", value, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityLike(String value) {
            addCriterion("wish_city like", value, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityNotLike(String value) {
            addCriterion("wish_city not like", value, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityIn(List<String> values) {
            addCriterion("wish_city in", values, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityNotIn(List<String> values) {
            addCriterion("wish_city not in", values, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityBetween(String value1, String value2) {
            addCriterion("wish_city between", value1, value2, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWishCityNotBetween(String value1, String value2) {
            addCriterion("wish_city not between", value1, value2, "wishCity");
            return (Criteria) this;
        }

        public Criteria andWorkDayIsNull() {
            addCriterion("work_day is null");
            return (Criteria) this;
        }

        public Criteria andWorkDayIsNotNull() {
            addCriterion("work_day is not null");
            return (Criteria) this;
        }

        public Criteria andWorkDayEqualTo(Integer value) {
            addCriterion("work_day =", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayNotEqualTo(Integer value) {
            addCriterion("work_day <>", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayGreaterThan(Integer value) {
            addCriterion("work_day >", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("work_day >=", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayLessThan(Integer value) {
            addCriterion("work_day <", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayLessThanOrEqualTo(Integer value) {
            addCriterion("work_day <=", value, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayIn(List<Integer> values) {
            addCriterion("work_day in", values, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayNotIn(List<Integer> values) {
            addCriterion("work_day not in", values, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayBetween(Integer value1, Integer value2) {
            addCriterion("work_day between", value1, value2, "workDay");
            return (Criteria) this;
        }

        public Criteria andWorkDayNotBetween(Integer value1, Integer value2) {
            addCriterion("work_day not between", value1, value2, "workDay");
            return (Criteria) this;
        }

        public Criteria andFieldStartIsNull() {
            addCriterion("field_start is null");
            return (Criteria) this;
        }

        public Criteria andFieldStartIsNotNull() {
            addCriterion("field_start is not null");
            return (Criteria) this;
        }

        public Criteria andFieldStartEqualTo(Long value) {
            addCriterion("field_start =", value, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartNotEqualTo(Long value) {
            addCriterion("field_start <>", value, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartGreaterThan(Long value) {
            addCriterion("field_start >", value, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartGreaterThanOrEqualTo(Long value) {
            addCriterion("field_start >=", value, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartLessThan(Long value) {
            addCriterion("field_start <", value, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartLessThanOrEqualTo(Long value) {
            addCriterion("field_start <=", value, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartIn(List<Long> values) {
            addCriterion("field_start in", values, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartNotIn(List<Long> values) {
            addCriterion("field_start not in", values, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartBetween(Long value1, Long value2) {
            addCriterion("field_start between", value1, value2, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldStartNotBetween(Long value1, Long value2) {
            addCriterion("field_start not between", value1, value2, "fieldStart");
            return (Criteria) this;
        }

        public Criteria andFieldEndIsNull() {
            addCriterion("field_end is null");
            return (Criteria) this;
        }

        public Criteria andFieldEndIsNotNull() {
            addCriterion("field_end is not null");
            return (Criteria) this;
        }

        public Criteria andFieldEndEqualTo(Long value) {
            addCriterion("field_end =", value, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndNotEqualTo(Long value) {
            addCriterion("field_end <>", value, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndGreaterThan(Long value) {
            addCriterion("field_end >", value, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndGreaterThanOrEqualTo(Long value) {
            addCriterion("field_end >=", value, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndLessThan(Long value) {
            addCriterion("field_end <", value, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndLessThanOrEqualTo(Long value) {
            addCriterion("field_end <=", value, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndIn(List<Long> values) {
            addCriterion("field_end in", values, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndNotIn(List<Long> values) {
            addCriterion("field_end not in", values, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndBetween(Long value1, Long value2) {
            addCriterion("field_end between", value1, value2, "fieldEnd");
            return (Criteria) this;
        }

        public Criteria andFieldEndNotBetween(Long value1, Long value2) {
            addCriterion("field_end not between", value1, value2, "fieldEnd");
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

        public Criteria andPerDiemEqualTo(Double value) {
            addCriterion("per_diem =", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemNotEqualTo(Double value) {
            addCriterion("per_diem <>", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemGreaterThan(Double value) {
            addCriterion("per_diem >", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemGreaterThanOrEqualTo(Double value) {
            addCriterion("per_diem >=", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemLessThan(Double value) {
            addCriterion("per_diem <", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemLessThanOrEqualTo(Double value) {
            addCriterion("per_diem <=", value, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemIn(List<Double> values) {
            addCriterion("per_diem in", values, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemNotIn(List<Double> values) {
            addCriterion("per_diem not in", values, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemBetween(Double value1, Double value2) {
            addCriterion("per_diem between", value1, value2, "perDiem");
            return (Criteria) this;
        }

        public Criteria andPerDiemNotBetween(Double value1, Double value2) {
            addCriterion("per_diem not between", value1, value2, "perDiem");
            return (Criteria) this;
        }

        public Criteria andArrivalDayIsNull() {
            addCriterion("arrival_day is null");
            return (Criteria) this;
        }

        public Criteria andArrivalDayIsNotNull() {
            addCriterion("arrival_day is not null");
            return (Criteria) this;
        }

        public Criteria andArrivalDayEqualTo(Long value) {
            addCriterion("arrival_day =", value, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayNotEqualTo(Long value) {
            addCriterion("arrival_day <>", value, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayGreaterThan(Long value) {
            addCriterion("arrival_day >", value, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayGreaterThanOrEqualTo(Long value) {
            addCriterion("arrival_day >=", value, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayLessThan(Long value) {
            addCriterion("arrival_day <", value, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayLessThanOrEqualTo(Long value) {
            addCriterion("arrival_day <=", value, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayIn(List<Long> values) {
            addCriterion("arrival_day in", values, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayNotIn(List<Long> values) {
            addCriterion("arrival_day not in", values, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayBetween(Long value1, Long value2) {
            addCriterion("arrival_day between", value1, value2, "arrivalDay");
            return (Criteria) this;
        }

        public Criteria andArrivalDayNotBetween(Long value1, Long value2) {
            addCriterion("arrival_day not between", value1, value2, "arrivalDay");
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