package com.dimai.mybatis_plus_demo;

import com.alibaba.fastjson.JSON;
import com.dimai.mybatis_plus_demo.mybatis.biz.BizUser;
import com.dimai.mybatis_plus_demo.mybatis.biz.BizUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by pijiang on 2019/11/8.
 */
@Slf4j
public class MybatisDynamicTest extends BaseTest{

    @Autowired
    private BizUserService bizUserService;

    @Test
    public void getBizUser() {
        BizUser bizUser = bizUserService.getBizUser("lisa", 1001l);
        log.info(JSON.toJSONString(bizUser));
    }

    @Test
    public void updateBizUser() {
        BizUser bizUser = new BizUser();
        int num = bizUserService.updateBizUser(bizUser.setAge(18l).setName("webxu"));
        log.info(JSON.toJSONString(num));
    }
}