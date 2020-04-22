package com.dimai.mybatis_plus_demo;

import org.junit.Test;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by pijiang on 2020/4/22.
 */
public class I18nTest extends BaseTest{

    @Test
    public void i18nTest(){
        //英文
        Locale locale1 = new Locale("en_US");
        ResourceBundle rb1 = ResourceBundle.getBundle("i18n.message", locale1);
        System.out.println("英文："+ rb1.getString("greeting.morning") + ", " + rb1.getString("greeting.afternoon"));

        //中文
        Locale locale2 = Locale.getDefault();
        ResourceBundle rb2 = ResourceBundle.getBundle("i18n.message", locale2);
        System.out.println("中文："+ rb2.getString("greeting.morning") + ", " + rb2.getString("greeting.afternoon"));
    }
}