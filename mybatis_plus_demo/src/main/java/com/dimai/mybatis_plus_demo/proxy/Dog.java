package com.dimai.mybatis_plus_demo.proxy;

/**
 * Created by pijiang on 2019/7/15.
 */
public class Dog implements Animal {

    @Override
    public void eat(String food) {
        System.out.println("小狗狗在吃：" + food);
    }

    @Override
    public void show() {
        System.out.println("小狗狗吃饱了，欢乐的在草地上嗨皮");
    }

}