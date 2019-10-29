package com.dimai.mybatis_plus_demo.validate.service.impl;

import com.dimai.mybatis_plus_demo.validate.base.BaseService;
import com.dimai.mybatis_plus_demo.validate.common.PermConstant;
import com.dimai.mybatis_plus_demo.validate.common.ServiceHelper;
import com.dimai.mybatis_plus_demo.validate.common.VResult;
import com.dimai.mybatis_plus_demo.validate.service.DemoService;
import com.dimai.mybatis_plus_demo.validate.service.PermStargy;
import com.dimai.mybatis_plus_demo.validate.service.Validate;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pijiang on 2019/10/28.
 */
@Service
public class DemoServiceImpl extends BaseService implements DemoService {

    @PostConstruct
    void initStargyList(){
        stargyMap.put(PermConstant.QUERY_ALL, Demo1PermStargy.instance);
        stargyMap.put(PermConstant.ADD_ALL, Demo2PermStargy.instance);
    }

    @Override
    public List<VResult> validateData(PermStargy permStargy, Map<String, Object> params) {
        List<Validate> validate = getValidate(permStargy.getPerm());
        List<VResult> results = new ArrayList<>();

        return ServiceHelper.doProcess(vs -> {
            if (CollectionUtils.isEmpty(vs)){
                vs.stream().forEach(v -> {
                    VResult vr = v.doValidate(params);
                    results.add(vr);
                });
            }else {
                validate.stream().forEach(v -> {
                    VResult vr = v.doValidate(params);
                    results.add(vr);
                });
            }
            return results;
        });
    }
}