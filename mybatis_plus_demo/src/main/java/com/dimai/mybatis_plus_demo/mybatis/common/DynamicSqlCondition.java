//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dimai.mybatis_plus_demo.mybatis.common;

import java.util.ArrayList;
import java.util.List;

public class DynamicSqlCondition {
    protected String orderByClause;
    protected boolean distinct;
    protected List<DynamicSqlCondition.Criteria> oredCriteria = new ArrayList();

    public static DynamicSqlCondition create() {
        return new DynamicSqlCondition();
    }

    public DynamicSqlCondition() {
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return this.orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return this.distinct;
    }

    public List<DynamicSqlCondition.Criteria> getOredCriteria() {
        return this.oredCriteria;
    }

    public void or(DynamicSqlCondition.Criteria criteria) {
        this.oredCriteria.add(criteria);
    }

    public DynamicSqlCondition.Criteria or() {
        DynamicSqlCondition.Criteria criteria = this.createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }

    public DynamicSqlCondition.Criteria createCriteria() {
        DynamicSqlCondition.Criteria criteria = this.createCriteriaInternal();
        if (this.oredCriteria.size() == 0) {
            this.oredCriteria.add(criteria);
        }

        return criteria;
    }

    protected DynamicSqlCondition.Criteria createCriteriaInternal() {
        DynamicSqlCondition.Criteria criteria = new DynamicSqlCondition.Criteria(this);
        return criteria;
    }

    public void clear() {
        this.oredCriteria.clear();
        this.orderByClause = null;
        this.distinct = false;
    }

    private static String handlePreAnd(String condition) {
        condition = condition.trim();
        if (condition.length() > 3) {
            String andStr = condition.substring(0, 3).toUpperCase();
            if (andStr.equals("AND")) {
                condition = condition.substring(3);
            }
        }

        return condition;
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
            return this.condition;
        }

        public Object getValue() {
            return this.value;
        }

        public Object getSecondValue() {
            return this.secondValue;
        }

        public boolean isNoValue() {
            return this.noValue;
        }

        public boolean isSingleValue() {
            return this.singleValue;
        }

        public boolean isBetweenValue() {
            return this.betweenValue;
        }

        public boolean isListValue() {
            return this.listValue;
        }

        public String getTypeHandler() {
            return this.typeHandler;
        }

        protected Criterion(String condition) {
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }

        }

        protected Criterion(String condition, Object value) {
            this(condition, value, (String)null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, (String)null);
        }
    }

    public static class Criteria extends DynamicSqlCondition.GeneratedCriteria {
        protected DynamicSqlCondition owner;

        protected Criteria(DynamicSqlCondition owner) {
            this.owner = owner;
        }

        public DynamicSqlCondition getDynamicSqlCondition() {
            return this.owner;
        }
    }

    protected abstract static class GeneratedCriteria {
        protected List<DynamicSqlCondition.Criterion> criteria = new ArrayList();

        protected GeneratedCriteria() {
        }

        public boolean isValid() {
            return this.criteria.size() > 0;
        }

        public List<DynamicSqlCondition.Criterion> getAllCriteria() {
            return this.criteria;
        }

        public List<DynamicSqlCondition.Criterion> getCriteria() {
            return this.criteria;
        }

        public DynamicSqlCondition.Criteria andIsNull(String column) {
            this.addCriterion(column + " is null");
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andCondtion(String condition) {
            if (condition == null) {
                throw new RuntimeException("condition cannot be null");
            } else {
                condition = DynamicSqlCondition.handlePreAnd(condition);
                this.addCriterion(condition);
                return (DynamicSqlCondition.Criteria)this;
            }
        }

        public DynamicSqlCondition.Criteria andIsNotNull(String column) {
            this.addCriterion(column + " is not null");
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andEqualTo(String column, Object value) {
            this.addCriterion(column + " =", value);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andNotEqualTo(String column, Object value) {
            this.addCriterion(column + " <>", value);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andGreaterThan(String column, Object value) {
            this.addCriterion(column + " >", value);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andGreaterThanOrEqualTo(String column, Object value) {
            this.addCriterion(column + " >=", value);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andLessThan(String column, Object value) {
            this.addCriterion(column + " <", value);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andLessThanOrEqualTo(String column, Object value) {
            this.addCriterion(column + " <=", value);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andLike(String column, Object value) {
            this.addCriterion(column + " like", value);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andNotLike(String column, Object value) {
            this.addCriterion(column + " not like", value);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andIn(String column, List<?> values) {
            this.addCriterion(column + " in", values);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andNotIn(String column, List<?> values) {
            this.addCriterion(column + " not in", values);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andBetween(String column, Object value1, Object value2) {
            this.addCriterion(column + " between", value1, value2);
            return (DynamicSqlCondition.Criteria)this;
        }

        public DynamicSqlCondition.Criteria andNotBetween(String column, Object value1, Object value2) {
            this.addCriterion(column + " not between", value1, value2);
            return (DynamicSqlCondition.Criteria)this;
        }

        protected DynamicSqlCondition.Criteria addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            } else {
                this.criteria.add(new DynamicSqlCondition.Criterion(condition));
                return (DynamicSqlCondition.Criteria)this;
            }
        }

        protected DynamicSqlCondition.Criteria addCriterion(String condition, Object value) {
            if (value == null) {
                throw new RuntimeException("Value cannot be null");
            } else {
                this.criteria.add(new DynamicSqlCondition.Criterion(condition, value));
                return (DynamicSqlCondition.Criteria)this;
            }
        }

        protected DynamicSqlCondition.Criteria addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            } else {
                this.criteria.add(new DynamicSqlCondition.Criterion(condition, value));
                return (DynamicSqlCondition.Criteria)this;
            }
        }

        protected DynamicSqlCondition.Criteria addCriterion(String condition, Object value1, Object value2) {
            if (value1 != null && value2 != null) {
                this.criteria.add(new DynamicSqlCondition.Criterion(condition, value1, value2));
                return (DynamicSqlCondition.Criteria)this;
            } else {
                throw new RuntimeException("Between values cannot be null");
            }
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 != null && value2 != null) {
                this.criteria.add(new DynamicSqlCondition.Criterion(condition, value1, value2));
            } else {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
        }
    }
}
