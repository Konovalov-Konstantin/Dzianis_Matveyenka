package org.example.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;
import java.util.Map;

// данные тянутся из application.yml по префиксу 'db' (видео_урок_38)
// также нужна аннотация @ConfigurationPropertiesScan над главным классом
// можно внедрить DatabaseProperties как бин в нужное место и геттерами вытаскивать нужные конфиги
@Value
@ConfigurationProperties(prefix = "db")
public class DatabaseProperties {

    String username;
    String password;
    String driver;
    String url;
    String hosts;
    PoolProperties pool;
    List<PoolProperties> pools;
    Map<String, Object> properties;

    @ConstructorBinding
    public DatabaseProperties(String username, String password, String driver, String url, String hosts,
                              PoolProperties pool, List<PoolProperties> pools, Map<String, Object> properties) {
        this.username = username;
        this.password = password;
        this.driver = driver;
        this.url = url;
        this.hosts = hosts;
        this.pool = pool;
        this.pools = pools;
        this.properties = properties;
    }

    @Value
    public static class PoolProperties {
        Integer size;
        Integer timeout;
    }
}
