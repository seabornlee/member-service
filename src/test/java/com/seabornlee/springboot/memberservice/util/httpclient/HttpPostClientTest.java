package com.seabornlee.springboot.memberservice.util.httpclient;

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

        String redirect = content.replaceAll("@@@","?JSESSIONID=");

        HttpGetClient getClient = new HttpGetClient(redirect);
        getClient.doGet();
        System.out.println(getClient.getContent());
        System.out.println("------------");

        getClient = new HttpGetClient("https://hz1.ejlerp.com/skuQuery/list");
        getClient.doGet();
        System.out.println(getClient.getContent());
    }
}