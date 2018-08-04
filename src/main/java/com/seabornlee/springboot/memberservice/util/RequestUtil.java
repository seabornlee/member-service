package com.seabornlee.springboot.memberservice.util;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    public static String getIp(HttpServletRequest request){
        return "";
    }

    public static String getUrl(HttpServletRequest request){
        StringBuilder sb = new StringBuilder();
        sb.append(request.getScheme()).append("://")
                .append(request.getServerName());
        if(request.getServerPort()!=80&&request.getServerPort()!=443){
            sb.append(":").append(request.getServerPort());
        }
        sb.append(request.getRequestURI());
        return sb.toString();
    }
}
