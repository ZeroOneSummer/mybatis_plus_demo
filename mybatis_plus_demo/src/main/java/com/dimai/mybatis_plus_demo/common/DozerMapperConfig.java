package com.dimai.mybatis_plus_demo.common;

import com.dimai.mybatis_plus_demo.dozer.test.SourceBean;
import com.dimai.mybatis_plus_demo.dozer.test.TargetBean;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerMapperConfig {

    @Bean
    public Mapper dozerMapper(){
        Mapper mapper = DozerBeanMapperBuilder.create()
                //指定 dozer mapping 的配置文件(放到 resources 类路径下即可)，可添加多个 xml 文件，用逗号隔开
                .withMappingFiles("dozerBeanMapping.xml")
                .withMappingBuilder(beanMappingBuilder())
                .build();
        return mapper;
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                // 个性化配置添加在此
                mapping(SourceBean.class, TargetBean.class)
                        .fields("data2", "binaryData2");  //同在源对象加注解@Mapping("binaryData")类似：不同名、不同类型
            }
        };
    }
}