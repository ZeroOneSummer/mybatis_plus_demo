package com.dimai.mybatis_plus_demo.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
 * Created by pijiang on 2019/7/24.
 */
public class PropertiesFile {

    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        String filePath = "E:\\ideawork_git\\mybatis_plus_demo\\mybatis_plus_demo\\src\\main\\resources\\application.yml";
        properties.load(new InputStreamReader(new FileInputStream(filePath), Charset.forName("UTF-8")));
        System.out.println(properties.getProperty("my.name"));
    }

}