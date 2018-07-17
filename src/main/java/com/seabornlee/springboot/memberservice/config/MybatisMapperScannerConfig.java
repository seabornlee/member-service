package com.seabornlee.springboot.memberservice.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class MybatisMapperScannerConfig {

    private final static String MAPPER_SCAN_PACKAGE = "com.flyingwillow.xxx.mapper";

    @Value("${mybatis.mapperScanPackage}")
    private String mapperScanPackage;

    @Bean
    public static MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(MAPPER_SCAN_PACKAGE);
        return mapperScannerConfigurer;
    }
}
