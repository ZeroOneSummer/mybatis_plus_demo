package com.dimai.mybatis_plus_demo.validate.service;

import com.dimai.mybatis_plus_demo.validate.common.VResult;

import java.util.List;
import java.util.Map;

/**
 * Created by pijiang on 2019/10/28.
 */
public interface DemoService {
    List<VResult> validateData(PermStargy permStargy, Map<String, Object> params);
}