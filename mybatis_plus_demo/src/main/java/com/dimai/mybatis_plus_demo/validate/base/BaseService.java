package com.dimai.mybatis_plus_demo.validate.base;

import com.dimai.mybatis_plus_demo.validate.service.PermStargy;
import com.dimai.mybatis_plus_demo.validate.service.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pijiang on 2019/10/28.
 */
public class BaseService {
    public Map<String, PermStargy> stargyMap = new HashMap<>();

    public List<Validate> getValidate(String perm){
        return stargyMap.get(perm).getVList();
    }
}