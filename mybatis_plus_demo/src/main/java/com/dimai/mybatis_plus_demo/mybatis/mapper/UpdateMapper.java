//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dimai.mybatis_plus_demo.mybatis.mapper;

import com.dimai.mybatis_plus_demo.mybatis.common.DynamicSqlCondition;
import com.dimai.mybatis_plus_demo.mybatis.common.DynamicSqlSourceProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

public interface UpdateMapper<PrimaryKey, Entity> {
    @DeleteProvider(
        type = DynamicSqlSourceProvider.class,
        method = "build"
    )
    int deleteByDynamic(DynamicSqlCondition var1);

    @InsertProvider(
        type = DynamicSqlSourceProvider.class,
        method = "build"
    )
    int insertSelective(Entity var1);

    @UpdateProvider(
        type = DynamicSqlSourceProvider.class,
        method = "build"
    )
    int updateByDynamic(@Param("record") Entity var1, @Param("condition") DynamicSqlCondition var2);

    @DeleteProvider(
        type = DynamicSqlSourceProvider.class,
        method = "build"
    )
    int deleteById(PrimaryKey var1);
}
