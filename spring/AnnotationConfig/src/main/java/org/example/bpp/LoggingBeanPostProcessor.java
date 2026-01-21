package org.example.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Component
public class LoggingBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> loggingBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException { // выполнится перед методом @PostConstruct в классе CatTestLogger
        if (bean.getClass().isAnnotationPresent(Logging.class)) {
            loggingBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        Class<?> beanClass = loggingBeans.get(beanName);

        if (beanClass != null) {
            // создание Proxy и подмена бинов нужно делать в методе postProcessAfterInitialization (не в postProcessBeforeInitialization). Иначе будет исключение.
            // в примере создается динамический proxy (через интерфейсы) при помощи JDK proxy. Лучше создавать proxy через настедование (cglib, Byte Buddy)
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    System.out.println("Logging method " + method.getName() + " of class " + beanClass.getName());
                    return method.invoke(bean, args);
                }
            });
        }
        return bean;
    }
}
