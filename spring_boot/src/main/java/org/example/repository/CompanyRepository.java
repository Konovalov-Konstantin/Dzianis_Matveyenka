package org.example.repository;

import lombok.Getter;
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
@Getter
public class CompanyRepository {

    /** чтоб в сгенерированном ломбоком конструкторе (@RequiredArgsConstructor) в поля userName и poolSize подтянулись значения (@Value)
     * из "application.yml (application.properties)", нужно создать в корне проекта файл "lombok.config" и добавить в него нужные
     * аннотации ломбока (Qualifier, Value и т.д.) с полными путями до этих аннотаций (см. урок 35.Lombok) **/
    @Qualifier("pool1")
    private final ConnectionPool connectionPool;

    /** чтоб в сгенерированном ломбоком конструкторе (@RequiredArgsConstructor) в поля userName и poolSize подтянулись значения (@Value)
     * из "application.yml (application.properties)", нужно создать в корне проекта файл "lombok.config" и добавить в него нужные
     * аннотации ломбока (Qualifier, Value и т.д.) с полными путями до этих аннотаций (см. урок 35.Lombok) **/
    @Value("${db.pool.size}")
    private final Integer poolSize;
    private final List<ConnectionPool> pools;

    public Optional<Company> findById(Integer id){
        return Optional.of(new Company(1, "NewCompany"));
    }

}
