package ru.iteco.accountbank.aspect;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class CacheResultAspect {

    private static final Map<String, Map<MethodArgs, Object>> CACHE = new ConcurrentHashMap<>();

    @Around("allMethodMarkCacheResultAnnotation()")
    public Object aroundAllMethodMarkCacheResultAnnotationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("Method: {} annotated @CacheResult", methodName);
        Map<MethodArgs, Object> methodArgsObjectMap = CACHE.get(methodName);
        if (methodArgsObjectMap != null) {
            log.info("Method: {} has cache. Cache: {}", methodName, methodArgsObjectMap);
            final MethodArgs methodArgs = getMethodArgs(proceedingJoinPoint.getArgs());
            log.info("Check cache result by method with args: {}({})", methodName, proceedingJoinPoint.getArgs());
            Object result = methodArgsObjectMap.get(methodArgs);
            if (result != null) {
                log.info("Return result from cache: method: {}({}), result: {}", methodName, proceedingJoinPoint.getArgs(), result);
                return result;
            } else {
                log.info("Call original method and record result into cache");
                result = proceedingJoinPoint.proceed();
                methodArgsObjectMap.put(methodArgs, result);
                return result;
            }
        } else {
            log.info("Method: {} not cache.", methodName);
            Object result = proceedingJoinPoint.proceed();
            methodArgsObjectMap = new ConcurrentHashMap<>();
            methodArgsObjectMap.put(
                    getMethodArgs(proceedingJoinPoint.getArgs()),
                    result
            );
            CACHE.put(methodName, methodArgsObjectMap);
            return result;
        }
    }

    @Pointcut("@annotation(ru.iteco.accountbank.model.annotation.CacheResult)")
    public void allMethodMarkCacheResultAnnotation() {}

    private MethodArgs getMethodArgs(Object[] args) {
        LinkedList<Object> linkedArgs = new LinkedList<>();
        Collections.addAll(linkedArgs, args);
        return new MethodArgs(linkedArgs);
    }

    private static class MethodArgs {

        private final LinkedList<Object> args;

        public MethodArgs(LinkedList<Object> args) {
            this.args = args;
        }

        public LinkedList<Object> getArgs() {
            return args;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            MethodArgs that = (MethodArgs) o;

            return args.equals(that.args);
        }

        @Override
        public int hashCode() {
            return args.hashCode();
        }

        @Override
        public String toString() {
            return "MethodArgs{" +
                    "args=" + args +
                    '}';
        }
    }

}
