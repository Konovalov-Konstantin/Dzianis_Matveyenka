package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    /** чтоб в сгенерированном ломбоком конструкторе (@RequiredArgsConstructor) в поля userName и poolSize подтянулись значения (@Value)
     * из "application.yml (application.properties)", нужно создать в корне проекта файл "lombok.config" и добавить в него нужные
     * аннотации ломбока (Qualifier, Value и т.д.) с полными путями до этих аннотаций (см. урок 35.Lombok) **/
    @Qualifier("pool2")
    private final ConnectionPool connectionPool;

}
