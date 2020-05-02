//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.dimai.mybatis_plus_demo.mybatis.biz;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

public abstract class BaseService {
    private static final int MAX_PAGE_SIZE = 10000;
    private static final int DEFAULT_PAGE_SIZE = 20;

    public BaseService() {
    }

    protected static final <T> T listToOne(List<T> list) {
        return CollectionUtils.isEmpty(list) ? null : list.get(0);
    }

    protected static final int pageIndex(int pageIndex) {
        return pageIndex <= 0 ? 1 : pageIndex;
    }

    protected static final int pageSize(int pageSize) {
        if (pageSize <= 0) {
            return 20;
        } else {
            return pageSize > 10000 ? 10000 : pageSize;
        }
    }

    public static final <T> T copyProperties(Object src, T des) {
        if (src == null) {
            return des;
        } else {
            BeanUtils.copyProperties(src, des);
            return des;
        }
    }

    public static final <T> T copyProperties(Object src, T des, String... ingnoreProperties) {
        if (src == null) {
            return des;
        } else {
            BeanUtils.copyProperties(src, des, ingnoreProperties);
            return des;
        }
    }
}
