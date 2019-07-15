package com.dimai.mybatis_plus_demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by pijiang on 2019/7/15.
 */
public class ProxyClientHandler implements InvocationHandler {

    private Animal animal;

    public ProxyClientHandler(Animal animal) {
        this.animal = animal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理方法前处理。。。");
        System.out.println("代理方法名：" + method);
        method.invoke(animal, args);
        System.out.println("代理方法后处理。。。");
        System.out.println("------------------华丽的分割线------------------");
        return null;
    }

    //测试
    public static void main(String[] args) throws Exception{
        Class clazz = Dog.class;
        Animal a = (Animal)Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), new ProxyClientHandler((Dog)clazz.newInstance()));
        a.eat("骨头");
        a.show();
    }

}