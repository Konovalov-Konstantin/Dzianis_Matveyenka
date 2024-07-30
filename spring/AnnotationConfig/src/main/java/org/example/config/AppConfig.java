package org.example.config;

import org.example.pool.ConnectionPool;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.*;

//@Import(AnyConfiguration.class) - для импорта конфиг-классов из пакетов/модулей, которые не попадают под сканирование контекста
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    ConnectionPool pool2 (@Value("${db.username}") String username) {
        return new ConnectionPool(username, 20);
    }

    @Bean("userRepository2")
    @Profile("prod")    // бин создастся, когда активирован профиль prod. Также используются логические (!prod, prod & dev, prod | dev )
//    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    UserRepository userRepository (ConnectionPool pool2){
        return new UserRepository(pool2);
    }
}
