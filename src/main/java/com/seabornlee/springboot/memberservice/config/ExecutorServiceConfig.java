package com.seabornlee.springboot.memberservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@ConfigurationProperties
public class ExecutorServiceConfig {

    @Value("${thread.pool.corePoolSize}")
    private Integer corePoolSize;

    @Value("${thread.pool.maxPoolSize}")
    private Integer maxPoolSize;

    @Value("${thread.pool.keepAliveSeconds}")
    private Integer keepAliveSeconds;


    @Bean(value = "myThreadPoolExecutor")
    public ThreadPoolExecutor threadPoolTaskExecutor(){

        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setKeepAliveSeconds(keepAliveSeconds);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor.getThreadPoolExecutor();

    }

}
