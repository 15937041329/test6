package com.test.demo.publicres.config;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Collections;

/**
 * @program: Test
 * @description: 通过Aop配置事务管理
 * @author: Ban shifeng
 * @create: 2019-08-07 10:48
 **/
@Aspect
@Configuration
public class TransactionConfig {
    private static final String AOP_POINTCUT_EXPRESSION = "execution (* com.test.demo.*.impl.*.*(..))";

    @Autowired
    private PlatformTransactionManager transactionManager;

    /**
     * 注册事务拦截器
     *
     * @return 返回一个带有拦截指定开头的方法名开启事务
     */
    @Bean
    public TransactionInterceptor txAdvice() {

        /*
         * 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
         */
        RuleBasedTransactionAttribute requireRule = new RuleBasedTransactionAttribute();
        requireRule.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));// 抛出异常后执行切点回滚
        requireRule.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        /*
         * 如果当前存在事务，则加入该事务；如果当前没有事务，则创建一个新的事务
         */
        RuleBasedTransactionAttribute requireRule_readOnly = new RuleBasedTransactionAttribute();
        requireRule_readOnly.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        requireRule_readOnly.setReadOnly(true);// 只读

        //设置切入方法名前缀
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("insert*", requireRule);
        source.addTransactionalMethod("add*", requireRule);
        source.addTransactionalMethod("update*", requireRule);
        source.addTransactionalMethod("modify*", requireRule);
        source.addTransactionalMethod("delete*", requireRule);
        source.addTransactionalMethod("remove*", requireRule);
        source.addTransactionalMethod("query*", requireRule_readOnly);
        source.addTransactionalMethod("get*", requireRule_readOnly);

        return new TransactionInterceptor(transactionManager, source);
    }

    /**
     * 为上述事务拦截器添加顾问,指定切点
     *
     * @return
     */
    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);// 添加切点表达式
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}