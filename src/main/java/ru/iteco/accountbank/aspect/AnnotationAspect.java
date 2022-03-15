package ru.iteco.accountbank.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(2)
@Slf4j
@Component
@Aspect
public class AnnotationAspect {

    @Before("@annotation(ru.iteco.accountbank.model.annotation.CacheResult)")
    public void beforeAllAnnotationCacheResultAdvice() {
        log.info("beforeAllAnnotationCacheResultAdvice:: call method mark @CacheResult");
    }

}
