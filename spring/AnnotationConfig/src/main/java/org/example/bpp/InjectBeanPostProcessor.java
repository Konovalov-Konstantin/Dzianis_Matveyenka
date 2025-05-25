package org.example.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ReflectionUtils;

import java.util.Arrays;

public class InjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException { // выполнится перед методом @PostConstuct в классе CatRepository

        Arrays.stream(bean.getClass().getDeclaredFields())
                .filter(fileld -> fileld.isAnnotationPresent(InjectBean.class))
                .forEach(fileld -> {
                    Object beanToInject = applicationContext.getBean(fileld.getType()); // поиск подходящего бина в контексте (типа CatConnectionPool.class)
                    ReflectionUtils.makeAccessible(fileld);                     // вместо fileld.setAccessible(true), чтоб не обрабатывать Exception
                    ReflectionUtils.setField(fileld, bean, beanToInject);       // вместо fileld.set(bean, beanToInject), чтоб не обрабатывать Exception
                });

        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
