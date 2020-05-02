package com.dimai.mybatis_plus_demo;

import com.alibaba.fastjson.JSON;
import com.dimai.mybatis_plus_demo.validate.common.VResult;
import com.dimai.mybatis_plus_demo.validate.service.DemoService;
import com.dimai.mybatis_plus_demo.validate.service.PermStargy;
import com.dimai.mybatis_plus_demo.validate.service.impl.Demo1PermStargy;
import com.dimai.mybatis_plus_demo.validate.service.impl.Demo2PermStargy;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pijiang on 2019/10/29.
 */
public class ValidateTest extends BaseTest{

    @Autowired
    DemoService demoService;

    @Test
    public void doValidate(){
        PermStargy demo1 = new Demo1PermStargy();  //按策略1校验（非空）
        PermStargy demo2 = new Demo2PermStargy();  //按策略2校验（非空+长度）
        Map<String, Object> params1 = new HashMap<>();
        Map<String, Object> params2 = new HashMap<String, Object>(){{
            put("no_1", "val_1");
            put("no_2", "val_2");
            put("no_3", "val_3");
            put("no_4", "val_4");
        }};

        List<VResult> results1 = demoService.validateData(demo1, params1);
        List<VResult> results2 = demoService.validateData(demo2, params2);
        System.out.println(JSON.toJSONString(results1));
        System.out.println(JSON.toJSONString(results2));
    }
}