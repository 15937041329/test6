package com.test.demo.publicres.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
    private Logger logger = LoggerFactory.getLogger(TransactionConfig.class);
    private ThreadLocal<Long> startTime = new ThreadLocal<>();
    /**
     * @Description: 定义切入点
     * @Title: pointCut
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:17
     */
    //被注解CustomAopAnnotation表示的方法
    //@Pointcut("@annotation(com.only.mate.springboot.annotation.CustomAopAnnotation")
    @Pointcut("execution (* com.test.demo.*.impl.*.*(..))")
    public void pointCut(){

    }

    /**
     * @Description: 定义前置通知
     * @Title: before
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:23
     * @param joinPoint
     * @throws Throwable
     */
    @Before("pointCut()")
    public void before(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());
        // 接收到请求，记录请求内容
        logger.info("【注解：Before】------------------切面  before");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        logger.info("【注解：Before】浏览器输入的网址=URL : " + request.getRequestURL().toString());
        logger.info("【注解：Before】HTTP_METHOD : " + request.getMethod());
        logger.info("【注解：Before】IP : " + request.getRemoteAddr());
        logger.info("【注解：Before】执行的业务方法名=CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("【注解：Before】业务方法获得的参数=ARGS : " + Arrays.toString(joinPoint.getArgs()));

    }

    /**
     * @Description: 后置返回通知
     * @Title: afterReturning
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:30
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "pointCut()")
    public void afterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("【注解：AfterReturning】这个会在切面最后的最后打印，方法的返回值 : " + ret);
        logger.info("【注解：AfterReturning】花费时间 : " + (System.currentTimeMillis() - startTime.get()) + "毫秒");
        startTime.remove();
    }

    /**
     * @Description: 后置异常通知
     * @Title: afterThrowing
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:37
     * @param jp
     */
    @AfterThrowing("pointCut()")
    public void afterThrowing(JoinPoint jp){
        logger.info("【注解：AfterThrowing】方法异常时执行.....");
    }

    /**
     * @Description: 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     * @Title: after
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:48
     * @param jp
     */
    @After("pointCut()")
    public void after(JoinPoint jp){
        logger.info("【注解：After】方法最后执行.....");
    }

    /**
     * @Description: 环绕通知,环绕增强，相当于MethodInterceptor
     * @Title: around
     * @author OnlyMate
     * @Date 2018年9月10日 下午3:52:56
     * @param pjp
     * @return
     */
    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) {
        logger.info("【注解：Around . 环绕前】方法环绕start.....");
        try {
            //如果不执行这句，会不执行切面的Before方法及controller的业务方法
            Object o =  pjp.proceed();
            logger.info("【注解：Around. 环绕后】方法环绕proceed，结果是 :" + o);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }
}