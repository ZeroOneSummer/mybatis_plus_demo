//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dimai.mybatis_plus_demo.mybatis.common;
import com.dimai.mybatis_plus_demo.mybatis.annotion.*;
import com.dimai.mybatis_plus_demo.mybatis.annotion.JDBCType;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Date;
import java.util.*;

public class TypeMetaData {
    private final Class<?> type;
    private final String defaultTableName;
    private final List<TypeMetaData.SqlField> sqlFields;
    private final List<TypeMetaData.SqlField> selectFields;
    private final List<TypeMetaData.SqlField> updateFields;
    private final List<TypeMetaData.SqlField> addFields;
    private final List<TypeMetaData.SqlField> primaryKeys;
    private final List<TypeMetaData.SqlField> orderBys;
    private TypeMetaData.SqlField idField = null;

    public TypeMetaData(Class<?> type) {
        this.type = type;
        this.sqlFields = new ArrayList();
        this.selectFields = new ArrayList();
        this.updateFields = new ArrayList();
        this.addFields = new ArrayList();
        this.primaryKeys = new ArrayList();
        this.idField = null;
        this.orderBys = new ArrayList();
        int idCount = 0;
        Field[] fields = (Field[])ReflectUtil.getAllFields(type).toArray(new Field[0]);
        boolean useUpperToUnderscore = type.getAnnotation(UseUpperToUnderscore.class) != null;
        Field[] var5 = fields;
        int var6 = fields.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            Field field = var5[var7];
            TypeMetaData.SqlField sqlField = new TypeMetaData.SqlField(field, useUpperToUnderscore);
            if (!sqlField.isIgnoreSelect()) {
                this.selectFields.add(sqlField);
            }

            if (!sqlField.isIgnoreUpdate()) {
                this.updateFields.add(sqlField);
            }

            if (!sqlField.isIgnoreAdd()) {
                this.addFields.add(sqlField);
            }

            if (sqlField.isPrimaryKey()) {
                this.primaryKeys.add(sqlField);
            }

            if (sqlField.isId()) {
                this.idField = sqlField;
                ++idCount;
            }

            if (sqlField.isOrderBy()) {
                this.orderBys.add(sqlField);
            }
        }

