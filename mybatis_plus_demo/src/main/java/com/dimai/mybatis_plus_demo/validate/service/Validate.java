package com.dimai.mybatis_plus_demo.validate.service;

import com.dimai.mybatis_plus_demo.validate.common.VResult;

import java.util.Map;

public interface Validate {
    VResult doValidate(Map<String, Object> params);
}
