package com.dimai.mybatis_plus_demo.validate.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by pijiang on 2019/10/28.
 */
@AllArgsConstructor
@Getter
public enum VType {
    OK("000", "OK"),
    PARAMS_IS_NOT_NULL("001", "参数不能为空"),
    SIZE__MORE_THAN_LIMIT("002", "长度超过最大限值");

    private String code;
    private String msg;
}