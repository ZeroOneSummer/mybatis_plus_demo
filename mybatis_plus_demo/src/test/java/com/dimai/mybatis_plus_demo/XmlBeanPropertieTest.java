package com.dimai.mybatis_plus_demo;

import com.alibaba.fastjson.JSON;
import com.dimai.mybatis_plus_demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by pijiang on 2019/7/11.
 * spring依赖注入
 */
@Slf4j
public class XmlBeanPropertieTest extends BaseTest {

    @Test
    public void getApplicationContext1(){
        ApplicationContext cxt = new ClassPathXmlApplicationContext("demo_test.xml");
        User user = (User)cxt.getBean("xml.test.User");
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void getApplicationContext2(){
//        ApplicationContext cxt = new FileSystemXmlApplicationContext("classpath:demo_test.xml");
        ApplicationContext cxt = new FileSystemXmlApplicationContext("file:E:\\ideawork_git\\mybatis_plus_demo\\mybatis_plus_demo\\src\\main\\resources\\demo_test.xml");
        User user = (User)cxt.getBean("xml.test.User");
        System.out.println(JSON.toJSONString(user));
    }

    @Test
    public void getProperties() throws Exception{
        Properties properties = new Properties();
        FileInputStream in = new FileInputStream("E:\\ideawork_git\\mybatis_plus_demo\\mybatis_plus_demo\\src\\main\\resources\\application.yml");
        InputStreamReader isr = new InputStreamReader(in, Charset.forName("UTF-8"));
        properties.load(isr);
        String property = properties.getProperty("my.name");
        System.out.println(property);
    }

    @Test
    public void createProxy() throws Exception{
        //接口转换
        Class<User.Iface> ifaceClass = User.Iface.class;
        String interfaceClassName = ifaceClass.getName();
        String clientClassName = interfaceClassName.replace("$Iface", "$Client");
        Class<?> clientClass = Class.forName(clientClassName);

        System.out.println(clientClass);
    }

}