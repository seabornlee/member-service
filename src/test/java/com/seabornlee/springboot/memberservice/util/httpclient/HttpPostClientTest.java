package com.seabornlee.springboot.memberservice.util.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.Assert.*;

public class HttpPostClientTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void doPost() throws IOException {
        HttpPostClient postClient = new HttpPostClient("http://www.runscm.com/user_login");
        postClient.addParam("username","potato");
        postClient.addParam("password","potato-plan");
        postClient.addParam("browserCode","chrome");

        postClient.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        postClient.doPost();

        String content = postClient.getContent();

        assertTrue(null!=content);

        logger.info(content);
        logger.info("------------");

        JSONObject result = JSON.parseObject(content);

        String redirect = result.getString("data");
        redirect = redirect.replaceAll("@@@","?JSESSIONID=");
        redirect = redirect.replaceAll("successfulUrl=/","successfulUrl=%2f");

        logger.info(redirect);

        HttpGetClient getClient = new HttpGetClient(redirect,true);
        getClient.doGet();
        content = getClient.getContent();
        assertTrue(null!=content);
        logger.info(content);
        logger.info("------------");

        getClient = new HttpGetClient("https://hz1.ejlerp.com/warehouse/list",true);
        getClient.doGet();
        content = getClient.getContent();
        assertTrue(null!=content);
        logger.info(content);
    }
}