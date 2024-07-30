package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @Qualifier("pool2") // чтоб в конструкторе ломбока (@RequiredArgsConstructor) подтянулось значение в поле, нужно создать в корне проекта файл "lombok.config" и добавить в него нужные аннотации ломбока (Qualifier, Value и т.д.) с полными путями до этих аннотаций
    private final ConnectionPool connectionPool;

}
