//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dimai.mybatis_plus_demo.mybatis.common;

import com.dimai.mybatis_plus_demo.mybatis.common.TypeMetaData.SqlField;
import com.dimai.mybatis_plus_demo.mybatis.annotion.TableAware;
import com.dimai.mybatis_plus_demo.mybatis.annotion.TableName;
import com.dimai.mybatis_plus_demo.mybatis.mapper.BaseMapper;
import com.dimai.mybatis_plus_demo.mybatis.mapper.SelectMapper;
import com.dimai.mybatis_plus_demo.mybatis.mapper.UpdateMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.ibatis.builder.annotation.ProviderSqlSource;
import org.apache.ibatis.mapping.*;
import org.apache.ibatis.mapping.ResultMapping.Builder;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.xmltags.XMLLanguageDriver;
import org.apache.ibatis.session.Configuration;

import java.lang.reflect.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DynamicSqlSourceProvider {
    protected static XMLLanguageDriver languageDriver = new XMLLanguageDriver();
    protected static String staticSelectWhere = "<where ><foreach collection=\"oredCriteria\" item=\"criteria\" separator=\"or\" ><if test=\"criteria.valid\" ><trim prefix=\"(\" suffix=\")\" prefixOverrides=\"and\" ><foreach collection=\"criteria.criteria\" item=\"criterion\" ><choose ><when test=\"criterion.noValue\" > and ${criterion.condition}</when><when test=\"criterion.singleValue\" > and ${criterion.condition} #{criterion.value}</when><when test=\"criterion.betweenValue\" > and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}</when><when test=\"criterion.listValue\" ><foreach collection=\"criterion.value\" item=\"listItem\" open=\"(\" close=\")\" separator=\",\" >#{listItem}</foreach></when></choose></foreach></trim></if></foreach></where>";
    protected static String staticUpdateWhere = "<where ><foreach collection=\"condition.oredCriteria\" item=\"criteria\" separator=\"or\" ><if test=\"criteria.valid\" ><trim prefix=\"(\" suffix=\")\" prefixOverrides=\"and\" ><foreach collection=\"criteria.criteria\" item=\"criterion\" ><choose ><when test=\"criterion.noValue\" > and ${criterion.condition}</when><when test=\"criterion.singleValue\" > and ${criterion.condition} #{criterion.value}</when><when test=\"criterion.betweenValue\" > and ${criterion.condition} #{criterion.value} and #{criterion.secondValue}</when><when test=\"criterion.listValue\" ><foreach collection=\"criterion.value\" item=\"listItem\" open=\"(\" close=\")\" separator=\",\" >#{listItem}</foreach></when></choose></foreach></trim></if></foreach></where>";
    private static final String DEFAULT_PK_COLUMN = "ID";
    private static final String INVALID_SQL = "INVALID_SQL";
    private static boolean useLowerCase = true;
    protected static Set<Class> parameterizedTypeMapperSet = Sets.newHashSet(new Class[]{BaseMapper.class, SelectMapper.class, UpdateMapper.class});

    public DynamicSqlSourceProvider() {
    }

    public static String build(Configuration configuration) {
        Iterator var1 = configuration.getMappedStatements().iterator();

        while(var1.hasNext()) {
            Object raw = var1.next();
            if (raw instanceof MappedStatement) {
                MappedStatement mappedStatement = (MappedStatement)raw;
                if (mappedStatement.getSqlSource() instanceof ProviderSqlSource) {
                    Class<?> providerClass = getProviderClass(mappedStatement);
                    if (providerClass == DynamicSqlSourceProvider.class) {
                        Class<?> mapperClass = getMapperClass(mappedStatement);
                        Class<?>[] generics = getMapperGenerics(mapperClass);
                        Class<?> primaryFieldClass = generics[0];
                        Class<?> modelClass = generics[1];
                        ResultMap resultMap = getResultMap(mappedStatement, modelClass);
                        String sqlScript = getSqlScript(mappedStatement, mapperClass, modelClass, primaryFieldClass, resultMap);
                        SqlSource sqlSource = createSqlSource(mappedStatement, sqlScript);
                        setSqlSource(mappedStatement, sqlSource);
                        String methodName = mappedStatement.getId().substring(mappedStatement.getId().lastIndexOf(".") + 1);
                        if (methodName.startsWith("selectBy")) {
                            setResultMap(mappedStatement, resultMap);
                        }
                    }
                }
            }
        }

        return "sql";
    }

    protected static SqlSource createSqlSource(MappedStatement mappedStatement, String script) {
        return languageDriver.createSqlSource(mappedStatement.getConfiguration(), "<script>" + script + "</script>", (Class)null);
    }

    protected static void setSqlSource(MappedStatement mappedStatement, SqlSource sqlSource) {
        if (sqlSource != null) {
            MetaObject metaObject = SystemMetaObject.forObject(mappedStatement);
            metaObject.setValue("sqlSource", sqlSource);
        }
    }

    protected static void setResultMap(MappedStatement mappedStatement, ResultMap resultMap) {
        if (resultMap != null) {
            MetaObject metaObject = SystemMetaObject.forObject(mappedStatement);
            metaObject.setValue("resultMaps", Collections.unmodifiableList(Collections.singletonList(resultMap)));
        }
    }

    protected static String selectById(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        String tableName = getTableName(modelClass);
        ResultMapping primaryColumnName = getPrimaryColumnMapping(resultMap);
        return primaryColumnName == null ? "INVALID_SQL" : String.format("SELECT * FROM %s WHERE %s = #{%s}", tableName, getColumnName(primaryColumnName), primaryColumnName.getProperty());
    }

    protected static String insertSelective(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        StringBuilder buf = new StringBuilder();
        String tableName = getTableName(modelClass);
        List<ResultMapping> fields = getModelField(modelClass, resultMap);
        buf.append(String.format("INSERT INTO %s", tableName));
        buf.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Iterator var8 = fields.iterator();

        ResultMapping resultMapping;
        while(var8.hasNext()) {
            resultMapping = (ResultMapping)var8.next();
            buf.append(String.format("<if test=\"%s != null\">", resultMapping.getProperty()));
            buf.append(String.format("%s,", getColumnName(resultMapping)));
            buf.append("</if>");
        }

        buf.append("</trim>");
        buf.append("VALUES");
        buf.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        var8 = fields.iterator();

        while(var8.hasNext()) {
            resultMapping = (ResultMapping)var8.next();
            buf.append(String.format("<if test=\"%s != null\">", resultMapping.getProperty()));
            buf.append(String.format("#{%s},", resultMapping.getProperty()));
            buf.append("</if>");
        }

        buf.append("</trim>");
        return buf.toString();
    }

    private static String getColumnName(ResultMapping resultMapping) {
        return useLowerCase ? resultMapping.getColumn().toLowerCase() : resultMapping.getColumn().toUpperCase();
    }

    protected static String insert(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        StringBuilder buf = new StringBuilder();
        String tableName = getTableName(modelClass);
        TypeMetaData typeMetaData = BeanTypeMetaDataCache.getMetaData(modelClass);
        List<SqlField> addFields = typeMetaData.getAddFields();
        buf.append(String.format("INSERT INTO %s", tableName));
        buf.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Iterator var9 = addFields.iterator();

        SqlField resultMapping;
        while(var9.hasNext()) {
            resultMapping = (SqlField)var9.next();
            buf.append(String.format("<if test=\"%s != null\">", resultMapping.getFieldName()));
            buf.append(String.format("%s,", getColumnName(resultMapping)));
            buf.append("</if>");
        }

        buf.append("</trim>");
        buf.append("VALUES");
        buf.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        var9 = addFields.iterator();

        while(var9.hasNext()) {
            resultMapping = (SqlField)var9.next();
            buf.append(String.format("<if test=\"%s != null\">", resultMapping.getFieldName()));
            buf.append(String.format("#{%s},", resultMapping.getFieldName()));
            buf.append("</if>");
        }

        buf.append("</trim>");
        return buf.toString();
    }

    private static String getColumnName(SqlField resultMapping) {
        return useLowerCase ? resultMapping.getColName().toLowerCase() : resultMapping.getColName().toUpperCase();
    }

    protected static String existsByDynamic(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        StringBuilder buf = new StringBuilder();
        String tableName = getTableName(modelClass);
        buf.append(String.format("SELECT count(1) FROM DUAL WHERE EXISTS(SELECT 1  FROM %s ", tableName));
        buf.append(String.format("<if test=\"_parameter != null\" >%s</if>)", staticSelectWhere));
        return buf.toString();
    }

    protected static String selectByDynamic(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        StringBuilder buf = new StringBuilder();
        String tableName = getTableName(modelClass);
        buf.append(String.format("SELECT *  FROM %s ", tableName));
        buf.append(String.format("<if test=\"_parameter != null\" >%s</if>", staticSelectWhere));
        return buf.toString();
    }

    protected static String updateByDynamic(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        StringBuilder buf = new StringBuilder();
        String tableName = getTableName(modelClass, "condition");
        List<ResultMapping> fields = getModelField(modelClass, resultMap);
        buf.append(String.format("UPDATE %s ", tableName));
        buf.append("<set>");
        Iterator var8 = fields.iterator();

        while(var8.hasNext()) {
            ResultMapping field = (ResultMapping)var8.next();
            buf.append(String.format("<if test=\"record.%s != null\">", field.getProperty()));
            buf.append(String.format("%s = #{record.%s},", getColumnName(field), field.getProperty()));
            buf.append("</if>");
        }

        buf.append("</set>");
        buf.append(String.format("<if test=\"_parameter != null\" >%s</if>", staticUpdateWhere));
        return buf.toString();
    }

    protected static String updateById(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        StringBuilder buf = new StringBuilder();
        String tableName = getTableName(modelClass);
        ResultMapping primaryColumnName = getPrimaryColumnMapping(resultMap);
        if (primaryColumnName == null) {
            return "INVALID_SQL";
        } else {
            List<ResultMapping> fields = getModelField(modelClass, resultMap);
            buf.append(String.format("UPDATE %s ", tableName));
            buf.append("<set>");
            Iterator var9 = fields.iterator();

            while(var9.hasNext()) {
                ResultMapping field = (ResultMapping)var9.next();
                if (!primaryColumnName.getProperty().equals(field.getProperty())) {
                    buf.append(String.format("<if test=\"%s != null\">", field.getProperty()));
                    buf.append(String.format("%s = #{%s},", getColumnName(field), field.getProperty()));
                    buf.append("</if>");
                }
            }

            buf.append("</set>");
            buf.append(String.format("WHERE %s=#{%s}", getColumnName(primaryColumnName), primaryColumnName.getProperty()));
            return buf.toString();
        }
    }

    protected static String deleteById(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        String tableName = getTableName(modelClass);
        ResultMapping primaryColumnName = getPrimaryColumnMapping(resultMap);
        return primaryColumnName == null ? "INVALID_SQL" : String.format("DELETE FROM %s WHERE %s = #{%s}", tableName, getColumnName(primaryColumnName), primaryColumnName.getProperty());
    }

    protected static String deleteByDynamic(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        StringBuilder buf = new StringBuilder();
        String tableName = getTableName(modelClass);
        buf.append(String.format("DELETE FROM %s ", tableName));
        buf.append(String.format("<if test=\"_parameter != null\" >%s</if>", staticSelectWhere));
        return buf.toString();
    }

    protected static String countByDynamic(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        StringBuilder buf = new StringBuilder();
        String tableName = getTableName(modelClass);
        buf.append(String.format("SELECT COUNT(1)  FROM %s ", tableName));
        buf.append(String.format("<if test=\"_parameter != null\" >%s</if>", staticSelectWhere));
        return buf.toString();
    }

    protected static Class<?> getProviderClass(MappedStatement mappedStatement) {
        try {
            Field providerTypeField = ProviderSqlSource.class.getDeclaredField("providerType");
            providerTypeField.setAccessible(true);
            Class<?> clazz = (Class)providerTypeField.get(mappedStatement.getSqlSource());
            return clazz;
        } catch (IllegalAccessException | NoSuchFieldException var3) {
            throw new RuntimeException(var3);
        }
    }

    protected static Class<?> getMapperClass(MappedStatement mappedStatement) {
        try {
            String mappedStatementId = mappedStatement.getId();
            String className = mappedStatementId.substring(0, mappedStatementId.lastIndexOf("."));
            return Class.forName(className);
        } catch (ClassNotFoundException var3) {
            throw new RuntimeException(var3);
        }
    }

    protected static String getSqlScript(MappedStatement mappedStatement, Class<?> mapperClass, Class<?> modelClass, Class<?> primaryFieldClass, ResultMap resultMap) {
        try {
            Method builderMethod = getBuilderMethod(mappedStatement, DynamicSqlSourceProvider.class, MappedStatement.class, Class.class, Class.class, Class.class, ResultMap.class);
            return builderMethod.invoke((Object)null, mappedStatement, mapperClass, modelClass, primaryFieldClass, resultMap).toString();
        } catch (InvocationTargetException | IllegalAccessException var6) {
            throw new RuntimeException(var6);
        }
    }

    protected static Method getBuilderMethod(MappedStatement mappedStatement, Class<?> builderClass, Class<?>... parameterTypes) {
        try {
            String mappedStatementId = mappedStatement.getId();
            String methodName = mappedStatementId.substring(mappedStatementId.lastIndexOf(".") + 1);
            return builderClass.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException var5) {
            throw new RuntimeException(var5);
        }
    }

    protected static ResultMap getResultMap(MappedStatement mappedStatement, Class<?> modelClass) {
        Configuration configuration = mappedStatement.getConfiguration();
        String baseResultMapId = mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf(".")) + ".BaseResultMap";
        if (configuration.getResultMapNames().contains(baseResultMapId)) {
            ResultMap resultMap = configuration.getResultMap(baseResultMapId);
            if (resultMap.getType().isAssignableFrom(modelClass)) {
                return resultMap;
            }
        }

        Iterator var12 = configuration.getResultMaps().iterator();

        while(var12.hasNext()) {
            Object rawMap = var12.next();
            ResultMap resultMap = rawMap instanceof ResultMap ? (ResultMap)rawMap : null;
            if (resultMap != null && modelClass == resultMap.getType() && !resultMap.getId().contains("-")) {
                return resultMap;
            }
        }

        List<ResultMapping> resultMappings = Lists.newArrayList();
        TypeMetaData typeMetaData = BeanTypeMetaDataCache.getMetaData(modelClass);
        List<SqlField> addFields = typeMetaData.getAddFields();
        List<SqlField> primaryFields = typeMetaData.getPrimaryKeys();
        Set<String> primaryKeySet = Sets.newHashSet();
        Iterator var9;
        SqlField sqlField;
        if (primaryFields != null) {
            var9 = primaryFields.iterator();

            while(var9.hasNext()) {
                sqlField = (SqlField)var9.next();
                primaryKeySet.add(getColumnName(sqlField));
            }
        }

        ResultMapping resultMapping;
        for(var9 = addFields.iterator(); var9.hasNext(); resultMappings.add(resultMapping)) {
            sqlField = (SqlField)var9.next();
            resultMapping = null;
            if (primaryKeySet.contains(getColumnName(sqlField))) {
                resultMapping = (new Builder(configuration, sqlField.getFieldName(), StringUtils.toUnderscore(sqlField.getFieldName()), sqlField.getColType())).flags(Collections.singletonList(ResultFlag.ID)).build();
            } else {
                resultMapping = (new Builder(configuration, sqlField.getFieldName(), StringUtils.toUnderscore(sqlField.getFieldName()), sqlField.getColType())).build();
            }
        }

        String id = mappedStatement.getId().substring(0, mappedStatement.getId().lastIndexOf("."));
        ResultMap resultMap = (new org.apache.ibatis.mapping.ResultMap.Builder(configuration, id + ".BaseResultMap", modelClass, resultMappings)).build();
        return resultMap;
    }

    protected static String getTableName(Class<?> modelClass) {
        return getTableName(modelClass, (String)null);
    }

    protected static String getTableName(Class<?> modelClass, String pre) {
        TableAware tableAware = (TableAware)modelClass.getAnnotation(TableAware.class);
        if (tableAware != null) {
            return tableAware.value();
        } else {
            TableName tableNameAnn = (TableName)modelClass.getAnnotation(TableName.class);
            return tableNameAnn != null ? tableNameAnn.value() : StringUtils.toUnderscore(modelClass.getSimpleName().replace("Entity", "")).toLowerCase();
        }
    }

    protected static String getPrimaryColumn(ResultMap resultMap) {
        ResultMapping resultMapping = getPrimaryColumnMapping(resultMap);
        return resultMapping == null ? null : getColumnName(resultMapping);
    }

    protected static ResultMapping getPrimaryColumnMapping(ResultMap resultMap) {
        if (resultMap != null && resultMap.getIdResultMappings().size() > 0) {
            List<ResultMapping> resultMappings = resultMap.getIdResultMappings();
            if (resultMappings.size() == 1) {
                return (ResultMapping)resultMappings.get(0);
            }

            Iterator var2 = resultMappings.iterator();

            while(var2.hasNext()) {
                ResultMapping mapping = (ResultMapping)var2.next();
                if (getColumnName(mapping).equalsIgnoreCase("ID")) {
                    return mapping;
                }
            }
        }

        return null;
    }

    protected static Class<?>[] getMapperGenerics(Class<?> mapperClass) {
        Type[] types = mapperClass.getGenericInterfaces();
        Type[] var2 = types;
        int var3 = types.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Type type = var2[var4];
            ParameterizedType parameterizedType = (ParameterizedType)type;
            if (parameterizedTypeMapperSet.contains(parameterizedType.getRawType())) {
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                Class<?>[] generics = new Class[typeArguments.length];

                for(int i = 0; i < typeArguments.length; ++i) {
                    generics[i] = (Class)typeArguments[i];
                }

                return generics;
            }
        }

        return null;
    }

    protected static List<ResultMapping> getModelField(Class<?> modelClass, ResultMap resultMap) {
        return resultMap.getResultMappings();
    }

    public static boolean isUseLowerCase() {
        return useLowerCase;
    }

    public static void setUseLowerCase(boolean useLowerCase) {
        DynamicSqlSourceProvider.useLowerCase = useLowerCase;
    }
}
