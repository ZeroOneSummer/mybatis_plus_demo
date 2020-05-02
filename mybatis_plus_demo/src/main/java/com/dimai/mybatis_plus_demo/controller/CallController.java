package com.dimai.mybatis_plus_demo.controller;

import com.alibaba.fastjson.JSON;
import com.dimai.mybatis_plus_demo.entity.User;

/**
 * Created by pijiang on 2019/9/19.
 */
public class CallController {

    protected void excuteAction(User user){
        System.out.println(JSON.toJSONString(user));
    }

}