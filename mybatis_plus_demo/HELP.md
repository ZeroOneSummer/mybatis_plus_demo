# mybatis-plus 基友搭配 效率翻倍

【MybatisPlus】
--------------------------------------------------------------------------------
官方文档：https://mp.baomidou.com/
1.依赖（自动代码生成只需要3个依赖即可：mybatis-plus-generato、velocity、ojdbc6）
<!-- mybatis-plus -->
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.1.1</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus</artifactId>
    <version>3.1.1</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.1.1</version>
</dependency>
<!-- 模板引擎 代码生成 -->
<dependency>
    <groupId>org.apache.velocity</groupId>
    <artifactId>velocity</artifactId>
    <version>1.7</version>
</dependency>
<!-- oracle-->
<dependency>
    <groupId>com.oracle</groupId>
    <artifactId>ojdbc6</artifactId>
    <version>11.1.0.6.0</version>
</dependency>

2.代码生成工具类
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
            //    strategyConfig.setRestControllerStyle(true)
            //    strategyConfig.setTablePrefix(new String[]{"TEST_"})
            //    .setExclude(new String[]{"test"})
                  .setInclude(new String[] {"USER"});   //表面必须大写

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

3.yml配置
# mybatis-plus
mybatis-plus:
    mapper-locations: classpath:/mapper/*.xml
    typeAliasesPackage: com.dimai.mybatis_plus_demo.entity
    global-config:
        #主键类型  0:"数据库ID自增", 1:"用户输入ID",2:"全局唯一ID (数字类型唯一ID)", 3:"全局唯一ID UUID";
        id-type: 0
        #字段策略 0:"忽略判断",1:"非 NULL 判断"),2:"非空判断"
        field-strategy: 2
        #驼峰下划线转换
        db-column-underline: true
        #刷新mapper 调试神器
        refresh-mapper: true
        #数据库大写下划线转换
        capital-mode: true
        #序列接口实现类配置
        #key-generator: com.baomidou.springboot.xxx
        #逻辑删除配置
        logic-delete-value: 0
        logic-not-delete-value: 1
        #自定义填充策略接口实现
        #meta-object-handler: com.baomidou.springboot.MyMetaObjectHandler
        #自定义SQL注入器
        #sql-injector: com.baomidou.springboot.xxx
    configuration:
        map-underscore-to-camel-case: true
        cache-enabled: false

4.mybatis-plus分页查询时，要得到准确的分页数据需配置
@Bean
public PaginationInterceptor paginationInterceptor() {
    return new PaginationInterceptor();
}

--------------------------------------------------------------------------------
【H2内存数据库】
使用教程：https://www.cnblogs.com/langtianya/p/3807639.html
1.sql脚本准备
resource目录下创建：db目录，放文件 table.sql（建表语句）和 data.sql（数据语句）

2.依赖
<!-- db-h2 -->
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>

3.配置
spring:
    datasource:
        driver-class-name: org.h2.Driver
# 初始化时配置
# schema: classpath:db/table-h2.sql
# data: classpath:db/data-h2.sql
        url: jdbc:h2:~/test;AUTO_SERVER=TRUE        # ~代表用户根目录，自动混合模式（允许多个客户端访问）
        username: admin
        password: admin
    h2:
        console:
            path: /h2               # http://localhost:8080/h2 访问DB管理界面
            enabled: true

4.访问管理界面
 http://localhost:8080/h2
用户名/密码：admin/admin

--------------------------------------------------------------------------------
【junit断言】
assertArrayEquals(expecteds, actuals)   查看两个数组是否相等。
assertEquals(expected, actual)                查看两个对象是否相等。类似于字符串比较使用的equals()方法
assertNotEquals(first, second)                 查看两个对象是否不相等。
assertNull(object)                                     查看对象是否为空。
assertNotNull(object)                                查看对象是否不为空。
assertSame(expected, actual)                  查看两个对象的引用是否相等。类似于使用“==”比较两个对象
assertNotSame(unexpected, actual)         查看两个对象的引用是否不相等。类似于使用“!=”比较两个对象
assertTrue(condition)                                 查看运行结果是否为true。
assertFalse(condition)                               查看运行结果是否为false。
assertThat(actual, matcher)                       查看实际值是否满足指定的条件
fail()                                                            让测试失败



