package com.dimai.mybatis_plus_demo.mybatis.biz;

import com.dimai.mybatis_plus_demo.mybatis.common.DynamicSqlCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Component
public class BizUserService extends BaseService {

    @Autowired
    private BizUserMapper bizUserMapper;

    public BizUser getBizUser(String userName, Long id) {
        Assert.hasText(userName, "userName required");

        DynamicSqlCondition condition = new DynamicSqlCondition();
        condition.createCriteria()
                .andEqualTo("id", id);
        List<BizUser> list = bizUserMapper.selectByDynamic(condition);
        return listToOne(list);
    }

    public int updateBizUser(BizUser bizUser) {
        Assert.notNull(bizUser, "bizUser required");
        Assert.hasText(bizUser.getName(), "userName required");

        DynamicSqlCondition condition = new DynamicSqlCondition();
        condition.createCriteria()
                .andEqualTo("biz_user", bizUser.getName());
        return bizUserMapper.updateByDynamic(bizUser.setName(null), condition);
    }
}