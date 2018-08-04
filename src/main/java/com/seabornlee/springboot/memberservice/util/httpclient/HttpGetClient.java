package com.seabornlee.springboot.memberservice.util.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class HttpGetClient {

    private Logger logger = LoggerFactory.getLogger(HttpGetClient.class);
    private String url;
    private int status = 0;
    private String content;
    private CloseableHttpClient httpClient = null;

    public HttpGetClient(String url) {
        this(url, false);
    }

    public HttpGetClient(String url, boolean ssl) {
        this.url = url;
        httpClient = ssl ? HttpClientSingletonFactory.getHttpsClient() : HttpClientSingletonFactory.getHttpClient();
    }

    public int getStatus() {
        return this.status;
    }

    public String getContent() {
        return content;
    }

    public boolean doGet(final String encoding) {

        HttpGet httpget = new HttpGet(this.url);

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(
                    final HttpResponse response) throws IOException {
                int stat = response.getStatusLine().getStatusCode();
                status = stat;
                if (stat >= 200 && stat < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity, encoding) : null;
                } else {
                    logger.debug("An error occurred, request url:" + url);
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };

        try {
            content = httpClient.execute(httpget, responseHandler);
        } catch (IOException e) {
            logger.debug("An error occurred, request url:" + url);
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean doGet() {
        return doGet("UTF-8");
    }

}
