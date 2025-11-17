package org.example.database.repository;

import org.example.database.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

//    Company save(Company company);    // реализация стандартых CRUD операций есть в CrudRepository
//    Optional<Company> findById(Integer id);   // реализация стандартых CRUD операций есть в CrudRepository
//    void delete(Company company);     // реализация стандартых CRUD операций есть в CrudRepository

    /** документация с примерами запросов https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html */
    Optional<Company> findByName(String name);
    List<Company> findAllByNameContainingIgnoreCase(String fragment);      // Containing - аналог Like из SQL


    // запрос при помощи ручного составления SQL-запроса
    @Query("select c from Company c where c.name = :name")
    Optional<Company> findByNameQuery(String name);

}
