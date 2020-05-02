package com.dimai.mybatis_plus_demo.dozer.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.dozer.Mapping;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pijiang on 2019/11/5.
 */
@Data
@AllArgsConstructor
public class SourceBean {
    @Mapping("pk")
    private Long id;    //不同类型、不同名称（注解式、配置式）
    private Long ids;   //不同类型、同名称
    @Mapping("binaryData")
    private String data;  //同类型、不同名称（注解式）
    private String data2;  //同类型、不同名称（配置式）
    private Date time; //不同类型、日期格式
    private String timeStr; //不同类型、日期格式
    private String mount;
    private BigDecimal mount2;
}