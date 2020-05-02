package com.dimai.mybatis_plus_demo.validate.service.impl;

import com.dimai.mybatis_plus_demo.validate.common.VResult;
import com.dimai.mybatis_plus_demo.validate.common.VType;
import com.dimai.mybatis_plus_demo.validate.service.Validate;

import java.util.Map;

/**
 * Created by pijiang on 2019/10/28.
 */
public class Demo2Validate implements Validate {
    public static Demo2Validate instance = new Demo2Validate();

    @Override
    public VResult doValidate(Map<String, Object> params) {
        if (params.size() > 3){
            return new VResult(VType.SIZE__MORE_THAN_LIMIT.getCode(), VType.SIZE__MORE_THAN_LIMIT.getMsg());
        }
        return VResult.isOkVResult();
    }
}