package org.example.config;

import org.example.bpp.InjectBeanPostProcessor;
import org.example.bpp.LoggingBeanPostProcessor;
import org.example.pool.ConnectionPool;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

//@Import(AnyConfiguration.class) - для импорта других @Configuration классов из пакетов/модулей, которые не попадают под сканирование контекста
@Configuration  // JAVA based конфигурация (аннотации @Configuration, @Bean)
@PropertySource("classpath:application.properties") // откуда тянуть property
public class AppConfig {

    @Bean   // аннотация @Bean используется в классах, помеченных @Configuration
    ConnectionPool pool2 (@Value("${db.username}") String username) {
        return new ConnectionPool(username.concat("2"), 20);
    }

    @Bean
    ConnectionPool pool3 () {
        return new ConnectionPool( "postgres3", 30);
    }

    @Bean("userRepository20")
    @Profile("prod")    // бин создастся, когда активирован профиль prod. Также используются логические (!prod, prod & dev, prod | dev )
//    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    UserRepository userRepository (ConnectionPool pool2){
        return new UserRepository(pool2);
    }

    @Bean
    InjectBeanPostProcessor injectBeanPostProcessor() {
        return new InjectBeanPostProcessor();
    }

    @Bean
    LoggingBeanPostProcessor loggingBeanPostProcessor() {
        return new LoggingBeanPostProcessor();
    }
}
