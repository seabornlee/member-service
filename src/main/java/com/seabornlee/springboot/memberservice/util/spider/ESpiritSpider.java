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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * e精灵数据抓取
 */
public class ESpiritSpider implements Spider {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static Pattern domainPattern = Pattern.compile(
            "^((http://)|(https://))?(?<domain>([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,6})");

    private String username;
    private String password;
    private String loginUrl;
    private final static CopyOnWriteArrayList<String> loginDomains = new CopyOnWriteArrayList<String>();

    public ESpiritSpider(String username, String password, String loginUrl) {
        this.username = username;
        this.password = password;
        this.loginUrl = loginUrl;
    }

    protected String login(String domain) throws IOException {

        logger.info("logging into " + loginUrl);
        HttpPostClient postClient = new HttpPostClient(loginUrl);
        postClient.addParam("username", username);
        postClient.addParam("password", password);
        postClient.addParam("browserCode", "chrome");

        postClient.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        postClient.doPost();

        String content = postClient.getContent();

        logger.info("login successfully. Response: " + content);

        JSONObject result = JSON.parseObject(content);

        String status = result.getString("status");
        if ("Successful".equalsIgnoreCase(status)) {
            String redirect = result.getString("data");
            redirect = redirect.replaceAll("@@@", "?JSESSIONID=");
            redirect = redirect.replaceAll("successfulUrl=/", "successfulUrl=%2f");

            logger.info("redirect to " + redirect);

            HttpGetClient getClient = new HttpGetClient(redirect, true);
            getClient.doGet();

            String old = getDomain(redirect);
            loginDomains.addIfAbsent(old);
            String newDomain = getDomain(domain);
            loginDomains.addIfAbsent(newDomain);
            if (!newDomain.equals(old)) {
                String redirectUrl = redirect.replace(old, newDomain);
                getClient = new HttpGetClient(redirectUrl, true);
                getClient.doGet();
            }

            logger.info("redirect successfully...");
            return old;
        } else if ("send_again".equalsIgnoreCase(status) && loginDomains.size() > 0) {
            return loginDomains.get(0);
        } else {
            throw new IllegalStateException("Failed to login. url:" + loginUrl);
        }

    }

    public String getDomain(String url) {

        Matcher matcher = domainPattern.matcher(url);
        if (matcher.find()) {
            return matcher.group("domain");
        }
        return null;
    }

    @Override
    public Object getData(String url, Map<String, String> params, boolean isPost, boolean isHttps) {

        String domain = null;
        try {
            domain = login(url);
        } catch (IOException e) {
            logger.error("An error occurred when login", e);
            return null;
        }

        if (null != domain) {
            String newDomain = getDomain(url);
            if (!newDomain.equals(domain)) {
                url = url.replaceAll(newDomain, domain);
            }
        }

        String content = null;

        if (isPost) {//

            HttpPostClient postClient = new HttpPostClient(url, new HashMap<>(params), true);

            try {
                postClient.doPost();

                content = postClient.getContent();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {//get

            HttpGetClient getClient = new HttpGetClient(processGetUrl(url, params), true);

            getClient.doGet();

            content = getClient.getContent();
        }

        if (null != content && content.length() > 0) {

            Object object = JSON.parse(content);
            return object;

        }

        return null;
    }

    private String processGetUrl(String url, Map<String, String> params) {

        if (null == params || params.isEmpty()) {
            return url;
        }

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> param : params.entrySet()) {
            sb.append("&").append(param.getKey()).append("=").append(param.getValue());
        }

        if (url.matches("([^?&=]+)=([^?&=]*)")) {
            return url + sb.toString();
        } else if (url.indexOf('?') == url.length() - 1) {
            return url + sb.substring(1);
        } else {
            return url + "?" + sb.substring(1);
        }

    }
}
