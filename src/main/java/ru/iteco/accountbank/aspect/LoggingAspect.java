package ru.iteco.accountbank.aspect;

import lombok.extern.slf4j.Slf4j;
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

    @Before("allMethod()")
    public void beforeAllGetMethodAdvice() {
        log.info("beforeAllGetMethodAdvice:: Call Get method");
    }

    @Pointcut("execution(public * get*(..))")
    public void allMethod() {}

}
