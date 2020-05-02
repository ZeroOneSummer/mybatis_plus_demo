package com.dimai.mybatis_plus_demo.validate.service.impl;

import com.dimai.mybatis_plus_demo.validate.common.PermConstant;
import com.dimai.mybatis_plus_demo.validate.service.PermStargy;
import com.dimai.mybatis_plus_demo.validate.service.Validate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by pijiang on 2019/10/28.
 */
public class Demo2PermStargy implements PermStargy {
    public static Demo2PermStargy instance = new Demo2PermStargy();

    @Override
    public String getPerm() {
        return PermConstant.ADD_ALL;
    }


    @Override
    public List<Validate> getVList() {
        return Arrays.asList(Demo1Validate.instance,
                             Demo2Validate.instance);
    }
}