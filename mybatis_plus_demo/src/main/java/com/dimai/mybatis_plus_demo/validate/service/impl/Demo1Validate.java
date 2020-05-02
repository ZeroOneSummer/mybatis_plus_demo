package com.dimai.mybatis_plus_demo.validate.service.impl;

import com.dimai.mybatis_plus_demo.validate.common.VResult;
import com.dimai.mybatis_plus_demo.validate.common.VType;
import com.dimai.mybatis_plus_demo.validate.service.Validate;

import java.util.Map;

/**
 * Created by pijiang on 2019/10/28.
 */
public class Demo1Validate implements Validate {
    public static Demo1Validate instance = new Demo1Validate();

    @Override
    public VResult doValidate(Map<String, Object> params) {
        if (params.isEmpty()){
            return new VResult(VType.PARAMS_IS_NOT_NULL.getCode(), VType.PARAMS_IS_NOT_NULL.getMsg());
        }
        return VResult.isOkVResult();
    }
}