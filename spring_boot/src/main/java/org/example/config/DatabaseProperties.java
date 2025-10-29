package org.example.config;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import java.util.List;
import java.util.Map;

// данные тянутся из application.yml по префиксу 'db' (видео_урок_38)
// также нужна аннотация @ConfigurationPropertiesScan над главным классом (@SpringBootApplication) либо @Component над данным классом
// Можно внедрить DatabaseProperties как бин в нужное место и геттерами вытаскивать нужные конфиги
@Value  // включает в себя @Getter @FieldDefaults(makeFinal=true, level=AccessLevel.PRIVATE) @AllArgsConstructor @ToString @EqualsAndHashCode.
@ConfigurationProperties(prefix = "db") // в параметрах указывается префикс из application.yml
public class DatabaseProperties {

    String username;    // названия полей должны совпадать с названиями в application.yml, тогда значения подставятся корректно
    String password;
    String driver;
    String url;
    String hosts;
    PoolProperties pool;
    List<PoolProperties> pools;
    Map<String, Object> properties;

    @ConstructorBinding // указывает, что этот конструктор использовать для маппинга свойств конфигурации с помощью аргументов конструктора
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
