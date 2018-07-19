package com.seabornlee.springboot.memberservice.config;

import com.github.pagehelper.PageHelper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "mybatis.pagehelper")
public class PageHelperConfig {

    private Map<String,String> properties;

    public Map<String, String> getProperties() {
        return properties;
    }

    public PageHelperConfig setProperties(Map<String, String> properties) {
        this.properties = properties;
        return this;
    }

    // page helper
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.putAll(properties);
        /*p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
        p.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(p);*/
        return pageHelper;
    }
}
