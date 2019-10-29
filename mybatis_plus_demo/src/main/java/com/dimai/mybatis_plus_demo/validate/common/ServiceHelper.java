package com.dimai.mybatis_plus_demo.validate.common;

import com.dimai.mybatis_plus_demo.validate.service.Validate;
import com.dimai.mybatis_plus_demo.validate.service.impl.Demo1Validate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by pijiang on 2019/10/28.
 */
public class ServiceHelper {

    public static  <T> T doProcess(Function<List<Validate>, T> fc){
        List<Validate> vs = getDefaultValidate();
        return fc.apply(vs);
    }

    static List<Validate> getDefaultValidate(){
        return Arrays.asList(new Demo1Validate());
    }
}