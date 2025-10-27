package org.example.config;

import org.example.pool.ConnectionPool;
import org.example.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class WebConfig {

    @Bean
    @Profile("web") // активируется, когда активирован профиль web (spring.profiles.active=web в application.properties)
    ConnectionPool pool4(){
        return new ConnectionPool("pool4",10);
    }

    @Bean
    UserRepository userRepository4() {
        return new UserRepository(pool4());
    }

}