        if (idCount > 1) {
            throw new RuntimeException("Cannot have multiple id properties, must only one.");
        } else {
            TableName tableName = (TableName)type.getAnnotation(TableName.class);
            if (tableName != null) {
                this.defaultTableName = tableName.value();
            } else {
                this.defaultTableName = type.getSimpleName();
            }

        }
    }

    public Class<?> getType() {
        return this.type;
    }

    public String getDefaultTableName() {
        return this.defaultTableName;
    }

    public List<TypeMetaData.SqlField> getSqlFields() {
        return this.sqlFields;
    }

    public List<TypeMetaData.SqlField> getSelectFields() {
        return this.selectFields;
    }

    public List<TypeMetaData.SqlField> getUpdateFields() {
        return this.updateFields;
    }

    public List<TypeMetaData.SqlField> getAddFields() {
        return this.addFields;
    }

    public List<TypeMetaData.SqlField> getPrimaryKeys() {
        return this.primaryKeys;
    }

    public List<TypeMetaData.SqlField> getOrderBys() {
        return this.orderBys;
    }

    public TypeMetaData.SqlField getIdField() {
        return this.idField;
    }

    public static class SqlField {
        private static Map<Class, JdbcType> JdbcTypeMap = new HashMap();
        private String fieldName;
        private String colName;
        private Class colType;
        private JdbcType jdbcType;
        private boolean ignoreSelect = false;
        private boolean ignoreUpdate = false;
        private boolean ignoreAdd = false;
        private boolean isPrimaryKey = false;
        private boolean isId = false;
        private String orderBy = "";

        public SqlField(Field field, boolean useUpperToUnderscore) {
            this.fieldName = field.getName();
            this.colType = field.getType();
            if (field.getAnnotation(ColumnName.class) != null) {
                this.colName = ((ColumnName)field.getAnnotation(ColumnName.class)).value();
            } else {
                this.colName = this.convertFieldNameToColName(this.fieldName, useUpperToUnderscore);
            }

            if (field.getAnnotation(IgnoreSelect.class) != null) {
                this.ignoreSelect = true;
            }

            if (field.getAnnotation(IgnoreUpdate.class) != null) {
                this.ignoreUpdate = true;
            }

            if (field.getAnnotation(IgnoreAdd.class) != null) {
                this.ignoreAdd = true;
            }

            if (field.getAnnotation(PrimaryKey.class) != null) {
                this.isPrimaryKey = true;
            }

            if (field.getAnnotation(Id.class) != null) {
                this.isId = true;
                Id id = (Id)field.getAnnotation(Id.class);
                if (StringUtils.hasText(id.column())) {
                    this.colName = id.column();
                }
            }

            if (field.getAnnotation(OrderBy.class) != null) {
                OrderBy orderBy = (OrderBy)field.getAnnotation(OrderBy.class);
                this.orderBy = orderBy.value();
            }

            if (field.getAnnotation(com.dimai.mybatis_plus_demo.mybatis.annotion.JDBCType.class) != null) {
                this.jdbcType = ((com.dimai.mybatis_plus_demo.mybatis.annotion.JDBCType)field.getAnnotation(JDBCType.class)).value();
            } else {
                this.jdbcType = this.convertJavaTypeToJdbcType(field.getType());
            }

        }

        private JdbcType convertJavaTypeToJdbcType(Class javaType) {
            return JdbcTypeMap.containsKey(javaType) ? (JdbcType)JdbcTypeMap.get(javaType) : JdbcType.OTHER;
        }

        private String convertFieldNameToColName(String fieldName, boolean useUpperToUnderscore) {
            return useUpperToUnderscore ? StringUtils.upperToUnderScore(fieldName) : fieldName;
        }

        public String getFieldName() {
            return this.fieldName;
        }

        public String getColName() {
            return this.colName;
        }

        public Class getColType() {
            return this.colType;
        }

        public boolean isIgnoreSelect() {
            return this.ignoreSelect;
        }

        public boolean isIgnoreUpdate() {
            return this.ignoreUpdate;
        }

        public boolean isIgnoreAdd() {
            return this.ignoreAdd;
        }

        public boolean isPrimaryKey() {
            return this.isPrimaryKey;
        }

        public boolean isId() {
            return this.isId;
        }

        public boolean isOrderBy() {
            return StringUtils.hasText(this.orderBy);
        }

        public JdbcType getJdbcType() {
            return this.jdbcType;
        }

        public String getOrderBy() {
            return this.orderBy;
        }

        static {
            JdbcTypeMap.put(String.class, JdbcType.VARCHAR);
            JdbcTypeMap.put(Integer.class, JdbcType.INTEGER);
            JdbcTypeMap.put(Integer.TYPE, JdbcType.INTEGER);
            JdbcTypeMap.put(Short.class, JdbcType.SMALLINT);
            JdbcTypeMap.put(Short.TYPE, JdbcType.SMALLINT);
            JdbcTypeMap.put(Double.class, JdbcType.DOUBLE);
            JdbcTypeMap.put(Double.TYPE, JdbcType.DOUBLE);
            JdbcTypeMap.put(BigDecimal.class, JdbcType.DECIMAL);
            JdbcTypeMap.put(Float.class, JdbcType.REAL);
            JdbcTypeMap.put(Float.TYPE, JdbcType.DOUBLE);
            JdbcTypeMap.put(Long.class, JdbcType.BIGINT);
            JdbcTypeMap.put(Long.TYPE, JdbcType.DOUBLE);
            JdbcTypeMap.put(Boolean.class, JdbcType.BOOLEAN);
            JdbcTypeMap.put(Boolean.TYPE, JdbcType.BOOLEAN);
            JdbcTypeMap.put(Date.class, JdbcType.DATE);
            JdbcTypeMap.put(java.sql.Date.class, JdbcType.DATE);
            JdbcTypeMap.put(Time.class, JdbcType.TIME);
            JdbcTypeMap.put(Timestamp.class, JdbcType.TIMESTAMP);
            JdbcTypeMap.put(Clob.class, JdbcType.CLOB);
            JdbcTypeMap.put(Blob.class, JdbcType.BLOB);
            JdbcTypeMap.put(Array.class, JdbcType.ARRAY);
        }
    }
}
