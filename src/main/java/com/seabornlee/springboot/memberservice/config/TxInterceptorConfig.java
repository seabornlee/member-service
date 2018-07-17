package com.seabornlee.springboot.memberservice.config;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

@Configuration
@AutoConfigureAfter(MybatisConfiguration.class)
public class TxInterceptorConfig {

    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.xxx.xxx..service.*.*(..))";
    @Autowired
    private PlatformTransactionManager transactionManager;

    // 创建事务通知
    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("delete*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("remove*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("tx*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("query*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        TransactionInterceptor tsi = new TransactionInterceptor(transactionManager,properties);
        return tsi;
    }

    @Bean
    public Advisor txAdviceAdvisor(TransactionInterceptor txAdvice) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
        return new DefaultPointcutAdvisor(pointcut, txAdvice);
    }
}
