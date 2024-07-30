package org.example;

import org.example.config.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

        DatabaseProperties databaseProperties = context.getBean(DatabaseProperties.class);

        databaseProperties.getProperties().forEach((s, o) -> {
            System.out.println(s + " - " + o);
        });

        System.out.println();
    }
}
