package com.dimai.mybatis_plus_demo;

import com.dimai.mybatis_plus_demo.dozer.test.SourceBean;
import com.dimai.mybatis_plus_demo.dozer.test.TargetBean;
import lombok.extern.slf4j.Slf4j;
import org.dozer.Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pijiang on 2019/11/4.
 */
@Slf4j
public class DozerTest extends BaseTest{

    @Autowired
    private Mapper dozerMapper;

    @Test
    public void test1(){
        SourceBean source = new SourceBean(1001L, 2002L,"不同属性名复制_注解",
                "不同属性名复制_配置", new Date(), "2018/12/02", "100.02", new BigDecimal("200.02"));
        TargetBean target = new TargetBean();
        dozerMapper.map(source, target);
        log.info("[{}]", source.toString());
        log.info("[{}]", target.toString());
    }
}