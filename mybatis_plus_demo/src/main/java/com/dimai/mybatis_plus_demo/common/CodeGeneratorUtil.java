package com.dimai.mybatis_plus_demo.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * mybatis-plus 代码自动生成工具类
 */
public class CodeGeneratorUtil {

    public static void main(String[] args) {
        // 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "/src/main/java")
                    .setFileOverride(true)
                    .setServiceName("%sService")
                    .setAuthor("z.o.s")
                    .setOpen(false);

        // 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.ORACLE)
                        .setUrl("jdbc:oracle:thin:@127.0.0.1:1521/ORCL")
                        .setDriverName("oracle.jdbc.driver.OracleDriver")
                        .setUsername("system")
                        .setPassword("system");

        // 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel)
                      .setColumnNaming(NamingStrategy.underline_to_camel)
                      .setEntityLombokModel(true)
                      .setEntityTableFieldAnnotationEnable(false)
                      .setRestControllerStyle(true)
                //    .setTablePrefix(new String[]{"TEST_"})
                //    .setExclude(new String[]{"test"})
                      .setInclude(new String[] {"USER"});   //表名必须大写

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.dimai.mybatis_plus_demo")
                     .setXml("mapper");

        // 代码生成器
        AutoGenerator generator = new AutoGenerator();
        generator.setGlobalConfig(globalConfig)
                 .setDataSource(dataSourceConfig)
                 .setStrategy(strategyConfig)
                 .setPackageInfo(packageConfig)
                 .execute();
    }

}