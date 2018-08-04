package com.seabornlee.springboot.memberservice.util.spider;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public interface Spider {

    public Object getData(String url, Map<String,String> params, boolean isPost, boolean isHttps);
}
