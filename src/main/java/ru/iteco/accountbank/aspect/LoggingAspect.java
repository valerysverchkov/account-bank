package ru.iteco.accountbank.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Slf4j
@Component
@Aspect
public class LoggingAspect {

    @Before("allGetMethod() || allSetMethod()")
    public void beforeAllGetMethodAdvice(JoinPoint joinPoint) {
        log.info("beforeAllGetOrSetMethodAdvice:: Call method: {} with arguments: {}", joinPoint.toShortString(), joinPoint.getArgs());
    }

    @Before("allMethodInService()")
    public void beforeAllMethodInServiceAdvice(JoinPoint joinPoint) {
        log.info("beforeAllMethodInServiceAdvice:: START {}", joinPoint.getSignature().toShortString());
    }

    @After("allMethodInService()")
    public void afterAllMethodInServiceAdvice(JoinPoint joinPoint) {
        log.info("afterAllMethodInServiceAdvice:: END {}", joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(value = "allMethodInService()", throwing = "exception")
    public void afterAllMethodInServiceThrowAdvice(JoinPoint joinPoint, Exception exception) {
        log.info("afterAllMethodInServiceThrowAdvice:: END {} WITH THROW: {}", joinPoint.getSignature().toShortString(), exception.toString());
    }

    @Pointcut("within(ru.iteco.accountbank.service.*)")
    public void allMethodInService() {};

    @Pointcut("execution(public * get*(..))")
    public void allGetMethod() {}

    @Pointcut("execution(public * set*(..))")
    public void allSetMethod() {}

}
