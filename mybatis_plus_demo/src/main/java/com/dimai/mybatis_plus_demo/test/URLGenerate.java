package com.dimai.mybatis_plus_demo.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by pijiang on 2019/8/22.
 * URL拼接
 */
public class URLGenerate {

    public static void main(String[] args) {
        List list = new ArrayList();
        String nonce = UUID.randomUUID().toString().replace("-","");
        String timeStamp = String.valueOf(System.currentTimeMillis()/1000L);
        String sig = DigestUtils.md5DigestAsHex(JSON.toJSONString(list).getBytes());
        String appSecrt = "zerodaydayone";

        Map<String, String> map = new HashMap<String, String>(){{
            put("nonce", nonce);
            put("timeStamp", timeStamp);
            put("sig", sig);
        }};
        List<Map.Entry<String, String>> entryList = new ArrayList<>(map.entrySet());

        Collections.sort(entryList, (o1, o2) -> o1.getKey().compareTo(o2.getKey()));
        String params = entryList.stream().map(m -> {
            try {
                return String.format("&%s=%s", m.getKey(), URLEncoder.encode(m.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                throw new RuntimeException("URLEncoder error.");
            }
        }).collect(Collectors.joining()).substring(1).concat("&key="+appSecrt);

        if (!StringUtils.isBlank(params)) params = "?".concat(params);
        StringBuffer baseURL = new StringBuffer("http://localhost:8080/dimai/getUser");
        String url = baseURL.append(params).toString().trim();

        System.out.println(url);
    }
}