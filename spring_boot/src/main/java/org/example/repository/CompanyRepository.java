package org.example.repository;

import lombok.RequiredArgsConstructor;
import org.example.database.entity.Company;
import org.example.pool.ConnectionPool;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CompanyRepository {

    @Qualifier("pool1") // чтоб в конструкторе ломбока (@RequiredArgsConstructor) подтянулось значение в поле, нужно создать в корне проекта файл "lombok.config" и добавить в него нужные аннотации ломбока (Qualifier, Value и т.д.) с полными путями до этих аннотаций
    private final ConnectionPool connectionPool;

    private final List<ConnectionPool> pools;

    @Value("${db.pool.size}")    // чтоб в конструкторе ломбока (@RequiredArgsConstructor) в это поле подтянулось значение из "application.properties", нужно создать в корне проекта файл "lombok.config" и добавить в него нужные аннотации ломбока (Qualifier, Value и т.д.) с полными путями до этих аннотаций
    private final Integer poolSize;

    public List<ConnectionPool> getPools() {
        return pools;
    }

    public Optional<Company> findById(Integer id){
        return Optional.of(new Company(1, "NewCompany"));
    }

}
