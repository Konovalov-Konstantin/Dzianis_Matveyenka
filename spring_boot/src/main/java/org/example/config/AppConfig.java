package org.example.config;

import org.example.pool.ConnectionPool;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

//@Import(AnyConfiguration.class) - для импорта конфиг-классов из пакетов/модулей, которые не попадают под сканирование контекста
@Configuration
//@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    ConnectionPool pool2 (@Value("${db.username}") String username, @Value("${db.pool.size}") int poolsize) {
        return new ConnectionPool(username, poolsize);
    }

    @Bean("userRepository2")
    @Profile("prod")    // бин создастся, когда активирован профиль prod. Также используются логические (!prod, prod & dev, prod | dev )
//    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    UserRepository userRepository (ConnectionPool pool2){
        return new UserRepository(pool2);
    }
}
