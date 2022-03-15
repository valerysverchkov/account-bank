package ru.iteco.accountbank.config;

import java.lang.reflect.Method;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import ru.iteco.accountbank.model.annotation.EncryptResult;

@Slf4j
@Component
public class EncryptResultBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, final String beanName) throws BeansException {
        log.info("Encrypt process for bean: {}", beanName);
        for (Method method : bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(EncryptResult.class)) {
                log.info("Bean is need proxy");
                ProxyFactory proxyFactory = new ProxyFactory(bean);
                proxyFactory.addAdvice((MethodInterceptor) invocation -> {
                    log.info("Before call method in bean: {}", beanName);
                    Object proceed = invocation.proceed();
                    for (Method declaredMethod : invocation.getThis().getClass().getDeclaredMethods()) {
                        if (invocation.getMethod().getName().equals(declaredMethod.getName())
                                && AnnotationUtils.findAnnotation(declaredMethod, EncryptResult.class) != null) {
                            log.info("Call {}, result of witch need to be encrypted", invocation.getMethod().getName());
                            return encrypt(proceed);
                        }
                    }
                    return proceed;
                });
                return proxyFactory.getProxy();
            }
        }
        return bean;
    }

    @SneakyThrows
    private Object encrypt(Object proceed) {
        Cipher cipher = Cipher.getInstance("AES");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        cipher.init(Cipher.ENCRYPT_MODE, keyGenerator.generateKey());
        byte[] bytes = cipher.doFinal(SerializationUtils.serialize(proceed));
        return Base64.getEncoder().encodeToString(bytes);
    }
}
