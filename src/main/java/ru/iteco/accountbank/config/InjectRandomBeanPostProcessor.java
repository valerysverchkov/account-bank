package ru.iteco.accountbank.config;

import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import ru.iteco.accountbank.model.annotation.InjectRandom;

@Slf4j
@Component
public class InjectRandomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        log.info("beanName: {}; beanClass: {}", beanName, bean.getClass().getSimpleName());

        for (Field declaredField : bean.getClass().getDeclaredFields()) {
            if (declaredField.isAnnotationPresent(InjectRandom.class)) {
                declaredField.setAccessible(true);
                InjectRandom annotation = declaredField.getAnnotation(InjectRandom.class);
                int random = getRandom(annotation.min(), annotation.max());
                log.info("Set random value in {}; Value: {}", declaredField.getName(), random);
                ReflectionUtils.setField(declaredField, bean, random);
            }
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private static int getRandom(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}
