package com.dimai.mybatis_plus_demo.dozer.test;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by pijiang on 2019/11/5.
 */
@Data
public class TargetBean {
    private String pk;
    private String ids;
    private String binaryData;
    private String binaryData2;
    private String time;
    private Date timeStr;
    private BigDecimal mount;
    private String mount2;
}