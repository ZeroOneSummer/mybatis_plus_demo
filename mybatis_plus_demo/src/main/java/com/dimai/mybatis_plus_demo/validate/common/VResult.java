package com.dimai.mybatis_plus_demo.validate.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by pijiang on 2019/10/28.
 */
@Data
@AllArgsConstructor
public class VResult {
    private String code;
    private String msg;

    public static VResult isOkVResult(){
        return new VResult(VType.OK.getCode(), VType.OK.getMsg());
    }
}