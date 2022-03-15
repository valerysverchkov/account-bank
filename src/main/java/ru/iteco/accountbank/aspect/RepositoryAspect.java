package ru.iteco.accountbank.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Aspect
public class RepositoryAspect {

    @Before("within(ru.iteco.accountbank.repository.*)")
    public void beforeAllRepositoryAdvice() {
        log.info("beforeAllRepositoryAdvice:: call method from repository package");
    }

}
