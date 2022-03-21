package ru.iteco.accountbank.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.iteco.accountbank.model.ExternalInfo;

@Component
@Aspect
@Slf4j
public class CheckRequestAspect {

    @Value("${id-not-process}")
    private Integer id;

    @Around("allMethodMarkCheckRequestAnnotation(externalInfo)")
    public Object aroundAllMethodMarkCheckRequestAnnotationAndHaveExternalInfoInArg(ProceedingJoinPoint proceedingJoinPoint,
                                                                                  ExternalInfo externalInfo) throws Throwable {
        log.info("CHECK REQUEST: {} with {}", proceedingJoinPoint.getSignature().toShortString(), externalInfo);
        if (!id.equals(externalInfo.getId())) {
            log.info("ALLOW REQUEST");
            return proceedingJoinPoint.proceed();
        } else {
            throw new RuntimeException("Decline request;");
        }
    }

    @Pointcut("@annotation(ru.iteco.accountbank.model.annotation.CheckRequest) && args(externalInfo, ..)")
    public void allMethodMarkCheckRequestAnnotation(ExternalInfo externalInfo) {
    }

}
