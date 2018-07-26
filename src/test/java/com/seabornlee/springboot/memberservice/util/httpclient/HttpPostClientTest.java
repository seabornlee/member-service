package com.seabornlee.springboot.memberservice.util.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HttpPostClientTest {

    @Test
    public void doPost() throws IOException {
        HttpPostClient postClient = new HttpPostClient("http://www.runscm.com/user_login");
        postClient.addParam("username","potato");
        postClient.addParam("password","potato-plan");
        postClient.addParam("browserCode","chrome");

        postClient.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        postClient.doPost();

        String content = postClient.getContent();

        System.out.println(content);
        System.out.println("------------");

        JSONObject result = JSON.parseObject(content);

        String redirect = result.getString("data");
        redirect = redirect.replaceAll("@@@","?JSESSIONID=");
        redirect = redirect.replaceAll("successfulUrl=/","successfulUrl=%2f");

        System.out.println(redirect);

        HttpGetClient getClient = new HttpGetClient(redirect,true);
        getClient.doGet();
        System.out.println(getClient.getContent());
        System.out.println("------------");

        getClient = new HttpGetClient("https://hz1.ejlerp.com/warehouse/list",true);
        getClient.doGet();
        System.out.println(getClient.getContent());
    }
}