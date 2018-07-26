package com.seabornlee.springboot.memberservice.util.spider;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public interface Spider {

    public Object getData(String url, HashMap<String,String> params, boolean isPost, boolean isHttps);
}
