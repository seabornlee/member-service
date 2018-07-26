package com.seabornlee.springboot.memberservice.util.spider;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.seabornlee.springboot.memberservice.util.httpclient.HttpGetClient;
import com.seabornlee.springboot.memberservice.util.httpclient.HttpPostClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * e精灵数据抓取
 *
 * */
public class ESpiritSpider implements Spider{

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String username;
    private String password;
    private String loginUrl;

    public ESpiritSpider(String username, String password, String loginUrl) {
        this.username = username;
        this.password = password;
        this.loginUrl = loginUrl;
    }

    protected void login() throws IOException {

        logger.info("logging into " + loginUrl);
        HttpPostClient postClient = new HttpPostClient(loginUrl);
        postClient.addParam("username",username);
        postClient.addParam("password",password);
        postClient.addParam("browserCode","chrome");

        postClient.setHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        postClient.doPost();

        String content = postClient.getContent();

        logger.info("login successfully. Response: " + content);

        JSONObject result = JSON.parseObject(content);

        String redirect = result.getString("data");
        redirect = redirect.replaceAll("@@@","?JSESSIONID=");
        redirect = redirect.replaceAll("successfulUrl=/","successfulUrl=%2f");

        logger.info("redirect to " + redirect);

        HttpGetClient getClient = new HttpGetClient(redirect,true);
        getClient.doGet();

        logger.info("redirect successfully...");
    }


    @Override
    public Object getData(String url, HashMap<String, String> params, boolean isPost, boolean isHttps) {

        try {
            login();
        } catch (IOException e) {
            logger.error("An error occurred when login",e);
            return null;
        }

        String content = null;

        if(isPost){//

            HttpPostClient postClient = new HttpPostClient(url, params, true);

            try {
                postClient.doPost();

                content = postClient.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else{//get


            HttpGetClient getClient = new HttpGetClient(processGetUrl(url, params),true);

            getClient.doGet();

            content = getClient.getContent();
        }

        if(null!=content&&content.length()>0){

            Object object = JSON.parse(content);
            return object;

        }

        return null;
    }

    private String processGetUrl(String url, HashMap<String,String> params){

        if(null==params||params.isEmpty()){
            return url;
        }

        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String,String> param : params.entrySet()){
            sb.append("&").append(param.getKey()).append("=").append(param.getValue());
        }

        if(url.matches("([^?&=]+)=([^?&=]*)")){
            return url + sb.toString();
        }else if(url.indexOf('?')==url.length()-1){
            return url + sb.substring(1);
        }else {
            return url + "?" + sb.substring(1);
        }

    }
}
