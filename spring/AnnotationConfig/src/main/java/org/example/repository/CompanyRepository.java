package org.example.repository;

import org.example.database.entity.Company;
import org.example.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompanyRepository {

    private final ConnectionPool connectionPool;
    private final List<ConnectionPool> pools;
    private final Integer poolSize;

    public CompanyRepository(ConnectionPool pool1,
                             List<ConnectionPool> pools,
                             @Value("${db.poolSize}") Integer poolSize) {
        this.connectionPool = pool1;
        this.pools = pools;
        this.poolSize = poolSize;
    }

    public List<ConnectionPool> getPools() {
        return pools;
    }

    public Optional<Company> findById(Integer id){
        return Optional.of(new Company(1, "NewCompany"));
    }

}
