package ru.iteco.accountbank.config;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.annotation.AnnotationUtils;
import ru.iteco.accountbank.model.annotation.CacheResult;

@Slf4j
public class CacheResultMethodInterceptor implements MethodInterceptor {

    private static final Map<String, Map<MethodArgs, Object>> CACHE = new ConcurrentHashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        final Method method = invocation.getMethod();
        boolean isAnnotationMethod = method.isAnnotationPresent(CacheResult.class);
        if (!isAnnotationMethod) {
            for (Method declaredMethod : invocation.getThis().getClass().getDeclaredMethods()) {
                if (method.getName().equals(declaredMethod.getName()) &&
                        AnnotationUtils.findAnnotation(declaredMethod, CacheResult.class) != null) {
                    isAnnotationMethod = true;
                    break;
                }
            }
        }
        if (isAnnotationMethod) {
            log.info("Method: {} annotated @CacheResult", method.getName());
            Map<MethodArgs, Object> methodArgsObjectMap = CACHE.get(method.getName());
            if (methodArgsObjectMap != null) {
                log.info("Method: {} has cache. Cache: {}", method.getName(), methodArgsObjectMap);
                final MethodArgs methodArgs = getMethodArgs(invocation.getArguments());
                log.info("Check cache result by method with args: {}({})", method.getName(), invocation.getArguments());
                Object result = methodArgsObjectMap.get(methodArgs);
                if (result != null) {
                    log.info("Return result from cache: method: {}({}), result: {}", method.getName(), invocation.getArguments(), result);
                    return result;
                } else {
                    log.info("Call original method and record result into cache");
                    result = invocation.proceed();
                    methodArgsObjectMap.put(methodArgs, result);
                    return result;
                }
            } else {
                log.info("Method: {} not cache.", method.getName());
                Object result = invocation.proceed();
                methodArgsObjectMap = new ConcurrentHashMap<>();
                methodArgsObjectMap.put(
                        getMethodArgs(invocation.getArguments()),
                        result
                );
                CACHE.put(method.getName(), methodArgsObjectMap);
                return result;
            }
        }
        return invocation.proceed();
    }

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
