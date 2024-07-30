package org.example.repository;

import org.example.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final ConnectionPool connectionPool;

    public UserRepository(@Qualifier("pool1") ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }
}
