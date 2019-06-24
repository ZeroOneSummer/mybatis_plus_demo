package com.dimai.mybatis_plus_demo.controller;


import com.alibaba.fastjson.JSON;
import com.dimai.mybatis_plus_demo.entity.User;
import com.dimai.mybatis_plus_demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author z.o.s
 * @since 2019-06-20
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/getUser")
    public User getUserById(@RequestBody User u){
        log.info("查询用户ID：{}", JSON.toJSONString(u));
        User user = userMapper.queryLast();
        return user;
    }

}


