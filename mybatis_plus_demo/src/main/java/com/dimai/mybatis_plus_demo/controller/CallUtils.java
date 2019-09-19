package com.dimai.mybatis_plus_demo.controller;

import com.dimai.mybatis_plus_demo.entity.User;

import java.lang.reflect.Method;

/**
 * Created by pijiang on 2019/9/19.
 */
public class CallUtils {

    public static <T> void excuteProess(String methodName, Class<T> beanClass){
        try {
            T bean = beanClass.newInstance();
            Method method = null;

            for(Class<?> clazz = bean.getClass(); clazz != Object.class; clazz = clazz.getSuperclass()){
                try {
                    method = beanClass.getDeclaredMethod(methodName, User.class);//方法，参数
                    method.setAccessible(true);
                    break;
                } catch (Exception e) {
                    //这里什么都不做！递归找父类中的方法
                }
            }

            if (method == null) throw new RuntimeException("未获取到任务执行方法");

            User params = new User();
            params.setId(1001L);
            params.setName("Lilan_Mama");

            //方法调用
            method.invoke(bean, params);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}