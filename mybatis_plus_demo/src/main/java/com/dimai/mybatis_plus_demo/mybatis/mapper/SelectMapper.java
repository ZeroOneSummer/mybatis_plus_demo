//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dimai.mybatis_plus_demo.mybatis.mapper;

import com.dimai.mybatis_plus_demo.mybatis.common.DynamicSqlCondition;
import com.dimai.mybatis_plus_demo.mybatis.common.DynamicSqlSourceProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SelectMapper<PrimaryKey, Entity> {
    @SelectProvider(
        type = DynamicSqlSourceProvider.class,
        method = "build"
    )
    int countByDynamic(DynamicSqlCondition var1);

    @SelectProvider(
        type = DynamicSqlSourceProvider.class,
        method = "build"
    )
    List<Entity> selectByDynamic(DynamicSqlCondition var1);

    @SelectProvider(
        type = DynamicSqlSourceProvider.class,
        method = "build"
    )
    Entity selectById(PrimaryKey var1);

    @SelectProvider(
        type = DynamicSqlSourceProvider.class,
        method = "build"
    )
    boolean existsByDynamic(DynamicSqlCondition var1);
}
