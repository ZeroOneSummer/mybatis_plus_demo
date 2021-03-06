package com.dimai.mybatis_plus_demo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dimai.mybatis_plus_demo.entity.User;
import com.dimai.mybatis_plus_demo.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * Created by pijiang on 2019/6/20.
 */
@Slf4j
public class UserInterfaceTest extends BaseTest{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getUser(){
        //controller测试
        try {
            User user = new User().setId(1001L).setName("lisi");
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/getUser")
                    .accept(MediaType.APPLICATION_JSON_UTF8)
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(JSON.toJSONString(user)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(MockMvcResultHandlers.log())
                    .andReturn();
            Assert.assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void selectTest(){
        //无实体查询
        List<Map<String, Object>> maps = userMapper.selectMaps(new QueryWrapper<>());
        log.info("用户列表：{}", maps);
        //分页条件查询
        IPage<User> iPage = userMapper.selectPage(new Page<>(1, 3),
                new QueryWrapper<User>().between("age", 18,50));
        log.info("当前页{}|每页{}条|总记录数{}|共{}页", iPage.getCurrent(), iPage.getSize(), iPage.getTotal(), iPage.getPages());
        iPage.getRecords().forEach(user -> {
            log.info("用户：{}", JSON.toJSONString(user));
        });
    }

    @Test
    public void updateTest(){
        log.info("根据ID更新用户ID：{}", 1);
        userMapper.updateById(new User().setId(1L)
                                        .setName("KKK"));
        log.info("根据条件更新用户ID：{}", 1);
        userMapper.update(new User().setName("OOO"), new UpdateWrapper<User>().eq("id", 1));
    }

    @Test
    public void InsertTest(){
        log.info("新增用户ID：{}", "自增");
        userMapper.insert(new User().setName("PPP").setAge(40L).setEmail("123456@qq.com"));
        log.info("查询结果：");
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        users.forEach(u -> {
            log.info("用户：{}", JSON.toJSONString(u));
        });
    }

    @Test
    public void delTest(){
        User user = userMapper.queryLast();
        log.info("删除最后一个用户ID:{}", user.getId());
        userMapper.deleteById(user.getId());
        log.info("查询结果：");
        List<User> users = userMapper.selectList(new QueryWrapper<>());
        users.forEach(u -> {
            log.info("用户：{}", JSON.toJSONString(u));
        });
    }

}