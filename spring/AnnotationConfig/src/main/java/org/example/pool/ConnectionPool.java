package org.example.pool;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;

@Component("pool1")
@Data
public class ConnectionPool {
    private final String username;
    private final Integer poolSize;

//    private final List<Object> args;
//    private final Map<String, Object> properties;


    public ConnectionPool(@Value("${db.username}") String username,
                          @Value("${db.poolSize}") Integer poolSize
//                          List<Object> args,
//                          Map<String, Object> properties
                          ) {
        this.username = username;
        this.poolSize = poolSize;
//        this.args = args;
//        this.properties = properties;
    }

    @PostConstruct // метод, который должен быть выполнен после создания бина, но перед его использованием (можно подкрутить настройки бина)
    private void init(){
        System.out.println(" *** ConnectionPool init method " + this);
    }

    @PreDestroy // метод, который должен быть вызван перед уничтожением объекта после закрытия спринг-контекста
    private void destroy(){
        System.out.println(" *** ConnectionPool destroy method " + this);
    }

//    public Map<String, Object> getProperties() {
//        return properties;
//    }
}
