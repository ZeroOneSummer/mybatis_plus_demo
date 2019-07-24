package com.dimai.mybatis_plus_demo.test;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by pijiang on 2019/7/24.
 */
@Data
public class ProperClass {

    private String id;
    private String name;

    public static void main(String[] args) throws Exception{
        Class<ProperClass> clazz = ProperClass.class;
        Object obj = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields){
            String name = f.getName();

            //法一
            PropertyDescriptor pd = new PropertyDescriptor(name, clazz);
            Method writeMethod = pd.getWriteMethod();
            writeMethod.invoke(obj, "345");

            //法二
            char[] chars = name.toCharArray();
            chars[0]-=32;
            String setName = "set".concat(String.valueOf(chars));
            Method method = clazz.getDeclaredMethod(setName, f.getType());
            method.invoke(obj, "123");
        }
        System.out.println(JSON.toJSONString(obj));
    }

}