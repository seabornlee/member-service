package com.seabornlee.springboot.memberservice.config;

import org.apache.commons.lang.StringUtils;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@AutoConfigureAfter(MybatisConfig.class)
@ConfigurationProperties(prefix = "spring.transaction")
public class TxInterceptorConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.seabornlee.springboot.memberservice.service..*.*(..))";
    private static final String SUFFIX = "*";
    private String aopPointcutExpression;

    private Map<String,String> txAdviceMap;
    @Autowired
    private PlatformTransactionManager transactionManager;

    public String getAopPointcutExpression() {
        return aopPointcutExpression;
    }

    public TxInterceptorConfig setAopPointcutExpression(String aopPointcutExpression) {
        this.aopPointcutExpression = aopPointcutExpression;
        return this;
    }

    public Map<String, String> getTxAdviceMap() {
        return txAdviceMap;
    }

    public TxInterceptorConfig setTxAdviceMap(Map<String, String> txAdviceMap) {
        this.txAdviceMap = txAdviceMap;
        return this;
    }

    // 创建事务通知
    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() throws Exception {
        Properties properties = new Properties();
        /*properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("remove*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("tx*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("query*", "PROPAGATION_REQUIRED,-Exception,readOnly");*/
        if(null!=txAdviceMap&&txAdviceMap.size()>0){
            for(Map.Entry<String,String> entry : txAdviceMap.entrySet()){
                properties.setProperty(entry.getKey()+SUFFIX, entry.getValue());
            }
        }
        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager,properties);
        return tsi;
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(StringUtils.isBlank(aopPointcutExpression)?AOP_POINTCUT_EXPRESSION:aopPointcutExpression);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
}
