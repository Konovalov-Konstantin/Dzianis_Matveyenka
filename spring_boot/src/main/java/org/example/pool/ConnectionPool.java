package org.example.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component("pool1")
@RequiredArgsConstructor
public class ConnectionPool {

    /** чтоб в конструкторе ломбока (@RequiredArgsConstructor) в это поле подтянулось значение из "application.yml (application.properties)",
     * нужно создать в корне проекта файл "lombok.config" и добавить в него нужные аннотации ломбока (Qualifier, Value и т.д.)
     * с полными путями до этих аннотаций (см. урок 35.Lombok **/
    @Value("${db.username}")
    private final String username;
    @Value("${db.pool.size}")
    private final Integer poolSize;

    @PostConstruct
    private void init(){
        log.warn(" *** ConnectionPool init method " + this);
    }

    @PreDestroy
    private void destroy(){
        log.info(" *** ConnectionPool destroy method " + this);
    }
}
