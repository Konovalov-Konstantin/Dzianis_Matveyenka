package org.example.config;

import org.example.pool.ConnectionPool;
import org.example.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("web") // активируется, когда активирован профиль web (spring.profiles.active=web в application.properties)
public class WebConfig {

    @Bean
    ConnectionPool pool3(){
        return new ConnectionPool("pool3",10);
    }

    @Bean
    UserRepository userRepository3(ConnectionPool pool3) {
        return new UserRepository(pool3);
    }

}
