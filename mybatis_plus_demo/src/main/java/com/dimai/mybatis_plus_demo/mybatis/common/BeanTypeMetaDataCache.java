package com.dimai.mybatis_plus_demo.mybatis.common;

import com.dimai.mybatis_plus_demo.mybatis.exception.JarvisSqlProviderException;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class BeanTypeMetaDataCache {
    private static Cache<Class, TypeMetaData> metaDataCache = CacheBuilder.newBuilder().build();

    public BeanTypeMetaDataCache() {
    }

    public static TypeMetaData getMetaData(final Class beanType) {
        try {
            return (TypeMetaData)metaDataCache.get(beanType, new Callable<TypeMetaData>() {
                public TypeMetaData call() throws Exception {
                    return new TypeMetaData(beanType);
                }
            });
        } catch (ExecutionException var2) {
            throw new JarvisSqlProviderException(var2);
        }
    }

    public static String getDefaultTableName(Class beanType) {
        return getMetaData(beanType).getDefaultTableName();
    }
}