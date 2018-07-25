package com.seabornlee.springboot.memberservice.util.httpclient;

import com.netease.product.util.json.FastjsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * Created by liuxuhui on 2015/2/27.
 */
public class HttpPostClient {

    private Logger logger = LogManager.getLogger(HttpPostClient.class);
    private String url;
    private int status = 0;
    private String content;
    private CloseableHttpClient httpClient = null;
    private IdentityHashMap<String,String> params = new IdentityHashMap<String,String>();
    private HashMap<String,String> headers = new HashMap<String,String>();

    public HttpPostClient(String url){
        this.url = url;
        httpClient = HttpClientSingletonFactory.getHttpClient();
    }

    public HttpPostClient(String url, HashMap<String,String> params){
        this.url = url;
        httpClient = HttpClientSingletonFactory.getHttpClient();
        if(null!=params){
            this.params.putAll(params);
        }
    }

    public int getStatus(){
        return this.status;
    }

    public String getContent(){
        return content;
    }

    public void addParam(String key, String value){
        params.put(key,value);
    }

    public void addParams(IdentityHashMap<String,String> params){
        if(null!=params){
            this.params.putAll(params);
        }
    }

    public void setHeader(String key, String value){
        headers.put(key,value);
    }

    public void setHeaders(HashMap<String,String> params){
        if(null!=params){
            this.headers.putAll(params);
        }
    }

    private List<NameValuePair> getParams(){
        List<NameValuePair> result = null;
        if(null!=params){
            result = new ArrayList<NameValuePair>();
            for(String key : params.keySet()){
                BasicNameValuePair pair = new BasicNameValuePair(key,params.get(key));
                result.add(pair);
            }
        }

        return result;
    }

    public boolean doPost(final String encoding) throws IOException {

        HttpPost httpPost = new HttpPost(this.url);

        if(headers!=null&&headers.size()>0){
            for (String key:headers.keySet()){
                httpPost.setHeader(key, headers.get(key));
            }
        }

        final List<NameValuePair> nvps = this.getParams();
        if(null!=nvps&&nvps.size()>0){
            httpPost.setEntity(new UrlEncodedFormEntity(nvps,encoding));
        }

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
                    logger.debug("An error occurred,request url:"+url+"\n request body:"+ FastjsonUtil.toJSONString(nvps));
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };

        try {
            content = httpClient.execute(httpPost, responseHandler);
        } catch (IOException e) {
            logger.debug("An error occurred,request url:"+url+"\n request body:"+ FastjsonUtil.toJSONString(nvps));
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public boolean doPost() throws IOException {
        return doPost("UTF-8");
    }
}
